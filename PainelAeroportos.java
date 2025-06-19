import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PainelAeroportos extends JPanel {

    private JTextField campoNome;
    private JTextField campoCodigo;
    private JTextField campoCidade;
    private DefaultListModel<String> listaAeroportosModel;

    public PainelAeroportos() {
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(4, 2, 10, 10));
        campoNome = new JTextField();
        campoCodigo = new JTextField();
        campoCidade = new JTextField();

        formulario.add(new JLabel("Nome do aeroporto:"));
        formulario.add(campoNome);
        formulario.add(new JLabel("Código (ex: GRU):"));
        formulario.add(campoCodigo);
        formulario.add(new JLabel("Cidade:"));
        formulario.add(campoCidade);

        JButton botaoCadastrar = new JButton("Cadastrar");
        formulario.add(botaoCadastrar);

        add(formulario, BorderLayout.NORTH);

        // Lista
        listaAeroportosModel = new DefaultListModel<>();
        JList<String> listaAeroportos = new JList<>(listaAeroportosModel);
        add(new JScrollPane(listaAeroportos), BorderLayout.CENTER);

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String codigo = campoCodigo.getText();
                String cidade = campoCidade.getText();

                if (!nome.isEmpty() && !codigo.isEmpty() && !cidade.isEmpty()) {
                    String entrada = String.format("%s (%s) - %s", nome, codigo.toUpperCase(), cidade);
                    listaAeroportosModel.addElement(entrada);
                    campoNome.setText("");
                    campoCodigo.setText("");
                    campoCidade.setText("");
                } else {
                    JOptionPane.showMessageDialog(PainelAeroportos.this,
                        "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

