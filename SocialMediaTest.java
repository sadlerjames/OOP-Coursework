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


public class SocialMediaTest {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {

        SocialMediaPlatform platform = new SocialMedia();



        try {
            platform.loadPlatform("testing123");
            System.out.println(platform.showAccount("User"));
            
            

            
        } catch (Exception e) {
            System.out.println(e);
        }



    }

}