
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author myq20
 * @version 2015/03/26
 *
 */
public class Tournament {

    private ArrayList<Game> roster;
    private ArrayList<Map> maps;
    private ArrayList<AntBrain> brains;
    public int gamesPlayed;
    ArrayList<Statistics> stats;
    private HashMap<AntBrain, Integer> scores;
    TournamentStatsMenu menu;

    /**
     * Constructor, initializes variables
     * Also will probably create the GUIs
     */
    public Tournament(ArrayList brains, ArrayList maps) {
        scores = new HashMap();
        roster = new ArrayList();
        this.brains = brains;
        this.maps = maps;
        stats = new ArrayList();
        gamesPlayed = 0;
        int noPlayers = this.brains.size();
        
        //loadBrains(this.brains);
        //loadMaps(this.maps);
        
        createMatchups();
        
        if(noPlayers > 2) {
            playGames();
        }
        else {
            create1v1Match();
            playGames();
        }
        
        menu = new TournamentStatsMenu(getScores());
        
    }
    /**
     * loads maps into the tournament. 
     * @param maps The maps to load
     * 
     */
    public void loadMaps(ArrayList<Map> mapList) {
        for (Map m : mapList) {
            maps.add(m);
        }
    }

    /**
     * @param brain The brains to load
     *  loads antBrains into the tournament
     */
    public void loadBrains(ArrayList<AntBrain> brainList) {
        for (AntBrain i : brainList) {
            brains.add(i);
        }
    }

    /**
     * Every ant brain to play against each other ant brain twice, not including itself, on each map available.
     * Create a game for every match up created this way and add it to the roster of games to play
     * Will automatically generate more maps if the number of maps is below a certain amount (still need to decide that amount and trigger the map generator)
     */
    public void createMatchups() {
        //If not enough maps generate more
        for (AntBrain i : brains) {
            for (AntBrain j : brains) {
                if (!(i.equals(j))) {
                    for (Map m : maps) {
                        Game g = new Game(m, i, j);
                        roster.add(g);
                    }
                }
            }
            scores.put(i, 0);
        }
    }
    /**
     * Creates a single game between the first two brains and the first map and add it to the roster
     */
    public void create1v1Match(){
        roster.add(new Game(maps.get(0), brains.get(0), brains.get(1)));
    }

    /**
     * Play each game and then display the statistics for that game
     * Probably obsolete
     */
    public void playGames() {
        for (Game g : roster) {
            //Load up the GUI and play the game
            Statistics gameStats = g.runGame();
            stats.add(gameStats);
            updateScores(g);
            gamesPlayed++;
            GameStatsMenu gsm = new GameStatsMenu(gameStats);
            boolean buttonPressed = gsm.getBP();
            while(!buttonPressed) { 
                buttonPressed = gsm.getBP();
            }
        }
    }
    
    public HashMap<AntBrain, Integer> getScores() {
        return scores;
    }
    
//    /**
//     * Removes the first game from the roster and plays it
//     * Needs more work to integrate the GUI into it
//     * @return The statistics for that game, stats are also added to an arraylist
//     */
//    public Statistics playNextGame(){
//        Game g=roster.remove(0);
//        for (int i=0; i<300000; i++){ 
//            g.nextTurn();
//            //gui.updateGui(g.getMap(), g.getAnts());
//        }
//        stats.add(g.getStatistics());
//        return g.getStatistics();
//    }
    
    /**
     * Generates the scores for each ant brain from the statistics arraylist
     * @return The scores in a hash table, the brain names are the keys, the scores are the values, there is no particular ordering in the hashmap
     */
    private void updateScores(Game game){
        Statistics gameStats = game.getStatistics();
        if (gameStats.redHillFood>gameStats.blackHillFood){
            scores.put(game.getRedBrain(), scores.get(game.getRedBrain())+2 );
            //addScore(scores, game.getRedBrain(), 2);
        }
        else if (gameStats.blackHillFood>gameStats.redHillFood){
            scores.put(game.getBlackBrain(), scores.get(game.getBlackBrain())+2 );
            //addScore(scores, game.getBlackBrain(), 2);
        }
        else {
            scores.put(game.getRedBrain(), scores.get(game.getRedBrain())+1 );
            scores.put(game.getBlackBrain(), scores.get(game.getBlackBrain())+1 );
        }
    }
    
    
    /**
     * Adds a score to the hashmap, internal method
     * @param scores The hashmap
     * @param name The name of the brain to add a score for
     * @param score The score to add
     */
    private void addScore(HashMap<AntBrain, Integer> scores, AntBrain ab, int score){
        if (scores.containsKey(ab)){
            score=score+scores.get(ab);
            scores.put(ab, score);
        } else {
            scores.put(ab, score);
        }
        
    }

    /**
     * Display the statistics for the game, for debugging
     */
    public void generateStats() {
        System.out.println("games played" + gamesPlayed);
        for (Statistics s : stats) {
            System.out.println("red ants left: " + s.redAnts);
            System.out.println("black ants left: " + s.blackAnts);
            System.out.println("red ant kills: " + s.redKills);
            System.out.println("black ant kills: " + s.blackKills);
            System.out.println("red ant food remaining: " + s.redHillFood);
            System.out.println("black ant food remaining: " + s.blackHillFood);
            System.out.println("red ants food carried: " + s.redCarryFood);
            System.out.println("black ants food carried: " + s.blackCarryFood);
            System.out.println("food left on map: " + s.mapFood);
        }
    }
}
