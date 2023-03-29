package socialmedia;

/**
 * CommentPost provides attributes and methods to permit the creation of comments on the platform.
 * 
 * @author Students: 720014004, 720033851
 * @version 1.0 
 */

public class CommentPost extends BasePost {
    private int parentID; //ID of parent post (standard/normal or comment type)

    /**
    * Constructor for a new CommentPost in the platform, setting handle, ID, parentID,
    * and message instance attributes. 
    * <p>
    * @param handle - the handle of the account authoring the comment 
    * @param ID - the comment's sequential ID
    * @param parentID - the ID of the parent post, which this post is commenting on (a child of)   
    * @param message - the comment's message 
    */

    public CommentPost(String handle, int ID, Integer parentID, String message) {
        setID(ID); //Assign ID
        setPostType(1); //Set post type to 'comment'
        setMessage(message); //Set message to provided string 
        setAuthor(handle); //Set author to provided handle 
        this.parentID = parentID; //Set parentID attribute to ID of parent post
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
