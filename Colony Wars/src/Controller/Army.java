/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Emre
 */
public class Army extends GameObject
{
    
    private int width = 100;
    private int height = 100;
    private int size,speed,ID;
    private double damage=1;
    private int productionRate;
    private Player player;
    private Nation nation;
    private Building targetBuilding=null;
    private BufferedImage armyImage;
    public Army(float x, float y, Handler handler, ObjectId id) {
        super (x, y, handler, id);
        ImageIcon icon = new ImageIcon(getClass().getResource("/Soldier.png"));
        setIcon(icon);
        
        setBounds((int)x, (int)y, width, height);
    }
    
    public Army copy()
    {
        Army army = new Army(this.x,this.y,this.handler,this.id);
        army.size = size;
        army.speed = speed;
        army.damage = damage;
        army.productionRate= productionRate;
        army.player = player;
        army.nation = nation;
        return army;
    }
    public boolean move()
    {
        
        // move on x axis
        float rightMove=(targetBuilding.getLocX()-x > speed) ? speed : (targetBuilding.getLocX()-x);
        float leftMove= (x-targetBuilding.getLocX()> speed) ? speed : (x-targetBuilding.getLocX());;
        if(x!=targetBuilding.getLocX())
            x += (x>targetBuilding.getLocX()) ? (-leftMove) : (rightMove);
        
        // move on y axis
        float upMovey=(targetBuilding.getLocY()-y > speed) ? speed : (targetBuilding.getLocY()-y);
        float downMovey= (y-targetBuilding.getLocY()> speed) ? speed : (y-targetBuilding.getLocY());;
        if(y!=targetBuilding.getLocY())
            y += (y>targetBuilding.getLocY()) ? (-downMovey) : (upMovey);
        
        return (x==targetBuilding.getLocX() && y== targetBuilding.getLocY());
        
    }
    
    @Override
    public void tick(ArrayList<GameObject> object) 
    {
        x += velX;
        y += velY;
    }
    
    public void collision(ArrayList<GameObject> object)
    {
        for(int i = 0; i<handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject == targetBuilding && tempObject!=null)//Bu çalışmayabilir , equeals methodu falan
            {
                if(getBounds().intersects(tempObject.getBounds()))
                        velY=0;
                        velX=0;
            }
        }
    }
    
    public void loadImage()
    {
        try
        {
            armyImage = ImageIO.read(getClass().getResource("/Adsiz.png"));
        } catch (IOException ex)
        {
            Logger.getLogger(GreenTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void render(Graphics g) 
    {
        g.drawImage(armyImage, (int)x, (int)y, null);
        
    }

    public int getProductionRate() {
        return productionRate;
    }

    public void setProductionRate(int productionRate) {
        this.productionRate = productionRate;
    }
    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArmySize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public Building getTargetBuilding() {
        return targetBuilding;
    }

    public void setTargetBuilding(Building targetBuilding) {
        this.targetBuilding = targetBuilding;
    }

    public BufferedImage getArmyImage() {
        return armyImage;
    }

    public void setArmyImage(BufferedImage armyImage) {
        this.armyImage = armyImage;
    }

    public void collide() throws IOException {
        targetBuilding.CollideArmy(this);
        
    }

    public void setProperties(Nation nation) {
        //army image could be specific to nation assign the image here!!
       this.nation=nation;
       setSpeed(nation.getUnitSpeed());
       setDamage(nation.getUnitDamage());
       setProductionRate(nation.getUnitProductionRate());
    }

}
