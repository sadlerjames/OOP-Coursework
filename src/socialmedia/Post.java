package socialmedia;

public class Post extends BasePost {

    //Constructor for generic empty post 
    public Post() {

        setID(0); //Assign ID 0 
        setPostType(3); //Set post type to 'empty post'
        setMessage("The original content was removed from the system and is no longer available.");
    }

    public Post(String handle, int id, String message) {

        setID(id); //Assign ID
        setPostType(0); //Set post type to 'normal'
        setMessage(message); 
        setAuthor(handle);
    }
}
