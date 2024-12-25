package cn.edu.xmu.oomall.comment.controller;

import cn.edu.xmu.oomall.comment.controller.dto.CommentDto;
import cn.edu.xmu.oomall.comment.controller.vo.CommentVo;
import cn.edu.xmu.oomall.comment.dao.CommentDao;
import cn.edu.xmu.oomall.comment.dao.bo.Comment;
import cn.edu.xmu.oomall.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    @Autowired
    private CommentService commentService;

    //创建评论
    @PostMapping("/{orderItemId}")
    public void createComment(@PathVariable Long orderItemId, @Valid @RequestBody CommentVo commentVo) {
        Comment commentBo = new Comment();
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(commentVo, commentDto);
        BeanUtils.copyProperties(commentDto, commentBo);
        commentService.createComment(orderItemId, commentBo);
    }

    @PutMapping("/{commentId}")
    public void reviewComment(@PathVariable Long commentId, @Valid Integer result, String message, @Valid @RequestBody CommentVo commentVo){
        Comment commentBo = new Comment();
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(commentVo, commentDto);
        BeanUtils.copyProperties(commentDto, commentBo);
        commentService.reviewComment(commentId, result, message, commentBo);
    }

    @PutMapping("/{commentId}")
    public void hideComment(@PathVariable Long commentId, @Valid @RequestBody CommentVo commentVo){
        Comment commentBo = new Comment();
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(commentVo, commentDto);
        BeanUtils.copyProperties(commentDto, commentBo);
        commentService.createComment(commentId, commentBo);
    }
}
