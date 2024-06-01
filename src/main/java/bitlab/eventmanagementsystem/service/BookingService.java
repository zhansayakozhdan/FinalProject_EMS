package bitlab.eventmanagementsystem.service;

import bitlab.eventmanagementsystem.dto.BookingDto;
import bitlab.eventmanagementsystem.entity.Booking;
import bitlab.eventmanagementsystem.entity.Event;
import bitlab.eventmanagementsystem.entity.User;
import bitlab.eventmanagementsystem.mapper.BookingMapper;
import bitlab.eventmanagementsystem.repository.BookingRepository;
import bitlab.eventmanagementsystem.repository.EventRepository;
import bitlab.eventmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final EventService eventService;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public Booking getBookingById(Long id){
        return bookingRepository.findById(id).orElseThrow();
    }

    public Booking getBookingByIdForView(Long id){
        return bookingRepository.findById(id).orElseThrow();
    }

    public Booking makeBooking(BookingDto bookingDto){
        Booking booking = BookingMapper.INSTANCE.toEntity(bookingDto, userRepository, eventRepository);
        User user = userService.getUserByEmail(bookingDto.getUserEmail());
        Event event = eventService.getEventById(bookingDto.getEventId());
        if(user == null || event == null){
            return null;
        }
        booking.setUser(user);
        String result = decreaseTicketQuantity(event, bookingDto.getNumberOfReservedSeats());
        if (result.equals("Successfully booked!")) {
            log.info("Success booking: bookingId{}", bookingDto.getId());
            booking.setEvent(event);
            eventService.editEvent(event);
            booking.setStatus("В ПРОЦЕССЕ");
        } else {
            return null;
        }
        booking.setTotalPrice(countTotalCost(bookingDto));
        return bookingRepository.save(booking);
    }

    public void cancelBookingById(Long id){
        log.info("Cancel booking: bookingId{}", id);
        Booking booking = getBookingById(id);
        if(booking == null){
            return;
        }
        Event event = eventService.getEventById(booking.getEvent().getId());
        increaseTicketQuantity(event, booking.getNumberOfReservedSeats());
        eventService.editEvent(event);
        bookingRepository.deleteById(id);
    }

    public void increaseTicketQuantity(Event event, int returnedTicket) {
        log.info("Increase ticket quantity of {} to {}", event.getQuantityOfTickets(), returnedTicket);
        event.setQuantityOfTickets(event.getQuantityOfTickets() + returnedTicket);
    }

    public String decreaseTicketQuantity(Event event, int reservedTicket) {
        if (event.getQuantityOfTickets() >= reservedTicket) {
            event.setQuantityOfTickets(event.getQuantityOfTickets() - reservedTicket);
            log.info("Successfully decreased ticket quantity");
            return "Successfully booked!";
        } else if (event.getQuantityOfTickets() < reservedTicket && event.getQuantityOfTickets() != 0) {
            return "There are only" + event.getQuantityOfTickets() + "tickets left";
        } else {
            return "There are no tickets left!";
        }
    }

    public Double countTotalCost(BookingDto bookingDto){
        Event event = eventService.getEventById(bookingDto.getEventId());
        double eventCost = event.getTicketCost();
        int numberOfTicket = bookingDto.getNumberOfReservedSeats();
        double totalPrice = eventCost * numberOfTicket;
        log.info("Total cost of booking {} is {}", bookingDto.getId(), eventCost);
        return totalPrice;
    }

    public List<Booking> getAllBookingsByUserId(Long userId) {
        log.info("Get all Bookings by userId: {}", userId);
        return bookingRepository.findAllBookingsByUserId(userId);
    }

    public List<Booking> getAllBookingsByEventId(Long eventId) {
        Event event = eventService.getEventById(eventId);
        if (event == null) {
            return null;
        } else {
            return bookingRepository.findAllBookingsByEventId(eventId);
        }
    }
}
