# GittiGidiyorUITestProject

Prerequisites: Java 11 version should be installed and JAVA_HOME should be set as local machine environment, 
Maven 3 version should be downloaded and should be set as maven path in local machine.

PS 1: Since there is chromewebdirver 11 version in resousrce folder, Chrome 11 version should be installed in local machine. If there is different Chrome version in local
chrome driver version should be changed as same as with Chrome version.
PS 2 : If Docker is installed in local machine, "docker-compose up" command should be run in project folder. After command execution finished and all selenium nodes are running,
test can be run through selenium grid with selenium remote chrome web driver option.

In project folder, 

if test would like to be run with chromewebdirver in local, command at below should be run
mvn -Dwebdriver=chrome install

if test would like to be run with remotewebdriver through selenium grid, command at below should be run
mvn -Dwebdriver=remote install

PS 3 : After project run successfully, the price is obtained from web page is saved in "product.txt" file. I left this file in place to be an example, it can be deleted before test execution. 
