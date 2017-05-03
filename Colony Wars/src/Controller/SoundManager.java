package Controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class SoundManager implements Runnable
{
    private boolean running = false;
    private Thread thread;
    public boolean playSong = false;
    private long clipTime;
    
    public SoundManager()
    {
    }


    public boolean isPlaySong() {
        return playSong;
    }
    public String url;
    public Clip clip, clip1;


   public synchronized void Start()
    {
        if(running)
            return;
        
        playSong = true;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {         
        
        try
        { 
          //URL defaultSound = this.getClass().getResourceAsStream("/Sound/deneme.wav"); 
            System.out.println(getClass().getResource("/Sounds/Intro.wav"));
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Sounds/Intro.wav"));
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();

        } 
          catch (Exception ex) 
        {ex.printStackTrace();}

        }


    public void Pause()
    {
        playSong = false;
        clipTime= clip.getMicrosecondPosition();
        clip.stop();

    }
    
    public void Resume()
    {
        playSong = true;
        clip.start();

    }
    
    public void setVolume(int number)
    {
        FloatControl gainControl = 
            (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(number);
    }
    
    public void setEffect(String url)
    {
        try
        { 
          //URL defaultSound = this.getClass().getResourceAsStream("/Sound/deneme.wav"); 
          AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/Sounds/" + url));
          clip1 = AudioSystem.getClip();
          clip1.open(audioInputStream1);     
          clip1.start();

        } 
          catch (Exception ex1) 
        {ex1.printStackTrace();}

    }
        
    
}