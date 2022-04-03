package com.ustc.chain.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.ChunkRequest;
import com.ustc.entity.RedisChunkTemp;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.utils.JsonUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;


/**
 * 将切块存储到Redis
 * @author 田宝宁
 */
@Component
public class ChunkRedisHandler extends Handler {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws JsonProcessingException {
        if (request instanceof ChunkRequest) {
            ChunkRequest chunk = (ChunkRequest) request;

            RedisChunkTemp temp=new RedisChunkTemp();
            temp.setUserid(chunk.getUserid());
            temp.setId(chunk.getId());
            temp.setName(chunk.getName());
            temp.setStorepath(chunk.getStorePath());
            temp.setTypecode(chunk.getTypecode());
            temp.setFilesuffix(FilenameUtils.getExtension(chunk.getName()));
            temp.setSize(chunk.getSize());
            temp.setChunks(chunk.getChunks());
            temp.setCurrentsize(chunk.getBytes().length);
            temp.setChunk(chunk.getChunk());
            /*
              key: 用户id-uuid-文件id-文件名-块序号
             */
            String key = chunk.getUserid() + "-" + chunk.getUuid() + "-" + chunk.getId() + "-" + chunk.getName()
                    + "-" + chunk.getChunk();
            System.out.println("chunk Redis Key: "+key);
            stringRedisTemplate.opsForValue().set(key, JsonUtils.objectToJson(temp), 30, TimeUnit.MINUTES);
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
