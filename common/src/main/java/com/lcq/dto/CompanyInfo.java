package com.lcq.dto;

import lombok.Data;

@Data
public class CompanyInfo {
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
     * 企业认证信息部分
     */
    private String companyName;
    private String companyType;
    private String companySummary;
    private String companyLogo;
    private String address;
    private Integer authState;
    private Long registerCapital;
    private String legalRepresentative;
}
