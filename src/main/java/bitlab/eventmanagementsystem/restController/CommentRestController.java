package bitlab.eventmanagementsystem.restController;

import bitlab.eventmanagementsystem.dto.CommentDto;
import bitlab.eventmanagementsystem.entity.Comment;
import bitlab.eventmanagementsystem.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentRestController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> getComments(@RequestParam Long eventId) {
        return commentService.getCommentsByEventId(eventId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentDto commentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        commentDto.setUserEmail(userEmail);
        return commentService.addComment(commentDto);
    }

    @PutMapping
    public Comment updateComment(@RequestBody CommentDto commentDto) {
        return commentService.updateComment(commentDto);
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

}
