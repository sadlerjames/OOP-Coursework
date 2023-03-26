package socialmedia;

public class CommentPost extends BasePost {
    private Integer parentID; //ID of parent (for endorsements or comments). Integer type, enables 'null' value if post has no parent 

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
