package Controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class EstampaDAO {

    public boolean cadastrarEstampa(String nome, String nomeArquivo) {
        Connection conn = new Conexao().conectaBD();
        String sql = "INSERT INTO estampa (nome, imagem) VALUES (?, ?)";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nome);
            pstm.setString(2, nomeArquivo);

            pstm.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no DAO ao cadastrar estampa: " + e.getMessage());
            return false;
        }
    }
}