package com.loushy.mymall.action;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class WenJuan {

    public static void main (String [] ages){
       Map<String,List> map  = getMapByJson(getJsonByText());
       System.out.println(map);
        imputExcel(getMapByJson(getJson(map)));
    }

    public static void imputExcel(Map<String,List> map){
        //第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("数据");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("被试");
        cell = row.createCell(1);
        cell.setCellValue("性别（1男,2女）");
        cell = row.createCell(2);
        cell.setCellValue("生源地（1城市,2农村）");
        cell = row.createCell(3);
        cell.setCellValue("是否为独生子女（1是,2否）");
        cell = row.createCell(4);
        cell.setCellValue("就读方式（1走读,2住校）");
        cell = row.createCell(5);
        cell.setCellValue("父母（1父,2母）");
        cell = row.createCell(6);
        cell.setCellValue("1");
        cell = row.createCell(7);
        cell.setCellValue("2");
        cell = row.createCell(8);
        cell.setCellValue("3");
        cell = row.createCell(9);
        cell.setCellValue("4");

        //第五步，写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
        map.forEach((k,v)->{
            HSSFRow row1 = sheet.createRow(Integer.parseInt(k));
            //创建单元格设值
            int i =0;
            if (v.indexOf(2)==0&&v.size()<9){
                for (Object index:v) {
                    System.out.println(i+5+":"+index);
                    row1.createCell(i+5).setCellValue(index.toString());
                    i++;
                }
            }else{
                for (Object index:v) {
                    System.out.println(i+":"+index);
                    row1.createCell(i).setCellValue(index.toString());
                    i++;
                }
            }
        });

        //将文件保存到指定的位置
        try {
            FileOutputStream fos = new FileOutputStream("E:\\数据.xls");
            workbook.write(fos);
            System.out.println("写入成功");
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Map getMapByJson(String json){
        return JSON.parseObject(json,Map.class);
    }


    public static List getListByJson(String json){
        return JSON.parseObject(json,List.class);
    }

    public static String getJson(Map<String,List> list){
        return JSON.toJSONString(list);
    }

    public static String getJsonByText(){
        String json = "";
        try{
            FileInputStream fis = new FileInputStream("E:\\数据.txt");
            byte[] buf = new byte[1024];
            int length = 0;
            //循环读取文件内容，输入流中将最多buf.length个字节的数据读入一个buf数组中,返回类型是读取到的字节数。
            //当文件读取到结尾时返回 -1,循环结束。
            while((length = fis.read(buf)) != -1){
	            json = json+new String(buf,0,length);
            }
            //最后记得，关闭流
            fis.close();
        }catch (Exception e){

        }
        System.out.println(json);
        String[] arr = json.split("]");
        List<String> list = new ArrayList<String>(Arrays.asList(arr));
        Integer index =1;
        String getJson ="{";
        for (String str:list) {
            if (index==list.size()){
                getJson=getJson+"\""+index+"\":"+str+"]}";
            }else {
                getJson=getJson+"\""+index+"\":"+str+"],";
            }

            index++;
        }
        getJson = getJson.replaceAll("\r|\n", "");
        getJson = getJson.replaceAll("，", ",");
        return getJson;
    }
}
