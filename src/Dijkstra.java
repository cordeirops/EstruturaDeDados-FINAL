import java.util.*;

public class Dijkstra {
    public static Map<Livro, Integer> djikstraPonderado(HashMap<Livro, Set<Livro>> grafo, Livro origem) {
        Map<Livro, Integer> distancias = new HashMap<>();
        Queue<Livro> fila = new LinkedList<>();

        distancias.put(origem, 0);
        fila.add(origem);

        while (!fila.isEmpty()) {
            Livro atual = fila.poll();
            int distanciaAtual = distancias.get(atual);

            for (Livro vizinho : grafo.getOrDefault(atual, new HashSet<>())) {
                int peso = calcularPeso(atual, vizinho);
                int novaDistancia = distanciaAtual + peso;

                if (!distancias.containsKey(vizinho) || novaDistancia < distancias.get(vizinho)) {
                    distancias.put(vizinho, novaDistancia);
                    fila.add(vizinho);
                }
            }
        }

        return distancias;
    }

    private static int calcularPeso(Livro origem, Livro destino) {
        if (origem.getGenero().equals(destino.getGenero())) {
            // Livros do mesmo gênero têm peso 1
            return 1;
        } else {
            // Livros de gêneros diferentes têm peso 2
            return 2;
        }
    }
}
