package com.example.springcloudalibabaoauthauth.result;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by mrt on 2018/5/21.
 */
@Data
@ToString
@NoArgsConstructor
public class LoginResult extends ResponseResult {
    public LoginResult(ResultCode resultCode, String access_token, String refresh_token) {
        super(resultCode);
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }

    public LoginResult(ResultCode resultCode, String access_token) {
        super(resultCode);
        this.access_token = access_token;
    }

    public LoginResult(ResultCode resultCode) {
        super(resultCode);
    }


    private String refresh_token;
    private String access_token;

}
