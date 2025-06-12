package org.example.post.ui;


import lombok.RequiredArgsConstructor;
import org.example.common.ui.Response;
import org.example.post.application.CommentService;
import org.example.post.application.dto.CreateCommentRequestDto;
import org.example.post.application.dto.LikeRequestDto;
import org.example.post.application.dto.UpdateCommentRequestDto;
import org.example.post.domain.comment.Comment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
        final Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updateComment(
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequestDto dto
    ) {
       final Comment comment = commentService.updateComment(commentId, dto);
       return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> unlike(@RequestBody LikeRequestDto dto) {
        commentService.likeComment(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> like(@RequestBody LikeRequestDto dto) {
        commentService.unlikeComment(dto);
        return Response.ok(null);
    }
}
