package cn.edu.xmu.oomall.comment.dao;

import cn.edu.xmu.oomall.comment.dao.bo.Comment;
import cn.edu.xmu.oomall.comment.mapper.po.CommentPo;
import cn.edu.xmu.oomall.comment.mapper.Commentmapper;
import cn.edu.xmu.oomall.order.dao.bo.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CommentDao {
    @Autowired
    private Commentmapper commentmapper;

    public void CreateComment(Comment commentBo)
    {
        CommentPo commentpo =new CommentPo();
        BeanUtils.copyProperties(commentBo,commentpo);
        CommentPo result = commentmapper.save(commentpo);
        if (result!= null) {
            System.out.println("评论成功！待审核");
        } else {
            System.out.println("评论失败！");
        }
    }
    public Comment findById(Long id)
    {
        Optional<CommentPo> commentPo = commentmapper.findById(id);
        if (commentPo == null) {
            return null;
        }
        Comment commentBo = new Comment();
        BeanUtils.copyProperties(commentPo,commentBo);
        return commentBo;
    }

    public CommentPo reviewComment(Comment commentBo)
    {
        CommentPo commentpo =new CommentPo();
        BeanUtils.copyProperties(commentBo,commentpo);
        return commentmapper.save(commentpo);
    }

    public CommentPo hideComment(Comment commentBo)
    {
        CommentPo commentpo =new CommentPo();
        BeanUtils.copyProperties(commentBo,commentpo);
        return commentmapper.save(commentpo);
    }
}

