package com.olleb.bad.core;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Àngel Ollé Blázquez
 * 
 *         Crash Servlet
 * 
 */

@WebServlet(name = "CoreServlet", urlPatterns = { "/death" })
public class CoreServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int DeathNumber = 6;
	private static final int min = 1, max = 10;

	static {
		// Not sure. os.arch -> JVM ARCH, not OS arch.
		URL s = Thread.currentThread().getContextClassLoader()
				.getResource(String.format("/resources/libcore-%s.so", Arch.valueOf(System.getProperty("os.arch"))));
		System.load(s.toString().replace("vfs:", ""));
		// System.loadLibrary(libcore-%s",Arch.valueOf(System.getProperty("os.arch"))));
	}

	public native void core();

	static enum Arch {
		amd64 {
			@Override
			public String toString() {
				return "x86_64";
			}
		},
		x86_64 {
			@Override
			public String toString() {
				return "x86_64";
			}
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int n = ThreadLocalRandom.current().nextInt(min, max + 1);
		if (n == DeathNumber)
			core();
		else {
			response.getWriter().println("Death number: " + DeathNumber + ". Your number: " + n + ". You're lucky!");
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
