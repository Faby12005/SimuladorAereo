import java.time.LocalDate;

public class Voo {
    private String codigo;
    private String origem;
    private String destino;
    private String horario;
    private LocalDate data;

    public Voo(String codigo, String origem, String destino, String horario, LocalDate data) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.horario = horario;
        this.data = data;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getHorario() {
        return horario;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return codigo + " - " + origem + " -> " + destino + " (" + horario + ")";
    }
}

