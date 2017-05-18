# htmlParser

<b>READ ME FIRST</b>

This project is not licenced by anyone, use freely in whatever way you want.

<b>WHAT IS IT</b>

Project created to parse the egis github page that lists all the technologies egis software uses. 
Page can be found here: https://github.com/egis/handbook/blob/master/Tech-Stack.md

<b>GETTING STARTED</b>

- clone the project to your local
	- git clone https://github.com/zwakelem/htmlParser.git
	Note: before this you should have initialised git and have login credentials on github.
	see <a href="https://guides.github.com/activities/hello-world/">this</a> for help.
	
- package into a jar
	- this is a maven project, navigate to folder with "pom.xml"
	- run "mvn clean assembly:single"
	- this should result in .jar in target folder.
	 
- run
	- navigate to target folder
	- run by passing the url(https://github.com/egis/handbook/blob/master/Tech-Stack.md) as an argument
	java -jar htmlparser-egis-1.0-jar-with-dependencies.jar https://github.com/egis/handbook/blob/master/Tech-Stack.md
		






