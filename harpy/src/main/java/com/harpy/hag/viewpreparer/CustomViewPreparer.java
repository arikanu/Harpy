package com.harpy.hag.viewpreparer;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;



public class CustomViewPreparer implements ViewPreparer {

	
	//HttpServletRequest request = ServletActionContext.getRequest();
	
	//HttpSession session = request.getSession();
	
    public void execute(TilesRequestContext tilesRequest, AttributeContext attributeContext) throws PreparerException {
    	
    	// uri'den title cekip yazdirmakta.
    	try {
    		SecurityContextHolderAwareRequestWrapper wrapper = (SecurityContextHolderAwareRequestWrapper) tilesRequest.getRequest();
    		String uri = wrapper.getRequestURI();
    		boolean partialUri = uri.contains("WEB-INF");
    		if (partialUri == false) {
        		String contextPath = wrapper.getContextPath();
            	String reducedUri = uri.substring(contextPath.length());
            	reducedUri = reducedUri.substring(1).replace("/", ".");
            	//attributeContext.putAttribute("title", new Attribute(reducedUri));
    		}
        	
		} catch (Exception e) {
			// TODO: handle exception
		}  	
    	
        
    }

}