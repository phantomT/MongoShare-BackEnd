package com.ustc.chain.core;

/**
 * handler初始化器， 初始化责任链中的请求和响应
 * @author 田宝宁
 */
public abstract class HandlerInitializer extends PipelineImpl {
    public HandlerInitializer(ContextRequest request, ContextResponse response) {
        ContextHolder.setTlReq(request);
        ContextHolder.setTlResp(response);
    }
    protected abstract void initChannel(Pipeline line);
}
