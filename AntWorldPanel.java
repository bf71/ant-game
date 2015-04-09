//import Ant.Ant;
//import Antmapgenerator.AntMapGenerator;
//import Antmapgenerator.Map;
//import Game.Game;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 *
 * @author ec263
 */
public class AntWorldPanel extends JPanel {
    
    
    private JFrame frame;
    //FontMetrics metrics;
    static Map map;
    Ant[] ants;
    static final int xStart = 10;
    static final int yStart = 10;
    static final double spacing = 13;
    static final int hexRadius = 8;
    //static final int offset = 7;
    
    AntWorldPanel (Map map) {
        initDisplay(map);
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        /*
        ants = new Ant[2];
        try {
            ants[0] = new Ant(Game.Colour.BLACK, 11, 11, 0, true , new Point(5, 5));
            ants[1] = new Ant(Game.Colour.RED, 11, 11, 0, true , new Point(3, 5));
        } catch (Exception ex) {
            Logger.getLogger(AntWorldPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        * 
        */
        
        Font f = new Font("Dialog", Font.BOLD, 12);
        
        g.setFont(f);

        for (int i = 0; i < 150; i++) {
            int offset;
            
            if (i % 2 != 0) {
                offset = 7;
            }
            else offset = 0;

            for (int j = 0; j < 150; j++) {
                Polygon hex = new Polygon();
                for (int pt = 0; pt < 6; pt++){
                    hex.addPoint((int)(xStart + offset + (spacing * j) + hexRadius * Math.sin(pt * 2 * Math.PI / 6)), (int)(yStart+ (spacing * i) +hexRadius * Math.cos(pt*2*Math.PI/6)));
                    }
                
                    if (map.getCell(i, j).isRocky()) {
                        g.setColor(Color.BLACK);
                    }
                    else if (map.getCell(i, j).getRedAnthill()){
                        g.setColor(Color.RED);
                    }
                    else if (map.getCell(i,j).getBlackAnthill()) {
                        g.setColor(Color.DARK_GRAY);
                    }
                    else if (map.getCell(i,j).hasFood() > 0) {

                        g.setColor(Color.YELLOW);
                    }
                    else
                        g.setColor(Color.gray);

                    g.fillPolygon(hex);
                    setLineThickness(g, 2);
                    g.setColor(Color.black);
                    g.drawPolygon(hex);
                    if (map.getCell(i,j).hasFood() > 0) {
                        String s = "";
                        s += map.getCell(i,j).hasFood();
                        //s+= "Fug";
                            g.setColor(Color.BLUE);
                            g.drawString(s, (int) (xStart + offset +(spacing * j) - 4), (int) (yStart+ (spacing * i) + hexRadius - 3) );
                    }
                    
                    /*
                    for (Ant a : ants) {
                        if (a.getPosition() == new Point (i , j)) {
                            g.setColor(Color.RED);
                            g.drawString("R", (int) (xStart + offset +(spacing * j) - 4), (int) (yStart+ (spacing * i) + hexRadius - 3) );
                        }
                    }
                    * 
                    */
                     
                    
                    
                   
                    
                /*
                if (ants[i][j].getColor() == "red") {
                    g.setColor(Color.RED);
                    g.drawString("R", (int) (xStart + offset +(spacing * j) - 5), (int) (yStart+ (spacing * i) + hexRadius - 3) );
                }
                else if (ants[i][j].getColor() =="black") {
                    g.setColor(Color.BLACK);
                    g.drawString("B", (int) (xStart + offset +(spacing * j) - 5), (int) (yStart+ (spacing * i) + hexRadius - 3) );
                }
                * 
                */ 
            }
            
            

        }       
        
        //Font f2 = new Font("Dialog", Font.BOLD, 100);
        
        //g.setFont(f2);
        if (ants != null) {
            for (Ant a : ants) {
                int offset;
                
                Point p = a.getPosition();
                
                //System.out.println("ant pos: " + a.getPosition());
                
                if (p.y % 2 != 0) {
                    offset = 7;
                }
                else { 
                    offset = 0;
                }
                
                String s = "+";
                if (a.getColor() == Game.Colour.RED) {
                    g.setColor(Color.RED);
                    //s += "+";
                }
                else {
                    g.setColor(Color.BLACK);
                    //s+="+";
                }

                //g.setColor(Color.RED);
                //System.out.println("ofset :" + offset);
                g.drawString(s, (int) (xStart + offset +(spacing * p.x) - 3), (int) (yStart+ (spacing * p.y) + hexRadius - 3) );

            }
        }
        
        
        

    }
 
  public static void setLineThickness(Graphics page, int thickness) {
    if (thickness < 0) thickness = 0;
    ((Graphics2D)page).setStroke(new BasicStroke(thickness));
  }
  
  public void updateMap() {
      
  }
    
    public void setMap(Map map) {
        this.map = map;
    }
    
    public void getAnts(Ant[] ants) {
        this.ants = ants;
    }
    
    public void disposeFrame() {
        frame.dispose();
    }
        
    public void initDisplay(Map map) {
        System.out.println("yij");
        //map.printMap();
        this.setMap(map);
        this.map.printMap();
        
        frame = new JFrame();
        //JScrollPane scroll = new JScrollPane();
        //AntWorldPanel drawPanel = new AntWorldPanel();
        
        //drawPanel.getAnts(ants);
        //drawPanel.setMap(map);
        
        //this.repaint();
        
        /*
        for (Ant a : ants) {
            System.out.println(a.getPosition());
        }
        * 
        */
        
        this.setPreferredSize(new Dimension(1965, 1965));
        this.setBorder(BorderFactory.createLineBorder(Color.RED));
        
        //scroll.setPreferredSize(new Dimension(1024,768));
        
        //scroll.setViewportView(this);
        //scroll.getVerticalScrollBar().setUnitIncrement(50);
        
        Container contentPane = frame.getContentPane();
        

        frame.setTitle("Ant World Display");
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        contentPane.add(this);
        //contentPane.add(scroll);

        repaint();
        
        frame.setSize(1920,1080);
        //frame.pack();
        frame.setVisible(true);
    }
    
    public void updateDisplay() {
        getAnts(ants);
        repaint();
    }
    
    public static void main(String[] args) throws Exception {
        
        AntMapGenerator amg = new AntMapGenerator(150,150);
        Ant[] ants = new Ant[4];
        ants[0] = new Ant(Game.Colour.RED, 11, 11, 0, true , new Point(3, 5)); 
        ants[1] = new Ant(Game.Colour.BLACK, 11, 11, 0, true , new Point(5, 5));
        ants[2] = new Ant(Game.Colour.RED, 11, 11, 0, true , new Point(6, 5));
        ants[3] = new Ant(Game.Colour.BLACK, 11, 11, 0, true , new Point(6, 6));
        
        
        //ntWorldPanel awp = new AntWorldPanel();
        //awp.ants = ants;

        amg.generateMap();
        map = amg.getMap();
        

        JFrame frame = new JFrame();
        JScrollPane scroll = new JScrollPane();
        AntWorldPanel drawPanel = new AntWorldPanel(map);
        
        drawPanel.getAnts(ants);
        
        //drawPanel.repaint();
        
        /*
        for (Ant a : ants) {
            System.out.println(a.getPosition());
        }
        * 
        */
        
        drawPanel.setPreferredSize(new Dimension(1965, 1965));
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        scroll.setPreferredSize(new Dimension(1024,768));
        
        scroll.setViewportView(drawPanel);
        scroll.getVerticalScrollBar().setUnitIncrement(50);
        
        Container contentPane = frame.getContentPane();
        

        frame.setTitle("Ant World Display");
        frame.setSize(1920,1080);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        contentPane.add(scroll);

        frame.show();
    }
    
}
