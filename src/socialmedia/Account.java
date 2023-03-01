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
        System.out.println("Below");
    }

    public int createAccount(String handle) {

        //Check if handle is a key in list of hashmaps
        for (int i=0; i < accounts.size(); i++) {

            if(accounts.get(i).get("handle") == handle) {
                //TODO: Throw exception 
            }
        }

        //Check if handle is greater than 30 characters
        if(handle.length() > 30) {
            //TODO: Throw InvalidHandleException
            System.out.println("Greater than 30");
        }

        //Check if handle contains whitespace
        if(handle.contains(" ")) {
            //TODO: Throw InvalidHandleException
            System.out.println("Contains whitespace");
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
        //Check if handle is a key in list of hashmaps
        for (int i=0; i < accounts.size(); i++) {

            if(accounts.get(i).get("handle") == handle) {
                //TODO: Throw exception 
            }
        }

        //Check if handle is greater than 30 characters
        if(handle.length() > 30) {
            //TODO: Throw InvalidHandleException
            System.out.println("Greater than 30");
        }

        //Check if handle contains whitespace
        if(handle.contains(" ")) {
            //TODO: Throw InvalidHandleException
            System.out.println("Contains whitespace");
        }

        //Adds user to Array
        HashMap<String, Object> accountHmap = new HashMap<String, Object>(); //Create account hashmap
        accountHmap.put("id", accountIDCounter); //Create ID 
        accountHmap.put("handle", handle); //Create Handle
        accountHmap.put("description", description); //Create Description
        
        accountIDCounter++; //Increment Account ID Counter
        
        accounts.add(accountHmap); //Add accountHmap to accounts arrayList

        return (accountIDCounter - 1); //To fix!
    }

    public void removeAccount(int handle) {
        //todo
    }

    public void removeAccount(String handle) {
        // to do
    }

    public void changeAccountHandle(String oldHandle, String newHandle) {
        //to do
    }

    public void updateAccountDescription(String handle, String description) {
        //to do
    }

    public String showAccount(String handle) {
        // to do
        return "hello there";
    }

}
