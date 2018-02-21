package com.example.sample;

import java.util.Locale;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 *
 * @see java.util.logging.Logger
 * @see org.apache.commons.logging.Log
 * @see ch.qos.logback.classic.Logger
 * @see org.apache.log4j.Logger
 */
@Aspect
public class LoggingInterceptor {

    @Autowired
    MessageSource messageSource;

    @Pointcut("execution(public java.util.logging.Logger *(..))")
    private void anyJULLoggingMethod() { }

    @Pointcut("execution(public org.apache.commons.logging.Log *(..))")
    private void anyJCLLoggingMethod() { }

    @Pointcut("execution(public ch.qos.logback.classic.Logger *(..))")
    private void anyLogbackLoggerMethod() { }

    @Pointcut("execution(public org.apache.log4j.Logger *(..))")
    private void anyLog4j1And2LoggerMethod() { }

    @Pointcut("execution(public org.slf4j.Logger *(..))")
    private void anySLF4JLoggerMethod() { }

    @Pointcut("anyJULLoggingMethod() || anyJCLLoggingMethod() || anyLogbackLoggerMethod() || anyLog4j1And2LoggerMethod() || anySLF4JLoggerMethod()")
    private void anyLoggerMethod() { }

    @Around("anyLoggerMethod()")
    public Object addIdToMessage(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        String messageId;
        if (args != null || args[0] instanceof String) {
            messageId = (String) args[0];
            String message = messageSource.getMessage(messageId, null, Locale.getDefault());
            args[0] = messageId + message;
        }

        // 対象メソッドの実行
        Object result = point.proceed(args);

        // メソッド実行結果の返却
        return result;
    }
 }
