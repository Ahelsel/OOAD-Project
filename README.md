# Semester Project - CSCI 4448 Object Oriented Analysis and Design

TODO: Update READme

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

#### GameScreen
This class will represent the screen that the game is played on and what is displayed to the user (i.e if there needs to be a pause menu or ammo count, it will be displayed here)
Attributes:
- Game: game

#### Level
The Level holds information about the Tiles and the Enemies
Attributes:
- Tile[][]: tiles
- Enemy[]: enemies

#### Tile
This class represents 50x50 pixel tiles that make up the level
A tile can be walkable or not walkable; if it is not walkable, the player or enemy cannot move through it
Towers cannot be placed on non-walkable tiles
Attributes:
- Boolean: isWalkable

#### PathFinder

#### Enemy
This class represents the enemies (for now, just one enemy type)
Attributes:
- Integer: health
- Integer: speed

#### Tower

#### Projectile
If a weapon is a ranged weapon, it will have a projectile represented by this class
Attributes:
- Boolean: modifier (if the projectile has a special effect) (Stretch Goal)
- Modifier: modifierType (if the projectile has a special effect) (Stretch Goal)

## Running the Project
In order to build and test the project in a Desktop environment, you will need to use the LibGDX-provided 'desktop' module.
To do this, run the 'DesktopLauncher' class located in the 'desktop/src/org/ooad/project' directory.



## Milestones to be Completed

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
- Add a HomeBase sprite
- Add a level sprite 

## Stretch Goals/Milestones

### Audio
Audio for firing weapons, being hit by an enemy, or dying. Background music for the game. 

### Weapon Modifiers
Add modifiers to the weapons that can be applied to the projectiles. These modifiers can be things like poison, fire, or ice.

## External Libraries
- LibGDX