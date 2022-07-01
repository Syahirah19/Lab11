package demo.ftmk.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import demo.ftmk.product.Product;

/**
 * Servlet implementation class OrderRedirectServlet
 */
public class OrderRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException {
			
			//errorHandling
			if ((request.getParameter("product") == null) || (request.getParameter("quantity") == null)) {
				response.sendRedirect("zeroOrder.html");
				return;
			}
				
			//session
			HttpSession session = request.getSession();
		
			// Get orderedProducts from session and
			List<OrderedProduct> OrderedProducts = (List<OrderedProduct>) session.getAttribute("OrderedProducts");
			
			// Validate list - instantiate new list if the list is null
			//nak jadikan attribute list tu jadi array
			if (OrderedProducts == null)
				OrderedProducts = new ArrayList<OrderedProduct>();
					
			// Get data from a web form
			int productId = Integer.parseInt(request.getParameter("Product"));
			int quantity = Integer.parseInt(request.getParameter("Quantity"));
			
			// Wrap data into an object of OrderedProduct
			Product product = new Product();
			product.setProductId(productId);
			
			OrderedProduct orderedProduct = new OrderedProduct();
			orderedProduct.setOrderedProduct(product); //ambil nama product > set dalam orderedProduct letak dalam ordered product 
			orderedProduct.setQuantity(quantity);		
			
			
			// Add object of OrderedProduct into a list
			OrderedProducts.add(orderedProduct);
			
			
			// Add list to session
			session.setAttribute("orderedProducts", OrderedProducts);
			
			// Redirect to the same page
			response.sendRedirect("orderRedirectForm.html");
			}

}


//masa create servlet awal awal kita tak tahu dia jadi client atau host.
//kalau nak jadi client = declaration dkt doPost
//kalau nak jadi host = declaration dkt doGet