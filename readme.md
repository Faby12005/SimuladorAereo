# ✈️ Simulador de Transporte Aéreo

## 🧩 Resumo do Sistema Simulador de Transporte Aéreo

### 1. Interface gráfica (GUI) com Swing

O sistema possui uma interface gráfica desenvolvida com Java Swing, organizada por painéis que representam funcionalidades específicas:

- **Página Inicial**  
  Exibe uma imagem de fundo e uma mensagem de boas-vindas.

- **Simulação / Mapa interativo dos voos**  
  Mostra rotas entre aeroportos com animação gráfica e percursos usando algoritmos de grafos.

- **Painel de Decisões de Voo**  
  Exibe uma tabela de voos com seus respectivos status (confirmado, reprogramado, cancelado) e uma lista dinâmica de reprogramações. Ambas as seções têm imagens de fundo e são atualizadas automaticamente.

- **Painel de Relatórios**  
  Mostra relatórios atualizados a cada 5 segundos, com dados formatados sobre os voos e passageiros.

- **Tela de Compra de Bilhetes**  
  Permite ao usuário escolher um voo, informar quantidade de bilhetes e nome do passageiro. Exibe o preço total e uma imagem do avião relacionado.

---

### 2. Estrutura de Dados

#### 🧷 Lista Duplamente Ligada (`Lista`, `No`)
- Utilizada para armazenar os voos cadastrados.
- Permite navegação para frente e para trás.
- Suporte para adição, busca e listagem (direta ou reversa) dos voos.

#### 🛫 Classe `Voo`
- Representa um voo com os seguintes atributos:
  - Código
  - Origem
  - Destino
  - Horário
  - Data

#### 💳 Classe `Compra` e Lista de Compras
- Armazena os bilhetes comprados.
- Cada compra contém dados do passageiro e do voo.
- Métodos para:
  - Calcular o total de passageiros por data.
  - Agrupar passageiros por faixa etária.

#### 👤 Classe `Passageiro`
- (Não detalhada aqui, mas utilizada nas compras)
- Armazena nome e faixa etária de cada passageiro.

---

### 3. Funcionalidades Principais

#### ✅ Compra de Bilhetes
- Usuário seleciona código do voo, nome e quantidade de bilhetes.
- O sistema valida os dados e exibe confirmação com preço e imagem do avião.

#### ✈️ Decisões de Voo
- Tabela com status de todos os voos.
- Lista de reprogramações atualizada automaticamente via `javax.swing.Timer` a cada 5 segundos.

#### 📊 Relatórios Automáticos
- Exibe:
  - Quantidade de voos do dia e do dia seguinte.
  - Total de passageiros por faixa etária.
  - Passageiros por ilha de origem.
- Atualizado automaticamente via `Timer`.

#### 🌍 Mapa Interativo de Voos
- Painel com desenho interativo das rotas entre aeroportos.
- Utiliza estrutura de grafos (`No`, `GrafoRotas`) e algoritmo DFS.
- Animação do avião voando entre as ilhas.

#### 🖼️ Imagens e Preços
- Exibição de imagem do avião e preço ao selecionar o código do voo na compra.

---

### 4. Atualizações Periódicas

- Utilização de `javax.swing.Timer` para atualização contínua:
  - Reprogramações na tela de decisões de voo.
  - Relatórios automáticos no painel de relatórios.

---

### 5. Tratamento de Erros e Validações

- Verificação de preenchimento dos campos obrigatórios na compra.
- Exibição de mensagens de erro em casos como:
  - Código de voo não encontrado.
  - Quantidade inválida.

---

### 👨‍💻 Desenvolvido com:
- **Java SE**
- **Java Swing**
- **Estruturas de Dados Personalizadas (Lista Ligada, Pilha, Grafo)**
- **Animação e Timers**

---



