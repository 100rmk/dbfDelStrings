package com.nesqui.dbfrefactor;

import java.io.*;
import java.util.*;


import com.linuxense.javadbf.*;



public class Main {

        public static void main(String[] args) throws IOException, DBFException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь к *.pdf or *.txt: ");
        String fileReadDirectory = scanner.nextLine(); // print directory

        List<Integer> pdfOrTxtList = PDFOrTxt.pdfTxt(fileReadDirectory);

        System.out.print("Введите путь к dbf.dbf: ");
        String dbfFile = scanner.nextLine(); // print directory of dbf

        System.out.print("Введите путь куда записать новый файл *.dbf: ");
        String resultDbfFile = scanner.nextLine();

        DBF dbfValues = new DBF(dbfFile);
        dbfValues.read();
        DBF dbfResult = new DBF(resultDbfFile);

        dbfResult.setFields(dbfValues.getFields());
        dbfValues.removeByValues(pdfOrTxtList); // its ok assert already in PDFOrTxt.java
        dbfResult.dbfWriter(dbfValues.getDbfValues());

    }
}
