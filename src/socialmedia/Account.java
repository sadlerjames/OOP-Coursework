package socialmedia;
import java.io.Serializable;

/**
 * Account provides attributes and methods to permit the creation of users on the platform.
 * 
 * @author Students: 720014004, 720033851
 * @version 1.0 
 */

public class Account implements Serializable {

    //Private instance attributes

    private int id; //Sequential unique ID
    private String handle; //Unique handle
    private String description;


    /**
    * Constructor for Account class, setting handle and ID instance attributes. 
    * <p>
    * @param handle the account's handle  
    * @param id the account's sequential ID 
    */ 
    
    public Account(String handle, int id) {
        this.id = id; //Assign ID
        this.handle = handle; //Assign handle
    }

    /**
    * Constructor for Account class, setting handle, ID, and description instance attributes. 
    * <p>
    * @param handle the account's handle
    * @param id the account's sequential ID
    * @param description the account's description text       
    */     
    
    public Account(String handle, int id, String description){
        this.id = id; //Assign ID
        this.handle = handle; //Assign handle    
        this.description = description; //Assign description
    }

    //Public instance attribute getters

    public int getID() {
        return id; 
    }

    public String getHandle() {
        return handle; 
    }

    public String getDescription() {
        return description; 
    }

    //Public instance attribute setters
    
    public void setHandle(String handle) { 
        this.handle = handle;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    /**
    * Helper method, generates a formated string displaying details
    * of an Account instance, provided the number of authored posts and recieved endorsements. 
    * <p>
    * @param noPosts the number of posts associated with the account instance 
    * @param noEndorsements the number of endorsements recieved by the account instance
    * @return a formatted string containing the account details.
    */ 

    public String generateAccountDetails(int noPosts, int noEndorsements) {

        String accountSummary = String.format("ID: %s \n" +
        "Handle: %s \n" +
        "Description: %s \n" +
        "Post count: %s \n" +
        "Endorse count: %s",
        id, handle, description, noPosts, noEndorsements);

        return accountSummary;
    }
}
