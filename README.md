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
- Improve removeAccount methods (exception handling)
- May be posible to optimise OO nature of some Account methods after modifying posts
- Check whether need to validate account description 
- Move methods back out to classes where possible (showIndividualPost?)/have objects do more of the computational load
- Change all ==, !=
- Move showIndividualPost to Post class
- Check through all 'for' loops, are they necessary? 
- What version of java will execute? Aim to remove '.indent' as is a workaround 
- Does showPostChildrenDetails always return in correct order? Guarrantee? 
- Correctly handle serialisation exceptions/test
- Delete 1st account in platform, should next account be allocated ID of prev (0)? 
- Modify showPostChildrenDetails to have helper function/use new method
- Check all naming conventions adhered to throughout
- Go through lecture slides/workshops, implement as many concepts as possible 
- Should you be able to show post children details of the generic empty post?
- Should description be "null" if not set? 
- Do we need separate comment/endorsement/normal post classes? 
- Coversheet vs coverpage? Also convert to PDF when done
- Using a custom equals() override in our classes? 
- Move parentID to comment/endorsement comments as not needed for normal posts
- Tested posts 100chars max? 
- Shoudld we be adding comments to the 
- showAccount formatting is different to ours? 
- showAccount doesn't load in objects
- Should BasePost be an abstract class? 


Question list:
- showIndividualPost - should it show only the number of direct comments on that post? 
- showIndividualPost - how to deal with message = "" being needed, however redundant 
- Check type in showPostChildrenDetails
- Does our test file get marked? Do we need assertions in the code as well as test file? 


