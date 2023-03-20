package socialmedia;

import java.io.IOException;

/**
 * SocialMedia is an implementor of the SocialMediaPlatform interface.
 * 
 * @author James Sadler, Joel Sawyer
 * @version 1.0 
 */
public class SocialMedia implements SocialMediaPlatform {

	@Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {//CHANGE THROWS
		Account platformUser = new Account(handle); //Create account object
		Account.getAccounts().add(platformUser); //Store account object

		return platformUser.getID(); //Return 'id' of generated account
	}

	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {

		Account platformUser = new Account(handle, description); //Create account object
		Account.getAccounts().add(platformUser); //Add account object to ArrayList

		return platformUser.getID();
	}

	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		Account platformUserReload = new Account(id, true); //Reload account
		platformUserReload.removeAccount(); //Correct posts


	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		Account platformUserReload = new Account(handle, true); //Reload account
		platformUserReload.removeAccount(); //Correct posts
	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {

		Account platformUserReload = new Account(oldHandle, true); //Reload account
		platformUserReload.changeAccountHandle(newHandle); //Change handle
		Account.getAccounts().add(platformUserReload); //Save account
		
	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		Account platformUserReload = new Account(handle, true); //Reload account
		platformUserReload.updateAccountDescription(description); //Change description
		Account.getAccounts().add(platformUserReload); //Save account
	} 

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
		Account platformUserReload = new Account(handle, true); //Reload account
		String accountSummary = platformUserReload.showAccount(handle); //Load description
		Account.getAccounts().add(platformUserReload); //Save account

		return accountSummary;
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        BasePost platformPost = new Post(handle, message); //Create post, initialise 
        BasePost.getPosts().add(platformPost); //Save post 
		return platformPost.getID(); //Return ID of post
	}

	@Override
	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		return 0;
	}
 
	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub
 
	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub
		return null; 
	}

	@Override
	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub 
		return null;
	} 

	@Override
	public int getNumberOfAccounts() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void savePlatform(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

}
