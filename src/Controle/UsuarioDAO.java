package Controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelo.Usuario;

public class UsuarioDAO {

    public Usuario validarLogin(String email, String senha) {
        Connection conn = new Conexao().conectaBD(); 
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, email);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("id")); 
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));

                return usuario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        Connection conn = new Conexao().conectaBD();
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3, usuario.getSenha());

            pstm.execute();
            pstm.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}