package uk.ac.ucl.servlets;

import uk.ac.ucl.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* This servlet is invoked when you want to display patient data from patient list.
   The url http://localhost:8080/patientData.html is mapped calling doPost on servlet object.
 */
@WebServlet("/patientData.html")
public class PatientDataServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        String patient = request.getParameter("id");            // Request patient name.
        int index = patient.indexOf(" ");
        List<String> searchResult = model.searchFor(patient.substring(index + 1, patient.length()));      // Passes the first name as argument.
        List<String> columns = model.getColumnNames();
        request.setAttribute("columns", columns);
        request.setAttribute("Data", searchResult);


        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientData.jsp");
        dispatch.forward(request, response);
    }
}
