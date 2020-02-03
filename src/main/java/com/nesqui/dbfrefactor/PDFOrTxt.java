package com.nesqui.dbfrefactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class PDFOrTxt {

    public static List<Integer> pdfTxt(String fileDirectory) throws IOException {
        if (fileDirectory.contains(".pdf")) {
          return PDFtoArray.getListFromPDF(fileDirectory);
        } else if (fileDirectory.contains(".txt")) {
            BufferedReader txtToArray = IntegerTxtToArray.getFile(fileDirectory);
            assert txtToArray != null;
           return IntegerTxtToArray.createTxtList(txtToArray);
        } else return null;
    }
}
