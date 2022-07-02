package web;

import Datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet{
    
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {

                case "editar":
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;
                default:
                    this.Default(request, response);
                    break;

            }

        } else {
            this.Default(request, response);
        }
        
        
    }
    
    private void Default (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("Lista de clientes: " + clientes);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", this.calcularSaldoTot(clientes));
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("clientes.jsp");
        
    }
    
    
    private double calcularSaldoTot (List<Cliente> clientes) {

        double saldoTotal = 0;

        for (Cliente cliente: clientes) {       
            saldoTotal += cliente.getSaldo();

        }
        return saldoTotal;
    }
    
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {

                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "modificar":
                    this.modificarCliente(request, response);
                    break;
                default:
                    this.Default(request, response);
                    break;

            }

        } else {
            this.Default(request, response);
        }
        
        
    }
    
    public void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        
        
        //Recuperamos valores del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        
        if (saldoString != null && !"".equals(saldoString)){
        saldo = Double.parseDouble(saldoString);
            
        }
    
        //Creamos objeto de cliente
        Cliente cliente = new Cliente(nombre, apellido, email,telefono,saldo);
    
        //Se inserta
        
        int registroModificado = new ClienteDaoJDBC().insertar(cliente);
        System.out.println("Registros modificados: " + registroModificado);
        
        //Se vuelve a desplegar el listado
        
        this.Default(request, response);
        
        
        
    }
    
    
    public void editarCliente (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        
        
        //Recuperamos valores del formulario
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new ClienteDaoJDBC().buscar(idCliente);
        request.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }
    
    public void modificarCliente (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        
        
        //Recuperamos valores del formulario
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        
        if (saldoString != null && !"".equals(saldoString)){
        saldo = Double.parseDouble(saldoString);
            
        }
    
        //Modificar objeto de cliente
        Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
    
        //Se inserta
        
        int registroModificado = new ClienteDaoJDBC().actualizar(cliente);
        System.out.println("Registros modificados: " + registroModificado);
        
        //Se vuelve a desplegar el listado
        
        this.Default(request, response);
    }
    
    public void eliminarCliente (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        
        
        //Recuperamos valores del formulario
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        
        //Eliminar un objeto
        
        Cliente cliente = new Cliente(idCliente);
        int registroModificado = new ClienteDaoJDBC().eliminar(cliente);
        
        System.out.println("Registro eliminado" + registroModificado);
        
        
        //Se vuelve a desplegar el listado
        this.Default(request, response);
        
    }
    
    
        
        
}
