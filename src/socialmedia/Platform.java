package socialmedia;

public class Platform {

    public static boolean checkHandleLegal(String handle) {
        //Check if handle is an attribute in ArrayList of objects
        for (int i=0; i < Account.getAccounts().size(); i++) {    
            if (Account.getAccounts().get(i).getHandle() == handle) { //Handle already exists
                return false; //as handle exists
            } 
        }
        return true; //Handle does not already exist
    }
 
    public int getNumberOfAccounts() {
        int numAccounts = Account.getAccounts().size();

        return numAccounts;
    }

    public int getTotalOriginalPosts() {
        int numPosts = 0; //Counter for number of original posts

        for (int i=0; i < BasePost.getPosts().size(); i++) {    
            if (BasePost.getPosts().get(i).getPostType() == 0) { //Oringinal posts have type '1'
                numPosts++;
            }
        }
        return numPosts;
    }

    public int getTotalEndorsmentPost() {
        int numEndorsements = 0; //Counter for number of original posts

        for (int i=0; i < BasePost.getPosts().size(); i++) {    
            if (BasePost.getPosts().get(i).getPostType() == 2) { //Endorsements posts have type '2'
            numEndorsements++;
            }
        }
        return numEndorsements;
    }
    
    public int getTotalCommentPosts() {
        int numComments = 0; //Counter for number of original posts

        for (int i=0; i < BasePost.getPosts().size(); i++) {    
            if (BasePost.getPosts().get(i).getPostType() == 1) { //Endorsements posts have type '2'
            numComments++;
            }
        }
        return numComments;
    }

    public int getMostEndorsedPost() {
        int maxNumEndorsements = 0; //Counter for number of original posts
        int idMaxPost = 0; //ID. of the post with max no. of endorsements 

        for (int i=0; i < BasePost.getPosts().size(); i++) {   
            int numEndorsements = BasePost.getPosts().get(i).getEndorsements().size();
            
            if (numEndorsements >= maxNumEndorsements) { //Endorsements posts have type '2'
            maxNumEndorsements = numEndorsements;
            idMaxPost = BasePost.getPosts().get(i).getID();
            }
        }
        return idMaxPost;
    }

    public int getMostEndorsedAccount() {
        int idMaxAccountEndorsements = 0; //ID of account with most endorsements
        int maxNumEndorsements = 0; //Counter 

        for (int i=0; i < Account.getAccounts().size(); i++) { //Iterate through authors (accounts)

            String authorHandle = Account.getAccounts().get(i).getHandle(); //Get handle of current obj
            int authorId = Account.getAccounts().get(i).getID(); //Get id of current obj

            int accountEndorsements = 0; //Counter - counts account's number of endorsements 
            for (int j=0; j < BasePost.getPosts().size(); j++) { //Iterate through posts (with matching author/handle)

                if (BasePost.getPosts().get(j).getAuthor() == authorHandle && BasePost.getPosts().get(j).getPostType() == 2) { //Only count endorsements 
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

    public void erasePlatform() {
 
        //Accounts array/counter
        
        Account.getAccounts().clear(); 
        Account.setAccountIDCounter(0); //Reset Account's ID counter

        //Posts array/counter

        Post.getPosts().clear();
        BasePost.setPostIDCounter(1);
    }

    public void savePlatform(String filename) {
        //TODO
        


    }

    public void loadPlatform(String filename) {
        //TODO
    }



}
