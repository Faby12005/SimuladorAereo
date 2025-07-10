import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Relatorio {
    private static PilhaMensagens pilha = new PilhaMensagens();
    private static Timer timer;

    private static final String[] voos = {"CV101", "CV202", "CV303", "CV404", "CV505", "CV606"};
    private static int totalRealizados = 0;
    private static int totalCancelados = 0;

    // Inicia a geração automática de relatórios a cada 5 segundos
    public static void iniciarRelatorioAutomatico() {
        if (timer == null) {
            timer = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gerarRelatorioAutomatico();
        }       }
        );
            timer.start();
        }
    }

    // Método interno que gera relatório automático e empilha a mensagem
    private static void gerarRelatorioAutomatico() {
        Random rand = new Random();
        String codigo = voos[rand.nextInt(voos.length)];
        boolean realizado = rand.nextBoolean();
        String status = realizado ? "Realizado" : "Cancelado";
        String hora = LocalTime.now().withNano(0).toString();

        if (realizado) totalRealizados++;
        else totalCancelados++;

        String mensagem = "✈️ Voo " + codigo + " " + status +
                          " às " + hora +
                          " ⇒ Totais: R=" + totalRealizados +
                          " | C=" + totalCancelados;
        pilha.push(mensagem);

        // Se quiser exibir popup toda hora, descomente:
        // JOptionPane.showMessageDialog(null, mensagem, "📊 Relatório de Voos", JOptionPane.INFORMATION_MESSAGE);
    }

    // Gera o relatório formatado para o dia informado
    public static String gerarRelatorio(LocalDate data) {
        StringBuilder sb = new StringBuilder();
        sb.append("📊 RELATÓRIO DE VOOS EM ").append(data).append("\n\n");
        sb.append("Total de voos realizados: ").append(totalRealizados).append("\n");
        sb.append("Total de voos cancelados: ").append(totalCancelados).append("\n\n");

        sb.append("📌 Histórico recente:\n");
        sb.append(pilha.listar());

        return sb.toString();
    }

    // Retorna todo o histórico da pilha como string
    public static String historico() {
        return pilha.listar();
    }

    // Remove e retorna a última mensagem adicionada (pop)
    public static String desempilharRelatorio() {
        return pilha.pop();
    }

    // Exibe todo o histórico completo em uma caixa de diálogo
    public static void exibirHistoricoCompleto() {
        JOptionPane.showMessageDialog(null, pilha.listar(), "Histórico de Relatórios", JOptionPane.INFORMATION_MESSAGE);
    }
}
