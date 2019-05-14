package com.water.irrigation.service.net.comment;

import com.water.irrigation.entity.dto.net.comment.NetCommentDto;
import com.water.irrigation.entity.net.comment.NetComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 评价信息服务层接口
 * @author lichunlei
 *
 */
public interface NetCommentService {

    /**
     * 根据条件获取对应的评级信息数据
     *
     * @param conditions 评价信息过滤条件
     * @param pageable   分页
     * @return 评级信息信息列表
     */
    Page<NetComment> findAll(NetCommentDto conditions, Pageable pageable);

    NetComment findOne(Long indocno);

    NetComment add(NetComment netComment,String username);

    NetComment update(NetComment netComment);

    void delete(List<NetComment> netComments);

    NetComment order(NetComment netComment);

    NetComment commit(NetComment netComment,String username);
}
