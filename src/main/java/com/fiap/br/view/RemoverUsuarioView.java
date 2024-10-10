package com.fiap.br.view;
import com.fiap.br.dao.UsuarioDao;
import com.fiap.br.exception.EntidadeNaoEncontradaException;
import com.fiap.br.models.register.Usuario;
import java.sql.SQLException;
public class RemoverUsuarioView {
    public static void main(String[] args) {
        try {
            UsuarioDao dao = new UsuarioDao();
            dao.remover("Rodrigo");
            dao.closeConection();
            System.out.println("Usuário Removido!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Usuário não encontrado");
        }
    }
}