package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.MoveRequest;
import com.ustc.entity.DiskFile;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.filecommon.dao.FileDao;
import com.ustc.utils.FileType;
import org.apache.http.client.utils.DateUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @author 田宝宁
 * @date 2021/7/9
 */
@Component
public class MoveUpdateHandler extends Handler {
    @Autowired
    FileDao fileDao;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response)throws IOException {
        if (request instanceof MoveRequest) {
            MoveRequest moveRequest = (MoveRequest) request;
            String pid = moveRequest.getPid();

            // 依次处理每个记录
            for (String id : moveRequest.getIdList()) {
                // 根据id从数据库中取出相应要被移动的记录
                DiskFile record = fileDao.findOne(id);
                // 根据父文件夹id以及文件名找出是否存在同名文件或文件夹
                DiskFile sameNameRecord = fileDao.findRecordByPidAndName(record.getUserid(), pid, record.getFileName());
                record.setPid(pid);
                // 如果不存在同名记录, 则直接更新
                if (sameNameRecord == null) {
                    fileDao.updateRecord(record);
                    //存在同名记录, 则给该记录名加上时间戳
                } else {
                    record.setFileName(record.getFileName() + DateUtils.formatDate(new Date(), DateUtils.PATTERN_ASCTIME));
                    fileDao.updateRecord(record);
                }
            }
        } else {
            throw new ServiceException(ServiceExceptionEnum.MOVE_PARAM_ERROR);
        }
    }
}
