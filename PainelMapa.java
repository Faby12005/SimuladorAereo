import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class PainelMapa extends JPanel {

    private GrafoRotas grafo;

    // Controle da animação DFS
    private List<No> caminhoAnimado = new ArrayList<>();
    private int passoAtual = 0;
    private float progressoAnimacao = 0.0f; // 0.0 a 1.0
    private final int DURACAO_ANIMACAO = 30; // frames por passo
    private Timer animacaoDFS;
    private boolean emPausa = false; // controla o delay de reinício

    public PainelMapa() {
        setPreferredSize(new Dimension(800, 600));
        grafo = new GrafoRotas();

        // Adicionando aeroportos
        grafo.adicionarNo("Santo Antão", 100, 100);
        grafo.adicionarNo("São Vicente", 150, 150);
        grafo.adicionarNo("São Nicolau", 230, 170);
        grafo.adicionarNo("Sal", 400, 100);
        grafo.adicionarNo("Boa Vista", 400, 200);
        grafo.adicionarNo("Maio", 480, 380);
        grafo.adicionarNo("Santiago", 350, 400);
        grafo.adicionarNo("Fogo", 250, 500);
        grafo.adicionarNo("Brava", 200, 530);

        // Adicionando rotas (grafo não-direcionado)
        grafo.conectar("São Vicente", "São Nicolau");
        grafo.conectar("Sal", "Boa Vista");
        grafo.conectar("Santiago", "Fogo");
       
        grafo.conectar("Sal", "Santiago");
        grafo.conectar("São Nicolau", "Sal");
        grafo.conectar("Boa Vista", "Maio");

        // Criar caminho DFS a partir de um nó inicial
        No inicial = grafo.buscarNo("São Vicente");
        if (inicial != null) {
            gerarCaminhoDFS(inicial);
            iniciarAnimacaoDFS();
        }
    }

    private void gerarCaminhoDFS(No inicio) {
        Set<No> visitados = new HashSet<>();
        Stack<No> pilha = new Stack<>();
        pilha.push(inicio);

        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            if (!visitados.contains(atual)) {
                visitados.add(atual);
                caminhoAnimado.add(atual);
                for (No vizinho : atual.adjacentes) {
                    pilha.push(vizinho);
                }
            }
        }
    }

    private void iniciarAnimacaoDFS() {
        animacaoDFS = new Timer(50, e -> {
            if (emPausa || caminhoAnimado.size() < 2)
                return;

            progressoAnimacao += 1.0f / DURACAO_ANIMACAO;

            if (progressoAnimacao >= 1.0f) {
                progressoAnimacao = 0.0f;
                passoAtual++;

                if (passoAtual >= caminhoAnimado.size() - 1) {
                    // Final da animação: reiniciar com pausa
                    emPausa = true;

                    // Timer de 1 segundo antes de reiniciar
                    new Timer(1000, evt -> {
                        passoAtual = 0;
                        progressoAnimacao = 0.0f;
                        emPausa = false;
                        ((Timer) evt.getSource()).stop(); // para o timer de delay
                    }).start();
                }
            }

            repaint();
        });

        animacaoDFS.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fundo do mapa
        g2.setColor(new Color(230, 240, 255));
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Desenhar rotas (linhas)
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.GRAY);
        for (No no : grafo.nos) {
            for (No vizinho : no.adjacentes) {
                g2.drawLine(no.x, no.y, vizinho.x, vizinho.y);
            }
        }

        // Desenhar aeroportos (nós)
        for (No no : grafo.nos) {
            g2.setColor(Color.BLUE);
            g2.fillOval(no.x - 8, no.y - 8, 16, 16);
            g2.setColor(Color.BLACK);
            g2.drawString(no.nome, no.x - 10, no.y - 10);
        }

        // Desenhar avião (✈️) entre os nós visitados
        if (passoAtual < caminhoAnimado.size() - 1) {
            No origem = caminhoAnimado.get(passoAtual);
            No destino = caminhoAnimado.get(passoAtual + 1);

            int xAviao = (int) (origem.x + (destino.x - origem.x) * progressoAnimacao);
            int yAviao = (int) (origem.y + (destino.y - origem.y) * progressoAnimacao);

            g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
            g2.drawString("✈️", xAviao - 8, yAviao - 8);
        }
    }
}
