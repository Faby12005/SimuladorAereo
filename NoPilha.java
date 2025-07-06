public class NoPilha {
    String alteracao;
    NoPilha anterior;

    public NoPilha(String alteracao) {
        this.alteracao = alteracao;
        this.anterior = null;
    }
}
