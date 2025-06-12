package org.example.post.ui;

import lombok.RequiredArgsConstructor;
import org.example.common.ui.Response;
import org.example.post.repository.post_queue.UserPostQueueQueryRepository;
import org.example.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(required = false) Long lastPostId
    ) {
        List<GetPostContentResponseDto> result = userPostQueueQueryRepository.getContentResponse(
                userId,
                lastPostId
        );
        return Response.ok(result);
    }
}
