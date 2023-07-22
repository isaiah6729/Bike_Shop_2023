package com.example.wgujavaproject;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * @author isaiah francis
 */

/**
 * this class creates the main screen for the tables
 */

public class mainformcontroller implements Initializable {

    Stage stage;
    Parent scene;

    /** products list */
    ObservableList<Product> products = null;
    
    /** parts table*/
    @FXML public TableView partTable;
    
    /** assign parts id */
    @FXML public TableColumn partID;
    
    /** assign parts name */
    @FXML public TableColumn partName;
    
    /** assign parts stock */
    @FXML public TableColumn partInventory;
    
    /** assign parts price */
    @FXML public TableColumn partPrice;
    
    /** looks up parts */
    @FXML public TextField partSearch;
    
    /** adds parts */
    @FXML public Button addPart;
    
    /** modify parts */
    @FXML public Button modifyPart;
    
    /** deletes parts */
    @FXML public Button deletePart;
    
    /** products search */
    @FXML public TextField productSearch;
    
    /** products table*/
    @FXML public TableView productTable;
    
    /** assign products id*/
    @FXML public TableColumn productID;
    
    /** assign products name*/
    @FXML public TableColumn productName;
    
    /** assign products stock */
    @FXML public TableColumn productInventory;
    
    /** assign products price */
    @FXML public TableColumn productPrice;
    
    /** add products */
    @FXML public Button addProduct;
    
    /** modify products */
    @FXML public Button modifyProduct;
    
    /** deletes products */
    @FXML public Button deleteProduct;
    
    /** exit screen */
    @FXML public Button inventoryExit;

    /**
     * initializes table
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        partTable.setItems(Inventory.getAllParts());
        productTable.setItems(Inventory.getAllProducts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * @param actionEvent searches for part by letter or number
     */
    @FXML
    public void onactionpartsearch(ActionEvent actionEvent) {
        
        String input = partSearch.getText();

        /**  if there is no selection, all parts show */
        if (input == null) {
            partTable.setItems(Inventory.getAllParts());
        }

        try {
            ObservableList<Part> parts = Inventory.lookupPart(input);
            partTable.setItems(parts);
            
            /**
             * resolved issue:
             * tried parts == null and got no response - must be parts.size() == 0
             * */
            if (parts.size() == 0) {

                int input2 = Integer.parseInt(input);

                /** look by number */
                Part part = Inventory.lookupPart(input2);
                
                if (part != null) {
                    parts.add(part);
                    partTable.setItems(parts);
                } 
                else {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Part not found");
                    Optional<ButtonType> search2 = alert2.showAndWait();
                    partTable.setItems(Inventory.getAllParts());
                }
            }

        } 
        catch (NumberFormatException exception) {
            
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Part not found");
            Optional<ButtonType> search2 = alert2.showAndWait();
            partTable.setItems(Inventory.getAllParts());
        }

    }

    /**
     * @throws java.io.IOException  for the FXMLLoader.load
     * @param actionEvent brings up add part page
     *
     */
    @FXML public void onActionaddPart(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("addpart1.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * @param actionEvent brings up modify parts screen
     * @throws java.io.IOException  for the Loader.load
     */
    @FXML
    public void onActionmodifyPart(ActionEvent actionEvent) throws IOException {

        Part input = (Part) partTable.getSelectionModel().getSelectedItem();

        /** checks to make sure there's a selection */
        if (input == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You have to select an item");
            Optional<ButtonType> deletebutton = alert.showAndWait();
        } 
        else {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("modifypart1.fxml"));
            loader.load();

            /** sends info to other controller */
            modifypart1controller modifypart1 = loader.getController();
            modifypart1.sendpart(input);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * @param actionEvent deletes part
     */
    @FXML
    public void onActiondeletePart(ActionEvent actionEvent) {

        Part partselectitem = (Part) partTable.getSelectionModel().getSelectedItem();

        /** checks for selection */
        if (partselectitem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You have to select an item");
            Optional<ButtonType> deletebutton = alert.showAndWait();
        } 
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete this " + "section. Are you sure you want to do this?");
            Optional<ButtonType> deletebutton = alert.showAndWait();

            if (deletebutton.isPresent() && deletebutton.get() == ButtonType.OK) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING, "Selection deleted");
                Optional<ButtonType> deletebutton2 = alert2.showAndWait();
                Inventory.deletePart(partselectitem);
            } 
            else {
                Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION, "Delete selection was aborted");
                Optional<ButtonType> deletebutton3 = alert3.showAndWait();
            }
        }
    }

    /**
     * @param actionEvent searches for product by letter or number
     */
    @FXML
    public void onactionproductsearch(ActionEvent actionEvent) {
        String input = productSearch.getText();

        /** populates if no selection */
        if (input == null) {
            productTable.setItems(Inventory.getAllProducts());
        }

        try {
            /** looks for input  */
            products = Inventory.lookupProduct(input);
            
            productTable.setItems(products);

            if (products.size() == 0) {

                int input2 = Integer.parseInt(input);
                
                Product product = Inventory.lookupProduct(input2);
                
                if (product != null) {
                    products.add(product);
                    productTable.setItems(products);
                } 
                else {
                    /** no ID or letter found */
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product not found");
                    Optional<ButtonType> search = alert.showAndWait();
                    productTable.setItems(Inventory.getAllProducts());
                }
            }
        } 
        catch (NumberFormatException exception) {
            /** no ID or letter found */
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product not found");
            Optional<ButtonType> search = alert.showAndWait();
            productTable.setItems(Inventory.getAllProducts());
        }
    }


    /**
     * @throws java.io.IOException  for the FXMLLoader.load
     * @param actionEvent brings up add screen
     */
    @FXML
    public void onactionaddProduct(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("addproduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * @param actionEvent brings up modify
     * @throws java.io.IOException  for the Loader.load

     *  RUNTIME ERROR: NullpointerException: on action methods deleted from class but not fxml. delete on action methods in fxml
     */
    @FXML
    public void onModifyProduct(ActionEvent actionEvent) throws IOException {

        Product input = (Product) productTable.getSelectionModel().getSelectedItem();

        if (input == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You must select an item");
            Optional<ButtonType> deletebutton = alert.showAndWait();
        }

        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("modifyproduct.fxml"));
            loader.load();

            modifyproductcontroller modifyproduct = loader.getController();
            modifyproduct.sendproduct(input);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * @param actionEvent deletes product
     */
    @FXML
    public void onDeleteProduct(ActionEvent actionEvent) {

        /** selects item */
        Product productselecteditem = (Product) productTable.getSelectionModel().getSelectedItem();

        if (productselecteditem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You have to select an item");
            Optional<ButtonType> deletebutton = alert.showAndWait();
        } 
        else {

            /** checks to see if associated parts list is empty */
            if (productselecteditem.getAllAssociatedParts().isEmpty()) {

                /** confirms delete */
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete this section. Are you sure you want to do this?");
                Optional<ButtonType> productbutton = alert.showAndWait();

                if (productbutton.isPresent() && productbutton.get() == ButtonType.OK) {
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Selection deleted");
                    Optional<ButtonType> productbutton2 = alert2.showAndWait();
                    Inventory.deleteProduct(productselecteditem);
                } 
                else {
                    Alert alert3 = new Alert(Alert.AlertType.ERROR, "Deleting selection was aborted");
                    Optional<ButtonType> deletebutton3 = alert3.showAndWait();
                }
            } 
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Parts assigned to product. Cannot delete.");
                Optional<ButtonType> deletebutton = alert.showAndWait();
            }
        }
    }

    /**
     * @param actionEvent exit page
     */
    @FXML
    public void onExit(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to exit?");
        Optional<ButtonType> exit = alert.showAndWait();
        if (exit.isPresent() && exit.get() == ButtonType.OK) {
            System.exit(0);
        }
    }


}
