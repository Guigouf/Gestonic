package com.guibou.gestonic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;

public class ViewLoader {

    /**
     * Method that returns the JavaFX Parent to be loaded
     *
     * @param fxmlPath
     * @param controllerFactory
     * @return Parent
     */
    public static Parent load(String fxmlPath, Callback<Class<?>, Object> controllerFactory) {

        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(controllerFactory);
            loader.setLocation(ViewLoader.class.getResource(fxmlPath));
            parent = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parent;
    }

}