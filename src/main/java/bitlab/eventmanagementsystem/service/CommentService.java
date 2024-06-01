package bitlab.eventmanagementsystem.service;

import bitlab.eventmanagementsystem.dto.CommentDto;
import bitlab.eventmanagementsystem.entity.Comment;
import bitlab.eventmanagementsystem.mapper.CommentMapper;
import bitlab.eventmanagementsystem.repository.CommentRepository;
import bitlab.eventmanagementsystem.repository.EventRepository;
import bitlab.eventmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
       private final CommentRepository commentRepository;
       private final UserRepository userRepository;
       private final EventRepository eventRepository;

       public Comment addComment(CommentDto commentDto){

           Comment comment = CommentMapper.INSTANCE.toEntity(commentDto, userRepository, eventRepository);
           return commentRepository.save(comment);
       }

       public List<CommentDto> getCommentsByEventId(Long eventId){
           List<Comment> commentList = commentRepository.findAllByEvent(eventId);
           List<CommentDto> commentDtoList = new ArrayList<>();
           for(Comment comment : commentList){
               CommentDto commentDto = CommentMapper.INSTANCE.toDto(comment);
               commentDtoList.add(commentDto);
           }
           return commentDtoList;
       }

       public CommentDto getCommentById(Long commentId){
           Comment comment = commentRepository.findById(commentId).orElse(null);
           CommentDto commentDto = CommentMapper.INSTANCE.toDto(comment);
           return commentDto;
       }

       public Comment updateComment(CommentDto commentDto){
           Comment comment = CommentMapper.INSTANCE.toEntity(commentDto, userRepository, eventRepository);
           return commentRepository.save(comment);
       }

       public void deleteComment(Long commentId){
           commentRepository.deleteById(commentId);
       }
}
