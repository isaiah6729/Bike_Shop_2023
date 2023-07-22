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
 * @author isaiah francis
*/

/**
 * this class adds to the product table, products, and parts that are part of the product
 */
public class addproductcontroller implements Initializable {

    Stage stage;
    Parent scene;

    /** products list */
    ObservableList<Part> parts2 = FXCollections.observableArrayList();
    
    /** assign products id*/
    @FXML public TextField productID;
    
    /** search parts */
    @FXML public TextField addproductsearch;
    
    /** add products */
    @FXML public Button addproduct;
    
    /** assign products name*/
    @FXML public TextField partName;
    
    /** assign products stock */
    @FXML public TextField partInventory;
    
    /** assign products price*/
    @FXML public TextField productPrice;
    
    /** assign products max*/
    @FXML public TextField productmax;
    
    /** assign products min*/
    @FXML public TextField addproductmin;
    
    /** remove associated parts */
    @FXML public Button addproductremoveassociatedpart;
    
    /** save products */
    @FXML public Button addproductsave;
    
    /** cancel products */
    @FXML public Button addproductcancel;
    
    /** parts table */
    @FXML public TableView addparttable;
    
    /** assign parts id*/
    @FXML public TableColumn partid;
    
    /** assign parts name*/
    @FXML public TableColumn partname;
    
    /** assign parts stock */
    @FXML public TableColumn partstock;
    
    /** assign parts price*/
    @FXML public TableColumn partprice;
    
    /**  parts table 2 */
    @FXML public TableView addparttable2;
    
    /** assign parts id*/
    @FXML public TableColumn productid;
    
    /** assign parts name */
    @FXML public TableColumn productname;
    
    /** assign parts stock */
    @FXML public TableColumn productstock;
    
    /** assign parts price */
    @FXML public TableColumn productprice;

    /**
     * initializes table
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addparttable.setItems(Inventory.getAllParts());

        partid.setCellValueFactory(new PropertyValueFactory<>("id"));
        partname.setCellValueFactory(new PropertyValueFactory<>("name"));
        partstock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partprice.setCellValueFactory(new PropertyValueFactory<>("price"));

        productid.setCellValueFactory(new PropertyValueFactory<>("id"));
        productname.setCellValueFactory(new PropertyValueFactory<>("name"));
        productstock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productprice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     *  @param actionEvent searches table
     */
    @FXML
    public void onactionaddproductsearch(ActionEvent actionEvent) {

        String input = addproductsearch.getText();

        if (input == null) {
            addparttable.setItems(Inventory.getAllParts());
        }

        /** searches table by letter */
        ObservableList<Part> products = Inventory.lookupPart(input);
        addparttable.setItems(products);

        if (products.size() == 0) {
            try {
                int input2 = Integer.parseInt((input));

                /** searches table by ID */
                Part part = Inventory.lookupPart(input2);
                if (part != null) {
                    products.add(part);
                    addparttable.setItems(products);
                } else {
                    /** no ID or letter found */
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Part not found");
                    Optional<ButtonType> add = alert.showAndWait();
                    addparttable.setItems(Inventory.getAllParts());
                }
            }
            /** no ID or letter found */ catch (NumberFormatException exception) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Part not found");
                Optional<ButtonType> add = alert.showAndWait();
                addparttable.setItems(Inventory.getAllParts());
            }
        }
    }

    /**
     * @param actionEvent adds parts from table 1 to table 2
     */
    @FXML
    public void onactionaddproduct(ActionEvent actionEvent)  {

        Part partselecteditem = (Part) addparttable.getSelectionModel().getSelectedItem();

        if (partselecteditem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item");
            Optional<ButtonType> add = alert.showAndWait();
        }

        else {
            parts2.add(partselecteditem);
            addparttable2.setItems(parts2);
        }
    }

    /**
     *  @param actionEvent removes parts from table 2
     * RUNTIME ERROR classcastexception: tried to cast observable list to product but cannot, corrected with parts2.remove(productselectitem);
     */
    @FXML
    public void onactionaddproductremoveassociatedpart(ActionEvent actionEvent) {

        Part productselectitem = (Part) addparttable2.getSelectionModel().getSelectedItem();

        if (productselectitem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select an item");
            Optional<ButtonType> deletebutton = alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this?");
            Optional<ButtonType> productbutton = alert.showAndWait();

            if (productbutton.isPresent() && productbutton.get() == ButtonType.OK) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING, "Selection deleted");
                Optional<ButtonType> productbutton2 = alert2.showAndWait();
                parts2.remove(productselectitem);

            } else {
                Alert alert3 = new Alert(Alert.AlertType.ERROR, "Deleting selection was aborted");
                Optional<ButtonType> productbutton3 = alert3.showAndWait();
            }
        }
    }


    /**
     * @throws java.io.IOException  for the FXMLLoader.load
     *  @param actionEvent saves the modified product
     */
    @FXML
    public void onactionaddproductsave(ActionEvent actionEvent) throws IOException {

        try {
            String name = partName.getText();

            /** checks for null */
            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Name cannot be left blank");
                Optional<ButtonType> add = alert.showAndWait();
            } 
            else {

                Integer stock = Integer.parseInt(partInventory.getText());
                Double price = Double.parseDouble(productPrice.getText());
                Integer max = Integer.parseInt(productmax.getText());
                Integer min = Integer.parseInt(addproductmin.getText());

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

                        Product newProduct = new Product(Inventory.getNewProductID(), name, price, stock, min, max);
                        Inventory.addProduct(newProduct);

                        for (Part part : parts2) {
                            newProduct.addAssociatedPart(part);
                        }
                       
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Product added successfully");
                        Optional<ButtonType> add = alert.showAndWait();

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
     *  @param actionEvent exits page
     *  @throws java.io.IOException  for the FXMLLoader.load
     */
    @FXML
    public void onactionaddproductcancel(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainform.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
