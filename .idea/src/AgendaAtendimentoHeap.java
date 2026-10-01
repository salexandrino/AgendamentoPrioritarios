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
            trocar(i, pai(i));
            i = pai(i);
        }
    }

    public Paciente removerPacientePrioritario() {
        if (estaVazia()) {
            return null;
        }

        Paciente elemento = heap[0];

        heap[0] = heap[cauda];
        heap[cauda] = null;
        cauda = cauda - 1;

        heapify(0);

        return elemento;
    }
}

    public Paciente quemEhOProximo(){

    }

    public void redimenrsionar(){

    }
    public void trocar(){

    }

    public void heapfy(Paciente[] heap, int i ){
        while (!ehFolha(i, cauda)) &&

    }


    private int pai(int i) { }
    private int esquerda(int i) {  }
    private int direita(int i) {  }
    private boolean estaVazia() {  }




}