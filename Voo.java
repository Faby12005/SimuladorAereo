public class Voo {
    private String codigo; 
    private String origem; 
    private String destino;
    private String horario;

    public Voo(String codigo, String origem, String destino, String horario) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.horario = horario;
    }

    public String toString() {
        return codigo + " | " + origem + " → " + destino + " | " + horario;
    }
}
