package com.vehicle.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String vehicleNo;
    private String telNo;
    private String role;
    private String userId;
    private String password;

    public User(int id, String name, String vehicleNo, String telNo, String role, String userId, String password) {
        this.id = id;
        this.name = name;
        this.vehicleNo = vehicleNo;
        this.telNo = telNo;
        this.role = role;
        this.userId = userId;
        this.password = password;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getTelNo() {
        return telNo;
    }

    public String getRole() {
        return role;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return id + "|" + name + "|" + vehicleNo + "|" + telNo + "|" +role + "|" + userId + "|" + password;
    }

    public static User fromString(String line){
        String[] parts = line.split("\\|");
        if(parts.length == 7){
            return new User(
               Integer.parseInt(parts[0]),
               parts[1],
               parts[2],
               parts[3],
               parts[4],
               parts[5],
               parts[6]
            );
        }
        return null;
    }
}
