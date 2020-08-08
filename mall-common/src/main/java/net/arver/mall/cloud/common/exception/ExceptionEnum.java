package net.arver.mall.cloud.common.exception;

/**
 * 异常枚举类.
 * @author arver
 */
public enum  ExceptionEnum {

    /**
     * 价格不能为空.
     */
    PRICE_CANNOT_BE_NULL(400, "价格不能为空");

    /**
     * 状态码.
     */
    private int status;

    /**
     * 消息.
     */
    private String msg;

    /**
     * 构造函数.
     * @param status 状态码
     * @param msg 消息
     */
    ExceptionEnum(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * getter.
     * @return 状态码
     */
    public int getStatus() {
        return status;
    }

    /**
     * getter.
     * @return 消息
     */
    public String getMsg() {
        return msg;
    }
}
