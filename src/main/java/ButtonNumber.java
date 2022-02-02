import javafx.scene.control.Button;

public class ButtonNumber {

    private final Button button = new Button();

    public Button getButton() { return button; }

    private final int value;

    public int getValue() { return value; }

    public ButtonNumber(int value) {
        this.value = value;
        button.setText("" + value);
        button.setPrefWidth(80);
        button.setPrefHeight(60);
        Style.setStyle(button,"rgba(128, 128, 128, ", "20");
    }
}
