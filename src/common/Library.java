package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Library {
   
    private Scanner sc = new Scanner(System.in);
    private String VALID_USERNAME = "^\\S{5}\\S*$";
    private String VALID_PASSWORD = "^\\S{6}\\S*$";
    
    public int inputIntLimit(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }
    
    public String inputString() {
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty!");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }
    
    public int inputInt() {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine());
                if (result > 0) return result;
                else {
                    System.err.println("Must be input number > 0!");
                    System.out.print("Enter again: ");
                }
            } catch (NumberFormatException e) {
                System.err.println("Must be input number!");
                System.out.print("Enter again: ");
            }
        }
    }
  
    public boolean inputYesNo() {
        while (true) {
            String result = inputString().trim();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    
    //check file data exist or not
    public boolean checkFileExist() {
        File file = new File("data/user.dat");
        if (!file.exists()) {
            try {
                System.err.println("File not exist!!!");
                file.createNewFile();
                System.err.println("File created.");
                return false;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    //allow user input username
    public String checkInputUsername() {
        System.out.print("Enter username: ");
        //loop until user input correct
        while (true) {
            String result = inputString();
            if (result.matches(VALID_USERNAME)) {
                return result;
            }
            System.err.println("You must enter least at 5 character, and no space!");
            System.out.print("Enter again: ");
        }
    }

    //allow user input password
    public String checkInputPassword() {
        System.out.print("Enter password: ");
        //loop until user input correct
        while (true) {
            String result = inputString();
            if (result.matches(VALID_PASSWORD)) {
                return result;
            }
            System.err.println("You must enter least at 6 character, and no space!");
            System.out.print("Enter again: ");
        }
    }

    //check username exist
    public boolean checkUsernameExist(String username) {
        File file = new File("data/user.dat");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] account = line.split(";");
                if (username.equalsIgnoreCase(account[0])) {
                    return false;
                }
            }
            bufferedReader.close();
            fileReader.close();
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }

}
