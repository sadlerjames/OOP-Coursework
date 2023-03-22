package socialmedia;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable; //Needed? 

/**
 * SocialMedia is an implementor of the SocialMediaPlatform interface.
 * 
 * @author James Sadler, Joel Sawyer
 * @version 1.0 
 */

public class SocialMedia implements SocialMediaPlatform {

	Platform socialPlatform = new Platform(); //Create platform instance 


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
		socialPlatform.setAccountIDCounter(accountIDCounter++); //Increment counter

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
		socialPlatform.setAccountIDCounter(accountIDCounter++); //Increment counter

		return platformUser.getID(); //Return 'id' of generated account
	}


	public Account reloadAccount(String handle) throws HandleNotRecognisedException {

		//Check if handle is legal (not pre-existing)
		if (socialPlatform.checkHandleLegal(handle) == true) {
			throw new HandleNotRecognisedException("This handle does not exist in the system, so the account cannot be re-loaded");   
		}

		for (int i=0; i < socialPlatform.getAccounts().size(); i++) {    
            if (socialPlatform.getAccounts().get(i).getHandle() == handle) { //Matching id (located account)
				
				Account reloadedAccount = socialPlatform.getAccounts().get(i);
				return reloadedAccount;
				
            }
        }

		return null;
	}


	public Account reloadAccount(int id) throws AccountIDNotRecognisedException {

		//Check if handle is legal (not pre-existing)
		if (socialPlatform.checkIDLegal(id) == true) {
			throw new AccountIDNotRecognisedException("An account with this ID does not exist in the system, so it cannot be re-loaded");   
		}

		for (int i=0; i < socialPlatform.getAccounts().size(); i++) {    
            if (socialPlatform.getAccounts().get(i).getID() == id) { //Matching id (located account)
				
				Account reloadedAccount = socialPlatform.getAccounts().get(i);
				return reloadedAccount;
				
            }
        }

		return null;
	}


	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {

		Account reloadedAccount = reloadAccount(id); //Load account to delete

        ArrayList<Integer> delPostIDs = new ArrayList<Integer>(); //List of post (to be deleted) id's 

        for (int j=0; j < socialPlatform.getPosts().size(); j++) { //loop through posts

            if (socialPlatform.getPosts().get(j).getAuthor() == reloadedAccount.getHandle()) { //Account to delete authors post
                int postID = socialPlatform.getPosts().get(j).getID(); //Get ID of post to delete
                delPostIDs.add(postID); //Add to list of ID's to delete

            }
        }

        for (int j=0; j < delPostIDs.size(); j++) {
            try {
                deletePost(delPostIDs.get(j)); //Attempt to delete post 
        
            } catch (Exception e) { //Post has already been deleted as a child 
            }
        }   

		socialPlatform.getAccounts().remove(reloadedAccount); //Remove account

	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		Account reloadedAccount = reloadAccount(handle); //Load account to delete

        ArrayList<Integer> delPostIDs = new ArrayList<Integer>(); //List of post (to be deleted) id's 

        for (int j=0; j < socialPlatform.getPosts().size(); j++) { //loop through posts

            if (socialPlatform.getPosts().get(j).getAuthor() == handle) { //Account to delete authors post
                int postID = socialPlatform.getPosts().get(j).getID(); //Get ID of post to delete
                delPostIDs.add(postID); //Add to list of ID's to delete

            }
        }

        for (int j=0; j < delPostIDs.size(); j++) {
            try {
                deletePost(delPostIDs.get(j)); //Attempt to delete post 
        
            } catch (Exception e) { //Post has already been deleted as a child 
            }
        }   

		socialPlatform.getAccounts().remove(reloadedAccount); //Remove account	
	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {

		//Check if handle is legal (not pre-existing)
		if (socialPlatform.checkHandleLegal(oldHandle) == true) {
			throw new HandleNotRecognisedException("This handle does not exist in the system, so the account cannot be re-loaded");   
		}
		
		if (socialPlatform.checkHandleLegal(newHandle) == false) {
            throw new IllegalHandleException("This handle already exists in the system, please choose another.");
        }

        if (socialPlatform.checkHandleValid(newHandle) == false) {
            throw new InvalidHandleException("Please ensure your handle is valid (less than 30 characters, no whitespace and not empty).");
        } 

		
		Account platformUserReload = reloadAccount(oldHandle); //Reload account
		platformUserReload.changeAccountHandle(newHandle); //Change handle
	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		
		//Check if handle is legal (not pre-existing)
		if (socialPlatform.checkHandleLegal(handle) == true) {
			throw new HandleNotRecognisedException("This handle does not exist in the system, so the description cannot be updated");   
		}
		
		Account platformUserReload = reloadAccount(handle); //Reload account
		platformUserReload.updateAccountDescription(description); //Change description
	} 

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
	
		Account reloadedAccount = reloadAccount(handle);

		//Counters for posts and endorsements
        int postCounter = 0;
        int endorsedCounter = 0;

		//Get no. of posts (authored by account)
        for (int j=0; j < socialPlatform.getPosts().size(); j++) {
            if (socialPlatform.getPosts().get(j).getAuthor() == handle) {
                postCounter++;
                endorsedCounter = endorsedCounter + socialPlatform.getPosts().get(j).getEndorsements().size(); //Count endorsement posts
            }
        }

		return reloadedAccount.showAccount(postCounter, endorsedCounter);
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

		//Check if handle is legal (not pre-existing)
		if (socialPlatform.checkPostIDLegal(id) == true) {
			throw new PostIDNotRecognisedException("This post does not exist in the system, so it cannot be re-loaded");   
		}

		for (int i=0; i < socialPlatform.getPosts().size(); i++) {    
            if (socialPlatform.getPosts().get(i).getID() == id) { //Matching id (located post)
				
				BasePost reloadedPost = socialPlatform.getPosts().get(i);
				return reloadedPost;
				
            }
        }

		return null;
	}

	@Override
	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {

		//Check author's handle exists
        if (socialPlatform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 

        //Check parent exists 
        if (socialPlatform.checkPostIDLegal(id) == true) {
            throw new PostIDNotRecognisedException("The parent post does not exist in the system");
        }

        //Check parent actionable (not endorsement)
        if (socialPlatform.checkPostActionable(id) == false) {
            throw new NotActionablePostException("The parent post is an endorsement, and cannot be endorsed");
        }

		String parentHandle = null;
		String parentMessage = null;

		int postIDCounter = socialPlatform.getPostIDCounter(); //Get the Post ID Counter

		//Get parent handle and parent message
        for (int i=0; i < socialPlatform.getPosts().size(); i++) {    
            if (socialPlatform.getPosts().get(i).getID() == id) { 
                parentHandle = socialPlatform.getPosts().get(i).getAuthor();
                parentMessage = socialPlatform.getPosts().get(i).getMessage();
            } 
        }

		BasePost platformEndorsement = new EndorsementPost(handle, postIDCounter, id, parentHandle, parentMessage);
        socialPlatform.getPosts().add(platformEndorsement); //Save post

		//Add comment's 'id' to parent's 'comments' arrayList
		BasePost parentPost = reloadPost(id);
        parentPost.getEndorsements().add(platformEndorsement.getID());  
		
		socialPlatform.incrementPostIDCounter(); //Increment counter

		return platformEndorsement.getID();
	}
 
	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		
		//Check author's handle exists
        if (socialPlatform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 

        //Check parent exists 
        if (socialPlatform.checkPostIDLegal(id) == true) {
            throw new PostIDNotRecognisedException("The parent post does not exist in the system");
        }

        //Check parent actionable (not endorsement, can comment)
        if (socialPlatform.checkPostActionable(id) == false) {
            throw new NotActionablePostException("The parent post is an endorsement, and cannot be commented");
        }

        //Check post is valid (less than 30 characters and not empty)
        if (socialPlatform.checkPostValid(message) == false) {
            throw new InvalidPostException("Please ensure your post is valid (less than 30 characters and not empty)");
        }

		int postIDCounter = socialPlatform.getPostIDCounter(); //Get the Post ID Counter

		BasePost platformComment = new CommentPost(handle, postIDCounter, id, message);
        socialPlatform.getPosts().add(platformComment); //Save post

		//Add comment's 'id' to parent's 'comments' arrayList
		BasePost parentPost = reloadPost(id);
        parentPost.getComments().add(platformComment.getID());  

		socialPlatform.incrementPostIDCounter(); //Increment counter

		return platformComment.getID();
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		//Check post exists
        if (socialPlatform.checkPostIDLegal(id) == true) {
            throw new PostIDNotRecognisedException("The post to delete does not exist in the system");
        }

		//Locate Endorsement post to delete and comments to update parent id attribute
        for (int i=0; i < socialPlatform.getPosts().size(); i++) {    
            if (socialPlatform.getPosts().get(i).getID() == id) {

                //delete the endorsement post of the post to be deleted
                if (socialPlatform.getPosts().get(i).getEndorsements().size() != 0) { //Endorsements exist
                    for (int j=0; j < socialPlatform.getPosts().get(i).getEndorsements().size(); j++) { //Interate through endorsement id's 
                        int orphanEndorsementID = socialPlatform.getPosts().get(i).getEndorsements().get(j); //Get id of orphan endorsement post
                            
                        for (int k=0; k < socialPlatform.getPosts().size(); k++) {    
                            if (socialPlatform.getPosts().get(k).getID() == orphanEndorsementID) { //Locate orphan
                                socialPlatform.getPosts().remove(k); //Remove orphan (endorsement) post object 
                            }
                        }
                        
                    }
                }
                
                //update the parent ID attribute of the comments of the post to be deleted
                if (socialPlatform.getPosts().get(i).getComments().size() != 0) {
                    for (int l=0; l < socialPlatform.getPosts().get(i).getComments().size(); l++) {
                        int orphanCommentID = socialPlatform.getPosts().get(i).getComments().get(l);
                        
                        for (int m=0; m < socialPlatform.getPosts().size(); m++) {    
                            if (socialPlatform.getPosts().get(m).getID() == orphanCommentID) { //Locate orphan comment in post array
                                socialPlatform.getPosts().get(m).setParentID(0); //Update parentID to empty post's ID (0)
                                break;
                            }
                        }
                    }
                }

                socialPlatform.getPosts().remove(i); //removes the requested post
            }
        }  
	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		//Check parent exists 
        if (socialPlatform.checkPostIDLegal(id) == true) {
            throw new PostIDNotRecognisedException("The post does not exist in the system");
        }

        String message = "";

		BasePost reloadedPost = reloadPost(id);
        
		message = String.format("ID: %s \n" +
		"Account: %s \n" +
		"No. endorsements: %s | No. comments: %s \n" +
		"%s",
		reloadedPost.getID(), reloadedPost.getAuthor(), reloadedPost.getEndorsements().size(), 
		reloadedPost.getComments().size(), reloadedPost.getMessage());

        return message;
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
            throw new NotActionablePostException("The parent post is an endorsement, and does not have children");
        }

        StringBuilder postDetails = new StringBuilder(); //Create stringbuilder

        //Need to get - handle, num endorsements, num comments, post message 

        for (int i=0; i < socialPlatform.getPosts().size(); i++) {    
            if (socialPlatform.getPosts().get(i).getID() == id) { //Locate original post ID 
                
                if (socialPlatform.getPosts().get(i).getComments().size() != 0) { //Check if original post has comments
                    postDetails.append(showIndividualPost(id) + System.lineSeparator() + "|"); //Add details of original post to string output
                    
                    for (int j=0; j < socialPlatform.getPosts().get(i).getComments().size(); j++) { //Loop through comments, calling on each  
                        
                        String childString = showPostChildrenDetails(socialPlatform.getPosts().get(i).getComments().get(j)).indent(1);  //Indent() indents recursive return (each below parent)

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
        return postDetails; //Return string
    }		
	 

	@Override
	public int getNumberOfAccounts() {
		return socialPlatform.getNumberOfAccounts();
	}

	@Override
	public int getTotalOriginalPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalEndorsmentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalCommentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedPost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedAccount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void erasePlatform() {
        //Accounts arrayList/counters
        socialPlatform.getAccounts().clear(); 
        socialPlatform.setAccountIDCounter(0); //Reset Account's ID counter

        //Posts arrayList/counters
        socialPlatform.getPosts().clear();
        socialPlatform.setPostIDCounter(1);
	}

	@Override
	public void savePlatform(String filename) throws IOException {
		// TODO Auto-generated method stub

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(socialPlatform); //Write platform object

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		//Overwrite socialPlatform with de-serialised object 

	}

}
