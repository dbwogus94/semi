package com.semi.update.All.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.mail.Multipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

public class UploadFileUtils {
	private Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	static final int THUMBNAIL_WIDTH = 300;
	static final int THUMBNAIL_HEIGHT = 300;
	
	static final int PORFILE_WIDTH = 200;
	static final int PORFILE_HEIGHT = 200;
	
	
	// img 업로드
	public static String fileUpload(String uploadPath, String fileName, byte[] fileData, String id_ymdPath) throws IOException {
		// 랜덤 id 생성
		UUID uid = UUID.randomUUID(); 
		// 랜덤 id + 파일명
		String newFileName = uid + "_" + fileName;
		// 이미지 경로 : uploadPath + ymdPath(년/월/일을 경로에 추가하기 위한 path)
		String imgPath = uploadPath + id_ymdPath;
		// File 객체 생성(경로 , 이름)
		File target = new File(imgPath, newFileName);
		// 파일 복사 : 받아온 바이트 데이터(fileData)를  위에 생성한 새로운 File 객체에 복사한다.  
		FileCopyUtils.copy(fileData, target); 
		// 스프링 프레임워크에서 지원하는 기능 > 스트림을 열어 > 반복문으로 파일 복사 > flush > close
		// 내부적으로 java.nio를 통하여  inputStream과 OutputStream을 만들어 준다.
		
		return newFileName;
	}
	
	public static String[] imgUploadAndThumb(String uploadPath, String fileName, byte[] fileData, String id_ymdPath) throws IOException {
		String[] fileNames = new String[2];
		
	// 받아온 데이터로 파일객체 생성
		// 랜덤 id 생성
		UUID uid = UUID.randomUUID();  // java.util의 UUID는 랜덤으로 2f48f241-9d64-4d16-bf56-70b9d4e0e79a 이와같은 형태로 고유한 값 생성
		// 랜덤 id + 파일명
		String newFileName = uid + "_" + fileName;
		// 이미지 경로 : uploadPath + id_ymdPath(id/년/월/일/ 을 경로에 추가하기 위한 path)
		String imgPath = uploadPath + File.separator  + id_ymdPath;
		// File 객체 생성(경로 , 이름)
		File target = new File(imgPath, newFileName);		// File(File parent, String Child) :  parent 객체 폴더의 child 라는 파일에 대한 File 객체를 생성한다
		
		// 파일 복사 : 받아온 바이트 데이터(fileData)를  위에 생성한 새로운 File 객체에 복사한다.  
		FileCopyUtils.copy(fileData, target); 
		// 스프링 프레임워크에서 지원하는 기능 > 스트림을 열어 > 반복문으로 파일 복사 > flush > close
		
		
	//섬네일 생성 
		// 섬네일 파일명 : s_ + 원본 파일명
		String thumbFileName = "s_" + newFileName;
		// File 객체생성(경로 /랜덤id+원본 파일명)
		File image = new File(imgPath + File.separator + newFileName);  //File.separator == "\" >> 파일 경로 구분
		
		// File 객체생성(경로/s/섬네일 파일명)
		File thumbnail = new File(imgPath + File.separator + "s" + File.separator + thumbFileName);
		
		
		if(image.exists()) { 	// 해당 파일의 존재 유무 반환 >> 있으면 true
			thumbnail.getParentFile().mkdirs();		
			// .getParentFile() >>> thumbnail 해당 파일의 상위 경로를 가져온다. 
			// .mkdirs() >>> 경로의 폴더가없으면 해당 폴더와 그 폴더의 상위 폴더까지 생성
			
			// Thumbnails 라이브러리를 이용하여 파일의 사이즈를 변경한다. 저장한다
			Thumbnails.of(image).size(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT).toFile(thumbnail);
		}
		
		fileNames[0] = thumbFileName;   // 썸내일
		fileNames[1] = newFileName;		// 원본파일 
		
		return fileNames;
	}
	
	//  /유져id/년/월/일 경로 생성, 폴더생성
	public static String calcPath(String uploadPath, String userId) {
		  Calendar cal = Calendar.getInstance();
		  
		  // /id/yyyy
		  String yearPath = File.separator + userId + File.separator + cal.get(Calendar.YEAR);
		  // /id/yyyy/mm
		  String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);	// new DecimalFormat("00").format(1); >>> 사용하면 01  표현형식을 format해준다.
		  // /id/yyyy/mm/dd
		  String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		  
		  // 원본파일
		  makeDir(uploadPath, File.separator +userId, yearPath, monthPath, datePath);
		  // 썸내일은 s 폴더 하나더 생성하여 관리
		  makeDir(uploadPath, File.separator +userId, yearPath, monthPath, datePath + "\\s");

		  // 유져id/년/월/일
		  return datePath;
	}
	
	// 폴더생성
	// String... >>  해당 메서드의 두번째 이후 파라미터는 배열로 받는다  >>> [/id, /id/yyyy, /id/yyyy/mm, /id/yyyy/mm/dd]
	private static void makeDir(String uploadPath, String... paths) {
		  if (new File(uploadPath + paths[paths.length - 1]).exists()) { // 배열의 가장 끝 폴더가 이미 있으면 리턴 
			  return; 
		  }
		  
		  // paths = [/id, /id/yyyy, /id/yyyy/mm, /id/yyyy/mm/dd]
		  for (String path : paths) {
			  File dirPath = new File(uploadPath + path);
			  if (!dirPath.exists()) { // 배열의 폴더를 매번 검사해 없으면 폴더 생성
				  dirPath.mkdir();
			  }
		  }
		  // 의문? : mkdir()를 사용하지 않고 mkdirs()를 사용했다면 이렇게 번거롭게 코드를 짤 필요가 없지 않을까?
	}
	
	
	// 스프링에서 지원하는 MultipartFile을 사용한 파일 업로드
	public static String SP_fileUpload(MultipartFile file, String uploadPath) {
		System.out.println("FileUpload >>>>>>>>>>>>>>>>>>>> [파일 업로드 실행]");
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		// 랜덤 고유id 생성
		UUID uid = UUID.randomUUID();
		// 경로
		String path = uploadPath;
		
		String newFileName = uid + "_" +file.getOriginalFilename();
		
		try {
			inputStream = file.getInputStream();	// new FileInputStream("파일")											// file.getInputStream() : 업로드한 파일 데이터를 읽어오는 InputStream을 구한다.(종료 필요)
			System.out.println("확인 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + inputStream);
			// 폴더 생성
			File storage = new File(path);
			if(!storage.exists()) {
				storage.mkdirs();	// 상위폴더가 없으면 상위 폴더까지 생성
			}
			// 저장할 파일 생성 >> 경로 + 고유번호 + 업로드한 파일의 이름
			File newFile = new File(path + File.separator + newFileName);	// file.getOriginalFilename() : 업로드한 파일의 이름을 구한다.
			if(!newFile.exists()) {
				newFile.createNewFile();	// 경로에 새로운 빈 파일 생성
			}
			
			outputStream = new FileOutputStream(newFile);	// 쓰여질 파일을 인자로 전달
			
			// file의 크기만큼 바이트 빈 배열 생성  >> 한번에 읽을 데이터 크기설정 하는것이다.(배열의 크기많큼 한번에 읽는다)
			byte[] b = new byte[(int) file.getSize()];											// file.getSize() : 업로드한 파일의 크기를 구한다.
			//byte[] b = new byte[4096];		
			// 읽기 : 스트림에 들어간 데이터 읽기
			int read =  0; 		// 1byte : 0 ~ 255
								// int : 1byte * 4 >>> 0000 0000 0000 0000
														
			// 쓰기 : 새로 생성된 newfile에  데이터 쓰기 
			while((read = inputStream.read(b)) != -1) { 
														// inputStream.read(byte[] input): 배열의 크기만큼 읽는다. 스트림 끝에 도달하면 -1을 반환한다.
														// inputStream.read(byte[] input, int offset, int length) : offset부터 leagth까지 읽는다. 스트림 끝에 도달하면 -1을 반환 
				outputStream.write(b, 0, read);			// outputStream.write(byte[ ] b, int off, int len) :  출력 스트림으로 주어진 바이트 배열 b[off]부터 len개까지의 바이트를 보냅니다.
														// outputStream.write(byte[ ] b) : 출력 스트림에 인자로 전달된 파일에 바이트 배열 b의 모든 바이트를 보냅니다(입력합니다).
				outputStream.flush();					// 잔여 바이트가 있다면 모두 내보내자
			}
			return newFileName;
		} catch (IOException e) {
			System.out.println("[String fileUpload] inputStream io 에러");
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				System.out.println("[String fileUpload] 스트림 close 에러");
				e.printStackTrace();
			}
		}
	return "mentor01.png";
	}

	
	// 프로필 파일 업로드
	public static String SP_ProfileUpload(MultipartFile file, String uploadPath) {
		System.out.println("FileUpload >>>>>>>>>>>>>>>>>>>> [프로필 업로드 실행]");
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		// 랜덤 고유id 생성
		UUID uid = UUID.randomUUID();
		// 경로
		String path = uploadPath;
		
		String newFileName = uid + "_" + file.getOriginalFilename();
		
		try {
			inputStream = file.getInputStream();												// file.getInputStream() : 업로드한 파일 데이터를 읽어오는 InputStream을 구한다.(종료 필요)
			// 폴더 생성
			File storage = new File(path);
			if(!storage.exists()) {
				storage.mkdirs();	// 상위폴더가 없으면 상위 폴더까지 생성
			}
			// 저장할 파일 생성 >> 경로 + 고유번호 + 업로드한 파일의 이름
			File newFile = new File(path + File.separator + newFileName);						// file.getOriginalFilename() : 업로드한 파일의 이름을 구한다.
			
			outputStream = new FileOutputStream(newFile);	// 쓰여질 파일을 인자로 전달 >> 이 때 파일 생성
			
			// file의 크기만큼 바이트 빈 배열 생성 >> 한번에 읽을 데이터 크기설정 하는것이다.(배열의 크기많큼 한번에 읽는다)
			byte[] b = new byte[(int) file.getSize()];											// file.getSize() : 업로드한 파일의 크기를 구한다.
			// 읽기 : 스트림에 들어간 데이터 읽기
			int read =  0;									
														
			// 쓰기 : 새로 생성된 newfile에  데이터 쓰기 
			while((read = inputStream.read(b)) != -1) { 
				outputStream.write(b, 0, read);			
				outputStream.flush();					
			}
			
			// 프로필 파일명 : P_ + 원본 파일명
			String thumbFileName = "P_" + newFileName;
			
			// File 객체생성(경로 /랜덤id+원본 파일명)
			File image = new File(path + File.separator + newFileName);  //File.separator == "\" >> 파일 경로 구분
					
			// File 객체생성(경로/프로필 파일명)
			File ProfileImg = new File(path + File.separator + thumbFileName);
					
			// Thumbnails 라이브러리를 이용하여 파일의 사이즈를 변경한다. 저장한다
			Thumbnails.of(image).size(PORFILE_HEIGHT, PORFILE_HEIGHT).toFile(ProfileImg);
			
			// 프로필 파일이 없으면?
			if(!ProfileImg.exists()) {
				return "mentor01.png";
			}
			
			return thumbFileName;
		} catch (IOException e) {
			System.out.println("[String fileUpload] inputStream io 에러");
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				System.out.println("[String fileUpload] 스트림 close 에러");
				e.printStackTrace();
			}
		}
		return "mentor01.png";
	}
	
	
}
