import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LivroGrafo grafo = new LivroGrafo();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        System.out.println("╔═╗╦ ╦╔═╗╔═╗╦═╗       ╔═╗╔╦╗╦╦  ╦╦╔╦╗╔═╗╔╦╗╔═╗  ╔═╗╔═╗╔╦╗╔═╗╔╦╗╦╦  ╦╔═╗  ╦\n" +
                "╠═╝║ ║║  ╠═╝╠╦╝  ───  ╠═╣ ║ ║╚╗╔╝║ ║║╠═╣ ║║║╣   ╚═╗║ ║║║║╠═╣ ║ ║╚╗╔╝╠═╣  ║\n" +
                "╩  ╚═╝╚═╝╩  ╩╚═       ╩ ╩ ╩ ╩ ╚╝ ╩═╩╝╩ ╩═╩╝╚═╝  ╚═╝╚═╝╩ ╩╩ ╩ ╩ ╩ ╚╝ ╩ ╩  ╩\n");


        System.out.println("╔╗ ╔═╗╔╦╗  ╦  ╦╦╔╗╔╔╦╗╔═╗                                                                   \n" +
                "╠╩╗║╣ ║║║  ╚╗╔╝║║║║ ║║║ ║                                                                   \n" +
                "╚═╝╚═╝╩ ╩   ╚╝ ╩╝╚╝═╩╝╚═╝                                                                   \n" +
                "\n"+
                "╔╦╗╔═╗╔═╗╔═╗╔╗╔╦  ╦╔═╗╦ ╦  ╦╦╔╦╗╔═╗  ╔═╗╔═╗╦═╗  ╔═╗╔═╗╔╦╗╦═╗╔═╗  ╔═╗  ╔═╗╔═╗╦═╗╔╦╗╔═╗╦╦═╗╔═╗\n" +
                " ║║║╣ ╚═╗║╣ ║║║╚╗╔╝║ ║║ ╚╗╔╝║ ║║║ ║  ╠═╝║ ║╠╦╝  ╠═╝║╣  ║║╠╦╝║ ║  ╚═╗  ║  ║ ║╠╦╝ ║║║╣ ║╠╦╝║ ║\n" +
                "═╩╝╚═╝╚═╝╚═╝╝╚╝ ╚╝ ╚═╝╩═╝╚╝ ╩═╩╝╚═╝  ╩  ╚═╝╩╚═  ╩  ╚═╝═╩╝╩╚═╚═╝  ╚═╝  ╚═╝╚═╝╩╚══╩╝╚═╝╩╩╚═╚═╝\n");


        do {
            System.out.println("\\│/\\│/\\│/  ╔╦╗╔═╗╔╗╔╦ ╦  \\│/\\│/\\│/\n" +
                    "─ ── ── ─  ║║║║╣ ║║║║ ║  ─ ── ── ─\n" +
                    "/│\\/│\\/│\\  ╩ ╩╚═╝╝╚╝╚═╝  /│\\/│\\/│\\\n");
            System.out.println("1. Cadastrar novo livro");
            System.out.println("2. Imprimir recomendações");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println();
            switch (opcao) {
                case 1:
                    scanner.nextLine(); // Limpa o buffer do scanner
                    grafo.cadastrarLivro(scanner);
                    break;
                case 2:
                    grafo.imprimeRecomendacoes();
                    break;
                case 3:
                    System.out.println("╔═╗╔╗╔╔═╗╔═╗╦═╗╦═╗╔═╗╔╗╔╔╦╗╔═╗  ╔═╗  ╔═╗╦═╗╔═╗╔═╗╦═╗╔═╗╔╦╗╔═╗   \n" +
                            "║╣ ║║║║  ║╣ ╠╦╝╠╦╝╠═╣║║║ ║║║ ║  ║ ║  ╠═╝╠╦╝║ ║║ ╦╠╦╝╠═╣║║║╠═╣   \n" +
                            "╚═╝╝╚╝╚═╝╚═╝╩╚═╩╚═╩ ╩╝╚╝═╩╝╚═╝  ╚═╝  ╩  ╩╚═╚═╝╚═╝╩╚═╩ ╩╩ ╩╩ ╩ooo");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 3);

        scanner.close();
    }
}
