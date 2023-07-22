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
 * this class adds to the part table and parts
 */

public class addpart1controller implements Initializable {

    Stage stage;
    Parent scene;

/** machine company label */
    @FXML public Label machinecompanyName;
  
    /** assign part id */
    @FXML public TextField partID;
  
    /** assign part min */
    @FXML public TextField partmin;
  
    /** assign machine company name */
    @FXML public TextField machinecompanyNameinput;
  
    /** assign part max */
    @FXML public TextField partMax;
  
    /** assign part price */
    @FXML public TextField partPrice;
  
    /** assign part stock */
    @FXML public TextField partInventory;
  
    /** assign part name */
    @FXML public TextField partName;
  
    /** assign part company name */
    @FXML public RadioButton outsourceclick;
  
    /** assign machine id */
    @FXML public RadioButton inHouseclick;
  
    /** cancel part */
    @FXML public Button Partcancel;
  
    /** save part  */
    @FXML public Button partsave;

    /**
     * initializes table
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * @param actionEvent saves input
     * @throws java.io.IOException  for the FXMLLoader.load
     */
    @FXML
    public void onactionpartsave(ActionEvent actionEvent) throws IOException {

        try {
            String name = partName.getText();

            /** checks for null */
            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Name cannot be left blank");
                Optional<ButtonType> add = alert.showAndWait();
            } else {

                Integer stock = Integer.parseInt(partInventory.getText());
                Double price = Double.parseDouble(partPrice.getText());
                Integer max = Integer.parseInt(partMax.getText());
                Integer min = Integer.parseInt(partmin.getText());

                /** max can't be less than min */
                if (max < min) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Max cannot be less than min");
                    Optional<ButtonType> maxmin = alert.showAndWait();
                } else {

                    /** stock has to be between max and min */
                    if (!(stock <= max && stock >= min)) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Inventory amount invalid. Must be between min and max");
                        Optional<ButtonType> maxmin = alert.showAndWait();
                    } else {

                        /** sets machine ID text */
                        if (inHouseclick.isSelected()) {
                            Integer machineID = Integer.parseInt(machinecompanyNameinput.getText());
                            Inventory.addPart(new InHouse(Inventory.getNewPartID(), name, price, stock, min, max, machineID));

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Part added successfully");
                            Optional<ButtonType> add = alert.showAndWait();

                            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                            scene = FXMLLoader.load(getClass().getResource("mainform.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                        }

                        /** sets company name text */
                        if (outsourceclick.isSelected()) {
                            String companyName = machinecompanyNameinput.getText();
                            Inventory.addPart(new Outsourced(Inventory.getNewPartID(), name, price, stock, min, max, companyName));

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Part added successfully");
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
        /** catches empty slots */ catch (NumberFormatException exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "No slots can be empty\n" +
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
    public void onActioncancelPart(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainform.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     *  @param actionEvent changes machine ID text
     */
    @FXML
    public void onactioninhouse(ActionEvent actionEvent) {
      
        if (inHouseclick.isSelected())
            machinecompanyName.setText("Machine ID");
    }

    /**
     * @param actionEvent  changes company name text
     */
    @FXML
    public void onactionoutsourced(ActionEvent actionEvent) {

        if (outsourceclick.isSelected())
            machinecompanyName.setText("Company Name");

    }
}






