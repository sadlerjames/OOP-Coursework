package socialmedia;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * SocialMedia is an implementor of the SocialMediaPlatform interface.
 * 
 * @author Students: 720014004, 720033851
 * @version 1.0 
 */

public class SocialMedia implements SocialMediaPlatform {
	private Platform socialPlatform; 

	public SocialMedia() {
		socialPlatform = new Platform(); //Create platform instance
		BasePost genericEmptyPost = new Post(); //Create generic empty post
		socialPlatform.getPosts().add(genericEmptyPost); //Add generic empty post to array 
	}

	@Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {//CHANGE THROWS

		//Check if handle is legal (not pre-existing)
        if (socialPlatform.checkHandleLegal(handle) == false) {
            throw new IllegalHandleException("This handle already exists in the system, please choose another.");
        }

        // Check if handle is valid (less than 30 characters, no whitespace and not empty)
        if (socialPlatform.checkHandleValid(handle) == false) {
            throw new InvalidHandleException("Please ensure your handle is valid (less than 30 characters, no whitespace and not empty).");
        }

		int accountIDCounter = socialPlatform.getAccountIDCounter();

		Account platformUser = new Account(handle, accountIDCounter); //Create account object, set ID 
		socialPlatform.getAccounts().add(platformUser); //Store account object
		socialPlatform.incrementAccountIDCounter(); //Increment counter

		return platformUser.getID(); //Return 'id' of generated account
	}

	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {

        //Check if handle is legal (not pre-existing)
        if (socialPlatform.checkHandleLegal(handle) == false) {
            throw new IllegalHandleException("This handle already exists in the system, please choose another.");
        }

        // Check if handle is valid (less than 30 characters, no whitespace and not empty)
        if (socialPlatform.checkHandleValid(handle) == false) {
            throw new InvalidHandleException("Please ensure your handle is valid (less than 30 characters, no whitespace and not empty).");
        }

		int accountIDCounter = socialPlatform.getAccountIDCounter();
		Account platformUser = new Account(handle, accountIDCounter, description); //Create account object (with decription), set ID 
		socialPlatform.getAccounts().add(platformUser); //Store account object
		socialPlatform.incrementAccountIDCounter(); //Increment counter

		return platformUser.getID(); //Return 'id' of generated account
	}


	public Account reloadAccount(String handle) throws HandleNotRecognisedException {

		for (int i=0; i < socialPlatform.getAccounts().size(); i++) {    
            if (socialPlatform.getAccounts().get(i).getHandle().equals(handle)) { //Matching id (located account)
				Account reloadedAccount = socialPlatform.getAccounts().get(i);
				return reloadedAccount;
            } 
        }

		throw new HandleNotRecognisedException("An account with this handle does not exist in the system."); //Not found, cannot exist  
	}


	public Account reloadAccount(int id) throws AccountIDNotRecognisedException {

		for (int i=0; i < socialPlatform.getAccounts().size(); i++) {    
            if (socialPlatform.getAccounts().get(i).getID() == id) { //Matching id (located account)
				Account reloadedAccount = socialPlatform.getAccounts().get(i);
				return reloadedAccount;
            }
        }

		throw new AccountIDNotRecognisedException("An account with this ID does not exist in the system.");   
	}

	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {

		Account reloadedAccount = reloadAccount(id); //Load account to delete

        ArrayList<Integer> delPostIDs = new ArrayList<Integer>(); //List of post (to be deleted) id's 

        for (int j=1; j < socialPlatform.getPosts().size(); j++) { //loop through posts

            if (socialPlatform.getPosts().get(j).getAuthor().equals(reloadedAccount.getHandle())) { //Account to delete authors post
                int postID = socialPlatform.getPosts().get(j).getID(); //Get ID of post to delete
                delPostIDs.add(postID); //Add to list of ID's to delete
            }
        }

        for (int j=0; j < delPostIDs.size(); j++) {
            try {
                deletePost(delPostIDs.get(j)); //Attempt to delete post 
        
            } catch (PostIDNotRecognisedException e) { //Post has already been deleted as a child
            }
        }   

		socialPlatform.getAccounts().remove(reloadedAccount); //Remove account

	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		Account reloadedAccount = reloadAccount(handle); //Load account to delete

        ArrayList<Integer> delPostIDs = new ArrayList<Integer>(); //List of post (to be deleted) id's 

        for (int j=1; j < socialPlatform.getPosts().size(); j++) { //loop through posts

            if (socialPlatform.getPosts().get(j).getAuthor().equals(handle)) { //Account to delete authors post
                int postID = socialPlatform.getPosts().get(j).getID(); //Get ID of post to delete
                delPostIDs.add(postID); //Add to list of ID's to delete

            }
        }

        for (int j=0; j < delPostIDs.size(); j++) {
            try {
                deletePost(delPostIDs.get(j)); //Attempt to delete post 
        
            } catch (PostIDNotRecognisedException e) { //Post has already been deleted as a child 
            }
        }   

		socialPlatform.getAccounts().remove(reloadedAccount); //Remove account	
	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		
		if (socialPlatform.checkHandleLegal(newHandle) == false) {
            throw new IllegalHandleException("This handle already exists in the system, please choose another.");
        }

        if (socialPlatform.checkHandleValid(newHandle) == false) {
            throw new InvalidHandleException("Please ensure your handle is valid (less than 30 characters, no whitespace and not empty).");
        } 

		
		Account platformUserReload = reloadAccount(oldHandle); //Reload account
		platformUserReload.setHandle(newHandle); //Change handle
	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		
		Account platformUserReload = reloadAccount(handle); //Reload account
		platformUserReload.setDescription(description); //Change description
	} 

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
	
		Account reloadedAccount = reloadAccount(handle);

		//Counters for posts and endorsements
        int postCounter = 0;
        int endorsedCounter = 0;

		//Get no. of posts (authored by account)
        for (int j=1; j < socialPlatform.getPosts().size(); j++) {
            if (socialPlatform.getPosts().get(j).getAuthor().equals(handle)) {
                postCounter++;
                endorsedCounter = endorsedCounter + socialPlatform.getPosts().get(j).getEndorsements(socialPlatform).size(); //Count endorsement posts
            }
        }

		return reloadedAccount.generateAccountDetails(postCounter, endorsedCounter);
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {

        //Check author's handle exists
        if (socialPlatform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 

        if (socialPlatform.checkPostValid(message) == false) {
            throw new InvalidPostException("Please ensure your post is valid (less than 30 characters and not empty)");
        }

		int postIDCounter = socialPlatform.getPostIDCounter(); //Get the Post ID Counter

        BasePost platformPost = new Post(handle, postIDCounter, message); //Create post, initialise 
        socialPlatform.getPosts().add(platformPost); //Save post

		socialPlatform.incrementPostIDCounter(); //Increment counter
		 
		return platformPost.getID(); //Return ID of post
	}

	public BasePost reloadPost(int id) throws PostIDNotRecognisedException {

		for (int i=1; i < socialPlatform.getPosts().size(); i++) {    
            if (socialPlatform.getPosts().get(i).getID() == id) { //Matching id (located post)
				
				BasePost reloadedPost = socialPlatform.getPosts().get(i);
				return reloadedPost;
				
            }
        }

		throw new PostIDNotRecognisedException("A post with this ID does not exist in the system");   
	}

	@Override
	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {

		//Check endorsement author's handle exists
        if (socialPlatform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system.");
        } 

        //Check parent actionable (not endorsement/empty)
        if (socialPlatform.checkPostActionable(id) == false) {
            throw new NotActionablePostException("The parent post not actionable (it may be an endorsement), and so cannot be endorsed");
        }

		BasePost reloadedParentPost = reloadPost(id); //Reload parent 

		int postIDCounter = socialPlatform.getPostIDCounter(); //Get the Post ID Counter

		BasePost platformEndorsement = new EndorsementPost(handle, postIDCounter, id, reloadedParentPost.getAuthor(), reloadedParentPost.getMessage());
        socialPlatform.getPosts().add(platformEndorsement); //Save post
		
		socialPlatform.incrementPostIDCounter(); //Increment counter

		return platformEndorsement.getID();
	}
 
	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		
		//Check comment author's handle exists
        if (socialPlatform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 

        //Check parent actionable (not endorsement/empty, can comment)
        if (socialPlatform.checkPostActionable(id) == false) {
            throw new NotActionablePostException("The parent post not actionable (it may be an endorsement), and cannot be commented");
        }

        //Check post is valid (less than 30 characters and not empty)
        if (socialPlatform.checkPostValid(message) == false) {
            throw new InvalidPostException("Please ensure your post is valid (less than 30 characters and not empty)");
        }

		int postIDCounter = socialPlatform.getPostIDCounter(); //Get the Post ID Counter

		BasePost platformComment = new CommentPost(handle, postIDCounter, id, message);
        socialPlatform.getPosts().add(platformComment); //Save post

		socialPlatform.incrementPostIDCounter(); //Increment counter

		return platformComment.getID();
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {

		BasePost reloadedPost = reloadPost(id); //Load post to be deleted

		//Delete/update dependences (endorsements and comments)
		
		if (reloadedPost.getEndorsements(socialPlatform).size() != 0) { //Post has endorsements
			for (int i=0; i < reloadedPost.getEndorsements(socialPlatform).size(); i++) { //Iterate through endorsements
				BasePost reloadedOrphanEndorsement = reloadedPost.getEndorsements(socialPlatform).get(i); //Get orphan endorsement obj
				socialPlatform.getPosts().remove(reloadedOrphanEndorsement); //Remove endorsement
			}
		}

		if (reloadedPost.getComments(socialPlatform).size() != 0) { //Post has comments
			for (int i=0; i < reloadedPost.getComments(socialPlatform).size(); i++) { //Iterate through comments
				BasePost reloadedOrphanComment = reloadedPost.getComments(socialPlatform).get(i); //Get orphan comment obj

				if (reloadedOrphanComment instanceof CommentPost) {
					CommentPost relComment = (CommentPost)reloadedOrphanComment;
					relComment.setParentID(0); //Update comment parentID to generic empty post's ID (0)
				}
			}
		}

		socialPlatform.getPosts().remove(reloadedPost); //Delete post
	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {

		BasePost reloadedPost = reloadPost(id); //Load post
        
        return reloadedPost.genPostDetails(socialPlatform);
	}

	@Override
	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
    
        //Check parent exists 
        if (socialPlatform.checkPostIDLegal(id) == true) {
            throw new PostIDNotRecognisedException("The post does not exist in the system");
        }

        //Check parent actionable (not endorsement)
        if (socialPlatform.checkPostActionable(id) == false) {
            throw new NotActionablePostException("The parent post is not actionable (likely an endorsement, so does not have children)");
        }

		StringBuilder returnedPostDetails = helpShowPostChildrenDetails(id);
        
        return returnedPostDetails; //Return StringBuilder
    }

	private StringBuilder helpShowPostChildrenDetails(int id) throws PostIDNotRecognisedException {

		StringBuilder postDetails = new StringBuilder(); //Create stringbuilder

        for (int i=1; i < socialPlatform.getPosts().size(); i++) {    
            if (socialPlatform.getPosts().get(i).getID() == id) { //Locate post with matching ID 
                
                if (socialPlatform.getPosts().get(i).getComments(socialPlatform).size() != 0) { //Check if post has comments
                    postDetails.append(showIndividualPost(id) + System.lineSeparator() + "|"); //Add post details to string output
                    
                    for (int j=0; j < socialPlatform.getPosts().get(i).getComments(socialPlatform).size(); j++) { //Loop through comments, calling on each  
                        
                        String childString = helpShowPostChildrenDetails(socialPlatform.getPosts().get(i).getComments(socialPlatform).get(j).getID()).toString(); //Recursive call, convert returned stringbuilder to string 

						String linesSplit[] = childString.split(System.lineSeparator()); //Split return into lines

						boolean firstLine = true;
						for (int k=0; k < linesSplit.length; k++) { //Iterate through 
							if (firstLine == true) {
								postDetails.append(System.lineSeparator() + "| > " + linesSplit[k] + System.lineSeparator()); //Add correct formatting to first lines
								firstLine = false; //First line formatting completed, do not re-run
							} else {
								postDetails.append("    " + linesSplit[k] + System.lineSeparator()); //Pad all other lines with whitespace 
							}
						}
                    }
                    
                } else {
                    postDetails.append(showIndividualPost(id)); //Add details of original post to string output
                }
            }
        }
		return postDetails;
	}  

	@Override
	public int getNumberOfAccounts() {
		return socialPlatform.getNumberOfAccounts();
	}

	@Override
	public int getTotalOriginalPosts() {
		return socialPlatform.getTotalOriginalPosts();
	}

	@Override
	public int getTotalEndorsmentPosts() {
		return socialPlatform.getTotalEndorsmentPost();
	}

	@Override
	public int getTotalCommentPosts() {
		return socialPlatform.getTotalCommentPosts();
	}

	@Override
	public int getMostEndorsedPost() {
		return socialPlatform.getMostEndorsedPost(socialPlatform);
	}

	@Override
	public int getMostEndorsedAccount() {
		return socialPlatform.getMostEndorsedAccount(socialPlatform);
	}

	@Override
	public void erasePlatform() {
        //Accounts arrayList/counters
        socialPlatform.getAccounts().clear(); 
        socialPlatform.resetAccountIDCounter(); //Reset Account's ID counter

        //Posts arrayList/counters
        socialPlatform.getPosts().clear();
        socialPlatform.resetPostIDCounter();
	}

	@Override
	public void savePlatform(String filename) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
		oos.writeObject(socialPlatform);
		oos.close();
	}

	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
		Object obj = ois.readObject(); //Write platform object
		ois.close();

		if (obj instanceof Platform) { //Safely downcast (if deserialised obj is a 'Platform' instance)
			socialPlatform = (Platform) obj; //Overwrite socialPlatform with deserialised object 
		}
	}
}
