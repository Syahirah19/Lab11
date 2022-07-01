package demo.ftmk.utem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * Servlet implementation class DateServlet
 */
public class DateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request,
    		HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	// Get writer
    	PrintWriter writer= response.getWriter();
    			
    	// Get param
    	int paramValue = Integer.parseInt(request.getParameter("param"));
    	
    	// Get current date
    			LocalDate now = LocalDate.now();
    			String date = now.toString();
    		
    			if (paramValue == -1) {
    				// Yesterday date
    				LocalDate yesterday = now.minusDays(1);
    				date = yesterday.toString();
    			}
    			
    			else if (paramValue == 1) {
    				// Yesterday date
    				LocalDate tomorrow = now.plusDays(1);
    				date = tomorrow.toString();
    			}
    			
    			// Display current date
    			writer.println("The selected date is "+ date);
		
		
    }			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
