package socialmedia;

public class EndorsementPost extends BasePost {
    private int parentID; //ID of parent post

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