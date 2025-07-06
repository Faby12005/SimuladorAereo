import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PainelMapa extends JPanel {

    // Representação de aeroportos e rotas
    private List<AeroportoVisual> aeroportos = new ArrayList<>();
    private List<Rota> rotas = new ArrayList<>();

    public PainelMapa() {
        setPreferredSize(new Dimension(800, 600));

        // Adicionando aeroportos fictícios com coordenadas X e Y
        aeroportos.add(new AeroportoVisual("Praia", 150, 300)); 
        aeroportos.add(new AeroportoVisual("Fogo", 300, 250));
        aeroportos.add(new AeroportoVisual("São Vicente", 250, 200)); 

        aeroportos.add(new AeroportoVisual("Sal", 500, 100)); 
        aeroportos.add(new AeroportoVisual("São Nicolau", 550, 250));
        aeroportos.add(new AeroportoVisual("Boavista", 450, 200));

        aeroportos.add(new AeroportoVisual("Maio", 250, 500));

        // Adicionando voos simulados (rotas)
      
        rotas.add(new Rota("São Vicente", "São Nicolau", this));
        rotas.add(new Rota("São Nicolau", "Sal", this));
        rotas.add(new Rota("Sal", "Boavista", this));
        rotas.add(new Rota("Boavista", "Maio", this));
        rotas.add(new Rota("Maio", "Praia", this));
        rotas.add(new Rota("Praia", "Fogo", this));
      

        for (Rota r : rotas) {
            r.iniciarAnimacao();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Suavização dos gráficos
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fundo
        g2.setColor(new Color(230, 240, 255));
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Desenhar rotas (linhas)
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.GRAY);
        for (Rota rota : rotas) {
            AeroportoVisual origem = buscarAeroporto(rota.origem);
            AeroportoVisual destino = buscarAeroporto(rota.destino);
            if (origem != null && destino != null) {
                g2.drawLine(origem.x, origem.y, destino.x, destino.y);

                // Coordenadas do avião em movimento
                int xAvião = origem.x + (destino.x - origem.x) * rota.progresso / 100;
                int yAvião = origem.y + (destino.y - origem.y) * rota.progresso / 100;

                // Avião (como texto emoji, ou você pode usar imagem depois)
                g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
                g2.drawString("✈️", xAvião - 8, yAvião - 8);

            }
        }

        // Desenhar aeroportos (círculos e nomes)
        for (AeroportoVisual ap : aeroportos) {
            g2.setColor(Color.BLUE);
            g2.fillOval(ap.x - 8, ap.y - 8, 16, 16);
            g2.setColor(Color.BLACK);
            g2.drawString(ap.nome, ap.x - 10, ap.y - 10);
        }
    }

    private AeroportoVisual buscarAeroporto(String nome) {
        for (AeroportoVisual ap : aeroportos) {
            if (ap.nome.equalsIgnoreCase(nome)) {
                return ap;
            }
        }
        return null;
    }

    // Classe para representar um ponto de aeroporto
    private static class AeroportoVisual {
        String nome;
        int x, y;

        public AeroportoVisual(String nome, int x, int y) {
            this.nome = nome;
            this.x = x;
            this.y = y;
        }
    }

    // Classe para representar uma rota entre dois aeroportos
    private static class Rota {
        String origem, destino;
        int progresso = 0; // de 0 a 100
        Timer animacao; // controla a movimentação do avião

        public Rota(String origem, String destino) {
            this.origem = origem;
            this.destino = destino;
        }

        public void iniciarAnimacao() {
            progresso = 0;
            animacao = new Timer(50, e -> {
                progresso++;
                if (progresso > 100) {
                    progresso = 0; // reinicia voo
                }
                painelMapa.repaint(); // repinta a tela
            });
            animacao.start();
        }

        PainelMapa painelMapa;

        public Rota(String origem, String destino, PainelMapa painel) {
            this.origem = origem;
            this.destino = destino;
            this.painelMapa = painel;
        }

    }
}