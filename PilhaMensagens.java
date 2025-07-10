

public class PilhaMensagens {
    private NoMensagem topo;

    public void push(String msg) {
        NoMensagem novo = new NoMensagem(msg);
        novo.prox = topo;
        topo = novo;
    }

    public String listar() {
        StringBuilder sb = new StringBuilder();
        NoMensagem atual = topo;
        while (atual != null) {
            sb.append(atual.mensagem).append("\n");
            atual = atual.prox;
        }
        return sb.toString();
    }

    public String pop() {
        if (topo == null) return null;
        String msg = topo.mensagem;
        topo = topo.prox;
        return msg;
    }

    public boolean estaVazia() {
        return topo == null;
    }
}
