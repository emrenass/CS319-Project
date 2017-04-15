/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayerPack;

import framework.GameObject;
import framework.ObjectId;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import window.Handler;

/**
 *
 * @author Emre
 */
public class GreenTree extends GameObject
{
    
    private BufferedImage tree;
    private int width = 32;
    private int height = 32;

    
    public GreenTree(float x, float y,Handler handler, ObjectId id) {
        super (x, y, handler, id);
        setMoveable(false);
        loadImage();
    }
    

    @Override
    public void tick(ArrayList<GameObject> object) 
    {
        
    }

    public void loadImage()
    {
        try
        {
            tree = ImageIO.read(getClass().getResource("/GreenTree.png"));
        } catch (IOException ex)
        {
            Logger.getLogger(GreenTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void render(Graphics g) 
    {
        g.drawImage(tree, (int)x, (int)y, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, width, height);
    }
    
    
}
