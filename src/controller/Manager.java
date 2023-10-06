package controller;

import common.Library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.User;

import view.Menu;

public class Manager extends Menu<String>{
    static String[] mc = {"Create a new account", "Login System", "Exit"};
    
    private Library library;
    
    public Manager(User user) {
        super("===================== USER MANAGEMENT SYSTEM =====================", mc);
        library = new Library();
    }

    
    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                createNewAccount();
                break;
            case 2:
                loginSystem();
                break;
            case 3:
                System.out.println("Exit the program successfully!");
                System.exit(0);
                break;
        }
    }   
    
    public  void createNewAccount() {
        //check file data exist or not
        if (!library.checkFileExist()) {
            return;
        }
        String username = library.checkInputUsername();
        String password = library.checkInputPassword();
        //check username exist or not
        if (!library.checkUsernameExist(username)) {
            System.err.println("Username exist.");
            return;
        }
        addAccountData(username, password);
    }

    //login system
    public  void loginSystem() {
        //check file data exist or not
        if (!library.checkFileExist()) {
            return;
        }
        String username = library.checkInputUsername();
        String password = library.checkInputPassword();
        //check username exist or not
        if (!library.checkUsernameExist(username)) {
            String passwordByUsername = passwordByUsername(username);
            if (passwordByUsername == null) {
              System.err.println("Username does not exist.");
            } else if (!password.equalsIgnoreCase(passwordByUsername)) {
              System.err.println("Password incorrect.");
              System.err.println("Login failed.");
            } else {
              System.err.println("Login success.");
            }
        }
    }

    //write new account to data
    public void addAccountData(String username, String password) {
        File file = new File("data/user.dat");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(username + ";" + password + "\n");
            fileWriter.close();
            System.err.println("Create successly.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //get password by username
    public String passwordByUsername(String username) {
        File file = new File("data/user.dat");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] account = line.split(";");
                if (username.equalsIgnoreCase(account[0])) {
                    return account[1];
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
