package net.miaohy.pb.exception;

import net.miaohy.pb.common.model.ResultCode;

public class PbException extends RuntimeException{

    private static final long serialVersionUID = 3309599586260030492L;

    private int code;

    public PbException(int code, String message) {
        super(message);
        this.code = code;
    }

    public PbException(String message) {
        super(message);
        this.code = ResultCode.FAIL.getCode();
    }

    public PbException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
