package com.example.wgujavaproject;


/**
 * @author isaiah francis
 */

/**
 * this class states the machine id */
public class InHouse extends Part {

    private int machineID;

    /** constructor
     * @param id sets id
     * @param max sets max
     * @param min sets min
     * @param name sets name
     * @param price sets price
     * @param stock sets stock
     * @param machineID sets machine id
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**
     * @param machineID sets machine ID
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    /**
     * @return gets machine ID
     */
    public int getMachineID() {
        return machineID;
    }
}

