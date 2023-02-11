package com.cq.common.web;


import com.google.common.collect.ImmutableMap;

/**
 * @author zhangzhiyu
 */
public enum RestStatusCode implements RestStatus {

    /**
     * <p>Field OK: 访问成功</p>
     */
    OK(20000, "请求成功"),
    
    
    /**
     * <p>Field OK: 访问成功</p>
     */
	CONFIRM(20001, "待确认"),

    /**
     * <p>Field BAD_REQUEST: 40xxx 客户端不合法的请求</p>
     */
    BAD_REQUEST(40000, "非法请求"),

    /**
     * <p>Field INVALID_MODEL_FIELDS: 字段校验非法</p>
     */
    INVALID_MODEL_FIELDS(40001, "字段校验非法"),

    /**
     * 参数类型非法，常见于SpringMVC中String无法找到对应的enum而抛出的异常
     */
    INVALID_PARAMS_CONVERSION(40002, "参数类型非法"),
    /**
     * 参数格式异常
     */
	INVALID_PARAMS_FORMAT(40003, "参数格式异常"),

    /**
     * <p>Field AUTHENTICED_FAILURE: 认证失败</p>
     */
    AUTHENTICED_FAILURE(40101, "用户名或者密码错误"),
    /**
     * <p>Field UNAUTHENTICED: 无效token</p>
     */
    UNAUTHENTICED(40102, "请重新登录。"),
    /**
     * <p>Field UNAUTHORIZED: 无权访问</p>
     */
    UNAUTHORIZED(40103, "无权访问"),

    /**
     * <p>Field SESSION_TIMEOUT: 无效验证码</p>
     */
    SESSION_TIMEOUT(40104, "无效验证码"),
    /**
     * <p>经销商登录失败</p>
     */
    AUTHENTICED_SST_FAILURE(40105, "经销商登录失败"),
    /**
     * <p>修改密码失败</p>
     */
    PWD_CHANGE_FAILURE(40106, "修改密码失败"),
    
    AUTHENTICED_REPLICATION(40107, "您的账号在其他地方登录，请重新登录。"),
    
    REQUEST_TIMEOUT(40800, "请求处理超时"),
    /**
     * http media type not supported
     */
    HTTP_MESSAGE_NOT_READABLE(41001, "HTTP消息不可读"),
    /**
     * a 获得分布式锁异常
     */
    LOCK_FAILED(41003, "锁定异常"),

    /**
     * 请求方式非法
     */
    REQUEST_METHOD_NOT_SUPPORTED(41002, "不支持的HTTP请求方法"),

    // 成功接收请求, 但是处理失败
    /**
     * 操作过快，请稍后再试
     */
    DUPLICATE_KEY(42001, "操作过快，请稍后再试"),
    /**
     * 数据操作失败
     */
    DUPLICATE_FAIL(42002, "操作失败"),
    /**
     * 重复下单
     */
    DUPLICATE_CREATE_ORDER(42003, "重复下单"),

    // 50xxx 服务端异常
    /**
     * 用于处理未知的服务端错误
     */
    SERVER_UNKNOWN_ERROR(50001, "服务端异常，请稍后再试"),

    // 60xxx 服务端异常
    /**
     * 用于处理未知的业务异常
     */
    BUSINESS_UNKNOWN_ERROR(60001, "不符合业务规则"),

    /**
     * 用于Feign调用导致的异常
     */
    FEIGN_UNKNOWN_ERROR(70001, "Feign调用异常"),

    /**
     * Zuul网关异常
     */
    ZUUL_ERROR(70002, "Zuul网关异常");
    /**
     * <p>Field CACHE: 遍历</p>
     */
    private static final ImmutableMap<Integer, RestStatusCode> CACHE;
    /**
     * <p>Field code: 返回代码</p>
     */
    private final int code;
    /**
     * <p>Field message: 返回消息</p>
     */
    private final String message;

    static {
        final ImmutableMap.Builder<Integer, RestStatusCode> builder = ImmutableMap.builder();
        for (RestStatusCode statusCode : values()) {
            builder.put(statusCode.code(), statusCode);
        }
        CACHE = builder.build();
    }

    /**
     * <p>Description: 构造方法</p>
     *
     * @param code    枚举code
     * @param message 枚举消息
     */
    RestStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * <p>Description: 获取枚举的某个实例</p>
     *
     * @param code 枚举code
     * @return 枚举实例
     */
    public static RestStatusCode valueOfCode(int code) {
        final RestStatusCode status = CACHE.get(code);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + code + "]");
        }
        return status;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String langMessage(String lang) {
        return null;
    }

}