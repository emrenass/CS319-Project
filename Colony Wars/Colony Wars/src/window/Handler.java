/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window;


import Buildings.Building;
import PlayerPack.GreenTree;
import framework.GameObject;
import framework.ObjectId;
import static framework.ObjectId.Building;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Emre
 */
public class Handler
{
    public ArrayList<GameObject> object = new ArrayList<GameObject>();
    private GameObject tempObject;
    
    public void tick()
    {
        for(int i = 0; i<object.size(); i++)
        {
            tempObject = object.get(i);
            tempObject.tick(object);
            
        }
        
            
            
    }
    
    public void render(Graphics g)
    {
        for(int i = 0; i<object.size(); i++)
        {
            tempObject = object.get(i);
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object)
    {
        this.object.add(object);
    }
    
    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }
            
}
