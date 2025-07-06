import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;


public class SimuladorTransporteAereo extends JFrame {
    private JPanel painelPrincipal;
    private CardLayout cardLayout;
    private Lista listaVoos = new Lista();
    private JTextField txtCodigo, txtNome;
    private JComboBox<String> comboQuantidade;
    private JLabel lblImagem, lblPreco;
    private JButton btnComprar;
    private JTable tabelaVoos;
    private DefaultListModel<String> modeloReprogramacoes = new DefaultListModel<>();
    private JTextArea relatorioText;

    

    private String[] voos = {"CV101", "CV202", "CV303"};
    private String[] precos = {"100$", "150$", "200$"};
    private String[] imagens = {
        "src/imagens/cv101.jpg",
        "src/imagens/cv202.jpg",
        "src/imagens/cv303.jpg"
    };

    public SimuladorTransporteAereo() {
        setTitle("Simulador de Transporte Aéreo");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        new javax.swing.Timer(5000, e -> {
            if (isVisible()) {
                atualizarRelatorio();
            }
        }).start();
        
        // Criação dos voos com data (exemplo 6 de julho de 2025)
        listaVoos.adicionar(new Voo("CV101", "São Vicente", "Sal", "08:30", LocalDate.of(2025,7,6)));
        listaVoos.adicionar(new Voo("CV202", "Praia", "Boa Vista", "09:15", LocalDate.of(2025,7,6)));
        listaVoos.adicionar(new Voo("CV303", "Sal", "Maio", "11:00", LocalDate.of(2025,7,6)));

        JMenuBar menuBar = new JMenuBar();
        JMenu menuInicio = new JMenu("Início");
        JMenu menuSimulacoes = new JMenu("Simulações");
        JMenu menuDecisoes = new JMenu("Decisões de Voo");
        JMenu menuRelatorios = new JMenu("Relatórios");
        JMenu menuComprar = new JMenu("Comprar Bilhetes");

        menuBar.add(menuInicio);
        menuBar.add(menuSimulacoes);
        menuBar.add(menuDecisoes);
        menuBar.add(menuRelatorios);
        menuBar.add(menuComprar);

        JMenuItem itemVoltarInicio = new JMenuItem("Voltar à Página Inicial");
        JMenuItem itemSimular = new JMenuItem("Simulação de voos");
        JMenuItem itemDecidir = new JMenuItem("Ver Reprogramações");
        JMenuItem itemRelatorio = new JMenuItem("Ver Relatório");
        JMenuItem itemComprar = new JMenuItem("Comprar Bilhete");

        menuInicio.add(itemVoltarInicio);
        menuSimulacoes.add(itemSimular);
        menuDecisoes.add(itemDecidir);
        menuRelatorios.add(itemRelatorio);
        menuComprar.add(itemComprar);

        setJMenuBar(menuBar);

        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        painelPrincipal.add(criarPainelInicial(), "inicial");
        painelPrincipal.add(criarPainelSimulacoes(), "simulacoes");
        painelPrincipal.add(criarPainelDecisoes(), "decisoes");
        painelPrincipal.add(criarPainelRelatorios(), "relatorios");
        painelPrincipal.add(criarPainelComprar(), "comprar");

        add(painelPrincipal);
        cardLayout.show(painelPrincipal, "inicial");

        itemVoltarInicio.addActionListener(e -> cardLayout.show(painelPrincipal, "inicial"));
        itemSimular.addActionListener(e -> cardLayout.show(painelPrincipal, "simulacoes"));
        itemDecidir.addActionListener(e -> {
            atualizarHistoricoDecisoes();
            atualizarTabelaHorarios(tabelaVoos, listaVoos);
            cardLayout.show(painelPrincipal, "decisoes");
        });
        itemRelatorio.addActionListener(e -> {
    cardLayout.show(painelPrincipal, "relatorios");
    atualizarRelatorio();  // atualiza o texto do JTextArea imediatamente
});

        itemComprar.addActionListener(e -> cardLayout.show(painelPrincipal, "comprar"));

        iniciarAtualizacaoDecisoes();

        setVisible(true);
    }

    private JPanel criarPainelInicial() {
        JPanel painelInicial = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon("img/aviao-tacv.jpg").getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelInicial.setLayout(new BorderLayout());

        JLabel textoBemVindo = new JLabel("Bem-vindo ao Simulador de Transporte Aéreo", SwingConstants.CENTER);
        textoBemVindo.setFont(new Font("SansSerif", Font.BOLD, 22));
        textoBemVindo.setForeground(Color.WHITE);
        textoBemVindo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        painelInicial.add(textoBemVindo, BorderLayout.NORTH);
        return painelInicial;
    }

    private JPanel criarPainelSimulacoes() {
        JPanel painelSimulacoes = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Mapa Interativo de Voos", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelSimulacoes.add(titulo, BorderLayout.NORTH);
        painelSimulacoes.add(new PainelMapa(), BorderLayout.CENTER);
        return painelSimulacoes;
    }

    private JPanel criarPainelDecisoes() {
        JPanel painelDecisoes = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon("img/international-2690971_1280.jpg").getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        String[] colunas = {"Voo", "Origem", "Destino", "Horário Previsto", "Status", "Observação"};
        Object[][] dados = {
            {"CV101", "São Vicente", "Sal", "08:30", "Confirmado", ""},
            {"CV202", "Praia", "Boa Vista", "09:15", "Reprogramado", "Saída às 10:00"},
            {"CV303", "Sal", "Maio", "11:00", "Cancelado", "Clima ruim"}
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(dados, colunas);
        tabelaVoos = new JTable(modeloTabela);
        tabelaVoos.setOpaque(false);
        ((DefaultTableCellRenderer) tabelaVoos.getDefaultRenderer(Object.class)).setOpaque(false);
        JScrollPane scrollTabela = new JScrollPane(tabelaVoos);
        scrollTabela.getViewport().setOpaque(false);

        JList<String> listaReprogramacoes = new JList<>(modeloReprogramacoes);
        listaReprogramacoes.setOpaque(false);
        JScrollPane scrollReprogramacoes = new JScrollPane(listaReprogramacoes);
        scrollReprogramacoes.getViewport().setOpaque(false);

        JTabbedPane abas = new JTabbedPane();
        abas.setOpaque(false);
        abas.addTab("Horários de Voos", scrollTabela);
        abas.addTab("Reprogramações", scrollReprogramacoes);

        painelDecisoes.add(abas, BorderLayout.CENTER);
        return painelDecisoes;
    }

    private void atualizarHistoricoDecisoes() {
        modeloReprogramacoes.clear();
        String[] historico = Reprogramações.historico().split("\n");
        for (String linha : historico) {
            modeloReprogramacoes.addElement(linha);
        }
    }

    public void atualizarTabelaHorarios(JTable tabela, Lista listaVoos) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        No atual = listaVoos.getInicio();
        while (atual != null) {
            modelo.addRow(new Object[]{
                atual.voo.getCodigo(),
                atual.voo.getOrigem(),
                atual.voo.getDestino(),
                atual.voo.getHorario()
            });
            atual = atual.prox;
        }
    }


    
    private JPanel criarPainelRelatorios() {
    JPanel p = new JPanel(new BorderLayout());

    relatorioText = new JTextArea();
    relatorioText.setEditable(false);
    relatorioText.setFont(new Font("Monospaced", Font.PLAIN, 14));
    JScrollPane scroll = new JScrollPane(relatorioText);
    p.add(scroll, BorderLayout.CENTER);

    // Inicializa o texto com relatório atual
    relatorioText.setText(Relatorio.gerarRelatorio(LocalDate.now()));

    return p;
}
private void atualizarRelatorio() {
    relatorioText.setText(Relatorio.gerarRelatorio(LocalDate.now()));
}
   


    private JPanel criarPainelComprar() {
        JPanel p = new JPanel(null);

        JLabel lblCodigo = new JLabel("Código do Voo:");
        lblCodigo.setBounds(10, 10, 100, 25);
        p.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(120, 10, 100, 25);
        p.add(txtCodigo);

        JLabel lblNome = new JLabel("Nome do Passageiro:");
        lblNome.setBounds(10, 50, 130, 25);
        p.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(150, 50, 200, 25);
        p.add(txtNome);

        JLabel lblQtd = new JLabel("Quantidade:");
        lblQtd.setBounds(10, 90, 100, 25);
        p.add(lblQtd);

        comboQuantidade = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        comboQuantidade.setBounds(120, 90, 50, 25);
        p.add(comboQuantidade);

        lblImagem = new JLabel();
        lblImagem.setBounds(370, 10, 150, 100);
        lblImagem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        p.add(lblImagem);

        lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(370, 120, 150, 25);
        p.add(lblPreco);

        btnComprar = new JButton("Comprar");
        btnComprar.setBounds(10, 140, 100, 30);
        p.add(btnComprar);

        btnComprar.addActionListener(e -> comprar());

        txtCodigo.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                atualizarImagemPreco();
            }
        });

        return p;
    }

    private void atualizarImagemPreco() {
        String codigo = txtCodigo.getText().toUpperCase();
        for (int i = 0; i < voos.length; i++) {
            if (voos[i].equals(codigo)) {
                lblImagem.setIcon(new ImageIcon(new ImageIcon(imagens[i])
                        .getImage().getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_SMOOTH)));
                lblPreco.setText("Preço: " + precos[i]);
                return;
            }
        }
        lblImagem.setIcon(null);
        lblPreco.setText("Preço:");
    }

    private void comprar() {
        String codigo = txtCodigo.getText().toUpperCase();
        String nome = txtNome.getText();
        int qtd = comboQuantidade.getSelectedIndex() + 1;

        if (codigo.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        No atual = Relatorio.listaVoos.getInicio();
        Voo voo = null;
        while (atual != null) {
            if (atual.voo.getCodigo().equalsIgnoreCase(codigo)) {
                voo = atual.voo;
                break;
            }
            atual = atual.prox;
        }

        if (voo == null) {
            JOptionPane.showMessageDialog(this, "Voo não encontrado.");
            return;
        }

        for (int i = 0; i < qtd; i++) {
            Passageiro p = new Passageiro(nome + (qtd > 1 ? " " + (i + 1) : ""));
            Compra compra = new Compra(p, voo);
            Compras.adicionarCompra(compra);
        }

        JOptionPane.showMessageDialog(this, "Compra efetuada: " + qtd + " bilhete(s) para " + nome + " no voo " + codigo);
        txtCodigo.setText("");
        txtNome.setText("");
        comboQuantidade.setSelectedIndex(0);
        lblImagem.setIcon(null);
        lblPreco.setText("Preço:");
    }

    private void iniciarAtualizacaoDecisoes() {
        new javax.swing.Timer(5000, e -> {
            if (modeloReprogramacoes != null && isVisible()) {
                String nova = Reprogramações.desempilharAlteracao();
                if (nova != null) {
                    modeloReprogramacoes.addElement(nova);
                }
            }
        }).start();
    }

    private void mostrarRelatorio() {
        LocalDate hoje = LocalDate.now();
        String textoRelatorio = Relatorio.gerarRelatorio(hoje);
        JOptionPane.showMessageDialog(this, textoRelatorio, "Relatório de Voos", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimuladorTransporteAereo::new);
    }
}
