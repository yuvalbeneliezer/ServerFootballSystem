package Security;

import java.util.HashMap;
import java.util.Map;

/**
 * this class responsible for the security of the users.
 * the passwords encrypted and saved for each user with AES algorithm.
 * Aouthors: Tair Cohen
 */
public class SecuritySystem {
    private Map<String, String> usersHashMap = new HashMap<>();
    private AESEncryption AES = new AESEncryption();
    private final String secretKey = "ssshhhhhhhhhhh!!!!";


    /**
     * this function add new user to the system.
     * the function is using the AES algorithm to encrypt the password/
     * the function checks if the user name already exist, and if so return false.
     *
     * @param userName
     * @param password
     * @return true - if the user name and password were adding successfully to the hash map,
     * otherwise - false;
     */
    public boolean addNewUser(String userName, String password) {
        if (password == null || userName == null ||
                password.isEmpty() || userName.isEmpty()) {
            System.out.println("password and user name are invalid");
            return false;
        }
        if (usersHashMap.containsKey(userName)) {
            System.out.println("This user name is already exist, please try new one");
            return false;
        }
        password = AES.encrypt(password, secretKey);
        usersHashMap.put(userName, password);
        return true;
    }


    /**
     * this function updates a new password for exiting user.
     *
     * @param userName
     * @param password
     * @return true if the password updated successfully, otherwise - false.
     */
    public boolean updatePassword(String userName, String password) {
        if (!usersHashMap.containsKey(userName)) {
            System.out.println("This user id doesnt exits in the system");
            return false;
        }
        if(userName == null || password == null || userName.isEmpty() || password.isEmpty()){
            System.out.println("password and user name are invalid");
            return false;
        }
        password = AES.encrypt(password, secretKey);
        usersHashMap.put(userName, password);
        System.out.println("password has been update to the user: " +userName);
        return true;
    }

    /**
     * this function checks if the password of a user is correct for login option.
     * the function is using AES decryption because the hash map holding encrypted passwords.
     *
     * @param userName
     * @param password
     * @return true if the password is correct, otherwise - false
     */
    public boolean checkPasswordForLogIn(String userName, String password) {
        if (!userName.contains(userName)) {
            System.out.println("the user name is NOT EXISTS in the system");
            return false;
        }
        if (AES.decrypt(usersHashMap.get(userName), secretKey).equals(password)) {
            // the user can log in, the password is correct
            return true;
        }
        //else..
        System.out.println("the password is wrong");
        return false;
    }

    /**
     * this function removes existing user from the hash map.
     *
     * @param userName
     */
    public void removeUser(String userName) {
        if (this.usersHashMap.containsKey(userName)) {
            this.usersHashMap.remove(userName);
        }
    }
}
