package com.example.wgujavaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * @author isaiah francis
*/

/**
 * this class generates the page
 * FUTURE ENHANCEMENT: another functionality could be a 'part recommendations' depending on what product that you're modifying.
 * this would appear on the add product or modify product pages
 */

public class HelloApplication extends Application {

     /**  this method generates page and title
      * RUNTIME ERROR: at first when I wanted to generate the fxml page, it gave me an exception warning. exception in application start method.
      * but after review, the fxml file need a controller with it, and once a controller was added, then the fxml file worked.
      */

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainform.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1020, 840);
        stage.setTitle("Isaiah's Tool Discovery!");
        stage.setScene(scene);
        stage.show();
    }

     /**  @param args places info in table */
    public static void main(String[] args) {

        InHouse inhouse1 = new InHouse(Inventory.getNewPartID(), "wheel", 10.99, 19, 1, 60, 12);
        InHouse inhouse2 = new InHouse(Inventory.getNewPartID(), "tire", 12.99, 22, 1, 70, 10);
        InHouse inhouse3 = new InHouse(Inventory.getNewPartID(), "seat", 17.99, 21, 1, 70, 10);
        InHouse inhouse4 = new InHouse(Inventory.getNewPartID(), "handle bars", 13.99, 15, 3, 70, 10);
        InHouse inhouse5 = new InHouse(Inventory.getNewPartID(), "basket", 32.99, 25, 1, 80, 10);

        Product product1 = new Product(Inventory.getNewProductID(), "motor bike", 100.99, 20, 1, 50);
        Product product2 = new Product(Inventory.getNewProductID(), "motorcycle", 109.99, 35, 1, 50);
        Product product3 = new Product(Inventory.getNewProductID(), "tricycle", 80.99, 10, 1, 50);
        Product product4 = new Product(Inventory.getNewProductID(), "electric bike", 105.99, 43, 1, 50);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);

        Inventory.addPart(inhouse1);
        Inventory.addPart(inhouse2);
        Inventory.addPart(inhouse3);
        Inventory.addPart(inhouse4);
        Inventory.addPart(inhouse5);

        launch();
    }
}
