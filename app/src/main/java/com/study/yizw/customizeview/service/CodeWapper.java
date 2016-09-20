package com.study.yizw.customizeview.service;

/**
 * 错误码包装类
 * <p>
 * Created by yizw on 2016/9/20 16:48.
 */

public class CodeWapper {

    /**
     * 针对服务器通用错误
     */
    public static final int SERVER_TYPE_COMMON = 1;

    /**
     * app服务器
     */
    public static final int SERVER_TYPE_APP = 1;

    /**
     * 统一登录服务器
     */
    public static final int SERVER_TYPE_SSO = 2;

    /**
     * 七牛服务器
     */
    public static final int SERVER_TYPE_QINIU = 3;


    /**
     * 实际转换后得到的错误码
     */
    public int code = ErrorCode.Code.SUCCESS;
    /**
     * 错误描述
     */
    public String desc;
    /**
     * 错误数据
     */
    public String data;

    /**
     * 服务器类型
     */
    public int serverType = SERVER_TYPE_COMMON;

    /**
     * 服务器返回的错误码
     */
    public String serverCode = "";

    public CodeWapper(int code, int serverType, String serverCode) {
        this.code = code;
        this.serverType = serverType;
        if (serverCode == null) {
            serverCode = "";
        }
        this.serverCode = serverCode;
    }

    public CodeWapper() {}

    @Override
    public String toString() {
        return "CodeWapper{" +
                "code=" + code +
                ", serverType=" + serverType +
                ", serverCode='" + serverCode + '\'' +
                ", desc='" + desc + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

}
