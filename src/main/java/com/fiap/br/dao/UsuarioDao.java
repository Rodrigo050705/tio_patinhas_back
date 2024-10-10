package com.fiap.br.dao;

import com.fiap.br.exception.EntidadeNaoEncontradaException;

import java.sql.ResultSet;
import java.util.*;
import com.fiap.br.factory.ConnectionFactory;
import com.fiap.br.models.register.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.fiap.br.utils.InitSQLString.initSQLString;

public class UsuarioDao {
    private Connection conection;

    public UsuarioDao() throws SQLException {
        conection = ConnectionFactory.getConnection();
    }

    public void cadastrar(Usuario usuario) throws SQLException {
        PreparedStatement stm = conection.prepareStatement("INSERT INTO CLIENTES (nome_completo, email, senha, cpf_cnpj, telefone, data_criacao) VALUES (seq_usuario.nextval, ?, ?, ?, ?, ?, ?)");
        stm.setString(1, usuario.getNomeCompleto());
        stm.setString(2, usuario.getEmail());
        stm.setString(3, usuario.getSenha());
        stm.setString(4, usuario.getCpfCnpj());
        stm.setString(5, usuario.getTelefone());
        stm.setDate(6, usuario.getDataCriacao());
        stm.executeUpdate();
    }

    public void closeConection() throws SQLException {
        conection.close();
    }

    public Usuario pesquisar(String nome_completo) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conection.prepareStatement("SELECT * FROM CLIENTES WHERE nome_completo = ?");
        stm.setString(1, nome_completo);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new EntidadeNaoEncontradaException("Usuário não encontrado");
        return parseUsuario(result);
    }

    private Usuario parseUsuario(ResultSet result) throws SQLException {
        String nome = result.getString("nome_completo");
        String tel = result.getString("telefone");
        return new Usuario(nome, tel);
    }

    public List<Usuario> listar() throws SQLException {
        PreparedStatement stm = conection.prepareStatement("SELECT * FROM CLIENTES");
        ResultSet result = stm.executeQuery();
        List<Usuario> lista = new ArrayList<>();
        while (result.next()){

            lista.add(parseUsuario(result));
        }
        return lista;
    }

    public void atualizar(Usuario usuario) throws SQLException {
        PreparedStatement stm = conection.prepareStatement("UPDATE CLIENTES SET nome_completo = ?, email = ?, senha = ?, telefone = ? where id_cliente = ?");
        stm.setString(1, usuario.getNomeCompleto());
        stm.setString(2, usuario.getEmail());
        stm.setString(3, usuario.getSenha());
        stm.setString(4, usuario.getTelefone());
        stm.setString(5, usuario.getIdUsuario());
        stm.executeUpdate();
    }

    public void remover(String nome_completo) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conection.prepareStatement("DELETE from CLIENTES where nome_completo = ?");
        stm.setString(1, nome_completo);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new EntidadeNaoEncontradaException("Usuário não encontrado para ser removido");
    }

}
