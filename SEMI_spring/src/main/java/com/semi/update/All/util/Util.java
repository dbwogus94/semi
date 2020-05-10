package com.semi.update.All.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	// 해당폴더 파일개수
	public static int getFileCount(String fileRoute) {
		int count = 0;
		File file = new File(fileRoute);
		for (File f : file.listFiles()) {
			if (f.isFile()) {
				count++;
			}
		}
		// System.out.println(count);
		return count;
	}
	
	// 해당폴더 파일경로+파일명 가져오기
	public static String[] getFilesName(String fileRoute) {
		File file = new File(fileRoute);
		int num = getFileCount(fileRoute);
		int index = 0;
		String[] fileNames = new String[num];
		for(File f : file.listFiles()) {		// listFiles() 해당 경로의 파일들과 폴더의 파일을 배열로 반환한다.
			if(f.isFile()) {			 		// 파일이면 true
				String name = f.getName();		// 파일이나 폴더의 이름을 넘겨준다
				fileNames[index] = name;
				index++;
			}
		}
		//System.out.println(Arrays.toString(arrS));
		return fileNames;
	}
	

	// Date -> String (yyyyMMdd) 형식으로 변환
	public static String isString(Date date) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		return transFormat.format(date);
	}

	// 한 자리수를 두 자리수로 변환
	public static String isTwo(String date) {
		return Integer.parseInt(date) < 10 ? "0" + date : date;
	}

	// 요일을 반환
	public static String getDayOfWeek(int dayOfWeek) {
		String planDayofweek = "";
		switch (dayOfWeek) {
		case 1:
			planDayofweek = "일";
			break;
		case 2:
			planDayofweek = "월";
			break;
		case 3:
			planDayofweek = "화";
			break;
		case 4:
			planDayofweek = "수";
			break;
		case 5:
			planDayofweek = "목";
			break;
		case 6:
			planDayofweek = "금";
			break;
		case 7:
			planDayofweek = "토";
			break;
		}
		return planDayofweek;
	}

}
