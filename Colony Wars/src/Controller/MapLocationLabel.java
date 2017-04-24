/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.BufferedImageLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author emre
 */

public class MapLocationLabel extends JLabel {

    private BufferedImage location;
    BufferedImageLoader loader = new BufferedImageLoader();
    
    public MapLocationLabel(){
        this.location = loader.loadImage("/location.png");
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(location, 0, 0, null);
    }
    
}
