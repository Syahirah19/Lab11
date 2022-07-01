package demo.ftmk.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class CheckOutRedirectServlet
 */
@WebServlet("/demo/checkOutRedirectServlet")
public class CheckOutRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpSeropsvlet#HttpServlet()
     */
    public CheckOutRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); //ambil value from server pass dkt doPost through this method
		//eg: cust click button dia akan send data & doGet akan catch data 
	}
	//doGet = dapat value
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//doPost = send value
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session
		HttpSession session = request.getSession();
	
		// Redirect servlet to zeroOrder.html if orderedProducts does not exist
		if (session.getAttribute("orderedProducts") == null) {
				response.sendRedirect("zeroOrder.html");
				return;
		}
		
		
		// Get orderedProducts from session and
		List<OrderedProduct> OrderedProducts = (List<OrderedProduct>) session.getAttribute("OrderedProducts");
	
		// Calculate total quantity and total order
		// Invoke the appropriate method from OrderDataManager
		OrderDataManager orderManager = new OrderDataManager();
		Order order = orderManager.processOrder(OrderedProducts);
						
		// Get writer [system.out.print display dkt console]
		PrintWriter writer =response.getWriter();
						
		// Display details of order
		writer.print("<html><h3>Details of Order</h3>");
		writer.print("Order Id: " + order.getOrderId()  + "<br>");
		writer.print("Date: " + order.getOrderDate()  + "<br>");
				
		writer.print("<table>");
		writer.print("\t<tr>");
		writer.print("\t<td>No</td>");
		writer.print("\t<td>Name</td>");
		writer.print("\t<td>Price Per Unit (RM)</td>");
		writer.print("\t<td>Quantity</td>");
		writer.print("\t<td>Sub Total (RM)</td>");
		writer.print("\t</tr>");
				
		// Display list of ordered products
		int number = 0;
		int totalQuantity = 0;
		double subTotal = 0;
		double totalAmountBeforeTax = 0;
		for (OrderedProduct orderedProduct : order.getOrderedProducts()) {

			subTotal = orderedProduct.getQuantity() * orderedProduct.getProduct().getPrice();
			totalQuantity += orderedProduct.getQuantity();
			totalAmountBeforeTax += subTotal;

			writer.print("\t<tr>");
			writer.print("\t<td>" + (number++) + "</td>");
			writer.print("\t<td>" + orderedProduct.getProduct().getName() + "</td>");
			writer.print("\t<td>" + orderedProduct.getProduct().getPrice() + "</td>");
			writer.print("\t<td>" + orderedProduct.getQuantity() + "</td>");
			writer.print("\t<td>" + String.format("%.2f", subTotal) + "</td>");
			writer.print("\t</tr>");
		}
				
		writer.print("</table><br>");
		writer.print("Total Quantity of Product: " + totalQuantity + "<br>");
		writer.print("Service Tax: RM " + String.format("%.2f", order.getServiceTax()) + "<br>");
		writer.print("Total Amount (Before Tax): RM " + String.format("%.2f", totalAmountBeforeTax) + "<br>");
		writer.print("<b>Total Amount (After Tax): RM " + String.format("%.2f", order.getTotalAmount()) + "</b><br>");
		writer.print("<a href=\"..\"index.jsp\">Home</a>");

		// Remove attribute from session
		session.removeAttribute("orderedProducts");
		
	}

}
