package com.barcode.IO;


/*  author Johnny Mao 
 *  date 2014.7.27
 *  time 10:00 am
 *  FileReader.java
 */

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import jxl.Workbook;
import jxl.Sheet;

public class FileReader {
     public static void readTextFile(String filepath,List<String> itemList,int length) {
		try {
			String encoding = "GBK";
			File file = new File(filepath);
			if (file.isFile() && file.exists()) {
				InputStreamReader reader = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String linetext = null;
				while ((linetext = bufferedReader.readLine())!= null) {
					linetext = linetext.substring(0,length).trim();
					itemList.add(linetext);
					//System.out.println(linetext);
					
				}
				reader.close();
				System.out.println("Successfully read txt data");
				
			}
			else {
				System.out.println("找不到指定文件");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("读取文件有错");
			e.printStackTrace();
		}
	}
     
    public static void readExcelFile(String filepath,List<String> itemList,int length) {
		try {
			Workbook workbook = Workbook.getWorkbook(new File(filepath));
			Sheet sheet = workbook.getSheet(0);
			int rows = sheet.getRows();
			for (int i = 0; i < rows; i++) {
				String linetext =  sheet.getCell(0,i).getContents();
				linetext = linetext.substring(0,length).trim();
				itemList.add(linetext);
				//System.out.println(linetext);
			}
			workbook.close();
			System.out.println("Successfully read excel data");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
