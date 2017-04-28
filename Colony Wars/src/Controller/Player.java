/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;

/**
 *
 * @author taha
 */
public class Player {
    private Nation nation;
    private ArrayList<Army> list = new ArrayList<Army>();
    private boolean isAI;

    public Player(Nation nation, boolean isAI) {
        this.nation = nation;
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
    
    public Building decideAttack(ArrayList<Building> build){
        ArrayList<Building> aiBase = new ArrayList<Building>();
        ArrayList<Building> playerBase = new ArrayList<Building>();
        for(Building builds : build) {
            if(builds.getPossessor().isAI()){
                aiBase.add(builds);
            }
            else {
                playerBase.add(builds);
            }
                
        }
        Building returnBuild = null;
            for(Building aiBuild : aiBase)
                for(Building posBuild : playerBase){
                    if(aiBuild.getArmy().getArmySize()*aiBuild.getPossessor().getNation().getUnitDamage() > 
                            posBuild.getArmy().getArmySize()*posBuild.getPossessor().getNation().getUnitDamage()) {
                        aiBuild.moveToBaseAI(posBuild);
                        aiBuild.getArmy().setTargetBuilding(posBuild);
                        returnBuild = aiBuild;
                    }
                        
        }
            return returnBuild;
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

    public boolean isAI() {
        return isAI;
    }
    
    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }
    public String toString()
    {
        if(isAI)
            return "AI";
        else 
            return "Player";
    }
    
}
