package socialmedia;

public class CommentPost extends BasePost{
    public CommentPost(String handle, int id, String message) throws HandleNotRecognisedException, 
        PostIDNotRecognisedException, NotActionablePostException, InvalidPostException{
    
        //Check author's handle exists
        if (Platform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 

        


        
    }
}
