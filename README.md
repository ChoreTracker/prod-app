
# PROD
Version: 1.0
PROD provides roommates, teams, and families an exciting new way to share responsibilities and track what needs to be done. Its unique mission orientation provides motivation for doing things like making your bed, cleaning the kitchen, or tracking which of your coworkers cleaned out their leftovers from the fridge. PROD tracks who is doing what, when it needs to be done, and what’s already been completed. PROD, keeping everyone productive!

## Deployment

When cloning the project, run the ProdAppApplication.java file, then navigate to localhost:8080/login on your browser and sign in using one of the following username/password combinations: Dad/admin, Mom/admin, Emma/user, Lucy/user, Sophie/user, or Jackson/user. This application was optimized for use on an iPad. We’ve built in features that allow users to add new Sectors (rooms/areas) into their space, new Missions (chores/tasks), and add new users. Users can select from various avatars and icons, and can change to different themes as well. We also built in a point-based reward system that users can also utilize.
 
## Built With
Spring Boot/MV/Securtiry - The web framework used
Java JPA - Crud repositories
Gradle - Dependency Management

## Dependencies Used
org.springframework.boot:spring-boot-starter-data-jpa
org.springframework.boot:spring-boot-starter-security
org.springframework.boot:spring-boot-starter-thymeleaf
‘org.thymeleaf.extras', name: 'thymeleaf-extras-springsecurity5'
org.springframework.boot:spring-boot-starter-web
org.springframework.data:spring-data-rest-hal-browser
org.springframework.boot:spring-boot-devtools
com.h2database:h2
test'org.springframework.boot:spring-boot-starter-test
test'org.springframework.security:spring-security-test

## Future Implementations

	•	Dynamic setup (to allow creation of Sectors and Missions based on user selected values)
	•	Interactive rewards system to track and update balances
	•	Notifications providing email and push notifications to users as missions are assigned and completed
	•	Picture uploads to allow admins to provide pictures of what the completed mission looks like as well as a comparison of what it looks like now
	•	More Themes - including dynamic changing of icons and text
	•	Formatting for devices beyond iPad
	•	Repository persistence and distribution across multiple devices
	•	Mobile device native application
	•	Accessibility
	•	Localization
 
## Contributors
-Don Miesle - https://github.com/dmiesle
-Hethur McKinley Aluma - https://github.com/hmcka
-Kurt Swinehart - https://github.com/KurtSwinehart
-Lisa Rees - https://github.com/L-Rees
