package com.example.wgujavaproject;


import javafx.collections.FXCollections;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 @author isaiah francis
 */

/**
 * this class modifies the product table, products, and parts that are part of the product
 */

public class modifyproductcontroller implements Initializable {

    Stage stage;
    Parent scene;

    /** parts list */
    ObservableList<Part> parts2 = FXCollections.observableArrayList();
 
    /** initialize table */
    Product productTable = new Product(0, null, 0, 0, 0, 0);
 
    /** object to transfer */
    private static Product producttomodify = new Product(0, null, 0, 0, 0, 0);

    /** modify parts table*/
    @FXML public TableView modifyparttable;
 
    /** modify products table */
    @FXML public TableView modifyproducttable;
 
    /** search products */
    @FXML public TextField modifyproductsearch;
 
    /** assign parts name*/
    @FXML public TableColumn modifyid;
 
    /** assign parts name*/
    @FXML public TableColumn modifyname;
 
    /** assign parts stock*/
    @FXML public TableColumn modifystock;
 
    /** assign parts price*/
    @FXML public TableColumn modifyprice;
 
    /** assign parts id*/
    @FXML public TableColumn modifypart2id;
 
    /** assign parts name*/
    @FXML public TableColumn modifypart2name;
 
    /** assign parts stock*/
    @FXML public TableColumn modifypart2stock;
 
    /** assign parts price*/
    @FXML public TableColumn modifypart2price;
 
    /** assign products id */
    @FXML public TextField modifyproductid;
 
    /** assign products name */
    @FXML public TextField modifyproductName;
 
    /** assign products stock */
    @FXML public TextField modifyInventory;
 
    /** assign products price*/
    @FXML public TextField modifyproductPrice;
 
    /** assign products max */
    @FXML public TextField modifyproductmax;
 
    /** assign products min */
    @FXML public TextField modifyproductmin;
 
    /** modify products */
    @FXML public Button modifyproduct;
 
    /** remove associated parts*/
    @FXML public Button modifyproductremoveassociatedpart;
 
    /** save products */
    @FXML public Button modifyproductsave;
 
    /** cancel products */
    @FXML public Button modifyproductcancel;

    /**
     * initialize table
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        modifyparttable.setItems(Inventory.getAllParts());

        modifyid.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyname.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifystock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        modifypart2id.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifypart2name.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifypart2stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifypart2price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     *
     * @param actionEvent search for parts
     */
    @FXML
    public void onactionaddproductsearch(ActionEvent actionEvent) {

        String input = modifyproductsearch.getText();

        if (input == null) {
            modifyparttable.setItems(Inventory.getAllParts());
        }

        ObservableList<Part> parts1 = Inventory.lookupPart(input);
        modifyparttable.setItems(parts1);

        if (parts1.size() == 0) {
         
            try {
                int input2 = Integer.parseInt(input);
                Part parts2 = Inventory.lookupPart(input2);
                if (parts2 != null) {
                    parts1.add(parts2);
                    modifyparttable.setItems(parts1);
                } 
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Part not found");
                    Optional<ButtonType> add = alert.showAndWait();
                    modifyparttable.setItems(Inventory.getAllParts());
                }
            } 
            catch (NumberFormatException exception) {
             
                Alert alert = new Alert(Alert.AlertType.WARNING, "Part not found");
                Optional<ButtonType> add = alert.showAndWait();
                modifyparttable.setItems(Inventory.getAllParts());
            }
        }
    }

    /**
     *
     * @param actionEvent add parts from table 1 to table 2
     */
    @FXML
    public void onactionaddproduct(ActionEvent actionEvent) {

        Part selectedpart = (Part) modifyparttable.getSelectionModel().getSelectedItem();

        if (selectedpart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item");
            Optional<ButtonType> add = alert.showAndWait();
        }

        else {
            producttomodify.addAssociatedPart(selectedpart);
            modifyproducttable.setItems((ObservableList) producttomodify.getAllAssociatedParts());
        }
    }


    /**
     * @param actionEvent remove parts from table 2
     */
    @FXML
    public void onactionmodifyproductremoveassociatedpart(ActionEvent actionEvent) {

        Part selectedpart = (Part) modifyproducttable.getSelectionModel().getSelectedItem();

        if (selectedpart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item");
            Optional<ButtonType> delete = alert.showAndWait();
        } 
        else {

            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this?");
            Optional<ButtonType> delete1 = alert1.showAndWait();

            if (delete1.isPresent() && delete1.get() == ButtonType.OK) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING, "Selection deleted");
                Optional<ButtonType> delete2 = alert2.showAndWait();
                producttomodify.deleteAssociatedPart(selectedpart);
            } 
            else {
                Alert alert3 = new Alert(Alert.AlertType.ERROR, "Deleting selection was aborted");
                Optional<ButtonType> deletebutton3 = alert3.showAndWait();
            }
        }
    }

    /**
     *
     * @param actionEvent save product
     *  @throws java.io.IOException  for the FXMLLoader.load
     *
     * RUNTIMEERROR indexoutofboundsexception: index number was going out of the list. to correct, i set the index
     * to Inventory.getAllProducts().indexOf(producttomodify) to get the object's index.
     *
     * RUNTIMEERROR concurrentmodificationexception: i had a for loop and enhanced loop together. to correct, I took away for loop with same product object
     */
    @FXML
    public void onactionmodifyproductsave(ActionEvent actionEvent) throws IOException {

        try {
            int intIndex = Inventory.getAllProducts().indexOf(producttomodify);

            Integer id = Integer.parseInt(modifyproductid.getText());
            Integer min = Integer.parseInt(modifyproductmin.getText());
            Integer max = Integer.parseInt(modifyproductmax.getText());
            Double price = Double.parseDouble(modifyproductPrice.getText());
            Integer stock = Integer.parseInt(modifyInventory.getText());
            String name = modifyproductName.getText();

            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Name cannot be left blank");
                Optional<ButtonType> add = alert.showAndWait();
            } 
            else {

                if (max < min) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Max cannot be less than min");
                    Optional<ButtonType> maxmin = alert.showAndWait();
                } 
                else {

                    if (!(stock <= max && stock >= min)) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Inventory amount invalid. Must be between min and max");
                        Optional<ButtonType> maxmin = alert.showAndWait();
                    } 
                    else {

                        /** update product */

                        productTable = new Product(id, name, price, stock, min, max);
                        Inventory.updateProduct(intIndex, productTable);

                        for (Part part : producttomodify.getAllAssociatedParts()) {
                            productTable.addAssociatedPart(part);
                        }

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product modified successfully");
                        Optional<ButtonType> maxmin = alert.showAndWait();

                        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("mainform.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                }
            }
        }
         
        /** catches empty slots */
        catch (NumberFormatException exception) {
         
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No slots can be empty\n" +
                    "Name must be a letter (no numbers)\n" +
                    "Company Name must be a letter (no numbers)\n" +
                    "Part ID must be a number (no letters)\n" +
                    "Max must be a number (no letters)\n" +
                    "Min must be a number (no letters)\n " +
                    "Price must be a number (no letters)\n" +
                    "Stock must be a number (no letters)");
            Optional<ButtonType> add = alert.showAndWait();
        }
    }


    /**
     *
     * @param actionEvent exit page
     *  @throws java.io.IOException  for the FXMLLoader.load
     */
    @FXML
    public void onactionmodifyproductcancel(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainform.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * @param product receive info from main page to modify object
     */
    @FXML
    public void sendproduct(Product product) {

        producttomodify = product;
        modifyproductid.setText(String.valueOf(product.getId()));
        modifyproductName.setText(product.getName());
        modifyInventory.setText(String.valueOf(product.getStock()));
        modifyproductPrice.setText(String.valueOf(product.getPrice()));
        modifyproductmax.setText(String.valueOf(product.getMax()));
        modifyproductmin.setText(String.valueOf(product.getMin()));

        modifyproducttable.setItems(product.getAllAssociatedParts());
        modifypart2id.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifypart2name.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifypart2stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifypart2price.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

}
