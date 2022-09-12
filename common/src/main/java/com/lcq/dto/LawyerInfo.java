package com.lcq.dto;

import lombok.Data;

@Data
public class LawyerInfo {
    /**
     * 用户部分
     */
    private Long registerTime;
    private String nickname;
    private String mail;
    private String phoneNumber;
    private String avatar;
    private String sign;

    /**
     * 律师认证信息
     */
    private String name;
    private Integer authState;
}
