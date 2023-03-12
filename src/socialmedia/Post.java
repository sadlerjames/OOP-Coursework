package socialmedia;

public class Post extends BasePost{

    //Class methods 
    //Update 'comments' or 'endorsements' arrayList with child id
    public static void addChild(int id, Integer parentID, int type) {
        //Locate parent 
        for (int i=0; i < getPosts().size(); i++) {    
            if (getPosts().get(i).getID() == parentID) { 
                if (type == 1) { //add to comments arrayList
                    getPosts().get(i).getComments().add(id);
                } else { //add to endorsements arrayList
                    getPosts().get(i).getEndorsements().add(id);
                }
            } 
        }
    }

    //Create generic empty post 
    public Post(int postType) {

        setID(0); //Assign ID 0 
        setPostType(3); //Set post type to 'empty post'
        setMessage("The original content was removed from the system and is no longer available.");
    }

    public Post(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {

        //Check author's handle exists
        if (Platform.checkHandleLegal(handle) == true) {
            throw new HandleNotRecognisedException("This handle does not exist in the system!");
        } 

        if (checkPostValid(message) == false) {
            throw new InvalidPostException("Please ensure your post is valid (less than 30 characters and not empty)");
        }

        int postIDCounter = getPostIDCounter(); //Get current value of counter
        setID(postIDCounter); //Assign ID
        incrementPostIDCounter(); //Increment counter
        setPostType(0); //Set post type to 'normal'
        setMessage(message); 
        setAuthor(handle);
    }
}
