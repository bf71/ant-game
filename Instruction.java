
/**
 *
 * @author Zachary Hoad
 */

/**
 * Super class for all instructions
 * @author Zachary Hoad
 */
public class Instruction {
    
}

/**
 * Enum type for directions
 * @author Zachary Hoad
 */
enum Direction{
    HERE, AHEAD, LEFTAHEAD, RIGHTAHEAD
}

/**
 * Enum type for conditions
 * markerNum is used to hold the type of marker
 * @author Zachary Hoad
 */
enum Condition{
    FRIEND, FOE, FRIENDWITHFOOD, FOEWITHFOOD, FOOD, ROCK, MARKER, FOEMARKER, HOME, FOEHOME;
    public int markerNum;
}

/**
 * Sense instruction
 * sensedir holds the dirction to sense in
 * cond holds the condition to check for
 * st1 holds the state to go to if the condition is true
 * st2 holds the state to go to otherwise
 * @author Zachary Hoad
 */
class Sense extends Instruction{
    public Direction sensedir;
    public int st1;
    public int st2;
    public Condition cond;
    
    public Sense(Direction sensedir, int st1, int st2, Condition cond){
        this.sensedir=sensedir;
        this.st1=st1;
        this.st2=st2;
        this.cond=cond;
    }
}

/**
 * Mark instruction
 * i holds the marker to set as true
 * st holds the state to go to after
 * @author Zachary Hoad
 */
class Mark extends Instruction{
    public int i;
    public int st;
    
    public Mark(int i, int st){
        this.i=i;
        this.st=st;
    }
}

/**
 * Unmark instruction
 * i holds the marker to set as false
 * st holds the state to go to after
 * @author Zachary Hoad
 */
class Unmark extends Instruction{
    public int st;
    public int i;
    
    public Unmark(int i, int st){
        this.st=st;
        this.i=i;
    }
}

/**
 * Pickup instruction
 * st1 is the state to go to if food was picked up
 * st2 is the state to go to otherwise
 * @author Zachary Hoad
 */
class PickUp extends Instruction{
    public int st1;
    public int st2;
    
    public PickUp(int st1, int st2){
        this.st1=st1;
        this.st2=st2;
    }
}

/**
 * Drop instruction
 * st holds next state
 * @author Zachary Hoad
 */
class Drop extends Instruction{
    public int st;
    
    public Drop(int st){
        this.st=st;
    }
}

enum leftOrRight{
    LEFT, RIGHT
}

/**
 * Turn instruction
 * st holds the state to go to after
 * lr holds whether to turn left or right
 * @author Zachary Hoad
 */
class Turn extends Instruction{
    public int st;
    public leftOrRight lr;
    
    public Turn(int st, leftOrRight lr){
        this.st=st;
        this.lr=lr;
    }
}

/**
 * Move instruction
 * st1 is the state to go to if the move succeeds
 * st2 if it fails
 * @author Zachary Hoad
 */
class Move extends Instruction{
    public int st1;
    public int st2;
    
    public Move(int st1, int st2){
        this.st1=st1;
        this.st2=st2;
    }
}

/**
 * Flip instruction
 * Chooses random number between 0 and n-1 inclusive
 * st1 is the state to go to if the result is 0
 * st2 otherwise
 * @author Zachary Hoad
 */
class Flip extends Instruction{
    public int n;
    public int st1;
    public int st2;
    
    public Flip(int n, int st1, int st2){
        this.n=n;
        this.st1=st1;
        this.st2=st2;
    }
}
