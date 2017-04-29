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
import javax.swing.JPanel;

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
    GameView game;
    
    public WinLabel(){
        super();
        loadWinImage();
        
    }
    
    public WinLabel(GameView game){
        super();
        loadWinImage();
        this.game = game;
        
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
        int numberOfResize = 5;
        if(times<=numberOfResize){
            width +=10;
            height +=10;
        }
        if (times>numberOfResize){
            width -=10;
            height -=10;
            
            if(times==numberOfResize*2){
                times = 0;
            }
        }
        repaint();
        game.repaint();
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
