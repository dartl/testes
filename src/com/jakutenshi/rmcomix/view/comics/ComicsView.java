package com.jakutenshi.rmcomix.view.comics;

import com.jakutenshi.rmcomix.controllers.ScreensController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Created by Akutenshi on 19.04.2015.
 */
public class ComicsView extends Application {
    Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(ScreensController.COMICS_VIEW_SCREEN_ID, ScreensController.COMICS_VIEW_SCREEN_FXML);
        mainContainer.loadScreen(ScreensController.SETTINGS_VIEW_SCREEN_ID, ScreensController.SETTINGS_VIEW_SCREEN_FXML);

        mainContainer.setScreen(ScreensController.COMICS_VIEW_SCREEN_ID);

        Scene scene = new Scene(mainContainer);

        stage.setTitle("RMComixReader");
        stage.setScene(scene);
        stage.show();
    }
}
