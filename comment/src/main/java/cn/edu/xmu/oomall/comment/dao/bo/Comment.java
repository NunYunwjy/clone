package cn.edu.xmu.oomall.comment.dao.bo;

import java.time.LocalDateTime;

import cn.edu.xmu.javaee.core.model.bo.OOMallObject;
import cn.edu.xmu.oomall.comment.dao.CommentDao;
import cn.edu.xmu.oomall.comment.mapper.po.CommentPo;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@ToString(callSuper = true)
@NoArgsConstructor
@Component
public class Comment extends OOMallObject implements Serializable{

    public Comment(Long id, Long creatorId, String creatorName, Long modifierId, String modifierName, LocalDateTime gmtCreate, LocalDateTime gmtModified, Long orderId, Long onSaleId, Integer quantity, Long price, Long discountPrice, Long point, String name, Long actId, Long couponId, Byte commented) {
        super(id, creatorId, creatorName, modifierId, modifierName, gmtCreate, gmtModified);
        this.parentCommentId=parentCommentId;
        this.content=content;
        this.message = message;
        this.status=status;
        this.orderItemId=orderItemId;
        this.productId=productId;
    }

    @Getter
    @Setter
    private Long parentCommentId;

    @Getter
    @Setter
    private Long orderItemId;

    @Getter
    @Setter
    private Long productId;


    @Getter
    @Setter
    private Integer status;//-1表示未审核，0表示审核未通过，1表示审核通过，2表示已隐藏

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private String message;//审核意见

    @Override
    public void setGmtCreate(LocalDateTime gmtCreate) {

    }

    @Override
    public void setGmtModified(LocalDateTime gmtModified) {

    }

    private CommentDao commentDao;

    //审核评论,result=0表示不通过，result=1表示通过
    public void reviewComment(Long commentId, Integer result, String message)
    {
        try {
            // 根据评论 ID 获取待审核的评论对象
            Comment comment = commentDao.findById(commentId);

            if (comment != null) {

                comment.setStatus(result);//result=0表示未通过，result=1表示通过
                if(message != null)
                {
                    comment.setMessage(message);//更新审核意见
                }
                // 根据更新结果输出相应的信息
                if (status != -1) {
                    if (status == 1) {
                        System.out.println("评论已审核通过！");
                        CommentPo commentPo = commentDao.reviewComment(comment);
                    } else if(status == 0){
                        System.out.println("评论未通过审核！");
                        CommentPo commentPo = commentDao.reviewComment(comment);
                    }
                } else {
                    System.out.println("评论审核失败！");
                }
            } else {
                System.out.println("找不到对应的评论！");
            }
        } catch (Exception e) {
            System.out.println("评论审核失败！");
            e.printStackTrace();
        }
    }

    //隐藏评论
    public void hideComment(Long commentId)
    {
        try {
            // 根据评论 ID 获取待审核的评论对象
            Comment comment = commentDao.findById(commentId);

            if (comment != null) {

                comment.setStatus(2);//更改评论状态，将其隐藏
                // 根据更新结果输出相应的信息
                if (status == 2) {

                    System.out.println("评论隐藏成功！");
                    CommentPo commentPo = commentDao.hideComment(comment);

                } else {
                    System.out.println("评论隐藏失败！");
                }
            } else {
                System.out.println("找不到对应的评论！");
            }
        } catch (Exception e) {
            System.out.println("评论隐藏失败！");
            e.printStackTrace();
        }
    }
}
