import java.util.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Livro {
    private final String titulo;
    private final String autor;
    private final int paginas;
    private final Genero genero;
    private final LivroGrafo grafo;

    public Livro(String titulo, String autor, int paginas, Genero genero, LivroGrafo grafo) {
        if (!GeneroLivro.isValidGenero(genero)) {
            throw new IllegalArgumentException("Gênero inválido: " + genero);
        }
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.genero = genero;
        this.grafo = grafo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Genero getGenero() {
        return genero;
    }
}

class LivroGrafo {
    private static final int MAX_VALUE = Integer.MAX_VALUE;

    private final Map<Livro, Set<Livro>> recomendacoes = new HashMap<>();
    private final List<Livro> listaDeLivros = new ArrayList<>();

    public LivroGrafo() {
        // Inicialização da lista de livros
        listaDeLivros.add(new Livro("A Culpa é das Estrelas", "John Green", 170, new Genero("Romance"), this));
        listaDeLivros.add(new Livro("É assim que acaba", "Colleen Hoover\n", 374, new Genero("Romance"), this));
        listaDeLivros.add(new Livro("Harry Potter e o Enigma do Príncipe", "J.K. Rowling", 450, new Genero("Aventura"), this));
        listaDeLivros.add(new Livro("A Volta ao Mundo em 80 Dias", "Júlio Verne", 245, new Genero("Aventura"), this));
        listaDeLivros.add(new Livro("O Silmarillion", "J. R. R. Tolkien", 560, new Genero("Fantasia"), this));
        listaDeLivros.add(new Livro("O Ladrão de Raios", "Rick Riordan", 306, new Genero("Fantasia"), this));
        listaDeLivros.add(new Livro("1984", "George Orwell", 316, new Genero("Ficção Científica"), this));
        listaDeLivros.add(new Livro("Admirável Mundo Novo", "Aldous Huxley", 272, new Genero("Ficção Científica"), this));
        listaDeLivros.add(new Livro("Sapiens - Uma Breve História da Humanidade", "Yuval Harari", 362, new Genero("História"), this));
        listaDeLivros.add(new Livro("Como a música ficou grátis", "Stephen Witt", 278, new Genero("História"), this));
        listaDeLivros.add(new Livro("Silvio Santos: a biografia definitiva", "Anna Medeiros", 332, new Genero("Biografia"), this));
        listaDeLivros.add(new Livro("A Marca da Vitória", "Philip H. Knight", 286, new Genero("Biografia"), this));

        // Atribui recomendações
        atribuirRecomendacoes();
    }

    public void cadastrarLivro(Scanner leitor) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("*************************************");
        System.out.print("Digite o título do livro ou digite 'voltar' para sair do cadastro: ");
        String titulo = leitor.nextLine();

        // Verifica se o usuário deseja voltar ao menu principal
        if (titulo.equalsIgnoreCase("VOLTAR")) {
            System.out.println("* Voltando ao menu principal... *");
            return;
        }

        System.out.print("Digite o autor do livro: ");
        String autor = leitor.nextLine();
        System.out.print("Digite a quantidade de páginas do livro: ");
        int paginas = leitor.nextInt();
        System.out.println("Escolha o gênero do livro:");
        GeneroLivro.printGeneros();
        int generoEscolhido = leitor.nextInt();
        Genero genero = GeneroLivro.obterGeneroPorIndice(generoEscolhido);
        leitor.nextLine(); // Limpar o buffer do scanner
        Livro livro = new Livro(titulo, autor, paginas, genero, this);
        listaDeLivros.add(livro);
        System.out.println("** Livro cadastrado com sucesso! **");
        // Atribui recomendações
        atribuirRecomendacoes();
        System.out.println("*************************************");
        System.out.println();

        // Pergunta se deseja cadastrar outro livro
        int resposta;
        do {
            System.out.print("Digite 1 para cadastrar outro livro ou 0 para voltar ao menu principal: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida! Por favor, insira 1 ou 0.");
                scanner.next();
            }
            resposta = scanner.nextInt();
            if (resposta != 0 && resposta != 1) {
                System.out.println("Opção inválida! Por favor, insira 1 ou 0.");
            }
        } while (resposta != 0 && resposta != 1);

        if (resposta == 0) {
            System.out.println("***** Voltando ao menu principal... *****");
            System.out.println();
            return;
        } else if (resposta == 1) {
            cadastrarLivro(leitor); // Chama o método para cadastrar outro livro
        }
    }

    public void atribuirRecomendacoes() {
        recomendacoes.clear();
        for (Livro livro : listaDeLivros) {
            Set<Livro> recomendacoesParaLivro = new HashSet<>();

            switch (livro.getGenero().nome().toLowerCase()) {
                case "romance":
                    recomendacoesParaLivro.addAll(obterLivrosPorGenero(new Genero("Aventura")));
                    break;
                case "aventura":
                    recomendacoesParaLivro.addAll(obterLivrosPorGenero(new Genero("Romance")));
                    break;
                case "ficção científica":
                    recomendacoesParaLivro.addAll(obterLivrosPorGenero(new Genero("Fantasia")));
                    break;
                case "fantasia":
                    recomendacoesParaLivro.addAll(obterLivrosPorGenero(new Genero("Ficção Científica")));
                    break;
                case "história":
                    recomendacoesParaLivro.addAll(obterLivrosPorGenero(new Genero("Biografia")));
                    break;
                case "biografia":
                    recomendacoesParaLivro.addAll(obterLivrosPorGenero(new Genero("História")));
                    break;
            }

            recomendacoesParaLivro.remove(livro);

            recomendacoes.put(livro, recomendacoesParaLivro);
        }
    }

    private List<Livro> obterLivrosPorGenero(Genero genero) {
        List<Livro> livrosPorGenero = new ArrayList<>();
        for (Livro livro : listaDeLivros) {
            if (livro.getGenero().equals(genero)) {
                livrosPorGenero.add(livro);
            }
        }
        return livrosPorGenero;
    }

    public void imprimeRecomendacoes() {
        Scanner scanner = new Scanner(System.in);

        // Exibição das recomendações para cada livro
        for (Map.Entry<Livro, Set<Livro>> entry : recomendacoes.entrySet()) {
            Livro livro = entry.getKey();
            Set<Livro> recomendacoes = entry.getValue();

            System.out.println("** Para você que leu '" + livro.getTitulo() + "', você também pode gostar de : **");
            for (Livro recomendado : recomendacoes) {
                System.out.println(" - " + recomendado.getTitulo());
            }
            System.out.println();
        }

        String escolha;
        do {
            System.out.print("Digite 'voltar' para retornar ao menu principal: ");
            escolha = scanner.nextLine().trim();
        } while (!escolha.equalsIgnoreCase("voltar"));
    }

    public void imprimirDistancias() {
        Scanner scanner = new Scanner(System.in);

        // Lista de todos os livros
        System.out.println("* Escolha o livro para ver as distâncias relacionadas:");
        int i = 1;
        for (Livro livro : listaDeLivros) {
            System.out.println(i + ". " + livro.getTitulo());
            i++;
        }

        // Escolha do livro
        int indiceLivro;
        do {
            System.out.println();
            System.out.println(" --- Digite o número correspondente ao livro ou 'voltar' para retornar ao menu principal: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("voltar")) {
                System.out.println("*** Voltando ao menu principal... ***");
                return;
            }
            try {
                indiceLivro = Integer.parseInt(entrada);
                if (indiceLivro < 1 || indiceLivro > listaDeLivros.size()) {
                    System.out.println("*** Livro não encontrado! Por favor, digite o número correspondente ao livro novamente. ***");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Tente novamente.");
                indiceLivro = -1;
            }
        } while (indiceLivro < 1 || indiceLivro > listaDeLivros.size());

        Livro livroSelecionado = listaDeLivros.get(indiceLivro - 1);
        Map<Livro, Integer> distancias = Dijkstra.djikstraPonderado(criarGrafoDistancias(), livroSelecionado);

        System.out.println("Para você que leu o livro '" + livroSelecionado.getTitulo() + "':");

        List<Livro> livrosPeso1 = new ArrayList<>();
        List<Livro> livrosPeso2 = new ArrayList<>();
        List<Livro> livrosDemaisPesos = new ArrayList<>();

        // Agrupa os livros por categoria de acordo com o peso da distância
        for (Map.Entry<Livro, Integer> entry : distancias.entrySet()) {
            Livro livroDestino = entry.getKey();
            int distancia = entry.getValue();

            if (distancia == 1) {
                livrosPeso1.add(livroDestino);
            } else if (distancia == 2 && recomendacoes.get(livroSelecionado).contains(livroDestino)) {
                livrosPeso2.add(livroDestino);
            } else if (distancia > 1 && !recomendacoes.get(livroSelecionado).contains(livroDestino)) {
                livrosDemaisPesos.add(livroDestino);
            }
        }

                // Imprime os livros por categoria de acordo com o peso da distância
                imprimirLivrosPorPeso("*** Vale a pena ler! (❁´◡`❁) (peso 1) ***", livrosPeso1);
                System.out.println();
                imprimirLivrosPorPeso("*** Você também pode gostar! ╰(*°▽°*)╯ (peso 2) ***", livrosPeso2);
                System.out.println();
                imprimirLivrosPorPeso("*** Experimente novas categorias... ^_^ (demais pesos) ***", livrosDemaisPesos);

                System.out.println();

                // Perguntando ao usuário se deseja ver as distâncias de outro livro ou voltar ao menu principal
                String escolha;
                do {
                    System.out.print("Digite 'voltar' para retornar ao menu principal ou '1' para listar distâncias de outro livro: ");
                    escolha = scanner.nextLine().trim();
                    if (escolha.equalsIgnoreCase("voltar")) {
                        System.out.println("*** Voltando ao menu principal... ***");
                        return;
                    }
                } while (!escolha.equals("1"));

                imprimirDistancias();
            }



    private void imprimirLivrosPorPeso(String categoria, List<Livro> livros) {
        if (!livros.isEmpty()) {
            System.out.println(categoria + ":");
            for (Livro livro : livros) {
                System.out.println("- " + livro.getTitulo());
            }
        }
    }

    // Métodos para criar o grafo de distâncias ponderadas com base nos gêneros
    private HashMap<Livro, Set<Livro>> criarGrafoDistancias() {
        HashMap<Livro, Set<Livro>> grafoDistancias = new HashMap<>();
        for (Livro livro : listaDeLivros) {
            Set<Livro> vizinhos = new HashSet<>();
            for (Livro outroLivro : listaDeLivros) {
                if (!livro.equals(outroLivro)) {
                    if (livro.getGenero().equals(outroLivro.getGenero())) {
                        vizinhos.add(outroLivro);
                    } else {
                        vizinhos.addAll(obterLivrosPorGenero(outroLivro.getGenero()));
                    }
                }
            }
            grafoDistancias.put(livro, vizinhos);
        }
        return grafoDistancias;
    }

    public List<Livro> getListaDeLivros() {
        return Collections.unmodifiableList(listaDeLivros);
    }
}