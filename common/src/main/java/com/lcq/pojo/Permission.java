package com.lcq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Permission {
    /*
    id-------------------主键
permission_name---权限名称
remark-----------------说明
create_time----------创建时间
is_deleted---- -------状态（0：未删除 1：删除）
*/
    @TableId(type = IdType.AUTO)
    private Long id;
    private String permissionName;
    private String value;
    private String uri;
    private Long createTime;
    private Integer isDeleted;

}
