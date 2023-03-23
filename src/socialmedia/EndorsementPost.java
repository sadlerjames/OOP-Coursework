package socialmedia;

public class EndorsementPost extends BasePost {

    public EndorsementPost(String handle, int ID, Integer parentID, String parentHandle, String parentMessage) {
        setID(ID); //Assign ID 
        setPostType(2); //Set post type to 'endorsement'
        setMessage(String.format("EP@%s: %s", parentHandle, parentMessage));
        setAuthor(handle);
        setParentID(parentID); //Assign id of parent post/comment
        
    }

}