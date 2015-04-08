
import java.util.ArrayList;

/**
 *
 * @author Zachary Hoad
 */
public class AntBrain {
    public ArrayList<Instruction> instructions;
    public String name;
    
    public AntBrain(ArrayList<Instruction> instructions, String name){
        this.instructions=instructions;
        this.name=name;
    }
}
