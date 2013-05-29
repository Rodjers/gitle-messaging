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
public class RestSend extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.getWriter().println("GET not supported. Use POST!");
		
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	
		
   		String appKey = req.getParameter("appKey");
   		
   		resp.getWriter().println("Message sent to all users of: " + appKey);
    }
    
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	
    	resp.getWriter().println("DELETE not supported. Use POST!");
    	
    }
}
