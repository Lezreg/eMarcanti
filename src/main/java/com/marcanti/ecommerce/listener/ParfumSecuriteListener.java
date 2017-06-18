package com.marcanti.ecommerce.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

public class ParfumSecuriteListener implements PhaseListener {
	
	private static final long serialVersionUID = 1L;
	
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

	public void afterPhase(PhaseEvent event) {
	}

	public void beforePhase(PhaseEvent event) {
		
		FacesContext facesContext = event.getFacesContext();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "no-cache");
		// Stronger according to blog comment below that references HTTP spec
		response.addHeader("Cache-Control", "no-store");
		response.addHeader("Cache-Control", "must-revalidate");
		// some date in the past
		response.addHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");
		
		
		/*HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String uri = request.getRequestURL().toString();
		boolean isPrivatePage = uri.contains("private");
		Membre membre = (Membre)request.getSession().getAttribute("membre");
		if(membre==null && isPrivatePage){
			request.getSession().invalidate();
			NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
			nh.handleNavigation(facesContext, null, "login");
		}*/
		
		
		//System.out.println("url : " + uri);
		
		/*ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String,String> requestHeader = ext.getRequestHeaderMap();
		String  urlRefered = requestHeader.get("referer");
		System.out.println("urlRefered : " + urlRefered);*/
		
		//System.out.println("Phase ID : " + event.getPhaseId());
	}
}
