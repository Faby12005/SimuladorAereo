import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class Relatorio {
    public static Lista listaVoos = new Lista();

    public static String gerarRelatorio(LocalDate data) {
        int numVoosHoje = 0;
        int numVoosAmanha = 0;

        No atual = listaVoos.getInicio();
        while (atual != null) {
            LocalDate dataVoo = atual.voo.getData();
            if (dataVoo != null) {
                if (dataVoo.equals(data)) {
                    numVoosHoje++;
                } else if (dataVoo.equals(data.plusDays(1))) {
                    numVoosAmanha++;
                }
            }
            atual = atual.prox;
        }

        int passageirosHoje = Compras.totalPassageirosPorData(data);
        int criancas = Compras.totalPassageirosPorFaixaEtaria(data, "Criança");
        int adultos = Compras.totalPassageirosPorFaixaEtaria(data, "Adulto");
        int idosos = Compras.totalPassageirosPorFaixaEtaria(data, "Idoso");

        Map<String, Integer> passageirosPorOrigem = new HashMap<>();
        List<Compra> comprasHoje = Compras.getComprasPorData(data);
        for (Compra compra : comprasHoje) {
            String origem = compra.getVoo().getOrigem();
            passageirosPorOrigem.put(origem, passageirosPorOrigem.getOrDefault(origem, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Relatório do Aeroporto para o dia ").append(data).append(":\n");
        sb.append("Voos realizados hoje: ").append(numVoosHoje).append("\n");
        sb.append("Voos previstos para amanhã: ").append(numVoosAmanha).append("\n");
        sb.append("Total de passageiros hoje: ").append(passageirosHoje).append("\n");
        sb.append(" - Crianças: ").append(criancas).append("\n");
        sb.append(" - Adultos: ").append(adultos).append("\n");
        sb.append(" - Idosos: ").append(idosos).append("\n");
        sb.append("Passageiros por ilha de origem:\n");
        for (Map.Entry<String, Integer> entry : passageirosPorOrigem.entrySet()) {
            sb.append("  * ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        sb.append("Equipamentos gastos: (a implementar)\n");

        return sb.toString();
    }

    public static void mostrarRelatorio(LocalDate data) {
        String textoRelatorio = gerarRelatorio(data);
        JOptionPane.showMessageDialog(null, textoRelatorio, "Relatório de Voos", JOptionPane.INFORMATION_MESSAGE);
    }
}
