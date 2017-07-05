package com.olleb.bad.oome2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Àngel Ollé Blázquez
 * 
 *         Heap Space OOME Servlet - Hiden Error
 * 
 */

@WebServlet(name = "OOMEServlet2", urlPatterns = { "/om2" })
public class OOMEServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Object> o = new ArrayList<Object>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			while (true) {
				d();
				ff();
			}
		} catch (Error e) { // Hide OOME
			response.getWriter().println("Ooops! You got an error!");
		} finally {
			response.getWriter().close();
		}
		response.getWriter().println("No errors.");
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	void d() {

	}

	void ff() {
		f();
	}

	void f() {
		o.add(0.);
	}

}
