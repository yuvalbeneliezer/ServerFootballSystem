package System;
import Security.SecuritySystem;
import Users.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * this class is Singleton for the Football System.
 */
public class FootballSystem {

    SecuritySystem securitySystem = new SecuritySystem();
    List<Guest> guestList = new LinkedList<>();
    Map<String,AUser> usersHashMap = new HashMap<>();
    /** Create an instance of the class at the time of class loading */
    private static final FootballSystem instance = new FootballSystem();

    /** private constructor to prevent others from instantiating this class */
    private FootballSystem(){

    }

    /** Provide a global point of access to the instance */
    public static FootballSystem getInstance(){
        return instance;
    }


    public AUser signIn(String userName, String password,String firstName,
                        String lastName, EUserType userType){
        // check if this user name already exits
        if(usersHashMap.containsKey(userName)){
            System.out.println("This user name is already exist.");
            return null;
        }
        // input check
        else if(userName == null || userName.isEmpty() ||
                password == null || password.isEmpty() ||
                firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty()
                || userType == null){
            System.out.println("please insert valid inputs");
            return null;
        }
        securitySystem.addNewUser(userName,password);
        return creatingNewUserByUserType(userName,firstName,lastName,userType);
    } // useCase 2.2
    /**
     * this function creating new user by his instance.
     * @param userName
     * @param firstName
     * @param lastName
     * @param userType
     * @return AUser by his instance.
     */
    private AUser creatingNewUserByUserType(String userName, String firstName, String lastName,EUserType userType) {
        AUser user = null;
        if(userType.equals(EUserType.representativeFootballAssociation)){
            user = new representativeFootballAssociation(userName,firstName,lastName);
            usersHashMap.put(userName,user);}
        else if(userType.equals(EUserType.Player)){
            // TODO: 4/11/2020 check with yuval 
            user = new Player(userName,firstName,lastName,null,null);
            usersHashMap.put(userName,user);
        }
        // TODO: 4/9/2020 check with yuval
        //else if(userType.equals(EUserType.Coach)){
        //    usersHashMap.put(userName,new Coach(id,firstName,lastName,));
        //}
        else if(userType.equals(EUserType.Fan)){
            user = new Fan(userName,firstName,lastName);
            usersHashMap.put(userName,user);
        }
        else if(userType.equals(EUserType.TeamManager)){
            user = new TeamManager(userName,firstName,lastName);
            usersHashMap.put(userName,user);
        }
        else if(userType.equals(EUserType.TeamOwner)){
            user = new TeamOwner(userName,firstName,lastName);
            usersHashMap.put(userName,user);
        }
        else if(userType.equals(EUserType.Referee)){
            // TODO: 4/11/2020 check with yuval 
            user = new Referee(userName,firstName,lastName,null);
            usersHashMap.put(userName,user);
        }
        return user;
    }
    /**
     * this function login a user to the system.
     * the function checks if the password and the user name are correct using the security system.
     * @param userName
     * @param password
     * @return AUser with the user name entered, or null if the user doesnt exist.
     */
    public AUser login(String userName, String password){
        if(securitySystem.checkPasswordForLogIn(userName,password)){
            if(usersHashMap.containsKey(userName)){
                return usersHashMap.get(userName);
            }
        }
        System.out.println("user name or password incorrect");
        return null;
    }  // useCase 2.3

    public void removeUser(String userName){
        this.usersHashMap.remove(userName);
    }

    public void removeUserFromSecuritySystem(String userName){
        this.securitySystem.removeUser(userName);
    }

    public AUser getUserByUserName(String userName){
        if(this.usersHashMap.containsKey(userName)){
            return this.usersHashMap.get(userName);
        }
        return null;
    }
}
