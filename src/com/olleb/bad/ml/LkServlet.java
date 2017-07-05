package com.olleb.bad.ml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Àngel Ollé Blázquez
 * 
 *         Memory Leaker Servlet
 * 
 */
@WebServlet(name = "LkServlet", urlPatterns = { "/lk" })
public class LkServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static List<Pepito> list = Collections.synchronizedList(new ArrayList<Pepito>());
	private static int req = 0;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		for (int i = 0; i < 1000; i++)
			new LkServlet();
		// 10000 objects on every request
		for (int i = 0; i < 1000; i++)
			list.add(new Pepito());
		response.getWriter().println("Gimme more requests!");
		response.getWriter().println("Requests: " + ++req);
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

	class Pepito {
		Juanito p[] = new Juanito[1000];
		{
			for (int i = 0; i < p.length; i++)
				p[i] = new LkServlet().new Juanito();
		}
	}

	class Juanito {

	}

}
