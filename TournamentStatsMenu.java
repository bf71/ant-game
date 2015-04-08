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
        
        JPanel statPanel = new JPanel();
        statPanel.setLayout(new GridLayout(noPlayers + 1,1));
        
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(1,2));
        name.setText("Team Name"); score.setText("Score");
        namePanel.add(name); namePanel.add(score);
        
        statPanel.add(namePanel);
        
        
        Object[] brainList = scores.keySet().toArray();
        for(Object obj: brainList) {
            AntBrain brain = (AntBrain)obj;
            PlayerRow p = new PlayerRow(brain, scores.get(brain));
            statPanel.add(p);
        }
        
        contentPane.add(statPanel);
        frame.setMinimumSize(new Dimension(500,400));
        frame.setResizable(false);
    }

}

class PlayerRow extends JPanel {
    JLabel name = new JLabel(), score = new JLabel();
    
    public PlayerRow(AntBrain brain, int _score) {
        this.setLayout(new GridLayout(1,2));
        name.setText(brain.name);
        score.setText(Integer.toString(_score));
        this.add(name);
        this.add(score);
    }
    
}