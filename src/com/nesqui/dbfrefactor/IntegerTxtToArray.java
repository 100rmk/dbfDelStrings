package com.nesqui.dbfrefactor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IntegerTxtToArray {

    IntegerTxtToArray(String fileName) {
    }
    public BufferedReader getFile(String file) {
        try {
            FileInputStream txtFile = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(txtFile));
            bufferedReader.close();
            return bufferedReader;
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    public List<Integer> createTxtList(BufferedReader txtFile) throws IOException {
        List<Integer> returnList = new ArrayList<>();
        while (txtFile.ready()) {
            String string = txtFile.readLine();
            if (string.matches((".\\d+ (строка):"))) {
                string = extractInt(string);
                int i = Integer.parseInt(string) + 1; // dbf first string always as title
                returnList.add(i);
            }
        }
        return returnList;
    }
    private static String extractInt(String str)
    {
        str = str.replaceAll("[^\\d]", " ");
        str = str.trim();
        str = str.replaceAll(" +", " ");

        if (str.equals(""))
            return "-1";

        return str;
    }
}
