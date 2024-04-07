# Semester Project - CSCI 4448 Object Oriented Analysis and Design


## Team Members
Anthony Helsel
Jesus Carnero
Rocky Erdenebat


## Project Description
TODO

## Realistic Goals / TODOs

### Fundamental Mechanics

Add a level / scene / gameplay environment:
    - 

Add a Main character that the user controls:
    -

Add enemy character(s) that the user can interact/battle with:
    -

Add a weapon system that the user can use to attack enemies:
    -

### Visuals / Animation
TODO for README: Sprite sheet, animations, character design, level design, objects, weapon design


## Stretch Goals

### Audio
TODO: 


## Project Structure
The main code for the project is located in the 'core/src' directory.

### Classes

#### Game
This class represents the Game itself and is where everything is initialized and ran

#### Level
This class represents the playable area of the game
Attributes:
- Integer: width
- Integer: height

#### GameScreen
This class will represent the screen that the game is played on and what is displayed to the user (i.e if there needs to be a pause menu or ammo count, it will be displayed here)
Attributes:
- Game: game

#### Player
This class will represent the player character
Attributes:
- Integer: health
- Integer: damage
- Integer: speed
- Weapon: weapon
- HomeBase: homeBase
- Boolean: isDead

#### Enemy
This class represents the enemies (for now, just one enemy type)
Attributes:
- Integer: health
- Integer: damage
- Integer: speed

#### Weapon
This class represents the weapons for the player
Attributes: 
- Boolean: isRanged
- Integer: damage
- Integer: range (if not ranged)
- Integer: ammoCapacity (if ranged)
- Integer: ammoCount (if ranged)
- Integer: reloadTime (if ranged)

#### HomeBase
This class represents the area the player must protect
Attributes:
- Integer: health
- Double: x
- Double: y
- Integer: radius
- Boolean: isDestroyed
- Boolean: isShielded (Stretch Goal)

#### Projectile (Stretch Goal)
If a weapon is a ranged weapon, it will have a projectile represented by this class
Attributes:
- Boolean: modifier (if the projectile has a special effect) (Stretch Goal)
- Modifier: modifierType (if the projectile has a special effect) (Stretch Goal)


## Running the Project
In order to build and test the project in a Desktop environment, you will need to use the LibGDX-provided 'desktop' module.
To do this, run the 'DesktopLauncher' class located in the 'desktop/src/org/ooad/project' directory.

