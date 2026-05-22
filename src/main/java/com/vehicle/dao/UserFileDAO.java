package com.vehicle.dao;

import com.vehicle.model.User;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserFileDAO {


    private static final String FILE_PATH = "D:\\SLIIT\\vehicle_data\\users.txt"; //set  data store path
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    // Ensure file and directory exist
    static {
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();

            // Create parent directories if they don't exist
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
                System.out.println("Created directory: " + parentDir.getAbsolutePath());
            }

            // Create file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Created file: " + file.getAbsolutePath());
            }

            // Calculate next ID from existing users
            int maxId = getAllUsers().stream().mapToInt(User::getId).max().orElse(0);
            idCounter.set(maxId + 1);

            System.out.println("Data file location: " + file.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error initializing data file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Read all users from file
    private static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return users;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    User user = User.fromString(line);
                    if (user != null) {
                        users.add(user);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // File will be created on write
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Write all users to file
    private static void saveAllUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
            System.out.println("Saved " + users.size() + " users to file");
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Create - Register new user
    public boolean registerUser(User user) {
        // Check if userId already exists
        if (getUserByUserId(user.getUserId()) != null) {
            System.out.println("Registration failed: User ID already exists - " + user.getUserId());
            return false;
        }

        List<User> users = getAllUsers();
        user.setId(idCounter.getAndIncrement());
        users.add(user);
        saveAllUsers(users);
        System.out.println("User registered successfully: " + user.getUserId());
        return true;
    }

    // Read - Login authentication
    public User login(String userId, String password) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
                System.out.println("Login successful: " + userId);
                return user;
            }
        }
        System.out.println("Login failed: " + userId);
        return null;
    }

    // READ - Get user by ID
    public User getUserById(int id) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    //Get user by userId
    public User getUserByUserId(String userId) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    //Update user profile
    public boolean updateUser(User updatedUser) {
        List<User> users = getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == updatedUser.getId()) {
                users.set(i, updatedUser);
                saveAllUsers(users);
                System.out.println("User updated: " + updatedUser.getUserId());
                return true;
            }
        }
        System.out.println("User update failed: User not found - ID: " + updatedUser.getId());
        return false;
    }

    //Delete user account
    public boolean deleteUser(int id) {
        List<User> users = getAllUsers();
        boolean removed = users.removeIf(user -> user.getId() == id);
        if (removed) {
            saveAllUsers(users);
            System.out.println("User deleted: ID " + id);
        } else {
            System.out.println("User delete failed: User not found - ID: " + id);
        }
        return removed;
    }
}