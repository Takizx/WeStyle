package Controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.Produto;

public class ProdutoDAO {

    // Configurações de conexão (mesmas que usamos no UsuarioDAO)
    private String url = "jdbc:mysql://localhost:3306/we_style";
    private String user = "root";
    private String password = "admin";

    /**
     * Este método faz o SELECT no banco e transforma as linhas da tabela
     * em uma lista de objetos que o Java entende.
     */
    public List<Produto> listarProdutos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getDouble("preco"));
                p.setTamanho(rs.getString("tamanho"));
                
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produtos: " + e.getMessage());
        }
        return lista;
    }
}