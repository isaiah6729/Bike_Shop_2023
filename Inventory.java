package com.example.wgujavaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * @author isaiah francis
*/

/**
 * Inventory class adds and deletes parts/products to the inventory
 */

public class Inventory {

    /** parts list */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
  
    /** products list */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
  
    /** parts initialize */
    static int partID = 1;
  
    /** products initialize */
    static int productID = 1;

    /**
     * @return partid
     * gets part ID and increases it in constructor
     */
    public static int getNewPartID() {
        return partID++;
    }

    /**
     * @return productid
     * gets product ID and increases it in constructor
     */
    public static int getNewProductID() {
        return productID++;
    }

    /**
     * @param newPart adds new part to list
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * @param newProduct adds new product to list
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * @param partID looks up the part by ID #
     * @return null
     *
     */
    public static Part lookupPart(int partID) {

        for (int i = 0; i < allParts.size(); ++i) {
            Part parts = allParts.get(i);

            if (parts.getId() == partID)
                return parts;
        }
        return null;
    }

    /**
     * @param productID looks up product by ID #
     *  @return null
     */
    public static Product lookupProduct(int productID) {

        /** searches list */
        for (int i = 0; i < allProducts.size(); ++i) {
            Product products = allProducts.get(i);

            /**  if ID matches */
            if (products.getId() == productID)
                return products;
        }
        return null;
    }

    /**
     * @param partName look up part by letters
     *  @return lookeduparts
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> lookedupParts = FXCollections.observableArrayList();

        for (Part parts : Inventory.getAllParts())
            if (parts.getName().contains(partName))
                lookedupParts.add(parts);

        return lookedupParts;
    }

    /**
     * @return lookedupproducts
     * @param productName look up product by letters
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> lookedupProducts = FXCollections.observableArrayList();

        for (Product product : Inventory.getAllProducts())
            if (product.getName().contains(productName))
                lookedupProducts.add(product);

        return lookedupProducts;
    }

    /**
     * @param index index
     * @param selectedPart  updates part object by index
     */
    public static void updatePart(int index, Part selectedPart)
    {
        allParts.set(index, selectedPart);
    }

    /**
     * @param index index
     * @param selectedProduct updates product object by index
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /**
     * @return allparts.remove(selectedpart)
     * @param selectedPart deletes part object
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * @return allproducts.remove(selectedproduct)
     * @param selectedProduct deletes product object
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * @return gets all parts in list
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * @return gets all products in list
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
