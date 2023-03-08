package socialmedia;
import java.util.ArrayList;


public class BasePost {
    //Class attributes
    private static int postIDCounter = 0;
    private static ArrayList<BasePost> posts = new ArrayList<BasePost>(); //Create list of posts 

    //Instance attributes 
    private int id;
    private String message; //100 character limit
    private String author; //Object account? 

    private ArrayList<Integer> comments = new ArrayList<Integer>();

    //Class methods
        
    // Getter Method for Accounts data 
    public static ArrayList<BasePost> getPosts() {
        return posts; //Implement safety checks?
    }

    public static void setPostIDCounter(int newPostIDCounter) {
        postIDCounter = newPostIDCounter;
    }

    //Instance methods

    //Getter methods
    public int getID() {
        return id;
    }

    public int getPostIDCounter() {
        return postIDCounter;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }


    //Setter methods
    public void setID(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    // Check if string more than 100 characters, or if empty (invalid)
    public boolean checkPostValid(String handle) {

        //Check if post is greater than 30 characters
        if(handle.length() > 100) {
            return false;
        }

        //Check if post is empty
        else if(handle.length() == 0) {
            return false;
        }

        //Post is valid
        else {
            return true;
        }
    }

    // Check if postID exists (return true if post if post ID exists)
    public boolean checkPostIDValid(String handle) {

        //Check if handle is an attribute in ArrayList of objects
        for (int i=0; i < posts.size(); i++) { //For each post object
            for (int j=0; j < posts.get(i).comments.size(); j++) {

            }
            
        }

        //Check if post ID exists 
        if(handle.length() > 100) {
            return false;
        }

  
        //Post ID is valid
        else {
            return true;
        }
    }


    

}
