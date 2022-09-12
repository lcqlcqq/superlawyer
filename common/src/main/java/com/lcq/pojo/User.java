package com.lcq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {

    /*
id---------主键【bigint】
username--------账号【varchar】
password--------密码【varchar】
type--------用户类型（1企业、2律师、0管理）【int】
register_time--------注册时间【bigint】
nickname--------昵称【varchar】
mail--------邮箱【varchar】
phone_number--------手机【varchar】
gender--------性别【varchar】
avatar--------头像【varchar】
rank--------会员等级（0：无会员 1：。。。）【int】
last_login--------上次登录时间【bigint】
last_pos--------上次登录地点【varchar】
sign--------用户简介【varchar】
is_deleted--------状态（0：未删除 1：删除）【int】
*/
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private Integer type;
    private Long registerTime;
    private String nickname;
    private String mail;
    private String phoneNumber;
    private String gender;
    private String avatar;
    private Integer memberRank;
    private Long lastLogin;
    private String lastPos;
    private String sign;
    private Integer isDeleted;
}
