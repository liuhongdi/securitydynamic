package com.securitydynamic.demo.exception;

import com.securitydynamic.demo.constant.ResponseCode;

/**
 * 业务逻辑异常
 */
public class BusinessException extends RuntimeException{

    //返回响应的代码和提示
    private ResponseCode rcode;
    public BusinessException(ResponseCode rcode) {
        this.rcode = rcode;
    }

    public ResponseCode getResponseCode() {
        return this.rcode;
    }
    public void setResponseCode(ResponseCode rcode) {
        this.rcode = rcode;
    }
}
