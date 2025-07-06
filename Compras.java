import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compras {
    private static List<Compra> listaCompras = new ArrayList<>();

    public static void adicionarCompra(Compra compra) {
        listaCompras.add(compra);
    }

    public static int totalPassageirosPorData(LocalDate data) {
        int count = 0;
        for (Compra c : listaCompras) {
            if (c.getVoo().getData().equals(data)) {
                count++;
            }
        }
        return count;
    }

    public static int totalPassageirosPorFaixaEtaria(LocalDate data, String faixaEtaria) {
        int count = 0;
        for (Compra c : listaCompras) {
            if (c.getVoo().getData().equals(data) && c.getPassageiro().getFaixaEtaria().equals(faixaEtaria)) {
                count++;
            }
        }
        return count;
    }

    public static List<Compra> getComprasPorData(LocalDate data) {
        List<Compra> resultado = new ArrayList<>();
        for (Compra c : listaCompras) {
            if (c.getVoo().getData().equals(data)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public static int totalPassageiros() {
        return listaCompras.size();
    }
}
