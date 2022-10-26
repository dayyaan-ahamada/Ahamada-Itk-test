import java.util.List;

public class ControllerSensor {

    private FileReader fileReader;

    public ControllerSensor(String pathToFile) {
        this.fileReader = new FileReader(pathToFile);
    }

    public void showAllInformation() {
        List<Sensor> sensors = List.of(
                new Sensor(SensorType.TEMPERATURE, fileReader.getLines()),
                new Sensor(SensorType.PRESSURE, fileReader.getLines()),
                new Sensor(SensorType.HUMIDITY, fileReader.getLines())
        );

        System.out.println("Nombre de capteurs en erreur : " + getNumbersOfStations());
        System.out.println("Nombre de stations météos : " + Sensor.getCaptorsInError());

        sensors.forEach(sensor -> {
            System.out.println("--------------------------------");
            sensor.showAllInformation();
        });
    }

    private int getNumbersOfStations() {
        int nbOfStations = 0;

        for (String line : this.fileReader.getLines()) {
            char firstChar = line.charAt(0);
            char secondChar = line.charAt(1);
            if (firstChar != '#' && secondChar != ',')
                nbOfStations++;
        }
        return nbOfStations;
    }
}
