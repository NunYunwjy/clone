package cn.edu.xmu.oomall.comment.service;

import cn.edu.xmu.oomall.comment.dao.CommentDao;
import cn.edu.xmu.oomall.comment.dao.bo.Comment;
import cn.edu.xmu.oomall.comment.mapper.po.CommentPo;
import cn.edu.xmu.oomall.order.dao.OrderDao;
import cn.edu.xmu.oomall.order.dao.bo.OrderItem;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private OrderDao orderDao;
    private CommentDao commentDao;


    public void createComment(Long orderItemId, Comment commentBo)
    {
        OrderItem orderItem = orderDao.findById(orderItemId);
        if (orderItem == null) {
             throw new IllegalArgumentException("Order item not found");
         }
        orderItem.CreateComment(commentBo);
    }

    @SneakyThrows
    public void reviewComment(Long id, Integer result, String message, Comment commentBo)
    {
        CommentPo comment = commentDao.findById(id);
        if (comment == null) {
            throw new IllegalArgumentException("Comment not found");
        }
        commentBo.reviewComment(id, result, message);
    }



    @SneakyThrows
    public void hideComment(Long id, Comment commentBo)
    {
        CommentPo comment = commentDao.findById(id);
        if (comment == null) {
            throw new IllegalArgumentException("Comment not found");
        }
        commentBo.hideComment(id);
    }
}
