/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window;

import Buildings.Building;
import PlayerPack.Army;
import PlayerPack.GreenTree;
import PlayerPack.Nation;
import PlayerPack.Player;
import framework.ObjectId;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jdk.nashorn.internal.ir.BreakNode;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Emre
 */
public class Game extends JPanel implements Runnable 
{

    private JButton backButton = new JButton("Exit");
    private JButton pauseButton = new JButton("Pause");
    private boolean paused = false;
    private JFrame frame;
    private Container panel;
    private boolean running = false;
    private Thread thread;
    private BufferedImage level = null;
    private BufferedImage background = null;
    Handler handler = new Handler();
    private int clickCount = 0;
    //trying
    JLabel label;
    ArrayList<Building> actionBuildings = new ArrayList<Building>();
    ArrayList<Building> listB = new ArrayList<Building>();
    Army army,army2;
    ArrayList<Army> list;
    ArrayList<Army> movingArmies = new ArrayList<Army>();
    ArrayList<JLabel> movingLabels = new ArrayList<JLabel>();
    Player player;
    
    
    public static int WIDTH, HEIGHT;
    public synchronized void Start()
    {
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public void copyFrameMain(JFrame frame){
        this.frame = frame;
    }
    
    public void copyContentPaneMain(Container panel){
        this.panel = panel;
    }
    
    public void init()
    {
        setLayout(null);
        WIDTH = getWidth();
        HEIGHT = getHeight();
        backButton.setVisible(true);
        backButton.setBounds(10, 10, 64, 32);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(panel);
                frame.setVisible(true);
                thread.stop();
            }
        });
        add(backButton);
        pauseButton.setVisible(true);
        pauseButton.setBounds(80, 10, 84, 32);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!paused)
                {
                    
                    thread.suspend();
                    paused=true;
                    pauseButton.setText("Resume");
                }
                else
                {
                    thread.resume();
                    paused=false;
                    pauseButton.setText("Pause");
                }
            }

            private void setColor(Color color) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        add(pauseButton);
       
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/Untitled.png");
        background = loader.loadImage("/GrassBackground.png");
        
        //trying
        army = new Army(10,10,handler,ObjectId.Army);
        army2 = new Army(20,20,handler,ObjectId.Army);
        list = new ArrayList<Army>();
        list.add(army);
        list.add(army2);
        player = new Player(new Nation(10,20,30),list, false);
        
        
        LoadImageLevel(level);
        
        
    }
    @Override
    public void run() 
    {
        init();
        long lastTime = System.nanoTime();
        long llTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        
        
        while(running)
        {
            
            for (Building listB1 : listB) {
                if (listB1.isClicked()) {
                    if (clickCount<2) {
                        actionBuildings.add(listB1);
                        clickCount++;
                        listB1.setClicked(false);
                    }
                }
                if(clickCount==2)
                {
                    actionBuildings.get(0).moveToBase( actionBuildings.get(1));
                    JLabel label =actionBuildings.get(0).getArmy();
                    movingLabels.add(label);
                    movingArmies.add(actionBuildings.get(0).getArmy());
                    add(label);
                    actionBuildings.remove(0);
                    actionBuildings.remove(0);
                    clickCount=0;
                }
                //System.out.println("clickCount: "+clickCount);
            }
           
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            while(delta >= 1){
                    tick();
                    updates++;
                    delta--;
                     
            }
            long del =(now - llTime);
            if(del >=5*ns*50)
            {
                listB.get(0).increaseArmySize();
                System.out.println("Type: "+ listB.get(0).getBuildingType()+ " Size: " + listB.get(0).getArmy().getArmySize());
                llTime = now;
                
                
                for(int i=0;i<movingArmies.size();i++)
                {
                    JLabel label = movingLabels.get(i);
                    Army army = movingArmies.get(i);

                    label.setLocation(army.getSpeed()+label.getX(),army.getSpeed()+label.getY());
                    System.out.println(army.getSpeed());
                    System.out.println(label.getX()+" "+label.getY());
               }
            }
            repaint();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    //System.out.println("FPS: " + frames + " TICKS: " + updates);
                    frames = 0;
                    updates = 0;
            }
        }
    }
    
    
    
    private void tick()
    {
        handler.tick();
    }
    
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        handler.render(g);
        
    }
    /*private void render()
    {
        /*BufferStrategy bs = this.getBufferStrategy();
        if( bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        
        
        Graphics g = bs.getDrawGraphics();
        g.drawImage(background, 0, 0, null);
        /*g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        handler.render(g);
        
        g.dispose();
        bs.show();
    }*/
    
    private void LoadImageLevel(BufferedImage image)
    {
        int w = image.getWidth();
        int h = image.getHeight();
        
        
        
        for(int xx = 0; xx < w; xx++)
        {
            for(int yy = 0; yy < h; yy++)
            {
                
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                
                if(red == 76 && green == 255 && blue == 0) 
                {
                    handler.addObject(new GreenTree(xx*32, yy*32, handler, ObjectId.GreenTree));
                }
                if(red == 255 && green == 0 && blue == 59)
                {
                    Building b = new Building(xx*32, yy*32,handler, ObjectId.Building, army, player, "enemyArsenal",false);
                    handler.addObject(b);
                    listB.add(b);
                    b.setBounds(xx*32, yy*32, 32, 32);
                    b.setText("Here");
                    this.add(b);
                    
                }
                if(red==22 && green == 34 && blue == 255){
                    Building b = new Building(xx*32, yy*32,handler, ObjectId.Building, army2, player, "playerHospital",false);
                    handler.addObject(b);
                    listB.add(b);
                    b.setText("Here");
                    b.setBounds(xx*32, yy*32, 32, 32);
                    this.add(b);
                    
                    
                }
            }
        }
    }
    
    public Handler getHandler() {
        return handler;
    }
    
}
