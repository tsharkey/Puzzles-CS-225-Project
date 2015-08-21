# CS-225-Final-Project
####Final Project for CS-225 Software Design

Current Games

| Game | By | About |
|--------|--------|--------|
| Framework | Catherinen Huang, Tom Sharkey | The design and organization of the project flow |
| Miners Game| Viet Dinh, Sean Johnston, Parth Patel | A game where you need to get everyone out of the collapsing mine in time |
| NineStones | John Cyzeski, Brendan Casey, Brendan Shaughnessy | You are troubled with a puzzle from a shop owner where you are trying to fine a gem amongst ninestones |
| Tigers | Henrique De Aguiar, Maria del Mar, Hannah Riggs | You are presented with two doors and you must try to find your love behind one of them while following the jailor's clues |

##Set-up

#####Download Git
* http://git-scm.com/downloads

* You can use the command line and set-up git hub or use source tree/ GitHub for desktop.
	* https://www.sourcetreeapp.com/ 
* If you are interested in using the command line from a Windows computer, you will need to get an additional program, https://msysgit.github.io/

#####Using terminal you can use the following commands to set up the repository
```cd``` to where you want to save the project

-make a new directory for the project
```mkdir PuzzlesNew``` (or what you want to call the project)

```git clone https://github.com/tsharkey/PuzzlesNew.git```

#####Commiting code to git
######Reccommended, but not necessary. If you do not want to use branches skip ahead
```
git branch <name_of_branch>
git checkout <name_of_branch>
```
You are now on your own branch of master and your code can be pushed to your own branch without impacting other people. 
#####Commiting to your branch or master
```
git add .
git commit -m "Make a useful message here in the quotes"
git push origin <name_of_branch>
```
name_of_branch could be master in some cases or if you made a branch then it is the name of your branch. If this is your first commit to your own branch then you will need to add a flag of -u inbetween push and origin, so that you can track your remote repository. Once you are done with the work on your branch, you will make a pull request into the master branch and that will get looked over and then you can merge your branch into master for everyone to get from the master branch.

#####If you are using SourceTree or Github for Desktop, you can follow the initial instructions for how to setup and use each applicaiton accordingly

###Making your own games
* All games must extend the GamePanel Class, please read the class comments
to see how it will work.
* It is reccommended that you make your own branch for each game and then pull request them into master, as a workflow. This will induce less merge conflicts and make working flow a lot smoother.


###Running the project

To run the project you can use the main method from the main class. Depending on what IDE you are using you may need to make some adjustments. If you are using an IDE that does not understand package structure such as Dr. Java, or BlueJ then you will need to change the relative pathing of the project so the IDE will be able to find the necessary resources. In this case, you will need to change the getResourceAsStream()methods to getResource() and use the relative path from the current file is. If you are using an advanced IDE like Eclipse, Netbeans, or Intellij then the project should be able to run in its current state.

****Important Info****
When making a jar file for the project please understand that it does not recognize
project structure. Therefore the jar file will most likely not work with relative paths such as
"../Games/Resources/image.png" , you will need to start from the the head and go down
"/Main/Games/tigers/images/door.gif"



