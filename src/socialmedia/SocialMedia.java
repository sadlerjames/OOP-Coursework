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

	/**
    * Constructor for the initalisation of the whole platform,
    * where the Platform object and generic empty post are created.
    */ 

	public SocialMedia() {
		socialPlatform = new Platform(); //Create platform instance
		BasePost genericEmptyPost = new Post(); //Create generic empty post
		socialPlatform.getPosts().add(genericEmptyPost); //Add generic empty post to array 
	}

	@Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {

		//Check if handle is legal (not pre-existing)
        if (socialPlatform.checkHandleLegal(handle) == false) {
            throw new IllegalHandleException("This handle already exists in the system");
        }

        // Check if handle is valid (less than 30 characters, no whitespace and not empty)
        if (socialPlatform.checkHandleValid(handle) == false) {
            throw new InvalidHandleException
				("Your handle must be valid (less than 30 characters, 0 whitespace, not empty).");
        }

		int accountIDCounter = socialPlatform.getAccountIDCounter(); //Get account ID counter

		Account platformUser = new Account(handle, accountIDCounter); //Create Account, set ID 
		socialPlatform.getAccounts().add(platformUser); //Store Account object
		socialPlatform.incrementAccountIDCounter(); //Increment counter

		return platformUser.getID(); //Return ID of generated account
	}

	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {

        //Check if handle is legal (not pre-existing)
        if (socialPlatform.checkHandleLegal(handle) == false) {
            throw new IllegalHandleException
			("This handle already exists in the system, please choose another.");
        }

        // Check if handle is valid (less than 30 characters, no whitespace and not empty)
        if (socialPlatform.checkHandleValid(handle) == false) {
            throw new InvalidHandleException("Please ensure your handle is valid (less than 30 characters, no whitespace and not empty).");
        }

		int accountIDCounter = socialPlatform.getAccountIDCounter(); //Get account ID counter
		//Create account (with decription), set ID 
		Account platformUser = new Account(handle, accountIDCounter, description); 
		socialPlatform.getAccounts().add(platformUser); //Store account object
		socialPlatform.incrementAccountIDCounter(); //Increment counter

		return platformUser.getID(); //Return 'id' of generated account
	}

	/**
    * Method for the reloading of a specified account (via its handle), 
	* returning the Account object.
	* <p>
	* @param handle - the handle of the Account to reload
	* @return the Account object with the specified handle
	* @throws HandleNotRecognisedException thrown when attempting to reload an account where the 
	* provided handle does not exist
    */ 

	public Account reloadAccount(String handle) throws HandleNotRecognisedException {

		for (int i=0; i < socialPlatform.getAccounts().size(); i++) {    
            if (socialPlatform.getAccounts().get(i).getHandle().equals(handle)) { //Matching ID
				Account reloadedAccount = socialPlatform.getAccounts().get(i);
				return reloadedAccount;
            } 
        }

		throw new HandleNotRecognisedException
		("An account with this handle does not exist in the system."); //Not found, cannot exist  
	}

	/**
    * Method for the reloading of a specified account (via its ID), 
	* returning the Account object.
	* <p>
	* @param id - the ID of the Account to reload 
	* @return the Account object with the specified ID
	* @throws AccountIDNotRecognisedException thrown when attempting to reload an account where the 
	* provided ID does not exist
    */ 

	public Account reloadAccount(int id) throws AccountIDNotRecognisedException {

		for (int i=0; i < socialPlatform.getAccounts().size(); i++) {    
            if (socialPlatform.getAccounts().get(i).getID() == id) { //Matching ID
				Account reloadedAccount = socialPlatform.getAccounts().get(i);
				return reloadedAccount;
            }
        }

		throw new AccountIDNotRecognisedException
		("An account with this ID does not exist in the system.");   
	}

	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {

		Account reloadedAccount = reloadAccount(id); //Reload account (to be deleted), by ID 

        ArrayList<Integer> delPostIDs = new ArrayList<Integer>(); //Store ID's of posts to delete

        for (int j=1; j < socialPlatform.getPosts().size(); j++) { 

			//Check if post authored by account being deleted
            if (socialPlatform.getPosts().get(j).getAuthor().equals(reloadedAccount.getHandle())) { 
                int postID = socialPlatform.getPosts().get(j).getID(); //Get ID of post (to delete)
                delPostIDs.add(postID);
            }
        }

        for (int j=0; j < delPostIDs.size(); j++) { 
            try {
                deletePost(delPostIDs.get(j)); //Attempt to delete post 
        
            } catch (PostIDNotRecognisedException e) { //Post deleted (as a child of a parent)
            }
        }   

		socialPlatform.getAccounts().remove(reloadedAccount); //Remove account

	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		Account reloadedAccount = reloadAccount(handle); //Reload account (to be deleted)

        ArrayList<Integer> delPostIDs = new ArrayList<Integer>(); //Store ID's of posts (to delete)

        for (int j=1; j < socialPlatform.getPosts().size(); j++) {

			//Check if post authored by account being deleted
            if (socialPlatform.getPosts().get(j).getAuthor().equals(handle)) { 
                int postID = socialPlatform.getPosts().get(j).getID(); //Get ID of post (to delete)
                delPostIDs.add(postID); //Add to list of ID's to delete

            }
        }

        for (int j=0; j < delPostIDs.size(); j++) {
            try {
                deletePost(delPostIDs.get(j)); //Attempt to delete post 
        
            } catch (PostIDNotRecognisedException e) { //Post already deleted (as a child)
            }
        }   

		socialPlatform.getAccounts().remove(reloadedAccount); //Remove account	
	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		
		//Check author's handle exists
		if (socialPlatform.checkHandleLegal(newHandle) == false) {
            throw new IllegalHandleException
			("This handle already exists in the system, please choose another.");
        }

		// Check if handle is valid (less than 30 characters, no whitespace and not empty)
        if (socialPlatform.checkHandleValid(newHandle) == false) {
            throw new InvalidHandleException
			("Ensure your handle is valid (less than 30 characters, 0 whitespace, not empty).");
        } 

		
		Account platformUserReload = reloadAccount(oldHandle); //Reload account (to be deleted)
		platformUserReload.setHandle(newHandle); //Change the handle to the new specified one
	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		
		Account platformUserReload = reloadAccount(handle); //Reload account (to be deleted)
		platformUserReload.setDescription(description); //Update description attribute
	} 

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
	
		Account reloadedAccount = reloadAccount(handle); //Reload account (to be deleted)

		//Counters for posts and endorsements
        int postCounter = 0;
        int endorsedCounter = 0;

		//Get no. of posts (authored by account)
        for (int j=1; j < socialPlatform.getPosts().size(); j++) {
            if (socialPlatform.getPosts().get(j).getAuthor().equals(handle)) {
                postCounter++; //Increment the post counter
                endorsedCounter = endorsedCounter + socialPlatform.getPosts().get(j)
					.getEndorsements(socialPlatform).size(); //Count endorsement posts
            }
        }
		
		//Call the helper function to generate account details and return
		return reloadedAccount.generateAccountDetails(postCounter, endorsedCounter); 
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {

        //Check author's handle exists
        if (socialPlatform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        }

		//Check post is valid (less than 100 characters and not empty)
        if (socialPlatform.checkPostValid(message) == false) {
            throw new 
				InvalidPostException("Your post must be valid (less than 30 chars, not empty)");
        }

		int postIDCounter = socialPlatform.getPostIDCounter(); //Get the Post ID Counter

        BasePost platformPost = new Post(handle, postIDCounter, message); //Create post, initialise 
        socialPlatform.getPosts().add(platformPost); //Save post

		socialPlatform.incrementPostIDCounter(); //Increment counter
		 
		return platformPost.getID(); //Return ID of post
	}

	/**
    * Method for the reloading of a specified post (via its ID), 
	* returning the BasePost object.
	* <p>
	* @param id - the ID of the Post to reload
	* @return the Post object with matching ID
	* @throws PostIDNotRecognisedException thrown when attempting to reload an post where the 
	* provided ID does not exist
    */ 

	public BasePost reloadPost(int id) throws PostIDNotRecognisedException {

		for (int i=1; i < socialPlatform.getPosts().size(); i++) {    
            if (socialPlatform.getPosts().get(i).getID() == id) { //Post has matching ID
				
				BasePost reloadedPost = socialPlatform.getPosts().get(i); 
				return reloadedPost; //Return BasePost object
				
            }
        }

		throw new PostIDNotRecognisedException("A post with this ID does not exist in the system");   
	}

	@Override
	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {

		BasePost reloadedParentPost = reloadPost(id); //Reload parent
		
		//Check endorsement author's handle exists
        if (socialPlatform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system.");
        } 

        //Check parent actionable (not endorsement/empty)
        if (socialPlatform.checkPostActionable(id) == false) {
            throw new 
				NotActionablePostException("The parent is not actionable, so cannot be endorsed");
        }

		int postIDCounter = socialPlatform.getPostIDCounter(); //Get the Post ID Counter

		BasePost platformEndorsement = new EndorsementPost(handle, postIDCounter, id, 
			reloadedParentPost.getAuthor(), reloadedParentPost.getMessage()); 
				//Construct an endorsement post object
        socialPlatform.getPosts().add(platformEndorsement); //Save post
		
		socialPlatform.incrementPostIDCounter(); //Increment counter

		return platformEndorsement.getID(); //Return the ID of the created endorsement post
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
            throw new NotActionablePostException
				("The parent post is not actionable, and cannot be commented");
        }

        //Check post is valid (less than 100 characters and not empty)
        if (socialPlatform.checkPostValid(message) == false) {
            throw new 
				InvalidPostException("Ensure our post is valid (less than 30 chars, not empty)");
        }

		int postIDCounter = socialPlatform.getPostIDCounter(); //Get the Post ID Counter

		//Construct a comment post object
		BasePost platformComment = new CommentPost(handle, postIDCounter, id, message); 
        socialPlatform.getPosts().add(platformComment); //Save post

		socialPlatform.incrementPostIDCounter(); //Increment counter

		return platformComment.getID(); //Return the ID of the created comment post
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {

		BasePost reloadedPost = reloadPost(id); //Load post (to be deleted)

		//Delete endorsements
		if (reloadedPost.getEndorsements(socialPlatform).size() != 0) { //Post has endorsements

			//Iterate through endorsements
			for (int i=0; i < reloadedPost.getEndorsements(socialPlatform).size(); i++) { 
				//Get orphan endorsement
				BasePost reloadOrpEndorsement = reloadedPost.getEndorsements(socialPlatform).get(i); 
				socialPlatform.getPosts().remove(reloadOrpEndorsement); //Remove endorsement
			}
		}
		
		//Update commments
		if (reloadedPost.getComments(socialPlatform).size() != 0) { //Post has comments
			for (int i=0; i < reloadedPost.getComments(socialPlatform).size(); i++) { //Iterate through comments
				BasePost reloadOrpComment = reloadedPost.getComments(socialPlatform).get(i); //Get orphan comment

				if (reloadOrpComment instanceof CommentPost) {
					CommentPost relComment = (CommentPost)reloadOrpComment;
					
					//Update comment parentID to generic empty post's ID (0)
					relComment.setParentID(0);
					
					assert(relComment.getParentID() == 0): "The parent ID was not set to 0";
				}
			}
		}

		socialPlatform.getPosts().remove(reloadedPost); //Delete post
	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {

		BasePost reloadedPost = reloadPost(id); //Load post (to be deleted)

		//Call the helper function to generate post details and return
        return reloadedPost.genPostDetails(socialPlatform); 
	}

	@Override
	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {

		BasePost reloadedParentPost = reloadPost(id); //Reload the parent post 

        //Check if parent is actionable
        if (socialPlatform.checkPostActionable(id) == false) {
            throw new NotActionablePostException("The parent post is not actionable");
        }
		
		//Execute helper function, pass in parent post 
		StringBuilder returnedPostDetails = helpShowPostChildrenDetails(reloadedParentPost); 
        
        return returnedPostDetails; //Return StringBuilder
    }

	/**
    * Private helper method for showPostChildrenDetails.
	* Removes the requirement to repeat reduntant checks (comments always actionable).
	* <p>
	* @param postObj - the BasePost object (for which the childen details are displayed)
	* @return the stringBuilder containing the post details in required 'tree' format
	* @throws PostIDNotRecognisedException thrown when attempting to reload an post that 
	* does not exist
    */ 
	
	private StringBuilder helpShowPostChildrenDetails(BasePost postObj) throws PostIDNotRecognisedException {

		StringBuilder postDetails = new StringBuilder(); //Create stringBuilder

		if (postObj.getComments(socialPlatform).size() != 0) { //Check if post has comments

			//Add post details (with required formatting) to stringBuilder
			postDetails.append(showIndividualPost(postObj.getID()) + System.lineSeparator() + "|"); 

			//Iterate through comments, recursively calling the helper on each 
			for (int j=0; j < postObj.getComments(socialPlatform).size(); j++) { 
				
				//Recursive call, convert returned stringbuilder to string 
				String childString = helpShowPostChildrenDetails
					(postObj.getComments(socialPlatform).get(j)).toString(); 

				//Split the recursive return into lines (at newline character)
				String linesSplit[] = childString.split(System.lineSeparator()); 

				boolean firstLine = true;
				for (int k=0; k < linesSplit.length; k++) {
					if (firstLine == true) { 
						//Add correct formatting to first line
						postDetails.append(System.lineSeparator() + "| > " 
							+ linesSplit[k] + System.lineSeparator()); 
						firstLine = false; //First line formatting completed, do not re-run
					} else {
						//Pad all other lines with 4 chars of whitespace 
						postDetails.append("    " + linesSplit[k] + System.lineSeparator());
					}
				}
			}
			
		} else {
			//Add details of parent post to stringBulder
			postDetails.append(showIndividualPost(postObj.getID())); 
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
        socialPlatform.getAccounts().clear(); //Clear the accounts array
        socialPlatform.resetAccountIDCounter(); //Reset Account's ID counter

        //Posts arrayList/counters
        socialPlatform.getPosts().clear(); //Clear the posts array
        socialPlatform.resetPostIDCounter(); //Reset Post's ID counter

		BasePost genericEmptyPost = new Post(); //Recreate generic empty post
		socialPlatform.getPosts().add(genericEmptyPost); //Add generic empty post to array 
	}

	@Override
	public void savePlatform(String filename) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
		oos.writeObject(socialPlatform); //Write object to file
		oos.close();
	}

	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
		Object obj = ois.readObject(); //Write platform object
		ois.close();

		//Safely downcast (if deserialised obj is a 'Platform' instance)
		if (obj instanceof Platform) { 
			socialPlatform = (Platform) obj; //Overwrite socialPlatform with deserialised object 
		}
	}
}
