package com.jakutenshi.rmcomix.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Akutenshi on 28.04.2015.
 */
public class ComicsViewController implements ControlledScreen, Initializable {

    @FXML
    Button prevPageBtn,             // Кнопка перелистывания изображений назад
            nextPageBtn,            // Кнопка перелистывания изображений вперед
            moveButtonImg;          // Кнопка перехода к выбраному изображению

    @FXML
    TextField imgCount;             // Поле с номером изображения

    @FXML
    Label maxImages;                // Максимальное количество изображений

    @FXML
    ImageView comicsPage;           // Область просмотра изображений

    @FXML
    GridPane pane,
            gridPanelNavigation;    // Панель с навигацией


    @FXML
    ScrollBar scrollBarNavigation;  // Скролл-бар текущей страницы

    ScreensController myController;

    private int currentPage = 0;    // Номер текущего изображения
    String[] jpgList;               // Массив изображений
    File currentDir;                // Выбранная директория с изображениями

    private double
            currentScaleImgX = 1,   // Текущий масштаб изображения по X
            currentScaleImgY = 1,   // Текущий масштаб изображения по Y
            currentTransX = 0,       // Текущее положение по X
            currentTransY = 0,       // Текущее положение по Y
            currentMouseX = 0,       // Текущее положение мыши по X
            currentMouseY = 0;       // Текущее положение мыши по Y

    private static final double MAX_SCALE_IMG = 5;    // Максимальный множитель для масштабирования изображения
    private static final double MIN_SCALE_IMG = 0.8;  // Минимальный множитель для масштабирования изображения

    public ComicsViewController() {
        int a = 0;
        a++;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
    }

    class PageTypeFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return (name.endsWith(".jpg") || name.endsWith(".png"));
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    private void onSettingsBtnAction() {

        myController.setScreen(ScreensController.SETTINGS_VIEW_SCREEN_ID);
    }

    /* Далее идут методы, отвечающие за перелистывание изображений */
    @FXML
    private void prevPageBtn() {
        if (jpgList != null && currentPage > 0) {
            currentPage--;
        }
        showPage();
    }
    @FXML
    private void nextPageBtn() {
        if (jpgList != null && currentPage < jpgList.length - 1) {
            currentPage++;
        }
        showPage();
    }
    private void showPage() {
        Image img = new Image("file:" + currentDir.getPath() + "\\" + jpgList[currentPage]);
        comicsPage.setImage(img);
        updateNumberNavigation();
        comicsPage.setCursor(Cursor.OPEN_HAND);
        System.gc();
    }
    /* Методы, отвечающие за перелистывание закончились */

    /* Метод выбирающий папку с изображениями */
    @FXML
    private void openDirectoryChooser() {
        try {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select directory with comix pages...");

        currentDir = chooser.showDialog(null);
            if (currentDir!=null) {
                selectComics(currentDir.getPath());
            }
        }finally {
        }
    }
    /* Конец */

    /* Метод, достающий изображения из заданной папки */
    private void selectComics(String path) {
        currentDir = new File(path);
        PageTypeFilter filter = new PageTypeFilter();
        jpgList = currentDir.list(filter);
        currentPage = 0;
        showPage();
        gridPanelNavigation.setDisable(false);
        scrollBarNavigation.setMax(jpgList.length);
        int i = jpgList.length;
        String str = Integer.toString(i);
        maxImages.setText(" / " + str);
    }
    /* Конец */

    /* Метод отвечающий за масштабирование изображения */
    public void ScrollImage(ScrollEvent event) {
        double Check = event.getDeltaY();           // Определяет куда крутят мышку
        if (Check > 0 && currentScaleImgX <= MAX_SCALE_IMG) {   // Если Check > 0 то увеличиваем масштаб
            currentScaleImgX +=0.1;         // увеличиваем машстаб по X
            currentScaleImgY +=0.1;         // увеличиваем машстаб по Y
            updateScaleImg();               // обновляем машстаб
        }
        else if (Check < 0 && currentScaleImgX > MIN_SCALE_IMG) {
            currentScaleImgX -=0.1;         // уменьшаем машстаб по X
            currentScaleImgY -=0.1;         // уменьшаем машстаб по Y
            updateScaleImg();               // обновляем машстаб
        }
    }
    /* Конец */

    /* Метод обновляющий масштаб изображения */
    private void updateScaleImg() {
        comicsPage.setScaleX(currentScaleImgX);
        comicsPage.setScaleY(currentScaleImgY);
    }
    /* Конец */

    /* Метод обновляющий положение изображения */
    private void updateTranslateImg() {
        comicsPage.setTranslateX(currentTransX);
        comicsPage.setTranslateY(currentTransY);
    }
    /* Конец */

    /* Метод выставляющий текущую страницу в навигаторе */
    private void updateNumberNavigation() {
        imgCount.setText(Integer.toString(currentPage+1));
        scrollBarNavigation.setValue(currentPage+1);
    }
    /* Конец */

    /* Методы перетаскивания изображения */
        /* Метод выполняющийся при зажатой и движущейся мышке */
    public void dragMouse(MouseEvent e) {
        currentTransX += (e.getX() - currentMouseX);
        currentTransY += (e.getY() - currentMouseY);
        updateTranslateImg();
    }
        /* Метод когда мышь зажимают на изображении */
    public void onImgPress(MouseEvent e) {
        currentMouseX = e.getX();
        currentMouseY = e.getY();
        comicsPage.setCursor(Cursor.CLOSED_HAND);
    }
        /* Метод когда мышь отжимают на изображении */
    public void onImgReleased(MouseEvent e) {
        comicsPage.setCursor(Cursor.OPEN_HAND);
    }
    /* Конец */

    /* Метод вращения изображения */
    public void rotateImage(int r) {
        comicsPage.setRotate(r);
    }
    /* Конец */

    /* Метод перехода к конкретному изображению */
    public void moveToImg() {
        int value;
        try {
            value = Integer.parseInt(imgCount.getText());
            if (value >= 0 && value <= jpgList.length) {
                currentPage = value-1;
                showPage();
            }
        } catch (NumberFormatException e) {
            // вывод сообщения об ошибке
        }
    }
    /* Конец */


    public void moveSliderNavigation(MouseEvent e) {
        currentPage = (int) scrollBarNavigation.getValue();
        showPage();
    }
}
