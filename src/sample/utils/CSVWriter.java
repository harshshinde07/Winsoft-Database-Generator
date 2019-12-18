package sample.utils;

import java.io.File;
import java.io.FileWriter;

public class CSVWriter {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String FILE_HEADER = "date,customerid,customername,branch,branchcode,password";

    public static void writeCsvFile(String date, String customerid, String customername, String branch, String branchcode, String password) {

        String fileName = "C:/winDairy/log.csv";

        FileWriter fileWriter = null;

        File log = new File("C:/winDairy/log.csv");

        try {
            fileWriter = new FileWriter(fileName, true);

            if(log.length() == 0) {
                fileWriter.append(FILE_HEADER);
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

            fileWriter.append(date);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(customerid);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(customername);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(branch);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(branchcode);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(password);
            fileWriter.append(NEW_LINE_SEPARATOR);

            System.out.println("Added successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }
    }
}
