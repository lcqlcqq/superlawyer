package com.lcq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Company {
    /*
id-------------------主键
user_id---------------用户id
company_name-----------企业名称
company_type------------企业类型
company_summary-------------企业简介
company_logo-------------企业logo
address------------企业通讯地址
auth_state--------认证状态（0：未认证 1：已认证）
auth_file----------认证文件
register_capital------注册资本（钱）【bigint】
legal_representative -------法人
is_deleted--------状态（0：未删除 1：删除）
*/
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String companyName;
    private String companyType;
    private String companySummary;
    private String companyLogo;
    private String address;
    private Integer authState;
    private Integer authFile;
    private Long registerCapital;
    private String legalRepresentative;
    private Integer isDeleted;

}

