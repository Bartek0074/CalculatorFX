import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Style {

    static void setStyle(Button button, String colorRGBA, String fontSize){

        button.setStyle("-fx-background-color: " + colorRGBA + " 0.9);" +
                "-fx-background-insets: 1;" +
                "-fx-font: " + fontSize + "px Arial;" +
                "-fx-font-weight: bold;");

        button.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-background-color: " + colorRGBA + " 0.7);" +
                        "-fx-background-insets: 1;" +
                        "-fx-font: " + fontSize + "px Arial;" +
                        "-fx-font-weight: bold;");
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-background-color: " + colorRGBA + "0.9);" +
                        "-fx-background-insets: 1;" +
                        "-fx-font: " + fontSize + "px Arial;" +
                        "-fx-font-weight: bold;");
            }
        });

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-background-color: " + colorRGBA + "1);" +
                        "-fx-background-insets: 1;" +
                        "-fx-font: " + fontSize + "px Arial;" +
                        "-fx-font-weight: bold;");
            }
        });
    }

}
