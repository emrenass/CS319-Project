/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayerPack;

/**
 *
 * @author taha
 */
public class Nation {
    private int unitSpeed,unitDamage,unitProductionRate;

    public Nation(int unitSpeed, int unitDamage, int unitProductionRate) {
        this.unitSpeed = unitSpeed;
        this.unitDamage = unitDamage;
        this.unitProductionRate = unitProductionRate;
    }

    public int getUnitSpeed() {
        return unitSpeed;
    }

    public void setUnitSpeed(int unitSpeed) {
        this.unitSpeed = unitSpeed;
    }

    public int getUnitDamage() {
        return unitDamage;
    }

    public void setUnitDamage(int unitDamage) {
        this.unitDamage = unitDamage;
    }

    public int getUnitProductionRate() {
        return unitProductionRate;
    }

    public void setUnitProductionRate(int unitProductionRate) {
        this.unitProductionRate = unitProductionRate;
    }
    
    
    
    
} 
