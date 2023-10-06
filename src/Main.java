

import controller.Manager;
import model.User;

public class Main {

    public static void main(String[] args) {
        User user = new User();
        
        new Manager(user).run();
    }
    
}
