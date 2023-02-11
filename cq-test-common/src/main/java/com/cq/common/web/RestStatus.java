package com.cq.common.web;

public interface RestStatus {
    /**
     * the status codes of per restful request.
     *
     * @return 20xxx if succeed, 40xxx if client error, 50xxx if server side crash.
     */
    int code();

    /**
     * 枚举名字
     *
     * @return status enum name
     */
    String name();

    /**
     * 枚举对应消息
     *
     * @return 消息描述
     */
    String message();

    /**
     * 多语言消息
     *
     * @param lang 语言参数
     * @return
     */
    String langMessage(String lang);
}
