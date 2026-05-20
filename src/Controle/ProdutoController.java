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

    public boolean cadastrarProduto(String nome, String preco, String corHex) {
        return dao.salvarNovoProduto(nome, preco, corHex);
    }

    public boolean removerProduto(String nome) {
        return dao.excluirProduto(nome);
    }

    public boolean atualizarProduto(String nomeAntigo, String novoNome, String novoPreco, String novaCor) {
        return dao.alterarProduto(nomeAntigo, novoNome, novoPreco, novaCor);
    }
}