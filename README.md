# CS-225-Final-Project
Puzzle Games

Final Project for CS-225 Software Design

Framework by:

Catherine Huang, Thomas Sharkey

Miners Game by:

Viet Dinh, Sean Johnston, Parth Patel

NineStone Game by:

John Cyzeski, Brendan Casey, Brendan Shaughnessy

Tigers Game by:

Henrique De Aguiar, Maria del Mar, Hannah Riggs


set-up
download git
---- http://git-scm.com/downloads

from command line/terminal
<code>cd</code> to where you want to save the project

-make a new directory for the project
<code>mkdir PuzzlesNew</code> (or what you want to call the project)

<code>git clone https://github.com/tsharkey/PuzzlesNew.git</code>


All games must extend the GamePanel Class, please read the class comments
to see how it will work.


Running the project

To run the project you can use the main method from the main class.
Depending on what IDE you are using you may need to make some adjustments.
If you are using an IDE that does not understand package structure such as Dr. Java, or
BlueJ then you will need to change the relative pathing of the project so the IDE will be able
to find the necessary resources. In this case, you will need to change the getResourceAsStream()
methods to getResource() and use the relative path from the current file is. If you are using an
advanced IDE like Eclipse, Netbeans, or Intellij then the project should be able to run in its
current state.

****Important Info****
When making a jar file for the project please understand that it does not recognize
project structure. Therefore the jar file will most likely not work with relative paths such as
"../Games/Resources/image.png" , you will need to start from the the head and go down
"/Main/Games/tigers/images/door.gif"



