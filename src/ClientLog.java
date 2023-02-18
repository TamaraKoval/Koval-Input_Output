import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {
    private List<String[]> basketLog = new ArrayList<>();
    private static final String[] HEADER = {"ProductNum", "Amount"};

    public ClientLog() {
        basketLog.add(HEADER);
    }

    public void log(int productNum, int amount) {
        String[] pair = {Integer.toString(productNum), Integer.toString(amount)};
        basketLog.add(pair);
    }

    public void exportAsCSV(File csvFile) throws IOException {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true));
            for (String[] pair : basketLog) {
                writer.writeNext(pair);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

