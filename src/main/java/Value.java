import java.text.DecimalFormat;

public class Value {

    private double value;
    private String valueString;


    public String getValueString() {
        return valueString;
    }

    public double getValue() {
        DecimalFormat format = new DecimalFormat("0.#####");
        valueString = format.format(value);
        return value;
    }

    public void setValue(double value) {
        DecimalFormat format = new DecimalFormat("0.#####");
        valueString = format.format(value);
        this.value = value;
    }
}
