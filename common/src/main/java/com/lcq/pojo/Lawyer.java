package com.lcq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Lawyer {
/*
id-------------------主键
user_id---------------用户id
name-----------律师姓名
mail---------------律师联系邮箱
phone-------------律师联系电话
auth_state--------认证状态（0：未认证 1：已认证）
auth_file----------认证文件
is_deleted--------状态（0：未删除 1：删除）
*/
@TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String name;
    private String mail;
    private String phone;
    private Integer authState;
    private Integer authFile;
    private Integer isDeleted;
}
