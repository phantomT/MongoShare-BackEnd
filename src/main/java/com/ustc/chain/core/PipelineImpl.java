package com.ustc.chain.core;

/**
 * 处理管道实现类
 * @author 田宝宁
 */
public class PipelineImpl implements Pipeline{
    /**
     * 第一个handler
     */
    private Handler firstHandler;
    /**
     * 当前handler
     */
    private Handler curHandler;
    @Override
    public void addLast(Handler handler) {
        // 如果当前管道为空
        if (curHandler == null) {
            curHandler = handler;
            firstHandler = handler;
        } else {
            curHandler.setNext(handler);
            curHandler = handler;
        }
    }

    public Handler getFirstHandler() {
        return firstHandler;
    }

    public PipelineImpl setFirstHandler(Handler firstHandler) {
        this.firstHandler = firstHandler;
        return this;
    }

    public Handler getCurHandler() {
        return curHandler;
    }

    public PipelineImpl setCurHandler(Handler curHandler) {
        this.curHandler = curHandler;
        return this;
    }
}
