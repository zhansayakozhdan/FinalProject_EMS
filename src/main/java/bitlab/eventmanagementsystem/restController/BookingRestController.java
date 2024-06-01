package bitlab.eventmanagementsystem.restController;

import bitlab.eventmanagementsystem.dto.BookingDto;
import bitlab.eventmanagementsystem.entity.Booking;
import bitlab.eventmanagementsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingRestController {
    private final BookingService bookingService;

    @GetMapping("{id}")
    public Booking getBookingById(@PathVariable Long id){
        return bookingService.getBookingByIdForView(id);
    }

    @PostMapping
    public Booking makeBooking(@RequestBody BookingDto bookingDto){
        return bookingService.makeBooking(bookingDto);
    }

    @GetMapping(params = "userId")
    public List<Booking> getBookingListByUserId(@RequestParam Long userId){
        return bookingService.getAllBookingsByUserId(userId);
    }

    @DeleteMapping ("/delete/{id}")
    public void cancelBookingById(@PathVariable Long id){
        bookingService.cancelBookingById(id);
    }
}
