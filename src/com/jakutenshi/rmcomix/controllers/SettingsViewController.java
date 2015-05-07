package com.jakutenshi.rmcomix.controllers;

import com.jakutenshi.rmcomix.model.ComicsArchive;
import javafx.fxml.FXML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Akutenshi on 28.04.2015.
 */
public class SettingsViewController implements ControlledScreen {
    ScreensController myController;
    ComicsViewController comicsViewController; /* Ссылка на контроллер окна показа комиксов */
    ComicsArchive archive;

    public SettingsViewController() {
        //parse home directory if it's exists for comics
        Properties properties = new Properties();
        try {
            InputStream propertiesInputStream = new FileInputStream("res/conf.properties");
            properties.load(propertiesInputStream);

            archive = new ComicsArchive();
            if (!properties.getProperty("homeDir").equals("null")) {
                archive.parseHomeDir(properties.getProperty("homeDir"));
                archive.savePersonDataToFile();
                archive.loadPersonDataFromFile();
                System.out.println(archive.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
        this.comicsViewController = myController.getLoaders("ComicsView").getController();
    }

    @FXML
    public void onToComicsViewBtn() {
        myController.setScreen(ScreensController.COMICS_VIEW_SCREEN_ID);
    }

    /* Метобы выбора поворота изображения */
    @FXML
    private void selectRotate360() {
        comicsViewController.rotateImage(360);
    }
    @FXML
    private void selectRotate180() {
        comicsViewController.rotateImage(180);
    }
    @FXML
    private void selectRotate90() {
        comicsViewController.rotateImage(90);
    }
    @FXML
    private void selectRotate0() {
        comicsViewController.rotateImage(0);
    }
    /* Конец*/

}
