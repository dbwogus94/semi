package com.semi.update.All.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadFileUtils {

	// 다운로드 >> 바이트 배열로 리턴
	public static byte[] file_toByte(File file) {
		byte[] outputData = null;
		InputStream in = null;
		OutputStream out = null;
		int buffer_size = 4096;

		try {
			// file객체를 통해 읽어올 파일 가져온다.
			in = new FileInputStream(file);

			// 인자로 넣어진 크기의 버퍼 용량 (4096 바이트)으로 새 바이트 배열 출력 스트림을 작성합니다
			out = new ByteArrayOutputStream(buffer_size);	
			/**
			 * # java.io.ByteArrayOutputStream
			 * 1) ByteArrayInputStream은 바이트 배열을 차례대로 읽어 들이기 위한 클래스입니다.
			 * 2) ByteArrayOutputStream은 내부적으로 저장 공간이 있어 해당 메소드를 이용해서 출력하게 되면 출력되는 모든 내용들이 내부적인 저장 공간에 쌓이게 됩니다.
			 * 3) 생성자에 인자로 받은 정수는 버퍼의 사이즈가 됩니다. 인자를 사용하지 않았을 경우 버퍼 용량의 초기값 는 32 바이트입이며, 이 사이즈는 필요에 따라서 커집니다.
			 * 4) .toByteArray() 메소드를 이용하면 저장된 모든 내용이 바이트 배열로 반환
			 */

			int byteCount = 0;
			byte[] buffer = new byte[buffer_size]; // 출력스트림과 동일한 크기의 바이트 배열 선언
			int bytesRead = -1;

			// .read()는 파라미터로 받은 배열의 크기만큼 한번에 읽으며 읽은 데이터를 입력스트림으로 보낸다. >>> 모두 읽었으면 -1을 리턴한다.
			while ((bytesRead = in.read(buffer)) != -1) { 	// 파일 읽기 실행
				out.write(buffer, 0, bytesRead); 			// .write(byte[ ] b, int off, int len) : 바이트 배열 byte[] off번지 부터 len까지 배열의 개수를 출력스트림으로 보냅니다.
				byteCount += bytesRead;
			}

			if (file.length() == byteCount) {
				return ((ByteArrayOutputStream) out).toByteArray(); // out.toByteArray() : 새로 할당 된 바이트 배열을 만듭니다. 크기는이 출력
																	// 스트림의 현재 크기이며 버퍼의 유효한 내용이 여기에 복사되었습니다.
			}
		} catch (FileNotFoundException e) {
			System.out.println("[file Download FileNotFoundException]");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush(); // 잔여 스트림 비우기
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return outputData;
	}

	public static void main(String[] args) throws IOException {
		//File f = new File("C:\\Users\\1994d\\Desktop\\아이콘 이미지\\conimg.jpg");
		//System.out.println("a : " + f.length());
		//System.out.println(DownloadFileUtils.file_toByte(f).length);
		
		// 스트림을 이용한 콘솔의 입력값을 출력  
//		InputStreamReader rd = new InputStreamReader(System.in);
//		int c = rd.read();
//		System.out.println(c);
		
		//파일 읽기
//		File f = new File("C:\\Users\\1994d\\Desktop\\test.txt");
//		FileInputStream fin = new FileInputStream(f);  // 파일을 열고 스트림 fin 생성
//
//		int c;
//		while((c = fin.read()) != -1){               //파일 끝까지 한 바이트씩 c에 읽어드린다
//		 System.out.print("A : " + (char)c);         // 바이트 c를 문자로 변환하여 화면에 출력한다.
//		 System.out.println();
//		 System.out.print("B : " +c);                // 바이트 c를 문자로 변환하여 화면에 출력한다.
//		 System.out.println();
//		}
//		fin.close();
		
		// 파일 쓰기 
//		FileOutputStream fout = new FileOutputStream("C:\\Users\\1994d\\Desktop\\testOut.txt");
//		int num[]={49,50,51,52,53};
//		//int num[]={1,4,63,-5,50};
//		byte b[]={65,66,67,68,69,70,13};
//		for(int i=0; i<num.length; i++) {
//			fout.write(num[i]);      // 파일에 num 배열의 정수 값으 ㄹ바이트 정보로 기록한다.
//			fout.write(58);
//			fout.write(b);           // 파일에 바이트 배열 b의 내용을 보두 그대로 기록한다.
//		}
//		fout.flush();
//		fout.close();
	
		// 버퍼 필터 스트림으로 파일 복사
		File f = new File("C:\\Users\\1994d\\Desktop\\[파일 업로드] JAVA io.txt");
		
		InputStream in = new FileInputStream(f);  // 파일을 열고 스트림 fin 생성
		OutputStream out = new FileOutputStream("C:\\Users\\1994d\\Desktop\\testOut.txt");		// 이때 빈 파일 생성
		
		BufferedInputStream bin = new BufferedInputStream(in);		
		BufferedOutputStream bout = new BufferedOutputStream(out);
		
		int bData;
		while(true) {
			bData = bin.read();
			if(bData == -1) {
				break;
			}
			System.out.print((char)bData);
			bout.write(bData);
		}
		bin.close();
		bout.close();

	}
	
	

}
