package com.nesqui.dbfrefactor;

import java.io.*;
import java.util.*;

import com.linuxense.javadbf.*;


public class Main {

    public static void main(String[] args) throws IOException, DBFException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь к pdf.txt: ");
        String fileDirectoryDBF = scanner.nextLine(); // print directory txt file

        System.out.print("Введите путь к dbf.dbf: ");
        String dbfFile = scanner.nextLine(); // print directory of dbf

        System.out.print("Введите путь куда записать новый файл (*.dbf): ");
        String resultDbfFile = scanner.nextLine();

        BufferedReader txtToArray = IntegerTxtToArray.getFile(fileDirectoryDBF);
        assert txtToArray != null;
        List<Integer> txtIntList = IntegerTxtToArray.createTxtList(txtToArray);

        DBF dbfValues = new DBF(dbfFile);
        dbfValues.read();
        DBF dbfResult = new DBF(resultDbfFile);

        dbfResult.setFields(dbfValues.getFields());
        dbfValues.removeByValues(txtIntList);
        dbfResult.dbfWriter(dbfValues.getDbfValues());
    }
}
