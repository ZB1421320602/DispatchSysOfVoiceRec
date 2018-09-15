package com.telecom.ccs.utils.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceScanPath {

    private String province;

    public  String getCurrentScanPath(String prefixPath) {


        //使用Date
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = sdf.format(date);

        String year = datetime.substring(0,4);
        String month = datetime.substring(5,7);
        String day = datetime.substring(8,10);
        String hour = datetime.substring(11,13);

        String relativePath =prefixPath+"/"+province+"/"+year+"/"+month+"/"+day+"/"+hour+"/";

       return relativePath;


    }
}
