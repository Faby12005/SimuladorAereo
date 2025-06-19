import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelCompanhias extends JPanel {

    private JTextField campoNome;
    private DefaultListModel<String> listaCompanhiasModel;

    public PainelCompanhias() {
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(2, 2, 10, 10));
        campoNome = new JTextField();

        formulario.add(new JLabel("Nome da companhia aérea:"));
        formulario.add(campoNome);

        JButton botaoCadastrar = new JButton("Cadastrar");
        formulario.add(botaoCadastrar);

        add(formulario, BorderLayout.NORTH);

        // Lista
        listaCompanhiasModel = new DefaultListModel<>();
        JList<String> listaCompanhias = new JList<>(listaCompanhiasModel);
        add(new JScrollPane(listaCompanhias), BorderLayout.CENTER);

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();

                if (!nome.isEmpty()) {
                    listaCompanhiasModel.addElement(nome);
                    campoNome.setText("");
                } else {
                    JOptionPane.showMessageDialog(PainelCompanhias.this,
                        "Informe o nome da companhia!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

