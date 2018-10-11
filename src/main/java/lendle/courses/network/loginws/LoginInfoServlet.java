/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lendle.courses.network.loginws;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lendle
 */
@WebServlet(name = "LoginInfoServlet", urlPatterns = {"/login_info"})
public class LoginInfoServlet extends HttpServlet {
    static{
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getImpl2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out=response.getWriter(); Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            //re-implement getImpl1
            //this time reponde in json-format
            //and use gson
            String id=request.getParameter("id");
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from login where id='"+id+"'");
            Login login=new Login();
            if(rs.next()){
                login.setId(rs.getString("id"));
                login.setPassword(rs.getString("password"));
            }
            out.print(new Gson().toJson(login));
            
            //////////////////////////////
        }catch(Exception e){
            throw new ServletException(e);
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getImpl2(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out=response.getWriter(); Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            //update the corresponding user
            String id=request.getParameter("id");
            String password=request.getParameter("password");
            Statement stmt=conn.createStatement();
            stmt.executeUpdate("update login set password='"+password+"' where id='"+id+"'");
            //////////////////////////////
            out.println("success");
        }catch(Exception e){
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out=response.getWriter(); Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            //delete the corresponding user
            String id=request.getParameter("id");
            Statement stmt=conn.createStatement();
            stmt.executeUpdate("delete from login where id='"+id+"'");
            //////////////////////////////
            out.println("success");
        }catch(Exception e){
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out=response.getWriter(); Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            //insert the corresponding user
            String id=request.getParameter("id");
            String password=request.getParameter("password");
            Statement stmt=conn.createStatement();
            stmt.executeUpdate("insert into login (id, password) values ('"+id+"', '"+password+"')");
            //////////////////////////////
            out.println("success");
        }catch(Exception e){
            throw new ServletException(e);
        }
    }

}
