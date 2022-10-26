import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private List<String> lines;

    public FileReader(String pathToFile) {
        this.lines = readFile(pathToFile);
    }

    public List<String> readFile(String pathToFile) {
        List<String> lines = new ArrayList<>();
        try (
                FileInputStream inputStream = new FileInputStream(pathToFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));) {
            while (reader.ready()) {
                lines.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<String> getLines() {
        return lines;
    }
}
