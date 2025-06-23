package estruturas;

import java.lang.reflect.Array;

public class ArvoreBinariaPesquisa<T extends Comparable<T>> {

    private class Nodo {
        private T chave;
        private Nodo filhoEsquerda;
        private Nodo filhoDireita;
        private Nodo pai;

        public Nodo(T chave) {
            this.chave = chave;
        }

        public void imprimirFilhos() {
            System.out.println();
            if (filhoEsquerda != null) {
                System.out.print(filhoEsquerda.chave + " ");
            }
            if (filhoDireita != null) {
                System.out.print(filhoDireita.chave);
            }
            System.out.println();
        }

        public int obterGrau() {
            int qtdFilhos = 0;

            if (filhoEsquerda != null) {
                qtdFilhos++;
            }
            if (filhoDireita != null) {
                qtdFilhos++;
            }

            return qtdFilhos;
        }
    }

    private Nodo raiz;
    private int tamanho;

    public ArvoreBinariaPesquisa(T valor) {
        raiz = new Nodo(valor);
        tamanho = 1;
    }

    public ArvoreBinariaPesquisa() {
        tamanho = 0;
    }

    public void inserir(T chave) {
        Nodo n = new Nodo(chave);
        if (raiz == null) {
            raiz = n;
            tamanho = 1;
            return;
        }
        this.inserir(chave, n, raiz);
    }

    private void inserir(T chave, Nodo n, Nodo pai) {
        if (chave.compareTo(pai.chave) < 0) {
            if (pai.filhoEsquerda == null) {
                pai.filhoEsquerda = n;
                n.pai = pai;
                tamanho++;
            } else {
                inserir(chave, n, pai.filhoEsquerda);
            }
        } else {
            if (pai.filhoDireita == null) {
                pai.filhoDireita = n;
                n.pai = pai;
                tamanho++;
            } else {
                inserir(chave, n, pai.filhoDireita);
            }
        }
    }

    public void imprimirFilhos(T pai) {
        Nodo nodoPai = obterNodo(pai);
        if (nodoPai == null) {
            System.out.println("Nodo não encontrado");
            return;
        }
        nodoPai.imprimirFilhos();
    }

    private Nodo obterNodo(T pai) {
        return buscarNodoRecursivo(raiz, pai);
    }

    private Nodo buscarNodoRecursivo(Nodo n, T chave) {
        if (n == null) {
            return null;
        }
        if (n.chave == chave) {
            return n;
        }

        Nodo encontrado;
        if (chave.compareTo(n.chave) < 0) {
            encontrado = buscarNodoRecursivo(n.filhoEsquerda, chave);
        } else {
            encontrado = buscarNodoRecursivo(n.filhoDireita, chave);
        }

        return encontrado;
    }

    public T obterPai(T chave) {
        Nodo nodo = obterNodo(chave);
        if (nodo == null) {
            System.out.println("Nodo não encontrado");
            return null;
        }
        if (nodo.pai == null) {
            System.out.println("Nodo raiz");
            return null;
        }
        return nodo.pai.chave;
    }

    public int obterTamanho() {
        return this.tamanho;
    }

    public void limpar() {
        raiz = null;
        tamanho = 0;
    }

    public int obterGrau(T chave) {
        Nodo n = obterNodo(chave);
        if (n == null) {
            return -1;
        }

        return n.obterGrau();
    }

    public void remover(T chave) {
        Nodo n = obterNodo(chave);
        if (n == null) {
            return;
        }

        Nodo pai = n.pai;

        int grau = n.obterGrau();
        if (grau == 0) {
            if (obterTamanho() == 1) {
                limpar();
                return;
            }
            if (pai.filhoEsquerda != null && pai.filhoEsquerda.chave == chave) {
                pai.filhoEsquerda = null;
            } else {
                pai.filhoDireita = null;
            }
        } else if (grau == 1) {
            Nodo filho;
            if (n.filhoEsquerda != null) {
                filho = n.filhoEsquerda;
            } else {
                filho = n.filhoDireita;
            }
            filho.pai = pai;
            if (pai != null) {
                if (pai.filhoEsquerda != null && pai.filhoEsquerda.chave == chave) {
                    pai.filhoEsquerda = filho;
                } else {
                    pai.filhoDireita = filho;
                }
            } else {
                raiz = filho;
            }
        } else {
            Nodo[] elementosArvoreDireita = elementosCentralOrdem(n.filhoDireita);
            Nodo substituto = elementosArvoreDireita[0];

            substituto.pai.filhoEsquerda = null;

            substituto.filhoEsquerda = n.filhoEsquerda;

            if (substituto.chave != n.filhoDireita.chave) {
                substituto.filhoDireita = n.filhoDireita;
            }

            substituto.filhoEsquerda.pai = substituto;

            if (substituto.filhoDireita != null) {
                substituto.filhoDireita.pai = substituto;
            }

            substituto.pai = pai;
            if (pai != null) {
                if (pai.filhoEsquerda != null && pai.filhoEsquerda.chave == chave) {
                    pai.filhoEsquerda = substituto;
                } else {
                    pai.filhoDireita = substituto;
                }
            } else {
                raiz = substituto;
            }
        }

        tamanho--;
    }    
    
    // metodo privado usado pelo remover
    private Nodo[] elementosCentralOrdem(Nodo raiz) {
        if (tamanho == 0) {
            return null;
        }
        
        Nodo[] elementos = (Nodo[]) new Object[tamanho];
        Integer pos = 0;

        pos = elementosCentralOrdem(elementos, raiz, pos);               

        return elementos;
    }

    private Integer elementosCentralOrdem(Nodo[] elementos, Nodo n, Integer pos) {
        if (n == null) {
            return pos;
        }

        pos = elementosCentralOrdem(elementos, n.filhoEsquerda, pos);
        elementos[pos] = n;
        pos++;
        pos = elementosCentralOrdem(elementos, n.filhoDireita, pos);
        
        return pos;
    }

    public T[] elementosPosOrdem() {
        if (tamanho == 0) {
            return null;
        }

        @SuppressWarnings("unchecked")
        T[] elementos = (T[]) Array.newInstance(raiz.chave.getClass(), tamanho);
        Integer pos = 0;

        elementosPosOrdem(elementos, raiz, pos);

        return elementos;
    }

    private Integer elementosPosOrdem(T[] elementos, Nodo n, Integer pos) {
        if (n == null) {
            return pos;
        }

        pos = elementosPosOrdem(elementos, n.filhoEsquerda, pos);
        pos = elementosPosOrdem(elementos, n.filhoDireita, pos);

        elementos[pos] = n.chave;
        pos++;
        return pos;
    }    

    public T[] elementosPreOrdem() {
        if (tamanho == 0) {
            return null;
        }
        
        @SuppressWarnings("unchecked")
        T[] elementos = (T[]) Array.newInstance(raiz.chave.getClass(), tamanho);
        Integer pos = 0;

        elementosPreOrdem(elementos, raiz, pos);

        return elementos;
    }

    private Integer elementosPreOrdem(T[] elementos, Nodo n, Integer pos) {
        if (n == null) {
            return pos;
        }

        elementos[pos] = n.chave;
        pos++;
        pos = elementosPreOrdem(elementos, n.filhoEsquerda, pos);        
        pos = elementosPreOrdem(elementos, n.filhoDireita, pos);
        
        return pos;
    }

    public T[] elementosCentralOrdem() {
        if (tamanho == 0) {
            return null;
        }        

        @SuppressWarnings("unchecked")
        T[] elementos = (T[]) Array.newInstance(raiz.chave.getClass(), tamanho);
        Integer pos = 0;

        elementosCentralOrdem(elementos, raiz, pos);                

        return elementos;
    }

    private Integer elementosCentralOrdem(T[] elementos, Nodo n, Integer pos) {
        if (n == null) {
            return pos;
        }

        pos = elementosCentralOrdem(elementos, n.filhoEsquerda, pos);
        elementos[pos] = n.chave;
        pos++;
        pos = elementosCentralOrdem(elementos, n.filhoDireita, pos);
        
        return pos;
    }

    public T[] elementosLarguraOrdem() {
        if (tamanho == 0) {
            return null;
        }

        @SuppressWarnings("unchecked")
        T[] elementos = (T[]) Array.newInstance(raiz.chave.getClass(), tamanho);
        Integer pos = 0;

        FilaEstatica<Nodo> fila = new FilaEstatica<Nodo>();

        fila.enfileirar(raiz);

        while (fila.obterTamanho() != 0) {
            Nodo n = fila.desenfileirar();
            elementos[pos] = n.chave;
            pos++;
            if (n.filhoEsquerda != null) {
                fila.enfileirar(n.filhoEsquerda);
            }
            if (n.filhoDireita != null) {
                fila.enfileirar(n.filhoDireita);
            }
        }                

        return elementos;
    }

    public boolean existe(T item) {
        if(this.obterNodo(item) != null) {
            return true;
        }
        return false;
    }

    public int obterAltura() {
        if (raiz == null) {
            return -1;
        }

        int esquerda = obterAltura(raiz.filhoEsquerda);
        int direita = obterAltura(raiz.filhoDireita);

        return 1 + Math.max(esquerda, direita);
    }

    private int obterAltura(Nodo n) {
        if (n == null) {
            return -1;
        }

        int esquerda = obterAltura(n.filhoEsquerda);
        int direita = obterAltura(n.filhoDireita);

        return 1 + Math.max(esquerda, direita);

    }

    public int obterNivel(T item) {
        return obterNivel(raiz, item, 0);
    }

    private int obterNivel(Nodo n, T chave, int nivelAtual) {
        if (n == null) {
            return -1;
        }

        if (n.chave.equals(chave)) {
            return nivelAtual;
        }

        if (chave.compareTo(n.chave) < 0) {
            return obterNivel(n.filhoEsquerda, chave, nivelAtual + 1);
        } else {
            return obterNivel(n.filhoDireita, chave, nivelAtual + 1);
        }
    }

    public T[] obterChavesAteChavePesquisa(T chave) {
        
        @SuppressWarnings("unchecked")
        T[] elementos = (T[]) Array.newInstance(raiz.chave.getClass(), tamanho);
        Integer pos = 0;
        Nodo n = raiz;
        boolean achou = false;

        while (n != null) {
            elementos[pos] = n.chave;
            pos++;
            if (n.chave.equals(chave)) {
                achou = true;
                break;
            }
            if (chave.compareTo(n.chave) < 0) {
                n = n.filhoEsquerda;
            } else {
                n = n.filhoDireita;
            }
            
        }

        if (!achou) {
            return null;            
        }

        //retornar array sem os nulls        
        @SuppressWarnings("unchecked")
        T[] retorno = (T[]) Array.newInstance(raiz.chave.getClass(), pos);
        for (int i = 0; i < pos; i++) {
            retorno[i] = elementos[i];
        }

        return retorno;

    }    
    
}