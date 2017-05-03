package Controller;

import java.util.ArrayList;

public class Objective {
    int objectiveID;
    boolean objectiveAchieved;
    ArrayList<Building> listB;
    public Objective(int objectiveID, boolean objectiveAchieved, ArrayList<Building> listB) {
        this.objectiveID = objectiveID;
        this.objectiveAchieved = objectiveAchieved;
        this.listB=listB;
    }

    public boolean isObjectiveAchieved() {
        return objectiveAchieved;
    }

    public Player checkObjectiveStatus() {
        Player winner=null;
        
        if(objectiveID==1){
            objectiveAchieved=true;
            Player possessor = listB.get(0).getPossessor();
            for( Building b :listB){
            objectiveAchieved = (possessor == b.getPossessor()) && objectiveAchieved;  
            winner = possessor;
            }
        }    
        
        if(objectiveID==2){
            objectiveAchieved=false;
            int AIArmySize=0;
            int playerArmySize=0;
            Player player=new Player(null,false);
            Player AI=new Player(null,true);
            for( Building b :listB){
                if(b.getPossessor().isAI()){
                    AIArmySize+=b.getArmy().getArmySize();
                }
                else{
                    playerArmySize+=b.getArmy().getArmySize();
                }
            }
            
            if(AIArmySize>150){
                objectiveAchieved=true;
                winner = AI;
            }
            else if(playerArmySize>150){
                objectiveAchieved=true;
                winner = player;
            }
        }
        
        return winner;
    }
    
    
    
}