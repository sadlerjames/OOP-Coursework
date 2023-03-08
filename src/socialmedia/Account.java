package socialmedia;
import java.util.ArrayList;
import java.util.HashMap;


public class Account {

    //Class attributes

    private static ArrayList<Account> accounts = new ArrayList<Account>(); //Create accounts list
    private static int accountIDCounter = 0;


    //Instance attributes

    private int id;
    private String handle;
    private String description;


    //Class methods
    
    // Getter Method for Accounts data 
    public static ArrayList<Account> getAccounts() {
        return accounts; //Implement safety checks?
    }


    //Instance methods

   //Getter method for id

    public int getID() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    //REMOVE - TEMP
    public String getDescription() {
        return description;
    }


    //Constructor using Handle
    public Account(String handle) throws IllegalHandleException, InvalidHandleException{

        //Check if handle is legal (not pre-existing)
        if (Platform.checkHandleLegal(handle) == false) {
            throw new IllegalHandleException("This handle already exists in the system, please choose another.");
        }

        // Check if handle is valid (less than 30 characters, no whitespace and not empty)
        if (checkHandleValid(handle) == false) {
            throw new InvalidHandleException("Please ensure your handle is valid (less than 30 characters, no whitespace and not empty).");
        }

        this.id = accountIDCounter; //Assign ID
        accountIDCounter++; //Increment counter

        this.handle = handle; //Assign handle
    }

    //Constructor using Handle and Description
    public Account(String handle, String description) throws IllegalHandleException, InvalidHandleException{

        //Check if handle is legal (not pre-existing)
        if (Platform.checkHandleLegal(handle) == false) {
            throw new IllegalHandleException("This handle already exists in the system, please choose another.");
        }

        // Check if handle is valid (less than 30 characters, no whitespace and not empty)
        if (checkHandleValid(handle) == false) {
            throw new InvalidHandleException("Please ensure your handle is valid (less than 30 characters, no whitespace and not empty).");
        }

        this.id = accountIDCounter; //Assign ID
        accountIDCounter++; //Increment counter

        this.handle = handle; //Assign handle    
        this.description = description; //Assign description
    }


    // Check if string more than 30 characters, if whitespace, or if empty (invalid)
    public boolean checkHandleValid(String handle) {

        //Check if handle is greater than 30 characters
        if(handle.length() > 30) {
            return false;
        }

        //Check if handle contains whitespace
        else if(handle.contains(" ")) {
            return false;
        }

        //Check if handle is empty
        else if(handle.length() == 0) {
            return false;
        }

        //Handle is valid
        else {
            return true;
        }
    }
 
    public void removeAccount(int handle) {
        // to do
    }

    public void removeAccount(String handle) {
        // to do
    }

    public void changeAccountHandle(String oldHandle, String newHandle) throws IllegalHandleException, InvalidHandleException, HandleNotRecognisedException {

        if (Platform.checkHandleLegal(oldHandle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 

        if (Platform.checkHandleLegal(newHandle) == false) {
            throw new IllegalHandleException("This handle already exists in the system, please choose another.");
        }

        if (checkHandleValid(newHandle) == false) {
            throw new InvalidHandleException("Please ensure your handle is valid (less than 30 characters, no whitespace and not empty).");
        } 
        
        //update the object's handle attribute 
        this.handle = newHandle;
        
    }

    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        if (Platform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 

        this.description = description;
    }

    public String showAccount(String handle) {
        // to do
        return "hello there";
    }
}
