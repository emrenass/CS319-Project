/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author emre
 */

public class WinLabel extends JLabel {
    BufferedImage image;
    BufferedImageLoader loader = new BufferedImageLoader();
    int width = 300;
    int height= 269;
    int times = 0;
    Timer timer;
    
    
    public WinLabel(){
        super();
        loadWinImage();
        
    }
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0,width, height, null);
        g.dispose();
    }
    
    public void loadWinImage(){
        image = loader.loadImage("/youwin.png");
    }
    
    public void animateImageLogic(){
        times++;
        if(times<=5){
            width +=10;
            height +=10;
        }
        if (times>5){
            width -=10;
            height -=10;
            
            if(times==10){
                times = 0;
            }
        }
        repaint();
    }
    
    public void startAnimate(){
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animateImageLogic();
            }
        });
        timer.start();
    }
    
    public void stopAnimate(){
        timer.stop();
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }

}
