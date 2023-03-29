package socialmedia;

/**
 * EndorsementPost provides attributes and methods to permit the endorsement of posts
 * on the platform.
 * @author Students: 720014004, 720033851
 * @version 1.0 
 */

public class EndorsementPost extends BasePost {
    private int parentID; //ID of parent post (standard/normal or comment type)

    /**
    * Constructor for a new EndorsementPost in the platform, intialising handle, ID, parentID,
    * parentHandle, and parentMessage instance attributes. 
    * <p>
    * @param handle - the handle of the account authoring the endorsement  
    * @param ID - the endorsement's sequential ID
    * @param parentID - the ID of the parent post, which this post is endorsing (a child of)   
    * @param parentHandle - the handle of the parent post's author, which this post is endorsing   
    * @param parentMessage - the parent post's message content  
    */

    public EndorsementPost(String handle, int ID, 
        Integer parentID, String parentHandle, String parentMessage) {

        setID(ID); //Assign sequential ID 
        setPostType(2); //Set post type to 2 -'endorsement'

        //Generate endorsement message in required format
        setMessage(String.format("EP@%s: %s", parentHandle, parentMessage)); 
        setAuthor(handle); //Set the endorsement's author

        //Set the endorsement's parentID to the ID of the parent post/comment
        this.parentID = parentID;
    }

    //ParentID instance attribute getter    
    public int getParentID() {
        return parentID;
    }

    //ParentID instance attribute setter
    public void setParentID(int parentID) {
        this.parentID = parentID;
    }
}