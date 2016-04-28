package com.barcode.engine;

/*  author Johnny Mao 
 *  date 2014.7.27
 *  time 10:00 am
 *  iterator.java
 */

import java.util.List;
import java.util.ArrayList;

public class iterator {
      public static List<String> generateNumber(int key,int length,List<String> itemlist) {
		 String left ="";
		 String right ="";
		 int add_number;
		 key = key -1;
		 int begin =key;
		 int end = key+length;
		 List<String> listofresult = new ArrayList<String>();
		 for(String I:itemlist){
			 int l = I.length();
			 left = I.substring(0, begin);
			 right = I.substring(end,l);
			 add_number = Integer.parseInt(I.substring(begin, end));
			 for (int j = 0; j < 5; j++) {
				listofresult.add(left+add_number+right);
				add_number++;
			}
			 
		 }
		 
		 return listofresult;
	}
}
