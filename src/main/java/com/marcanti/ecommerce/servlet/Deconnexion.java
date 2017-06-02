package com.marcanti.ecommerce.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Deconnexion extends HttpServlet {

	/**
	 * Numéro de version. <br />
	 * Pour faire plaisir au compilateur.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * url de redirection vers la page d'authentification
	 */
	private String mRedirectUrlLogin;

	/**
	 * Gestion des Logs.
	 */
	private final Log mLog = LogFactory.getLog(getClass());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		response.sendRedirect(mRedirectUrlLogin);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}

	@Override
	public void init(ServletConfig pConfig) throws ServletException {
		super.init(pConfig);
		ServletContext ctx = pConfig.getServletContext();

		// ApplicationConfig mAppBean = (ApplicationConfig)
		// ctx.getAttribute(ApplicationConfig.BEAN_KEY);

		// mRedirectUrlLogin =
		// ApplicationInit.redirectUrl(mAppBean.getUrlAuthSucces(), ctx);

		if (mLog.isInfoEnabled()) {
			mLog.info(String.format("Url de redirection ", mRedirectUrlLogin));
		}
	}

}
