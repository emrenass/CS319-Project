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
import Controller.Objective;
import Controller.Player;
import Model.SavedData;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * bu bir kalkismadir
 * @author Emre & Taha 
 */
public class GameView extends JPanel implements Runnable 
{

   
    private JButton pauseButton = new JButton();

    
    private boolean paused = false;
    private JFrame frame;
    private Container container;
    private boolean running = false;
    private Thread thread;

    private BufferedImage level = null;
    private BufferedImage background = null;
    Handler handler = new Handler();
    private int clickCount = 0;
    private int levelNo=1;
    //trying
    private String gameMode="Skirmish";
    private int difficultyLevel = 1;
    private NationType playerNation = NationType.Doth;
    JLabel label;
    ArrayList<Building> actionBuildings = new ArrayList<Building>();
    ArrayList<Building> listB = new ArrayList<Building>();
    //Army army,army2;
    ArrayList<Army> list;
    ArrayList<Army> movingArmies = new ArrayList<Army>();
    ArrayList<JLabel> movingLabels = new ArrayList<JLabel>();
    Player player;
    Player AI;
    PauseMenu pause;
    WinLabel winlabel;
    TimeLabel timeLabel;
    
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    
    public static int WIDTH, HEIGHT;
    
    public GameView(){
        
    }
    
    public GameView(int levelNo,String gameMode, int difficultyLevel, NationType playerNation){
        this.levelNo=levelNo;
        this.gameMode = gameMode;
        this .difficultyLevel=difficultyLevel;
        this.playerNation = playerNation;
    }
    public GameView(String gameMode, int difficultyLevel, NationType playerNation){
        this.gameMode = gameMode;
        this .difficultyLevel=difficultyLevel;
        this.playerNation = playerNation;
    }
    
    public synchronized void Start()
    {
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    
    
    public void init()
    {
        removeAll();
        setLayout(null);
        timeLabel = new TimeLabel();
        timeLabel.setBounds(730,10,50,20);
        add(timeLabel);
        pause = new PauseMenu(frame, this, timeLabel);
        pause.setBounds(300, 100, 200, 400);
        
        WIDTH = getWidth();
        HEIGHT = getHeight();
        
        
        pauseButton.setBounds(10, 10, 32, 32);
        
        try{
            pauseButton.setIcon(new ImageIcon(getClass().getResource("/pauseButtonIcon.png")));
            pauseButton.setContentAreaFilled(false);
        }
        catch(Exception e) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, e);
            pauseButton.setBounds(10, 10, 84, 32);
            pauseButton.setText("Pause");
        }
        pauseButton.setMargin(new Insets(0, 0, 0, 0));
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    timeLabel.stopTime();
                    timeLabel.setStoped(true);
                    add(pause);
                    validate();
                    repaint();
                    thread.suspend();
                    pauseButton.setVisible(false);
            }
        });
        add(pauseButton);
       
        BufferedImageLoader loader = new BufferedImageLoader();
        background = loader.loadImage("/GrassBackground.png");
        
        //trying
        AI = new Player(new Nation(NationType.Cult),true);
        player = new Player(new Nation(playerNation), false);
        
       
        ResetAllLists();
        
        revalidate();
        repaint();
        Map map = new Map(levelNo++,this, handler,listB,player,  AI);
        
    }
    
    
    /*
    The run method has been cleared with created 3 methods which are (update: use it for non physical objects
    fixedUpdate: for physical objects and gameOver: is the logic that execute for end of the game)
    */
    @Override
    public void run() 
    {
        init();
        long lastTime = System.nanoTime();
        long llTime = System.nanoTime();
        long runTime = System.nanoTime();
        long secTime = System.nanoTime();
        
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        
        
        while(running)
        {
           long now = System.nanoTime();
           update();
            
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            while(delta >= 1){
                    tick();
                    updates++;
                    delta--;
                     
            }
            //5 sec loop
            long del =(now - llTime);
            //1 sec loop
            long secDel = (now - secTime);
            if(secDel >=ns*5)
            {
                fixedUpdate();
                secTime = now;
                
            }
            
            repaint();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    //System.out.println("FPS: " + frames + " TICKS: " + updates);
                    frames = 0;
                    updates = 0;
            }
            
            if(del >=5*ns*50)
            {
                llTime = now;
                gameOver();
                AIAction();
            }
            
        }
    }
    
    public void update(){
        for (Building listB1 : listB) {
                if (listB1.isClicked()) {
                    if (clickCount<2) {
                        
                        clickCount++;
                        if(clickCount == 1 && listB1.getPossessor().isAI()){
                            clickCount = 0;
                            listB1.setClicked(false);
                            break;
                        }
                        actionBuildings.add(listB1);
                        listB1.setClicked(false);
                        listB1.addBorder();
                        System.out.println("Click girdi");
                    }
                }
                if(clickCount==2)
                {
                    if(actionBuildings.get(0).getArmy().getArmySize()>0){
                        Army movingArmy = actionBuildings.get(0).moveToBase( actionBuildings.get(1));
                        JLabel label =movingArmy;
                        movingLabels.add(label);
                        movingArmies.add(movingArmy);
                        add(label);
                    }
                    actionBuildings.get(actionBuildings.size()-1).removeBorder();
                    actionBuildings.get(actionBuildings.size()-2).removeBorder();
                    actionBuildings.remove(0);
                    actionBuildings.remove(0);
                    clickCount=0;
                }
                //System.out.println("clickCount: "+clickCount);
            }
        
        /*The method decideAttack called here if the decided is not null it uses the same logic
        to move it's armies same with player logic        
        */
        /*
       ArrayList<Building> decided = AI.decideAttack(difficultyLevel,listB);
       if(decided.size() > 0) {
           for(Building decidedBuilding : decided) {
            Army movingArmy = decidedBuilding.moveToBaseAI(decidedBuilding.getArmy().getTargetBuilding());
            JLabel label =movingArmy;
            movingLabels.add(label);
            movingArmies.add(movingArmy);
            add(label);
           }
       }
       */
    }
    
    public void fixedUpdate()
    {
        
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
        }
        
    }
    public void AIAction()
    {
        ArrayList<Building> decided = AI.decideAttack(difficultyLevel,listB);
        if(decided.size() > 0) {
           for(Building decidedBuilding : decided) {
            Army movingArmy = decidedBuilding.moveToBaseAI(decidedBuilding.getArmy().getTargetBuilding());
            JLabel label =movingArmy;
            movingLabels.add(label);
            movingArmies.add(movingArmy);
            add(label);
           }
       }
    }
    public void gameOver(){
        //This function will change place
        for(Building builds : listB) {
            builds.increaseArmySize();
        }
        /////////////////////////////
        
        Objective obj = new Objective(levelNo-1,true,listB);
        Player possessor = obj.checkObjectiveStatus();
        
        
        

        if(obj.isObjectiveAchieved()){
            long startWinTime = System.nanoTime();
            long nowWinTime = System.nanoTime();
            boolean started = false;
            winlabel = new WinLabel(this);
            winlabel.setBounds(200, 120, winlabel.getWidth()+50, winlabel.getHeight()+50);
            add(winlabel);
            while(true){
                nowWinTime = System.nanoTime();
                if(!started && nowWinTime - startWinTime <=5*ns*50){
                    winlabel.startAnimate();
                    started = true;
                }
                else if(nowWinTime - startWinTime > 5*ns*50){
                    winlabel.stopAnimate();
                    started = false;
                    break;
                }
            }
            System.out.println( possessor.toString()+"won");

            /*
            This part should change when we add AI
            */
            try{

                FileInputStream fis = new FileInputStream("Save.cw");
                ObjectInputStream ois = new ObjectInputStream(fis);
                SavedData result = (SavedData) ois.readObject();
                ois.close();
                result.setCurrentLevel(levelNo);
                FileOutputStream fos = new FileOutputStream("Save.cw");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(result);
                oos.close();
            }
            catch (IOException ex) {
                Logger.getLogger(GraphicManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(gameMode.equals("campaign")){
                removeAll();
                run();
            }
            else 
                  System.exit(0);//go to main menu here
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

    private void ResetAllLists() {
        handler = new Handler();
        actionBuildings = new ArrayList<Building>();
        listB = new ArrayList<Building>();
        list = new ArrayList<Army>();
        movingArmies = new ArrayList<Army>();
        movingLabels = new ArrayList<JLabel>();
        clickCount=0;
    }
    
    
    public void copyFrameMain(JFrame frame){
        this.frame = frame;
    }
    
    public void copyContentPaneMain(Container container){
        this.container = container;
    }
    
    public Thread getThread() {
        return thread;
    }
    
    public JButton getPauseButton() {
        return pauseButton;
    }
    
    public Container getContainer() {
        return container;
    }
    
    
}
