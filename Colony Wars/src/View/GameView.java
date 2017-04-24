/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Army;
import Controller.Building;
import Controller.GreenTree;
import Controller.Handler;
import Controller.Map;
import Controller.Nation;
import Controller.NationType;
import Controller.ObjectId;
import Controller.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Emre
 */
public class GameView extends JPanel implements Runnable 
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
    //Army army,army2;
    ArrayList<Army> list;
    ArrayList<Army> movingArmies = new ArrayList<Army>();
    ArrayList<JLabel> movingLabels = new ArrayList<JLabel>();
    Player player;
    Player AI;
    
    
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
                    running = false;
                    paused=true;
                    pauseButton.setText("Resume");
                }
                else
                {
                    thread.resume();
                    running = true;
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
        AI = new Player(new Nation(NationType.Cult),true);
        player = new Player(new Nation(NationType.Doth), false);
        
        Map map = new Map(1, this, handler,listB,player,  AI);
        
        
    }
    @Override
    public void run() 
    {
        init();
        long lastTime = System.nanoTime();
        long llTime = System.nanoTime();
        long runTime = System.nanoTime();
        long secTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        
        
        while(running)
        {
            long now = System.nanoTime();
            
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
                    Army movingArmy = actionBuildings.get(0).moveToBase( actionBuildings.get(1));
                    JLabel label =movingArmy;
                    movingLabels.add(label);
                    movingArmies.add(movingArmy);
                    add(label);
                    actionBuildings.remove(0);
                    actionBuildings.remove(0);
                    clickCount=0;
                }
                //System.out.println("clickCount: "+clickCount);
            }
           
            
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            while(delta >= 1){
                    tick();
                    updates++;
                    delta--;
                     
            }
            //5 sec loop
            long del =(now - llTime);
            if(del >=5*ns*50)
            {
                boolean gameOver=true;
                Player possessor = listB.get(0).getPossessor();
                for( Building b :listB){
                gameOver = (possessor == b.getPossessor()) && gameOver;
                b.increaseArmySize();
                System.out.println("Type: "+ b.getBuildingType()+ " Size: " + b.getArmy().getArmySize());
                }
                llTime = now;
                
                if(gameOver){
                    System.out.println( possessor.toString()+"won");
                    break;
                    //put Gameover window here!!
                }
            }
            //1 sec loop
            long secDel = (now - secTime);
            if(secDel >=ns*50)
            {
                //movement of armies
                for(int i=0;i<movingArmies.size();i++)
                {
                    JLabel label = movingLabels.get(i);
                    Army army = movingArmies.get(i);
                    
                    
                    label.setText(""+army.getArmySize());
                    
                    boolean done = army.move();
                    if(done)
                    {
                        try {
                            //collision
                            System.out.println("");
                            army.collide();
                        } catch (IOException ex) {
                            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
                        }
               
                        
                        remove(label);
                        movingArmies.remove(i);
                        movingLabels.remove(i);
                    }
                    label.setLocation((int)army.getLocX(),(int)army.getLocY());
                    //label.setLocation(army.getSpeed()+label.getX(),army.getSpeed()+label.getY());
                    secTime = now;
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
    
    /**
    private void LoadImageLevel(BufferedImage image)
    {
        int w = image.getWidth();
        int h = image.getHeight();
        
        
        
        for(int xx = 0; xx < w; xx++)
        {
            for(int yy = 0; yy < h; yy++)
            {
                Army army = new Army(xx*32,yy*32,handler,ObjectId.Army);
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
                    //set army properties according to nation of the player here
                    army.setPlayer(AI);
                    army.setProperties(AI.getNation());
                    Building b = new Building(xx*32, yy*32,handler, ObjectId.Building, army, AI, "enemyArsenal",false);
                    handler.addObject(b);
                    listB.add(b);
                    b.setBounds(xx*32, yy*32, 32, 32);
                    this.add(b);
                }
                if(red==22 && green == 34 && blue == 255){
                    //set army properties according to nation of the player here
                    army.setPlayer(player);
                    army.setProperties(player.getNation());
                    Building b = new Building(xx*32, yy*32,handler, ObjectId.Building, army, player, "playerHospital",false);
                    handler.addObject(b);
                    listB.add(b);
                    b.setBounds(xx*32, yy*32, 32, 32);
                    this.add(b);
                }
            }
        }
    }
    */
    public Handler getHandler() {
        return handler;
    }
    
}
