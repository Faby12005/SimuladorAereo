import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Reprogramações {
    private static Pilha pilha = new Pilha();
    private static Timer geradorAutomatico;

    private static final String[] voos = {"CV101", "CV202", "CV303", "CV404", "CV505", "CV606"};
    private static final String[] motivos = {
        "Atraso por mau tempo",
        "Troca de aeronave",
        "Problema técnico",
        "Alteração de rota",
        "Reprogramação por manutenção",
        "Replanejamento de horário"
    };

    public static void iniciarGeradorAutomatico() {
        if (geradorAutomatico == null) {
            geradorAutomatico = new Timer(6000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gerarReprogramacao();
                }
            });
            geradorAutomatico.start();
        }
    }

    private static void gerarReprogramacao() {
        Random rand = new Random();
        String voo = voos[rand.nextInt(voos.length)];
        String motivo = motivos[rand.nextInt(motivos.length)];
        String novaHora = (8 + rand.nextInt(12)) + "h" + (rand.nextInt(60) < 10 ? "0" : "") + rand.nextInt(60);
        String mensagem = "Voo " + voo + " foi reprogramado: " + motivo + " | Novo horário: " + novaHora;
        pilha.empilhar(mensagem);
    }

    public static String historico() {
        return pilha.historico();
    }

    public static void registrarAlteracao(String alteracao) {
        pilha.empilhar(alteracao);
        JOptionPane.showMessageDialog(null, "Alteração automática registrada com sucesso!");
    }

    public static void mostrarHistorico() {
        JOptionPane.showMessageDialog(null, pilha.historico());
    }

public static String desempilharAlteracao() {
    return pilha.desempilhar();
}

}
    

