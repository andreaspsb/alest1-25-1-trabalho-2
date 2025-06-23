# Trabalho 2 - Árvore Binária de Pesquisa

Este projeto é uma implementação em Java de uma Árvore Binária de Pesquisa (Binary Search Tree - BST), desenvolvida para a disciplina de Algoritmos e Estruturas de Dados 1.

O programa lê uma sequência de números inteiros de um arquivo, constrói uma BST com eles e executa os principais algoritmos de caminhamento (pré-ordem, em-ordem, pós-ordem e em largura). Também realiza a busca por uma chave específica, registrando o caminho percorrido. Os resultados de todas as operações são salvos em arquivos de texto separados.

## Funcionalidades

- **Construção da Árvore a partir de Arquivo:** Lê um arquivo `entrada.txt`, onde cada linha (exceto a última) contém uma chave inteira a ser inserida na árvore.
- **Caminhamentos (Traversals):** Gera arquivos de saída com a sequência de nós visitados para cada tipo de caminhamento:
    - `preordem.txt` (pré-ordem)
    - `central.txt` (em-ordem)
    - `posordem.txt` (pós-ordem)
    - `largura.txt` (em nível)
- **Pesquisa de Chave:**
    - Busca por uma chave especificada na última linha do arquivo de entrada.
    - Gera o arquivo `resultado.txt` que detalha o processo de busca:
        1. Lista das chaves dos nós visitados, uma por linha.
        2. Na penúltima linha, o número total de nós visitados.
        3. Na última linha, o resultado: "Achou" ou "Não Achou".

## Estrutura do Projeto

- `App.java`: Classe principal que gerencia a leitura do arquivo, a construção da árvore e a geração dos arquivos de saída.
- `ArvoreBinariaPesquisa.java`: Implementa a estrutura de dados da Árvore Binária de Pesquisa e seus métodos (inserção, busca, caminhamentos).
- `No.java`: Define a estrutura de um nó da árvore.
- `entrada.txt`: Arquivo de entrada de exemplo.

## Como Executar

### Pré-requisitos

- Java Development Kit (JDK) 8 ou superior.

### Passos para Execução

1. **Clone o repositório:**
    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    cd <NOME_DO_DIRETORIO_CLONADO>
    ```

2. **Prepare o arquivo de entrada:**
    - Crie um arquivo chamado `entrada.txt` na raiz do projeto.
    - Adicione as chaves inteiras a serem inseridas na árvore, uma por linha.
    - Na última linha do arquivo, insira a chave que deseja pesquisar.

    **Exemplo de `entrada.txt`:**
    ```text
    50
    30
    70
    20
    40
    60
    80
    35
    40
    ```

3. **Compile os arquivos Java:**
    ```bash
    javac *.java
    ```

4. **Execute a aplicação:**
    ```bash
    java App
    ```

5. **Verifique a saída:**
    Após a execução, os seguintes arquivos serão criados ou atualizados na raiz do projeto:
    - `preordem.txt`: Caminhamento pré-ordem.
    - `central.txt`: Caminhamento em-ordem.
    - `posordem.txt`: Caminhamento pós-ordem.
    - `largura.txt`: Caminhamento em largura (nível).
    - `resultado.txt`: Detalhamento da busca pela chave.

    Caso deseje executar novamente, apague os arquivos de saída para evitar sobreposição de dados.

## Exemplo de Saída

Para o `entrada.txt` de exemplo acima, a chave a ser buscada é `40`.

- **`central.txt` (em-ordem):**
  ```text
  20 30 35 40 50 60 70 80
  ```

- **`resultado.txt` (busca pela chave 40):**
  ```text
  50
  30
  40
  3
  Achou
  ```

## Observações

- Certifique-se de que o arquivo `entrada.txt` está no mesmo diretório dos arquivos `.java`.
- Os arquivos de saída serão sobrescritos a cada execução.
- O projeto pode ser facilmente adaptado para outros tipos de dados ou operações na árvore.
