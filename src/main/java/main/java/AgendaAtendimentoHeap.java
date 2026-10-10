
package main.java;

public class AgendaAtendimentoHeap {

    private Paciente[] heap;
    private int cauda;

    public AgendaAtendimentoHeap() {
        heap = new Paciente[10];
        cauda = -1;
    }

    public void cadastrarAtendimentoDia(Paciente elemento) {
        if (cauda >= heap.length - 1) {
            redimensionar();
        }

        cauda = cauda + 1;
        heap[cauda] = elemento;

        int i = cauda;

        while (i > 0 &&
                heap[pai(i)].getIdade() < heap[i].getIdade()) {
            trocar(heap, i, pai(i));
            i = pai(i);
        }
    }

    public Paciente quemEhOProximo() {
        if (estaVazia()) {
            return null;
        }

        return heap[0];
    }

    public Paciente removerPacientePrioritario() {
        if (estaVazia()) {
            return null;
        }

        Paciente elemento = heap[0];

        heap[0] = heap[cauda];
        heap[cauda] = null;
        cauda = cauda - 1;

        heapify(heap, 0, cauda);

        return elemento;
    }

    private void redimensionar() {
        Paciente[] novoHeap = new Paciente[heap.length * 2];

        for (int i = 0; i < heap.length; i++) {
            novoHeap[i] = heap[i];
        }

        heap = novoHeap;
    }

    private void trocar(Paciente[] heap, int i, int j) {
        Paciente auxiliar = heap[i];
        heap[i] = heap[j];
        heap[j] = auxiliar;
    }

    private void heapify(Paciente[] heap, int i, int cauda) {
        while (!ehFolha(i, cauda) &&
                indiceValido(i, cauda)) {

            int iMax = indiceMaior(
                    heap,
                    i,
                    esquerda(i),
                    direita(i),
                    cauda
            );

            if (iMax != i) {
                trocar(heap, i, iMax);
                i = iMax;
            } else {
                break;
            }
        }
    }

    private int indiceMaior(
            Paciente[] heap,
            int i,
            int esquerda,
            int direita,
            int cauda) {

        int maior = i;

        if (indiceValido(esquerda, cauda)
                && heap[esquerda].getIdade()
                > heap[maior].getIdade()) {
            maior = esquerda;
        }

        if (indiceValido(direita, cauda)
                && heap[direita].getIdade()
                > heap[maior].getIdade()) {
            maior = direita;
        }

        return maior;
    }

    // Clona a agenda e aplica Heap Sort.
    // O vetor resultante fica em ordem crescente.
    // A impressão decrescente é realizada no Main.
    public Paciente[] obterPacientesPorIdadeDecrescente() {

        Paciente[] copia = new Paciente[cauda + 1];

        for (int i = 0; i <= cauda; i++) {
            copia[i] = heap[i];
        }

        int n = copia.length;

        // Constrói o Max-Heap na cópia.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyOrdenacao(copia, n, i);
        }

        // Aplica o Heap Sort.
        for (int fim = n - 1; fim > 0; fim--) {
            Paciente auxiliar = copia[0];
            copia[0] = copia[fim];
            copia[fim] = auxiliar;

            heapifyOrdenacao(copia, fim, 0);
        }

        return copia;
    }

    private void heapifyOrdenacao(
            Paciente[] vetor,
            int tamanho,
            int raiz) {

        int maior = raiz;
        int esquerda = 2 * raiz + 1;
        int direita = 2 * raiz + 2;

        if (esquerda < tamanho
                && vetor[esquerda].getIdade()
                > vetor[maior].getIdade()) {
            maior = esquerda;
        }

        if (direita < tamanho
                && vetor[direita].getIdade()
                > vetor[maior].getIdade()) {
            maior = direita;
        }

        if (maior != raiz) {
            Paciente auxiliar = vetor[raiz];
            vetor[raiz] = vetor[maior];
            vetor[maior] = auxiliar;

            heapifyOrdenacao(vetor, tamanho, maior);
        }
    }

    // Conta pacientes com idade estritamente maior.
    // Retorna -1 quando o CPF não está na agenda.
    public int falta_quantos(long cpf) {

        Paciente pacienteProcurado = null;

        for (int i = 0; i <= cauda; i++) {
            if (heap[i].getCpf() == cpf) {
                pacienteProcurado = heap[i];
                break;
            }
        }

        if (pacienteProcurado == null) {
            return -1;
        }

        int faltam = 0;
        int idadeProcurada = pacienteProcurado.getIdade();

        for (int i = 0; i <= cauda; i++) {
            if (heap[i].getCpf() != cpf
                    && heap[i].getIdade() > idadeProcurada) {
                faltam++;
            }
        }

        return faltam;
    }

    private int pai(int i) {
        return (i - 1) / 2;
    }

    private int esquerda(int i) {
        return 2 * i + 1;
    }

    private int direita(int i) {
        return 2 * i + 2;
    }

    private boolean indiceValido(int i, int cauda) {
        return i >= 0 && i <= cauda;
    }

    private boolean ehFolha(int i, int cauda) {
        return i > pai(cauda) && i <= cauda;
    }

    private boolean estaVazia() {
        return cauda == -1;
    }
}
