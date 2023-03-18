# Object Oriented Programming Coursework

### Structure of Project
Interface files (`SocialMediaPlatform.java` and `MiniSocialMediaPlatform.java`) defines how the front end interacts with the backend. We don't write any code in the platform files. The interface is like a specification file of what front end wants. 

Ignore `BadSocialMedia.java` and `BadMiniSocialMedia.java`
SocialMedia.java is the code for the methods in the interface files.

Everything we write (e.g. classes) will be called from the `SocialMedia.java` file.

TODO: 
- Check/audit static methods 
- Potentially modify Post constructor to use Account objects
- Check getter methods required 
- Consider moving posts ArrayList from BasePost.java to Post.java
- Cannot access parentID attribute of comment post 
- Should numComments be double or int type?
- Audit use of getter and setter methods? 
- Add assertions throughout codebase
- Correctly handling deleting comments vs normal posts? Should comments be set to the generic empty post
- ShowIndividualPost on an endorsement? Can be shown, just no comments or endorsements (0). 
- getMostEndorsedPost sometimes set to empty post? Correct behaviour? Some for getMostEndorsedAccount. Sometimes returns null?
- fix showIndividualPost formatting 
- Possibility of reducing the number of static objects? Reloading saved objects to enable?
- Improve removeAccount methods
- Add @override where needed

Question list:
- showIndividualPost - should it show only the number of direct comments on that post? 
- showIndividualPost - how to deal with message = "" being needed, however redundant 
- Check type in showPostChildrenDetails


