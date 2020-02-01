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

        BufferedReader txtToArray = IntegerTxtToArray.getFile(fileDirectoryDBF);
        assert txtToArray != null;
        List<Integer> txtIntList = IntegerTxtToArray.createTxtList(txtToArray);

        DBF dbfValues = new DBF(dbfFile);
        DBF dbfResult = new DBF("");

        dbfResult.setFields(dbfValues.getFields());
        dbfValues.removeByValues(txtIntList);
        dbfResult.dbfWriter(dbfValues.getDbfValues());
    }
}

//        FileInputStream file = new FileInputStream(fileDirectoryDBF.equals("") ? "D:/pdf.txt" : fileDirectoryDBF); //fileDirectoryDBF
//        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
//        ArrayList<Integer> deleteValues = new ArrayList<>();
//        //ПОЛУЧАЕМ СТРОКИ ИЗ PDF.TXT
//        while (reader.ready()) {
//            String string = reader.readLine();
//            if (string.matches("\\d+ (строка):")) {
//                string = extractInt(string);
//                int i = Integer.parseInt(string) + 1;
//                deleteValues.add(i); //test for dbf who have first empty string
//            }
//        }
//        System.out.println(deleteValues.size());
//        for (Integer i : deleteValues) {
//            System.out.println(i);
//        }
//        reader.close();
//        System.out.println("Успешно!\n");
//
//        //DBF READER
//        System.out.print("Введите путь dbf файла, который нужно отредактировать: ");
//        String dbfFile = scanner.nextLine(); // считываем файл dbf
//        System.out.println("Успешно!\n");
//        DBFReader dbfReader = null; // заносим в объект dbfReader класса DBFReader Значение null
//        List<Object[]> dbfRowList = new ArrayList<>(); // создаем ArrayList с Genericom Object[] и заносим его в dbfRowList
//        //ЗАПИСЬ ОТРЕДАКТИРОВАННОГО DBF В НОВЫЙ ФАЙЛ
//        System.out.print("Введите путь для нового(отредактированного) dbf файла: ");
//        String pathResult = scanner.nextLine(); // указываем путь отредактированного dbf файла
//        System.out.println("Успешно!\n");
//        DBFWriter dbfWriter = new DBFWriter(new File(pathResult)); // вводим dbf в поток для записи
//        try {
//            dbfReader = new DBFReader(new FileInputStream(dbfFile.equals("") ? "D:/dbf.dbf" : dbfFile)); // вводим dbf в поток для чтения
//            int numberOfFields = dbfReader.getFieldCount(); // значение строк в dbf
//            DBFField[] dbfFields = new DBFField[numberOfFields];
//            for (int i = 0; i < numberOfFields; i++) {
//
//                dbfFields[i] = dbfReader.getField(i);
//            }
//            dbfWriter.setFields(dbfFields);
//            Object[] row;
//
//            while ((row = dbfReader.nextRecord()) != null) {
//                dbfRowList.add(row);
//            }
//        } catch (DBFException e) {
//            System.out.println("NAEBNULOS'");
//        }
//		finally {
//            DBFUtils.close(reader);
//        }
//        // Удаление строк из dbfRowList
//        System.out.println("DO UDALENIYA " + dbfRowList.size());
//        for (int i = dbfRowList.size() - 1; i >= 0; i--) {
//            if (deleteValues.contains(i)) {
//                dbfRowList.remove(i);
//                deleteValues.remove(deleteValues.indexOf(i));
//                if (deleteValues.size() == 0) break;
//            }
//        }

//        int delta = 0;
//        for (int i = 0; i < dbfRowList.size()>; i++) {
//            if (deleteValues.contains(i)) {
//                dbfRowList.remove(i-delta);
//                deleteValues.remove(deleteValues.indexOf(i));
//
//            }
//        }
//        int delta = 0;
//        for (Integer i: deleteValues) {
//            removeFromDBF(i - delta, dbfRowList);
//            delta++;
//        }
//
//        System.out.println(deleteValues.size());
//
//        for (Object[] i : dbfRowList) {
//            dbfWriter.addRecord(i);
//        }
//
//        System.out.println("POSLE UDALENIYA " + dbfRowList.size());
//        dbfWriter.close();
//
//    }
//
//    static void removeFromDBF(int pos, List<Object[]> dbfList) {
//        dbfList.remove(pos);
//    }
