package com.lcq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("service")
@Data
public class Serv {/*
    id-------------------主键
    service_name------------服务方案名称
    service_rank------------服务等级
    charges----------收费标准
    service_summary-------------服务内容
    file_id---------------文件模板id
    create_time------创建时间
    is_deleted--------状态（0：未删除 1：删除）
    */
    @TableId(type = IdType.AUTO)
    private Long id;
    private String serviceName;
    private String serviceRank;
    private String charges;
    private String serviceSummary;
    private Long fileId;
    private Long createTime;
    private Integer isDeleted;


}
