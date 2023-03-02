package socialmedia;
import java.util.ArrayList;
import java.util.HashMap;

public class Account {

    //Class attributes

    private static ArrayList<HashMap> accounts = new ArrayList<HashMap>(); //Create accounts list
    private static int accountIDCounter = 0;


    //Instance attributes

    private int id;
    private String handle;
    private String description;

    // Constructor to allow for testing - WILL BE DELETED
    public Account() {
        System.out.println("Constructor Initalised");
    }

    // Getter Method for Accounts data
    public static ArrayList<HashMap> getAccounts() {
        return accounts;
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
 

    public int createAccount(String handle) {

        //Check if handle is legal (not pre-existing)
        if (Platform.checkHandleLegal(handle) == false) {
            System.out.println("Throw illegalHandleException");
        }

        // Check if handle is valid (less than 30 characters, no whitespace and not empty)
        if (checkHandleValid(handle) == false) {
            System.out.println("Throw invalidHandleException");
        }

        //Adds user to Array
        HashMap<String, Object> accountHmap = new HashMap<String, Object>(); //Create account hashmap
        accountHmap.put("id", accountIDCounter); //Create ID 
        accountHmap.put("handle", handle); //Create Handle
        accountHmap.put("description", ""); //Create Description
        
        accountIDCounter++; //Increment Account ID Counter
        
        accounts.add(accountHmap); //Add accountHmap to accounts arrayList

        return (accountIDCounter - 1); //To fix!
    } 

    public int createAccount(String handle, String description) {
       
       //Check if handle is legal (not pre-existing)
        if (Platform.checkHandleLegal(handle) == false) {
            System.out.println("Throw illegalHandleException");
        }

        // Check if handle is valid (less than 30 characters, no whitespace and not empty)
        if (checkHandleValid(handle) == false) {
            System.out.println("Throw invalidHandleException");
        }

        //Adds user to Array
        HashMap<String, Object> accountHmap = new HashMap<String, Object>(); //Create account hashmap
        accountHmap.put("id", accountIDCounter); //Create ID 
        accountHmap.put("handle", handle); //Create Handle
        accountHmap.put("description", description); //Create Description
        
        accountIDCounter++; //Increment Account ID Counter
        
        accounts.add(accountHmap); //Add accountHmap to accounts arrayList

        //DELETEEEEEEEEEE
        System.out.println(accounts);

        return (accountIDCounter - 1); //To fix!
    }

    public void removeAccount(int handle) {
        // to do
    }

    public void removeAccount(String handle) {
        // to do
    }

    public void changeAccountHandle(String oldHandle, String newHandle) {

        if (Platform.checkHandleLegal(oldHandle) == true) {
            System.out.println("Handle not recognised exception");
        } 

        if (Platform.checkHandleLegal(newHandle) == false) {
            System.out.println("Illegal handle exception");
        }

        if (checkHandleValid(newHandle) == false) {
            System.out.println("Invalid handle exception");
        }

        for (int i=0; i < accounts.size(); i++) { //Loop through list of hashmap's 
            if(accounts.get(i).get("handle") == oldHandle) { //Get list item with matching old handle 
                accounts.get(i).put("handle", newHandle); //Update handle
                break;
            } 
        }
    }

    public void updateAccountDescription(String handle, String description) {
        if (Platform.checkHandleLegal(handle) == true) {
            System.out.println("Handle not recognised exception");
        } 

        for (int i=0; i < accounts.size(); i++) { //Loop through list of hashmap's 
            if(accounts.get(i).get("handle") == handle) { //Get list item with matching old handle 
                accounts.get(i).put("description", description); //Update handle
                break;
            } 
        }

    }

    public String showAccount(String handle) {
        // to do
        return "hello there";
    }

}
