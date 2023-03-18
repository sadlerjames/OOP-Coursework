package socialmedia;
import java.util.ArrayList;
import java.io.Serializable;


public class BasePost {
    //Class attributes
    private static int postIDCounter = 1; //Generic empty post has id 0 
    private static ArrayList<BasePost> posts = new ArrayList<BasePost>(); //Create list of posts 

    //Instance attributes 
    private int id;
    private int postType; //Track if post is 'normal' (0), 'comment' (1), 'endorsement' (2), 'generic empty post' (3)
    private Integer parentID;
    private String message; //100 character limit
    private String author; //Object account? 

    private ArrayList<Integer> endorsements = new ArrayList<Integer>();
    private ArrayList<Integer> comments = new ArrayList<Integer>();


    //Class methods


    public static void deletePost(int id) throws PostIDNotRecognisedException {

        //Check post exists
        if (checkPostIDLegal(id) == true) {
            throw new PostIDNotRecognisedException("The post to delete does not exist in the system");
        }

        //Locate Endorsement post to delete and comments to update parent id attribute
        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).id == id) {

                //delete the endorsement post of the post to be deleted
                if (posts.get(i).endorsements.size() != 0) { //Endorsements exist
                    for (int j=0; j < posts.get(i).endorsements.size(); j++) { //Interate through endorsement id's 
                        int orphanEndorsementID = posts.get(i).endorsements.get(j); //Get id of orphan endorsement post
                            
                        for (int k=0; k < posts.size(); k++) {    
                            if (posts.get(k).id == orphanEndorsementID) { //Locate orphan
                                posts.remove(k); //Remove orphan (endorsement) post object 
                            }
                        }
                        
                    }
                }
                

                //update the parent ID attribute of the comments of the post to be deleted
                if (posts.get(i).comments.size() != 0) {
                    for (int l=0; l < posts.get(i).comments.size(); l++) {
                        int orphanCommentID = posts.get(i).comments.get(l);
                        
                        for (int m=0; m < posts.size(); m++) {    
                            if (posts.get(m).id == orphanCommentID) { //Locate orphan comment in post array
                                posts.get(m).parentID = 0; //Update parentID to empty post's ID (0)
                                break;
                            }
                        }
                    }
                }

                posts.remove(i); //removes the requested post
            }
        }  
    }

    public static String showIndividualPost(int id) throws PostIDNotRecognisedException {

        //Check parent exists 
        if (checkPostIDLegal(id) == true) {
            throw new PostIDNotRecognisedException("The post does not exist in the system");
        }

        String message = "";

        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).id == id) { 
                String parentHandle = posts.get(i).author;
                int numComments = posts.get(i).comments.size();
                int numEndorsements = posts.get(i).endorsements.size();
                String parentMessage = posts.get(i).message;

                message = String.format("ID: %s \n" +
                "Account: %s \n" +
                "No. endorsements: %s | No. comments: %s \n" +
                "%s",
                id, parentHandle, numEndorsements, numComments, parentMessage);
                break;
            }
        }
        
        return message;
    }
        
    // Getter Method for Posts data 
    public static ArrayList<BasePost> getPosts() {
        return posts; //Implement safety checks?
    }
    
    //SORT STATIC!
    public static void incrementPostIDCounter() {
        postIDCounter++;
    }

    // Check if string more than 100 characters, or if empty (invalid)
    public static boolean checkPostValid(String handle) {

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
    //Returns true if post id doesn't exist (legal to create)
    public static boolean checkPostIDLegal(int id) {

        //Check if post exists with matching 'id'attribute in ArrayList of objects
        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).id == id) { //ID already exists
                return false; //as ID exists
            } 
        }
        //Post ID not in system
        return true;
    }

    //Check if parent is actionable (normal or comment)
    //Returns true if normal or comment (0 or 1 type)
    public static boolean checkPostActionable(int id) {
        //Check if post exists with matching 'id'attribute in ArrayList of objects
        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).id == id) { //Post is desired post
                if (posts.get(i).postType == 0 || posts.get(i).postType == 1) {
                    return true;
                }
            } 
        }
        return false;
    }


    //Instance methods
        
    //Getter methods

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

    public int getPostIDCounter() {
        return postIDCounter;
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


    //Setter methods
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

    public static void setPostIDCounter(int ID) {
        postIDCounter = ID;
    }

    //Static? - shouldn't be!
    public static String showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {
        
        //Check parent exists 
        if (checkPostIDLegal(id) == true) {
            throw new PostIDNotRecognisedException("The post does not exist in the system");
        }

        //Check parent actionable (not endorsement)
        if (BasePost.checkPostActionable(id) == false) {
            throw new NotActionablePostException("The parent post is an endorsement, and does not have children");
        }

        StringBuilder postDetails = new StringBuilder(); //Create stringbuilder

        //Need to get - handle, num endorsements, num comments, post message 

        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).id == id) { //Locate original post ID 
                
                if (posts.get(i).getComments().size() != 0) { //Check if original post has comments
                    postDetails.append(showIndividualPost(id) + System.lineSeparator() + "|"); //Add details of original post to string output
                    
                    for (int j=0; j < posts.get(i).getComments().size(); j++) { //Loop through comments, calling on each  
                        
                        String childString = showPostChildrenDetails(posts.get(i).getComments().get(j)).indent(1); //Indent() indents recursive return (each below parent)

                        String preIDChildString = childString.substring(0, childString.indexOf(System.getProperty("line.separator"))); //Get string before first 'line.separator' which will be the ID
                        String afterIDChildString = childString.substring(childString.indexOf(System.getProperty("line.separator"))+1); //Get string after first 'line.separator' 

            
                        postDetails.append(System.lineSeparator() + "| >" + preIDChildString + System.lineSeparator()); //Adds the | > ID to the StringBuilder postDetails
                        postDetails.append(afterIDChildString.indent(3)); //Indent() indents recursive return 

                    }
                    
                } else {
                    postDetails.append(showIndividualPost(id)); //Add details of original post to string output
                }
            }
        }
        return postDetails.toString(); //Return string
    }

}
