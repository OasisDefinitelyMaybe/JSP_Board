package com.momo.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.momo.dao.MemberDao;
import com.momo.dto.MemberDto;

/**
 * Servlet implementation class MemberRegController
 */
@WebServlet("/book/regProcess")
public class MemberRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * 회원가입을 진행한다.
     * 주소표시줄에서 바로 호출하는 경우, 기본방식으로 요청이 되어지므로
     * 405오류가 발생할 수 있다. 
     */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("doGet() 요청!!"); 
		//한글깨짐처리
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.append("post방식으로 요청해주세요");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/book/regProcess 요청");
         //파라미터 수집
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name"); 
        String email = request.getParameter("email");
        
        // dto를 생성
        MemberDto dto = new MemberDto(id, pw, name, "", email);
        System.out.println(dto);
        MemberDao dao = new MemberDao();
        
        // 회원가입
        int res = dao.regMember(dto);
        // 자원반납
        dao.close();
        
        // 메세지 출력후 페이지 전환 (성공 : 로그인페이지로 이동, 실패 : 뒤로가기)
        if(res > 0) {
        	// 회원가입 성공
        	request.setAttribute("msg", "회원가입 성공!! 로그인 후 이용해주세요.");
        	request.setAttribute("url", "/06session/ex/servletEx/loginForm.jsp");
        } else {
        	// 회원가입 실패
        	//     - 컬럼의 사이즈보다 입력값이 큰경우, 타입이 맞지 않는 경우 -> 입력화면에 유효성검증을 추가하여 해결 
           request.setAttribute("msg", "회원가입 실패!! 관리자에게 문의해주세요.");
        }
        
        // forward방식으로 페이지 전환 - request영역을 공유
        request.getRequestDispatcher("/book/msgBox.jsp").forward(request, response);
	}
}











