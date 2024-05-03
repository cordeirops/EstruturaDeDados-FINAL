# Sistema de Recomendação de Livros

Este é um sistema simples de recomendação de livros implementado em Java. Ele permite cadastrar novos livros e obter recomendações com base no gênero dos livros cadastrados.

***
## Classes:

### Livro

Representa um livro com atributos como título, autor, número de páginas e gênero.

### LivroGrafo

Responsável por gerenciar a lista de livros e suas recomendações. Ele também permite cadastrar novos livros e atribuir recomendações com base nos gêneros dos livros.

### Genero

Uma classe `record` que representa o gênero de um livro.

### GeneroLivro

Fornece métodos para validar gêneros de livros e obter gêneros com base em índices.

### Dijkstra

Implementa o algoritmo de Dijkstra para calcular as distâncias entre os livros com base nos gêneros.
- Peso 1: livros do mesmo gênero;
- Peso 2: livros de gêneros relacionados/recomendados;
- Demais pesos: gêneros que podem ser experimentados pelo leitor.

### Main

O ponto de entrada do programa, onde o usuário interage com o sistema através de um menu.

***

## Funcionalidades

1. **Cadastrar novo livro:** Permite ao usuário adicionar um novo livro ao sistema informando título, autor, número de páginas e gênero.
2. **Imprimir recomendações:** Exibe as recomendações de livros com base nos gêneros dos livros cadastrados.
3. **Mostrar distâncias a partir de um livro (Dijkstra):** Calcula as distâncias entre os livros com base nos gêneros e exibe as informações.
9. **Sair:** Encerra o programa.

## Como usar

1. Compile todas as classes Java.
2. Execute a classe `Main`.
3. Siga as instruções do menu para cadastrar livros, obter recomendações ou calcular distâncias.
4. O programa pode ser executado pelo arquivo .jar na localizado na pasta OUT/Artifacts/arquivo.jar 
(CMD >> java -jar nomedoarquivo.jar)
* Versão JDK: 21.0.2
***

### Observações
O sistema possui uma lista de livros pré-cadastradas em seu código, dessa maneira, os novos livros cadastrados
recebem recomendações dessa lista de livros preenchida anteriormente. Assim, o sistema sempre terá livros para recomendar.

***

## Autor
Este sistema foi desenvolvido por *Pedro Sbaraini Cordeiro*.

Você pode encontrar o código-fonte deste projeto no [GitHub](https://github.com/cordeirops).

