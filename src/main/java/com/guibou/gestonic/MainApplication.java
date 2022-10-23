package com.guibou.gestonic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {

    public static void main(String[] args) {
            launch();
        }

    /**
     * With this start method, using {@link ControllerFactory#controllerForClass(Class)} allows to initiate the controller if needed
     * @param primaryStage The primary Stage of the application
     */
    public void startNEW(Stage primaryStage) {
        Parent root = ViewLoader.load("fxml/templateChoice.fxml",
                    ControllerFactory::controllerForClass);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestionnaire Supersonic");
            primaryStage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/logo_rond.png"))));
            primaryStage.show();
        }

        public void start(Stage primaryStage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/templateChoice.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("Gestionnaire Supersonic");
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/logo_rond.png"))));
            primaryStage.show();
        }
}

