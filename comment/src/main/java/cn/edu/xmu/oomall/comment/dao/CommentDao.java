package cn.edu.xmu.oomall.comment.dao;

import cn.edu.xmu.oomall.comment.dao.bo.Comment;
import cn.edu.xmu.oomall.comment.mapper.po.CommentPo;
import cn.edu.xmu.oomall.comment.mapper.Commentmapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CommentDao {
    @Autowired
    private Commentmapper commentmapper;

    public CommentPo CreateComment(Comment commentBo)
    {
        CommentPo commentpo =new CommentPo();
        BeanUtils.copyProperties(commentBo,commentpo);
        return commentmapper.saveComment(commentpo);
    }

    public CommentPo findById(Long id)
    {
        Optional<CommentPo> commentPo = commentmapper.findById(id);
        if (commentPo == null) {
            return null;
        }
        Comment commentBo = new Comment();
        BeanUtils.copyProperties(commentPo,commentBo);
        return commentBo;
    }

}
