package payment;

import Test.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

@Service
public class paymentService {

    @Autowired
    private paymentRepository paymentRepository;

    public paymentEntity makePayment(int userId, boolean isRegistered, String subscriptionType, LocalDateTime entryTime, LocalDateTime exitTime) {
        long totalDays = calculateDays(entryTime, exitTime);
        double total = calculateCost(isRegistered, subscriptionType, totalDays);
        String resolvedType = !isRegistered ? "UNREGISTERED" : subscriptionType.toUpperCase();

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

    public List<paymentEntity> getAllPayments() {
        return paymentRepository.findAll();
    }

    public paymentEntity getPaymentById(int id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment record not found with ID: " + id));
    }

    public paymentEntity updatePayment(int id, double newAmount, String newStatus) {
        paymentEntity payment = getPaymentById(id);
        payment.setAmount(newAmount);
        payment.setStatus(newStatus);
        return paymentRepository.save(payment);
    }

    public void deletePayment(int id) {
        paymentEntity payment = getPaymentById(id);
        paymentRepository.delete(payment);
    }

    private long calculateDays(LocalDateTime entry, LocalDateTime exit) {
        Duration duration = Duration.between(entry, exit);
        long totalHours = duration.toHours();
        long totalDays = duration.toDays();
        if (totalDays == 0 && totalHours > 0) {
            totalDays = 1;
        }
        return totalDays;
    }

    private double calculateCost(boolean isRegistered, String subscriptionType, long totalDays) {
        if (!isRegistered) {
            return 25.0 * totalDays;
        }
        if (subscriptionType.equalsIgnoreCase("MONTHLY")) return 230.0;
        if (subscriptionType.equalsIgnoreCase("WEEKLY")) return 50.0;
        throw new RuntimeException("Invalid subscription type: " + subscriptionType);
    }
}
