package bitlab.eventmanagementsystem.mapper;

import bitlab.eventmanagementsystem.dto.BookingDto;
import bitlab.eventmanagementsystem.dto.CommentDto;
import bitlab.eventmanagementsystem.entity.Booking;
import bitlab.eventmanagementsystem.entity.Comment;
import bitlab.eventmanagementsystem.entity.Event;
import bitlab.eventmanagementsystem.entity.User;
import bitlab.eventmanagementsystem.repository.EventRepository;
import bitlab.eventmanagementsystem.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "user.email", target = "userEmail")
    BookingDto toDto(Booking booking);

    @Mapping(target = "event", expression = "java(mapToEvent(bookingDto, eventRepository))")
    @Mapping(target = "user", expression = "java(mapToUser(bookingDto, userRepository))")
    Booking toEntity(BookingDto bookingDto, UserRepository userRepository, EventRepository eventRepository);
    default User mapToUser(BookingDto bookingDto, UserRepository userRepository) {
        return userRepository.findByEmail(bookingDto.getUserEmail()).orElse(null);
    }

    default Event mapToEvent(BookingDto bookingDto, EventRepository eventRepository) {
        return eventRepository.findById(bookingDto.getEventId()).orElse(null);
    }
}
