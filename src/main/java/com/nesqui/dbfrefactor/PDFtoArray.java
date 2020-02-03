package com.nesqui.dbfrefactor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PDFtoArray extends PDFTextStripper  {
    static List<Integer> lines = new ArrayList<>();

    public PDFtoArray() throws IOException {
    }

        public static List<Integer> getListFromPDF(String fileName) throws IOException {
            PDDocument document = null;

            try {
                document = PDDocument.load(new File(fileName));
                PDFTextStripper stripper = new PDFtoArray();
                stripper.setSortByPosition(true);
                stripper.setStartPage(0);
                stripper.setEndPage(document.getNumberOfPages());

                Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
                stripper.writeText(document, dummy);

            } finally {
                if (document != null) {
                    document.close();
                }
            }
            return lines;
        }
        @Override
        protected void writeString(String str, List<TextPosition> textPositions) {
            if (str.matches(("\\d+ (строка):"))) {
                str = extractInt(str);
                int i = Integer.parseInt(str) - 1;
                lines.add(i);
            }
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

