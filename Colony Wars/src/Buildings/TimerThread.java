/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

import PlayerPack.Army;

/**
 *
 * @author taha
 */
public class TimerThread  extends Thread{
   private Army army;

    public TimerThread(Army army, ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        this.army = army;
    }
   
    public void run()
    {
        army.setSize(army.getArmySize()+army.getProductionRate());
        System.out.println(army.getArmySize());
    }
}
