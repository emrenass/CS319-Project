/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import PlayerPack.GreenTree;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import window.Handler;

/**
 *
 * @author Emre
 */
public abstract class GameObject extends JLabel
{
    protected float x;
    protected float y;
    protected float velX = 0;
    protected float velY = 0;
    protected boolean moveable;
    protected ObjectId id;
    protected Handler handler;
    
    public GameObject(float x, float y, Handler handler, ObjectId id)  
    {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
        
        
    }
    
    public abstract void tick(ArrayList<GameObject> object);
    public abstract void render (Graphics g);
    public abstract Rectangle getBounds();

    
    
    
    public void setX(float x)
    {
        this.x = x;
    }
    public void setY(float y)
    {
        this.y = y;
    }
    
    public float getVelocityX()
    {
        return velX;
    }
    public float getVelocityY()
    {
        return velY;
    }
    public void setVelocityX(float x)
    {
        velX = x;
    }
    public void setVelocityY(float y)
    {
        velY = y;
    }
    public ObjectId getObjectId()
    {
        return id;
    }
    
    public boolean isMoveable()
    {
        return moveable;
    }

    public void setMoveable(boolean moveable)
    {
        this.moveable = moveable;
    }
    
    
    
}
