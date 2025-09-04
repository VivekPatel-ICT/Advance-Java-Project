
package com.phm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.phm.bean.LoginBean;
import com.phm.helper.DBService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Medlist")
public class Selectservlet extends HttpServlet {

    private DBService dbService;

    @Override
    public void init() throws ServletException {
        dbService = new DBService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<LoginBean> medicines = dbService.fetchAllMedicines();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Medicine List</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Name</th><th>Brand</th><th>Price</th><th>Stock</th><th>Actions</th></tr>");

        for (LoginBean bean : medicines) {
            out.println("<tr>");
            out.println("<td>" + bean.getId() + "</td>");
            out.println("<td>" + bean.getName() + "</td>");
            out.println("<td>" + bean.getBrand() + "</td>");
            out.println("<td>" + bean.getPrice() + "</td>");
            out.println("<td>" + bean.getStock() + "</td>");
            out.println("<td>");
            out.println("<a href='Edit?id=" + bean.getId() + "'>Edit</a> | ");
            out.println("<a href='Delete?id=" + bean.getId() + "' onclick=\"return confirm('Are you sure?');\">Delete</a>");
            out.println("</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<br><a href='home.html'>Add New Medicine</a>");
        out.println("</body></html>");
    }
}
