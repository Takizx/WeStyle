package Controle;

import java.util.List;

public class ProdutoController {
    
    private ItensPedidoDAO dao;

    public ProdutoController() {
        this.dao = new ItensPedidoDAO();
    }

    public List<String[]> obterProdutosParaCatalogo() {
        return dao.listarProdutosCatalogo();
    }
}