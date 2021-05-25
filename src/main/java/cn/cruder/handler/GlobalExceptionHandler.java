package cn.cruder.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * @Author Cruder 全局
 * @Date 2021/5/25 0025 22:14
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     *
     * @param e MethodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: ", e);
        BindingResult bindingResult = e.getBindingResult();
        HashMap<String, Object> errorResult = new HashMap<>();
        List<String> msgList = new ArrayList<String>();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            ObjectError error = errors.get(0);
            msgList.add(error.getDefaultMessage());
            String firstMsg = msgList.get(0);
            errorResult.put("status", "2001");
            errorResult.put("message", firstMsg);
        }
        return errorResult;
    }


    /**
     * 拦截捕捉 ConstraintViolationException 异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Object constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.error("ConstraintViolationException: ", ex);
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            System.err.println(cvl.getMessageTemplate());
            msgList.add(cvl.getMessageTemplate());
        }
        HashMap<String, Object> errorResult = new HashMap<>();
        errorResult.put("status", "2002");
        errorResult.put("message", msgList);
        return errorResult;
    }

    /**
     * 拦截捕捉 BindException 异常
     */
    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException ex) {
        log.error("BindException: ", ex);
        List<FieldError> bindingResult = ex.getBindingResult().getFieldErrors();
        List<String> msgList = new ArrayList<>();
        for (FieldError fieldError : bindingResult) {
            System.err.println(fieldError.getField() + " " + fieldError.getDefaultMessage());
            msgList.add(fieldError.getDefaultMessage());
        }
        String firstMsg = msgList.get(0);
        HashMap<String, Object> errorResult = new HashMap<>();
        errorResult.put("status", "2003");
        errorResult.put("message", firstMsg);
        return errorResult;
    }



}
