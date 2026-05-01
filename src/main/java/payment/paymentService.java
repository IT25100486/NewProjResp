package payment;

import Test.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Duration;

@Service
public class paymentService {

    @Autowired
    private paymentRepository paymentRepository;

    public paymentEntity makePayment(
            int userId,
            boolean isRegistered,
            String subscriptionType,
            LocalDateTime entryTime,
            LocalDateTime exitTime
    ) {
        Duration duration = Duration.between(entryTime, exitTime);
        long totalHours = duration.toHours();
        long totalDays = duration.toDays();

        if (totalDays == 0 && totalHours > 0) {
            totalDays = 1;
        }

        double total = 0;
        String resolvedType = "";

        if (!isRegistered) {
            total = 25.0 * totalDays;
            resolvedType = "UNREGISTERED";
        } else {
            if (subscriptionType.equalsIgnoreCase("MONTHLY")) {
                total = 230.0;
                resolvedType = "MONTHLY";
            } else if (subscriptionType.equalsIgnoreCase("WEEKLY")) {
                total = 50.0;
                resolvedType = "WEEKLY";
            } else {
                throw new RuntimeException("Invalid subscription type: " + subscriptionType);
            }
        }

        User user = new User();
        user.setId(userId);

        paymentEntity payment = new paymentEntity();
        payment.setUser(user);
        payment.setAmount(total);
        payment.setStatus("SUCCESS");
        payment.setSubscriptionType(resolvedType);
        payment.setEntryTime(entryTime);
        payment.setExitTime(exitTime);
        payment.setDurationInDays(totalDays);

        return paymentRepository.save(payment);
    }
}