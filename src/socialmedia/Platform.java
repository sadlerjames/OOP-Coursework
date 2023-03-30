package socialmedia;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Platform stores all data relating to a specific social media platform instance.
 * This includes all accounts, posts, and counters. 
 * When the social media platform is saved, the platform class can be serialised 
 * to allow this data to persist. 
 * <p>
 * The class also provides methods to retrieve platform statistics, in addition to
 * validity checks.      
 * @author Students: 720014004, 720033851
 * @version 1.0 
 */

public class Platform implements Serializable {

    //Accounts
    private ArrayList<Account> accounts = new ArrayList<Account>(); //Store account objects
    private int accountIDCounter = 0; //Sequentially count accounts, provide unique ID 

    //Posts
    //Store BasePost objects (normal, comment, endorsement, posts)
    private ArrayList<BasePost> posts = new ArrayList<BasePost>();
    
    //Sequentially count accounts, provide unique ID.
    private int postIDCounter = 1; //Begin at 1, generic empty post assigned ID 0 

    //Platform private instance attribute 'getter' methods

    public ArrayList<Account> getAccounts() {
        return accounts; 
    }

    public int getAccountIDCounter() {
        return accountIDCounter;
    }

    public ArrayList<BasePost> getPosts() {
        return posts; 
    }

    public int getPostIDCounter() {
        return postIDCounter;
    }

    //Platform private instance attribute 'setter' methodss

    public void incrementPostIDCounter() {
        postIDCounter++;
    }

    public void incrementAccountIDCounter() {
        accountIDCounter++;
    }

    public void resetAccountIDCounter() {
        accountIDCounter = 0;
    }

    public void resetPostIDCounter(){
        postIDCounter = 1;
    }


    //Check methods 

    /**
    * Method to check whether a handle is legal to create (does not exist) 
    * <p>
    * @param handle - the handle to determine the legality of 
    * @return Returns <b>false</b> if a handle cannot be created (not unique),
    * and <b>true</b> if it is legal to create. 
    */ 

    public boolean checkHandleLegal(String handle) {
        //Check if handle is an attribute in ArrayList of objects
        for (int i=0; i < accounts.size(); i++) {    
            if (accounts.get(i).getHandle().equals(handle)) { //Handle already exists
                return false;
            } 
        }
        return true; //Handle does not exist
    }


    /**
    * Method to check whether a handle is valid.
    * A handle cannot be more than 30 characters, cannot 
    * contain whitespace, or be empty (invalid). 
    * <p>
    * @param handle - the handle to determine the validity of 
    * @return Returns <b>false</b> if the handle is invalid, 
    * or <b>true</b> if the handle is valid.
    */ 
 
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

    /**
    * Method to check whether a post message is valid.
    * A post message cannot be more than 100 characters, and 
    * cannot be empty (invalid). 
    * <p>
    * @param postMessage - the post message to determine the validity of 
    * @return Returns <b>false</b> if the post message is invalid, 
    * or <b>true</b> if the post message is valid.
    */ 

    public boolean checkPostValid(String postMessage) {

        //Check if post is less than 100 characters
        if(postMessage.length() > 100) {
            return false;
        }

        //Check if post is empty
        else if(postMessage.length() == 0) {
            return false;
        }

        //Post is valid
        else {
            return true;
        }
    }

    /**
    * Method to check whether a post is actionable.
    * An actionable post is either a normal post or a comment post 
    * (endorsement posts are non-actionable).
    * <p>
    * @param id - the ID of the post to check 
    * @return Returns <b>false</b> if the post is non-actionable, 
    * or <b>true</b> if the post is actionable.
    */ 

    public boolean checkPostActionable(int id) {

        for (int i=0; i < posts.size(); i++) {   
            if (posts.get(i).getID() == id) { //Located post in list with matching ID  
                //Normal/comment post
                if (posts.get(i).getPostType() == 0 || posts.get(i).getPostType() == 1) {
                    return true;
                }
            } 
        }
        return false;
    }

    //Platform getter methods 
    
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
    * Method to determine the number of original posts in the platform.
    * Iterates over all posts, including them in the count provided they
    * have type '0' (normal/original post). 
    * <p>
    * @return the number of original posts in the platform
    */ 

    public int getTotalOriginalPosts() {
        int numPosts = 0; 

        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).getPostType() == 0) { //Original posts have type '0'
                numPosts++;
            }
        }
        return numPosts;
    }

    /**
    * Method to determine the number of endorsement posts in the platform.
    * Iterates over all posts, including them in the count provided they
    * have type '2' (endorsement post). 
    * <p>
    * @return the number of endorsement posts in the platform
    */ 

    public int getTotalEndorsmentPost() {
        int numEndorsements = 0;

        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).getPostType() == 2) { //Endorsements posts have type '2'
            numEndorsements++;
            }
        }
        return numEndorsements;
    }

    /**
    * Method to determine the number of comment posts in the platform.
    * Iterates over all posts, including them in the count provided they
    * have type '1' (comment post). 
    * <p>
    * @return the number of comment posts in the platform
    */ 
    
    public int getTotalCommentPosts() {
        int numComments = 0; 

        for (int i=0; i < posts.size(); i++) {    
            if (posts.get(i).getPostType() == 1) { //Comment posts have type '1'
            numComments++;
            }
        }
        return numComments;
    }

    /**
    * Method to determine the most endorsed post in the platform.
    * <p>
    * @param socialPlatform - the Platform object storing the posts, 
    * enables retrieving a post's endorsements (getEndorsements)
    * @return the ID of the post in the platform with the most endorsements. 
    * In the case of a draw, the last post in the list is returned (highest ID).
    * In the case there are no posts in the system, -1 is returned.
    */ 

    public int getMostEndorsedPost(Platform socialPlatform) {
        int maxNumEndorsements = 0; //Counter for number of original posts
        int idMaxPost = 0; //ID. of the post with max no. of endorsements
        
        if (posts.size() == 1) { //No posts in the system (disregarding the generic empty post)
            return -1; //Denotes error 
        }

        for (int i=0; i < posts.size(); i++) {
            //For each post, get no. of endorsements
            int numEndorsements = posts.get(i).getEndorsements(socialPlatform).size();
            
            //Post 'i' has current highest no. of endorsements
            if (numEndorsements >= maxNumEndorsements) {   
            maxNumEndorsements = numEndorsements; //Update maximum recorded endorsements
            idMaxPost = posts.get(i).getID(); //Update ID 
            }
        }

        assert(idMaxPost != 0): "0 is never a valid return (ID of generic empty post)";

        return idMaxPost;
    }


    /**
    * Method to determine the most endorsed account in the platform.
    * <p>
    * @param socialPlatform - the Platform object storing the posts, 
    * enables retrieving a post's endorsements (getEndorsements)
    * @return the ID of the account in the platform with the most endorsements. 
    * In the case of a draw, the last account in the list is returned (highest ID).
    * In the case there are no accounts in the system, -1 is returned.
    */ 

    public int getMostEndorsedAccount(Platform socialPlatform) {
        int idMaxAccountEndorsements = 0; //ID of account with most endorsements
        int maxNumEndorsements = 0; //Counter 

        if (accounts.size() == 0) { //No accounts in the system
            return -1; //Denotes error 
        }

        for (int i=0; i < accounts.size(); i++) { //Iterate through accounts

            String accountHandle = accounts.get(i).getHandle(); //Get account's handle
            int accountID = accounts.get(i).getID(); //Get account ID 

            int accountEndorsements = 0; //Count an account's endorsements total 

            for (int j=1; j < posts.size(); j++) { //Iterate through posts 

                if (posts.get(j).getAuthor().equals(accountHandle)) { //Post authored by account  
                    //Add post's no. of endorsements to account's endorsements total
                    accountEndorsements = accountEndorsements + 
                        posts.get(j).getEndorsements(socialPlatform).size(); 
                }
            }
            
            //The current account's no. endorsements is greater than maxNumEndorsements 
            if (accountEndorsements > maxNumEndorsements) {
                maxNumEndorsements = accountEndorsements; //Update values
                idMaxAccountEndorsements = accountID; 
            }
        }
        return idMaxAccountEndorsements;
    }
}
