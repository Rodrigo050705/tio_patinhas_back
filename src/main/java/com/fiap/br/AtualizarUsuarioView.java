package com.fiap.br.view;

import com.fiap.br.dao.UsuarioDao;
import com.fiap.br.exception.EntidadeNaoEncontradaException;
import com.fiap.br.models.register.Usuario;
import java.sql.SQLException;

public class AtualizarUsuarioView {
    public static void main(String[] args) {
        try {
            UsuarioDao dao = new UsuarioDao();
            Usuario usuario = dao.pesquisar("Rodrigo");
            usuario.setNomeCompleto("Camisa Polo");
            usuario.setEmail("CAMISAPOLO@GMAIL.COM");
            usuario.setSenha("amsafas");
            usuario.setTelefone("01155060224");
            dao.atualizar(usuario);
            dao.closeConection();
            System.out.println("Usuário atualizado!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Produto não encontrado");
        }
    }
}
