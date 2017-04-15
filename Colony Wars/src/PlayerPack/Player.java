/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayerPack;

import java.util.ArrayList;

/**
 *
 * @author taha
 */
public class Player {
    private Nation nation;
    private ArrayList<Army> list;
    private boolean isAI;

    public Player(Nation nation, ArrayList<Army> list, boolean isAI) {
        this.nation = nation;
        this.list = list;
        this.isAI = isAI;
    }
    public void initializeAttributes()
    {
        for(int i=0;i<list.size();i++)
        {
            list.get(i).setSpeed(nation.getUnitSpeed());
            list.get(i).setDamage(nation.getUnitDamage());
            list.get(i).setProductionRate(nation.getUnitProductionRate());
        }
    }
    
    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public ArrayList<Army> getList() {
        return list;
    }

    public void setList(ArrayList<Army> list) {
        this.list = list;
    }

    public boolean isIsAI() {
        return isAI;
    }

    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }
    
    
}
