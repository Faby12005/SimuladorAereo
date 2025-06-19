import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelAvioes extends JPanel {

    private JTextField campoModelo;
    private JTextField campoCapacidade;
    private JTextField campoPrefixo;
    private DefaultListModel<String> listaAvioesModel;

    public PainelAvioes() {
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(4, 2, 10, 10));
        campoModelo = new JTextField();
        campoCapacidade = new JTextField();
        campoPrefixo = new JTextField();

        formulario.add(new JLabel("Modelo do avião:"));
        formulario.add(campoModelo);
        formulario.add(new JLabel("Capacidade (número):"));
        formulario.add(campoCapacidade);
        formulario.add(new JLabel("Prefixo (ex: PR-GOG):"));
        formulario.add(campoPrefixo);

        JButton botaoCadastrar = new JButton("Cadastrar");
        formulario.add(botaoCadastrar);

        add(formulario, BorderLayout.NORTH);

        // Lista
        listaAvioesModel = new DefaultListModel<>();
        JList<String> listaAvioes = new JList<>(listaAvioesModel);
        add(new JScrollPane(listaAvioes), BorderLayout.CENTER);

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String modelo = campoModelo.getText();
                String capacidadeStr = campoCapacidade.getText();
                String prefixo = campoPrefixo.getText();

                if (!modelo.isEmpty() && !capacidadeStr.isEmpty() && !prefixo.isEmpty()) {
                    try {
                        int capacidade = Integer.parseInt(capacidadeStr);
                        String entrada = String.format("%s - %s (%d lugares)", prefixo.toUpperCase(), modelo, capacidade);
                        listaAvioesModel.addElement(entrada);
                        campoModelo.setText("");
                        campoCapacidade.setText("");
                        campoPrefixo.setText("");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PainelAvioes.this,
                                "Capacidade deve ser um número inteiro!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(PainelAvioes.this,
                        "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
