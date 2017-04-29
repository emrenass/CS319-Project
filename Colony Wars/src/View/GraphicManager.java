/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.SavedData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author emre
 */
public class GraphicManager extends javax.swing.JFrame {

    /**
     * Creates new form GraphicManager
     */
    SavedData save;
    public GraphicManager() {
        
        initComponents();
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newGameButton = new javax.swing.JButton();
        loadGameButton = new javax.swing.JButton();
        soundSettingsButton = new javax.swing.JButton();
        creditsButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        tutorialButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        newGameButton.setText("New Game");
        newGameButton.setMaximumSize(new java.awt.Dimension(111, 32));
        newGameButton.setMinimumSize(new java.awt.Dimension(111, 32));
        newGameButton.setPreferredSize(new java.awt.Dimension(111, 32));
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });
        getContentPane().add(newGameButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 140, -1));

        loadGameButton.setText("Load Game");
        loadGameButton.setMaximumSize(new java.awt.Dimension(111, 32));
        loadGameButton.setMinimumSize(new java.awt.Dimension(111, 32));
        loadGameButton.setPreferredSize(new java.awt.Dimension(111, 32));
        loadGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGameButtonActionPerformed(evt);
            }
        });
        getContentPane().add(loadGameButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 140, -1));

        soundSettingsButton.setText("Sound Settings");
        soundSettingsButton.setMaximumSize(new java.awt.Dimension(111, 32));
        soundSettingsButton.setMinimumSize(new java.awt.Dimension(111, 32));
        soundSettingsButton.setPreferredSize(new java.awt.Dimension(111, 32));
        soundSettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soundSettingsButtonActionPerformed(evt);
            }
        });
        getContentPane().add(soundSettingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 140, -1));

        creditsButton.setText("Credits");
        creditsButton.setMaximumSize(new java.awt.Dimension(111, 32));
        creditsButton.setMinimumSize(new java.awt.Dimension(111, 32));
        creditsButton.setPreferredSize(new java.awt.Dimension(111, 32));
        creditsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditsButtonActionPerformed(evt);
            }
        });
        getContentPane().add(creditsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 140, -1));

        exitButton.setText("Exit");
        exitButton.setMaximumSize(new java.awt.Dimension(111, 32));
        exitButton.setMinimumSize(new java.awt.Dimension(111, 32));
        exitButton.setPreferredSize(new java.awt.Dimension(111, 32));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 140, -1));

        tutorialButton2.setText("Tutorial");
        tutorialButton2.setMaximumSize(new java.awt.Dimension(111, 32));
        tutorialButton2.setMinimumSize(new java.awt.Dimension(111, 32));
        tutorialButton2.setPreferredSize(new java.awt.Dimension(111, 32));
        tutorialButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutorialButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(tutorialButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 140, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wallpaper.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void creditsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditsButtonActionPerformed
        CreditsWindow credits = new CreditsWindow();
        credits.copyFrameMain(this);
        credits.copyContentPaneMain(getContentPane());
        setContentPane(credits);

        this.invalidate();
        this.validate();
    }//GEN-LAST:event_creditsButtonActionPerformed

    private void soundSettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soundSettingsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soundSettingsButtonActionPerformed

    private void loadGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGameButtonActionPerformed
        LoadGameWindow loadGame = new LoadGameWindow();
        loadGame.copyFrameMain(this);
        loadGame.copyContentPaneMain(getContentPane());
        setContentPane(loadGame);

        this.invalidate();
        this.validate();
    }//GEN-LAST:event_loadGameButtonActionPerformed

    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed
        boolean enter = true;
        File file = new File("Save.cw");
        if(file.exists()){
            final String message = 
                "There is a saved game.\n"
                + "You will lose your previous game.\n"
                +"Do you want to continue?";
            int reply = JOptionPane.showConfirmDialog(this, message,"Warning", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if(reply==JOptionPane.YES_OPTION)
                System.out.println("Okay");
            if(reply==JOptionPane.NO_OPTION)
                enter = false;
                
        }
        
        if(enter)
        {
            try{
                FileOutputStream fos = new FileOutputStream("Save.cw");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                save = new SavedData(1);
                oos.writeObject(save);
                oos.close();
            }
            catch (IOException ex) {
                Logger.getLogger(GraphicManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            GameView game = new GameView();
            game.copyFrameMain(this);
            game.copyContentPaneMain(getContentPane());
            game.Start();
            setContentPane(game);

            this.invalidate();
            this.validate();
        }
    }//GEN-LAST:event_newGameButtonActionPerformed

    private void tutorialButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutorialButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tutorialButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton creditsButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loadGameButton;
    private javax.swing.JButton newGameButton;
    private javax.swing.JButton soundSettingsButton;
    private javax.swing.JButton tutorialButton2;
    // End of variables declaration//GEN-END:variables

public static void main(String args[]) {
    
        if(System.getProperty("os.name").startsWith("Win"))
            System.setProperty("sun.java2d.d3d","true");
        else
            System.setProperty("sun.java2d.opengl", "true");
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();
        try{
            //0-Swing, 1-Mac, 2-?, 3-Windows, 4-Old Windows
            UIManager.setLookAndFeel(looks[1].getClassName());
        
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraphicManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphicManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphicManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphicManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        /* Create and display the form */
                new GraphicManager().setVisible(true);
                
                JFrame frame = new JFrame();
        JLabel label1 = new JLabel("!!! For AI Testing you will always lose I made enemyArsenal damage (*20) so change it before coding");
        JLabel label2 = new JLabel("!!! Read the comment for decideAttack in player class for AI");
        JLabel label3 = new JLabel("!!! AI cant send true number of soldier for an unkown reason");
        label1.setForeground(Color.red);
        label2.setForeground(Color.blue);
        label3.setForeground(Color.magenta);
        frame.setLayout(null);
        frame.setSize(800, 600);
        label1.setBounds(0,0,800, 50);
        label2.setBounds(0,100,800, 50);
        label3.setBounds(0,200,800, 50);
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.show();
                
                /*JFrame frame = new JFrame();
                WinLabel lbl = new WinLabel();
                lbl.startAnimate();
                frame.setSize(600, 600);
                frame.add(lbl);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.show();*/
    }

}
