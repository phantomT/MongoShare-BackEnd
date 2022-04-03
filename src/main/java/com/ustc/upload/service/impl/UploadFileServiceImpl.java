package com.ustc.upload.service.impl;

import com.google.common.collect.Interner;
import com.ustc.chain.core.HandlerInitializer;
import com.ustc.chain.core.Pipeline;
import com.ustc.chain.core.ResponsibleChain;
import com.ustc.chain.handler.*;
import com.ustc.chain.param.ChunkRequest;
import com.ustc.chain.param.MergeRequest;
import com.ustc.entity.Chunk;
import com.ustc.entity.MergeFileBean;
import com.ustc.lock.Lock;
import com.ustc.upload.dao.DiskMd5Dao;
import com.ustc.upload.service.UploadFileService;
import com.ustc.utils.SpringContentUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author 田宝宁
 */
@Component
@Transactional
public class UploadFileServiceImpl implements UploadFileService {
    @Autowired
    private SpringContentUtils scu;
    @Autowired
    private DiskMd5Dao diskMd5Dao;

    @Override
    public void uploadChunk(Chunk chunk) throws SolrServerException, IOException {
        // 1. 参数转换, 将chunk转换为责任链中的Request
        ChunkRequest chunkRequest = new ChunkRequest();
        BeanUtils.copyProperties(chunk, chunkRequest);

        // 2. 创建责任链的启动类
        ResponsibleChain responsibleChain = new ResponsibleChain();

        // 3. 组装责任链
        responsibleChain.loadHandler(new HandlerInitializer(chunkRequest, null) {
            @Override
            protected void initChannel(Pipeline line) {
                // 3.1 参数校验
                line.addLast(scu.getHandler(ChunkValidateHandler.class));
                // 3.2 校验是否支持对应格式
                // line.addLast(new ChunkFileSuffixHandler());
                // 3.3 切块存储
                line.addLast(scu.getHandler(ChunkStoreHandler.class));
                // 3.4 保存记录至Redis
                line.addLast(scu.getHandler(ChunkRedisHandler.class));
            }
        });
        // 4. 执行职责链
        responsibleChain.execute();
    }

    @Override
    public Integer checkFile(String fileMd5) {
        boolean md5IsExist = diskMd5Dao.findMd5IsExist(fileMd5);
        return md5IsExist ? 1 : 0;
    }


    /**
     * 如果是文件上传, 锁的名字为 filemd5
     * 如果是文件夹上传, 则锁的名字为 userid-folderid-rootname
     *
     * @param bean
     */
    @Override
    public void mergeChunk(MergeFileBean bean) throws SolrServerException, IOException {
        // 1. 获取文件的md5, 将其当作锁
        String lockname = bean.getFilemd5();
        System.out.println(bean.getFilemd5());
        // 2. 确保相同的字符串指向常量池中的同一个对象
        Interner<String> lock = Lock.getStringPool();

        // 3. 如果是文件夹
        if (!bean.getRelativepath().isEmpty()) {
            String[] names = bean.getRelativepath().split("/");
            String userid = bean.getUserid();
            String folderid = bean.getPid();

            lockname = userid + "-" + folderid + "-" + names[0];
        }
        // 上锁
        synchronized (lock.intern(lockname)) {
            MergeRequest mergeRequest = new MergeRequest();
            BeanUtils.copyProperties(bean, mergeRequest);

            ResponsibleChain chain = new ResponsibleChain();
            chain.loadHandler(new HandlerInitializer(mergeRequest, null) {
                @Override
                protected void initChannel(Pipeline line) {
                    //1.基本参数的校验
                    line.addLast(scu.getHandler(MergeValidateHandler.class));
                    //2.检验容量是否足够
                    line.addLast(scu.getHandler(MergeCapacityIsEnoughHandler.class));
                    //3.从Redis获取切块记录
                    line.addLast(scu.getHandler(MergeGetChunkHandler.class));
                    //4.校验文件是否完整
                    line.addLast(scu.getHandler(MergeFileIsCompleteHandler.class));
                    //5.校验md5是否已经在disk_md5表存在
                    line.addLast(scu.getHandler(MergeFileIsExistHandler.class));
                    //6.保存disk_md5表
                    line.addLast(scu.getHandler(MergeSaveToDiskMd5Handler.class));
                    //7.保存disk_chunk表
                    line.addLast(scu.getHandler(MergeSaveToDiskMd5ChunkHandler.class));
                    //8.如果是文件夹上传，则先创建文件夹
                    line.addLast(scu.getHandler(MergeCreateFolderHandler.class));
                    //9.保存disk_file表
                    line.addLast(scu.getHandler(MergeSaveToDiskFileHandler.class));
                    //10.新增Solr, 方便后面进行文件搜索
//                    line.addLast(scu.getHandler(MergeSolrHandler.class));
                    //11.删除Redis记录
                    line.addLast(scu.getHandler(MergeDelRedisHandler.class));
                }
            });
            chain.execute();
        }
    }
}
