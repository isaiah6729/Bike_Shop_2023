package com.example.wgujavaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author isaiah francis
 */

/**
 * this class modifies the part table and parts
 */

public class modifypart1controller implements Initializable {

    Stage stage;
    Parent scene;

    /** part object */
    private static Part parttoModify = null;
    
    /** label to change */
    public Label machinecompanyName;
    
    /** assign parts machine id / company name*/
    @FXML public TextField machinecompanyNameinput;
    
    /** assign parts id*/
    @FXML public TextField modifypartid;
    
    /** assign parts min*/
    @FXML public TextField partmin;
    
    /** assign parts max*/
    @FXML public TextField partMax;
    
    /** assign parts price*/
    @FXML public TextField partPrice;
    
    /** assign parts stock */
    @FXML public TextField partInventory;
    
    /** assign parts name */
    @FXML public TextField partName;
    
    /** assign company name */
    @FXML public RadioButton outsourceclick;
    
    /** assign machine id */
    @FXML public RadioButton inHouseclick;
    
    /** cancel parts */
    @FXML public Button cancelPart;
    
    /** save parts */
    @FXML public Button partsave;

    /**
     * @param part recieves input from main screen product object
     */
    @FXML
    public void sendpart(Part part) {

        parttoModify = part;
        modifypartid.setText(String.valueOf(part.getId()));
        partName.setText(part.getName());
        partInventory.setText(String.valueOf(part.getStock()));
        partPrice.setText(String.valueOf(part.getPrice()));
        partMax.setText(String.valueOf(part.getMax()));
        partmin.setText(String.valueOf(part.getMin()));

        if (part instanceof InHouse) {
            machinecompanyNameinput.setText(String.valueOf(((InHouse) part).getMachineID()));
        }

        if (part instanceof Outsourced) {
            machinecompanyNameinput.setText(((Outsourced) part).getCompanyName());
        }
    }

    /**
     * @throws java.io.IOException  for the FXMLLoader.load
     * @param actionEvent saves input
     */
    @FXML
    public void onactionpartsave(ActionEvent actionEvent) throws IOException {
        try {

            /** recieves index of object */
            int intIndex = Inventory.getAllParts().indexOf(parttoModify);

            int id = Integer.parseInt(modifypartid.getText());
            String name = partName.getText();
            Integer stock = Integer.parseInt(partInventory.getText());
            Double price = Double.parseDouble(partPrice.getText());
            Integer max = Integer.parseInt(partMax.getText());
            Integer min = Integer.parseInt(partmin.getText());

            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Name cannot be empty");
                Optional<ButtonType> add = alert.showAndWait();
            } 
            else {

                /** max can't be less than min */
                if (max < min) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Max cannot be less than min");
                    Optional<ButtonType> maxmin = alert.showAndWait();
                } 
                else {

                    /** stock has to be between max and min */
                    if (!(stock <= max && stock >= min)) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Inventory amount invalid. Must be between min and max");
                        Optional<ButtonType> maxmin = alert.showAndWait();
                    } 
                    else {
                        
                        if (inHouseclick.isSelected()) {
                            Integer machineID = Integer.parseInt(machinecompanyNameinput.getText());
                            Inventory.updatePart(intIndex, new InHouse(id, name, price, stock, min, max, machineID));

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Part modified successfully");
                            Optional<ButtonType> add = alert.showAndWait();

                            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                            scene = FXMLLoader.load(getClass().getResource("mainform.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                        }

                        if (outsourceclick.isSelected()) {
                            
                            String companyName = machinecompanyNameinput.getText();
                            Inventory.updatePart(intIndex, new Outsourced(id, name, price, stock, min, max, companyName));

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Part modified successfully");
                            Optional<ButtonType> add = alert.showAndWait();

                            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                            scene = FXMLLoader.load(getClass().getResource("mainform.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                        }
                    }
                }
            }
        } 
        catch (NumberFormatException exception) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING, "No slots can be empty\n" +
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
     * @throws java.io.IOException  for the FXMLLoader.load
     * @param actionEvent exits page
     */
    @FXML
    public void onActioncancelPart(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainform.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * @param actionEvent changes text
     */
    @FXML
    public void onactioninhouse(ActionEvent actionEvent) {

        if (inHouseclick.isSelected())
            machinecompanyName.setText("Machine ID");
    }

    /**
     * @param actionEvent changes text
     */
    @FXML
    public void onactionoutsourced(ActionEvent actionEvent) {

        if (outsourceclick.isSelected())
            machinecompanyName.setText("Company Name");
    }

}

