package socialmedia;
import java.util.ArrayList;


public class Post {
    private int id;
    private String message; //100 character limit
    private Account author; //Object account? 
    private ArrayList<Integer> comments = new ArrayList<Integer>();


    public int createPost(String handle, String message) {
        return 4;
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
