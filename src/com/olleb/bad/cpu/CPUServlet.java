package com.olleb.bad.cpu;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Àngel Ollé Blázquez
 * 
 *         High CPU usage Servlet
 * 
 */

@WebServlet(name = "CPUServlet", urlPatterns = { "/cp" })
public class CPUServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int t = 100;
	private static final Matcher matcher = Pattern.compile("(a*)*b").matcher(new String(new char[t]).replace("\0", "a"));
	private final Set<Callable<String>> callables;

	{
		callables = new HashSet<Callable<String>>();
		for (int i = 1; i <= Runtime.getRuntime().availableProcessors() / 2; i++) {
			final int j = i;
			callables.add(new Callable<String>() {
				@Override
				public String call() throws Exception {
					matcher.find();
					return "Task #" + j;

				}
			});
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println(":-)");
		response.getWriter().close();
		try {
			Executors.newCachedThreadPool().invokeAny(callables);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
