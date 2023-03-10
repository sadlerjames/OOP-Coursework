package socialmedia;

public class EndorsementPost extends BasePost {

    public EndorsementPost(String handle, int parentID) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException{
        
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

        int postIDCounter = getPostIDCounter();
        setID(postIDCounter); //Assign ID 
        incrementPostIDCounter(); //Increment the Post ID Counter
        setPostType(2); //Set post type to 'comment'
        setAuthor(handle);
        setParentID(parentID); //Assign id of parent post/comment
        
        //Add comment's 'id' to parent's 'comments' arrayList
        Post.addChild(postIDCounter, parentID, 2);  


    }

}