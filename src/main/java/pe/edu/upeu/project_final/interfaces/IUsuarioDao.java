package pe.edu.upeu.project_final.interfaces;

import pe.edu.upeu.project_final.model.Usuario;

public interface IUsuarioDao {
    public Usuario login(String user, String password);
    public boolean insert_user(Usuario usuario);

}
