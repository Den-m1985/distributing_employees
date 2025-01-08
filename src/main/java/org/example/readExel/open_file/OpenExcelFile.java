package org.example.readExel.open_file;

import org.apache.poi.ss.usermodel.Workbook;
import org.example.readExel.XlsxReader;

public class OpenExcelFile {

    public Workbook openFile(String extension, String defaultPath) {
        String pathXLS = new ChoosePath().choosePath(extension, defaultPath);
        return new XlsxReader().xlsxRead(pathXLS);
    }

}
