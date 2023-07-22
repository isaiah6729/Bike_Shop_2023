package com.example.wgujavaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author isaiah francis
 */

/**
 * product class for product table
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** constructor
     * @param id sets id
     * @param max sets max
     * @param min sets min
     * @param name sets name
     * @param price sets price
     * @param stock sets stock
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * @param id set id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name set name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price set price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock set stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min set min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max set max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @param part add parts from table 1 to table 2
     */
    public void addAssociatedPart(Part part) {

        associatedParts.add(part);
    }

    /**
     * @return associatedParts.remove(selectedAssociatedPart)
     * @param selectedAssociatedPart delete parts from associated table
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {

        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * @return associatedParts, look at all parts from table 2
     */
    public ObservableList<Part> getAllAssociatedParts() {

        return associatedParts;
    }


}
