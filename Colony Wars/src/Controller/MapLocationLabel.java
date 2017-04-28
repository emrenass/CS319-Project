/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import View.BufferedImageLoader;
import View.GameView;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author emre
 */

public class MapLocationLabel extends JLabel {

    private BufferedImage location;
    BufferedImageLoader loader = new BufferedImageLoader();
    int levelNumber;
    private final String levelString = "Level ";
    GameView gw;

    
    
    public MapLocationLabel(){
        //setSize(new Dimension(64, 104));
        //setBorder(BorderFactory.createLineBorder(Color.blue,5, true));
        
    }
    
    public void setIconPath(boolean playable){
        if(playable){
            this.location = loader.loadImage("/locationPlayable.png");
        }
        else {
            this.location = loader.loadImage("/location.png");
        }
        repaint();
    }
    
    public MapLocationLabel(int levelNumber)
    {
        this.levelNumber = levelNumber;
        this.location = loader.loadImage("/location.png");
        setText(levelString + String.valueOf(levelNumber));
    }

    
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(location, 0, 0, null);
    }
    
    public GameView getGw() {
        return gw;
    }
    
    public int getLevelNumber() {
        return levelNumber;
    }
}
