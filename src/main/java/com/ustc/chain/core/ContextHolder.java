package com.ustc.chain.core;

/**
 * @author 田宝宁
 */
public class ContextHolder {
    // 使用ThreadLocal来管理Request和Response

    private static ThreadLocal<ContextRequest> tlReq = new ThreadLocal<>();
    private static ThreadLocal<ContextResponse> tlResp = new ThreadLocal<>();

    public static void setTlReq(ContextRequest req) {
        tlReq.set(req);
    }

    public static ContextRequest getReq(){
        return tlReq.get();
    }

    public static void clearReq(){
        tlReq.remove();
    }

    public static void setTlResp(ContextResponse resp) {
        tlResp.set(resp);
    }

    public static ContextResponse getResp() {
        return tlResp.get();
    }

    public static void clearResp() {
        tlResp.remove();
    }
}
