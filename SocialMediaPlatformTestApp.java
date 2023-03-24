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

		Integer first_user;
		Integer second_user;
		Integer third_user;
		Integer fourth_user;

		//testing all functions and features to do with accounts
		try {
			//testing creation of an account and assigned id
			first_user = platform.createAccount("my_handle");
			assert (first_user == 0) : "the id of the created account does not match the supposed one of id = 0";


			//testing getNUmberOfAccounts function
			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";


			//testing removeAccount function
			platform.removeAccount(first_user);
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";

			
			//testing adding description
			second_user = platform.createAccount("my_second_handle", "this is the description");
			assert (second_user == 1) : "the id of the created account does not match the supposed one of id = 1";


			//testing showAccount function
			//expected details about the accont with handle "my_second_handle"
			String second_user_account_description = String.format("ID: 1 \n" +
			"Handle: my_second_handle \n" +
			"Description: this is the description \n" +
			"Post count: 0 \n" +
			"Endorse count: 0");

			assert (platform.showAccount("my_second_handle").equals(second_user_account_description)) : "does not return correct details about the user from the showAccount function";


			//testing changeAccountHandle Function
			platform.changeAccountHandle("my_second_handle", "new_second_handle");

			String second_user_updated_account_description = String.format("ID: 1 \n" +
			"Handle: new_second_handle \n" +
			"Description: this is the description \n" +
			"Post count: 0 \n" +
			"Endorse count: 0");
			assert (platform.showAccount("new_second_handle").equals(second_user_updated_account_description)) : "the accound handle has not been updated to the new one";


			//testing removeAccount function via a id with posts as well as getTotalOriginalPosts
			platform.createPost("new_second_handle", "This is a post"); // id = 1
			platform.createPost("new_second_handle", "This is a second post from the same user"); // id =2
			assert (platform.getTotalOriginalPosts() == 2): "Posts not being created correctly or getTotalOriginalPosts not detecting correct number of posts.";

			platform.removeAccount(second_user);
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";
			assert (platform.getTotalOriginalPosts() == 0): "Posts not being deleted correctly or getTotalOriginalPosts not detecting correct number of posts.";

			
			//testing removeAccount function via a handle with posts as well as getTotalOriginalPosts
			third_user = platform.createAccount("new_second_handle", "this is the description");

			platform.createPost("new_second_handle", "This is a post"); // id = 3
			platform.createPost("new_second_handle", "This is a second post from the same user"); // id = 4
			platform.endorsePost("new_second_handle", 3); // id = 5

			assert (platform.getTotalOriginalPosts() == 2): "Posts not being created correctly or getTotalOriginalPosts not detecting correct number of posts.";
			assert (platform.getTotalEndorsmentPosts() == 1): "Posts not being created correctly or getTotalOriginalPosts not detecting correct number of posts.";

			platform.removeAccount("new_second_handle");
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";
			assert (platform.getTotalOriginalPosts() == 0): "Posts not being deleted correctly or getTotalOriginalPosts not detecting correct number of posts.";
			assert (platform.getTotalEndorsmentPosts() == 0): "Posts not being deleted correctly or getTotalOriginalPosts not detecting correct number of posts.";



			//testing updateAccountDescription function
			fourth_user = platform.createAccount("fourth_user", "this is the description for this accound");

			platform.updateAccountDescription("fourth_user", "this is the new account description");

			String fourth_user_updated_account_description = String.format("ID: 3 \n" +
			"Handle: fourth_user \n" +
			"Description: this is the new account description \n" +
			"Post count: 0 \n" +
			"Endorse count: 0");
			assert (platform.showAccount("fourth_user").equals(fourth_user_updated_account_description)) : "the accound description has not been updated to the new one";



		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		} catch (HandleNotRecognisedException e) {
			assert (false) : "HandleNotRecognisedException thrown incorrectly";
		} catch (InvalidPostException e) {
			assert (false) : "InvalidPostException thrown incorrectly"; 
		} catch (NotActionablePostException e) {
			assert (false) : "NotActionablePostException thrown incorrectly";
		} catch (PostIDNotRecognisedException e) {
			assert (false) : "PostIDNotRecognisedException thrown incorrectly";
		}

		System.out.println("The system ran all tests and didn't find any errors. Yayy you have no bugs!");

	}

}
