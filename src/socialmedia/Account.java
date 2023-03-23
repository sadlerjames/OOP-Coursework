package socialmedia;
import java.io.Serializable;

public class Account implements Serializable {

    //Instance attributes

    private int id;
    private String handle;
    private String description;

    //Account getters

    public int getID() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public String getDescription() {
        return description;
    }

    //Account setters

    public void setHandle(String handle) {
        this.handle = handle;
    }

    //Constructor using Handle
    public Account(String handle, int id) {
        this.id = id; //Assign ID
        this.handle = handle; //Assign handle
    }

    //Constructor using Handle and Description
    public Account(String handle, int id, String description){
        this.id = id; //Assign ID
        this.handle = handle; //Assign handle    
        this.description = description; //Assign description
    }

    public void changeAccountHandle(String newHandle) throws IllegalHandleException, InvalidHandleException, HandleNotRecognisedException {        
        //update the object's handle attribute 
        this.handle = newHandle;
        
    }

    public void updateAccountDescription(String description) {
        //update the object's description attribute
        this.description = description;
    }
 
    public String showAccount(int noPosts, int noEndorsements) {

        String accountSummary = String.format("ID: %s \n" +
        "Handle: %s \n" +
        "Description: %s \n" +
        "Post count: %s \n" +
        "Endorse count: %s",
        id, handle, description, noPosts, noEndorsements);

        return accountSummary;
    }
}
