public class Passageiro {
    private String nome;
    private int idade; // em anos

    public Passageiro(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    

    public Passageiro(String string) {
        //TODO Auto-generated constructor stub
    }



    public String getFaixaEtaria() {
        if (idade < 12) return "Criança";
        else if (idade < 60) return "Adulto";
        else return "Idoso";
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}


