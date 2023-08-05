package com.codeshu.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CodeShu
 * @date 2023/8/5 15:02
 */
public class MyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("I'm MyServlet,I am running...");
		resp.getWriter().write("I'm MyServlet,I am running...");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		this.doGet(req, resp);
	}
}
