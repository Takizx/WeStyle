package Controle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItensPedidoDAO {

    public List<String[]> listarProdutosCatalogo() {
        List<String[]> produtos = new ArrayList<>();
        String sql = "SELECT nome, preco, imagem, customizado FROM produto";
        Connection conn = new Conexao().conectaBD();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] p = new String[4];
                p[0] = rs.getString("nome");
                p[1] = rs.getString("preco");
                p[2] = rs.getString("imagem");
                p[3] = rs.getString("customizado");
                produtos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public boolean salvarNovoProduto(String nome, String preco, String corHex) {
        Connection conn = new Conexao().conectaBD();
        String sql = "INSERT INTO produto (nome, preco, imagem, customizado) VALUES (?, ?, ?, 1)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nome);
            pstm.setString(2, preco);
            pstm.setString(3, corHex); 
            pstm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirProduto(String nome) {
        Connection conn = new Conexao().conectaBD();
        String sql = "DELETE FROM produto WHERE nome = ? AND customizado = 1";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nome);
            pstm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterarProduto(String nomeAntigo, String novoNome, String novoPreco, String novaCor) {
        Connection conn = new Conexao().conectaBD();
        String sql = "UPDATE produto SET nome = ?, preco = ?, imagem = ? WHERE nome = ? AND customizado = 1";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, novoNome);
            pstm.setString(2, novoPreco);
            pstm.setString(3, novaCor);
            pstm.setString(4, nomeAntigo);
            pstm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int obterPedidoAtivo() {
        Connection conn = new Conexao().conectaBD();
        try {
            String sqlBusca = "SELECT id_pedido FROM pedido WHERE id_usuario = 1 AND status_pedido = 'Pendente' LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sqlBusca);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id_pedido");

            String sqlInsert = "INSERT INTO pedido (status_pedido, valor_total, id_usuario, data_pedido) VALUES ('Pendente', 0, 1, NOW())";
            PreparedStatement ps2 = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            ps2.execute();
            ResultSet rs2 = ps2.getGeneratedKeys();
            if (rs2.next()) return rs2.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void incluirItem(int idPedido, int idProduto, int qtd, double preco, String tamanho) {
        Connection conn = new Conexao().conectaBD();
        String sql = "INSERT INTO itens_pedido (id_pedido, id_produto, quantidade, preco_unitario, customizacao) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idPedido);
            pstm.setInt(2, idProduto);
            pstm.setInt(3, qtd);
            pstm.setDouble(4, preco);
            pstm.setString(5, tamanho);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> listarItensDoCarrinho(int idPedido) {
        List<String[]> lista = new ArrayList<>();
        Connection conn = new Conexao().conectaBD();
        String sql = "SELECT ip.id_item, p.nome, ip.preco_unitario, ip.customizacao FROM itens_pedido ip " +
                     "JOIN produto p ON ip.id_produto = p.id_produto WHERE ip.id_pedido = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idPedido);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                lista.add(new String[]{
                    rs.getString("nome") + " (" + rs.getString("customizacao") + ")", 
                    String.valueOf(rs.getDouble("preco_unitario")),
                    String.valueOf(rs.getInt("id_item"))
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void alterarTamanhoItem(int idItem, String novoTamanho) {
        Connection conn = new Conexao().conectaBD();
        String sql = "UPDATE itens_pedido SET customizacao = ? WHERE id_item = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, novoTamanho);
            pstm.setInt(2, idItem);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirItem(int idItem) {
        Connection conn = new Conexao().conectaBD();
        String sql = "DELETE FROM itens_pedido WHERE id_item = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idItem);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int buscarIdProduto(String nome) {
        Connection conn = new Conexao().conectaBD();
        String sql = "SELECT id_produto FROM produto WHERE nome = ? LIMIT 1";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, nome);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) return rs.getInt("id_produto");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; 
    }
}