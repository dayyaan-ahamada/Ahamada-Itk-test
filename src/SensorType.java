public enum SensorType {
    TEMPERATURE("Température", 4),
    HUMIDITY("Humidité", 2),
    PRESSURE("Pression", 17);

    private final String frenchName;
    private final int startIndex;

    private SensorType(String frenchName, int startIndex) {
        this.frenchName = frenchName;
        this.startIndex = startIndex;
    }

    public String getFrenchName() {
        return frenchName;
    }

    public int getStartIndex() {
        return startIndex;
    }
}
