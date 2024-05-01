# Semester Project - CSCI 4448 Object Oriented Analysis and Design

## Team Members
- Anthony Helsel
- Jesus Carnero
- Rocky Erdenebat

## Project Description
This project is a top-down combat/shooter game where the player is tasked with defending a base from the enemies that spawn around it. The base has a health value that will decrease if enemies reach the center of it. The game is over when the health of the HomeBase or Player reaches 0.

## Project Structure
The main code for the project is located in the 'core/src' directory.

### Classes
#### Game
This class represents the Game itself and is where everything is initialized and ran

#### StartScreen
This screen is rendered when the application is first started. It has a button for Play and Quit that are clickable and either start the game or exit the application

#### PauseScreen
When inside the game, pressing esc will bring this screen up that gives you the option to leave or resume the game
(Pressing esc while in this menu will resume the game)

#### GameScreen
This Screen contains everything relevant to the game. Including towers, enemies, textures for the paths/background. 
In this class, we call the render functions and progress the game, which calls the helper render functions for each aspect of the game.


#### Level
The Level holds information about the Tiles, Enemies, and Towers. 

#### Tile
This class represents 50x50 pixel tiles that make up the level
A tile can be walkable or not walkable; if it is not walkable, the player or enemy cannot move through it
Towers cannot be placed on non-walkable tiles

#### PathFinder
The PathFinder is called when the Level is created and stores PivotPoints

#### PivotPoints
The PivotPoints are the coordinates where the enemy should turn.

#### Enemy
This class represents the enemies (for now, just one enemy type)

#### Tower
Represents the tower objects. Each keeps track of its coordinates and any enemy within its range. If there is an enemy in the range,
there will be a target enemy stored in the Tower (until it leaves).

#### Projectile (Not implemented)
If a weapon is a ranged weapon, it will have a projectile represented by this class
Attributes:

## Running the Project
In order to build and test the project in a Desktop environment, you will need to use the LibGDX-provided 'desktop' module.
To do this, run the 'DesktopLauncher' class located in the 'desktop/src/org/ooad/project' directory.



## Milestones to be Completed (from Checkpoint)

### Fundamental Mechanics
- Implement player movement (WASD or Arrow Keys)
- Implement enemy movement (gravitate towards the Player or HomeBase)
- Implement player attack (Mouse Click)
- Implement reloading for the player's weapon (R Key)
- Implement enemy attack (if in range of the Player or HomeBase)
- Implement collision detection
  - Level
  - Player
  - Enemy
  - HomeBase
  - Projectile
- Implement health system for the Player, HomeBase, and Enemy
  - Player and HomeBase health will decrease when hit by an enemy
  - Enemy health will decrease when hit by a projectile


### Visuals
- Create a basic level with a HomeBase and Player
- Add a Player sprite with movement (change Sprite based on direction)
- Add an Enemy sprite with movement (change Sprite based on direction)
- Add a Weapon sprite for the Player
- Add a Projectile sprite for the Player's Weapon
- Add a level sprite 

## External Libraries
- LibGDX