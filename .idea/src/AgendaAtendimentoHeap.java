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

        while (i > 0 && heap[pai(i)].getIdade() < heap[i].getIdade()) {
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
        while (!ehFolha(i, cauda) && indiceValido(i, cauda)) {

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

    private int indiceMaior(Paciente[] heap, int i, int esquerda,
                            int direita, int cauda) {

        if (heap[i].getIdade() > heap[esquerda].getIdade()) {

            if (indiceValido(direita, cauda)
                    && heap[i].getIdade() < heap[direita].getIdade()) {
                return direita;
            }

            return i;
        }

        if (indiceValido(direita, cauda)
                && heap[esquerda].getIdade() < heap[direita].getIdade()) {
            return direita;
        }

        return esquerda;
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