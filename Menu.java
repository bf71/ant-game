import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * The top page of the GUI
 * @author Alli Allfrey
 */
public class Menu {
    private JFrame frame;
    private UploadMenu uploadMenu;
//    private GamePackage pck = new GamePackage();
    private boolean menuFinished = false;
    
    /**
     * Constructor for initial menu
     */
    public Menu() { 
        runMainMenu();
    }
    
//    public GamePackage getGamePackage() {
//        pck.brainList = uploadMenu.getBrainList();
//        pck.worldList = uploadMenu.getMapMenu().getMapList();
//        return pck;
//    }
    
    private void runMainMenu() {
        //Setup frame and divisons
        frame = new JFrame("The Ant Game");
        Container contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(900,650));
        frame.setResizable(false);
        
        JPanel blankNorth = new JPanel();
        blankNorth.setPreferredSize(new Dimension(900,300)); 
        blankNorth.setMinimumSize(new Dimension(900,300));
        contentPane.add(blankNorth, BorderLayout.NORTH);
        
        JPanel blankEast = new JPanel(); 
        blankEast.setPreferredSize(new Dimension(200,50)); 
        blankEast.setMinimumSize(new Dimension(200,50));
        contentPane.add(blankEast, BorderLayout.EAST);
        
        JPanel blankSouth = new JPanel();
        blankSouth.setPreferredSize(new Dimension(900,300)); 
        blankSouth.setMinimumSize(new Dimension(900,300));
        contentPane.add(blankSouth, BorderLayout.SOUTH);
        
        JPanel blankWest = new JPanel();
        blankWest.setPreferredSize(new Dimension(200,50)); 
        blankWest.setMinimumSize(new Dimension(200,50));
        contentPane.add(blankWest, BorderLayout.WEST);

        JPanel centreGrid = new JPanel();
        centreGrid.setPreferredSize(new Dimension(500,50)); 
        centreGrid.setMinimumSize(new Dimension(500,50));
        centreGrid.setLayout(new GridLayout(1,3));
        
        // Add 1v1 button
        JButton oneButton = new JButton("Play 1 v 1"); 
        oneButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("1 v 1");
                frame.dispose();
                uploadMenu = new UploadMenu(2);
            }           
        });
        oneButton.setPreferredSize(new Dimension(200,50));
        oneButton.setMinimumSize(new Dimension(200,50));
        centreGrid.add(oneButton);
        
        JPanel blankCentre = new JPanel();
        blankCentre.setPreferredSize(new Dimension(100,50)); 
        blankCentre.setMinimumSize(new Dimension(100,50));
        centreGrid.add(blankCentre);
        
        // Add tournament button
        JButton tButton = new JButton("Play a Tournament"); 
        tButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Tournament Selected");
                try {
                    String players = JOptionPane.showInputDialog( "Number of Players:");
                    int noPlayers = Integer.parseInt(players);
                    if (noPlayers > 2) {
                        System.out.println("Creating tournament with " + noPlayers + " players!");
                        frame.dispose();
                        uploadMenu = new UploadMenu(noPlayers);
                    }
                    else {
                        JOptionPane.showMessageDialog( null, "Please enter a number greater than 2", 
                                "Tournament Error", JOptionPane.PLAIN_MESSAGE );
                    }
                }
                catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog( null, "Please enter a number between 1 and 10", 
                            "Tournament Error", JOptionPane.PLAIN_MESSAGE );
                }
            }           
        });
        tButton.setPreferredSize(new Dimension(200,50));
        tButton.setMinimumSize(new Dimension(200,50));
        centreGrid.add(tButton);
        
        contentPane.add(centreGrid, BorderLayout.CENTER);
        
        // Pack and display
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setSize(new Dimension(900,650));
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        Menu gui = new Menu();
    }

}

class UploadMenu {
    
    private ArrayList brainList = new ArrayList();
    private UploadMapMenu mapMenu;
    private JFrame frame;
    private JTextField filename = new JTextField(), dir = new JTextField(), 
            countName = new JTextField(), countBox = new JTextField();
    private JButton browse = new JButton("Browse"), commit = new JButton("Commit"),
            cont = new JButton("Continue");
    private int brainCount = 0;

    public UploadMenu(int noPlayers) {
        final int no_Players = noPlayers;
        
        //Setup frame and divisons
        frame = new JFrame("The Ant Game: Upload Brains");
        Container contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(500,100));
        
        // Add upload panel
        JPanel upl = new JPanel();
        upl.setLayout(new FlowLayout());
        contentPane.add(upl, BorderLayout.EAST);
        
        // Add browse button 
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(brainCount != no_Players) {
                    JFileChooser c = new JFileChooser();
                    int rVal = c.showOpenDialog(frame);
                    if (rVal == JFileChooser.APPROVE_OPTION) {
                        String name = c.getSelectedFile().getName();
                        int i = name.lastIndexOf('.');
                        String extension = name.substring(i+1);
                        if(extension.equals("ant")) {
                            filename.setText(name);
                            dir.setText(c.getCurrentDirectory().toString());
                        }
                        else {
                            JOptionPane.showMessageDialog( null, "Please select an ant brain file.", 
                            "File Error", JOptionPane.PLAIN_MESSAGE );
                        }
                    }
                    if (rVal == JFileChooser.CANCEL_OPTION) {
                        filename.setText("Browse cancelled");
                        dir.setText("");
                    }
                }
                else {
                    JOptionPane.showMessageDialog( null, "You cannot upload any more ant brains", 
                            "Upload Error", JOptionPane.PLAIN_MESSAGE );
                }
            }
        });
        upl.add(browse);
        
        // Add file information fields
        filename.setEditable(false);
        dir.setEditable(false);
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(300,50));
        p.setLayout(new GridLayout(2, 1));
        p.add(filename);
        p.add(dir);
        upl.add(p);
        
        // Add commit button
        commit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String directory = dir.getText() + "\\" +  filename.getText();
                if(directory.isEmpty()) {
                    JOptionPane.showMessageDialog( null, "Please upload a file to commit", 
                            "Upload Error", JOptionPane.PLAIN_MESSAGE );
                }
                else {
                    System.out.println("Testing Brain " + brainCount);
                    try {
                        // get the brain from the parser
                        BrainParser parser = new BrainParser(directory);
                        AntBrain brain = new AntBrain(parser.getInstructions(), Integer.toString(brainCount+1));
                        brainList.add(brain);
                        brainCount++;
                        countBox.setText(Integer.toString(brainCount));
                    } catch (FileNotFoundException ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog( null, "Brain not found, Please Try Again",
                                "Upload Error", JOptionPane.PLAIN_MESSAGE );
                    } catch (IOException ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog( null, "Invalid brain, Please Try Again",
                                "Upload Error", JOptionPane.PLAIN_MESSAGE );
                    } catch (ParseException ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog( null, "Invalid brain, Please Try Again",
                                "Upload Error", JOptionPane.PLAIN_MESSAGE );
                    }
                }
            }           
        });
        upl.add(commit);
        
        // Add the count fields
        JPanel countP = new JPanel();
        countP.setLayout(new FlowLayout());
        countName.setEditable(false);
        countName.setText("Number of Brains Uploaded:");
        countBox.setEditable(false);
        countBox.setText(Integer.toString(brainCount));
        countP.add(countName);
        countP.add(countBox);
        // Add continue button
        cont.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(brainCount == no_Players) {
                    frame.dispose();
                    mapMenu = new UploadMapMenu(no_Players, brainList);
                }
                else {
                    JOptionPane.showMessageDialog( null, "Please upload the correct number of ant brains: " + no_Players, 
                            "Upload Error", JOptionPane.PLAIN_MESSAGE );
                }
            }           
        });
        countP.add(cont);
        contentPane.add(countP, BorderLayout.SOUTH);

        // Pack and display
        frame.setSize(new Dimension(300,200));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
    }
    
//    public ArrayList getBrainList() {
//        return brainList;
//    }
//    
//    public UploadMapMenu getMapMenu() {
//        return mapMenu;
//    }
}

class UploadMapMenu {
    
    private ArrayList mapList = new ArrayList();
    private JFrame frame;
    private JTextField filename = new JTextField(), dir = new JTextField(), 
            countName = new JTextField(), countBox = new JTextField();
    private JButton browse = new JButton("Browse"), commit = new JButton("Commit"),
            cont = new JButton("Continue");
    private int mapCount = 0;

    public UploadMapMenu(int noPlayers, final ArrayList brains) {
        final int mapsRequired;
        if(noPlayers == 2) {
            mapsRequired = 1;
        } else {
            mapsRequired = noPlayers;
        }
        //Setup frame and divisons
        frame = new JFrame("The Ant Game: Upload Maps");
        Container contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(500,100));
        
        // Add upload panel
        JPanel upl = new JPanel();
        upl.setLayout(new FlowLayout());
        contentPane.add(upl, BorderLayout.EAST);
        
        // Add browse button 
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mapCount != mapsRequired) {
                    JFileChooser c = new JFileChooser();
                    int rVal = c.showOpenDialog(frame);
                    if (rVal == JFileChooser.APPROVE_OPTION) {
                        String name = c.getSelectedFile().getName();
                        int i = name.lastIndexOf('.');
                        String extension = name.substring(i+1);
                        if(extension.equals("world")) {
                            filename.setText(name);
                            dir.setText(c.getCurrentDirectory().toString());
                        }
                        else {
                            JOptionPane.showMessageDialog( null, "Please select a world file.", 
                            "File Error", JOptionPane.PLAIN_MESSAGE );
                        }
                    }
                    if (rVal == JFileChooser.CANCEL_OPTION) {
                        filename.setText("Browse cancelled");
                        dir.setText("");
                    }
                }
                else {
                    JOptionPane.showMessageDialog( null, "You cannot upload any more worlds", 
                            "Upload Error", JOptionPane.PLAIN_MESSAGE );
                }
            }
        });
        upl.add(browse);
        
        // Add file information fields
        filename.setEditable(false);
        dir.setEditable(false);
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(300,50));
        p.setLayout(new GridLayout(2, 1));
        p.add(filename);
        p.add(dir);
        upl.add(p);
        
        // Add commit button
        commit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String directory = dir.getText() + "\\" +  filename.getText();
                if(directory.isEmpty()) {
                    JOptionPane.showMessageDialog( null, "Please upload a file to commit", 
                            "Upload Error", JOptionPane.PLAIN_MESSAGE );
                }
                else {
                    System.out.println("Testing Map " + mapCount);
                    AntWorldParser parser = new AntWorldParser();
                    try {
                        if(parser.runTest(directory)) {
                            mapList.add(parser.makeMap());
                            mapCount++;
                            countBox.setText(Integer.toString(mapCount));
                        } else {
                            JOptionPane.showMessageDialog( null, "Invalid World, Please Try Again",
                                    "Upload Error", JOptionPane.PLAIN_MESSAGE );
                        }
                    } catch (IOException ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog( null, "Error Parsing World, Please Try Again",
                                "Upload Error", JOptionPane.PLAIN_MESSAGE );
                    }
                }
            }           
        });
        upl.add(commit);
        
        // Add the count fields
        JPanel countP = new JPanel();
        countP.setLayout(new FlowLayout());
        countName.setEditable(false);
        countName.setText("Number of Maps Uploaded:");
        countBox.setEditable(false);
        countBox.setText(Integer.toString(mapCount));
        countP.add(countName);
        countP.add(countBox);
        // Add continue button
        cont.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(mapCount == mapsRequired) {
                    //start the game using the parameters
                    frame.dispose();
                    System.out.println("Menu Finished... Starting Game.");
                    Tournament t = new Tournament(brains, mapList);
                }
                else if(mapCount < mapsRequired) {
                    JOptionPane.showMessageDialog( null, "Not enough maps have been uploaded, "
                            + "to use the default map generator for the remaining maps required"
                            + "please press continue again.",
                            "Upload Error", JOptionPane.PLAIN_MESSAGE );
                }
                else {
                    JOptionPane.showMessageDialog( null, "You have uploaded too many maps", 
                            "Upload Error", JOptionPane.PLAIN_MESSAGE );
                }
            }           
        });
        countP.add(cont);
        contentPane.add(countP, BorderLayout.SOUTH);

        // Pack and display
        frame.setSize(new Dimension(300,200));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
    }
    
//    public ArrayList getMapList() {
//        return mapList;
//    }
}

//class GamePackage {
//    ArrayList brainList = new ArrayList();
//    ArrayList worldList = new ArrayList();
//    
//    public void addBrain(AntBrain brain) {
//        brainList.add(brain);
//    }
//    
//    public void addMap(Map map) {
//        worldList.add(map);
//    }
//}