import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class SimpleCalculatorFXUtil {

    static void createBoard(BorderPane borderPane, Stage stage){

        Value oldValue = new Value();
        Value newValue = new Value();
        Value actualValue = new Value();

        Status status = new Status();
        status.setStatus("start");
        Status typingNumber = new Status();
        typingNumber.setStatus("no");

        ArrayList<ButtonNumber> buttonNumbers = new ArrayList<>();
        Label calculations = new Label();
        Label result = new Label("0");
        Button buttonClear = new Button("C/CE");
        Button buttonOff = new Button("OFF");
        Button buttonRoot = new Button();
        Button buttonPercent = new Button("%");
        Button buttonDivision = new Button("/");
        Button buttonMultiplication = new Button("x");
        Button buttonSubtraction = new Button("-");
        Button buttonAddition = new Button("+");
        Button buttonEquals = new Button("=");
        Button buttonDot = new Button(".");

        for (int i = 0; i <= 9; i++){
            buttonNumbers.add(new ButtonNumber(i));
            int number = i;
            buttonNumbers.get(i).getButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (result.getText().length() < 9) {

                        if (typingNumber.getStatus().equals("no")){
                            typingNumber.setStatus("yes");
                            result.setText("" + buttonNumbers.get(number).getValue());
                        }
                        else {
                            result.setText("" + result.getText() + buttonNumbers.get(number).getValue());
                        }
                        newValue.setValue(Double.parseDouble(result.getText()));
                    }

                    System.out.println("old value:" + oldValue.getValue() + " new value:" + newValue.getValue());
                }
            });
        }

        buttonClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                result.setText("0");
                calculations.setText("");
                typingNumber.setStatus("no");
                oldValue.setValue(0);
                newValue.setValue(0);
                actualValue.setValue(0);
                status.setStatus("start");
            }
        });

        buttonOff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.hide();
            }
        });

        buttonRoot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (status.getStatus().equals("start")){
                    status.setStatus("root");
                    typingNumber.setStatus("no");
                    oldValue.setValue(Math.sqrt(newValue.getValue()));
                    calculations.setText("sqrt(" + newValue.getValueString() + ")");
                    result.setText("" + oldValue.getValueString());
                }
                else if (status.getStatus().equals("root") && typingNumber.getStatus().equals("no")){
                    calculations.setText("sqrt(" + oldValue.getValueString() + ")");
                    oldValue.setValue(Math.sqrt(oldValue.getValue()));
                    result.setText("" + oldValue.getValueString());
                }
                else if (status.getStatus().equals("root") && typingNumber.getStatus().equals("yes")){
                    typingNumber.setStatus("no");
                    oldValue.setValue(Math.sqrt(oldValue.getValue()));
                    result.setText("" + oldValue.getValueString());
                }
                else if (!status.getStatus().equals("root") &&
                        !status.getStatus().equals("start") &&
                        typingNumber.getStatus().equals("yes")){
                    newValue.setValue(Math.sqrt(newValue.getValue()));
                    if (status.getStatus().equals("addition")) {
                        calculations.setText(oldValue.getValueString() + " + sqrt(" + newValue.getValueString() + ")");
                        oldValue.setValue(oldValue.getValue() + newValue.getValue());
                    }
                    if (status.getStatus().equals("subtraction")){
                        calculations.setText(oldValue.getValueString() + " - sqrt(" + newValue.getValueString() + ")");
                        oldValue.setValue(oldValue.getValue() - newValue.getValue());
                    }
                    if (status.getStatus().equals("multiplication")){
                        calculations.setText(oldValue.getValueString() + " x sqrt(" + newValue.getValueString() + ")");
                        oldValue.setValue(oldValue.getValue() * newValue.getValue());
                    }
                    if (status.getStatus().equals("division")){
                        calculations.setText(oldValue.getValueString() + " / sqrt(" + newValue.getValueString() + ")");
                        oldValue.setValue(oldValue.getValue() / newValue.getValue());
                    }
                    status.setStatus("root");
                    typingNumber.setStatus("no");
                    result.setText("" + oldValue.getValueString());
                }
                else {
                    status.setStatus("root");
                    typingNumber.setStatus("no");
                    calculations.setText("sqrt(" + oldValue.getValueString() + ")");
                    oldValue.setValue(Math.sqrt(oldValue.getValue()));
                    result.setText("" + oldValue.getValueString());
                }
            }
        });

        buttonPercent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (status.getStatus().equals("addition")) {
                    newValue.setValue(0.01 * newValue.getValue() *  oldValue.getValue());
                    calculations.setText(oldValue.getValueString() + " + " + newValue.getValueString());
                }
                if (status.getStatus().equals("subtraction"))
                {
                    newValue.setValue(0.01 * newValue.getValue() *  oldValue.getValue());
                    calculations.setText(oldValue.getValueString() + " - " + newValue.getValueString());
                }
                if (status.getStatus().equals("multiplication")) {
                    newValue.setValue(0.01 * newValue.getValue());
                    calculations.setText(oldValue.getValueString() + " * " + newValue.getValueString());
                }
                if (status.getStatus().equals("division")) {
                    newValue.setValue(0.01 * newValue.getValue());
                    calculations.setText(oldValue.getValueString() + " / " + newValue.getValueString());
                }
            }
        });

        buttonDivision.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (status.getStatus().equals("start")){
                    status.setStatus("division");
                    typingNumber.setStatus("no");
                    oldValue.setValue(newValue.getValue());
                }
                else if (status.getStatus().equals("division") && typingNumber.getStatus().equals("no")){
                    oldValue.setValue(actualValue.getValue());
                }
                else if (status.getStatus().equals("division") && typingNumber.getStatus().equals("yes")){
                    typingNumber.setStatus("no");
                    oldValue.setValue(oldValue.getValue() / newValue.getValue());
                    result.setText("" + oldValue.getValueString());
                }
                else if (!status.getStatus().equals("division") &&
                        !status.getStatus().equals("start") &&
                        typingNumber.getStatus().equals("yes")){
                    if (status.getStatus().equals("addition"))
                        oldValue.setValue(oldValue.getValue() + newValue.getValue());
                    if (status.getStatus().equals("subtraction"))
                        oldValue.setValue(oldValue.getValue() - newValue.getValue());
                    if (status.getStatus().equals("multiplication"))
                        oldValue.setValue(oldValue.getValue() * newValue.getValue());
                    if (status.getStatus().equals("root"))
                        oldValue.setValue(Math.sqrt(oldValue.getValue()));
                    status.setStatus("division");
                    typingNumber.setStatus("no");
                    result.setText("" + oldValue.getValueString());
                }
                else {
                    status.setStatus("division");
                    typingNumber.setStatus("no");
                    oldValue.setValue(actualValue.getValue());
                }
                calculations.setText(oldValue.getValueString() + " / ");
            }
        });

        buttonMultiplication.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (status.getStatus().equals("start")){
                    status.setStatus("multiplication");
                    typingNumber.setStatus("no");
                    oldValue.setValue(newValue.getValue());
                }
                else if (status.getStatus().equals("multiplication") && typingNumber.getStatus().equals("no")){
                    oldValue.setValue(actualValue.getValue());
                }
                else if (status.getStatus().equals("multiplication") && typingNumber.getStatus().equals("yes")){
                    typingNumber.setStatus("no");
                    oldValue.setValue(oldValue.getValue() * newValue.getValue());
                    result.setText("" + oldValue.getValueString());
                }
                else if (!status.getStatus().equals("multiplication") &&
                        !status.getStatus().equals("start") &&
                        typingNumber.getStatus().equals("yes")){
                    if (status.getStatus().equals("addition"))
                        oldValue.setValue(oldValue.getValue() + newValue.getValue());
                    if (status.getStatus().equals("subtraction"))
                        oldValue.setValue(oldValue.getValue() - newValue.getValue());
                    if (status.getStatus().equals("division"))
                        oldValue.setValue(oldValue.getValue() / newValue.getValue());
                    if (status.getStatus().equals("root"))
                        oldValue.setValue(Math.sqrt(oldValue.getValue()));
                    status.setStatus("multiplication");
                    typingNumber.setStatus("no");
                    result.setText("" + oldValue.getValueString());
                }
                else {
                    status.setStatus("multiplication");
                    typingNumber.setStatus("no");
                    oldValue.setValue(actualValue.getValue());
                }
                calculations.setText(oldValue.getValueString() + " x");
            }
        });

        buttonSubtraction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (status.getStatus().equals("start")){
                    status.setStatus("subtraction");
                    typingNumber.setStatus("no");
                    oldValue.setValue(newValue.getValue());
                }
                else if (status.getStatus().equals("subtraction") && typingNumber.getStatus().equals("no")){
                    oldValue.setValue(actualValue.getValue());
                }
                else if (status.getStatus().equals("subtraction") && typingNumber.getStatus().equals("yes")){
                    typingNumber.setStatus("no");
                    oldValue.setValue(oldValue.getValue() - newValue.getValue());
                    result.setText("" + oldValue.getValueString());
                }
                else if (!status.getStatus().equals("subtraction") &&
                         !status.getStatus().equals("start") &&
                          typingNumber.getStatus().equals("yes")){
                    if (status.getStatus().equals("addition"))
                        oldValue.setValue(oldValue.getValue() + newValue.getValue());
                    if (status.getStatus().equals("multiplication"))
                        oldValue.setValue(oldValue.getValue() * newValue.getValue());
                    if (status.getStatus().equals("division"))
                        oldValue.setValue(oldValue.getValue() / newValue.getValue());
                    if (status.getStatus().equals("root"))
                        oldValue.setValue(Math.sqrt(oldValue.getValue()));
                    status.setStatus("subtraction");
                    typingNumber.setStatus("no");
                    result.setText("" + oldValue.getValueString());
                }
                else {
                    status.setStatus("subtraction");
                    typingNumber.setStatus("no");
                    oldValue.setValue(actualValue.getValue());
                }
                calculations.setText(oldValue.getValueString() + " - ");
            }
        });

        buttonAddition.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (status.getStatus().equals("start")){
                status.setStatus("addition");
                typingNumber.setStatus("no");
                oldValue.setValue(newValue.getValue());
                }
                else if (status.getStatus().equals("addition") && typingNumber.getStatus().equals("no")){
                    oldValue.setValue(actualValue.getValue());
                }
                else if (status.getStatus().equals("addition") && typingNumber.getStatus().equals("yes")){
                    typingNumber.setStatus("no");
                    oldValue.setValue(oldValue.getValue() + newValue.getValue());
                    result.setText("" + oldValue.getValueString());
                }
                else if (!status.getStatus().equals("addition") &&
                        !status.getStatus().equals("start") &&
                        typingNumber.getStatus().equals("yes")){
                    if (status.getStatus().equals("subtraction"))
                        oldValue.setValue(oldValue.getValue() - newValue.getValue());
                    if (status.getStatus().equals("multiplication"))
                        oldValue.setValue(oldValue.getValue() * newValue.getValue());
                    if (status.getStatus().equals("division"))
                        oldValue.setValue(oldValue.getValue() / newValue.getValue());
                    if (status.getStatus().equals("root"))
                        oldValue.setValue(Math.sqrt(oldValue.getValue()));
                    status.setStatus("addition");
                    typingNumber.setStatus("no");
                    result.setText("" + oldValue.getValueString());
                }
                else {
                    status.setStatus("addition");
                    typingNumber.setStatus("no");
                    oldValue.setValue(actualValue.getValue());
                }

                calculations.setText(oldValue.getValueString() + " + ");
            }
        });

        buttonEquals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                switch (status.getStatus()) {
                    case "addition" -> {
                        actualValue.setValue(oldValue.getValue() + newValue.getValue());
                        calculations.setText(oldValue.getValueString() + " + " + newValue.getValueString() + " = ");
                        oldValue.setValue(actualValue.getValue());
                        typingNumber.setStatus("no");
                    }
                    case "subtraction" -> {
                        actualValue.setValue(oldValue.getValue() - newValue.getValue());
                        calculations.setText(oldValue.getValueString() + " - " + newValue.getValueString() + " = ");
                        oldValue.setValue(actualValue.getValue());
                        typingNumber.setStatus("no");
                    }
                    case "multiplication" -> {
                        actualValue.setValue(oldValue.getValue() * newValue.getValue());
                        calculations.setText(oldValue.getValueString() + " x " + newValue.getValueString() + " = ");
                        oldValue.setValue(actualValue.getValue());
                        typingNumber.setStatus("no");
                    }
                    case "division" -> {
                        actualValue.setValue(oldValue.getValue() / newValue.getValue());
                        calculations.setText(oldValue.getValueString() + " / " + newValue.getValueString() + " = ");
                        oldValue.setValue(actualValue.getValue());
                        typingNumber.setStatus("no");
                    }
                    case "root" -> {
                        actualValue.setValue(oldValue.getValue());
                        if (!calculations.getText().endsWith("= "))
                            calculations.setText(calculations.getText() + " = ");
                        typingNumber.setStatus("no");
                    }
                }
                result.setText("" + actualValue.getValueString());
            }
        });

        buttonDot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (result.getText().length() < 10 &&
                    result.getText().length() > 0 &&
                    !result.getText().contains(".")) {

                        result.setText(result.getText() + '.');
                        double value = Double.parseDouble(result.getText());
                        newValue.setValue(value);

                }
                System.out.println("old value:" + oldValue.getValue() + " new value:" + newValue.getValue());
            }
        });


        calculations.setPrefHeight(24);
        calculations.setPrefWidth(340);
        calculations.setAlignment(Pos.BOTTOM_RIGHT);

        result.setPrefHeight(42);
        result.setPrefWidth(340);
        result.setAlignment(Pos.CENTER_RIGHT);

        buttonClear.setPrefWidth(80);
        buttonClear.setPrefHeight(60);
        Style.setStyle(buttonClear,"rgba(255, 127, 80, ", "20");

        buttonOff.setPrefWidth(80);
        buttonOff.setPrefHeight(60);
        Style.setStyle(buttonOff, "rgba(220, 20, 60, ", "20");

        buttonRoot.setPrefWidth(80);
        buttonRoot.setPrefHeight(60);
        buttonRoot.setGraphic(new ImageView(new Image("root.png")));
        Style.setStyle(buttonRoot,"rgba(34, 139, 34, ", "20");

        buttonPercent.setPrefWidth(80);
        buttonPercent.setPrefHeight(60);
        Style.setStyle(buttonPercent,"rgba(34, 139, 34, ", "20");

        buttonDivision.setPrefWidth(80);
        buttonDivision.setPrefHeight(60);
        Style.setStyle(buttonDivision,"rgba(34, 139, 34, ", "20");

        buttonMultiplication.setPrefWidth(80);
        buttonMultiplication.setPrefHeight(60);
        Style.setStyle(buttonMultiplication,"rgba(34, 139, 34, ", "20");

        buttonSubtraction.setPrefWidth(80);
        buttonSubtraction.setPrefHeight(60);
        Style.setStyle(buttonSubtraction,"rgba(34, 139, 34, ", "20");

        buttonAddition.setPrefWidth(80);
        buttonAddition.setPrefHeight(60);
        Style.setStyle(buttonAddition,"rgba(34, 139, 34, ", "20");

        buttonEquals.setPrefWidth(80);
        buttonEquals.setPrefHeight(60);
        Style.setStyle(buttonEquals,"rgba(30, 144, 255, ","20");

        buttonDot.setPrefWidth(80);
        buttonDot.setPrefHeight(60);
        Style.setStyle(buttonDot,"rgba(128, 128, 128, ","20");

        GridPane gridPane = new GridPane();

        gridPane.add(buttonClear,0,0);
        gridPane.add(buttonOff,1,0);
        gridPane.add(buttonRoot,2,0);
        gridPane.add(buttonPercent,3,0);
        gridPane.add(buttonNumbers.get(7).getButton(),0,1);
        gridPane.add(buttonNumbers.get(8).getButton(),1,1);
        gridPane.add(buttonNumbers.get(9).getButton(),2,1);
        gridPane.add(buttonDivision,3,1);
        gridPane.add(buttonNumbers.get(4).getButton(),0,2);
        gridPane.add(buttonNumbers.get(5).getButton(),1,2);
        gridPane.add(buttonNumbers.get(6).getButton(),2,2);
        gridPane.add(buttonMultiplication,3,2);
        gridPane.add(buttonNumbers.get(1).getButton(),0,3);
        gridPane.add(buttonNumbers.get(2).getButton(),1,3);
        gridPane.add(buttonNumbers.get(3).getButton(),2,3);
        gridPane.add(buttonSubtraction,3,3);
        gridPane.add(buttonNumbers.get(0).getButton(),0,4);
        gridPane.add(buttonDot,1,4);
        gridPane.add(buttonEquals,2,4);
        gridPane.add(buttonAddition,3,4);

        borderPane.setTop(calculations);
        borderPane.getTop().setStyle("-fx-background-color: rgba(255, 255, 255, 1);" +
                "-fx-effect: dropshadow(gaussian, white, 0, 0, 0, 0);" +
                "-fx-background-insets: -10;" +
                "-fx-font: 24px Arial;" +
                "-fx-font-weight: bold;");
        borderPane.setCenter(result);
        borderPane.getCenter().setStyle("-fx-background-color: rgba(255, 255, 255, 1);" +
                "-fx-effect: dropshadow(gaussian, white, 0, 0, 0, 0);" +
                "-fx-background-insets: -10;" +
                "-fx-font: 42px Arial;" +
                "-fx-font-weight: bold;");
        borderPane.setBottom(gridPane);
        borderPane.setPadding(new Insets(2));
    }
}