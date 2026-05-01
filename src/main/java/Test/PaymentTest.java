package Test;

import java.time.LocalDateTime;
import java.time.Duration;

public class PaymentTest {

    public static void main(String[] args) {

        // Test 1 - Unregistered user - 3 days
        System.out.println("===== TEST 1 - Unregistered User =====");
        testPayment(1, false, "",
                LocalDateTime.of(2025, 5, 1, 8, 0, 0),
                LocalDateTime.of(2025, 5, 4, 8, 0, 0));

        // Test 2 - Registered Monthly
        System.out.println("===== TEST 2 - Registered Monthly =====");
        testPayment(2, true, "MONTHLY",
                LocalDateTime.of(2025, 5, 1, 8, 0, 0),
                LocalDateTime.of(2025, 5, 30, 8, 0, 0));

        // Test 3 - Registered Weekly
        System.out.println("===== TEST 3 - Registered Weekly =====");
        testPayment(3, true, "WEEKLY",
                LocalDateTime.of(2025, 5, 1, 8, 0, 0),
                LocalDateTime.of(2025, 5, 7, 8, 0, 0));

        // Test 4 - Exit before Entry (error case)
        System.out.println("===== TEST 4 - Exit Before Entry =====");
        testPayment(4, false, "",
                LocalDateTime.of(2025, 5, 5, 8, 0, 0),
                LocalDateTime.of(2025, 5, 1, 8, 0, 0));
    }

    static void testPayment(int userId, boolean isRegistered, String subscriptionType,
                            LocalDateTime entryTime, LocalDateTime exitTime) {

        // validation
        if (exitTime.isBefore(entryTime)) {
            System.out.println("ERROR: Exit time cannot be before entry time");
            System.out.println();
            return;
        }

        // calculate duration
        Duration duration = Duration.between(entryTime, exitTime);
        long totalHours = duration.toHours();
        long totalDays = duration.toDays();

        if (totalDays == 0 && totalHours > 0) {
            totalDays = 1;
        }

        // calculate amount
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
                System.out.println("ERROR: Invalid subscription type");
                System.out.println();
                return;
            }
        }

        // print result
        System.out.println("User ID         : " + userId);
        System.out.println("Registered      : " + isRegistered);
        System.out.println("Subscription    : " + resolvedType);
        System.out.println("Entry Time      : " + entryTime);
        System.out.println("Exit Time       : " + exitTime);
        System.out.println("Duration (days) : " + totalDays);
        System.out.println("Amount          : $" + total);
        System.out.println("Status          : SUCCESS");
        System.out.println();
    }
}