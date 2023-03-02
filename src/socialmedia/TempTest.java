package socialmedia;

public class TempTest {

    public static void main(String[] args){
        Account testUser = new Account();
        Account secondTestUser = new Account();
        //Prints account ID
        System.out.println(testUser.createAccount("James"));
        System.out.println(secondTestUser.createAccount("Joel", "jksjflkds"));
        
        testUser.updateAccountDescription("sdfjdslk", "skjfjaslke");
    }


    
}
