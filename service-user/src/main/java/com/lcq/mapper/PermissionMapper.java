package com.lcq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcq.pojo.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 查询用户对应的权限路径
     * @param username
     * @return
     */
    @Select("SELECT * FROM permission WHERE permission.id in \n" +
            "(SELECT permission_id FROM role_permission where role_id =\n" +
            "(SELECT user_role.role_id FROM user join user_role on user.id = user_role.user_id WHERE user.username = #{username}))")
    List<Permission> getUserPermission(@Param("username") String username);
}
