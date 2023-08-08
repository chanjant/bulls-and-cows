# Bulls & Cows (10% in final grade, with 80 total marks)

```text
Author: Jant Chan
Note:
Task Eight: Advanced Configuration
- Player are allowed to change the maximum allowed number of turns
- Player are allowed to change the length of a secret code. Maximum length is 10 as there's only 10 digits.
- Player are allowed to configure the above values by keyboard input. They will be asked and have a choice to configure before the game start.

```
## Overview
This assignment requires you to develop a simple game called Bulls & Cows. There are eight compulsory tasks in this assignment to complete.

Before starting the assignment, read through and gain an understanding of the requirements.

## Criteria
Your assignment will be marked based on the correctness of your program and your programming style. Here are some questions to consider for the programming style:

* Is the code well-structured?
* Is the code self-explanatory?
* Can you understand the code easily?
* Are all variables properly defined with meaningful and clear code?
* Is there any commented out code?

Marks will be deducted if your code cannot be compiled or has bad programming style.

## Submission
After completing the assignment, you should have a functional Java program with some number of classes (depending on your own design), one UML class diagrams. You submit by ensuring your GitHub repository for this assignment is up-to-date. We will download your repository for marking on the due date.

Late submissions are not permitted. You will get a mark of zero if your repository does not contain your submission when we download it.

## Assignment Details
The bulls and cows game is a code-breaking game designed for two or more players. Each player chooses a secret code of 4 digits from 0 – 9. The digits must be all different. The goal of the game is for each player to guess the other player's secret code.

The players in turn present their guesses to the opponents. The opponents respond by telling the players:
1. The number of bulls, i.e. the number of matching digits in their right positions, and
2. The number of cows, i.e. the number of matching digits but in different positions.

For example, if the computer's secret code is 4281, the match responses for the following guesses are shown as follows:

![alt text](bc-first.PNG "Example Output 1")

For more information about the game itself can be found [here](https://en.wikipedia.org/wiki/Bulls_and_Cows).

### Requirements
The main goal of this assignment is to develop the bulls and cows game that allows a single player to play interactively against the computer. The game stores two secret codes, one from the player and one from the computer. The player and the computer will try to guess each other’s secret code. Both the player and the computer only have seven attempts for guessing the secret code. If the player enters an invalid input, the game should ask the player to try again. The game also lets the player choose the difficulty level to play against the computer. There are three levels: easy, medium and hard. The details of the difficulty levels will be described in later sections. In addition, the game can read from a text file that contains multiple guesses from the player, and save the results to another text file.

For this assignment, you'll complete a series of tasks as you work your way towards a fully functional implementation. As you complete each task, it might be a good idea to save a working version of the program at that point.

## Task One: Design and Feedback (10 marks)
For this assignment, there is very little which is already given to you. Through this assignment, you'll gain experience in designing and building a complex program from scratch. Before starting to code, don't forget to design your classes and methods (i.e. create UML class and sequence diagrams)! You should apply the concepts you have learned so far in the course. You should not have everything in one class, and try to promote code reuse as much as possible. You must have at least one use of inheritance in this assignment.

Using either pen & paper or the diagramming tool of your choice, prepare a UML class diagram which shows all the classes and important methods of your Bulls & Cows implementation, along with the appropriate relationships showing how these classes fit together. You should add this class diagram (as a PNG or JPEG) in the top level of your repository (i.e. where the README is) before the end of the first week of the semester break.

To receive full marks for this assignment, you must show your design to one of the teaching staff before the end of the first week of the semester break for feedback on your design. You then need to take the feedback into consideration - whether you are going to follow the suggestions or you are going with your original design. You must also update your repository with the initial design before the end of the first week of the semester break. Failure to demonstrate your initial design will result in a mark of zero for this assignment.

Note: It is OK if your implementation doesn't match your initial design 100% - things do change!

## Task Two: The Beginning (12 marks)
Implement the first part of the game allowing the player to guess the computer's secret code. The computer randomly generates the secret code at the beginning of the game, which it then lets the player guess. Remember that when generating the computer's secret code, each of the four digits must be different. Note that the player only has seven attempts to guess the secret code. The prompt for player input, results for each guess and the final outcome (i.e. whether the player has won the game or not) should be displayed appropriately to the console.

## Task Three: Easy AI (12 marks)
Modify your code so that the player can now also enter a secret code when the game begins, which the computer must guess. Remember to verify that the player has chosen a valid secret code. The player and computer each take turns guessing the other's code. The game ends when either side successfully guesses the other's code (resulting in a win for that side), or when each side has made seven incorrect guesses (resulting in a draw).

For this task, have the player play against an easy AI. When the AI makes a guess, it will simply generate a random (valid) guess.

## Task Four: Medium AI (6 marks)
Modify your code so that at the beginning of the game (before the player enters their own secret code), they will be asked to select either an easy or medium AI opponent to play against.

If the player chooses to play against an easy AI, the game should proceed in exactly the same manner as in Task Three. However, if a medium AI is selected, the AI should keep track of guesses it has already made. The AI will not make the same guess twice.

## Task Five: Hard AI (15 marks)
Modify your code so that the player can additionally choose to play against a hard AI opponent.

If the player chooses to play against an easy or medium AI, the game should proceed in exactly the same manner as in Task Three or Four. However, if a hard AI is selected, the computer should be much more intelligent when guessing, rather than just choosing at random. One possible strategy for implementing the hard AI is given as follows.

### Possible Strategy
This strategy involves keeping a list of all possible guesses, and then intelligently pruning that list based on the result of each guess made by the AI. In this strategy, the first guess by the computer will be chosen randomly. After this guess, all subsequent guesses will be carefully planned. The computer keeps a track of precisely which codes remain consistent with all the information it has received so far. The computer will only choose a guess that has a chance of being the correct one.

For example, let’s assume the computer’s first guess scored 1 bull and 1 cow. Then the only codes that still have a chance to be the correct one, are those which match up 1 bull and 1 cow with the first guess. All other codes should be eliminated. The computer will go through its list of all possible codes and test each against its first guess. If a code matches 1 bull and 1 cow with the first guess, then it will remember that the code is still a possible candidate for the secret code. If a code does not match 1 bull and 1 cow with the first guess, the code will be eliminated. After this is completed, the computer randomly chooses any of the possible candidates for the second guess.

If the computer’s second guess scored 2 bulls and 1 cow, the computer checks all the remaining candidates to see which codes match up 2 bulls and 1 cow with the second guess. Those codes that do not match are eliminated. In this manner, each guess is consistent with all the information obtained up until that point in the game.

To illustrate the process, consider the scenario shown in the following figure, in which a simpler version of the game is being played. In this demonstration, secret codes are only two digits in length, and may only contain the characters 1 through 4. The player has chosen "2 1" as their secret code, which the AI is trying to guess. Using this process of elimination, the AI is able to quickly guess the player’s code.

![alt text](bc-second.PNG "Example Output 2")

## Task Six: Reading Guesses from a File (7 marks)
Modify your code so that before the game begins, the player is asked whether they wish to enter their guesses manually, or to automatically guess based on pre-supplied guesses in a file.

If the first option is chosen, then the game should progress in the same fashion as in Task Five above. If the second option is chosen, then the following actions should be taken:

Firstly, the player should be asked to enter a filename. If the player enters an invalid filename, they should be re-prompted until they enter the name of a file that actually exists. This file should then be read and interpreted as a text file, where each line contains a separate guess. For example, a sample file input.txt may contain the following text:

![alt text](bc-third.PNG "Example Output 3")

You may assume that each line of the file contains a valid guess.

Once the file has been read, then the game should proceed as normal. However, when the player would be prompted to enter a guess, the next guess in the list of pre-supplied guesses should automatically be chosen instead. If there are no more pre-supplied guesses (for example, if the player needs to enter their fifth guess but the file only contains four guesses), then the player should be prompted as normal.

## Task Seven: Saving to a File (8 marks)
Modify your code so that, when the game ends (win, lose, or draw), the player is asked if they wish to save the results to a text file. If they do, then they’ll be prompted to enter a filename. The game should then save the following information to the given file:
* The player and computer’s secret number
* Each guess that was made, in the same order as occurred during the game, along with the result of that guess (i.e. how many bulls & cows it got)
* The identity of the winner (or a message stating that the game was a draw)

The data must be readable when opened in a standard text editor. An example results file is illustrated as follows, though exactly how your file is organized is up to you.

![alt text](bc-fourth.PNG "Example Output 4")

## Task Eight: Advanced Configuration (10 marks)
Modify your code so that two of the following values are configurable for Easy AI:
* The maximum allowed number of turns before the game ends in a draw (default is seven)
* The length of a secret code (default is four)
* The allowed characters in a secret code (default are the digits 0 - 9, but letters should be able to be included also. For example, you could say that the allowed characters for a particular game are the digits 0 - 9, and the letters A - F).

To configure these values, you may either read them in from a file, or simply have constant variables somewhere obvious in your code, which the user can edit and then re-compile.

Note: It is important that, when configuring these values, the rest of your code should not break! Everything else should continue working as normal.

Please also write the configuration you have implemented and the instructions on how to configure the values in the README file in your github repo.

