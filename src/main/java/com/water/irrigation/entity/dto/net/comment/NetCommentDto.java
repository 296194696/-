package com.water.irrigation.entity.dto.net.comment;

import com.water.irrigation.entity.net.comment.NetComment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class NetCommentDto extends NetComment {

    /**
     * 页数
     */
    private Integer pageno = 1;

    /**
     * 每页记录数
     */
    private Integer pagesize = 10;

    /**
     * 需要删除的数据列表
     */
    private List<NetComment> delLists;

    /**
     * 登录
     */
    private String username;
}
