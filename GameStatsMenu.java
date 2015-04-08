import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author aga23
 */
public class GameStatsMenu {
    private boolean buttonPressed;
    private JFrame frame;
    private JLabel redTeam = new JLabel(), blackTeam = new JLabel(), 
            rAnts = new JLabel(), bAnts = new JLabel(), rFood = new JLabel(), 
            rHillFood = new JLabel(), bHillFood = new JLabel(), mapFood = new JLabel(),
            bFood = new JLabel(), winner = new JLabel(), winnerField = new JLabel(),
            food = new JLabel(), ants = new JLabel(), rowName = new JLabel(), hillFood = new JLabel();
    private JButton nextG = new JButton("Play next game");
    
    /**
     * Constructor for initial menu
     */
    public GameStatsMenu(Statistics gameStats) { 
        // Put statistics as arguement, pasrse it to runMenu to put in text boxes
        runMenu(gameStats);
    }
    
    public boolean getBP() {
        return buttonPressed;
    }
    
    private void runMenu(Statistics gameStats) {
        
        frame = new JFrame("Game Results");
        Container contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(500,400));
        frame.setResizable(false);
        
        JPanel blankNorth = new JPanel();
        blankNorth.setPreferredSize(new Dimension(500,50)); 
        blankNorth.setMinimumSize(new Dimension(500,50));
        contentPane.add(blankNorth, BorderLayout.NORTH);
        
        JPanel blankEast = new JPanel(); 
        blankEast.setPreferredSize(new Dimension(100,350)); 
        blankEast.setMinimumSize(new Dimension(100,350));
        contentPane.add(blankEast, BorderLayout.EAST);
        
        JPanel blankWest = new JPanel();
        blankWest.setPreferredSize(new Dimension(100,350)); 
        blankWest.setMinimumSize(new Dimension(100,350));
        contentPane.add(blankWest, BorderLayout.WEST);
        
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new GridLayout(3,1));
        
        // Set up stat grid
        JPanel statPanel = new JPanel();
        statPanel.setPreferredSize(new Dimension(100,100));
        statPanel.setLayout(new GridLayout(4,3));
        
        // Add top row
        rowName.setText("Stat");
        statPanel.add(rowName);
        blackTeam.setText("Black Team");
        statPanel.add(blackTeam);
        redTeam.setText("Red Team");
        statPanel.add(redTeam);
        
        // Add second row
        ants.setText("Ants Remaining:");
        statPanel.add(ants);
        bAnts.setText(Integer.toString(gameStats.blackAnts));
        statPanel.add(bAnts);
        rAnts.setText(Integer.toString(gameStats.redAnts));
        statPanel.add(rAnts);
        
        // Add third row
        hillFood.setText("Food Collected:");
        statPanel.add(hillFood);
        bHillFood.setText(Integer.toString(gameStats.blackHillFood));
        statPanel.add(bHillFood);
        rHillFood.setText(Integer.toString(gameStats.redHillFood));
        statPanel.add(rHillFood);
        
        // Add forth row
        food.setText("Food Carried:");
        statPanel.add(food);
        bFood.setText(Integer.toString(gameStats.blackCarryFood));
        statPanel.add(bFood);
        rFood.setText(Integer.toString(gameStats.redCarryFood));
        statPanel.add(rFood);
        
        outerPanel.add(statPanel);

        // Winner Section
        JPanel winP = new JPanel();
        winP.setLayout(new FlowLayout());
        winner.setText("Winner:");
        if (gameStats.redHillFood>gameStats.blackHillFood){
            winnerField.setText("RED TEAM");
        }
        else if (gameStats.blackHillFood>gameStats.redHillFood){
            winnerField.setText("BLACK TEAM");
        }
        else {
            winnerField.setText("DRAW");
        }
        winnerField.setText("RED TEAM");
        winP.add(winner);
        winP.add(winnerField);
        
        outerPanel.add(winP);
        
        // Next Game Section
        nextG.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog( null, "The next game will now be played", 
                            "Next Game", JOptionPane.PLAIN_MESSAGE );
                frame.dispose();
                buttonPressed = true;
            }
        });
        outerPanel.add(nextG);
        
        contentPane.add(outerPanel, BorderLayout.CENTER);

        // Pack and display
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setSize(new Dimension(500,400));
        frame.setVisible(true);
    }
    
//    public static void main(String[] args) {
//        GameStatsMenu gui = new GameStatsMenu();
//    }
}