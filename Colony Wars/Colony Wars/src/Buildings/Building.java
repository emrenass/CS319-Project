/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;
import PlayerPack.Army;
import PlayerPack.Player;
import framework.GameObject;
import framework.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import window.Handler;

/**
 *
 * @author taha
 */
public class Building extends GameObject implements Runnable{
    
    private Army army;
    private Player possessor;
    private String buildingType;
    private boolean isIdle;
    Timer timer = new Timer();
    private BufferedImage enemyArsenal;
    public Building(float x, float y, Handler handler, ObjectId id,Army army, Player possessor, String buildingType,boolean isIdle) {
        super(x, y, handler, id);
        this.possessor = possessor;
        this.army=army;
        this.isIdle= isIdle;
        loadImage();
        
    }

    public void increaseArmySize()
    {
        
        army.setSize(army.getSize()+army.getProductionRate());
        System.out.println(army.getSize());
    }
    public void moveToBase(Building building){
        Army army2= army.copy();
        army.setSize(army.getSize()/2);
        army2.setSize(army2.getSize()/2);
        
        possessor.getList().add(army2);
        //both armies have the same id!!!
        
        army.setTargetBuilding(building);
    }
    public void buildingChecker()
    {
        this.increaseDamage();
        this.increaseProduction();
        this.increaseSpeed();
    }
    public void increaseSpeed(){
        if(buildingType.equalsIgnoreCase("Tailor") && !isIdle)
        {
            army.setSpeed(army.getSpeed()+10);//10 DEFAULT VALUE DEGISTIR
        }
    }
    public void increaseProduction(){
        if(buildingType.equalsIgnoreCase("Hospital") && !isIdle)
        {
            army.setProductionRate(army.getProductionRate()+10);//10 DEFAULT VALUE DEGISTIR
        }
    }
    public void increaseDamage(){
        if(buildingType.equalsIgnoreCase("Armory") && !isIdle)
        {
            army.setDamage(army.getDamage()+10);//10 DEFAULT VALUE DEGISTIR
        }
    }
    @Override
    public void tick(ArrayList<GameObject> object) {
       
    }

    @Override
    public void render(Graphics g) 
    {
       g.drawImage(enemyArsenal, (int)x, (int)y, null);
    }
    
    public void loadImage()
    {
        try
        {
            enemyArsenal = ImageIO.read(getClass().getResource("/enemyArsenal.png"));
        } catch (IOException ex)
        {
            Logger.getLogger(Building.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        return enemyArsenal;
    }

    public void setEnemyArsenal(BufferedImage enemyArsenal) {
        this.enemyArsenal = enemyArsenal;
    }

    @Override
    public void run() {
        
        try {
            Thread.sleep(1000);
            army.setSize(army.getSize()+army.getProductionRate());
            System.out.println(army.getSize());
        } catch (InterruptedException ex) {
            Logger.getLogger(Building.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
