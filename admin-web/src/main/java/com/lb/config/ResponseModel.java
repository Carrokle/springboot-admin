package com.lb.config;

import java.io.Serializable;

import lombok.Data;

/**
 * Date: 2019-2-28
 * Description:
 */
@Data
public class ResponseModel<T> implements Serializable {
    private static final long serialVersionUID = -1241360949457314497L;
    private int status;
    private T result;
    private String message;
    private String code;

    public ResponseModel() {
       /* HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        response.setCharacterEncoding("UTF-8");*/
    }

    @Override
    public String toString() {
        return "ResponseModel [status=" + this.status + ", result=" + this.result +  ", message=" + this.message + ", code=" + this.code + "]";
    }
}
