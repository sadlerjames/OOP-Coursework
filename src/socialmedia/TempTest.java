package socialmedia;

public class TempTest {

    public static void main(String[] args) throws IllegalHandleException, InvalidHandleException, HandleNotRecognisedException, InvalidPostException, NotActionablePostException, PostIDNotRecognisedException, AccountIDNotRecognisedException {

        SocialMedia socialMediaObj = new SocialMedia(); //Create a platform

        socialMediaObj.createAccount("JamesS");
        socialMediaObj.createPost("JamesS", "This is a post!");
        socialMediaObj.updateAccountDescription("JamesS", "We love Java...");

        socialMediaObj.removeAccount(0);

        System.out.println(socialMediaObj.showIndividualPost(1));

        //socialMediaObj.showAccount("JamesS");

		//Account platformUser = new Account("JoelMSawyer", "We don't");
        //Account platformUser2 = new Account("James"); //Create account object
		//socialPlatformObj.getAccounts().add(platformUser); //Add account object to arrayList  
        //socialPlatformObj.getAccounts().add(platformUser2);  

   

        

        
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