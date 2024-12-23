//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.order.dao.bo;
import cn.edu.xmu.oomall.comment.dao.CommentDao;
import cn.edu.xmu.oomall.comment.dao.bo.Comment;

import cn.edu.xmu.javaee.core.model.bo.OOMallObject;
import cn.edu.xmu.oomall.comment.mapper.po.CommentPo;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString(callSuper = true)
@NoArgsConstructor
public class OrderItem extends OOMallObject implements Serializable {

    @Builder
    public OrderItem(Long id, Long creatorId, String creatorName, Long modifierId, String modifierName, LocalDateTime gmtCreate, LocalDateTime gmtModified, Long orderId, Long onSaleId, Integer quantity, Long price, Long discountPrice, Long point, String name, Long actId, Long couponId, Byte commented) {
        super(id, creatorId, creatorName, modifierId, modifierName, gmtCreate, gmtModified);
        this.orderId = orderId;
        this.onSaleId = onSaleId;
        this.quantity = quantity;
        this.price = price;
        this.discountPrice = discountPrice;
        this.point = point;
        this.name = name;
        this.actId = actId;
        this.couponId = couponId;
        this.commented = commented;
    }

    @Setter
    @Getter
    private Long orderId;

    @Setter
    @Getter
    private Long onSaleId;

    @Setter
    @Getter
    private Integer quantity;

    @Setter
    @Getter
    private Long price;

    @Setter
    @Getter
    private Long discountPrice;

    @Setter
    @Getter
    private Long point;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Long actId;

    @Setter
    @Getter
    private Long couponId;

    @Setter
    @Getter
    private Byte commented;

    @Override
    public void setGmtCreate(LocalDateTime gmtCreate) {

    }

    @Override
    public void setGmtModified(LocalDateTime gmtModified) {

    }

    //创建评论
    private CommentDao commentDao;

    public void CreateComment(Comment commentBo)
    {
        try {
            commentBo.setOrderItemId(id);
            commentBo.setCreatorId(creatorId);
            commentBo.setCreatorName(creatorName);
            CommentPo result = commentDao.CreateComment(commentBo);
            if (result!= null) {
                OrderItem.super.setId(result.getId());
                System.out.println("评论成功！待审核");
            } else {
                System.out.println("评论失败！");
            }
        } catch (Exception e) {
            System.out.println("评论失败！");
            e.printStackTrace();
        }

    }
}
