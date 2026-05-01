package payment;

import Test.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class paymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    private double amount;
    private String status;
    private String subscriptionType;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private long durationInDays;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}