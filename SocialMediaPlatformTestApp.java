import java.io.IOException;

import socialmedia.AccountIDNotRecognisedException;
import socialmedia.IllegalHandleException;
import socialmedia.InvalidHandleException;
import socialmedia.InvalidPostException;
import socialmedia.NotActionablePostException;
import socialmedia.Platform;
import socialmedia.Post;
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
		Integer fith_user;
		Integer sixth_user;

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
			System.out.println(platform.getTotalOriginalPosts());
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";
			assert (platform.getTotalOriginalPosts() == 0): "Posts not being deleted correctly or getTotalOriginalPosts not detecting correct number of posts. For original posts.";
			assert (platform.getTotalEndorsmentPosts() == 0): "Posts not being deleted correctly or getTotalOriginalPosts not detecting correct number of posts. For endorsement post";



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

		Integer sixth_post;
		Integer seventh_post;
		Integer eigth_post;
		Integer ninth_post;
		Integer tenth_post;

		//testing all functions and features to do with posts
		try {
			//creating users to test the post functions on
			fith_user = platform.createAccount("fith_user");
			sixth_user = platform.createAccount("sixth_user");


			//creating a post and testing if it has the correct ID
			sixth_post = platform.createPost("fith_user", "This is a post message");
			assert (sixth_post == 6): "the created post has been set the wrong ID";


			//endorsing a post and testing if it has correct ID and has been given the correct details (e.g. description)
			seventh_post = platform.endorsePost("fith_user", 6);
			assert (seventh_post == 7): "the created endorsement has been set the wrong ID";
			assert (platform.getTotalEndorsmentPosts() == 1): "The system has calculated the wrong number of endorsements";

			String endorsed_post_details = String.format("ID: 7 \n" +
			"Account: fith_user \n" +
			"No. endorsements: 0 | No. comments: 0 \n" +
			"EP@fith_user: This is a post message");
			
			assert (platform.showIndividualPost(7).equals(endorsed_post_details)): "The endorsed post does not have the right details or in the correct format";


			//testing comments to see if given correct ID
			eigth_post = platform.commentPost("sixth_user", 6, "This is a commend on ID Post 6");

			assert (eigth_post == 8): "The comment has been assigned the wrong ID";


			//testing comments of comments and show post children details
			ninth_post = platform.commentPost("fith_user", 8, "This is a comment on a comment, yayyy exciting");
			assert (ninth_post == 9): "The comment has been assigned the wrong ID";

			assert (platform.getTotalCommentPosts() == 2): "The total number of comments in the ssytem does not add up";

			StringBuilder postChildrenDetails = new StringBuilder(); //Create stringbuilder

			postChildrenDetails.append("ID: 6"+System.lineSeparator());
			postChildrenDetails.append("Account: fith_user"+System.lineSeparator());
			postChildrenDetails.append("No. endorsements: 1 | No. comments: 1"+System.lineSeparator());
			postChildrenDetails.append("This is a post message"+System.lineSeparator());
			postChildrenDetails.append("|"+System.lineSeparator());
			postChildrenDetails.append("| > ID: 8"+System.lineSeparator());
			postChildrenDetails.append("    Account: sixth_user"+System.lineSeparator());
			postChildrenDetails.append("    No. endorsements: 0 | No. comments: 1"+System.lineSeparator());
			postChildrenDetails.append("    This is a commend on ID Post 6"+System.lineSeparator());
			postChildrenDetails.append("    |"+System.lineSeparator());
			postChildrenDetails.append("    | > ID: 9"+System.lineSeparator());
			postChildrenDetails.append("        Account: fith_user"+System.lineSeparator());
			postChildrenDetails.append("        No. endorsements: 0 | No. comments: 0"+System.lineSeparator());
			postChildrenDetails.append("        This is a comment on a comment, yayyy exciting");
			
			System.out.println(" ");
			System.out.println("Need to check that the following output matches the one after");
			System.out.println(" ");

			System.out.println(postChildrenDetails);
			System.out.println(" ");
			System.out.println(platform.showPostChildrenDetails(6));

			// assert (platform.showPostChildrenDetails(6).equals(postChildrenDetails)): "PostChildrenDetails have not been displayed in the correct format";

			
			//testing delete post
			tenth_post = platform.createPost("sixth_user", "Hello this is a post about nothing");

			assert (tenth_post == 10): "The tenth post has been assigned the wrong ID";
			assert (platform.getTotalOriginalPosts() == 2): "The system has counted the number of original posts wrong";

			platform.deletePost(10);
			assert (platform.getTotalOriginalPosts() == 1): "The system has not counted the correct number of posts after deleting one";


			//testing getNumberOfAccounts
			assert (platform.getNumberOfAccounts() == 3): "The system has not counted the correct number of accounts";
			

			//testing getMostEndorsedPost
			platform.createPost("sixth_user", "this is another post");
			platform.createPost("sixth_user", "this is a second post");
			platform.createPost("sixth_user", "this is a third post");

			platform.endorsePost("fith_user", 11);
			platform.endorsePost("fourth_user", 11);
			platform.endorsePost("fith_user", 11);

			platform.endorsePost("fith_user", 12);
			platform.endorsePost("fourth_user", 13);

			assert (platform.getMostEndorsedPost() == 11): "Does not get the ID of the most endorsed post";


			//testing getMostEndorsedAccount
			assert (platform.getMostEndorsedAccount() == 5): "Does not get the ID of the account with the most number of endorsements";


		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (HandleNotRecognisedException e) {
			assert (false) : "HandleNotRecognisedException thrown incorrectly";
		} catch (InvalidPostException e) {
			assert (false) : "InvalidPostException thrown incorrectly"; 
		} catch (PostIDNotRecognisedException e) {
			assert (false) : "PostIDNotRecognisedException thrown incorrectly";
		} catch (NotActionablePostException e) {
			assert (false) : "NotActionablePostException thrown incorrectly";
		}


		//testing to see if erasing, loading and saving platform works
		try {

			//saving the platform to a file
			platform.savePlatform("Platform.ser");

			//erasing the platform
			platform.erasePlatform();

			//testing to see if all values have been reset
			assert (platform.getNumberOfAccounts() == 0): "The platform has not erased all accounts";
			assert (platform.getTotalOriginalPosts() == 0): "The platform has not erased all original posts";
			assert (platform.getTotalCommentPosts() == 0): "The platform has not erased all comment posts";
			assert (platform.getTotalEndorsmentPosts() == 0): "The platform has not erased all endorsment posts";
			

			platform.loadPlatform("Platform.ser");

			//testing to see if all data has been loaded back in from the file
			assert (platform.getNumberOfAccounts() == 3): "The platform has not loaded all accounts";
			assert (platform.getTotalOriginalPosts() == 4): "The platform has not erased loaded original posts";
			assert (platform.getTotalCommentPosts() == 2): "The platform has not erased loaded comment posts";
			assert (platform.getTotalEndorsmentPosts() == 6): "The platform has not erased loaded endorsment posts";



		} catch (IOException e){
			assert (false): "IOException thrown incorrectly";
		} catch (ClassNotFoundException e){
			assert (false): "ClassNotFoundException thrown incorrectly";
		}

		platform.erasePlatform();
		System.out.println("Testing errors:");
		
		//testing to see if invalid handle exception and illegal handle exception 
		// try {
			
		// 	//testing to see if an invalid handle is entered into create account it throws an error
		// 	platform.createAccount("hellosdf ");

		// 	//testing to see if an invalid handle is entered into create account it throws an error
		// 	platform.createAccount("first_user");
		// 	platform.createAccount("first_user");

		// } catch (IllegalHandleException e) {
		// 	assert (false): "Illegal Handle Excpetion";
		// } catch (InvalidHandleException e) {
		// 	assert (false): "Invalid Handle Exception";
		// }

		

		//testing to see if AccountIDNotRecognisedException and HandleNotRecognisedException work
		// int test_user;
		// try {
		// 	test_user = platform.createAccount("user");

		// 	// platform.removeAccount(test_user + 1);

		// 	platform.removeAccount("userr");




		// } catch (IllegalHandleException e) {
		// 	assert (false): "Illegal Handle Excpetion";
		// } catch (InvalidHandleException e) {
		// 	assert (false): "Invalid Handle Exception";
		// }  catch (HandleNotRecognisedException e) {
		// 	assert (false): "Handle Not Recognised Exception";
		// }
		// // catch (AccountIDNotRecognisedException e) {
		// // 	assert (false): "Account ID Not Recognised Exception";
		// // }





		//testing to see if Invalid Post Exception works
		// try {
		// 	platform.createAccount("user");

		// 	platform.createPost("user", "");

		// 	platform.createPost("user", "jjdsjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");



		// } catch (IllegalHandleException e) {
		// 	assert (false): "Illegal Handle Excpetion";
		// } catch (InvalidHandleException e) {
		// 	assert (false): "Invalid Handle Exception";
		// } catch (HandleNotRecognisedException e) {
		// 	assert (false): "Handle Not Recognised Exception";
		// }
		// catch (InvalidPostException e) {
		// 	assert (false): "Invalid Post Exception";
		// }




		//testing to see if PostIDNotRecognisedException and NotActionablePostException

		int aPost;
		int bPost;
		try {
			platform.createAccount("user");

			aPost = platform.createPost("user", "Hello");

			platform.endorsePost("user", 0);
			
			// platform.endorsePost("user", bPost);



		} catch (IllegalHandleException e) {
			assert (false): "Illegal Handle Excpetion";
		} catch (InvalidHandleException e) {
			assert (false): "Invalid Handle Exception";
		} catch (HandleNotRecognisedException e) {
			assert (false): "Handle Not Recognised Exception";
		}
		catch (InvalidPostException e) {
			assert (false): "Invalid Post Exception";
		}
		catch (PostIDNotRecognisedException e) {
			assert (false): "PostIDNotRecognisedException";
		}
		catch (NotActionablePostException e) {
			assert (false): "NotActionablePostException";
		}



		System.out.println("The system ran all tests and didn't find any errors. Yayy you have no bugs!");

	}

}
