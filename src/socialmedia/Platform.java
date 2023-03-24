package socialmedia;
import java.io.Serializable;
import java.util.ArrayList;

public class Platform implements Serializable {

    //Accounts
    private ArrayList<Account> accounts = new ArrayList<Account>(); //Create accounts list
    private int accountIDCounter = 0;

    //Posts
    private ArrayList<BasePost> posts = new ArrayList<BasePost>(); //Create list of posts 
    private int postIDCounter = 1; //Generic empty post has ID 0 


    //Getter methods for Platform 

    public ArrayList<Account> getAccounts() {
        return accounts; //Implement safety checks?
    }

    public int getAccountIDCounter() {
        return accountIDCounter;
    }


    // Getter Method for Posts data 
    public ArrayList<BasePost> getPosts() {
        return posts; //Implement safety checks?
    }

    public int getPostIDCounter() {
        return postIDCounter;
    }

    //Platform setter methods

    public void incrementPostIDCounter() {
        postIDCounter++;
    }

    //Increment method for accountIDCounter
    public void incrementAccountIDCounter() {
        accountIDCounter++;
    }

    //Reset accounts counter to 0
    public void resetAccountIDCounter() {
        accountIDCounter = 0;
    }

    //Reset posts counter to 0
    public void resetPostIDCounter(){
        postIDCounter = 0;
    }


    //Platform check methods 

    public boolean checkHandleLegal(String handle) {
        //Check if handle is an attribute in ArrayList of objects
        for (int i=0; i < accounts.size(); i++) {    
            if (accounts.get(i).getHandle().equals(handle)) { //Handle already exists
                return false; //as handle exists
            } 
        }
        return true; //Handle does not already exist
    }
 
    // Check if string more than 30 characters, if whitespace, or if empty (invalid)
    public boolean checkHandleValid(String handle) {

        //Check if handle is greater than 30 characters
        if (handle.length() > 30) {
            return false;
        }

        //Check if handle contains whitespace
        else if (handle.contains(" ")) {
            return false;
        }

        //Check if handle is empty
        else if (handle.length() == 0) {
            return false;
        }

        //Handle is valid
        else {
            return true;
        }
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

    //Likely can be deleted when showPostChildrenDetails is updated
    // Check if postID exists (return true if post if post ID exists)
    //Returns true if post id doesn't exist (legal to create)
    public boolean checkPostIDLegal(int id) {

        //Check if post exists with matching 'id'attribute in ArrayList of objects
        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).getID() == id) { //ID already exists
                return false; //as ID exists
            } 
        }
        //Post ID not in system
        return true;
    }

    //Check if parent is actionable (normal or comment)
    //Returns true if normal or comment (0 or 1 type)
    public boolean checkPostActionable(int id) {
        //Check if post exists with matching 'id'attribute in ArrayList of objects
        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).getID() == id) { //Post is desired post
                if (posts.get(i).getPostType() == 0 || posts.get(i).getPostType() == 1) {
                    return true;
                }
            } 
        }
        return false;
    }

    //Platform getter methods 
    
    public int getNumberOfAccounts() {
        int numAccounts = accounts.size();

        return numAccounts;
    }

    public int getTotalOriginalPosts() {
        int numPosts = 0; //Counter for number of original posts

        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).getPostType() == 0) { //Oringinal posts have type '1'
                numPosts++;
            }
        }
        return numPosts;
    }

    public int getTotalEndorsmentPost() {
        int numEndorsements = 0; //Counter for number of original posts

        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).getPostType() == 2) { //Endorsements posts have type '2'
            numEndorsements++;
            }
        }
        return numEndorsements;
    }
    
    public int getTotalCommentPosts() {
        int numComments = 0; //Counter for number of original posts

        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).getPostType() == 1) { //Endorsements posts have type '2'
            numComments++;
            }
        }
        return numComments;
    }

    public int getMostEndorsedPost() {
        int maxNumEndorsements = 0; //Counter for number of original posts
        int idMaxPost = 0; //ID. of the post with max no. of endorsements 

        for (int i=0; i < posts.size(); i++) {   
            int numEndorsements = posts.get(i).getEndorsements().size();
            
            if (numEndorsements >= maxNumEndorsements) { //Endorsements posts have type '2'
            maxNumEndorsements = numEndorsements;
            idMaxPost = posts.get(i).getID();
            }
        }
        return idMaxPost;
    }

    public int getMostEndorsedAccount() {
        int idMaxAccountEndorsements = 0; //ID of account with most endorsements
        int maxNumEndorsements = 0; //Counter 

        for (int i=0; i < accounts.size(); i++) { //Iterate through authors (accounts)

            String authorHandle = accounts.get(i).getHandle(); //Get handle of current obj
            int authorId = accounts.get(i).getID(); //Get id of current obj

            int accountEndorsements = 0; //Counter - counts account's number of endorsements 
            for (int j=0; j < posts.size(); j++) { //Iterate through posts (with matching author/handle)

                if (posts.get(j).getAuthor().equals(authorHandle) && posts.get(j).getPostType() == 2) { //Only count endorsements 
                    accountEndorsements++; //Increment account's endorsements total
                }
            }
            
            //If the number of endorsements for the given account is greater that the current max number of endorsements then update values
            if (accountEndorsements > maxNumEndorsements) {
                maxNumEndorsements = accountEndorsements;
                idMaxAccountEndorsements = authorId;
            }
        }
        
        return idMaxAccountEndorsements;
    }
}
