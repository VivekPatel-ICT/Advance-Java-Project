package com.phm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.phm.bean.LoginBean;
import com.phm.helper.DBService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Edit")
public class Editservlet extends HttpServlet {

    private DBService dbService;

    @Override
    public void init() throws ServletException {
        dbService = new DBService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        LoginBean medicine = dbService.fetchMedicineById(id);

        if (medicine == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Medicine not found.");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Edit Medicine</h2>");
        out.println("<form action='Edit' method='post'>");

        out.println("<input type='hidden' name='id' value='" + medicine.getId() + "'>");

        out.println("<table>");
        out.println("<tr><td>Name:</td><td><input type='text' name='name' value='" + medicine.getName() + "' readonly></td></tr>");
        out.println("<tr><td>Brand:</td><td><input type='text' name='brand' value='" + medicine.getBrand() + "' required></td></tr>");
        out.println("<tr><td>Price:</td><td><input type='text' name='price' value='" + medicine.getPrice() + "' required></td></tr>");
        out.println("<tr><td>Stock:</td><td><input type='text' name='stock' value='" + medicine.getStock() + "' required></td></tr>");
        out.println("<tr><td colspan='2'><input type='submit' value='Update'></td></tr>");
        out.println("</table>");

        out.println("</form>");
        out.println("<br><a href='Medlist'>Back to List</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        LoginBean medicine = new LoginBean();
        dbService.updateMedicineById(medicine);

        response.sendRedirect("Medlist");
    }
}
