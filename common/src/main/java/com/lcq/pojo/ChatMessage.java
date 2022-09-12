package com.lcq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ChatMessage {
    /*
id-------------------主键
send_id---------------发送人id
to_id-----------接收人id
content------------消息内容
multimedia----------多媒体内容
send_time-----------发送时间
is_read------------接收者是否已读
is_deleted--------状态（0：未删除 1：删除）
*/
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long sendId;
    private Long toId;
    private String content;
    private String multimedia;
    private Long sendTime;
    private Integer isRead;
    private Integer isDeleted;
}
