package com.example.wgujavaproject;


/**
 * @author isaiah francis
 */

/** this class states the company name */
public class Outsourced extends Part {

    private String companyName;

    /** constructor
     * @param id sets id
     * @param max sets max
     * @param min sets min
     * @param name sets name
     * @param price sets price
     * @param stock sets stock
     * @param companyName sets company name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * @param companyName sets company name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return gets company name
     */
    public String getCompanyName() {
        return companyName;
    }
}
