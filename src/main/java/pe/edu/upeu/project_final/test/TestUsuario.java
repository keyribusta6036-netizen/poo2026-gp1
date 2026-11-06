package pe.edu.upeu.project_final.test;

import pe.edu.upeu.project_final.implementacion.UsuarioDaoImpl;
import pe.edu.upeu.project_final.interfaces.IUsuarioDao;
import pe.edu.upeu.project_final.model.Usuario;

public class TestUsuario {
    private static IUsuarioDao dao = new UsuarioDaoImpl();
    public void  valid_login(){
        Usuario u = dao.login("admin","admin123");
        if (u !=null) {
            System.out.println("ID_USUARIO" + u.getIdusuario());
            System.out.println("NOMBRES" + u.getNombres());
            System.out.println("USUARIO" + u.getUser());
            System.out.println("TIPO_USER" + u.getTipoUser());
        }else{
            System.out.println("error login");
        }
    }
    public static void main ( String[]args){
        TestUsuario t = new TestUsuario();
        t.valid_login();
    }
}
