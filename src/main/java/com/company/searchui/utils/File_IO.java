package com.company.searchui.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * File I/O Static Utility Class
 *
 * @author phildolganov
 */
public class File_IO {
    /**
     * loadProps - method to load a Properties file
     *
     * @param file - the file to load
     * @return Properties - The properties to retrieve
     * @throws Exception
     */
    public static Properties loadProperties(String file) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(file));

        return props;
    }

    /**
     * lookupError - method to retrieve error messages using code
     *
     * @param propFilePath - the property file including path to read
     * @param code - the error code to use
     * @return String
     * @throws Exception
     */
    public static String lookupError(String propFilePath, String code) throws Exception {
        Properties exceptionProps = new Properties();
        exceptionProps.load(new FileInputStream(propFilePath));

        // get error message using code as key
        return exceptionProps.getProperty(code);
    }

    /**
     * extractData_CSV - method to extract CSV file data for use in testing
     *
     * @param csvFile - the CSV file to read
     * @param rowID - The rowID to parse
     * @return List<String>
     * @throws Exception
     */
    public static List<String> extractData_CSV(String csvFile, String rowID) throws Exception {
        List<String> rows = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        String line = "";

        while ( (line = reader.readLine()) != null) {
            if (line.startsWith(rowID)){
                rows.add(line);
            }
        }
        reader.close();
        return rows;
    }

    /**
     * extractData_LOG - method to extract Log file data for use in testing
     *
     * @param logFile - the logfile to read
     * @return List<String>
     * @throws Exception
     */
    public static List<String> extractData_LOG(String logFile) throws Exception {
        List<String> rows = new ArrayList<>();

        BufferedReader reader =new BufferedReader(new FileReader(logFile));
        String line = "";

        while ( (line = reader.readLine()) != null) {
                rows.add(line);
        }
        reader.close();
        return rows;
    }

    /**
     * writeFile - method to stuff a row entry into a file
     *
     * @param file - the file to write to
     * @param rowData - the line to write into the file
     * @throws Exception
     */
    public static void writeFile(String file, String rowData) throws Exception {

        Boolean bFound = false;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String getLine = "";

        // verify if row entry exists
        while ( (getLine = reader.readLine()) != null){
            if (getLine.contains(rowData)){
                bFound = true;
                break;
            }
        }
        reader.close();

        if (bFound != true){
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.append(rowData);
            writer.newLine();
            writer.close();
        }
    }
}
