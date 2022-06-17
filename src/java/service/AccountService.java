package service;

/**
 *
 * @author Paolo
 */
public class AccountService {
    
    String username1 = "abe";
    String username2 = "barb";
    
     
    public User login(String username, String password) {
        if (username.equals(username1) && password.equals("password")) {
            return new User(username, null);
        } else if (username.equals(username2) && password.equals("password")) {
            return new User(username, null);
        }
        return null;
    }
    
}
