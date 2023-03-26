package socialmedia;

public class EndorsementPost extends BasePost {
    private Integer parentID; //ID of parent (for endorsements or comments). Integer type, enables 'null' value if post has no parent 

    public EndorsementPost(String handle, int ID, Integer parentID, String parentHandle, String parentMessage) {
        setID(ID); //Assign ID 
        setPostType(2); //Set post type to 'endorsement'
        setMessage(String.format("EP@%s: %s", parentHandle, parentMessage));
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