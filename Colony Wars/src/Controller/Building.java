/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author taha
 */
public class Building extends GameObject implements MouseListener{
    
    
    private Army army;
    private Player possessor;
    private String buildingType;
    private boolean isIdle;
    private boolean clicked = false;
    Timer timer = new Timer();
    private BufferedImage buildingImage;
    Border blueBorder = BorderFactory.createLineBorder(Color.blue,5, true);
    Border redBorder = BorderFactory.createLineBorder(Color.red,5);
    public Building(float x, float y, Handler handler, ObjectId id,Army army, Player possessor, String buildingType,boolean isIdle) {
        super(x, y, handler, id);
        this.possessor = possessor;
        
        this.army=army;
        this.isIdle= isIdle;
        this.buildingType=buildingType;
        loadImage();
        setSize(32,32);
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Color.yellow);
        addMouseListener(new MouseAdapter(){
                        @Override
			public void mousePressed(MouseEvent me){
                            if(clicked==false){
                                clicked = true;
                                System.out.println(clicked);
                            }
                            else{
                                clicked = false;
                                System.out.println("Else girdi");
                            }
			}
		});
        buildingChecker();
        
    }
    public void addBorder(){
        setBorder(blueBorder);
    }
    public void removeBorder(){
        setBorder(BorderFactory.createEmptyBorder());
    }
    public void increaseArmySize()
    {
        
        army.setSize(army.getArmySize()+army.getProductionRate());
        setText(String.valueOf(army.getArmySize()));
    }
    public Army moveToBase(Building building){
        Army army2= army.copy();//moving army
        army.setSize(army.getArmySize()/2);
        army2.setSize(army2.getArmySize()/2);
        
        ArrayList<Army> temp = possessor.getList();
        temp.add(army2);
        possessor.setList(temp);
        
        //both armies have the same id!!!
        System.out.println("Source: "+ this.buildingType+ "Target: "+ building.getBuildingType());
        army2.setTargetBuilding(building);
        return army2;
    }
    
    public Army moveToBaseAI(Building building){
        Army army2= army.copy();//moving army
        army.setSize(army.getArmySize()/2);
        army2.setSize(army2.getArmySize()/2);
        
        ArrayList<Army> temp = possessor.getList();
        temp.add(army2);
        possessor.setList(temp);
        
        //both armies have the same id!!!
        System.out.println("Source: "+ this.buildingType+ "Target: "+ building.getBuildingType());
        army2.setTargetBuilding(building);
        return army2;
    }
    
    

    public void buildingChecker()
    {
        this.increaseDamage();
        this.increaseProduction();
        this.increaseSpeed();
    }
    public void increaseSpeed(){
        if((buildingType.equalsIgnoreCase("playerTailor")||buildingType.equalsIgnoreCase("enemyTailor")) && !isIdle)
        {
            army.setSpeed(army.getSpeed()+10);//10 DEFAULT VALUE DEGISTIR
        }
    }
    public void increaseProduction(){
        if((buildingType.equalsIgnoreCase("playerHospital")||buildingType.equalsIgnoreCase("enemyHospital")) && !isIdle)
        {
            army.setProductionRate(army.getProductionRate()+10);//10 DEFAULT VALUE DEGISTIR
        }
    }
    public void increaseDamage(){
        if((buildingType.equalsIgnoreCase("playerArsenal")||buildingType.equalsIgnoreCase("enemyArsenal")) && !isIdle)
        {
            army.setDamage(army.getDamage()+0.5);//0.5 DEFAULT VALUE DEGISTIR
        }
    }
    @Override
    public void tick(ArrayList<GameObject> object) {
       
    }

    @Override
    public void render(Graphics g) 
    {
       g.drawImage(buildingImage, (int)x, (int)y, null);
    }
    
    public void loadImage()
    {
        try {
            buildingImage = ImageIO.read(getClass().getResource("/"+ buildingType +".png"));
        } catch (IOException ex) {
            Logger.getLogger(Building.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    
    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, 128, 128);
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public Player getPossessor() {
        return possessor;
    }

    public void setPossessor(Player possessor) {
        this.possessor = possessor;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public boolean isIsIdle() {
        return isIdle;
    }

    public void setIsIdle(boolean isIdle) {
        this.isIdle = isIdle;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public BufferedImage getEnemyArsenal() {
        return buildingImage;
    }

    public void setEnemyArsenal(BufferedImage enemyArsenal) {
        this.buildingImage = enemyArsenal;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void CollideArmy(Army incomingArmy) throws IOException {
        //fight
        if(incomingArmy.getPlayer() != possessor)
        {
            if(incomingArmy.getArmySize()*incomingArmy.getDamage()>army.getArmySize()*army.getDamage())
            {
                possessor = incomingArmy.getPlayer();
                incomingArmy.setSize ((int)(((incomingArmy.getArmySize()*incomingArmy.getDamage()-army.getArmySize()*army.getDamage()))/(incomingArmy.getDamage())));
                this.setArmy(incomingArmy);
                
                //The building image should change here
                if(possessor.toString().equalsIgnoreCase("player")) {
                    /**if(buildingType.contains("Arsenal")){
                        buildingType= "playerArsenal";
                        * */
                        buildingType=buildingType.replaceAll("enemy", "player");
                                
                        loadImage();
                        
                    //}   
                    
                }
                else //(possessor.toString().equalsIgnoreCase("AI")) {
                {
                    /**if(buildingType.contains("Hospital")){
                        buildingType= "enemyHospital";
                        loadImage();
                }
                */
                    System.out.println("The image will change now!");
                    buildingType = buildingType.replaceAll("player", "enemy");
                    loadImage();
                }
            }
            else
            {
                army.setSize ((int)((army.getArmySize()*army.getDamage()-incomingArmy.getArmySize()*incomingArmy.getDamage()) / army.getDamage()));
                System.out.println(army.getArmySize());
            }
        }
        //support
        else
        {
            army.setSize(army.getArmySize()+incomingArmy.getArmySize());
        }
            
    }
    
}  
