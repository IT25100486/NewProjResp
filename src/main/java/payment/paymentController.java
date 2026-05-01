package payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/payments")
public class paymentController {

    @Autowired
    private paymentService paymentService;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @PostMapping("/pay")
    public paymentEntity pay(
            @RequestParam int userId,
            @RequestParam boolean isRegistered,
            @RequestParam(required = false, defaultValue = "") String subscriptionType,
            @RequestParam String entryTime,
            @RequestParam String exitTime
    ) {
        LocalDateTime entry = LocalDateTime.parse(entryTime, FORMATTER);
        LocalDateTime exit  = LocalDateTime.parse(exitTime, FORMATTER);

        if (exit.isBefore(entry)) {
            throw new RuntimeException("Exit time cannot be before entry time");
        }

        return paymentService.makePayment(userId, isRegistered, subscriptionType, entry, exit);
    }
}