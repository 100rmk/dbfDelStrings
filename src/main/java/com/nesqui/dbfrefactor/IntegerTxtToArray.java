package com.nesqui.dbfrefactor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IntegerTxtToArray {

    public static BufferedReader getFile(String file) {
        try {
            FileInputStream txtFile = new FileInputStream(file);

            return new BufferedReader(new InputStreamReader(txtFile));

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            return null;
        }

    }
    public static List<Integer> createTxtList(BufferedReader txtFile) throws IOException {

        List<Integer> returnList = new ArrayList<>();

        while (txtFile.ready()) {

            String string = txtFile.readLine();

            if (string.matches((".\\d+ (строка):"))) {

                string = extractInt(string);

                int i = Integer.parseInt(string) - 1; // dbf first string always as title
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
