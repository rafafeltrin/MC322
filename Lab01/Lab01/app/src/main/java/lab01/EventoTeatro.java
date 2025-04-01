package lab01;

public class EventoTeatro extends Evento {
    private int duracao;
    private String generoTeatro;
    private String faixaEtaria;

    public EventoTeatro(String nome, Local local, String data, double precoIngresso, int duracao, String generoTeatro, String faixaEtaria){
        super(nome, local, data, precoIngresso);
        this.duracao = duracao;
        this.generoTeatro = generoTeatro;
        this.faixaEtaria = faixaEtaria;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getGeneroTeatro() {
        return generoTeatro;
    }

    public void setGeneroTeatro(String generoTeatro) {
        this.generoTeatro = generoTeatro;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public void exibirDetalhes(){
        System.out.println("Nome: " + getNome());
        System.out.println("Local: " + getLocal().getNome());
        System.out.println("Capacidade: " + getLocal().getCapacidade());
        System.out.println("Data: " + getData());
        System.out.println("Duração: " + duracao + " minutos");
        System.out.println("Gênero: " + generoTeatro);
        System.out.println("Faixa etária: " + faixaEtaria);
    }
}
