package com.fiap.br.view;

import com.fiap.br.dao.UsuarioDao;
import com.fiap.br.models.register.Usuario;
import java.sql.SQLException;

public class CadastroUsuarioView {
    public static void main(String[] args) {
        try {
            UsuarioDao dao = new UsuarioDao();
            Usuario usuario = new Usuario("Rodrigo", "Calça Jeans azul");
            dao.cadastrar(usuario);
            dao.closeConection();
            System.out.println("Usuário cadastrado!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
