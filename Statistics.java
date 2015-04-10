
/**
 *
 * @author Zachary Hoad
 */
public class Statistics {
    
    public String redName;
    public String blackName;
    public int redAnts;
    public int blackAnts;
    public int redKills;
    public int blackKills;
    public int redHillFood;
    public int blackHillFood;
    public int mapFood;
    public int redCarryFood;
    public int blackCarryFood;
    
    public Statistics(){
        redName="";
        blackName="";
        redAnts=0;
        blackAnts=0;
        redKills=0;
        blackKills=0;
        redHillFood=0;
        blackHillFood=0;
        mapFood=0;
        redCarryFood=0;
        blackCarryFood=0;
    }
    
    public void printStats() {
        System.out.println("redName: " + redName);
        System.out.println("blackName: " + blackName);
        System.out.println("redAnts: " + redAnts);
        System.out.println("blackAnts: " + blackAnts);
        System.out.println("redKills: " + redKills);
        System.out.println("blackKills: " + blackKills);
        System.out.println("redHillFood: " + redHillFood);
        System.out.println("blackHillFood: " + blackHillFood);
        System.out.println("mapFood: " + mapFood);
        System.out.println("redCarryFood: " + redCarryFood);
        System.out.println("blackCarryFood: " + blackCarryFood);
        
    }
}
