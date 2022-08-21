package ru.korolev;

import ru.korolev.persist.Product;
import ru.korolev.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {
    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        productRepository.insert(new Product("apple", 100));
        productRepository.insert(new Product("lemon", 200));
        productRepository.insert(new Product("bread", 30));
        productRepository.insert(new Product("milk", 70));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        if (request.getPathInfo() == null) {

            writer.println("<table>");
            writer.println("<tr>");
            writer.println("<th>ProductId</th>");
            writer.println("<th>ProductName</th>");
            writer.println("<th>ProductCost</th>");
            writer.println("</tr>");
            for (Product product : productRepository.findAll()) {
                writer.println("<tr>");
                writer.println("<td> <a href=\"http://localhost:8080" + request.getContextPath() + request.getServletPath()
                        + "/" + product.getId() + "\">" + product.getId() + "</a></td>");
                writer.println("<td>" + product.getProductName() + "</td>");
                writer.println("<td>" + product.getCost() + "</td>");
                writer.println("</tr>");
            }
            writer.println("</table>");
        } else {
            writer.println("<h2><a href=\"http://localhost:8080" + request.getContextPath() + request.getServletPath()
                    + "\">BACK</a></h2>");
            int id = Integer.parseInt(request.getPathInfo().substring(1));
            writer.println("<b>PRODUCTNANE: </b>" + productRepository.findById((long) id).getProductName() + "<br>");
            writer.println("<b>Cost: </b>" + productRepository.findById((long) id).getCost());


        }

    }
}
