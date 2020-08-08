package net.arver.mall.cloud.common.vo;

import net.arver.mall.cloud.common.exception.ExceptionEnum;

/**
 * 异常结果类.
 * @author arver
 */
public class ExceptionResult {

    /**
     * 状态.
     */
    private int status;

    /**
     * message.
     */
    private String message;

    /**
     * timestamp.
     */
    private Long timestamp;

    /**
     * 构造函数.
     * @param em 异常枚举
     */
    public ExceptionResult(final ExceptionEnum em) {
        this.status = em.getStatus();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }
}
