package socialmedia;
import java.util.ArrayList;
import java.util.HashMap;


public class Post {
    //Class attributes
    private static int postIDCounter = 0;
    private static ArrayList<HashMap> posts = new ArrayList<HashMap>(); //Create accounts list

    //Instance attributes 
    private int id;
    private String message; //100 character limit
    private Account author; //Object account? 

    private ArrayList<Integer> comments = new ArrayList<Integer>();
    private ArrayList<Integer> endorsements = new ArrayList<Integer>();


    // Check if string more than 100 characters, or if empty (invalid)
    public boolean checkPostValid(String handle) {

        //Check if handle is greater than 30 characters
        if(handle.length() > 100) {
            return false;
        }

        //Check if handle is empty
        else if(handle.length() == 0) {
            return false;
        }

        //Handle is valid
        else {
            return true;
        }
    }


    public int createPost(String handle, String message) {

        //check if the handle exists
        if (Platform.checkHandleLegal(handle) == true) {
            System.out.println("Handle not recognised exception");
        }

        //check if the message is valid
        if (checkPostValid(message) == false) {
            System.out.println("Invalid post exception");
        }

        //Adds user to Array
        HashMap<String, Object> postHmap = new HashMap<String, Object>(); //Create account postHmap
        postHmap.put("id", postIDCounter); //Create ID 
        postHmap.put("message", message); //Create Message
        postHmap.put("author", ""); //Create link to author obj
        postHmap.put("comments", comments); //Create list of comment's ID's
        postHmap.put("endorsments", endorsements); //Create list of endorsement's ID's
        
        postIDCounter++; //Increment Post ID Counter
        
        posts.add(postHmap); //Add postHmap to posts arrayList

        return (postIDCounter - 1); //To fix!

    }

    public int endorsePost(String handle, int id) {
        return 4;
    }

    public int commentPost(String handle, int id, String message) {
        return 4;
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
