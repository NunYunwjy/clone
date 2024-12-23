package cn.edu.xmu.oomall.comment.mapper;

import cn.edu.xmu.oomall.comment.mapper.po.CommentPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Commentmapper extends JpaRepository<CommentPo, Long> {
    CommentPo saveComment(CommentPo commentPo);
}