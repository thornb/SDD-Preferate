In order to start the spring server, make sure you have the most updated files pulled from github. Also make sure you have maven and spring correctly installed.


Then navigate to the SDD-Preferate folder and run the command

mvn spring-boot:run

(If you are on the sdd account on the linux server use sudo becuase you need root permissions. Enter the same password for logging in)
sudo mvn spring-boot:run

This should have maven and spring compile, build and launch the tomcat server on port 8080


Since going to www.rpipreferate.com defaults to port 80, we will host the WebDocs portion of the project in the /var/www/rpipreferate.com/public_html folder

So copy the files into the apache port 80 server after a git pull by using the command:
cp -a /home/sdd/GitHub/SDD-Preferate/WebDocs/. /var/www/rpipreferate.com/public_html/
in ubuntu:
sudo cp -a ~/Desktop/SDD-Preferate/WebDocs/. /var/www/html

then if you go to rpipreferate.com you should be able to view the web files
