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


    public static  String showAccount(String handle) throws HandleNotRecognisedException {

        if (Platform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 
        
        String account_summary = "";

        for (int i=0; i < accounts.size(); i++) {    
            if (accounts.get(i).handle == handle) { 

                int accountID = accounts.get(i).id;
                String accountDesciption = accounts.get(i).description;
                
                int postCounter = 0;
                int endorsedCounter = 0;

                //Get no. of posts (authored by account)
                for (int j=0; j < BasePost.getPosts().size(); j++) {
                    if (BasePost.getPosts().get(j).getAuthor() == handle) {
                        postCounter ++;
                        endorsedCounter = endorsedCounter + BasePost.getPosts().get(j).getEndorsements().size(); //Count endorsement posts

                        account_summary = String.format("ID: %s \n" +
                        "Handle: %s \n" +
                        "Description: %s \n" +
                        "Post count: %s \n" +
                        "Endorse count: %s",
                        accountID, handle, accountDesciption, postCounter, endorsedCounter);
                    }
                }
                break;   
            }
        }
        return account_summary;
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
 
    public static void removeAccount(int id) throws AccountIDNotRecognisedException {

        ArrayList<Integer> delPostIDs = new ArrayList<Integer>(); //List of post (to be deleted) id's 

        //Throw exception if account does not exist
        if (checkIDLegal(id) == true) {
            throw new AccountIDNotRecognisedException("This id already exists in the system, please choose another.");
        }

        for (int i=0; i < accounts.size(); i++) {    
            if (accounts.get(i).id == id) { //Matching id 
                String accountHandle = accounts.get(i).handle; //Get acccount handle 

                for (int j=0; j < BasePost.getPosts().size(); j++) { //loop through posts

                    if (BasePost.getPosts().get(j).getAuthor() == accountHandle) { //Account to delete authors post
                        int postID = BasePost.getPosts().get(j).getID(); //Get ID of post to delete

                        delPostIDs.add(postID); //Add to list of ID's to delete

                        //Add endorsement's of all posts to list to also be deleted
                    }
                }

                for (int j=0; j < delPostIDs.size(); j++) {
                    
                    try {
                        BasePost.deletePost(delPostIDs.get(j));
                
                    } catch (Exception e) { //Post deleted as a child 
                    }
                }    
                accounts.remove(i); //Remove account object
                break; //Deleted account
            }
        }
    }

    public static void removeAccount(String handle) throws HandleNotRecognisedException {
        ArrayList<Integer> delPostIDs = new ArrayList<Integer>(); //List of post (to be deleted) id's 

        //Throw exception if handle does not exist
        if (Platform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 

        for (int i=0; i < accounts.size(); i++) {    
            if (accounts.get(i).handle == handle) { //Matching id 

                for (int j=0; j < BasePost.getPosts().size(); j++) { //loop through posts

                    if (BasePost.getPosts().get(j).getAuthor() == handle) { //Account to delete authors post
                        int postID = BasePost.getPosts().get(j).getID(); //Get ID of post to delete

                        delPostIDs.add(postID); //Add to list of ID's to delete
                        //Add endorsement's of all posts to list to also be deleted
                    }
                }

                for (int j=0; j < delPostIDs.size(); j++) {
                    
                    try {
                        BasePost.deletePost(delPostIDs.get(j));
                
                    } catch (Exception e) { //Post deleted as a child 
                    }
                }    
                accounts.remove(i); //Remove account object
                break; //Deleted account
            }
        }
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
}
