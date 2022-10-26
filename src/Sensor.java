import java.util.*;

public class Sensor {

    private final SensorType sensorType;
    private final List<String> lines;
    private final List<Double> values;
    private static int captorsInError = 0;

    public Sensor(SensorType sensorType, List<String> lines) {
        this.sensorType = sensorType;
        this.lines = lines;
        this.values = this.getValues();
    }

    public void showAllInformation() {
        System.out.println(this.sensorType.getFrenchName() + " minimum: " + getMin());
        System.out.println(this.sensorType.getFrenchName() + " maximum: " + getMax());
        System.out.println(this.sensorType.getFrenchName() + " moyenne: " + getAverage());
    }

    private List<Double> getValues() {
        return this.lines.stream()
                .filter(line -> line.charAt(0) == this.getCharType())
                .filter(line -> line.charAt(1) == ',')
                .map(this::getValue)
                .filter(Objects::nonNull)
                .toList();
    }

    private Double getValue(String line) {
        try {
            return Double.parseDouble(line.substring(this.sensorType.getStartIndex()));
        } catch (NumberFormatException n) {
            captorsInError++;
            return null;
        }
    }

    private char getCharType() {
        return this.sensorType.name().toUpperCase().charAt(0);
    }

    private String getMin() {
        return this.values
                .stream()
                .min(Double::compare)
                .map(Objects::toString)
                .orElse(StringUtils.NO_VALUES);
    }

    private String getMax() {
        return this.values
                .stream()
                .max(Double::compare)
                .map(Objects::toString)
                .orElse(StringUtils.NO_VALUES);
    }

    private String getAverage() {
        double average = this.values
                .stream()
                .mapToDouble(d -> d)
                .summaryStatistics()
                .getAverage();

        return average != 0
                ? String.valueOf(Math.round(average * 100.0) / 100.0)
                : StringUtils.NO_VALUES;
    }

    public static int getCaptorsInError() {
        return captorsInError;
    }
}
