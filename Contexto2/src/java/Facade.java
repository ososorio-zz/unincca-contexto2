
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Facade extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String cedula=request.getParameter("cedula");
            if(cedula!=null){
                
            int parametro=Integer.parseInt(cedula);
            Consultas ins=new Consultas();
            String resultado=ins.usuarioValidoVotaciones(parametro);
           
            out.println("<html>");
            out.println("<head>");
            out.println("<title>PARCIAL CONTEXTO</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h3><center>" + resultado + "</h3>");
            out.println("</body>");
            out.println("</html>");
            }
            else{
                 out.println("<h3>Por Fabor enviar dato por Url </h3>"); 
            }
        } finally {            
            out.close();
        }
    
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
