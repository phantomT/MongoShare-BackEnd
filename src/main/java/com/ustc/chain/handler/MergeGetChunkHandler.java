package com.ustc.chain.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.MergeRequest;
import com.ustc.entity.RedisChunkTemp;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 从Redis获取切块记录
 * @author 田宝宁
 */
@Component
public class MergeGetChunkHandler extends Handler {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws JsonProcessingException {
        if (request instanceof MergeRequest) {
            MergeRequest bean = (MergeRequest) request;

            // key: 用户id-uuid-文件id-文件名-块序号, 不需要块序号, 模糊查询, 找出所有块
            String userid = bean.getUserid();
            String uuid = bean.getUuid();
            String fileId = bean.getFileid();
            String filename = bean.getFilename();
            List<RedisChunkTemp> temps = new ArrayList<>();

            // 通过模糊查询将存在redis中的块记录取出来
            String key = userid + "-" + uuid + "-" + fileId + "-" + filename + "-*";
            Set<String> keys = stringRedisTemplate.keys(key);
            if (keys != null) {
                for (String tempKey : keys) {
                    String redisChunkTempStr = stringRedisTemplate.opsForValue().get(tempKey);
                    RedisChunkTemp redisChunkTemp = JsonUtils.jsonToPojo(redisChunkTempStr, RedisChunkTemp.class);
                    temps.add(redisChunkTemp);
                }
            }

            bean.setChunkTemps(temps);
            this.updateRequest(request);
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
