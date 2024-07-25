package chap04.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie/ex/*")
public class CookieServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("요청 URL: " + req.getRequestURL());
		System.out.println("요청 URI: " + req.getRequestURI());
		
		String root = "/chap04/cookie/ex/";
		String uri = req.getRequestURI();
		
		// 하나의 서블릿에서 uri에 따라 다른 처리를 진행해줄 수 있다.
		String cmd = uri.substring(root.length());
		
		System.out.printf("URI가 %s일 때, cmd는 %s입니다.\n", uri, cmd);
		
		if (cmd.equals("list")) {
			// 클라이언트가 보유하고 있는 쿠키는 모든 요청에 함께 실려오게 된다.(경로만 맞다면)
			// 쿠키를 하나만 꺼내는 메서드가 기본적으로 구현되어 있지 않다.
			Cookie[] cookies = req.getCookies();
			
			// 어트리뷰트에는 모든 자바 객체를 추가할 수 있지만 Object로 업캐스팅된다.
			req.setAttribute("cookies", cookies);
			
			req.getRequestDispatcher("/cookie/cookie_list.jsp").forward(req, resp);
		} else if (cmd.equals("add")) {
			// 새로운 쿠키를 응답에 함께 실어보내면 응답을 받은 웹 브라우저에 추가한다.
			Cookie cookie = new Cookie("fruit", "apple");
			
			cookie.setMaxAge(-1); // 웹 브라우저가 꺼지면 함께 삭제되는 쿠키(세션 쿠키)
			cookie.setPath("/chap04/cookie/");
			
			Cookie cookie2 = new Cookie("coffee", "mega-coffee");
			cookie2.setMaxAge(5); // 수명이 5초 밖에 안 되는 쿠키
			cookie2.setPath("/chap04/cookie/");
			
			Cookie cookie3 = new Cookie("employee", "Smith");
			cookie3.setMaxAge(60 * 5); // 수명이 5분인 쿠키
			cookie3.setPath("/chap04/cookie/");
			
			resp.addCookie(cookie);
			resp.addCookie(cookie2);
			resp.addCookie(cookie3);
			
			resp.sendRedirect("/chap04/cookie/cookie_index.jsp");
		} else if (cmd.equals("delete")) {
			// 이미 존재하는 쿠키를 삭제할 때는 요청에 실려있는 쿠키의 수명을 0으로 수정한 후에 응답에 실어보내주면 된다.
			Cookie[] cookies = req.getCookies();
			
			if (cookies != null) {
				String to_delete = req.getParameter("cookie-name");
				
				System.out.println("내가 지워야 하는 쿠키 이름: " + to_delete);
				
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(to_delete)) {
						// 해당 이름의 쿠키의 수명을 0으로 만들고 응답에 실어 삭제
						cookie.setMaxAge(0);
						// 이름만 맞다고 삭제되는 게 아니라 경로까지 맞아야 해당 쿠키를 정확하게 지칭하는 것이 된다.
						cookie.setPath("/chap04/cookie/");
						resp.addCookie(cookie);
						break;
					}
				}
			}
			// 이 서블릿의 list 커맨드로 다시 요청해주세요~ 라고 응답
			resp.sendRedirect("/chap04/cookie/ex/list");
		} else if (cmd.equals("modify")) {
			String to_modi = req.getParameter("to_modi");
			String modi_value = req.getParameter("modi_value");
			
			System.out.println("바꿔야 하는 쿠키 이름: " + req.getParameter("to_modi"));
			System.out.println("바꾸고 싶은 값: " + req.getParameter("modi_value"));
			
			// 쿠키 값을 수정하고 싶으면 해당 쿠키를 꺼내서 값을 바꾼 후 응답에 다시 실어놓으면 된다.
			Cookie[] cookies = req.getCookies();
			
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(to_modi)) {
						cookie.setValue(modi_value);
						cookie.setPath("/chap04/cookie/");
						resp.addCookie(cookie);
						break;
					}
				}
			}
			
			resp.sendRedirect("/chap04/cookie/ex/list");
		} else {
			// URI가 원하는 cmd가 아닐 때 그냥 메인으로 리다이렉트
			resp.sendRedirect("chap04/cookie/cookie_index.jsp");
		}
	}
}
