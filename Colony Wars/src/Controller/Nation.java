/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author taha
 */
public class Nation {
    private int unitSpeed,unitProductionRate;
    private double unitDamage;
    protected NationType nationType;

    public Nation(NationType nationType) {
        this.nationType=nationType;
        
        if(nationType==NationType.Cult)
        {
            setUnitSpeed(30);
            setUnitDamage(1.5);
            setUnitProductionRate(30);
        }
        
        if(nationType==NationType.Doth)
        {
            setUnitSpeed(20);
            setUnitDamage(1.5);
            setUnitProductionRate(20);
        }
        
        if(nationType==NationType.Azad)
        {
            setUnitSpeed(30);
            setUnitDamage(1);
            setUnitProductionRate(20);
        }
    }

    public int getUnitSpeed() {
        return unitSpeed;
    }

    public void setUnitSpeed(int unitSpeed) {
        this.unitSpeed = unitSpeed;
    }

    public double getUnitDamage() {
        return unitDamage;
    }

    public void setUnitDamage(double unitDamage) {
        this.unitDamage = unitDamage;
    }

    public int getUnitProductionRate() {
        return unitProductionRate;
    }

    public void setUnitProductionRate(int unitProductionRate) {
        this.unitProductionRate = unitProductionRate;
    }

    public NationType getNation() {
        return nationType;
    }

    public void setNation(NationType nation) {
        this.nationType = nation;
    }
    
    
    
    
} 
