package com.ustc.lock;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;

/**
 * 字符串常量池,将常量池中的字符串当作锁
 * @author 田宝宁
 */
public class Lock {
    private static volatile Interner<String> stringPool;

    public static Interner<String> getStringPool() {
        if (stringPool == null) {
            synchronized (Lock.class) {
                if (stringPool == null) {
                    stringPool = Interners.newWeakInterner();
                }
            }
        }
        return stringPool;
    }
}
