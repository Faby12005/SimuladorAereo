public class Lista {
    private No inicio;
    private No fim;

    public No getInicio() {
        return inicio;
    }

    public No getFim() {
        return fim;
    }

    public void adicionar(Voo voo) {
        No novo = new No(voo);
        if (inicio == null) {
            inicio = novo;
            fim = novo;
        } else {
            fim.prox = novo;
            novo.ant = fim;
            fim = novo;
        }
    }

    public String listarDireto() {
        if (inicio == null) return "Nenhum voo registrado.";
        StringBuilder sb = new StringBuilder();
        No atual = inicio;
        while (atual != null) {
            sb.append(atual.voo.toString()).append("\n");
            atual = atual.prox;
        }
        return sb.toString();
    }

    public String listarReverso() {
        if (fim == null) return "Nenhum voo registrado.";
        StringBuilder sb = new StringBuilder();
        No atual = fim;
        while (atual != null) {
            sb.append(atual.voo.toString()).append("\n");
            atual = atual.ant;
        }
        return sb.toString();
    }

    public Voo buscar(String codigo) {
        No atual = inicio;
        while (atual != null) {
            if (atual.voo.getCodigo().equalsIgnoreCase(codigo)) {
                return atual.voo;
            }
            atual = atual.prox;
        }
        return null;
    }
}
