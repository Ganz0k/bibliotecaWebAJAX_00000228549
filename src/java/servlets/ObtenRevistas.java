/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import backup.CatalogoRevistasFix;
import com.google.gson.Gson;
import controldao.CatalogoRevistasJpaController;
import dao.CatalogoRevistas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luisg
 */
@WebServlet(name = "ObtenRevistas", urlPatterns = {"/ObtenRevistas"})
public class ObtenRevistas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        CatalogoRevistasJpaController control = new CatalogoRevistasJpaController();
        List<CatalogoRevistas> lista = control.findCatalogoRevistasEntities();
        List<CatalogoRevistasFix> listaFix = new ArrayList<>();
        
        for (CatalogoRevistas cR : lista) {
            CatalogoRevistasFix cRF = new CatalogoRevistasFix(cR.getIsbn(), cR.getTitulo(), cR.getEditorial(), cR.getClasificacion(), cR.getPeriodicidad(), cR.getFecha().toString());
            listaFix.add(cRF);
        }
        
        String revistas = gson.toJson(listaFix);
        try (PrintWriter out = response.getWriter()) {
            out.println(revistas);
            out.flush();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
