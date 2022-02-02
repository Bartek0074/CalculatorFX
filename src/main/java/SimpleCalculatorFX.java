import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SimpleCalculatorFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        BorderPane borderPane = new BorderPane();

        SimpleCalculatorFXUtil.createBoard(borderPane, primaryStage);

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        primaryStage.setWidth(340);
        primaryStage.setHeight(440);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Calculator Lite");
        primaryStage.show();
        primaryStage.getIcons().add(new Image("icon-app.png"));

    }
}