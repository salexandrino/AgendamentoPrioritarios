# 🏥 Agendamento de Pacientes Prioritários — UBS (SUS)

O **Sistema de Agendamento da Unidade Básica de Saúde (UBS)** é uma solução desenvolvida para gerenciar a recepção de pacientes a partir das 07:00h. O projeto implementa uma **Fila de Prioridade** utilizando a estrutura de dados **Max Heap**, garantindo que pacientes com maior idade tenham preferência no atendimento médico.

---

## 📌 Sumário
- [Sobre o Projeto](#-sobre-o-projeto)
- [Estrutura de Dados](#-estrutura-de-dados)
- [Modelagem do Paciente](#-modelagem-do-paciente)
- [Operações Principais](#-operações-principais)
- [Roteiro de Testes Executado](#-roteiro-de-testes-executado)
- [Requisitos do Vídeo de Demonstração](#-requisitos-do-vídeo-de-demonstração)

---

## 🩺 Sobre o Projeto

Em uma Unidade Básica de Saúde do SUS, a organização da fila diária exige a combinação da ordem de chegada com critérios legais e operacionais de prioridade. 

Nesta atividade prática, a recepção cadastra o paciente informando seus dados básicos e idade. A agenda de consultas do dia é estruturada exclusivamente em **Heap**, otimizando o tempo de inserção, consulta e chamada do próximo paciente.

> ⚠️ **Nota:** A estrutura de dados foi totalmente reformulada em relação aos exercícios anteriores para empregar **exclusivamente Heap**, sem reutilizar estruturas de Árvore Binária de Busca (BST) ou listas simples.

---

## 🛠️ Estrutura de Dados

A agenda de atendimentos é gerenciada por um **Max Heap**, onde:
* **Chave de Prioridade:** O campo `idade`.
* **Regra do Heap:** O paciente de maior idade é sempre mantido na raiz da árvore/vetor, garantindo complexidade $O(1)$ na consulta do próximo atendido e $O(\log n)$ nas operações de inserção e remoção.

---

## 👤 Modelagem do Paciente

Cada nó/elemento armazenado no Heap representa um paciente com os seguintes atributos:

| Atributo | Tipo de Dado | Descrição |
| :--- | :--- | :--- |
| `cpf` | Inteiro (64 bits / Long) | Documento do paciente (Chave numérica, ex: `12345678900`) |
| `nome_completo` | Texto (String) | Nome completo do paciente |
| `cartao_sus` | Texto (String) | Número do Cartão Nacional de Saúde |
| `tipo_atendimento` | Texto (String) | Categoria do serviço (ex: *"Triagem"*, *"Vacinação"*, *"Consulta Agendada"*) |
| `idade` | Inteiro | Idade do paciente (Utilizada para definir a prioridade no Heap) |

---

## ⚙️ Operações Principais

O sistema disponibiliza as seguintes funções para manipulação do Heap:

* `cadastrar_atendimento_dia(heap, cpf, nome, cartao_sus, tipo_atendimento, idade)`
  * **[Inserção]**: Insere um novo paciente no Heap e executa a subida (*heapify up*) para posicionar o paciente conforme a sua idade.
* `remover_paciente_prioritario(heap)`
  * **[Remoção]**: Remove e retorna o paciente do topo do Heap (maior idade), reorganizando a estrutura via descida (*heapify down*).
* `quem_eh_o_proximo(heap)`
  * **[Consulta]**: Retorna os dados do paciente prioritário (raiz do Heap) sem removê-lo da agenda.

---

## 🧪 Roteiro de Testes Executado

A simulação reproduz o fluxo contínuo da recepção para uma sequência de **5 pacientes**:

1. **Recepção e Cadastro:** O paciente chega, seu CPF é coletado e seus dados são inseridos na agenda (`cadastrar_atendimento_dia`).
2. **Consulta da Raiz:** O sistema verifica quem é o próximo paciente da fila (`quem_eh_o_proximo`).
3. **Chamada ao Consultório:** O paciente prioritário é chamado e retirado da agenda do dia (`remover_paciente_prioritario`).

---

## 🎥 Vídeo de Demonstração

Conforme as orientações do exercício, o vídeo gravado atende aos seguintes parâmetros:
* **Identificação Inicial:** Apresentação contendo nome do estudante, disciplina e semestre letivo.
* **Abordagem:** Teste caixa-preta focado na perspectiva do usuário (demonstração de entradas e saídas).
* **Formato:** Narrado por áudio (sem necessidade de câmera) com duração máxima de **3 minutos**.
