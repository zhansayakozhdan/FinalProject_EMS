package bitlab.eventmanagementsystem.mapper;

import bitlab.eventmanagementsystem.dto.CommentDto;
import bitlab.eventmanagementsystem.entity.Comment;
import bitlab.eventmanagementsystem.entity.Event;
import bitlab.eventmanagementsystem.entity.User;
import bitlab.eventmanagementsystem.repository.EventRepository;
import bitlab.eventmanagementsystem.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "user.email", target = "userEmail")
    CommentDto toDto(Comment comment);

    @Mapping(target = "event", expression = "java(mapToEvent(commentDto, eventRepository))")
    @Mapping(target = "user", expression = "java(mapToUser(commentDto, userRepository))")
    Comment toEntity(CommentDto commentDto, UserRepository userRepository, EventRepository eventRepository);

    default User mapToUser(CommentDto commentDto, UserRepository userRepository) {
        return userRepository.findByEmail(commentDto.getUserEmail()).orElse(null);
    }

    default Event mapToEvent(CommentDto commentDto, EventRepository eventRepository) {
        return eventRepository.findById(commentDto.getEventId()).orElse(null);
    }
}
