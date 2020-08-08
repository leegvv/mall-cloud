package net.arver.mall.cloud.common.exception;

/**
 * 自定义异常.
 * @author arver
 */
public class ServiceException extends RuntimeException {

    /**
     * 异常枚举.
     */
    private ExceptionEnum exceptionEnum;

    /**
     * 构造函数.
     * @param exceptionEnum 异常枚举类
     */
    public ServiceException(final ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    /**
     * 获取异常枚举了.
     * @return 异常枚举类
     */
    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
