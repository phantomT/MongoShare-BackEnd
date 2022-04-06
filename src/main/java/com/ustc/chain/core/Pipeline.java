package com.ustc.chain.core;

/**
 * 责任链中的处理管道
 * @author 田宝宁
 */
public interface Pipeline {
    void addLast(Handler handler);
}
