package com.phm.servlet;

import com.phm.bean.LoginBean;
import com.phm.helper.DBService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Add")
public class Registerservlet extends HttpServlet {

    private DBService dbService;

    @Override
    public void init() throws ServletException {
        // Initialize DBService (you can use any initialization logic you prefer)
        dbService = new DBService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String priceStr = request.getParameter("price");
        String stockStr = request.getParameter("stock");

        if (name == null || name.trim().isEmpty() || brand == null || brand.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Name and Brand are required fields.");
            return;
        }

        double price = 0.0;
        int stock = 0;

        try {
            price = Double.parseDouble(priceStr);
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid price or stock value.");
            return;
        }

        // ✅ Set values into the LoginBean
        LoginBean bean = new LoginBean();
        bean.setName(name);
        bean.setBrand(brand);
        bean.setPrice(price);
        bean.setStock(stock);

        // Save using DBService
        dbService.addMedicine(bean);

        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Medicine Registered Successfully!</h2>");
        response.getWriter().println("<a href='home.html'>Add Another</a><br>");
        response.getWriter().println("<a href='Medlist'>View Medicines</a>");
        response.getWriter().println("</body></html>");
    }

}
