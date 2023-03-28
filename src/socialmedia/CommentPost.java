package socialmedia;

/**
 * CommentPost provides attributes and methods to permit the creation of comment posts on the platform.
 * 
 * @author Students: 720014004, 720033851
 * @version 1.0 
 */

public class CommentPost extends BasePost {
    private int parentID; //ID of parent

    public CommentPost(String handle, int ID, Integer parentID, String message) {
        setID(ID); //Assign ID
        setPostType(1); //Set post type to 'comment'
        setMessage(message); 
        setAuthor(handle);
        this.parentID = parentID; //Assign id of parent post/comment
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }
}
