public class Paciente {

    private long cpf;
    private String nome;
    private String cartaoSUS;
    private TipoAtendimento tipoAtendimento;
    private int idade;

    public Paciente(long cpf, String nome, String cartaoSUS,
                    TipoAtendimento tipoAtendimento, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.cartaoSUS = cartaoSUS;
        this.tipoAtendimento = tipoAtendimento;
        this.idade = idade;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCartaoSUS() {
        return cartaoSUS;
    }

    public void setCartaoSUS(String cartaoSUS) {
        this.cartaoSUS = cartaoSUS;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "\nNome: " + nome +
                "\nCPF: " + cpf +
                "\nCartão SUS: " + cartaoSUS +
                "\nTipo de atendimento: " + tipoAtendimento +
                "\nIdade: " + idade + " anos";
    }
}