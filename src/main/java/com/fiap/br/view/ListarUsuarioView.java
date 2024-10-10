package com.fiap.br.view;
import com.fiap.br.dao.UsuarioDao;
import com.fiap.br.models.register.Usuario;
import java.sql.SQLException;
import java.util.List;
public class ListarUsuarioView {
    public static void main(String[] args) {
        try {
            UsuarioDao dao = new UsuarioDao();
            List<Usuario> usuarios = dao.listar();
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getNomeCompleto() + " " +  usuario.getTelefone() + ". ");
            }
            dao.closeConection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}