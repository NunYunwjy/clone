package cn.edu.xmu.oomall.comment.comsumer;

import cn.edu.xmu.oomall.comment.dao.CommentDao;
import cn.edu.xmu.oomall.comment.dao.bo.Comment;
import cn.edu.xmu.oomall.order.dao.openfeign.dto.CommentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "comment-topic", consumerGroup = "comment-consumer-group")
public class CommentConsumer {
    @Autowired
    private CommentDao commentDao;

    private ObjectMapper objectMapper = new ObjectMapper();

    Comment comment = new Comment();

    public void onMessage(String message) {
        try {
            CommentDto commentdto = objectMapper.readValue(message, CommentDto.class);
            BeanUtils.copyProperties(commentdto,comment);
            commentDao.CreateComment(comment);
        } catch (JsonProcessingException e) {
            // 处理反序列化异常
            e.printStackTrace();
        }
    }
}
