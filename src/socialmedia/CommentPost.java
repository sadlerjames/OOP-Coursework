package socialmedia;

public class CommentPost extends BasePost {

    public CommentPost(String handle, int ID, Integer parentID, String message) {
        setID(ID); //Assign ID
        setPostType(1); //Set post type to 'comment'
        setMessage(message); 
        setAuthor(handle);
        setParentID(parentID); //Assign id of parent post/comment
    }
}
