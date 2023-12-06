package com.sh.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet - 업무로직 처리. jsp로 forwarding
 * JSP - html 작성
 *
 */
@WebServlet("/testPerson3.do")
public class TestPersonServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// 요청메세지 인코딩 처리, POST 요청 시에만 필요
		req.setCharacterEncoding("utf-8");	
		
	// 사용자 입력값 처리
		String name = req.getParameter("name");
		String color = req.getParameter("color");
		String animal = req.getParameter("animal");
		String[] foods = req.getParameterValues("food");
		System.out.println(name);
		System.out.println(color);
		System.out.println(animal);
		System.out.println(Arrays.toString(foods));
		
	// 업무로직 : 사용자의 취향을 고려해 추천 키워드 제공
	// switch statement : JDK 17 이상
		String recommendation = switch(color) {
		case "빨강" -> "매운 떡볶이";
		case "노랑" -> "노랑 동화책";
		case "초록" -> "초록색 상추";
		case "파랑" -> "시원한 사이다";
		default -> "저도 몰라요";
		};
		System.out.println(recommendation); // 서버생성 데이터
		// jsp전달하기 - request 객체에 속성으로 저장
		req.setAttribute("recommendation", recommendation);
	
	// jsp 포워딩 (RequestDispatcher)
	// 파일 경로(절대 경로) src/main/webapp(웹 루트) 이하부터 작성
	RequestDispatcher reqDispatcher =
			req.getRequestDispatcher("/WEB-INF/views/testPersonResult.jsp");
	reqDispatcher.forward(req, resp);
	
	}	
}
