package org.mifos.webdriver.pageobjects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by pranathb on 13/05/14.
 */
public class CSVtoStringArray {
    public static Collection<String[]> csvFileAsCollectionOfStringArrays(String csvFileName) {

        List<String[]> csvRows = new ArrayList<String[]>();
        String rawCSVRow ;
        BufferedReader csvFileReader = null;
        String delimiter="#";

        System.out.println("Reading data from " + csvFileName);
        try {
            csvFileReader = new BufferedReader(new FileReader(csvFileName));

        } catch (FileNotFoundException e) {
            System.out.println("Could not find file " + csvFileName);
            e.printStackTrace();
        }
        int rowNumber=1;
        try {

            while ((rawCSVRow=csvFileReader.readLine())!=null) {
                String delimitedItems[] = rawCSVRow.split(delimiter);
                csvRows.add(delimitedItems);
                rowNumber++;
            }

        } catch (IOException e) {
            System.out.println("Error reading row number " + rowNumber);
            e.printStackTrace();
        }

        try {
            csvFileReader.close();
        } catch (IOException e) {
            System.out.println("Error closing file " + e.getMessage());
            e.printStackTrace();
        }

        return csvRows;
    }

}
