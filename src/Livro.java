import java.util.*;

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
    private final Map<Livro, Set<Livro>> recomendacoes = new HashMap<>();
    private final List<Livro> listaDeLivros = new ArrayList<>();

    public LivroGrafo() {
        // Inicialização da lista de livros
        listaDeLivros.add(new Livro("A Culpa é das Estrelas", "John Green", 170, new Genero("Romance"), this));
        listaDeLivros.add(new Livro("Harry Potter e o Enigma do Príncipe", "J.K. Rowling", 450, new Genero("Aventura"), this));
        listaDeLivros.add(new Livro("O Silmarillion", "J. R. R. Tolkien", 560, new Genero("Fantasia"), this));
        listaDeLivros.add(new Livro("1984", "George Orwell", 316, new Genero("Ficção Científica"), this));
        listaDeLivros.add(new Livro("Sapiens - Uma Breve História da Humanidade", "Yuval Harari", 362, new Genero("História"), this));
        listaDeLivros.add(new Livro("Silvio Santos: a biografia definitiva", "Anna Medeiros", 332, new Genero("Biografia"), this));

        // Atribui recomendações
        atribuirRecomendacoes();
    }

    public void cadastrarLivro(Scanner leitor) {
        System.out.println("*************************************");
        System.out.print("Digite o título do livro: ");
        String titulo = leitor.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = leitor.nextLine();
        System.out.print("Digite a quantidade de páginas do livro: ");
        int paginas = leitor.nextInt();
        System.out.println("Escolha o gênero do livro:");
        GeneroLivro.printGeneros();
        int generoEscolhido = leitor.nextInt();
        Genero genero = GeneroLivro.obterGeneroPorIndice(generoEscolhido);
        Livro livro = new Livro(titulo, autor, paginas, genero, this);
        listaDeLivros.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
        // Atribui recomendações
        atribuirRecomendacoes();
        System.out.println("*************************************");
        System.out.println();
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

    private void addLivro(Livro livro) {
        recomendacoes.put(livro, new HashSet<>());
    }

    private void addRecomendacao(Livro livro, Livro recomendado) {
        if (!recomendacoes.containsKey(livro)) {
            recomendacoes.put(livro, new HashSet<>());
        }
        recomendacoes.get(livro).add(recomendado);
    }

    public void imprimeRecomendacoes() {
        for (Map.Entry<Livro, Set<Livro>> entry : recomendacoes.entrySet()) {
            Livro livro = entry.getKey();
            Set<Livro> recomendacoes = entry.getValue();

            System.out.println("****** Para você que leu '" + livro.getTitulo() + "', também pode gostar de : ******");
            for (Livro recomendado : recomendacoes) {
                System.out.println(" - " + recomendado.getTitulo());
            }
            System.out.println();
        }
    }

    public List<Livro> getListaDeLivros() {
        return Collections.unmodifiableList(listaDeLivros);
    }
}

