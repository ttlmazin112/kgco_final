package com.exam.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class tokenTest {
	public static void main(String[] args) {
		String str ="2019-6";
		
		 StringTokenizer st = new StringTokenizer(str,"-");
		String year =st.nextToken();
		String month = st.nextToken();
		System.out.println(year+":"+month);
		
		Date curDate = new Date();
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd");
		SimpleDateFormat sdfMonthyear = new SimpleDateFormat("yyyyMM");
		int curDateNum = Integer.parseInt(sdfDate.format(curDate));
		int curyearMonthNum = Integer.parseInt(sdfMonthyear.format(curDate));
		System.out.println(curyearMonthNum);
		
	}//main
		 
}
