//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.order.dao.bo;

import cn.edu.xmu.javaee.core.model.bo.OOMallObject;
import cn.edu.xmu.oomall.order.dao.openfeign.dto.CommentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString(callSuper = true)
@NoArgsConstructor
@Component
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

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void CreateComment(CommentDto commentDto) {
        try {
            String commentJson = objectMapper.writeValueAsString(commentDto);
            rocketMQTemplate.convertAndSend("comment-topic", commentJson);
        } catch (JsonProcessingException e) {
            // 处理序列化异常
            e.printStackTrace();
        }
    }

}
