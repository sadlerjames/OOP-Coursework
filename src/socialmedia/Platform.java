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
 
 
    public int getTotalOriginalPosts() {
        return 4;
    }

    public int getTotalEndorsmentPost() {
        return 4;
    }
    
    public int getTotalCommentPosts() {
        return 4;
    }

    public int getMostEndorsedPost() {
        return 4;
    }

    public int getMostEndorsedAccount() {
        return 4;
    }

    public void erasePlatform() {
        // to do
    }

    public void savePlatform(String filename) {
        // to do
    }

    public void loadPlatform(String filename) {
        // to do
    }

    public int getNumberOfAccounts() {
        return 4;
    }


}
