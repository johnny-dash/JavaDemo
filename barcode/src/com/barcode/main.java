package com.barcode;

/*  author Johnny Mao 
 *  date 2014.7.27
 *  time 10:00 am
 *  main.java
 */

import java.util.List;
import java.util.Scanner;

import com.barcode.IO.FileReader;
import com.barcode.IO.FilePrinter;
import com.barcode.engine.iterator;
import java.util.ArrayList;
import javax.swing.*;


public class main {
    public static void main(String argv[]) {
		String txtfilepath ="/Users/mzd/Desktop/test.txt";
		String excelfilepath = "/Users/mzd/Desktop/test.xls";
		//String txtfilepath ="D:/Data/inputdata.txt";
		//String excelfilepath = "D:/Data/inputdata.xls";
		List<String> barcodeList = new ArrayList<String>();
		System.out.println("Please put the input data at D:/Data File. And change the name into inputdata.txt or inputdata.xls");
		System.out.println("Please select the type of input data. Enter 1 as txt. Enter 2 as excel");
		Scanner scanner =new Scanner(System.in);
		int type = scanner.nextInt();
		
		System.out.println("Plesae enter the length of barcode, the location of starting counting number, the length of counting number respectively:");
		
		int barcodelength = scanner.nextInt();
		int key = scanner.nextInt();
		int countlength =scanner.nextInt();
		if (type == 1) {
			FileReader.readTextFile(txtfilepath,barcodeList,barcodelength);
		}
		else if (type == 2) {
			FileReader.readExcelFile(excelfilepath, barcodeList,barcodelength);
		}
		else {
			System.out.println("You are entering a invalid type number!");
		}
		
		
		List<String> result = iterator.generateNumber(key, countlength, barcodeList);
		
		
		
		
		FilePrinter.outputTXT(result);
		FilePrinter.outputExcel(result);
		
		JOptionPane.showMessageDialog(null, "我们输出的jar已经可以运行了", "测试jar的标题", JOptionPane.OK_OPTION); 
	}
}
