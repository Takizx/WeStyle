package Controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItensPedidoDAO {

    public List<String[]> listarProdutosCatalogo() {
        Connection conn = new Conexao().conectaBD();
        String sql = "SELECT nome, preco, cor_hex, estampa, customizado FROM produto";
        List<String[]> lista = new ArrayList<>();

        try (PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                String[] prod = new String[5];
                prod[0] = rs.getString("nome");
                prod[1] = rs.getString("preco");
                prod[2] = rs.getString("cor_hex");
                prod[3] = rs.getString("estampa");
                prod[4] = rs.getString("customizado"); 
                lista.add(prod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean salvarNovoProduto(String nome, String preco, String corHex, String estampa) {
        Connection conn = new Conexao().conectaBD();
        String sql = "INSERT INTO produto (nome, preco, cor_hex, estampa, id_categoria, customizado) VALUES (?, ?, ?, ?, 1, 1)";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nome);
            pstm.setString(2, preco);
            pstm.setString(3, corHex);
            pstm.setString(4, estampa);
            pstm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterarProduto(String nomeAntigo, String novoNome, String novoPreco, String novaCor, String novaEstampa) {
        Connection conn = new Conexao().conectaBD();
        String sql = "UPDATE produto SET nome = ?, preco = ?, cor_hex = ?, estampa = ? WHERE nome = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, novoNome);
            pstm.setString(2, novoPreco);
            pstm.setString(3, novaCor);
            pstm.setString(4, novaEstampa);
            pstm.setString(5, nomeAntigo);
            pstm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirProduto(String nome) {
        Connection conn = new Conexao().conectaBD();
        String sql = "DELETE FROM produto WHERE nome = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nome);
            pstm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int obterPedidoAtivo() { 
        Connection conn = new Conexao().conectaBD(); 
        String sqlSelect = "SELECT id_pedido FROM pedido WHERE status_pedido = 'Pendente' LIMIT 1"; 
        try (PreparedStatement pstm = conn.prepareStatement(sqlSelect); ResultSet rs = pstm.executeQuery()) { 
            if (rs.next()) return rs.getInt("id_pedido"); 
        } catch (SQLException e) {} 
        
        String sqlInsert = "INSERT INTO pedido (status_pedido, id_usuario) VALUES ('Pendente', 1)"; 
        try (PreparedStatement pstm2 = conn.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS)) { 
            pstm2.execute(); 
            ResultSet rsKey = pstm2.getGeneratedKeys(); 
            if (rsKey.next()) return rsKey.getInt(1); 
        } catch (SQLException e) {} 
        return 0; 
    }
    
    public int buscarIdProduto(String n) { 
        Connection conn = new Conexao().conectaBD(); 
        String sql = "SELECT id_produto FROM produto WHERE nome = ? LIMIT 1"; 
        try (PreparedStatement pstm = conn.prepareStatement(sql)) { 
            pstm.setString(1, n); 
            ResultSet rs = pstm.executeQuery(); 
            if (rs.next()) return rs.getInt("id_produto"); 
        } catch (SQLException e) {} 
        return 0; 
    }
    
    public boolean incluirItem(int idPed, int idProd, int q, double prc, String c) { 
        Connection conn = new Conexao().conectaBD(); 
        String sql = "INSERT INTO itens_pedido (id_pedido, id_produto, quantidade, preco_unitario, customizacao) VALUES (?, ?, ?, ?, ?)"; 
        try (PreparedStatement pstm = conn.prepareStatement(sql)) { 
            pstm.setInt(1, idPed); 
            pstm.setInt(2, idProd); 
            pstm.setInt(3, q); 
            pstm.setDouble(4, prc); 
            pstm.setString(5, c); 
            pstm.execute(); 
            return true; 
        } catch (SQLException e) { 
            e.printStackTrace();
            return false; 
        } 
    }
    
    public List<String[]> listarItensDoCarrinho(int idPed) { 
        Connection conn = new Conexao().conectaBD(); 
        String sql = "SELECT p.nome, ip.preco_unitario, ip.id_item FROM itens_pedido ip JOIN produto p ON ip.id_produto = p.id_produto WHERE ip.id_pedido = ?"; 
        List<String[]> lista = new ArrayList<>(); 
        try (PreparedStatement pstm = conn.prepareStatement(sql)) { 
            pstm.setInt(1, idPed); 
            ResultSet rs = pstm.executeQuery(); 
            while (rs.next()) { 
                String[] item = new String[3]; 
                item[0] = rs.getString("nome"); 
                item[1] = String.valueOf(rs.getDouble("preco_unitario")); 
                item[2] = String.valueOf(rs.getInt("id_item")); 
                lista.add(item); 
            } 
        } catch (SQLException e) {} 
        return  lista; 
    }
    
    public boolean excluirItem(int id) { 
        Connection conn = new Conexao().conectaBD(); 
        String sql = "DELETE FROM itens_pedido WHERE id_item = ?"; 
        try (PreparedStatement pstm = conn.prepareStatement(sql)) { 
            pstm.setInt(1, id); 
            pstm.execute(); 
            return true; 
        } catch (SQLException e) { 
            return false; 
        } 
    }
    
    public boolean finalizarPedidoNoBanco(int id, double sub, double fr, double tot, String reg) { 
        Connection conn = new Conexao().conectaBD(); 
        String sql = "UPDATE pedido SET subtotal = ?, valor_frete = ?, valor_total = ?, regiao_entrega = ?, status_pedido = 'Finalizado' WHERE id_pedido = ?"; 
        try (PreparedStatement pstm = conn.prepareStatement(sql)) { 
            pstm.setDouble(1, sub); 
            pstm.setDouble(2, fr); 
            pstm.setDouble(3, tot); 
            pstm.setString(4, reg); 
            pstm.setInt(5, id); 
            pstm.execute(); 
            return true; 
        } catch (SQLException e) { 
            return false; 
        } 
    }
}