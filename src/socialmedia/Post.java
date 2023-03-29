package socialmedia;

/**
 * Post provides constructors to permit the creation of standard posts on the platform, 
 * in addition to a no-arg contstructor permitting the creation of the generic empty post. 
 * @author Students: 720014004, 720033851
 * @version 1.0 
 */

public class Post extends BasePost {

    /**
    * Constructor for the generic empty post, always assigning ID 0, type '3' (empty post), 
    * and the provided empty post message.
    * <p>
    * When a post with comments is deleted, the parentID attributes of its comments are set to 0, 
    * referring them to this post.
    */     
    
    public Post() {
        setID(0); //Assign ID 0 
        setPostType(3); //Set post type to 3 - 'generic empty post'
        
        //Provided empty post message
        setMessage("The original content was removed from the system and is no longer available.");
    }

    /**
    * Constructor for a new standard Post in the platform, initialising
    * handle, ID, and message instance attributes. 
    * <p>
    * @param handle - the handle of the account authoring the post 
    * @param id - the post's sequential ID
    * @param message - the post's message  
    */ 

    public Post(String handle, int id, String message) {
        setID(id); //Assign ID
        setPostType(0); //Set post type to 'normal'
        setMessage(message); //Set message to provided string 
        setAuthor(handle); //Set author to provided handle 
    }
}
