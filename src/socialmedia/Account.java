package socialmedia;
import java.util.ArrayList;
import java.io.Serializable;


public class Account implements Serializable {

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


    public static boolean checkIDLegal(int id) {
        //Check if ID is an attribute in ArrayList of objects
        for (int i=0; i < accounts.size(); i++) {    
            if (accounts.get(i).id == id) { //Handle already exists
                return false; //as id exists
            } 
        }
        return true; //ID does not already exist
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

    //Setter method for accountIDCounter
    public static void setAccountIDCounter(int ID){
        accountIDCounter = ID;
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

    //Constructor to re-initilise (by handle)  
    public Account(String handle, Boolean reloadAccount) throws HandleNotRecognisedException {

        //Check if handle is legal (not pre-existing)
        if (Platform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system, so the account cannot be re-loaded");   
        }

        for (int i=0; i < accounts.size(); i++) {    
            if (accounts.get(i).handle == handle) { //Matching id 
                //Re-initialise object
                this.id = accounts.get(i).id;
                this.handle = handle;
                this.description = accounts.get(i).description;

                accounts.remove(i); //Remove existing account 
            }
        }
    } 

    //Constructor to re-initilise (by ID)  
    public Account(int id, Boolean reloadAccount) throws AccountIDNotRecognisedException {

        //Throw exception if account does not exist
        if (checkIDLegal(id) == true) {
            throw new AccountIDNotRecognisedException("An account with this ID does not exist in the system, so the account cannot be re-loaded.");
        }

        for (int i=0; i < accounts.size(); i++) {    
            if (accounts.get(i).id == id) { //Matching id 
                //Re-initialise object
                this.id = id;
                this.handle = accounts.get(i).handle;
                this.description = accounts.get(i).description;

                accounts.remove(i); //Remove existing account 
            }
        }
    } 

    // Check if string more than 30 characters, if whitespace, or if empty (invalid)
    public boolean checkHandleValid(String handle) {

        //Check if handle is greater than 30 characters
        if (handle.length() > 30) {
            return false;
        }

        //Check if handle contains whitespace
        else if (handle.contains(" ")) {
            return false;
        }

        //Check if handle is empty
        else if (handle.length() == 0) {
            return false;
        }

        //Handle is valid
        else {
            return true;
        }
    }
 
    public String showAccount(String handle) {

        //if (Platform.checkHandleLegal(handle) == true) {
        //    throw new HandleNotRecognisedException("This handle does not exist in the system!");
       // } 

        String accountSummary = "";

        int postCounter = 0;
        int endorsedCounter = 0;

        //Get no. of posts (authored by account)
        for (int j=0; j < BasePost.getPosts().size(); j++) {
            if (BasePost.getPosts().get(j).getAuthor() == handle) {
                postCounter ++;
                endorsedCounter = endorsedCounter + BasePost.getPosts().get(j).getEndorsements().size(); //Count endorsement posts

            }
        }

        accountSummary = String.format("ID: %s \n" +
        "Handle: %s \n" +
        "Description: %s \n" +
        "Post count: %s \n" +
        "Endorse count: %s",
        id, handle, description, postCounter, endorsedCounter);

        return accountSummary;
    }


    public void removeAccount() {

        ArrayList<Integer> delPostIDs = new ArrayList<Integer>(); //List of post (to be deleted) id's 

        for (int j=0; j < BasePost.getPosts().size(); j++) { //loop through posts

            if (BasePost.getPosts().get(j).getAuthor() == handle) { //Account to delete authors post
                int postID = BasePost.getPosts().get(j).getID(); //Get ID of post to delete

                delPostIDs.add(postID); //Add to list of ID's to delete
            }
        }

        for (int j=0; j < delPostIDs.size(); j++) {
            
            try {
                BasePost.deletePost(delPostIDs.get(j));
        
            } catch (Exception e) { //Post deleted as a child 
            }
        }    
        
    }

    public void changeAccountHandle(String newHandle) throws IllegalHandleException, InvalidHandleException, HandleNotRecognisedException {


        if (Platform.checkHandleLegal(newHandle) == false) {
            throw new IllegalHandleException("This handle already exists in the system, please choose another.");
        }

        if (checkHandleValid(newHandle) == false) {
            throw new InvalidHandleException("Please ensure your handle is valid (less than 30 characters, no whitespace and not empty).");
        } 
        
        //update the object's handle attribute 
        this.handle = newHandle;
        
    }

    public void updateAccountDescription(String description) {
        //if (Platform.checkHandleLegal(handle) == true) {
         //   throw new HandleNotRecognisedException("This handle does not exist in the system!");
       // } 

        this.description = description;
    }
}
