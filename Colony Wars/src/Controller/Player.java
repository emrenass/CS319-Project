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
    
    /*
    decideAttack function called in the GameView class the code basically takes all the buildings in the map
    split it into aiBase and playerBase(idle in the future). In the second loop it compare two arraylist
    in order to find suitable base to attack and if it finds with the given function in the if statement it returns
    that suitable building which can attack player's base. Also it sets it's base's army's targetbuilding to result. In the game view menu it's calling by the
    same method we use for player's base.
    
    This function only for AI.
    */
    public ArrayList<Building> decideAttack(int difficultyLevel,ArrayList<Building> build){
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
         ArrayList<Building> returnBuild = new ArrayList<Building>();
        
        if(difficultyLevel==1){
                for(Building aiBuild : aiBase)
                    for(Building posBuild : playerBase){
                        if(aiBuild.getArmy().getArmySize()*aiBuild.getPossessor().getNation().getUnitDamage() > //nationın damage'i yerine direk building in armysinin damagina bakmak daha iyi olabilir
                                posBuild.getArmy().getArmySize()*posBuild.getPossessor().getNation().getUnitDamage()) {//çünkü bina arsenalse damageları normalden yüksek
                            //aiBuild.moveToBaseAI(posBuild);
                            aiBuild.getArmy().setTargetBuilding(posBuild);
                            returnBuild.add(aiBuild);
                            System.out.println("only one army in list");
                            return returnBuild;//only one army moves
                        }
                    }    
        }
        else if(difficultyLevel==2){
            int decidedNum=0;
                for(Building aiBuild : aiBase)
                    for(Building posBuild : playerBase){
                        if(aiBuild.getArmy().getArmySize()*aiBuild.getPossessor().getNation().getUnitDamage() > //nationın damage'i yerine direk building in armysinin damagina bakmak daha iyi olabilir
                                posBuild.getArmy().getArmySize()*posBuild.getPossessor().getNation().getUnitDamage()) {//çünkü bina arsenalse damageları normalden yüksek
                            //aiBuild.moveToBaseAI(posBuild);
                            aiBuild.getArmy().setTargetBuilding(posBuild);
                            returnBuild.add(aiBuild);
                            if(decidedNum++>0)
                                return returnBuild;
                        }

            }
        }
        else if(difficultyLevel==3){
                for(Building aiBuild : aiBase)
                    for(Building posBuild : playerBase){
                        if(aiBuild.getArmy().getArmySize()*aiBuild.getPossessor().getNation().getUnitDamage() > //nationın damage'i yerine direk building in armysinin damagina bakmak daha iyi olabilir
                                posBuild.getArmy().getArmySize()*posBuild.getPossessor().getNation().getUnitDamage()) {//çünkü bina arsenalse damageları normalden yüksek
                            //aiBuild.moveToBaseAI(posBuild);
                            aiBuild.getArmy().setTargetBuilding(posBuild);
                            returnBuild.add(aiBuild);
                            return returnBuild;
                        }

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
