package socialmedia;

public class EndorsementPost extends BasePost {

    private String parentHandle;
    private String parentMessage;

    public EndorsementPost(String handle, Integer parentID) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException{
        
        //Check author's handle exists
        if (Platform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 

        //Check parent exists 
        if (BasePost.checkPostIDLegal(parentID) == true) {
            throw new PostIDNotRecognisedException("The parent post does not exist in the system");
        }

        //Check parent actionable (not endorsement)
        if (BasePost.checkPostActionable(parentID) == false) {
            throw new NotActionablePostException("The parent post is an endorsement, and cannot be endorsed");
        }

        int postIDCounter = getPostIDCounter();
        setID(postIDCounter); //Assign ID 
        incrementPostIDCounter(); //Increment the Post ID Counter
        setPostType(2); //Set post type to 'comment'

        //Get parent handle and parent message
        for (int i=0; i < getPosts().size(); i++) {    
            if (getPosts().get(i).getID() == parentID) { 
                this.parentHandle = getPosts().get(i).getAuthor();
                this.parentMessage = getPosts().get(i).getMessage();
            } 
        }

        setMessage(String.format("EP@%s: %s", parentHandle, parentMessage));
        setAuthor(handle);
        setParentID(parentID); //Assign id of parent post/comment
        
        //Add comment's 'id' to parent's 'comments' arrayList
        Post.addChild(postIDCounter, parentID, 2);  
    }

}