package com.lcq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Project {
    /*
id-------------------主键
project_name-----------项目名称
project_type------------项目类型
company_id-----------企业id
service_id------------服务方案id
lawyer_id------------负责律师id
content---------------咨询内容
create_time------创建时间
ending_time-------结束时间
is_paid----------支付情况（0：未支付 1：已支付）
is_finished---------项目状态（0：待分配 1：已分配 2：已结束）
is_deleted--------状态（0：未删除 1：删除）
*/
    @TableId(type = IdType.AUTO)
    private Long id;
    private String projectName;
    private String projectType;
    private Long companyId;
    private Long serviceId;
    private Long lawyerId;
    private String content;
    private Long createTime;
    private Long endingTime;
    private Integer isPaid;
    private Integer isFinished;
    private Integer isDeleted;

}
