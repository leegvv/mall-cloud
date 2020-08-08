package net.arver.mall.cloud.common.advice;

import net.arver.mall.cloud.common.exception.ExceptionEnum;
import net.arver.mall.cloud.common.exception.ServiceException;
import net.arver.mall.cloud.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理.
 * @author arver
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理.
     * @param e 异常
     * @return 统一异常结果
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionResult> handleException(final ServiceException e) {
        final ExceptionEnum em = e.getExceptionEnum();
        return ResponseEntity.status(em.getStatus()).body(new ExceptionResult(em));
    }
}
