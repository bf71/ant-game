
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package antmapgenerator;

import java.awt.Point;
import java.util.Random;


/**
 *
 * @author ec263
 */
public class AntMapGenerator {
    
    int height;
    int width;
    Ant[] ants;
    Map map;
    Point[] redAnthill;
    Point[] blackAnthill;

    public AntMapGenerator(int height, int width) {
        this.height = height;
        this.width = width;
        map = new Map(height, width);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AntMapGenerator a = new AntMapGenerator(150, 150);
        
        
        a.getCell(0, 5).setRocky(true);
        
        Point p = new Point (0,5);
        if (a.rocky(p)){
            System.out.println("rocky test passed");
        }
        
        p = new Point (5,1);
//        a.getCell(5,1).setOccupied(true);
//        if (a.someAntIsAt(p)) {
//            System.out.println("some ant is at test passed");
//        }
        
        
        
        //a.getCell(0, 5).setHasFood(true);
        //a.map[0][5].setHasFood(true);
        //a.map[5][0] = 2;
        a.placeAnthills();
        a.placeFoodBlobs();
        a.placeRocks();
        //a.placeRocks(a.makeRocks());
        a.map.printMap();
        //a.makeRedAnthill();
        //a.makeAnthill();
        
    }
    
    public void generateMap() {
        placeAnthills();
        placeFoodBlobs();
        placeRocks();
        //placeRocks(makeRocks());
        map.printMap();
    }
    
    /**
     * 
     * @return the map
     */
    public Map getMap() {
        return map;
    }
    
    public void setAnts(Ant[] ants) {
        this.ants = ants;
    }
    
    public Ant[] getAnts(){
        return ants;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return Cell at (x, y)
     */
    public Cell getCell (int x, int y) {
        return map.cells[y][x];
    }
    
    //under construction
    public boolean checkPlaceable(Point p) {
        //boolean ok;
        int x = p.x;
        int y = p.y;
        int offset;
        Point[] points = new Point[7];
        
        //offset -1 if y is even
        if (y % 2 == 0) {
            offset = -1;
        }
        else
            offset = 0;
        
        points[0] = new Point(x + offset, y-1);
        points[1] = new Point(x + 1 + offset, y-1);
        points[2] = new Point(x - 1, y);
        points[3] = new Point(x, y);
        points[4] = new Point(x + 1, y);
        points[5] = new Point(x + offset, y + 1);
        points[6] = new Point(x + 1 + offset, y + 1);
        
        //check the cell and surrounding cells for stuff
        //return false if stuff is found
        //return true if cell and surrounding cells are clear
        //i.e. the element can be placed
        for (Point pt: points) {
            if(map.getCell(pt).rocky || map.getCell(pt).blackAnthill || map.getCell(pt).redAnthill) {
                return false;
            }
            if(map.getCell(pt).hasFood != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * This will probably end up in another class
     * @param p
     * @return 
     */
    public boolean rocky (Point p) {
        if (map.cells[p.y][p.x].rocky) {
            return true;
        }
        else
            return false;
    }
    
    /**
     * This will probably go in another class
     * @param p
     * @return 
     
    public boolean someAntIsAt (Point p) {
        if (map.cells[p.y][p.x].occupied) {
            return true;
        }
        else
            return false;
    }
    * /
    
    /**
     * 
     * @return an array of Points for an anthill (hexagon of side 7)
     */
    public Point[] makeAnthill() {
        Point[] anthill = new Point[127];
        
        int x = randInt(1,148);
        int y = randInt(1,148);
        int offset;
        
        while (x-6 <= 1 || x+6 >= 148 || y-6 <= 1 || y+6 >= 148) {
            x = randInt(1,148);
            y = randInt(1,148);
        }
        
        System.out.println("Centre: (" + x + ", " + y + ")");
        
        if (y % 2 == 0) {
            offset = -1;
            //anthill = makeEvenYAnthill(new Point (x, y));
        }
        else {
            offset = 0;
            //anthill = makeOddYAnthill(new Point (x, y));
        }
        
        int counter = 0;
        
        for (int i = x-3; i < x+4; i++) {
            anthill[counter] = new Point(i, y-6);
            counter++;
        }
        for (int i = x-3+offset; i < x+5+offset; i++) {
            anthill[counter] = new Point(i, y-5);
            counter++;
        }
        for (int i = x-4; i < x+5; i++) {
            anthill[counter] = new Point (i, y-4);
            counter++;
        }
        for (int i = x-4+offset; i < x+6+offset; i++) {
            anthill[counter] = new Point (i, y-3);
            counter++;
        }
        for (int i = x-5; i < x+6; i++) {
            anthill[counter] = new Point (i, y-2);
            counter++;
        }
        for (int i = x-5+offset; i < x+7+offset; i++) {
            anthill[counter] = new Point (i, y-1);
            counter++;
        }
        for (int i = x-6; i < x+7; i++) {
            anthill[counter] = new Point (i, y);
            counter++;
        }
        for (int i = x-5+offset; i < x+7+offset; i++) {
            anthill[counter] = new Point (i, y+1);
            counter++;
        }
        for (int i = x-5; i < x+6; i++) {
            anthill[counter] = new Point (i, y+2);
            counter++;
        }
        for (int i = x-4+offset; i < x+6+offset; i++) {
            anthill[counter] = new Point (i, y+3);
            counter++;
        }
        for (int i = x-4; i < x+5; i++) {
            anthill[counter] = new Point (i, y+4);
            counter++;
        }
        for (int i = x-3+offset; i < x+5+offset; i++) {
            anthill[counter] = new Point(i, y+5);
            counter++;
        }
        for (int i = x-3; i < x+4; i++) {
            anthill[counter] = new Point(i, y+6);
            counter++;
        }
        
        return anthill;
    }
    
    /**
     * places the anthills on the map
     */
    public void placeAnthills() {
        Point[] redAnthill = makeAnthill();
        Point[] blackAnthill = makeAnthill();
        boolean ok = false;        
        
        //ant hill
        Point red = redAnthill[63];
        Point black = blackAnthill[63];
        
        //black x >= red x + 14
        //black x <= red x -14
        //black y <= red y - 14
        //black y >= red y + 14
        
        /*
        while (!(black.x >= red.x + 13) || !(black.x <= red.x - 13) || !(black.y <= red.y -13) || !(black.y >= red.y+13)) {
            blackAnthill = makeAnthill();
            black= blackAnthill[63];
        }
        * 
        */
        
        /*
        for (Point p : redAnthill) {
            for (Point q : blackAnthill) {
                if (p.x == q.x && p.y == q.y) {
                    blackAnthill = makeAnthill();
                }
            }
        }
        * 
        */
        
        /*
        while (!ok) {
            if (black.x < red.x + 14) {
                blackAnthill = makeAnthill();
            }
            else if (black.x > red.x -14) {
                blackAnthill = makeAnthill();
            }
            else if (black.y > red.y - 14) {
                blackAnthill = makeAnthill();
            }
            else if (black.y < red.y + 14) {
                blackAnthill = makeAnthill();
            }
            else
                ok = true;
                break;
        }
        * 
        */
        
        //System.out.println("got here");
        
        for (Point p : redAnthill) {
           //System.out.println("got here");
            getCell(p.x, p.y).setRedAnthill(true);
        }
        
        for (Point p : redAnthill) {
            for (Point q : blackAnthill) {
                if (p.x == q.x && p.y == q.y) {
                    blackAnthill = makeAnthill();
                }
            }
        }
        
        for (Point p : blackAnthill) {
            if (!checkPlaceable(p)) {
                blackAnthill = makeAnthill();
            } 
        }
        
        for (Point p : blackAnthill) {
            getCell(p.x, p.y).setBlackAnthill(true);
        }
        
        this.redAnthill = redAnthill;
        this.blackAnthill = blackAnthill;
        
    }
    
    /**
     * UNDER CONTSTRUCTION - Currently causes stack overflow error
     * @return an array of Points for a foodblob.
     */
    public Point[] makeFoodBlob() {
        Point[] foodBlob = new Point[25];
        
        int x = randInt(1, 149);
        int y = randInt(1, 149);
        
        while (x-6 <= 0 || x+6 >= 149 || y-6 <= 0 || y+6 >= 149) {
            x = randInt(1,148);
            y = randInt(1,148);
        }
        
        int rightOrLeft = randInt(0,1);
        int offset;
        int[] leftOffset = new int[5];
        boolean ok = false;
        
        //if the foodBlob is left oriented
        if (rightOrLeft == 1) {
            leftOffset[0] = 0;
            leftOffset[1] = -1;
            leftOffset[2] = -2;
            leftOffset[3] = -3;
            leftOffset[4] = -4;
        }
        else {
            for (int n: leftOffset){
                leftOffset[n] = 0;
            }
        }
        
        if (y % 2 == 0) {
            offset = -1;
        }
        else
            offset = 0;
        
        int counter = 0;
        
        for (int i = x-3; i< x+2; i++) {
            foodBlob[counter] = new Point(i + leftOffset[0], y-2);
            counter++;
        }
        for (int i = x-2+offset; i < x+3+offset; i++) {
            foodBlob[counter] = new Point(i + leftOffset[1], y-1);
            counter++;
        }
        for (int i = x-2; i < x+3; i++) {
            foodBlob[counter] = new Point(i + leftOffset[2], y);
            counter++;
        }
        for (int i = x-1+offset; i < x+4+offset; i++) {
            foodBlob[counter] = new Point(i + leftOffset[3], y+1);
            counter++;
        }
        for (int i = x-1; i < x+4; i++) {
            foodBlob[counter] = new Point(i + leftOffset[4], y +2);
            counter++;
        }
        //System.out.println("counter: " + counter);
        /*
        for (Point p: foodBlob) {
            if (p.x < 0 || p.x > 149 || p.y < 0 || p.y >149) {
                foodBlob = makeFoodBlob();
            }
        }
        * 
        */
        
        /*
        while (!ok) {
            //System.out.println("while");
            for (Point p: foodBlob) {
                if (p.x < 0 || p.x > 149 || p.y < 0 || p.y >149) {
                    foodBlob = makeFoodBlob();
                    break;
                }
            }
            break;
        }
        * 
        */
        
        for (Point p: foodBlob) {
            //System.out.println(p);
            //if (p.x < 0 || p.x > 149 || p.y < 0 || p.y >149 ) {
                //System.out.println("x, y : " + p.x + ", " + p.y);
                //break;
            //}
            if (!checkPlaceable(p)) {
                foodBlob = makeFoodBlob();
            } 
        }
        
        return foodBlob;
    }
    
    /**
     * makes and places food blobs
     */
    public void placeFoodBlobs() {
        int counter = 11;
        
        while (counter != 0) {
            Point[] foodBlob = makeFoodBlob();
            for (Point p: foodBlob) {
                map.getCell(p).setHasFood(5);
            }
            counter--;
        }
    }
    
    /**
     * 
     * @return array of 14 rocks
     */
    public Point[] makeRocks() {
        //int counter = 0;
        Point[] rocks = new Point[14];
        
        /*
        while(counter < 14) {
            int x = randInt(2, 148);
            int y = randInt(2, 148);
            Point p = new Point(x, y);
            if (checkPlaceable(p)){
                rocks[counter] = p;
                counter++;
            }
        }
        * 
        */
        
        for (int i = 0; i < 14; i ++) {
            rocks[i] = makeRock();
        }
        
        return rocks;
    }
    
    public Point makeRock() {
        Point rock;
        
        int x = randInt(2,148);
        int y = randInt(2, 148);
        Point p = new Point(x, y);
        
        if (checkPlaceable(p)) {
            rock = p;
        }
        else {
            rock = makeRock();
        }
        
        return rock;
    }
    
    /**
     * places rocks on the map
     * @param rocks 
     */
    public void placeRocks() {
        Point[] rocks = new Point[14];
        
        for (int i = 0; i < 14; i++) {
            rocks[i] = makeRock();
        }
        
        for (Point p : rocks) {
            map.getCell(p).rocky = true;
        }
    }
    
    /**
     * 
     * @param min
     * @param max
     * @return random int between min and max
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        
        int randomNum = rand.nextInt((max - min) + 1) + min;
        
        return randomNum;
    }

}
