package Controller;

import View.BufferedImageLoader;
import View.GameView;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Map {
    protected BufferedImage image;
    protected int mapNo;
    private ArrayList<GameObject> objectList;
    GameView gw;
    protected int width,height;
    Handler handler;
    ArrayList<Building> listB;
    Player player,AI;

    public Map(int mapNo,GameView gw, Handler handler, ArrayList<Building> listB,Player player, Player AI) {
        this.mapNo = mapNo;
        this.handler = handler;
        this.gw=gw;
        this.listB = listB;
        listB.removeAll(listB);
        this.player = player;
        this.AI = AI;
        
        BufferedImageLoader loader = new BufferedImageLoader();
        String mapName= "/Map"+this.mapNo+".png";
        image=loader.loadImage(mapName);
        
       LoadImageLevel(image);
    }
  
    
    public void LoadImageLevel(BufferedImage image)
    {
        
        int w = image.getWidth();
        int h = image.getHeight();
        
        
        
        for(int xx = 0; xx < w; xx++)
        {
            for(int yy = 0; yy < h; yy++)
            {
                Army army = new Army(xx*32,yy*32,handler,ObjectId.Army);
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                
                if(red == 76 && green == 255 && blue == 0) 
                {
                    handler.addObject(new GreenTree(xx*32, yy*32, handler, ObjectId.GreenTree));
                }
                if(red == 255 && green == 0 && blue == 59)
                {
                    //set army properties according to nation of the player here
                    army.setPlayer(AI);
                    army.setProperties(AI.getNation());
                    Building b = new Building(xx*32, yy*32,handler, ObjectId.Building, army, AI, "enemyArsenal",false);
                    b.setBounds(xx*32, yy*32, 32, 32);
                    handler.addObject(b);
                    listB.add(b);
                    gw.add(b);
                }
                if(red == 255 && green == 61 && blue == 213)
                {
                    //set army properties according to nation of the player here
                    army.setPlayer(player);
                    army.setProperties(player.getNation());
                    Building b = new Building(xx*32, yy*32,handler, ObjectId.Building, army, player, "playerArsenal",false);
                    b.setBounds(xx*32, yy*32, 32, 32);
                    handler.addObject(b);
                    listB.add(b);
                    gw.add(b);
                }
                if(red==22 && green == 34 && blue == 255){
                    //set army properties according to nation of the player here
                    army.setPlayer(player);
                    army.setProperties(player.getNation());
                    Building b = new Building(xx*32, yy*32,handler, ObjectId.Building, army, player, "playerHospital",false);
                    b.setBounds(xx*32, yy*32, 32, 32);
                    handler.addObject(b);
                    listB.add(b);
                    gw.add(b);
                }
                if(red==50 && green ==255 && blue == 200){
                    //set army properties according to nation of the player here
                    army.setPlayer(AI);
                    army.setProperties(AI.getNation());
                    Building b = new Building(xx*32, yy*32,handler, ObjectId.Building, army, AI, "enemyHospital",false);
                    b.setBounds(xx*32, yy*32, 32, 32);
                    handler.addObject(b);
                    listB.add(b);
                    gw.add(b);
                }
                
                if(red==59 && green == 255&& blue ==56 ){
                    //set army properties according to nation of the player here
                    army.setPlayer(AI);
                    army.setProperties(AI.getNation());
                    Building b = new Building(xx*32, yy*32,handler, ObjectId.Building, army, AI, "enemyTailor",false);
                    b.setBounds(xx*32, yy*32, 32, 32);
                    handler.addObject(b);
                    listB.add(b);
                    gw.add(b);
                }
                
                if(red==255&& green ==191 && blue == 96){
                    //set army properties according to nation of the player here
                    army.setPlayer(player);
                    army.setProperties(player.getNation());
                    Building b = new Building(xx*32, yy*32,handler, ObjectId.Building, army, player, "playerTailor",false);
                    b.setBounds(xx*32, yy*32, 32, 32);
                    handler.addObject(b);
                    listB.add(b);
                    gw.add(b);
                }
            }
        }
    }
    

}