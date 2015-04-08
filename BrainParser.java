import java.io.BufferedReader;

/**
 *
 * @author Ben Frost
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**The main class of the brain parser package
 * This class makes an instance of a javacc generated parser to the correct ant brain specification
 * then if the parse is successful, manipulates the brain file and formulates a list of instruction objects from it
 *
 * @author Ben
 */
public class BrainParser {

    String toParse;
    BufferedReader r;
    String holder = "";
    int lineCount = 0;
    ArrayList<String> stringList = new ArrayList<String>();
    ArrayList<String> filteredList = new ArrayList<String>();
    ArrayList<Instruction> InstructionList = new ArrayList<Instruction>();

    /**Returns the arrayList holding the Instructions creating from the brain file
     *
     * @return InstructionList 
     */
    public ArrayList getInstructions() {
        return InstructionList;
    }

//    /**Returns the name of the File the parser is generating instructions from
//     *
//     * @return FileName
//     */
//    public String getFileName() {
//        return directory;
//    }

    /**The constructor takes the name of the file to parse in a String fornmat
     *
     * @param s name of the file to parse
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public BrainParser(String directory) throws FileNotFoundException, IOException, ParseException {
        ParseFile(directory);
    }

    /**The main method for checking the syntactic correctness of a file
     * 
     * This method reads the given file into a temporary string variable and checks that file length isn't over the given limit
     * if the file isn't over the length limit, it calls the javacc generated parser on a StringReader constructed from the temporary strin holding the file
     *if this is parser successfully, the file is trimmed, comments removed, and turned into an arrayList of instructions of Strings
     * this is then finally turned into an arrayList of instruction objects
     * 
     * @param directory
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void ParseFile(String directory) throws FileNotFoundException, IOException, ParseException {

        BufferedReader r = new BufferedReader(new FileReader(directory)); // looks for the file creating a path from the given filename

        directory = r.readLine(); 
        
        if (directory == null) {throw new IOException();}

        while (directory != null) {
            stringList.add(directory); // adds each string line of the file to an arraylist
            holder = holder + directory + "\n";
            lineCount += 1; // counting lines while they aren't empty
            directory = r.readLine();

        }

        System.out.println(holder); // prints file

        if (lineCount <= 10000) { // if file isn't too long, then parse it

            StringReader reader = new StringReader(holder);
            r.close();

            AntBrainParser parser = new AntBrainParser(reader);
            parser.Start();

            toStringArrayList();

            toInstructionList();

            for (Instruction i : InstructionList) { // testing instruction list is created properly

                // if (i.getClass() == Sense.class) 
                {
                   // System.out.println(i);
                }

            }

        } else {

            System.out.println("The brain file is too long");
        }

    }

    /** Removes comments and trims instructions from the brain file
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void toStringArrayList() throws FileNotFoundException, IOException {

        for (String s : stringList) {

            if (s.contains(";")) {

                s = s.split(";")[0]; // split string at first instance of semicolon marking a comment
            }

            filteredList.add(s.trim()); 

        }

    }

    /**Converts the arrayList of Strings into actual instruction objects, one instruction at a time
     * checks to see which instruction they are, and trim and manipulate the strings to retrieve its parameters
     *Creates the suitable instruction for the line and adds it to the arrayList of instructions
     */
    public void toInstructionList() {

        for (String s : filteredList) {

            if (s.contains("Sense")) {

                String direction = "";
                String state1 = "";
                String state2 = "";
                String cond = "";
                String marker = "";
                int charCount = 0;
                int state = 0;

                s = s.substring(5); // remove initial instruction command
                s = s.trim();

                while (s.charAt(charCount) != ' ') { //collect characters until the next whiespace

                    direction += s.charAt(charCount);
                    charCount++;
                }

                //System.out.println(direction);
                s = s.substring(charCount); // consume the next word on line and trim the resulting string again
                s = s.trim();

                charCount = 0;

                while (s.charAt(charCount) != ' ') {

                    state1 += s.charAt(charCount);
                    charCount++;
                }

                //System.out.println(state1);
                s = s.substring(charCount);
                s = s.trim();

                charCount = 0;

                while (s.charAt(charCount) != ' ') {

                    state2 += s.charAt(charCount);
                    charCount++;
                }

                //System.out.println(state2);
                s = s.substring(charCount);
                s = s.trim();
                
                

                cond = s;
                cond.trim();
                
                
                charCount = 0;
                
                if (!cond.contains("Marker ")){
               
                // System.out.println(cond);
                Sense sen = new Sense(Direction.valueOf(direction.toUpperCase()), state = Integer.parseInt(state1), state = Integer.parseInt(state2), Condition.valueOf(cond.toUpperCase()));
                InstructionList.add(sen);
              
                } 
                else {
                    
                while( cond.charAt(charCount) != ' ') {

                    marker += cond.charAt(charCount);
                    charCount++;
                }
                    
                marker = cond.substring(charCount);
                marker = marker.trim();
               
                    
                    
                 Sense sen = new Sense(Direction.valueOf(direction.toUpperCase()), state = Integer.parseInt(state1), state = Integer.parseInt(state2), Condition.MARKER, Integer.parseInt(marker));
                
                 InstructionList.add(sen);
                
                }
                // create a new sense by converting strings to enums and integers, add it to list.

                
            }

            if ((s.contains("Mark" )&& (!s.contains("Marker")))) {

                String marker = "";
                String state = "";
                int charCount = 0;
                int stater = 0;

                s = s.substring(4);
                s = s.trim();

                while (s.charAt(charCount) != ' ') {

                    marker += s.charAt(charCount);
                    charCount++;
                }

                //    System.out.println(marker);
                s = s.substring(charCount);
                s = s.trim();

                state = s;
                state = state.trim();

                //   System.out.println(state);
                Mark mar = new Mark(stater = Integer.parseInt(marker), stater = Integer.parseInt(state));
                InstructionList.add(mar);

            }
            if (s.contains("Unmark")) {

                String marker = "";
                String state = "";
                int charCount = 0;
                int stater = 0;

                s = s.substring(6);
                s = s.trim();

                while (s.charAt(charCount) != ' ') {

                    marker += s.charAt(charCount);
                    charCount++;
                }

                //  System.out.println(marker);
                s = s.substring(charCount);
                s = s.trim();

                state = s;
                state = state.trim();

                // System.out.println(state);
                Unmark unmar = new Unmark(stater = Integer.parseInt(marker), stater = Integer.parseInt(state));
                InstructionList.add(unmar);
            }
            if (s.contains("PickUp")) {

                String state1 = "";
                String state2 = "";
                int State = 0;
                int charCount = 0;

                s = s.substring(6);
                s = s.trim();

                while (s.charAt(charCount) != ' ') {

                    state1 += s.charAt(charCount);
                    charCount++;
                }

                //System.out.println(state1);
                s = s.substring(charCount);
                s = s.trim();

                state2 = s;
                state2 = state2.trim();

                //System.out.println(state2);
                PickUp pup = new PickUp(State = Integer.parseInt(state1), State = Integer.parseInt(state2));
                InstructionList.add(pup);

            }
            if (s.contains("Drop")) {

                String state = "";
                int stater = 0;

                s = s.substring(4);
                s = s.trim();

                state = s;
                state = state.trim();

                //    System.out.println(state);
                Drop d = new Drop(stater = Integer.parseInt(state));
                InstructionList.add(d);

            }
            if (s.contains("Turn")) {

                String direction = "";
                String state = "";
                int charCount = 0;
                int stater = 0;

                s = s.substring(4);
                s = s.trim();

                while (s.charAt(charCount) != ' ') {

                    direction += s.charAt(charCount);
                    charCount++;
                }

                // System.out.println(direction);
                s = s.substring(charCount);
                s = s.trim();

                state = s;
                state = state.trim();

                // System.out.println(state);
                Turn t = new Turn(stater = Integer.parseInt(state), leftOrRight.valueOf(direction.toUpperCase()));
                InstructionList.add(t);
            }
            if (s.contains("Move")) {

                String state1 = "";
                String state2 = "";
                int charCount = 0;
                int state = 0;

                s = s.substring(4);
                s = s.trim();

                while (s.charAt(charCount) != ' ') {

                    state1 += s.charAt(charCount);
                    charCount++;
                }

                //  System.out.println(state1);
                s = s.substring(charCount);
                s = s.trim();

                state2 = s;
                state2 = state2.trim();

                // System.out.println(state2);
                Move m = new Move(state = Integer.parseInt(state1), state = Integer.parseInt(state2));
                InstructionList.add(m);

            }
            if (s.contains("Flip")) {

                String flip = "";
                String state1 = "";
                String state2 = "";
                int charCount = 0;
                int state = 0;

                s = s.substring(4);
                s = s.trim();

                while (s.charAt(charCount) != ' ') {

                    flip += s.charAt(charCount);
                    charCount++;
                }

                //System.out.println(flip);
                s = s.substring(charCount);
                s = s.trim();

                charCount = 0;

                while (s.charAt(charCount) != ' ') {

                    state1 += s.charAt(charCount);
                    charCount++;
                }

                //System.out.println(state1);
                s = s.substring(charCount);
                s = s.trim();

                state2 = s;
                state2 = state2.trim();

                //System.out.println(state2);
                Flip f = new Flip(state = Integer.parseInt(flip), state = Integer.parseInt(state1), state = Integer.parseInt(state2));
                InstructionList.add(f);

            }

        }

    }

    /**for testing loading a simple brain file
     *
     * @param args
     * @throws ParseException
     * @throws TokenMgrError
     * @throws IOException
     */
    public static void main(String[] args) throws ParseException, TokenMgrError, IOException {

        BrainParser s = new BrainParser("1");

    }

}
