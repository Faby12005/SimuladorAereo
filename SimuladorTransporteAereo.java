import javax.swing.*;

public class SimuladorTransporteAereo extends JFrame {
    public SimuladorTransporteAereo() {
        setTitle("Simulador de Transporte Aéreo");
        setSize(420, 330);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnSimulacoes = new JButton("Simulações");
        JButton btnDecisoes = new JButton("Decisões de Voo");
        JButton btnRelatorios = new JButton("Relatórios");
        JButton btnComprar = new JButton("Comprar Agora");

        btnSimulacoes.setBounds(100, 30, 200, 40);
        btnDecisoes.setBounds(100, 80, 200, 40);
        btnRelatorios.setBounds(100, 130, 200, 40);
        btnComprar.setBounds(100, 180, 200, 40);

        btnSimulacoes.addActionListener(e -> simularVoo());
        btnDecisoes.addActionListener(e -> mostrarDecisoes());
        btnRelatorios.addActionListener(e -> Relatorio.mostrarRelatorio());
        btnComprar.addActionListener(e -> comprarVoo());

        add(btnSimulacoes);
        add(btnDecisoes);
        add(btnRelatorios);
        add(btnComprar);

        setVisible(true);
    }

    private void simularVoo() {
        String codigo = JOptionPane.showInputDialog("Código do voo:");
        String origem = JOptionPane.showInputDialog("Origem:");
        String destino = JOptionPane.showInputDialog("Destino:");
        String horario = JOptionPane.showInputDialog("Horário:");

        if (codigo != null && origem != null && destino != null && horario != null) {
            Relatorio.adicionarVoo(new Voo(codigo, origem, destino, horario));
            JOptionPane.showMessageDialog(null, "Voo simulado com sucesso!");
        }
    }

    private void mostrarDecisoes() {
        String alteracao = JOptionPane.showInputDialog("Descreva a reprogramação:");
        if (alteracao != null && !alteracao.isEmpty()) {
            Reprogramacoes.registrarAlteracao(alteracao);
        }
        Reprogramacoes.mostrarHistorico();
    }

    private void comprarVoo() {
        String codigo = JOptionPane.showInputDialog("Digite o código do voo:");
        String lista = Relatorio.listaVoos.listar();

        if (!lista.contains(codigo)) {
            JOptionPane.showMessageDialog(null, "Voo não encontrado.");
        } else {
            JOptionPane.showMessageDialog(null, "Compra confirmada para o voo: " + codigo);
        }
    }
}