package com.phm.servlet;

import java.io.IOException;

import com.phm.helper.DBService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {

    private DBService dbService;

    @Override
    public void init() throws ServletException {
        dbService = new DBService(); // initialize the service
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the "id" parameter from the request
        String idParam = request.getParameter("id");

        try {
            int id = Integer.parseInt(idParam);

            // Call service to delete the record
            dbService.deleteMedicineById(id);

            // Redirect back to the list page after deletion
            response.sendRedirect("Medlist");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format.");
        }
    }
}
