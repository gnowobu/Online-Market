package com.tzy.Service;

public class ServiceException extends RuntimeException {
    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable e){
        super(msg, e);
    }
}
