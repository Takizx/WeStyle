package Controle;

import java.util.List;

public class ProdutoController {

    private ItensPedidoDAO dao = new ItensPedidoDAO();

    public List<String[]> obterProdutosParaCatalogo() {
        return dao.listarProdutosCatalogo();
    }

    public boolean cadastrarProduto(String nome, String preco, String corHex, String estampa) {
        return dao.salvarNovoProduto(nome, preco, corHex, estampa);
    }

    public boolean atualizarProduto(String nomeAntigo, String novoNome, String novoPreco, String novaCor, String novaEstampa) {
        return dao.alterarProduto(nomeAntigo, novoNome, novoPreco, novaCor, novaEstampa);
    }

    public boolean removerProduto(String nome) {
        return dao.excluirProduto(nome);
    }
}