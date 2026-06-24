package Visao;

import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import Controle.ProdutoController;
import Controle.ItensPedidoDAO;
import java.util.List;

public class JanelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private static JPanel container;
    private static CardLayout cl;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JanelaPrincipal frame = new JanelaPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public JanelaPrincipal() {
        setTitle("WeStyle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1400, 900);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        cl = new CardLayout();
        container = new JPanel(cl);

        container.add(new Telaprincipal(), "principal");
        container.add(new TelaEscolha(), "escolha");
        container.add(new TelaCatalogo(), "catalogo");
        container.add(new TelaPerfil(), "perfil");
        container.add(new TelaPersonalizar(), "personalizar");
        container.add(new TelaEstampa(), "estampa");
        container.add(new TelaRecuperarSenha(), "recuperarSenha");
        container.add(new Telaentrar(), "entrar");
        container.add(new Telacadastro(), "cadastro");
        container.add(new TelaCarrinho(), "carrinho");
        container.add(new TelaAlterarSenha(), "alterarSenha");

        getContentPane().add(container);
        
        cl.show(container, "principal"); 
    }

    public static void mudarTela(String nomeDaTela) {
        if (nomeDaTela.equalsIgnoreCase("perfil")) {
            container.add(new TelaPerfil(), "perfil");
        } else if (nomeDaTela.equalsIgnoreCase("alterarsenha")) {
            container.add(new TelaAlterarSenha(), "alterarSenha");
        } else if (nomeDaTela.equalsIgnoreCase("personalizar")) {
            String prodSelecionado = Modelo.DadosCompartilhados.produtoSelecionado;
            if (prodSelecionado != null && !prodSelecionado.isEmpty()) {
                ProdutoController pc = new ProdutoController();
                List<String[]> produtos = pc.obterProdutosParaCatalogo();
                if (produtos != null) {
                    for (String[] p : produtos) {
                        if (p[0].equals(prodSelecionado)) {
                            container.add(new TelaPersonalizar(p[0], p[1], p[2], p[3]), "personalizar");
                            break;
                        }
                    }
                }
            } else {
                container.add(new TelaPersonalizar(), "personalizar");
            }
        } else if (nomeDaTela.equalsIgnoreCase("catalogo")) {
            Modelo.DadosCompartilhados.produtoSelecionado = "";
            container.add(new TelaCatalogo(), "catalogo");
        } else if (nomeDaTela.equalsIgnoreCase("estampa")) {
            container.add(new TelaEstampa(), "estampa");
        } else if (nomeDaTela.equalsIgnoreCase("entrar")) {
            container.add(new Telaentrar(), "entrar");
        } else if (nomeDaTela.equalsIgnoreCase("cadastro")) {
            container.add(new Telacadastro(), "cadastro");
        } else if (nomeDaTela.equalsIgnoreCase("carrinho")) {
            container.add(new TelaCarrinho(), "carrinho");
        } else if (nomeDaTela.equalsIgnoreCase("finalizar")) {
            ItensPedidoDAO dao = new ItensPedidoDAO();
            int idPed = dao.obterPedidoAtivo();
            List<String[]> itens = dao.listarItensDoCarrinho(idPed);
            double total = 0.0;
            if (itens != null) {
                for (String[] item : itens) {
                    try {
                        total += Double.parseDouble(item[1]);
                    } catch (Exception e) {}
                }
            }
            container.add(new TelaFinalizar(itens, total, idPed), "finalizar");
        } else if (nomeDaTela.equalsIgnoreCase("detalhes")) {
            String nomeProd = Modelo.DadosCompartilhados.produtoSelecionado;
            ProdutoController pc = new ProdutoController();
            List<String[]> produtos = pc.obterProdutosParaCatalogo();
            Color corInicial = Color.WHITE;
            String preco = "0.00";
            String estampa = "";
            
            if (produtos != null) {
                for (String[] p : produtos) {
                    if (p[0].equals(nomeProd)) {
                        preco = p[1];
                        try {
                            corInicial = Color.decode("#" + p[2]);
                        } catch (Exception e) {
                            corInicial = Color.WHITE;
                        }
                        if (p.length > 3) {
                            estampa = p[3];
                        }
                        break;
                    }
                }
            }
            container.add(new TelaDetalhes(nomeProd, corInicial, preco, estampa), "detalhes");
        }
        
        cl.show(container, nomeDaTela);
    }
}