package Controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

    public List<String> listarNomesEstampas() {
        Connection conn = new Conexao().conectaBD();
        String sql = "SELECT nome FROM estampa";
        List<String> lista = new ArrayList<>();

        try (PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }

    public String buscarImagemPorNome(String nome) {
        Connection conn = new Conexao().conectaBD();
        String sql = "SELECT imagem FROM estampa WHERE nome = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nome);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return rs.getString("imagem");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}