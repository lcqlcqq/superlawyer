package com.lcq.param;

import lombok.Data;

@Data
public class RegisterParam {
    private String username;
    private String password;
    private Integer type;
    private String nickname;
    private String mail;
    private String phoneNumber;
    private String gender;
    private String avatar;
    private String sign;
}
