package socialmedia;

public class TempTest {

    public static void main(String[] args) throws IllegalHandleException, InvalidHandleException, HandleNotRecognisedException, InvalidPostException {
        //Will be moved to SocialMedia class 
		Account platformUser = new Account("JoelMSawyer", "We don't");
        Account platformUser2 = new Account("James"); //Create account object
		Account.getAccounts().add(platformUser); //Add account object to arrayList  
        Account.getAccounts().add(platformUser2);  
        // System.out.println(platformUser.getID());
        // System.out.println(platformUser2.getID());


        
        //Change handle (to -> SocialMedia class) 
        // Account accountObjToUpdate = Account.getAccounts().get(1); //Get account 1

        // accountObjToUpdate.changeAccountHandle("James", "JamesNewHandle");

        //Update description 

        // accountObjToUpdate.updateAccountDescription("James", "We all love Java?");
        

        // //Check if handle is an attribute in ArrayList of objects
        // for (int i=0; i < Account.getAccounts().size(); i++) {    
        //     System.out.println(Account.getAccounts().get(i).getDescription());
        // }


        BasePost platformPost = new Post("James", "We do love a bit of java");

        BasePost.getPosts().add(platformPost);

        //Check if handle is an attribute in ArrayList of objects
        for (int i=0; i < BasePost.getPosts().size(); i++) {    
            System.out.println(BasePost.getPosts().get(i).getID());
            System.out.println(BasePost.getPosts().get(i).getAuthor());
            System.out.println(BasePost.getPosts().get(i).getMessage());
        }


    }
}

    //     //Prints account ID
    //     System.out.println(testUser.createAccount("James"));
    //     System.out.println(secondTestUser.createAccount("Joel", "jksjflkds"));
        
    //     testUser.updateAccountDescription("sdfjdslk", "skjfjaslke");
    // 


    
