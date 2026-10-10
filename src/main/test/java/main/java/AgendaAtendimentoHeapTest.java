
package main.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgendaAtendimentoHeapTest {

    private AgendaAtendimentoHeap agenda;

    @BeforeEach
    void preparar() {
        agenda = new AgendaAtendimentoHeap();
    }

    private Paciente criarPaciente(
            long cpf, String nome, int idade) {

        return new Paciente(
                cpf,
                nome,
                "SUS-" + cpf,
                TipoAtendimento.TRIAGEM,
                idade
        );
    }

    @Test
    void agendaVaziaNaoTemProximoNemRemocao() {
        assertNull(agenda.quemEhOProximo());
        assertNull(agenda.removerPacientePrioritario());
    }

    @Test
    void cadastraEConsultaMaiorIdade() {
        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "Joao", 20));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(2, "Maria", 60));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(3, "Pedro", 35));

        assertEquals(60,
                agenda.quemEhOProximo().getIdade());
    }

    @Test
    void consultaNaoRemovePaciente() {
        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "Maria", 60));

        assertSame(
                agenda.quemEhOProximo(),
                agenda.quemEhOProximo()
        );

        assertEquals(60,
                agenda.removerPacientePrioritario().getIdade());
    }

    @Test
    void removerReorganizaMaxHeap() {
        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "A", 10));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(2, "B", 62));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(3, "C", 35));

        assertEquals(62,
                agenda.removerPacientePrioritario().getIdade());

        assertEquals(35,
                agenda.quemEhOProximo().getIdade());
    }

    @Test
    void removerTodosEmOrdemDePrioridade() {
        for (int i = 0; i < 15; i++) {
            agenda.cadastrarAtendimentoDia(
                    criarPaciente(i + 1, "P" + i, i * 3));
        }

        for (int idade = 42; idade >= 0; idade -= 3) {
            assertEquals(
                    idade,
                    agenda.removerPacientePrioritario().getIdade()
            );
        }

        assertNull(agenda.removerPacientePrioritario());
    }

    @Test
    void removePacienteUnico() {
        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "A", 12));

        assertEquals(12,
                agenda.removerPacientePrioritario().getIdade());

        assertNull(agenda.quemEhOProximo());
    }

    @Test
    void removeComDoisPacientes() {
        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "A", 30));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(2, "B", 10));

        assertEquals(30,
                agenda.removerPacientePrioritario().getIdade());

        assertEquals(10,
                agenda.removerPacientePrioritario().getIdade());

        assertNull(agenda.quemEhOProximo());
    }

    @Test
    void heapSortRetornaVetorCrescente() {
        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "A", 10));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(2, "B", 62));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(3, "C", 35));

        Paciente[] ordenados =
                agenda.obterPacientesPorIdadeDecrescente();

        assertArrayEquals(
                new int[]{10, 35, 62},
                idades(ordenados)
        );
    }

    @Test
    void heapSortAgendaVazia() {
        assertEquals(
                0,
                agenda.obterPacientesPorIdadeDecrescente().length
        );
    }

    @Test
    void heapSortPacienteUnico() {
        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "A", 50));

        assertArrayEquals(
                new int[]{50},
                idades(agenda.obterPacientesPorIdadeDecrescente())
        );
    }

    @Test
    void heapSortAceitaIdadesRepetidas() {
        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "A", 45));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(2, "B", 20));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(3, "C", 45));

        assertArrayEquals(
                new int[]{20, 45, 45},
                idades(agenda.obterPacientesPorIdadeDecrescente())
        );
    }

    @Test
    void heapSortNaoModificaAgendaOriginal() {
        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "A", 20));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(2, "B", 60));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(3, "C", 35));

        Paciente prioritario = agenda.quemEhOProximo();

        agenda.obterPacientesPorIdadeDecrescente();

        assertSame(
                prioritario,
                agenda.quemEhOProximo()
        );

        assertEquals(60,
                agenda.removerPacientePrioritario().getIdade());

        assertEquals(35,
                agenda.removerPacientePrioritario().getIdade());

        assertEquals(20,
                agenda.removerPacientePrioritario().getIdade());
    }

    @Test
    void faltaQuantosContaIdadesEstritamenteMaiores() {
        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "Saymon", 10));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(2, "Neuza", 62));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(3, "Roberto", 35));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(4, "Joao", 45));

        assertEquals(0, agenda.falta_quantos(2));
        assertEquals(1, agenda.falta_quantos(4));
        assertEquals(2, agenda.falta_quantos(3));
        assertEquals(3, agenda.falta_quantos(1));
    }

    @Test
    void faltaQuantosPacienteInexistente() {
        assertEquals(-1, agenda.falta_quantos(999));

        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "A", 30));

        assertEquals(-1, agenda.falta_quantos(999));
    }

    @Test
    void faltaQuantosMesmaIdadeNaoContaComoMaior() {
        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "A", 40));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(2, "B", 40));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(3, "C", 55));

        assertEquals(1, agenda.falta_quantos(1));
        assertEquals(1, agenda.falta_quantos(2));
    }

    @Test
    void faltaQuantosAtualizaDepoisDoAtendimento() {
        agenda.cadastrarAtendimentoDia(
                criarPaciente(1, "A", 20));
        agenda.cadastrarAtendimentoDia(
                criarPaciente(2, "B", 60));

        assertEquals(1, agenda.falta_quantos(1));

        agenda.removerPacientePrioritario();

        assertEquals(0, agenda.falta_quantos(1));
        assertEquals(-1, agenda.falta_quantos(2));
    }

    private int[] idades(Paciente[] pacientes) {
        int[] resultado = new int[pacientes.length];

        for (int i = 0; i < pacientes.length; i++) {
            resultado[i] = pacientes[i].getIdade();
        }

        return resultado;
    }
}
