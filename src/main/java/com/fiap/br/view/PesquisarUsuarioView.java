package com.fiap.br.view;
import com.fiap.br.dao.UsuarioDao;
import com.fiap.br.exception.EntidadeNaoEncontradaException;
import com.fiap.br.models.register.Usuario;
import java.sql.SQLException;
public class PesquisarUsuarioView {
    public static void main(String[] args) {
        try {
            UsuarioDao dao = new UsuarioDao();
            Usuario usuario = dao.pesquisar("Rodrigo");
            System.out.println(usuario.getNomeCompleto()+ " " + usuario.getTelefone() + ".");
            dao.closeConection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Usuário não existe na tabela");
        }
    }
}