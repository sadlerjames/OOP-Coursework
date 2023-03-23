# Object Oriented Programming Coursework

### Structure of Project
Interface files (`SocialMediaPlatform.java` and `MiniSocialMediaPlatform.java`) defines how the front end interacts with the backend. We don't write any code in the platform files. The interface is like a specification file of what front end wants. 

Ignore `BadSocialMedia.java` and `BadMiniSocialMedia.java`
SocialMedia.java is the code for the methods in the interface files.

Everything we write (e.g. classes) will be called from the `SocialMedia.java` file.

TODO: 
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
- May be posible to optimise OO nature of some Account methods after modifying posts
- Create the empty post when platform initialised 
- Check whether need to validate account description 
- Intead of using for loops to access object variables, reload the object itself
- Update deletePost to use new method 
- Move methods back out to classes where possible (showIndividualPost?)
- Change all ==, !=
- Move showIndividualPost to Post class
- Check through all 'for' loops, are they necessary? 
- What version of java will execute? Aim to remove '.indent' as is a workaround 
- Does showPostChildrenDetails always return in correct order 
- Correctly handle serialisation exceptions 
- Delete 1st account in platform, should next account be allocated ID of prev (0)? 
- Modify showPostChildrenDetails to have helper function/use new method
- Where is correct location for the testapp? In src folder? 

Question list:
- showIndividualPost - should it show only the number of direct comments on that post? 
- showIndividualPost - how to deal with message = "" being needed, however redundant 
- Check type in showPostChildrenDetails
- Does our test file get marked? Do we need assertions in the code aswell as test file? 


