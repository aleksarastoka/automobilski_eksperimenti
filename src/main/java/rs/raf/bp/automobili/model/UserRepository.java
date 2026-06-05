package rs.raf.bp.automobili.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserRepository {

    private static final String USERS_FILE = "users.txt";

    public static boolean login(String username, String password) {
        makeFileIfMissing();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean usernameExists(String username) {
        makeFileIfMissing();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 1 && parts[0].equals(username)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean register(String username, String password) {
        makeFileIfMissing();
        if (usernameExists(username)) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + ";" + password);
            writer.newLine();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void makeFileIfMissing() {
        File file = new File(USERS_FILE);
        if (file.exists()) {
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("admin;admin");
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private UserRepository() {
    }
}
