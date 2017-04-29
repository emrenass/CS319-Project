/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author emre
 */
public class TimeLabel extends JLabel {

    long timeCount = 0;
    Date startDate = new Date();
    int min = 0;
    final float fontSize = 20f;
    Timer timer;
    Boolean stoped = false;
    public TimeLabel() {
        try {
            startTime();
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/Fonts/zorque.ttf").openStream());
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(font);
            font = font.deriveFont(fontSize);
            setFont(font);
        } catch (Exception ex) {
            Logger.getLogger(TimeLabel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void startTime(){
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                timeCount++;
                Date endDate = new Date();
                int seconds = (int)((endDate.getTime() - startDate.getTime()) / 1000);
                
                if(seconds == 60) {
                    seconds = 0;
                    min++;
                    startDate =new Date();
                }
                
                if(stoped) {
                    startDate =new Date();
                }
                
                setText(min + ":" + seconds);
            }
        });
        timer.start();
    }
    
    public void stopTime(){
        timer.stop();
    }
    
    public void setStoped(boolean stoped) {
        this.stoped = stoped;
    }

}
