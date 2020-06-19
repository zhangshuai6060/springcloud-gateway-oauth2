package com.example.springcloudalibabaoauthauth.util;

import com.example.springcloudalibabaoauthauth.result.ResultCode;
import com.google.common.collect.ImmutableMap;
import lombok.ToString;


/**
 * Created by admin on 2018/3/5.
 */
@ToString
public enum AuthCode implements ResultCode {
    AUTH_USERNAME_NONE(false, 23001, "请输入账号！"),
    AUTH_PASSWORD_NONE(false, 23002, "请输入密码！"),
    AUTH_VERIFYCODE_NONE(false, 23003, "请输入验证码！"),
    AUTH_ACCOUNT_NOTEXISTS(false, 23004, "账号不存在！"),
    AUTH_CREDENTIAL_ERROR(false, 23005, "账号或密码错误！"),
    AUTH_LOGIN_ERROR(false, 23006, "登陆过程出现异常请尝试重新操作！"),
    AUTH_LOGIN_APPLYTOKEN_FAIL(false, 23007, "登录失败!"),
    AUTH_LOGIN_TOKEN_SAVEFAIL(false, 23008, "Redis存储失败"),
    AUTH_RESCAV_THE_QR_CODE(false, 23009, "请重新扫描二维码"),
    AUTH_UNBOUND_USER(false, 23010, "您在当前系统中没有绑定用户"),
    AUTH_USER_LOCK(false, 23011, "当前用户已被禁用,请联系管理员!"),
    AUTH_THE_DISTANCE_IS_TOO_LARGE(false, 23012, "距离大于2公里,校验失败"),
    AUTH_ABNORMAL(false, 230013, "登陆过程出现异常请联系管理员！"),
    AUTH_WRONG_PASSWORD(false, 230014, "密码错误！");


    //操作代码
    boolean success;

    //操作代码
    int code;
    //提示信息
    String message;

    private AuthCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private static final ImmutableMap<Integer, AuthCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, AuthCode> builder = ImmutableMap.builder();
        for (AuthCode commonCode : values()) {
            builder.put(commonCode.code(), commonCode);
        }
        CACHE = builder.build();
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
