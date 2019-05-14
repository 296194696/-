package com.water.irrigation.dao.net.comment;

import com.water.irrigation.entity.net.comment.NetComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NetCommentDao extends JpaRepository<NetComment,Long>, JpaSpecificationExecutor<NetComment> {

    NetComment findByIndocno(Long indocno);
}
