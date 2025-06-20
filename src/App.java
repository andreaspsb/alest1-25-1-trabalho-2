import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import estruturas.ArvoreBinariaPesquisa;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.rodarAplicacao();
    }

    public void rodarAplicacao() {

        int[] numeros = new int[200];
        int qtdElementos = carregarArquivo("src/arquivos/entradas.txt", numeros);

        ArvoreBinariaPesquisa<Integer> abp = new ArvoreBinariaPesquisa<Integer>();
        for (int i = 0; i < qtdElementos - 1; i++) {
            abp.inserir(numeros[i]);
        }

        Integer[] elementos = abp.elementosPreOrdem();
        gravarArquivo("src/arquivos/preordem.txt", elementos);

        elementos = abp.elementosPosOrdem();
        gravarArquivo("src/arquivos/posordem.txt", elementos);

        elementos = abp.elementosCentralOrdem();
        gravarArquivo("src/arquivos/central.txt", elementos);

        elementos = abp.elementosLarguraOrdem();
        gravarArquivo("src/arquivos/largura.txt", elementos);

        int chavePesquisa = numeros[qtdElementos - 1];
        elementos = abp.obterChavesAteChavePesquisa(chavePesquisa);
        String arquivoResultado = "src/arquivos/resultado.txt";
        
        if (elementos != null) {
            gravarArquivo(arquivoResultado, elementos);
            gravarArquivoAux(arquivoResultado, String.format("Achou apos visitar %d nodos", elementos.length), true);            
        } else {
            gravarArquivoAux(arquivoResultado, "Não achou", false);            
        }

    }

    private static int carregarArquivo(String fileName, int[] numeros) {
        int index = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null && index < numeros.length) {
                numeros[index++] = Integer.parseInt(line);
            }
            System.out.println("Arquivo carregado com sucesso!");            
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return index++;
    }

    private static void gravarArquivo(String fileName, Integer[] numeros) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Integer numero : numeros) {
                bw.write(String.valueOf(numero));
                bw.newLine();
            }
            System.out.println("Arquivo gravado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gravar o arquivo: " + e.getMessage());
        }
    }

    private static void gravarArquivoAux(String fileName, String mensagem, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, append))) {
            bw.write(mensagem);
            bw.newLine();
            System.out.println("Arquivo gravado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gravar o arquivo: " + e.getMessage());
        }
    }

}
