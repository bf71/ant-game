
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Zachary Hoad
 */
public class Game {
    
    private static Map map;
    private Map cleanMap;
    private ArrayList<Ant> ants;
    private AntBrain redBrain;
    private AntBrain blackBrain;
    private Statistics statistics;
    private AntWorldPanel display;
    private JFrame frame;
    
    
    /**
     * Constructor, initialises Statistics and Ants
     * @param map The map the game should take place on
     * @param redBrain The brain for the red ants
     * @param blackBrain The brain for the black ants
     */
    public Game(Map map, AntBrain redBrain, AntBrain blackBrain){
        this.map=map;
        cleanMap=map;
        frame = new JFrame("Map");
        this.redBrain=redBrain;
        this.blackBrain=blackBrain;
        statistics=new Statistics();
        initialiseRandomint(new BigInteger(""+System.currentTimeMillis()));
        
        statistics.mapFood=1375; //Tournament legal worlds must have this much food
        statistics.blackAnts=127; //And this many ants
        statistics.redAnts=127;
        statistics.redName=redBrain.name;
        statistics.blackName=blackBrain.name;
        
        ants=new ArrayList();
        
        for (Ant a: ants) {

            for (int i = 0; i < 150; i++) {
                for (int j = 0; j < 150; j++) {
                    Point p = new Point (i, j);
                    if (anthillAt(p, Colour.RED) && !someAntIsAt(p) && a.getColor().equals(Colour.RED)) {
                        setAntAt(p, a);
                    }
                    else if (anthillAt(p, Colour.BLACK) && someAntIsAt(p) && a.getColor().equals(Colour.RED)) {
                        setAntAt(p, a);
                    }
                }
            }

        }
        
        int h=0;
        do{
            int w=0;
            do{
                Point pos=new Point(h, w);
                if (anthillAt(pos, Colour.RED)){
                    try {
                        Ant a = new Ant(Colour.RED, 0, 0, 0, false, pos);
                        ants.add(a);
                    } catch (Exception ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);//Should never happen
                    }
                }
                if (anthillAt(pos, Colour.BLACK)){
                    try {
                        Ant a=new Ant(Colour.BLACK, 0, 0, 0, false, pos);
                        ants.add(a);
                    } catch (Exception ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }//Should never happen
                }
                w++;
            } while(w<map.width&&ants.size()<254);
            h++;
        } while(h<map.height&&ants.size()<254);
        
        if (ants.size()!=254){
            System.out.println("URGENT PROBLEM! Look in Game constructor");
        }
        
        
    }
    
    /**
     * Alternate constructor, doesn't initialise anything, for use with newMap method
     * Likely obsolete
     * @param redBrain
     * @param blackBrain 
     */
    public Game(AntBrain redBrain, AntBrain blackBrain){
        this.redBrain=redBrain;
        this.blackBrain=blackBrain;
    }
    
    public AntBrain getRedBrain() {
        return redBrain;
    }
    
    public AntBrain getBlackBrain() {
        return blackBrain;
    }
    
    /**
     * Returns new Game created using standard constructor from existing brains and supplied map
     * Likely obsolete
     * @param map
     * @return New game object
     */
    public Game newMap(Map map){
        return new Game(map, redBrain, blackBrain);
    }
    
    /**
     * Returns new game object with the ant brains controlling the other colour
     * Likely obsolete
     * @return new game
     */
    public Game swapSides(){
        return new Game(cleanMap, blackBrain, redBrain);
    }
    
    /**
     * Returns the statistics from the game
     * @return the statistics
     */
    public Statistics getStatistics(){
        return statistics;
    }
    
    /**
     * Returns the map the game is taking place on
     * @return The map
     */
    public Map getMap(){
        return map;
    }
    
    public Game getGame() {
        return this;
    }
    
    /**
     * Returns an array list of the ants
     * @return the ants
     */
    public ArrayList<Ant> getAnts(){
        return ants;
    }
    
    /**
     * Runs step function for every ant, each ant advances one state (unless it is resting)
     */
    public void nextTurn(){
        for (int i=0; i<254; i++){
            step(i);
        }
    }
    
    public void disposeFrame() {
        frame.dispose();
    }   
    
    public void runGame() {
//        frame = new JFrame("Wurld Star");
//        Container contentPane = frame.getContentPane();
//        contentPane.setLayout(new BorderLayout());
//        //JPanel p = new JPanel();
//        JLabel b = new JLabel("label");
//        //p.add(b);
//        contentPane.add(b, BorderLayout.NORTH);
//        contentPane.setVisible(true);
//        frame.setSize(new Dimension(900,650));
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
        //contentPane.add(p);
        
        //wd = new WorldDisplay();
        //display = new AntWorldPanel(map);
        //display.initDisplay(map);
        
        //display = new AntWorldPanel(map);
        //display.initDisplay(map);
        //labelFrame();
        //displayMap(map);
//        new Thread() {
//          public void run() {
//            displayMap(map);
//          }
//        }.start();
        
        for(int i = 0; i < 150; i++) {
            nextTurn();

            //display.repaint();
            System.out.println("turn " + i);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        disposeFrame();
        
        //display.repaint();

        //return statistics;
    }
    
    public void displayMap(Map m) {
        Container c = frame.getContentPane();
        display = new AntWorldPanel(map, ants);
        display.setPreferredSize(new Dimension(1965, 1965));
        display.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JScrollPane scroll = new JScrollPane();
        scroll.setPreferredSize(new Dimension(1920,1080));
        scroll.setViewportView(display);
        scroll.getVerticalScrollBar().setUnitIncrement(50);
        
        c.add(scroll);
        
        frame.setSize(1920,1080);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void labelFrame() {
        frame = new JFrame("frames");
        Container contentPane = frame.getContentPane();
        JLabel l = new JLabel("this is a lable");
        contentPane.add(l);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Advances the ant by one state
     * Mostly supplied as pseudocode
     * @param id the ant to advance
     */
    private void step(int id){
        if (antIsAlive(id)){
            Point point = findAnt(id);
            Ant a = ants.get(id);
            if (resting(a)>0){
                setResting(a, resting(a)-1);
            } else {
                Instruction i = getInstruction(colour(a), state(a));
                if(i instanceof Sense){
                    Sense s =(Sense)i;
                    Point pp=sensedCell(point, direction(a), s.sensedir);
                    if (cellMatches(pp, s.cond, colour(a))){
                        setState(a, s.st1);
                    } else {
                        setState(a, s.st2);
                    }
                }
                if (i instanceof Mark){
                    Mark m = (Mark)i;
                    setMarkerAt(point, colour(a), m.i);
                    setState(a, m.st);
                }
                if (i instanceof Unmark){
                    Unmark u =(Unmark)i;
                    clearMarkerAt(point, colour(a), u.i);
                    setState(a, u.st);
                }
                if (i instanceof PickUp){
                    PickUp p = (PickUp)i;
                    if (hasFood(a)||foodAt(point)==0){
                        setState(a, p.st2);
                    } else {
                        setFoodAt(point, foodAt(point)-1);
                        setHasFood(a, true);
                        setState(a, p.st1);
                        statistics.mapFood--;
                        if (anthillAt(point, Colour.RED)){
                            statistics.redHillFood--;
                        }
                        if (anthillAt(point, Colour.BLACK)){
                            statistics.blackHillFood--;
                        }
                    }
                }
                if (i instanceof Drop){
                    Drop d = (Drop)i;
                    if (hasFood(a)){
                        setFoodAt(point, foodAt(point)+1);
                        setHasFood(a, false);
                        statistics.mapFood++;
                        if (anthillAt(point, Colour.RED)){
                            statistics.redHillFood++;
                        }
                        if (anthillAt(point, Colour.BLACK)){
                            statistics.blackHillFood++;
                        }
                    }
                    setState(a, d.st);
                }
                if (i instanceof Turn){
                    Turn t = (Turn)i;
                    setDirection(a, turn(t.lr, direction(a)));
                    setState(a, t.st);
                }
                if (i instanceof Move){
                    Move m = (Move)i;
                    Point newp = adjacentCell(point, direction(a));
                    if (rocky(newp)||someAntIsAt(newp)){
                        setState(a, m.st2);
                    } else {
                        clearAntAt(point);
                        setAntAt(newp, a);
                        setState(a, m.st1);
                        setResting(a, 14);
                        checkForSurroundedAnts(newp);
                    }
                }
                if (i instanceof Flip){
                    Flip f =(Flip)i;
                    if (randomint(f.n)==0){
                        setState(a, f.st1);
                    } else {
                        setState(a, f.st2);
                    }
                }
            }
        }
    }
    
    /**
     * Takes a direction and gives the new direction resulting from turning left of right
     * Mostly supplied as pseudocode
     * @param lr Turn left or right
     * @param dir Initial direction
     * @return New direction
     */
    private int turn(leftOrRight lr, int dir){
        if (lr==leftOrRight.LEFT){
            return (dir+5)%6;
        }
        if (lr==leftOrRight.RIGHT){
            return(dir+1)%6;
        }
        return -1; //Shouldn't happen
    }
    
    /**
     * Returns the cell in the position relative to the point and directions supplied
     * Mostly supplied as pseudocode
     * @param p The point
     * @param d The direction you are facing
     * @param sd The direction of the new cell
     * @return The point representing the position of the new cell
     */
    private Point sensedCell(Point p, int d, Direction sd){
        if (sd==Direction.HERE){
            return p;
        }
        if (sd==Direction.AHEAD){
            return adjacentCell(p, d);
        }
        if (sd==Direction.LEFTAHEAD){
            return adjacentCell(p, turn(leftOrRight.LEFT, d));
        }
        if (sd==Direction.RIGHTAHEAD){
            return adjacentCell(p, turn(leftOrRight.RIGHT, d));
        }
        return null;
    }
    
    /**
     * Returns the point representing the cell in the direction specified relative to the cell specified
     * Mostly supplied as pseudocode
     * @param p The point you are at
     * @param d The direction the adjacent cell is in
     * @return Point representing the position of that cell
     */
    private Point adjacentCell(Point p, int d){
        switch (d){
          case 0: return new Point(p.x+1, p.y);
          case 1: if (even(p.y)){ return new Point(p.x, p.y+1);} else {return new Point(p.x+1, p.y+1);}
          case 2: if (even(p.y)) {return new Point(p.x-1, p.y+1); } else { return new Point(p.x, p.y+1);}
          case 3: return new Point(p.x-1, p.y);
          case 4: if (even(p.y)) { return new Point(p.x-1, p.y-1); } else { return new Point(p.x, p.y-1); }
          case 5: if (even(p.y)) { return new Point(p.x, p.y-1); } else { return new Point(p.x+1, p.y-1); }
        }
        return null;
    }
    
    /**
     * Checks if the supplied integer is even
     * @param i The integer to check
     * @return True if it is even, false if it isn't
     */
    private boolean even(int i){
        return i%2==0;
    }
    
    /**
     * Enum type representing the two colours
     */
    public enum Colour{
        RED, BLACK
    }
    
    /**
     * Returns the colour that is not the supplied colour
     * Mostly supplied as pseudocode
     * @param c The supplied colour
     * @return The colour that is not that
     */
    private Colour otherColour(Colour c){
        if (c==Colour.BLACK){
            return Colour.RED;
        }
        return Colour.BLACK;
    }
    
    /**
     * Checks if the cell at the position represented by the given point is rocky or not
     * Mostly supplied as pseudocode
     * @param p The point to check
     * @return True if it is rocky, false if it isn't
     */
    private boolean rocky(Point p){
        return map.getCell(p).isRocky();
    }
    
    /**
     * Returns the amount of food at the cell represented by the supplied point
     * Mostly supplied as pseudocode
     * @param p The point to check
     * @return The amount of food
     */
    private int foodAt(Point p){
        return map.getCell(p).hasFood();
    }
    
    /**
     * Sets the amount of food at the cell represented by the supplied point to the supplied value
     * Mostly supplied as pseudocode
     * @param p The point
     * @param f The value
     */
    private void setFoodAt(Point p, int f){
        map.getCell(p).setHasFood(f);
    }
    
    /**
     * Checks if there is an anthill of the specified colour at the cell at the supplied point
     * Mostly supplied as pseudocode
     * @param p The point to check
     * @param c The colour
     * @return True if there is an anthill of that colour at that point, false otherwise
     */
    private boolean anthillAt(Point p, Colour c){
        //System.out.println(p);
        if(p.x==11&&p.y==71){
            map.printMap();
            System.out.println(map.getCell(p));
        }
        return map.getCell(p).getAnthill(c);
    }

    /**
     * Sets the chemical marker of the specified type and colour in the specified cell to be true
     * Mostly supplied as pseudocode
     * @param p The point the cell is at
     * @param c The colour of the marker
     * @param i The type of the marker
     */
    private void setMarkerAt(Point p, Colour c, int i){
        map.getCell(p).setMarker(i, true, c);
    }
    
    /**
     * Sets the chemical marker of the specified type and colour in the specified cell to be false
     * Mostly supplied as pseudocode
     * @param p The point the cell is at
     * @param c The colour of the marker
     * @param i The type of the marker
     */
    private void clearMarkerAt (Point p, Colour c, int i){
        map.getCell(p).setMarker(i, false, c);
    }
    
    /**
     * Checks the chemical marker of the specified type and colour in the specified cell
     * Mostly supplied as pseudocode
     * @param p The point the cell is at
     * @param c The colour of the marker
     * @param i The type of the marker
     * @return True if that marker is there, false otherwise
     */
    private boolean checkMarkerAt(Point p, Colour c, int i){
        return map.getCell(p).getMarker(i, c);
    }
    
    /**
     * Checks if any chemical marker of the specified colour is in the specified cell
     * Mostly supplied as pseudocode
     * @param p The point the cell is at
     * @param c The colour of the marker
     * @return True if any marker of that colour is there, false otherwise
     */
    private boolean checkAnyMarker(Point p, Colour c){
        boolean[] markers=map.getCell(p).getMarkers(c);
        for (int i=0; i<markers.length; i++){
            if (markers[i]){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the cell at the supplied point matches the supplied condition
     * Mostly supplied as pseudocode
     * @param p The point to check
     * @param cond The condition to check
     * @param c The colour needed for some conditions
     * @return True if the cell matches that condition, false otherwise
     */
    private boolean cellMatches(Point p, Condition cond, Colour c){
        if(cond==Condition.ROCK){
            return rocky(p);
        }
        if(cond==Condition.FRIEND){
            return ((someAntIsAt(p))&&(colour(antAt(p))==c));
        }
        if(cond==Condition.FOE){
            return ((someAntIsAt(p))&&(colour(antAt(p))!=c));
        }
        if(cond==Condition.FRIENDWITHFOOD){
            return ((someAntIsAt(p))&&(colour(antAt(p))==c)&&(hasFood(antAt(p))));
        }
        if(cond==Condition.FOEWITHFOOD){
            return ((someAntIsAt(p))&&(colour(antAt(p))!=c)&&(hasFood(antAt(p))));
        }
        if(cond==Condition.FOOD){
            return foodAt(p)>0;
        }
        if(cond==Condition.MARKER){
            return checkMarkerAt(p, c, cond.markerNum);
        }
        if(cond==Condition.FOEMARKER){
            return checkAnyMarker(p, otherColour(c));
        }
        if(cond==Condition.HOME){
            return anthillAt(p, c);
        }
        if(cond==Condition.FOEHOME){
            return anthillAt(p, otherColour(c));
        }
        return false;
    }
    
    /**
     * Returns the instruction for the ant brain of the supplied colour at the supplied state
     * Mostly supplied as pseudocode
     * @param c The colour
     * @param s The state
     * @return The instruction
     */
    private Instruction getInstruction(Colour c, int s){
        if (c==Colour.RED){
            return redBrain.instructions.get(s);
        }
        return blackBrain.instructions.get(s);
    }
    
    /**
     * Returns the colour of the ant
     * Mostly supplied as pseudocode
     * @param a The ant
     * @return Its colour
     */
    private Colour colour(Ant a){
        return a.getColor();
    }
    
    /**
     * Returns the number of ants of the specified colour are in the cells around the supplied cell
     * Mostly supplied as pseudocode
     * @param p The point the cell is at
     * @param c The colour
     * @return The number of ants
     */
    private int adjacentAnts (Point p, Colour c){
        int n=0;
        for (int d=0; d<=5; d++){
            Point cell=adjacentCell(p, d);
            if (someAntIsAt(cell)&&colour(antAt(cell))==c){
                n++;
            }
        }
        return n;
    }
    
    /**
     * Checks if the ant at the supplied point is surrounded by ants of the other colour
     * If it is it kills that ant and drops the right amount of food
     * Mostly supplied as pseudocode
     * @param p The point to check
     */
    private void checkForSurroundedAntAt(Point p){
        if (someAntIsAt(p)){
            Ant a = antAt(p);
            if (adjacentAnts(p, otherColour(colour(a)))>=5){
                killAntAt(p);
            }
        }
    }
    
    /**
     * Runs checkForSurroundedAntAt on the supplied cell and all directly adjacent cells
     * Mostly supplied as pseudocode
     * @param p The cell
     */
    private void checkForSurroundedAnts(Point p){
        checkForSurroundedAntAt(p);
        for (int d=0; d<=5; d++){
            checkForSurroundedAntAt(adjacentCell(p, d));
        }
    }
    
    private ArrayList<BigInteger> randomintS;
    
    /**
     * Initialises the random number generator, called by the constructor
     * @param seed The seed to initialise the rng with
     */
    private void initialiseRandomint(BigInteger seed){
        randomintS=new ArrayList();
        for (int i=0; i<4; i++){
            randomintS.add(BigInteger.ZERO);
        }
        randomintS.set(3, seed);
        for (int i=2; i>=0; i--){
            BigInteger news=randomintS.get(i+1);
            news=news.multiply(new BigInteger("22695477"));
            news=news.add(BigInteger.ONE);
            randomintS.set(i, news);
        }
    }
    
    /**
     * Returns a random number between 0 and n-1 inclusive
     * @param n n
     * @return Random number
     */
    private int randomint(int n){
        if (n<1){
            return 0;
        }
        randomintS.remove(3);
        BigInteger news=randomintS.get(0);
        news=news.multiply(new BigInteger("22695477"));
        news=news.add(BigInteger.ONE);
        randomintS.add(0, news);
        news=news.divide(new BigInteger("65536"));
        news=news.mod(new BigInteger("16384"));
        news=news.mod(new BigInteger(""+n));
        return news.intValue();
    }
    
    /**
     * Gets the state that ant is in
     * Mostly supplied as pseudocode
     * @param a The ant
     * @return Its state
     */
    private int state(Ant a){
        return a.getState();
    }
    
    /**
     * Gets the ant's resting timer
     * Mostly supplied as pseudocode
     * @param a The ant
     * @return Its resting timer
     */
    private int resting(Ant a){
        return a.getResting();
    }
    
    /**
     * Gets the direction the ant is facing
     * Mostly supplied as pseudocode
     * @param a The ant
     * @return Its direction
     */
    private int direction(Ant a){
        return a.getDirection();
    }
    
    /**
     * Checks if the ant has any food
     * Mostly supplied as pseudocode
     * @param a The ant
     * @return True if it has food, false if it doesn't
     */
    private boolean hasFood(Ant a){
        return a.has_food();
    }
    
    /**
     * Sets the state of the ant to the value supplied
     * Mostly supplied as pseudocode
     * @param a The ant
     * @param s The state
     */
    private void setState(Ant a, int s){
        try {
            a.setState(s);
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }//Should always work
    }
    
    /**
     * Sets the resting timer of the ant to the supplied value
     * Mostly supplied as pseudocode
     * @param a The ant
     * @param s The value
     */
    private void setResting(Ant a, int s){
        a.setResting(s);
    }
    
    /**
     * Sets the direction of the ant to the supplied value
     * Mostly supplied as pseudocode
     * @param a The ant
     * @param d The value
     */
    private void setDirection(Ant a, int d){
        try {
            a.setDirection(d);
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }//Should always work
    }
    
    /**
     * Sets if the ant has food or not, also updates statistics
     * Mostly supplied as pseudocode
     * @param a The ant
     * @param b True if it should have food, false if it shouldn't
     */
    private void setHasFood(Ant a, boolean b){
        a.setHas_food(b);
        if (a.getColor()==Colour.BLACK){
            if (b){
                statistics.blackCarryFood++;
            } else {
                statistics.blackCarryFood--;
            }
        } else {
            if (b){
                statistics.redCarryFood++;
            } else {
                statistics.redCarryFood--;
            }
        }
    }
    
    /**
     * Checks if there is any ant at the specified point
     * Mostly supplied as pseudocode
     * @param p The point
     * @return True if there is an ant there, false otherwise
     */
    private boolean someAntIsAt(Point p){
        if (p.x<0||p.x>=map.height||p.y<0||p.y>=map.width){
            return false;
        }
        for (Ant a:ants){
            if (a.getPosition()==p){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns the ant at the specified position
     * Mostly supplied as pseudocode
     * @param p The position
     * @return The ant there
     */
    private Ant antAt(Point p){
        for (Ant a:ants){
            if (a.getPosition()==p){
                return a;
            }
        }
        return null;
    }
    
    /**
     * Sets the position of the ant to the supplied point
     * Mostly supplied as pseudocode
     * @param p The point
     * @param a The ant
     */
    private void setAntAt(Point p, Ant a){
        a.setPosition(p);
    }
    
    /**
     * Sets the ant at the supplied position to have a null position
     * Mostly supplied as pseudocode
     * @param p The positions
     */
    private void clearAntAt(Point p){
        antAt(p).setPosition(null);
    }
    
    /**
     * Checks if the ant with the supplied id is alive
     * Dead ants have their position as -1, -1
     * Mostly supplied as pseudocode
     * @param id The id of the ant
     * @return True if it is alive, false if it isn't
     */
    private boolean antIsAlive(int id){
        return(!(ants.get(id).getPosition().equals(new Point(-1, -1))));
    }
    
    /**
     * Finds the position of the ant with the supplied id
     * Mostly supplied as pseudocode
     * @param id The id of the ant
     * @return Its position
     */
    private Point findAnt(int id){
        return ants.get(id).getPosition();
    }
    
    /**
     * Kills the ant at the specified point
     * Dead ants have position -1, -1
     * Also updates statistics
     * Mostly supplied as pseudocode
     * @param p The point
     */
    private void killAntAt(Point p){
        Ant a=antAt(p);
        if(a.has_food()){
            setHasFood(a, false);
            setFoodAt(p, foodAt(p)+1);
            statistics.mapFood++;
            if (anthillAt(p, Colour.BLACK)){
                statistics.blackHillFood++;
            } else {
                statistics.redHillFood++;
            }
        }
        setFoodAt(p, (foodAt(p)+3));
        statistics.mapFood+=3;
        if (anthillAt(p, Colour.BLACK)){
            statistics.blackHillFood+=3;
        } else {
            statistics.redHillFood+=3;
        }
        a.setPosition(new Point(-1, -1));
        if(colour(antAt(p))==Colour.BLACK){
            statistics.blackAnts--;
            statistics.redKills++;
        } else {
            statistics.redAnts--;
            statistics.blackKills++;
        }
    }
    
    
    
    
    
}
