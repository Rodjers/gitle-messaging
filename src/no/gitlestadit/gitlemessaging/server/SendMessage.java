/**
 * 
 */
package no.gitlestadit.gitlemessaging.server;

import javax.servlet.http.*;

import java.io.IOException;
/**
 * @author Oddgeir Gitlestad
 *
 */
public class SendMessage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
    		resp.getWriter().println("Method GET not supported. Use POST!");
        
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp) 
    		throws IOException{
    		resp.getWriter().println("Message sent to " + req.getParameter("userId"));
    	}
}
