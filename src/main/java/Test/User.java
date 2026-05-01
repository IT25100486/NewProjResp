package Test;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String vehicleNo;
    private String telNo;
    private String role;
    private String userId;
    private String password;

    public User() {}

    public User(int id, String name, String vehicleNo, String telNo, String role, String userId, String password) {
        this.id = id;
        this.name = name;
        this.vehicleNo = vehicleNo;
        this.telNo = telNo;
        this.role = role;
        this.userId = userId;
        this.password = password;
    }
}