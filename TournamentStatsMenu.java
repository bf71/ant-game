import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.*;

/**
 *
 * @author aga23
 */
public class TournamentStatsMenu {
    
    private JFrame frame;
    private int noPlayers;
    private HashMap<AntBrain, Integer> scores;
    private JLabel name = new JLabel(), score = new JLabel();
    
    public TournamentStatsMenu(HashMap<AntBrain, Integer> scores) {
        // Take stats array as arguement
        this.scores = scores;
        noPlayers = this.scores.size();
        runMenu();
    }

    private void runMenu() {
        //Setup frame and divisons
        frame = new JFrame("Tournament Results");
        Container contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new BorderLayout());
        
//        JPanel blankNorth = new JPanel();
//        blankNorth.setPreferredSize(new Dimension(900,50)); 
//        blankNorth.setMinimumSize(new Dimension(900,50));
//        contentPane.add(blankNorth, BorderLayout.NORTH);
//        
//        JPanel blankEast = new JPanel(); 
//        blankEast.setPreferredSize(new Dimension(200,50)); 
//        blankEast.setMinimumSize(new Dimension(200,50));
//        contentPane.add(blankEast, BorderLayout.EAST);
//        
//        JPanel blankWest = new JPanel();
//        blankWest.setPreferredSize(new Dimension(200,50)); 
//        blankWest.setMinimumSize(new Dimension(200,50));
//        contentPane.add(blankWest, BorderLayout.WEST);

        
        JPanel statPanel = new JPanel();
        statPanel.setLayout(new GridLayout(noPlayers + 1,1));
        
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(1,2));
        name.setText("Team Name"); score.setText("Score");
        namePanel.add(name); namePanel.add(score);
        
        statPanel.add(namePanel);
        
        AntBrain currentWinner;
        boolean draw = false;
        
        Object[] brainList = scores.keySet().toArray();
        currentWinner = (AntBrain)brainList[0];
        for(Object obj: brainList) {
            AntBrain brain = (AntBrain)obj;
            PlayerRow p = new PlayerRow(brain, scores.get(brain));
            statPanel.add(p);
            if(scores.get(currentWinner) < scores.get(brain)) {
                currentWinner = brain;
            }
            else if (scores.get(currentWinner) == scores.get(brain)) {
                draw = true;   
            }
        }
        
        JLabel winner;
        
        if(draw) {
            winner = new JLabel("Draw");
        } else {
            winner = new JLabel("Winner: " + currentWinner.name);
        }

        contentPane.add(winner, BorderLayout.NORTH);
        
        contentPane.add(statPanel, BorderLayout.CENTER);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

}

class PlayerRow extends JPanel {
    JLabel name = new JLabel(), score = new JLabel();
    
    public PlayerRow(AntBrain brain, int _score) {
        System.out.println(brain.name + " score: " + _score);
        this.setLayout(new GridLayout(1,2));
        name.setText(brain.name);
        score.setText(Integer.toString(_score));
        this.add(name);
        this.add(score);
    }
    
}