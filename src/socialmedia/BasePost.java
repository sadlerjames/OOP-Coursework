package socialmedia;
import java.io.Serializable;
import java.util.ArrayList;

public class BasePost implements Serializable {

    private int id;
    private int postType; //Track if post is 'normal' (0), 'comment' (1), 'endorsement' (2), 'generic empty post' (3)
    private Integer parentID; //Integer as will be 'null' if post has no parent 
    private String message; //100 character limit
    private String author; //Object account? 

    private ArrayList<Integer> endorsements = new ArrayList<Integer>();
    private ArrayList<Integer> comments = new ArrayList<Integer>();

        
    //BasePost Getter methods

    public ArrayList<Integer> getComments() {
        return comments; //Implement safety checks?
    }

    public ArrayList<Integer> getEndorsements() {
        return endorsements; //Implement safety checks?
    }

    public int getID() {
        return id;
    }

    public int getParentID() {
        return parentID;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public int getPostType() {
        return postType;
    }

    //BasePost Setter methods

    public void setID(int id) {
        this.id = id;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPostType(int postType){
        this.postType = postType;
    } 

    @Override
    public String toString() {

        String message = String.format("ID: %s \n" +
		"Account: %s \n" +
		"No. endorsements: %s | No. comments: %s \n" +
		"%s",
		id, author, endorsements.size(), 
		comments.size(), this.message);
        
        return message;
    }

}