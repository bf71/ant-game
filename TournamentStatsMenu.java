import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

/**
 *
 * @author aga23
 */
public class TournamentStatsMenu {
    
    private JFrame frame;
    
    public TournamentStatsMenu(HashMap<AntBrain, Integer> scores) {
        // Take stats array as arguement
        runMenu();
    }

    private void runMenu() {
        //Setup frame and divisons
        frame = new JFrame("Tournament Results");
        Container contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        contentPane.setLayout(new GridLayout(3,5));
        frame.setMinimumSize(new Dimension(1000,1000));
        frame.setResizable(false);
    }

}

class Player extends JPanel {
    public Player(AntBrain brain, int score) {
        
    }
}