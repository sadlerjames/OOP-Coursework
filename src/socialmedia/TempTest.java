package socialmedia;
import java.io.IOException;

public class TempTest {

    public static void main(String[] args) throws ClassNotFoundException, IOException, IllegalHandleException, InvalidHandleException, HandleNotRecognisedException, InvalidPostException, NotActionablePostException, PostIDNotRecognisedException, AccountIDNotRecognisedException {

        SocialMedia socialMediaObj = new SocialMedia(); //Create a platform 

        //socialMediaObj.createAccount("JoelMSawyer");
        //socialMediaObj.createAccount("JamesS");
        

        //socialMediaObj.commentPost("JoelMSawyer", 1, "This is a test comment");

        //socialMediaObj.updateAccountDescription("JamesS", "We love Java...");

        socialMediaObj.loadPlatform("testSave.ser");

        //System.out.println(socialMediaObj.socialPlatform.getAccounts().get(0).getHandle());

        System.out.println(socialMediaObj.showAccount("JamesS"));

        //System.out.println(socialMediaObj.socialPlatform.getAccounts().get(0).getHandle());

        //socialMediaObj.removeAccount(0);

        // System.out.println(socialMediaObj.showIndividualPost(1));

        //System.out.println(socialMediaObj.showPostChildrenDetails(1));



   

        

        
        //Change handle (to -> SocialMedia class) 
        // Account accountObjToUpdate = Account.getAccounts().get(1); //Get account 1

        // accountObjToUpdate.changeAccountHandle("James", "JamesNewHandle");

        //Update description 

        // accountObjToUpdate.updateAccountDescription("James", "We all love Java?");
        

        // //Check if handle is an attribute in ArrayList of objects
        // for (int i=0; i < Account.getAccounts().size(); i++) {    
        //     System.out.println(Account.getAccounts().get(i).getDescription());
        // }

        //Create empty post 

        // BasePost emptyPost = new Post(3);

        // BasePost platform2Post = new Post("JoelMSawyer", "We do love a bit of java!!!");
        // BasePost.getPosts().add(platform2Post);


        // //Delete account 

		// Account platformUserReload5 = new Account(0, true); //Reload account
		// platformUserReload5.removeAccount();


        // BasePost platformPost = new Post("James", "We do love a bit of javaaa");
        // BasePost.getPosts().add(platformPost);



        // BasePost platformEndorsement = new EndorsementPost("James", 2);
        // BasePost.getPosts().add(platformEndorsement);


        // System.out.println(BasePost.getPosts().get(1).getPostType()); //Stores post at index pos 


        // Account.removeAccount("JoelMSawyer");

        //for (int i=0; i < Account.getAccounts().size(); i++) {    
           // System.out.println(Account.getAccounts().get(i).getHandle());
       // }


        // // //Creating a commment 
        // BasePost platformComment = new CommentPost("James", 1, "Hello");
        // BasePost.getPosts().add(platformComment);

        // BasePost platformComment2 = new CommentPost("James", 1, "Hi there");
        // BasePost.getPosts().add(platformComment2);

        // BasePost platformComment3 = new CommentPost("JoelMSawyer", 4, "sup");
        // BasePost.getPosts().add(platformComment3);

        // BasePost platformComment4 = new CommentPost("JoelMSawyer", 6, "hi thereee");
        // BasePost.getPosts().add(platformComment4);

        // BasePost platformComment5 = new CommentPost("James", 1, "Hello");
        // BasePost.getPosts().add(platformComment5);


        // System.out.println(BasePost.showPostChildrenDetails(1));

        // Platform platformObj = new Platform();

        // System.out.println(platformObj.getMostEndorsedAccount());

     //Check if handle is an attribute in ArrayList of objects
        // System.out.println("Remaining Posts: ");

        // for (int i=0; i < BasePost.getPosts().size(); i++) {    

        //     BasePost aPost = BasePost.getPosts().get(i); //Stores post at index pos 

        //     System.out.println(aPost.getAuthor());
        //     System.out.println(aPost.getMessage());



        // }


        
        
        // BasePost.deletePost(1);

        // System.out.println("Deleted post");
        // for (int i=0; i < BasePost.getPosts().size(); i++) {    

        //     BasePost aPost = BasePost.getPosts().get(i); //Stores post at index pos 

        //     System.out.println(aPost.getID());
        //     System.out.println(aPost.getComments());
        //     System.out.println(aPost.getAuthor());
        //     System.out.println(aPost.getParentID());
        // }


    }
}