package com.barcode.IO;

/*  author Johnny Mao 
 *  date 2014.7.27
 *  time 10:00 am
 *  FilePrinter.java
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.List;


import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class FilePrinter {
      public static void outputTXT(List<String> itemList) {
    	  try {
			FileWriter output = new FileWriter("/Users/mzd/Desktop/output.txt");
			BufferedWriter bf = new BufferedWriter(output);
			for(String I:itemList)
			{
				bf.write(I+"\r\n");
			}
			bf.flush();
			bf.close();
			System.out.println("Successfully build txt data :-)");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
	}
      
    public static void outputExcel(List<String> itemList) {
		try {
			int x,y=0;
			WritableWorkbook workbook = Workbook.createWorkbook(new File("/Users/mzd/Desktop/output.xls"));
				
			WritableSheet sheet =workbook.createSheet("first sheet", 0);
			
			for(String I:itemList){
				Label contant = new Label(0,y,I);
				sheet.addCell(contant);
				y++;
			}
			
			workbook.write();
			workbook.close();
			System.out.println("Successfully build excel data :-)");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
