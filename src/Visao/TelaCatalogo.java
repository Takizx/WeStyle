package Visao;

import java.awt.*;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;
import Controle.ProdutoController;
import Modelo.Usuario;
import Modelo.Sessao;
import Modelo.DadosCompartilhados;

public class TelaCatalogo extends JPanel {

    private JPanel catalogo;
    private ProdutoController controller = new ProdutoController();
    Color verde = new Color(106, 143, 123);
    Color linha = new Color(200, 220, 210);

    public TelaCatalogo() {
        this.setLayout(new BorderLayout());
        this.setBackground(verde); 

        JPanel navbar = new JPanel(new MigLayout("insets 15, fillx", "[left]push[center][center][center]push[right]", ""));
        navbar.setBackground(verde);
        navbar.setBorder(new MatteBorder(0, 0, 1, 0, linha));
        
        JLabel logo = new JLabel("WeStyle");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 30));
        navbar.add(logo);
        
        navbar.add(criarBotaoNav("Inicio"));
        navbar.add(criarBotaoNav("Personalizar"));
        
        Usuario logado = Sessao.getUsuario();
        if (logado == null || !"CRIADOR".equals(logado.getTipo())) {
            navbar.add(criarBotaoNav("Carrinho"));
        }
        
        navbar.add(criarBotaoNav("Perfil"));
        
        this.add(navbar, BorderLayout.NORTH);

        JPanel fundo = new JPanel(new MigLayout("wrap, fillx, insets 20", "[center]", "[][][grow]"));
        fundo.setBackground(verde);
        this.add(fundo, BorderLayout.CENTER);

        JLabel titulo = new JLabel("Catálogo WeStyle");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 39));
        fundo.add(titulo, "gapy 10");

        catalogo = new JPanel(new MigLayout("insets 10, gap 25, center, wrap 4"));
        catalogo.setOpaque(false);
        JScrollPane scroll = new JScrollPane(catalogo);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        fundo.add(scroll, "grow, push"); 

        renderizarProdutos();
    }

    private void renderizarProdutos() {
        catalogo.removeAll();
        List<String[]> produtos = controller.obterProdutosParaCatalogo();
        Usuario logado = Sessao.getUsuario();
        String tipoUsuario = (logado != null) ? logado.getTipo() : "COMPRADOR";
        
        for (String[] p : produtos) {
            final String nome = p[0];
            final String preco = p[1];
            final String corHex = p[2];
            final String nomeEstampa = p[3];
            final boolean ehCustomizado = p.length > 4 && "1".equals(p[4]);

            JPanel card = new JPanel(new MigLayout("wrap, insets 15, align center", "[center]", "[]10[]5[]10[]10[]"));
            card.setBackground(verde);
            card.setBorder(new LineBorder(linha, 1));

            JPanel preview = new JPanel(new BorderLayout());
            try { 
                preview.setBackground(Color.decode("#" + corHex)); 
            } catch (Exception e) { 
                preview.setBackground(Color.GRAY); 
            }
            
            if (nomeEstampa != null && !nomeEstampa.isEmpty()) {
                JLabel lblEstampa = new JLabel();
                lblEstampa.setHorizontalAlignment(SwingConstants.CENTER);
                
                File arquivoEstampa = new File("Imagens/" + nomeEstampa);
                if (arquivoEstampa.exists()) {
                    ImageIcon icon = new ImageIcon(arquivoEstampa.getAbsolutePath());
                    Image img = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                    lblEstampa.setIcon(new ImageIcon(img));
                }
                preview.add(lblEstampa, BorderLayout.CENTER);
            }
            
            card.add(preview, "width 250!, height 250!"); 

            card.add(new JLabel(nome) {{ setForeground(Color.WHITE); setFont(new Font("Arial", Font.BOLD, 17)); }});
            card.add(new JLabel("R$ " + preco) {{ setForeground(Color.WHITE); }});

            if (ehCustomizado && "CRIADOR".equals(tipoUsuario)) {
                JPanel painelEdicao = new JPanel(new MigLayout("insets 0", "[grow][grow]", "[]"));
                painelEdicao.setOpaque(false);

                JButton btnAlt = new JButton("Alterar");
                btnAlt.setBackground(Color.WHITE);
                btnAlt.setForeground(verde);
                btnAlt.addActionListener(e -> { JanelaPrincipal.mudarTela("personalizar"); });

                JButton btnExc = new JButton("Excluir");
                btnExc.setBackground(Color.WHITE);
                btnExc.setForeground(new Color(200, 50, 50)); 
                btnExc.addActionListener(e -> {
                    Object[] options = {"Sim", "Não"};
                    int resp = JOptionPane.showOptionDialog(null, 
                        "Você deseja mesmo excluir esta peça?", 
                        "Confirmação", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, 
                        null, options, options[0]);
                    
                    if(resp == 0) { 
                        controller.removerProduto(nome);
                        renderizarProdutos();
                    }
                });

                painelEdicao.add(btnAlt, "width 80!, height 30!");
                painelEdicao.add(btnExc, "width 80!, height 30!");
                card.add(painelEdicao, "gapy 5");
            } else {
                card.add(new JLabel(" "), "height 30!");
            }
            
            if (!"CRIADOR".equals(tipoUsuario)) {
                JButton btnDet = new JButton("Ver Detalhes");
                btnDet.setBackground(Color.WHITE);
                btnDet.setForeground(verde);
                btnDet.setFont(new Font("Arial", Font.BOLD, 14));
                btnDet.addActionListener(e -> { 
                    DadosCompartilhados.produtoSelecionado = nome;
                    JanelaPrincipal.mudarTela("detalhes"); 
                });
                card.add(btnDet, "width 165!, height 35!");
            } else {
                card.add(new JLabel(" "), "height 35!");
            }

            catalogo.add(card);
        }
        catalogo.revalidate();
        catalogo.repaint();
    }

    private JButton criarBotaoNav(String texto) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Tahoma", Font.PLAIN, 20));
        b.setForeground(Color.WHITE);
        b.setBackground(verde);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        b.addActionListener(e -> {
            if(texto.equals("Inicio")) {
                JanelaPrincipal.mudarTela("escolha");
            } else if(texto.equals("Personalizar")) { 
                JanelaPrincipal.mudarTela("personalizar"); 
            } else if(texto.equals("Carrinho")) {
                JanelaPrincipal.mudarTela("carrinho");
            } else if(texto.equals("Perfil")) {
                JanelaPrincipal.mudarTela("perfil");
            }
        });
        return b;
    }
}