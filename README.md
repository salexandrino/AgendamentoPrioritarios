🏥 Agendamento de Pacientes Prioritários — UBS (SUS)
Este repositório contém a implementação do sistema de agendamento de consultas do dia para uma Unidade Básica de Saúde (UBS) do SUS. O sistema gerencia a fila de recepção dos pacientes a partir das 7h, priorizando o atendimento de acordo com a idade do paciente (do mais velho para o mais novo) por meio de uma estrutura de dados de Max Heap.

📌 Sumário
Sobre o Projeto

Estrutura de Dados (Heap Max)

Atributos do Paciente

Funcionalidades Principais


🩺 Sobre o Projeto
Em uma UBS, a ordem de chegada precisa ser combinada com critérios de priorização para garantir atendimento rápido aos pacientes com maior idade. Neste exercício prático, utilizamos uma Fila de Prioridade baseada em Heap para garantir que a inserção, a consulta do próximo a ser atendido e a remoção do paciente prioritário ocorram de forma eficiente.

Nota: Conforme os requisitos, a estrutura de dados utilizada foi alterada em relação aos exercícios anteriores para empregar exclusivamente Heap.

🛠️ Estrutura de Dados (Heap Max)
A agenda de atendimentos do dia é modelada através de um Max Heap, onde:

A chave de ordenação da prioridade é o atributo idade.

O paciente com a maior idade permanece sempre na raiz do Heap.

👤 Atributos do Paciente
Cada registro de paciente contém os seguintes dados:

cpf (Inteiro de 64 bits / Long): Documento do paciente (ex: 12345678900).

nome_completo (Texto): Nome completo do paciente.

cartao_sus (Texto): Número do Cartão Nacional de Saúde.

tipo_atendimento (Texto): Tipo do atendimento (ex: "Triagem", "Vacinação", "Consulta Agendada").

idade (Inteiro): Idade do paciente (utilizada na priorização).

⚙️ Funcionalidades Principais
cadastrar_atendimento_dia(heap, cpf, nome, cartao_sus, tipo_atendimento, idade)

[Inserção]: Insere um novo paciente na agenda de atendimentos do dia (Heap), reorganizando a estrutura de acordo com a idade.

remover_paciente_prioritario(heap)

[Remoção]: Remove e retorna o paciente com a maior idade (raiz) após ser chamado para o consultório.

quem_eh_o_proximo(heap)

[Consulta]: Retorna o paciente prioritário do Heap sem removê-lo da fila.
