package socialmedia;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * BasePost provides attributes and methods shared between normal, endorsement, and comment posts.
 * 
 * @author Students: 720014004, 720033851
 * @version 1.0 
 */

public class BasePost implements Serializable {

    private int id; //Sequential unique ID
    private int postType; //Post type - 'normal' (0), 'comment' (1), 'endorsement' (2), 'generic empty post' (3)
    private String message;
    private String author; //Author's account handle 

    
    //BasePost Getter methods

    public int getID() {
        return id;
    }

    public int getPostType() {
        return postType;
    }

    public String getMessage() {
        return message;
    }


    public String getAuthor() {
        return author;
    }

 	/**
	 * The method returns the comments of a post, when provided the platform data.
	 * <p>
	 * @param socialPlatform - the Platform object storing the posts, enables searching for child comments.
	 * @return an arrayList containing comment BasePost objects 
	 */   

    public ArrayList<BasePost> getComments(Platform socialPlatform) {

        ArrayList<BasePost> comments = new ArrayList<BasePost>(); //Hold comment BasePost objects

	    for (int i=0; i < socialPlatform.getPosts().size(); i++) { //Iterate through all posts
            
            BasePost iterationPost = socialPlatform.getPosts().get(i);

            if (iterationPost instanceof CommentPost) { 
                CommentPost iterationComment = (CommentPost)iterationPost; //Safely downcast generic BasePost to CommentPost and discard endorsements

                if (iterationComment.getParentID() == id) { //Comment is a child of this post 
                    comments.add(iterationComment); //Add comment to returned array
                }
            }
        }     

        return comments;
    }

 	/**
	 * The method returns the posts endorsing a post, when provided the platform data.
	 * <p>
	 * @param socialPlatform - the Platform object storing the posts, enables searching for endorsements.
	 * @return an arrayList containing endorsement BasePost objects 
	 */   

    public ArrayList<BasePost> getEndorsements(Platform socialPlatform) {

        ArrayList<BasePost> endorsements = new ArrayList<BasePost>(); //Hold endorsement BasePost objects

	    for (int i=0; i < socialPlatform.getPosts().size(); i++) { //Iterate through all posts
            
            BasePost iterationPost = socialPlatform.getPosts().get(i); 

            if (iterationPost instanceof EndorsementPost) {
                EndorsementPost iterationEndorsement = (EndorsementPost)iterationPost; //Safely downcast generic BasePost to EndorsementPost and discard comments

                if (iterationEndorsement.getParentID() == id) { //Endorsement is a child of this post 
                    endorsements.add(iterationEndorsement); //Add endorsement to array
                }
            }
        }     

        return endorsements;
    }

    //BasePost Setter methods

    public void setID(int id) {
        this.id = id;
    }

    public void setPostType(int postType){
        this.postType = postType;
    } 

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
    * Generates a formatted string displaying the details of an Post instance. 
    * (the post's ID, it's author, the number of endorsements, the number of comments, and the post's message)
    * <p>    
    * The number of endorsements and the number of comments are determined by finding the size of the return from the  
    * getEndorsements and getComments methods. Other post information is directly read from the instance attributes. 
    * <p>
    * @return a formatted string containing the post details.
    */ 

    public String genPostDetails(Platform socialPlatform) {

        String message = String.format("ID: %s \n" +
		"Account: %s \n" +
		"No. endorsements: %s | No. comments: %s \n" +
		"%s",
		id, author, getEndorsements(socialPlatform).size(), 
		getComments(socialPlatform).size(), this.message);
        
        return message;
    }

}