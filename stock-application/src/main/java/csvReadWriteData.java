import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class csvReadWriteData {
    public static void main(String[] args) {
        List<StockData> data = new ArrayList<StockData>();
        for(int i=0;i< args.length;i++)
        {
            readCSVData(data,args[i]);
        }

        writeCSVData(data);

    }

    public static void readCSVData(List<StockData> data,String path) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVReader csvReader = new CSVReader(reader);
            String[] nextRecord;
            double range;
            csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                StockData sd = new StockData();
                range = Double.parseDouble(nextRecord[3]) - Double.parseDouble(nextRecord[4]);
                sd.setDailyRange(String.valueOf(range));
                data.add(sd);
            }
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public static void writeCSVData(List<StockData> data) {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File("C://temp/data.csv");
        try {

            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            String[] header = {"Range", "RunningRange"};
            writer.writeAll(Collections.singleton(header));
            for (int i = 0; i < data.size(); i++) {
                StockData sData = data.get(i);
                if (i == 0) {
                    String[] dRange = new String[2];
                    dRange[0] = sData.getDailyRange();
                    dRange[1] = sData.getDailyRange();
                    sData.setTotalRange(sData.getDailyRange());
                    writer.writeNext(dRange);
                } else {
                    StockData prevSData = data.get(i - 1);
                    String[] dRange = new String[2];
                    dRange[0] = sData.getDailyRange();
                    double tRange = Double.parseDouble(sData.getDailyRange()) + Double.parseDouble(prevSData.getTotalRange());
                    dRange[1] = String.valueOf(tRange);
                    sData.setTotalRange(String.valueOf(tRange));
                    writer.writeNext(dRange);
                }
            }

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
