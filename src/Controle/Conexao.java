package Controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    public Connection conectaBD() {
        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost:3306/we_style?userTimezone=true&serverTimezone=UTC";
            
            String usuario = "root"; 
            String senha = "admin"; 

            conn = DriverManager.getConnection(url, usuario, senha);

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de Conexão: " + erro.getMessage());
        }
        return conn;
    }
}