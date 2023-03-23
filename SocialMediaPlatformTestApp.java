import socialmedia.AccountIDNotRecognisedException;
import socialmedia.IllegalHandleException;
import socialmedia.InvalidHandleException;
import socialmedia.InvalidPostException;
import socialmedia.NotActionablePostException;
import socialmedia.PostIDNotRecognisedException;
import socialmedia.HandleNotRecognisedException;
import socialmedia.SocialMedia;
import socialmedia.SocialMediaPlatform;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the SocialMediaPlatform interface -- note you will
 * want to increase these checks, and run it on your SocialMedia class (not the
 * BadSocialMedia class).
 *
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class SocialMediaPlatformTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");

		SocialMediaPlatform platform = new SocialMedia();

		assert (platform.getNumberOfAccounts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalOriginalPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";

		Integer id;
		Integer second_user;

		try {
			//testing creation of an account and assigned id
			id = platform.createAccount("my_handle");
			assert (id == 0) : "the id of the created account does not match the supposed one of id = 0";

			second_user = platform.createAccount("my_handle_2");
			System.out.println(second_user);
			assert (second_user == 1): "the id of the created account does not match the supposed one of id = 1";

			//testing getNUmberOfAccounts function
			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";

			//testing removeAccount function
			platform.removeAccount(id);
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";

			//testing adding description and id
			id = platform.createAccount("my_second_handle", "this is the description");
			assert (id == 1) : "the id of the created account does not match the supposed one of id = 1";

			// String accountSummary = String.format("ID: %s \n" +
			// "Handle: %s \n" +
			// "Description: %s \n" +
			// "Post count: %s \n" +
			// "Endorse count: %s",
			// id, handle, description, noPosts, noEndorsements);








			System.out.println("");
			System.out.println("Now testing to see if the description is properly added. It should be: ");
			System.out.println("");
			System.out.println("ID: 1");
			System.out.println("Handle: my_second_handle");
			System.out.println("Description: this is the description");
			System.out.println("Post count: 0");
			System.out.println("Endorse count: 0");
			System.out.println("");
			System.out.println("Now running function:");
			System.out.println(platform.showAccount("my_second_handle"));
			

		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		} catch (HandleNotRecognisedException e) {
			assert (false) : "HandleNotRecognisedException thrown incorrectly";
		}

		try {
			id = platform.createAccount("my_handle", "this is the description of the handle");
			

			// Should print the following in the terminal

			// ID: 0
			// Handle: my_handle
			// Description: this is the description of the handle
			// Post count: 0
			// Endorse count: 0



			
			
			



		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} 



	}

}
