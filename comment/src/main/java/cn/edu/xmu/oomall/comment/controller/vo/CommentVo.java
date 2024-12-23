package cn.edu.xmu.oomall.comment.controller.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CommentVo {

    @NotNull(message = "评论不能为空")
    private String content;

    private Integer status;//状态

    private Integer result;//审核结果:0 代表不通过，1 代表通过

    private String message;//审核意见
}
