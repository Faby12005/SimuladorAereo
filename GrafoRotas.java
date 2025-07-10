import java.util.*;

public class GrafoRotas {
    public List<No> nos = new ArrayList<>();

    public void adicionarNo(String nome, int x, int y) {
        nos.add(new No(nome, x, y));
    }

    public void conectar(String origem, String destino) {
        No noOrigem = buscarNo(origem);
        No noDestino = buscarNo(destino);
        if (noOrigem != null && noDestino != null) {
            noOrigem.adjacentes.add(noDestino);
            noDestino.adjacentes.add(noOrigem); // grafo não-direcionado
        }
    }

    public No buscarNo(String nome) {
        for (No n : nos) {
            if (n.nome.equalsIgnoreCase(nome)) {
                return n;
            }
        }
        return null;
    }
}
