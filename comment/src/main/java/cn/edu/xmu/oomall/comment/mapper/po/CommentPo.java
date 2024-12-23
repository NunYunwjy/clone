package cn.edu.xmu.oomall.comment.mapper.po;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 创建者id
     */
    private Long creatorId;

    /**
     * 创建者
     */
    private String creatorName;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;


    private Long parentCommentId;

    private Long orderItemId;

    private Integer status;//-1表示未审核，0表示审核未通过，1表示审核通过，2表示已隐藏

    private String content;

    private String message;//审核意见

}
