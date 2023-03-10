package socialmedia;

public class CommentPost extends BasePost{

    public CommentPost(String handle, int parentID, String message) throws HandleNotRecognisedException, 
        PostIDNotRecognisedException, NotActionablePostException, InvalidPostException{
    
        //Check author's handle exists
        if (Platform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 

        //Check parent exists 
        if (BasePost.checkPostIDLegal(parentID) == true) {
            throw new PostIDNotRecognisedException("The parent post does not exist in the system");
        }

        //Check parent actionable (not endorsement, can comment)
        if (BasePost.checkPostActionable(parentID) == false) {
            throw new NotActionablePostException("The parent post is an endorsement, and cannot be commented");
        }

        //Check post is valid (less than 30 characters and not empty)
        if (checkPostValid(message) == false) {
            throw new InvalidPostException("Please ensure your post is valid (less than 30 characters and not empty)");
        }
    
        int postIDCounter = getPostIDCounter();
        setID(postIDCounter); //Assign ID 
        incrementPostIDCounter(); //Increment the Post ID Counter
        setPostType(1); //Set post type to 'comment'
        setMessage(message); 
        setAuthor(handle);
        setParentID(parentID); //Assign id of parent post/comment
        
        //Add comment's 'id' to parent's 'comments' arrayList
        Post.addChild(postIDCounter, parentID, 1);  
    }
}
