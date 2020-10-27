package com.montanha.factory;

import com.montanha.pojo.Usuario;
import com.montanha.pojo.Viagem;

public class UsuarioDataFactory {
    public static Usuario criarUsuarioAdmin() {
        Usuario usuarioAdmin = new Usuario();

        usuarioAdmin.setEmail("admin@email.com");
        usuarioAdmin.setSenha("654321");

        return usuarioAdmin;
    }

    public static Usuario criaUsuario() {
        Usuario usuario = new Usuario();

        usuario.setEmail("usuario@email.com");
        usuario.setSenha("123456");

        return usuario;
    }
}
