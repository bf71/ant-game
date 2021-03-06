
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Ant World Parser
 * @author Ben Frost
 * @author Alli Allfrey
 */
public class AntWorldParser {
    
    BufferedReader r;
    String directory;
    char[][] map;
    char[][] originalMap;
    
    /**
     * Constructor for the Ant World Parser
     *  
     */
    public AntWorldParser() {   
    }
    
    /**
     * Runs each of the world tests within the program
     * @param directory the name of the file in N drive
     * @throws FileNotFoundException
     * @throws IOException
     * @return True if world is tournament approved, otherwise false
     */
    public boolean runTest(String directory) throws IOException, FileNotFoundException, IOException {
        // Read the map into a buffered reader
        r = new BufferedReader(new FileReader(directory));
        this.directory = directory;
        
        // Check the dimentions
        if (checkDimensions()) {
            // Put the map into a 2D array of characters
            buildMap();
            // Check each character
            if (checkChars()) {
                // Check each of the other conditions of a valid ant world
                return checkRocks() && checkBorder() && checkAntHills() && checkFood();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * Print out the results of the parser
     * @throws IOException
     */
    public void printParse() throws IOException {
        // Check the dimentions
        Boolean dimCheck = checkDimensions();
        System.out.println("DimCheck: " + dimCheck);
        if (dimCheck) {
            // Put the map into a 2D array of characters
            buildMap();
            // Check each character
            Boolean charCheck = checkChars();
            System.out.println("charCheck: " + charCheck);
            if (charCheck) {
                // Check each of the other conditions of a valid ant world
                System.out.println("checkRocks: " + checkRocks());
                System.out.println("checkBorder: " + checkBorder());
                System.out.println("checkAntHills: " + checkAntHills());
                System.out.println("checkFood: " + checkFood());
            }
            // Print out the world
            for (int i = 0; i < 150; i++) {
                for (int j = 0; j < 150; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println("");
                if(i%2 == 0) { System.out.print(" "); }
            }
        }
    }

    /**
     * @return True if dimensions are 150 x 150, otherwise false
     * @throws IOException
     */
    public boolean checkDimensions() throws IOException {
        boolean dimCheck;
        // check the dimension values at beginning of world file
        if (("150".equals(r.readLine())) && ("150".equals(r.readLine()))) {
            dimCheck = true;
        } 
        else { 
            return false; 
        }
        // Check length of world
        String s = "";
        int length = 0;
        s = r.readLine();
        while (s != null) {
            s = r.readLine();
            length++;
        }
        if (length == 150) {
            dimCheck = true;
        } else {
            return false;
        }
        // Check width of world
        r.close();
        r = new BufferedReader(new FileReader(directory));
        r.readLine();
        r.readLine();
        for (int i = 0; i < 150; i++) {
            String line = removeSpaces(r.readLine());
            if (line.length() != 150) {
                return false;
            }
        }
        r.close();
        return dimCheck;
    }
    
    /**
     * Read the world and put it into a 2D array of characters
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void buildMap() throws FileNotFoundException, IOException {
        String row = "";
        map = new char[150][150];
        r = new BufferedReader(new FileReader(directory));
        r.readLine();
        r.readLine();
        for (int i = 0; i < 150; i++) {
            row = removeSpaces(r.readLine());
            for (int j = 0; j < 150; j++) {
                map[i][j] = row.charAt(j);
            }
        }
        originalMap = new char[150][150];
        
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                originalMap[i][j] = map[i][j];
            }
        }
//        for (int i = 0; i < 150; i++) {
//                for (int j = 0; j < 150; j++) {
//                    System.out.print(originalMap[i][j] + " ");
//                }
//                System.out.println("");
//                if(i%2 == 0) { System.out.print(" "); }
//        }
    }
    
    /**
     * Gets a map object of the parsed map and returns it
     * @return Map object of parsed map
     */
    public Map makeMap() {
        return new Map(150,150,originalMap);
    }
    
    /**
     * Checks each character in the world to see if it is valid
     * @return True if all characters in world are valid, otherwise false
     */
    public Boolean checkChars() {
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                if(map[i][j] == ('#' | '.' | '+' | '-' | '5')) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Checks the number of rocks within the world, not including border
     * @return True if correct number of rocks, otherwise false
     */
    public Boolean checkRocks() {
        int rockCount = 0;
        for (int i = 1; i < 149; i++) {
            for (int j = 1; j < 149; j++) {
                if (map[i][j] == '#') {
                    rockCount++;
                }
            }

        }
        return (rockCount == 14);
    }

    /**
     * Checks the border (edge characters) of the world is rocks
     * @return True if border is all rocks, otherwise false
     */
    public Boolean checkBorder() {
        for (int i = 0; i < 150; i++) {
            if ((map[i][149] != '#') || (map[i][0] != '#')) {
                return false;
            }
            if ((map[149][i] != '#') || (map[0][i] != '#')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks that there are two well formed ant hills, with character + and -, with no other ants on the world
     * @return True if correct ant hills exist, otherwise false
     */
    public Boolean checkAntHills() {
        int posCount = 0;
        int negCount = 0;
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                // Look for well formed positive ant hill
                if (map[i][j] == '+') {
                    if (checkRightAntHill(i, j, '+') && posCount != 1) {
                        registerRightAntHill(i, j);
                        posCount++;
                    }
                    else if (checkLeftAntHill(i, j, '+') && posCount != 1) {
                        registerLeftAntHill(i, j);
                        posCount++;
                    }
                    else {
                        return false;
                    }
                }
                // Look for well formed negative ant hill
                else if (map[i][j] == '-') {
                    if (checkRightAntHill(i, j, '-') && negCount != 1) {
                        registerRightAntHill(i, j);
                        negCount++;
                    }
                    else if (checkLeftAntHill(i, j, '-') && negCount != 1) {
                        registerLeftAntHill(i, j);
                        negCount++;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return (negCount == 1) && (posCount == 1);
    }
    
    /**
     * Checks world for correct number of well formed food blocks
     * @return True if correct amount of well formed food is within the world, otherwise false
     */
    public boolean checkFood() {
        int foodCount = 0;
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                // Look for well formed food blocks
                if (map[i][j] == '5') {
                    if (validFoodA(i, j) && foodCount != 12) {
                        registerFoodA(i, j);
                        foodCount++;
                    }
                    else if (validFoodB(i, j) && foodCount != 12) {
                        registerFoodB(i, j);
                        foodCount++;
                    }
                    else if (validFoodC(i, j) && foodCount != 12) {
                        registerFoodC(i, j);
                        foodCount++;
                    }
                    else if (validFoodD(i, j) && foodCount != 12) {
                        registerFoodD(i, j);
                        foodCount++;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return foodCount == 11;
    }
    
    // Change the character of an food block line
    private void registerFoodLine(int row, int column) {
        for (int i = 0; i < 5; i++) {
            map[row][i + column] = 'F';
        }
    }
    
    // Change the character of a food block's first line
    private void registerFirstFoodLine(int row, int column) {
        map[row][column] = 'F';
        for (int i = 1; i < 5; i++) {
            map[row][i + column] = 'F';
        }
    }
    
    // Return true if food line is valid part of food block, otherwise false
    private boolean validFoodLine(int row, int column) {
        for (int i = 0; i < 5; i++) {
            if (map[row][i + column] != '5') {
                return false;
            }
        }
        return true;
    }
    
    // Return true if first food line is valid part of a food block, otherwise false
    private boolean validFirstFoodLine(int row, int column) {
        for (int i = 1; i < 5; i++) {
            if (map[row][i + column] != '5') {
                return false;
            }
        }
        return true;
    }
    
    // Return true if food block is of type A, otherwise false
    private boolean validFoodA(int row, int column) {
        if(!validFirstFoodLine(row, column)) { return false; }
        column--;
        row++;
        if(!validFoodLine(row, column)) { return false; }
        row++;
        if(!validFoodLine(row, column)) { return false; }
        row++;
        column--;
        if(!validFoodLine(row, column)) { return false; }
        row++;
        if(!validFoodLine(row, column)) { return false; }
        return true;
    }
    
    // Register a food block of type A as seen
    private void registerFoodA(int row, int column) {
        registerFirstFoodLine(row, column);
        column--;
        row++;
        registerFoodLine(row, column);
        row++;
        registerFoodLine(row, column);
        column--;
        row++;
        registerFoodLine(row, column);
        row++;
        registerFoodLine(row, column);
    }
    
    // Return true if food block is of type B, otherwise false
    private boolean validFoodB(int row, int column) {
        if(!validFirstFoodLine(row, column)) { return false; }
        row++;
        if(!validFoodLine(row, column)) { return false; }
        column++;
        row++;
        if(!validFoodLine(row, column)) { return false; }
        row++;
        if(!validFoodLine(row, column)) { return false; }
        column++;
        row++;
        if(!validFoodLine(row, column)) { return false; }
        return true;
    }
    
    // Register a food block of type B as seen
    private void registerFoodB(int row, int column) {
        registerFirstFoodLine(row, column);
        row++;
        registerFoodLine(row, column);
        column++;
        row++;
        registerFoodLine(row, column);
        row++;
        registerFoodLine(row, column);
        column++;
        row++;
        registerFoodLine(row, column);
    }
    
    // Return true if food block is of type C, otherwise false
    private boolean validFoodC(int row, int column) {
        if(!validFirstFoodLine(row, column)) { return false; }
        column++;
        row++;
        if(!validFoodLine(row, column)) { return false; }
        row++;
        if(!validFoodLine(row, column)) { return false; }
        column++;
        row++;
        if(!validFoodLine(row, column)) { return false; }
        row++;
        if(!validFoodLine(row, column)) { return false; }
        return true;
    }
    
    // Register a food block of type C as seen
    private void registerFoodC(int row, int column) {
        registerFirstFoodLine(row, column);
        column++;
        row++;
        registerFoodLine(row, column);
        row++;
        registerFoodLine(row, column);
        column++;
        row++;
        registerFoodLine(row, column);
        row++;
        registerFoodLine(row, column);
    }
    
    // Return true if food block is of type D, otherwise false
    private boolean validFoodD(int row, int column) {
        if(!validFirstFoodLine(row, column)) { return false; }
        row++;
        if(!validFoodLine(row, column)) { return false; }
        column--;
        row++;
        if(!validFoodLine(row, column)) { return false; }
        row++;
        if(!validFoodLine(row, column)) { return false; }
        column--;
        row++;
        if(!validFoodLine(row, column)) { return false; }
        return true;
    }
    
    // Register a food block of type D as seen
    private void registerFoodD(int row, int column) {
        registerFirstFoodLine(row, column);
        row++;
        registerFoodLine(row, column);
        column--;
        row++;
        registerFoodLine(row, column);
        row++;
        registerFoodLine(row, column);
        column--;
        row++;
        registerFoodLine(row, column);
    }

    // Remove the spaces from a world line string
    private String removeSpaces(String str) {
        String s = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                s += c;
            }
        }
        return s;
    }
    
    // Return true if there is a valid right sided ant hill, otherwise false
    private boolean checkRightAntHill(int row, int column, char team) {
        boolean trueHill = true;
        for (int i = 1; i < 7; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        for (int i = 0; i < 8; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        column--;
        row++;
        for (int i = 0; i < 9; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        for (int i = 0; i < 10; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        column--;
        row++;
        for (int i = 0; i < 11; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        for (int i = 0; i < 12; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        column--;
        row++;
        for (int i = 0; i < 13; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        column++;
        row++;
        for (int i = 0; i < 12; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        for (int i = 0; i < 11; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        column++;
        row++;
        for (int i = 0; i < 10; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        for (int i = 0; i < 9; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        column++;
        row++;
        for (int i = 0; i < 8; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        for (int i = 0; i < 7; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        return trueHill;
    }

    // Register a right sided ant hill as seen
    private void registerRightAntHill(int row, int column) {
        map[row][column] = 'P';
        for (int i = 1; i < 7; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        for (int i = 0; i < 8; i++) {
            map[row][i + column] = 'P';
        }
        column--;
        row++;
        for (int i = 0; i < 9; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        for (int i = 0; i < 10; i++) {
            map[row][i + column] = 'P';
        }
        column--;
        row++;
        for (int i = 0; i < 11; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        for (int i = 0; i < 12; i++) {
            map[row][i + column] = 'P';
        }
        column--;
        row++;
        for (int i = 0; i < 13; i++) {
            map[row][i + column] = 'P';
        }
        column++;
        row++;
        for (int i = 0; i < 12; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        for (int i = 0; i < 11; i++) {
            map[row][i + column] = 'P';
        }
        column++;
        row++;
        for (int i = 0; i < 10; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        for (int i = 0; i < 9; i++) {
            map[row][i + column] = 'P';
        }
        column++;
        row++;
        for (int i = 0; i < 8; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        for (int i = 0; i < 7; i++) {
            map[row][i + column] = 'P';
        }
    }
    
    // Return true if there is a valid left sided ant hill, otherwise false
    private boolean checkLeftAntHill(int row, int column, char team) {
        boolean trueHill = true;
        for (int i = 1; i < 7; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        column--;
        for (int i = 0; i < 8; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        for (int i = 0; i < 9; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        column--;
        row++;
        for (int i = 0; i < 10; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        for (int i = 0; i < 11; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        column--;
        row++;
        for (int i = 0; i < 12; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        for (int i = 0; i < 13; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        for (int i = 0; i < 12; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        column++;
        row++;
        for (int i = 0; i < 11; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        for (int i = 0; i < 10; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        column++;
        row++;
        for (int i = 0; i < 9; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        row++;
        for (int i = 0; i < 8; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        column++;
        row++;
        for (int i = 0; i < 7; i++) {
            if (map[row][i + column] != team) {
                return false;
            }
        }
        return trueHill;
    }
    
    // Register a left sided ant hill as seen
    private void registerLeftAntHill(int row, int column) {
        map[row][column] = 'P';
        for (int i = 1; i < 7; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        column--;
        for (int i = 0; i < 8; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        for (int i = 0; i < 9; i++) {
            map[row][i + column] = 'P';
        }
        column--;
        row++;
        for (int i = 0; i < 10; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        for (int i = 0; i < 11; i++) {
            map[row][i + column] = 'P';
        }
        column--;
        row++;
        for (int i = 0; i < 12; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        for (int i = 0; i < 13; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        for (int i = 0; i < 12; i++) {
            map[row][i + column] = 'P';
        }
        column++;
        row++;
        for (int i = 0; i < 11; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        for (int i = 0; i < 10; i++) {
            map[row][i + column] = 'P';
        }
        column++;
        row++;
        for (int i = 0; i < 9; i++) {
            map[row][i + column] = 'P';
        }
        row++;
        for (int i = 0; i < 8; i++) {
            map[row][i + column] = 'P';
        }
        column++;
        row++;
        for (int i = 0; i < 7; i++) {
            map[row][i + column] = 'P';
        }
    }
}