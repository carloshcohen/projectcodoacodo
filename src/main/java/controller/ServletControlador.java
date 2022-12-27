package controller;

import data.AutoDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Auto;

@WebServlet("/servletControlador")
public class ServletControlador extends HttpServlet{
        
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {     
//        try {
//            List<Auto> autos;
//            autos = new AutoDAO().seleccionar();
//            autos.forEach(System.out::println);
//
//            req.setAttribute("autos", autos);
//            req.setAttribute("totalAutos", autos.size());
//            req.setAttribute("valorPlanta", calcularPrecio(autos));
//            req.getRequestDispatcher("autos.jsp").forward(req, res);
//        
//        } catch (ClassNotFoundException ex) {
//            System.out.println("HAY QUILOMBO EN EL DOGET");
//            Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try{
        String accionJava = req.getParameter("accion");
         if (accionJava!=null){
             switch (accionJava) {
                 case "editar":
                     editarAuto(req,res);
                     break;
                 case "eliminar":
                     venderAuto(req,res);
                     break;
                 default:
                     accionDefault(req,res);
                     break;
             }
         }else{
            accionDefault(req,res);
         }
        }catch (Exception e){
            System.out.println("Problemas en el DoGet");
            System.out.println(e);
        }
    }
        
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try{ 
        String accionJava = req.getParameter("accion");
         if (accionJava!=null){
             switch (accionJava) {
                 case "insertar":
                     insertarAuto(req,res);
                     break;
                 case "modificar":
                     modificarAuto(req, res);
                     break;
                 default:
                     accionDefault(req,res);
                     break;
             }
         }else{
             accionDefault(req,res);
         }
        }catch(Exception e){
            System.out.println("Problemas en el DoPost");
            System.out.println(e);
        }
    }
    
    //-----------------------------------------------------
    private double calcularPrecio(List<Auto> autoscalculo){
        double total = 0;
        for (int i = 0; i < autoscalculo.size(); i++) {
            total += autoscalculo.get(i).getPrecio();
        }
        return total;
    }
    //-----------------------------------------------------
    private void accionDefault (HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException{
        
        try {
            List<Auto> autos;
            autos = new AutoDAO().seleccionar();
            System.out.println("------------------------");
            autos.forEach(System.out::println);
            System.out.println("------------------------");
            
            HttpSession sesion = req.getSession();
                    
            sesion.setAttribute("autos", autos);
            sesion.setAttribute("totalAutos", autos.size());
            sesion.setAttribute("valorPlanta", calcularPrecio(autos));
            //req.getRequestDispatcher("autos.jsp").forward(req, res);
            res.sendRedirect("autos.jsp");
            
            System.out.println("Cantidad de autos " + autos.size());
            System.out.println("precio total de los autos " + calcularPrecio(autos));
            System.out.println("------------------------");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("HAY QUILOMBO EN DEFAULT");
            ex.printStackTrace(System.out);
        }
    }
    
    //-----------------------------------------------------
    private void insertarAuto(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException{
        try {
            double precio=0;
            int puertas=0;
            String marca = req.getParameter("marca");
            String model = req.getParameter("modelo");
            String caja = req.getParameter("caja");
            
            String precioString = req.getParameter("precio");
            if (precioString != null && !precioString.equals("")){
                precio = Double.parseDouble(precioString);
            }
            
            System.out.println("----Precio cargado----"+precio);
            System.out.println("----String precio cargado----"+precioString);
            
            String puertasString = req.getParameter("puertas");
            if (puertasString != null && !puertasString.equals("")){
                puertas = Integer.parseInt(puertasString);
            }
            boolean dispo = true;
            Auto auto = new Auto(marca, model, caja, precio, puertas, dispo);
            
            int regMod = new AutoDAO().insertarAuto(auto);
            System.out.println("Registros guardados "+regMod);
            accionDefault(req, res);
        } catch (ClassNotFoundException ex) {
            System.out.println("HAY QUILOMBO EN INSERTAR");
            Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //-----------------------------------------------------
    private void editarAuto(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException{
        try {
            int idAuto = Integer.parseInt(req.getParameter("id"));
            Auto auto = new AutoDAO().seleccionarPorId(idAuto);
            req.setAttribute("auto", auto);
            String jspEditar = "/WEB-INF/paginas/operaciones/editarAuto.jsp";
            req.getRequestDispatcher(jspEditar).forward(req, res);
        } catch (ClassNotFoundException ex) {
            System.out.println("HAY QUILOMBO EN EDITAR");
            Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void modificarAuto(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException, ClassNotFoundException{
        double precioo=0;
        int puertass=0;
        int idauto = Integer.parseInt(req.getParameter("id"));
        String marcaa = req.getParameter("marca");
        String model = req.getParameter("modelo");
        String caja = req.getParameter("caja");
        String precioString = req.getParameter("precio");
        if (precioString != null && !precioString.equals("")){
            precioo = Double.parseDouble(precioString);
        }
        System.out.println("----Bandera----"+precioo);
        String puertasString = req.getParameter("puertas");
        if (puertasString != null && !puertasString.equals("")){
            puertass = Integer.parseInt(puertasString);
        }
        boolean dispo = true;
        
        Auto auto = new Auto(idauto, marcaa, model, caja, precioo, puertass, dispo);
        
        int regMod = new AutoDAO().actualizar(auto);
        System.out.println("Registros actualizados: "+regMod);
        
        accionDefault(req, res);
    }
    
    private void venderAuto(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException, ClassNotFoundException{
        
        int idauto = Integer.parseInt(req.getParameter("id"));
        int regMod = new AutoDAO().eliminar(idauto);
        System.out.println("Autos vendidos: "+regMod);
        
        accionDefault(req, res);
    }
}
