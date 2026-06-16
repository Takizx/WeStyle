package Visao;

import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

        // Adiciona a tela inicial de boas-vindas
        container.add(new Telaprincipal(), "principal");

        // Pré-carrega as telas básicas do fluxo
        container.add(new TelaEscolha(), "escolha");
        container.add(new TelaCatalogo(), "catalogo");
        container.add(new TelaPerfil(), "perfil");
        container.add(new TelaPersonalizar(), "personalizar");
        container.add(new TelaEstampa(), "estampa");
        container.add(new TelaRecuperarSenha(), "recuperarSenha");
        
        // CORREÇÃO: Adicionando as instâncias iniciais de entrar e cadastro no container
        container.add(new Telaentrar(), "entrar");
        container.add(new Telacadastro(), "cadastro");

        getContentPane().add(container);
        
        cl.show(container, "principal"); 
    }

    /**
     * Alterna a exibição para o painel correspondente ao nome informado.
     * @param nomeDaTela String identificadora ("principal", "escolha", "catalogo", etc.)
     */
    public static void mudarTela(String nomeDaTela) {
   
        // Recria ou atualiza as telas dinamicamente antes de exibir para evitar dados desatualizados ou travados
        if (nomeDaTela.equalsIgnoreCase("perfil")) {
            container.add(new TelaPerfil(), "perfil");
        } else if (nomeDaTela.equalsIgnoreCase("personalizar")) {
            container.add(new TelaPersonalizar(), "personalizar");
        } else if (nomeDaTela.equalsIgnoreCase("catalogo")) {
            container.add(new TelaCatalogo(), "catalogo");
        } else if (nomeDaTela.equalsIgnoreCase("estampa")) {
            container.add(new TelaEstampa(), "estampa");
        } else if (nomeDaTela.equalsIgnoreCase("entrar")) {
            container.add(new Telaentrar(), "entrar"); // Limpa os campos de login ao abrir
        } else if (nomeDaTela.equalsIgnoreCase("cadastro")) {
            container.add(new Telacadastro(), "cadastro"); // Limpa os campos de cadastro ao abrir
        }
        
        cl.show(container, nomeDaTela);
    }
}