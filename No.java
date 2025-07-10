import java.util.*;

public class No {
    public String nome;
    public int x, y;
    public List<No> adjacentes = new ArrayList<>();

    public No(String nome, int x, int y) {
        this.nome = nome;
        this.x = x;
        this.y = y;
    }
}
