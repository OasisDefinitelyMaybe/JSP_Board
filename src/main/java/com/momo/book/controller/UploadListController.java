package com.momo.book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.momo.dao.FileDao;
import com.momo.dto.FileDto;

/**
 * Servlet implementation class UploadListController
 */
@WebServlet("/upload/list")
public class UploadListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * 첨부파일의 목록을 조회하여 반환한다.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   // 파일목록을 조회합니다.
       FileDao dao = new FileDao();
       List<FileDto> list = dao.getList();
       System.out.println("list : " + list);
       dao.close();
       request.setAttribute("list", list);
       request.getRequestDispatcher("/10upload/list.jsp").forward(request, response);
    }	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}