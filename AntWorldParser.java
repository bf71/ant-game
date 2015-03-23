import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Ben Frost
 * @author Alli Allfrey
 */
public class AntWorldParser {

    BufferedReader r;
    String FileName;
    char[][] map;
         
    public AntWorldParser(String FileName) throws FileNotFoundException, IOException {
    
        r = new BufferedReader(new FileReader("N:\\" + FileName + ".world"));
        this.FileName = FileName;
     
        //System.out.println(r.readLine());
        
        Boolean dimCheck = checkDimensions();
        System.out.println("DimCheck: " + dimCheck);
        if(dimCheck){
            buildMap();
            System.out.println("checkRocks: " + checkRocks());
            System.out.println("checkBorder: " + checkBorder());
            System.out.println("AntHills: " +checkAntHills());
            for(int i = 0; i < 150; i++) {
                for(int j = 0; j <150; j++) {
                    System.out.print(map[i][j] + " ");
               
                }      System.out.println("");
            }
        }
        
        
        
        
        String temp = r.readLine();
        //System.out.println(temp.length());
    
        //read into array of chars
    }
    public Boolean checkDimensions() throws IOException{
        
        Boolean dimCheck = false;
        
        if (("150".equals(r.readLine())) &&("150".equals(r.readLine()))) {
            dimCheck = true;
        } else {return false;}
        
        
        // Length Check
        String s = "";
        int length = 0;
        s = r.readLine(); 
        while( s != null) {
        
            //System.out.println(s);
        
            s = r.readLine();
        
            length++;
        
        }
        
        System.out.println("World Length: " + length);
        
        if( length == 150) { dimCheck = true; } else {
        return false;}
        
        
        // Width Check
        r.close();
        r = new BufferedReader(new FileReader("N:\\" + FileName + ".world"));
        r.readLine();
        r.readLine();
        
        for (int i = 0; i < 150; i++) {
        
            String line = removeSpaces(r.readLine());
          // System.out.println(line);
           if(line.length() != 150) {return false;}
            
            
        }
        
        r.close();
        
        return dimCheck;}
    
    public void buildMap() throws FileNotFoundException, IOException {
    
        String row = "";
        map = new char[150][150];
        r = new BufferedReader(new FileReader("N:\\" + FileName + ".world"));
        r.readLine();
        r.readLine();
    
        for (int i = 0; i < 150; i++) {
            row = removeSpaces(r.readLine());
            //System.out.println("Line " + i + ": ");
            for(int j = 0; j < 150; j++) {
                map[i][j] = row.charAt(j);
              //  System.out.print(map[i][j]);
                }
        }
    }

    public Boolean checkRocks() {
    
        int rockCount = 0;
        
        for(int i = 1; i < 149; i++) {
            for (int j = 1; j < 149; j++) {
                if(map[i][j] == '#') 
                {
                    rockCount ++;
                }
        }
        
        }
        
        
        //System.out.println(rockCount);
        
        return (rockCount == 14);
        
    }
    
    
    public Boolean checkBorder() {
        
        for(int i = 0; i < 150; i++) {
        
            if((map[i][149] != '#') || (map[i][0] != '#')) {
            return false;
            }
            
            if((map[149][i] != '#') || (map[0][i] != '#')) {
            return false;
            }
            
        
        }
        
        return true;
    }
    
    
    public Boolean checkAntHills(){
        
        boolean hasAntHill = false;
        int posCount = 0;
        
        for(int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
            
                if (map[i][j] == '+') {
                   hasAntHill = checkRightAntHill(i,j,'+');
                   writeRightAntHill(i,j, '+');
                   
                   return hasAntHill;
//                    if ( posCount == 0) {
//                        if (checkRightAntHill(i,j, '+') | checkLeftAntHill(i,j,'+')){
//                            posCount++;
//                        }
//                    }
//                    else {
//                        return false;
//                    }
                }
                    
                
            }
        
        }
        
        
        return hasAntHill;}
    
    
    public Boolean checkFood() {return false;}
  
    
            
    public static void main(String[] args) throws IOException {
        
        AntWorldParser l = new AntWorldParser("1");
    }
    
    public String removeSpaces(String str) {
    String sb = "";
    for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);
        if ( c != ' ')  {
            sb += c;
        }
    }
    return sb;
    }
    
    public boolean checkRightAntHill(int row, int column, char team) {
    
        boolean trueHill = true;
        
        for(int i = 1; i < 7; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }
        
        row++;
        
        for(int i = 0; i < 8; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }
        
        column--;
        row++;
        
        for(int i = 0; i < 9; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }
        
        row++;
        
        for(int i = 0; i < 10; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }
        
        column--;
        row++;
        
         for(int i = 0; i < 11; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }
        
        row++;
        
        for(int i = 0; i < 12; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }
        
        column--;
        row++;
        
         for(int i = 0; i < 13; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }
        
        column++;
        row++;
        
        for(int i = 0; i < 12; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }
        
        row++;
        
        for(int i = 0; i < 11; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }
        
        column++;
        row++;
        
        for(int i = 0; i < 10; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }
        
        row++;
        
        for(int i = 0; i < 9; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }
        
        column++;
        row++;
        
        for(int i = 0; i < 8; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }
        
        row++;
        
        for(int i = 0; i < 7; i++) {
            if(map[row][i+column] != team) {
                return false;
            }
        }

    return trueHill;
    }
    
    public void writeRightAntHill(int row, int column, char team) {
    
        boolean trueHill = true;
        
        map[row][column] = 'P';
        
        for(int i = 1; i < 7; i++) {
            map[row][i+column] = 'P';
        }
        
        row++;
        
        for(int i = 0; i < 8; i++) {
            map[row][i+column] = 'P';
        }
        
        column--;
        row++;
        
        for(int i = 0; i < 9; i++) {
            map[row][i+column] = 'P';
        }
        
        row++;
        
        for(int i = 0; i < 10; i++) {
            map[row][i+column] = 'P';
        }
        
        column--;
        row++;
        
         for(int i = 0; i < 11; i++) {
            map[row][i+column] = 'P';
        }
        
        row++;
        
        for(int i = 0; i < 12; i++) {
            map[row][i+column] = 'P';
        }
        
        column--;
        row++;
        
         for(int i = 0; i < 13; i++) {
            map[row][i+column] = 'P';
        }
        
        column++;
        row++;
        
        for(int i = 0; i < 12; i++) {
            map[row][i+column] = 'P';
        }
        
        row++;
        
        for(int i = 0; i < 11; i++) {
            map[row][i+column] = 'P';
        }
        
        column++;
        row++;
        
        for(int i = 0; i < 10; i++) {
            map[row][i+column] = 'P';
        }
        
        row++;
        
        for(int i = 0; i < 9; i++) {
            map[row][i+column] = 'P';
        }
        
        column++;
        row++;
        
        for(int i = 0; i < 8; i++) {
            map[row][i+column] = 'P';
        }
        
        row++;
        
        for(int i = 0; i < 7; i++) {
            map[row][i+column] = 'P';
        }
    }
    
    public boolean checkLeftAntHill(int i, int j, char team) {
        return false;
    }
    
}
