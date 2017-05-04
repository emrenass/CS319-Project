package Controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    public String url;
    public Clip clip, clip1;
    public long clipTime;
    
    public SoundManager()
    {
    }


    public boolean isPlaySong() {
        return playSong;
    }
    


   public synchronized void Start()
    {
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        playSong = true;
        thread.start();
    }

    @Override
    public void run()
    {         
        
        try
        { 
          //URL defaultSound = this.getClass().getResourceAsStream("/Sound/deneme.wav"); 
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/Sounds/Intro.wav"));
          DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
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
        clipTime = clip.getMicrosecondPosition();
        clip.stop();
        playSong = false;

    }
    
    public void Resume()
    {
        clip.setMicrosecondPosition(clipTime);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        playSong = true;

    }
    
    public void setVolume(float number)
    {
        if (number < 0f || number > 1f)
        throw new IllegalArgumentException("Volume not valid: " + number);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
    gainControl.setValue(20f * (float) Math.log10(number));
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