import java.util.Scanner;

public class Jogador {
    private String nome;
    private char[][] meuJogo;
    private char[][] jogoDoAdversario;
    private static final int TAMANHO_TABULEIRO = 8;
    private static final char AGUA = '-';
    private static final char TIRO_NA_AGUA = 'O';
    private static final char TIRO_ACERTO = 'X';

    public Jogador(String nome) {
        this.nome = nome;
        this.meuJogo = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        this.jogoDoAdversario = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        inicializarTabuleiros();
    }

    private void inicializarTabuleiros() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                meuJogo[i][j] = AGUA;
                jogoDoAdversario[i][j] = AGUA;
            }
        }
    }

    public void posicionarArmas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(nome + ", posicione suas armas no tabuleiro.");
        posicionarArma(scanner, 's', 3);
        posicionarArma(scanner, 'c', 2);
        posicionarArma(scanner, 'p', 1);
    }

    private void posicionarArma(Scanner scanner, char tipo, int quantidade) {
        System.out.println("Posicione " + quantidade + " " + (tipo == 's' ? "Submarino(s)" : tipo == 'c' ? "Cruzador(es)" : "Porta-avião(ões)") + " (" + tipo + ").");
        for (int i = 0; i < quantidade; i++) {
            System.out.println("Digite a linha e coluna para o " + (i + 1) + "º " + tipo + " (exemplo: 1 2): ");
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();
            if (linha >= 0 && linha < TAMANHO_TABULEIRO && coluna >= 0 && coluna < TAMANHO_TABULEIRO && meuJogo[linha][coluna] == AGUA) {
                meuJogo[linha][coluna] = tipo;
            } else {
                System.out.println("Posição inválida. Tente novamente.");
                i--;
            }
        }
    }

    public boolean atirar(Jogador adversario) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            System.out.println(nome + ", informe a linha e coluna do seu tiro " + (i + 1) + " (exemplo: 1 2): ");
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();

            if (linha >= 0 && linha < TAMANHO_TABULEIRO && coluna >= 0 && coluna < TAMANHO_TABULEIRO) {
                char resultado = adversario.verificarSeAcertou(linha, coluna);
                jogoDoAdversario[linha][coluna] = resultado;
                if (resultado == TIRO_ACERTO) {
                    System.out.println("Acertou uma arma!");
                } else {
                    System.out.println("Água!");
                }
            } else {
                System.out.println("Posição inválida. Tente novamente.");
                i--;
            }
        }
        return adversario.todasArmasAfundadas();
    }

    public char verificarSeAcertou(int linha, int coluna) {
        if (meuJogo[linha][coluna] != AGUA) {
            char tipo = meuJogo[linha][coluna];
            meuJogo[linha][coluna] = TIRO_ACERTO;
            return TIRO_ACERTO;
        } else {
            return TIRO_NA_AGUA;
        }
    }

    public boolean todasArmasAfundadas() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (meuJogo[i][j] == 's' || meuJogo[i][j] == 'c' || meuJogo[i][j] == 'p') {
                    return false;
                }
            }
        }
        return true;
    }

    public void exibirTabuleiroAdversario() {
        System.out.println("Tabuleiro de tiros:");
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                System.out.print(jogoDoAdversario[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String getNome() {
        return nome;
    }
}
