package socialmedia;

public class TempTest {

    public static void main(String[] args) throws IllegalHandleException, InvalidHandleException, HandleNotRecognisedException, InvalidPostException, NotActionablePostException, PostIDNotRecognisedException, AccountIDNotRecognisedException {
        //Will be moved to SocialMedia class 
		Account platformUser = new Account("JoelMSawyer", "We don't");
        Account platformUser2 = new Account("James"); //Create account object
		Account.getAccounts().add(platformUser); //Add account object to arrayList  
        Account.getAccounts().add(platformUser2);  


        
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

        BasePost emptyPost = new Post(3);


        BasePost platformPost = new Post("James", "We do love a bit of javaaa");
        BasePost.getPosts().add(platformPost);

        BasePost platform2Post = new Post("JoelMSawyer", "We do love a bit of java!!!");
        BasePost.getPosts().add(platform2Post);

        BasePost platformEndorsement = new EndorsementPost("James", 2);
        BasePost.getPosts().add(platformEndorsement);

        BasePost platform2Endorsement = new EndorsementPost("JoelMSawyer", 1);
        BasePost.getPosts().add(platform2Endorsement);

        BasePost platform3Endorsement = new EndorsementPost("JoelMSawyer", 2);
        BasePost.getPosts().add(platform3Endorsement);

        // System.out.println(BasePost.getPosts().get(1).getPostType()); //Stores post at index pos 


        Account.removeAccount("JoelMSawyer");

        for (int i=0; i < Account.getAccounts().size(); i++) {    
            System.out.println(Account.getAccounts().get(i).getHandle());
        }


        // //Creating a commment 
        // BasePost platformComment = new CommentPost("James", 1, "Hello");
        // BasePost.getPosts().add(platformComment);

        // Platform platformObj = new Platform();

        // System.out.println(platformObj.getMostEndorsedAccount());

     //Check if handle is an attribute in ArrayList of objects
        System.out.println("Remaining Posts: ");

        for (int i=0; i < BasePost.getPosts().size(); i++) {    

            BasePost aPost = BasePost.getPosts().get(i); //Stores post at index pos 

            System.out.println(aPost.getAuthor());
            System.out.println(aPost.getMessage());



        }
        
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

    //     //Prints account ID
    //     System.out.println(testUser.createAccount("James"));
    //     System.out.println(secondTestUser.createAccount("Joel", "jksjflkds"));
        
    //     testUser.updateAccountDescription("sdfjdslk", "skjfjaslke");
    // 


    
