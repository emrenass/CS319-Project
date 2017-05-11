package Controller;

import java.util.ArrayList;

public class Objective {
    int objectiveID;
    boolean objectiveAchieved;
    ArrayList<Building> listB;
    private Building AIArsenal,playerArsenal;
    public Objective(int objectiveID, boolean objectiveAchieved, ArrayList<Building> listB,Building AIArsenal,Building playerArsenal) {
        this.objectiveID = objectiveID;
        this.objectiveAchieved = objectiveAchieved;
        this.listB=listB;
        this.AIArsenal=AIArsenal;
        this.playerArsenal=playerArsenal;
    }

    public boolean isObjectiveAchieved() {
        return objectiveAchieved;
    }

    public Player checkObjectiveStatus() {
        Player winner=null;
        
        if(objectiveID==1 || objectiveID==4 ){
            objectiveAchieved=true;
            Player possessor = listB.get(0).getPossessor();
            for( Building b :listB){
            objectiveAchieved = (possessor == b.getPossessor()) && objectiveAchieved;  
            winner = possessor;
            }
        }    
        
        if(objectiveID==2 || objectiveID==5){
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
            
            if(AIArmySize>650){
                objectiveAchieved=true;
                winner = AI;
            }
            else if(playerArmySize>150){
                objectiveAchieved=true;
                winner = player;
            }
        }
        
        if(objectiveID==3){
            System.out.println("Im in 3rd objective");
            objectiveAchieved=false;
          
            if(AIArsenal ==null)System.out.println("buneamk");
            if(playerArsenal ==null)System.out.println("buneamk2");
            if(!AIArsenal.getPossessor().isAI()){
                winner=new Player(null,false);
                objectiveAchieved=true;
            }
            if(playerArsenal.getPossessor().isAI()){
                winner=new Player(null,true);
                objectiveAchieved=true;
            }
        }
        
        return winner;
    }
    
    
    
}