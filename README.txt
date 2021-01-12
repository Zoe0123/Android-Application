(I hope you know how to read markdown)

# Team 0615 Phase 2 Submission README
It should just work when you open the project.

See the pdf for class organization as desired.

## Instructions for Playing Individual Games
### Trivia Game
- Answer trivia questions on screen by selecting one of three multiple choice button answers

### Ball Bumping Game
- Choose the angle at which to shoot your ball (by clicking on one of the numbers) and watch it go! Try to get it inside the marked GOAL

### WhackAnAlien
- Quickly click on the aliens as they appear to hit them
  - The top number beside an alien represents its health
  - The bottom number beside an alien represents its distance (how far away it is from you)
    - You lose a life if the bottom number hits zero before you kill it



## A note on the organization of our game.

Everything (except language selection) is managed within player accounts.
Specifically, users must log in before they can enter Practice Mode, Start a new Adventure, Continue their previous adventure, or view the current scoreboard. 
We also have two modes the user can play: Adventure Mode and Practice Mode.

### Adventure Mode:
- The player can play through all levels of the game in a sequential manner. During Adventure mode, statistics are tracked and accumulated (Lives, Time, and Score).
- Before starting an adventure, the user chooses their desired preferences (Color Theme and Difficulty). They then play through the Trivia Game, Ball Bumping Game, and WhackAnAlien until they win or die.
- If the player wins the adventure, they have the option of saving their score to the scoreboard.

### Practice Mode:
- In practice mode, the user can play each game individually to hone their skills for adventure mode. Note that in practice mode, the Time statistic is not tracked! This allows a more relaxed environment for players to practice.
- Once users select practice mode, they can choose their desired color scheme before selecting which game to play.
- Upon selecting a game, the game launches in easy difficulty. As they player keeps winning, difficulty continues increasing until the player defeats the hardest version.



### Regarding grading, below summarizes what our app implements:
#### Customization:
- Language
- Color scheme
- Difficulty

#### Statistics tracked through all games:
- Lives
- Score
- Time

#### New features/extensions for Phase 2:
- Scoreboard
- Turning each level into individual games
  - Each of these games can be played through the Practice mode. Selecting any of these three games takes the player through three 'levels' of the individual game.