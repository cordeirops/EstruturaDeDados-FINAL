import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

record Genero(String nome) {
}
public class GeneroLivro {
    private static final Set<Genero> GENEROS_VALIDOS = new HashSet<>(Arrays.asList(
            new Genero("Romance"), new Genero("Ficção Científica"), new Genero("Fantasia"),
            new Genero("Aventura"), new Genero("História"), new Genero("Biografia")));

    public static boolean isValidGenero(Genero genero) {
        return GENEROS_VALIDOS.contains(genero);
    }

    public static void printGeneros() {
        int indice = 1;
        for (Genero genero : GENEROS_VALIDOS) {
            System.out.println(indice + ". " + genero.nome().toUpperCase());
            indice++;
        }
    }

    public static Genero obterGeneroPorIndice(int indice) {
        switch (indice) {
            case 1:
                return new Genero("Aventura");
            case 2:
                return new Genero("Romance");
            case 3:
                return new Genero("História");
            case 4:
                return new Genero("Biografia");
            case 5:
                return new Genero("Ficcção Científica");
            case 6:
                return new Genero("Fantasia");
            default:
                return null;
        }
    }
}
