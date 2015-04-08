/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Point;

/**
 *
 * @author ec263
 */
public class Map {
    
    int height;
    int width;
    Cell[][] cells;
    
    public Map (int height, int width){
        this.height = height;
        this.width = width;
        cells = new Cell[height][width];
        newMap();
    }
    
    public Map (int height, int width, char[][] world) {
        this.height = height;
        this.width = width;
        cells = new Cell[height][width];
        buildWorld(world);
    }
    
    /**
     * 
     * @param p
     * @return Cell at Point p
     */
    public Cell getCell(Point p) {
        return cells[p.y][p.x];
    }
    
    /**
     * Fills the map with Cells
     * all empty except rocky Cells around the perimeter
     */
    public void newMap () {
        boolean[] redMarkers = new boolean[6];
        boolean[] blackMarkers = new boolean[6];
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height-1 || j == width-1) {
                    //cells[i][j] = new Cell(0, true, false, redMarkers, blackMarkers);       //rocky cells around the perimeter
                } else {
                    //cells[i][j] = new Cell(0, false, false, redMarkers, blackMarkers);
                }
            }
        }
    }
    
    private void buildWorld(char[][] world) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char current = world[i][j];
                switch (current) {
                    case '#':   cells[i][j] = new Cell(0, true, new boolean[6], new boolean[6]);
                                break;
                    case '5':   cells[i][j] = new Cell(5, false, new boolean[6], new boolean[6]);
                                break;
                    case '.':   cells[i][j] = new Cell(0, false, new boolean[6], new boolean[6]);
                                break;
                    case '+':   cells[i][j] = new Cell(0, false, new boolean[6], new boolean[6]);
                                cells[i][j].setRedAnthill(true);
                                break;
                    case '-':   cells[i][j] = new Cell(0, false, new boolean[6], new boolean[6]);
                                cells[i][j].setBlackAnthill(true);
                                break;
                }
            }
        }
    }
    
    /**
     * prints map
     */
    public void printMap() {
        System.out.println(width);
        System.out.println(height);
        for (int i = 0; i < height; i++) {
            String s = "";
            if (i % 2 != 0) {
                s += " ";
            }            
            for (int j = 0; j < width; j++) {
                s += " " + cells[i][j];
            }
            System.out.println(s);
        }
    }
}
