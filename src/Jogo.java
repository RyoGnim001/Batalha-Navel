import java.util.Scanner;

public class Jogo {
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o nome do Jogador 1:");
        Jogador jogador1 = new Jogador(scanner.nextLine());

        System.out.println("Informe o nome do Jogador 2:");
        Jogador jogador2 = new Jogador(scanner.nextLine());

        jogador1.posicionarArmas();
        jogador2.posicionarArmas();

        boolean jogoTerminado = false;
        while (!jogoTerminado) {
            System.out.println("\n--- Rodada do " + jogador1.getNome() + " ---");
            jogador1.exibirTabuleiroAdversario();
            jogoTerminado = jogador1.atirar(jogador2);

            if (jogoTerminado) break;

            System.out.println("\n--- Rodada do " + jogador2.getNome() + " ---");
            jogador2.exibirTabuleiroAdversario();
            jogoTerminado = jogador2.atirar(jogador1);
        }

        System.out.println("Fim de jogo! Parabéns ao vencedor!");
    }
}
