package com.semi.update.All.mail;

import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SMTPDto {
	private static final int port = 465;
	private String host;
	private String user;
	private String tail;
	private String password;
	// 위의 필드속성은 servlet-content.xml에서 di/ioc를 통하여 값을 가져온다
	// 그렇기 때문에 속성을 담아주는 생성자가 없음 >>> 따로 사용하고 싶다면 set를 통해 필드속성을 넣어주어야 한다.

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTail() {
		return tail;
	}

	public void setTail(String tail) {
		this.tail = tail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static int getPort() {
		return port;
	}

	
	private Properties props = System.getProperties();
	// {java.runtime.name=Java(TM) SE Runtime Environment, sun.boot.library.path=C:\Program Files\Java\jdk1.8.0_221\jre\bin, java.vm.version=25.221-b11 ....
	// System.getProperties();에는 위와같은 정보가 들어있음
	
	private boolean setEnv() {
		props.put("mail.smtp.host", host);		// 메일 전송을 처리해줄 서버 
		props.put("mail.smtp.psrt", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.enable", "true");
		props.put("mail.smtp.trust", host);
		return true;
	}

	// 인증번호 만들기
	public StringBuffer createAuthNo() throws Exception {
		// 인증번호 생성
		// MimeMessage 세션을 생성한 뒤 메세지 작성할 때
		StringBuffer temp = new StringBuffer();// String은 값이 고정적일때, buffer은 값이 유동적일때 사용한다
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		return temp;
	}

	
	// sendingHead 메서드 : 메일 인증 설정 및 메세지 작성 객체 생성
		// 1) 프로퍼티스를 통해 javax.mail.Session을 생성
		// 2) 생성된 session을 바인딩하여 메세지를 추가하고 메일 전송에 사용되는 클래스를 리턴한다.  
	private Message sendingHead() {
		// 발신자 메일 주소 및 비밀번호 설정 하기 ->2중잠금 설정의 경우 (모바일 인증번호 발송시 ) 따로 비밀번호를 설정하여 그것을 불러와야함
																
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
		/* 
		 # 코드 설명 
		 	1) javax.mail.Session : 메일 API에서 사용되는 특성과 기본값을 함께 수집합니다, 단일 기본 세션은 데스크톱의 여러 응용 프로그램에서 공유 할 수 있습니다
		 	2) Session.getDefaultInstance(프로퍼티스, 새로운 인증관련 클래스) : 프로퍼티스를 이용하여 새로운 인증객체를 생성하며, 해당 메서드는 새로운 객체를 생성시에만 프로퍼티스를 사용하여 설정값을 사용한다. 
		 	3) props : 설정정보가 들어있는 전역변수
		 	4) new javax.mail.Authenticator() : 네트워크 연결에 대한 인증을 얻는 방법을 구현한 클래스의 메서드
		*/
			
		// 인어클래스의 필드속성 : new javax.mail.Authenticator()의 필드속성(전역변수)
			String un = user; 	
			String pw = password;

		// 인어클래스의 메서드 : 인증에 사용할 아이디 비번 설정
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				// javax.mail.PasswordAuthentication :  Authenticator에서 사용하는 데이터 저장소입니다. 단순히 사용자 이름과 비밀번호를 저장하는 저장소입니다.
				return new javax.mail.PasswordAuthentication(un, pw);
			}
		}); 	// 인어클래스 끝 : new javax.mail.Authenticator() 생성 끝
		
		session.setDebug(true); // for debug
		
		Message msg = new MimeMessage(session); // 위에서 생성한 session을 바인딩하여 MimeMessage 생성
		/*	
		 # 코드설명
			 Message : 해당 클래스는  보내는 사람, 받는 사람, 제목, 내용과 같이 메일과 관련된 내용을 지정할 수 있도록 해준다.
			 MimeMessage : Message클래스에 추가로 다양한  Mime 타입을 지원하는 메세지를 전송할때 사용하는 클래스 
		 */
		return msg;
	}

	
	// 메세지를 직접 작성해 메일 전송을 수행
	public boolean sendAuthNo(String receiver, String code) throws Exception {
		// System.getProperties();를 통해 가져온 properties에 추가정보를 담는다. 
		setEnv(); 										// 1) 설정에 사용되는 전역변수 props를 설정한다.
		Message msg = sendingHead();					// 2) 프로퍼티를 사용하여 메일 인증 및 메세지 작성에 사용되는 클래스 생성

		// 메일제목
		msg.setSubject("안녕하세요 운토티 회원가입 인증번호입니다");	// 3) 메세지 내용 작성		
		// 메일내용
		msg.setText("인증번호는 : " + code + "\n" + "를 입력해 주세요");
		// 발신자 셋팅 , 보내는 사람의 이메일주소를 한번 더 입력합니다. 이때는 이메일 풀 주소를 다 작성해주세요.
		msg.setFrom(new InternetAddress(user + tail));
		// 수신자셋팅
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

		Transport.send(msg); 							// 4) 메세지 전송
		return true;
	}

	
	
	
	// 전송할 메세지 만들기: 수신자의 주소와 수신자에게 보내야하는 내용들을 세팅
	private void sendingBody(Message msg, String receiver, String title, String text) throws Exception {
		msg.setFrom(new InternetAddress(user + tail)); // 발신자 셋팅 , 보내는 사람의 이메일주소를 한번 더 입력합니다. 이때는 이메일 풀 주소를 다 작성해주세요.
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver)); // 수신자셋팅
		msg.setSubject(title); // 제목셋팅
	}

	// 파일과 함께 전송
	public boolean sendMail(String receiver, String title, String text, String filePath, String fileName)
			throws Exception {
		setEnv();											// 1) 인증에 사용될 전역변수 프로퍼티 설정
		Message msg = sendingHead();						// 2) 프로퍼티를 사용하여 메일 인증 및 메세지 작성에 사용되는 클래스 생성
		sendingBody(msg, receiver, title, text);			// 3) 메세지 작성에 사용되는 클래스에 전송할 텍스트 메세지 작성

		if (filePath != null && filePath.length() > 0) {	// 4) 파일 추가
			Multipart multipart = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(text, "UTF-8");
			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filePath);
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName(MimeUtility.encodeText(fileName, "UTF-8", null));
			multipart.addBodyPart(textBodyPart); // add the text part
			multipart.addBodyPart(attachmentBodyPart); // add the attachement part
			msg.setContent(multipart);
		}
		Transport.send(msg);								// 5) 전송
		return true;
	}

	// 파일 없이 전송
	public boolean sendMail(String receiver, String title, String text) throws Exception {
		setEnv();									// 1) 인증에 사용될 전역변수 프로퍼티 설정
		Message msg = sendingHead();				// 2) 프로퍼티를 사용하여 메일 인증 및 메세지 작성에 사용되는 클래스 생성
		sendingBody(msg, receiver, title, text);	// 3) 메세지 작성에 사용되는 클래스에 전송할 텍스트 메세지 작성
		msg.setText(text);	
		Transport.send(msg);						// 4) 전송
		return true;
	}

}
