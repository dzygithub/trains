package com.inter.trains;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * load data from external file
 */
public class LoadDataFile {

    private List<String> dataList;
    private File file;

    public LoadDataFile(String filePath) {
        this.dataList = new ArrayList<String>();
        this.file = new File(filePath);
        this.read(this.file);
    }


    public void read(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = "";
            while ((str = bufferedReader.readLine()) != null) {
                if(!"".equals(str.trim())) dataList.add(str);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getDataList() {
        return dataList;
    }

}
