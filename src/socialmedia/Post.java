package socialmedia;
import java.util.ArrayList;

public class Post extends BasePost{
    //Instance attributes

    private ArrayList<Integer> endorsements = new ArrayList<Integer>(); //Only 'normal' posts may have endorsements


    public Post(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {

        //Check author's handle exists
        if (Platform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 

        if (checkPostValid(message) == false) {
            throw new InvalidPostException("Please ensure your post is valid (less than 30 characters and not empty)");
        }

        int postIDCounter = getPostIDCounter();
        setID(postIDCounter); //Assign ID
        setPostIDCounter(postIDCounter++);

        setMessage(message); 
        setAuthor(handle);
    }


    public void deletePost(int id) {
        //to do
    }

    public String showIndividualPost(int id) {
        return "To-Do";
    }

    public String showPostChildrenDetails(int id) {
        return "To-Do";
    }


}
