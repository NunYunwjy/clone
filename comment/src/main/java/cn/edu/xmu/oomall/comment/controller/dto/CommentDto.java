package cn.edu.xmu.oomall.comment.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CommentDto {

    private Long id;

    private Long parentCommentId;

    private Long productId;

    private Long orderItemId;

    private Integer status;//-1表示未审核，0表示审核未通过，1表示审核通过，2表示已隐藏

    private String content;

    private String result;//审核意见
}
