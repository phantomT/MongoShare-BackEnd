package com.ustc.chain.core;

import java.io.IOException;

/**
 * 责任链中的处理器
 *
 * @author 田宝宁
 */
public abstract class Handler {
    /**
     * 下一个handler
     */
    private Handler next;

    public void updateRequest(ContextRequest request) {
        ContextHolder.clearReq();
        ContextHolder.setTlReq(request);
    }

    public void updateResponse(ContextResponse response) {
        ContextHolder.clearResp();
        ContextHolder.setTlResp(response);
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    public boolean hasNext() {
        return next != null;
    }

    /**
     * 执行具体的处理
     *
     * @param request  请求
     * @param response 响应
     */
    public abstract void doHandler(ContextRequest request, ContextResponse response) throws IOException, InterruptedException;
}
