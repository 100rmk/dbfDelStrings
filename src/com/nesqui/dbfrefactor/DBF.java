package com.nesqui.dbfrefactor;

import com.linuxense.javadbf.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DBF {
    private String fileName;
    private DBFField[] fields;
    private List<Object[]> dbfValues = new ArrayList<>();

    public DBF(String fileName) {
        this.fileName = fileName;
    }

    public void read() {
        DBFReader reader = null;

        try {

            reader = new DBFReader(new FileInputStream(fileName));

            int numberOfFields = reader.getFieldCount();
            fields = new DBFField[numberOfFields];

            for (int i = 0; i < numberOfFields; i++) {
                fields[i] = reader.getField(i);
            }

            Object[] rowObjects;

            while ((rowObjects = reader.nextRecord()) != null) {
                dbfValues.add(rowObjects);
            }
        } catch (DBFException | IOException e) {
            e.printStackTrace();
        } finally {
            DBFUtils.close(reader);
        }
    }

    public void dbfWriter(List<Object[]> list) {
        DBFWriter dbfWriter = new DBFWriter(new File(fileName));
        dbfWriter.setFields(fields);
        for (Object[] objects : list) {
            dbfWriter.addRecord(objects);
        }

        dbfWriter.close();
    }

    public void removeByValues(List<Integer> values) {
        for (int i = dbfValues.size()-1; i >= 0; i--) {
            if (values.contains(i)) {
                dbfValues.remove(i);
                values.remove(values.indexOf(i));
                i--;
            }
        }
    }

    public List<Object[]> getDbfValues() {
        return dbfValues;
    }

    public void setDbfValues(List<Object[]> dbfValues) {
        this.dbfValues = dbfValues;
    }

    public DBFField[] getFields() {
        return fields;
    }

    public void setFields(DBFField[] fields) {
        this.fields = fields;
    }
}
