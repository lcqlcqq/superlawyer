package com.lcq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Role {
    /*
id-------------------主键
role_name-------角色名称
permission_id---权限类别（主要定义角色属于哪种层级）
create_time------创建时间
is_deleted--------状态（0：未删除 1：删除）
*/
    @TableId(type = IdType.AUTO)
    private Long id;
    private String roleName;
    private String description;
    private Long createTime;
    private Integer isDeleted;

}
