package Controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItensPedidoDAO {

    public void incluirItem(int idPedido, int idProduto, int qtd, double preco) {
        Connection conn = new Conexao().conectaBD();
        String sql = "INSERT INTO itens_pedido (id_pedido, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idPedido);
            pstm.setInt(2, idProduto);
            pstm.setInt(3, qtd);
            pstm.setDouble(4, preco);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> listarItensPorPedido(int idPedido) {
        List<String[]> lista = new ArrayList<>();
        Connection conn = new Conexao().conectaBD();
        String sql = "SELECT ip.id_item, p.nome, ip.preco_unitario FROM itens_pedido ip " +
                     "JOIN produto p ON ip.id_produto = p.id_produto WHERE ip.id_pedido = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idPedido);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                lista.add(new String[]{
                    rs.getString("nome"), 
                    String.valueOf(rs.getDouble("preco_unitario")),
                    String.valueOf(rs.getInt("id_item"))
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
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
}