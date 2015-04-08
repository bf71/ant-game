import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author aga23
 */
public class TournamentStatsMenu {
    
    private JFrame frame;
    
    public TournamentStatsMenu() {
        // Take stats array as arguement
        runMenu();
    }

    private void runMenu() {
        //Setup frame and divisons
        frame = new JFrame("The Ant Game");
        Container contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new GridLayout(3,5));
        frame.setMinimumSize(new Dimension(1000,150));
        frame.setResizable(false);
    }

}