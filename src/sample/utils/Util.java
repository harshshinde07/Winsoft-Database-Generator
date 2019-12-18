package sample.utils;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Util {
    public static final String ICON_IMAGE_LOC = "/sample/utils/icon.png";


    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
    }
}
