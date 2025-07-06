public class Pilha {
    
    private NoPilha topo;

    public void empilhar(String alteracao) {
        NoPilha novo = new NoPilha(alteracao);
        novo.anterior = topo;
        topo = novo;
    }

    public String desempilhar() {
        if (topo == null) {
            return null;
        }
        String valor = topo.alteracao;
        topo = topo.anterior;
        return valor;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public String historico() {
        if (estaVazia()) return "Nenhuma reprogramação registrada.";

        StringBuilder sb = new StringBuilder("Histórico de reprogramações:\n");
        NoPilha atual = topo;
        while (atual != null) {
            sb.append("- ").append(atual.alteracao).append("\n");
            atual = atual.anterior;
        }
        return sb.toString();
    }
}


