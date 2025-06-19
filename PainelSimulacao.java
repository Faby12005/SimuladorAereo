import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class PainelSimulacao extends JPanel {

    private List<SimulacaoVoo> voosSimulados = new ArrayList<>();
    private JPanel painelVoos;

    public PainelSimulacao() {
        setLayout(new BorderLayout());

        painelVoos = new JPanel();
        painelVoos.setLayout(new BoxLayout(painelVoos, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(painelVoos);
        add(scroll, BorderLayout.CENTER);

        JButton botaoSimular = new JButton("Iniciar Simulação de Voos");
        add(botaoSimular, BorderLayout.SOUTH);

        botaoSimular.addActionListener((ActionEvent e) -> {
            iniciarSimulacaoDeExemplo(); // depois você troca isso por dados reais
        });
    }

    private void iniciarSimulacaoDeExemplo() {
        painelVoos.removeAll();
        voosSimulados.clear();

        // Simulação de exemplo (você pode substituir isso com voos reais depois)
        for (int i = 1; i <= 5; i++) {
            String voo = "Voo " + i + " - SP → RJ";
            SimulacaoVoo simulacao = new SimulacaoVoo(voo);
            voosSimulados.add(simulacao);
            painelVoos.add(simulacao.getPainel());
            simulacao.iniciar();
        }

        revalidate();
        repaint();
    }

    private static class SimulacaoVoo {
        private String nome;
        private JPanel painel;
        private JProgressBar barraProgresso;
        private Timer timer;
        private int progresso = 0;
        private JLabel iconeAvião;
        private JLabel statusLabel;

        public SimulacaoVoo(String nome) {
            this.nome = nome;

            // Icone avião
            iconeAvião = new JLabel("🛫");
            iconeAvião.setFont(new Font("SansSerif", Font.PLAIN, 24));
            iconeAvião.setHorizontalAlignment(SwingConstants.CENTER);

            // Status
            statusLabel = new JLabel("🟡 Em voo");
            statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            // Barra de progresso
            barraProgresso = new JProgressBar(0, 100);
            barraProgresso.setStringPainted(true);
            barraProgresso.setForeground(new Color(66, 135, 245));

            // Painel de layout
            painel = new JPanel(new BorderLayout(10, 10));
            painel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            painel.setBackground(new Color(245, 248, 255));

            // Topo: título + status
            JPanel topo = new JPanel(new BorderLayout());
            topo.setOpaque(false);
            topo.add(new JLabel("<html><b>" + nome + "</b></html>"), BorderLayout.WEST);
            topo.add(statusLabel, BorderLayout.EAST);

            painel.add(topo, BorderLayout.NORTH);
            painel.add(barraProgresso, BorderLayout.CENTER);
            painel.add(iconeAvião, BorderLayout.WEST);
        }

        public JPanel getPainel() {
            return painel;
        }

        public void iniciar() {
            timer = new Timer(100, (ActionEvent e) -> {
                progresso++;
                barraProgresso.setValue(progresso);

                if (progresso >= 100) {
                    timer.stop();
                    barraProgresso.setValue(100);
                    barraProgresso.setForeground(new Color(0, 180, 80));
                    barraProgresso.setString("Concluído");
                    statusLabel.setText("🟢 Concluído");
                    iconeAvião.setText("✅");
                }
            });
            timer.start();
        }
    }
}
