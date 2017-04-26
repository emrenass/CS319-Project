package Model;


import java.io.File;
import java.io.Serializable;

public class SavedData implements Serializable {

        int currentLevel;
	
        public SavedData(int currentLevel){
            this.currentLevel = currentLevel;  
        }
        
        public int getCurrentLevel(){
            return currentLevel;
        }
        
        public void setCurrentLevel(int curretLevel){
            this.currentLevel = curretLevel;
        }

}