import javax.swing.*;

public class JanelaPrincipal extends JFrame {

    public JanelaPrincipal() {
        setTitle("Simulador de Transporte Aéreo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centraliza

        JTabbedPane abas = new JTabbedPane();

        abas.addTab("Aeroportos", new PainelAeroportos());
        abas.addTab("Aviões", new PainelAvioes());
        abas.addTab("Companhias", new PainelCompanhias());
        abas.addTab("Simulação", new PainelSimulacao());
        abas.addTab("Mapa", new PainelMapa());
        



        // futuras abas:
        // abas.addTab("Aviões", new PainelAvioes());
        // abas.addTab("Companhias", new PainelCompanhias());
        // abas.addTab("Voos", new PainelVoos());

        add(abas);

        setVisible(true);
    }
}
