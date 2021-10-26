Jawad Timzit
CSSE 436: Cloud Computing
Program 2: http-Program1

This program takes in two argumenets, an http and a NumofHops as a command line argument and will return http websites. My program will download html from the starting URL as the first argument and it will parse it finding the first <a href> reference to other absolute URL's. My program also will vivit only a page 1 time if encouters a page that is been visited already it will skip and find the next reference. The operation will be repeated until until there is no more links then it will print the html in text of the last page and the URL of each page that was visited. The program will be able to do the operation NumHops times.

*** COMPILATION INSTRUCTIONS *** 
All necessary .java and .jar files for compiling the program are included in the project submission: 
    - WebCrawler.java
    - WebCrawler.jar

In order to compile the program, type the following into the command line: 


Upon successful execution of this command, an executable file (.exe for Windows and .out for Ubuntu) will be generated.
To execute the program on Windows, type the following into the command line:
Program.exe Seattle

To execute the program on Ubuntu, type the following into the command line:
./a.out Seattle

The console should then display the requested information on the city.
