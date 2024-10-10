package com.fiap.br.view;
import com.fiap.br.dao.UsuarioDao;
import com.fiap.br.models.register.Usuario;
import com.fiap.br.exception.EntidadeNaoEncontradaException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
public class UsuarioView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDao dao;
        System.out.println("Bem vindo ao banco do tio patinhas!");
        try {
            dao = new UsuarioDao();
            int escolha;
            do {
                System.out.println("Escolha uma opção:" +
                        "1-Cadastrar" +
                        "2-Pesquisar por Código" +
                        "3-Listar" +
                        "4-Atualizar" +
                        "5-Remover" +
                        "0-Sair");
                escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                        cadastrar(scanner, dao);
                        break;
                    case 2:
                        pesquisarUsuario(scanner, dao);
                        break;
                    case 3:
                        listar(dao);
                        break;
                    case 4:
                        atualizar(scanner, dao);
                        break;
                    case 5:
                        removerUsuario(scanner, dao);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (escolha != 0);
            dao.closeConection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    private static void cadastrar(Scanner scanner, UsuarioDao dao) {
        System.out.println("Digite o nome do usuário:");
        String nome = scanner.next() + scanner.nextLine();

        System.out.println("Digite o telefone do usuário:");
        String telefone = scanner.next() + scanner.nextLine();

        Usuario novoUsuario = new Usuario(nome, telefone);
        try {
            dao.cadastrar(novoUsuario);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
    private static void pesquisarUsuario(Scanner scanner, UsuarioDao dao) {
        System.out.println("Digite o nome do usuário:");
        String nome_pesq = scanner.nextLine();
        try {
            Usuario usuario = dao.pesquisar(nome_pesq);
            System.out.println("Usuário encontrado:");
            System.out.println(usuario.getNomeCompleto() + ", " + usuario.getTelefone() + ".");
        } catch (SQLException | EntidadeNaoEncontradaException e) {
            System.err.println("Erro ao pesquisar usuário: " + e.getMessage());
        }
    }
    private static void listar(UsuarioDao dao) {
        try {
            List<Usuario> usuarios = dao.listar();
            System.out.println("Lista de usuários:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getNomeCompleto() + ", " + usuario.getTelefone() + ".");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
    }
    private static void atualizar(Scanner scanner, UsuarioDao dao) {
        System.out.println("Digite o nome completo do usuário que deseja atualizar:");
        String nome = scanner.nextLine();
        try {
            Usuario usuario = dao.pesquisar(nome);
            System.out.println("Digite o novo nome do usuário:");
            String nome_novo = scanner.next() + scanner.nextLine();
            System.out.println("Digite o novo telefone do usuário:");
            String telefone_novo = scanner.next() + scanner.nextLine();
            usuario.setNomeCompleto(nome_novo);
            usuario.setTelefone(telefone_novo);
            dao.atualizar(usuario);
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException | EntidadeNaoEncontradaException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }
    private static void removerUsuario(Scanner scanner, UsuarioDao dao) {
        System.out.println("Digite o nome do usuário que deseja remover:");
        String nome = scanner.nextLine();
        try {
            dao.remover(nome);
            System.out.println("Usuário removido com sucesso!");
        } catch (SQLException | EntidadeNaoEncontradaException e) {
            System.err.println("Erro ao remover usuário: " + e.getMessage());
        }
    }
}