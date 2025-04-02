//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab01;

/**
 * Representa um evento do tipo teatro, com informações adicionais como duração, gênero e faixa etária.
 * 
 * @author Rafael Feltrin - 276246
 */
public class EventoTeatro extends Evento {
    private int duracao;
    private String genero;
    private String faixaEtaria;

    /**
     * Construtor da classe EventoTeatro.
     * 
     * @param nome           Nome do evento.
     * @param local          Local onde o evento será realizado.
     * @param data           Data do evento.
     * @param precoIngresso  Preço do ingresso do evento.
     * @param duracao        Duração do evento em minutos.
     * @param genero         Gênero do teatro.
     * @param faixaEtaria    Faixa etária recomendada para o evento.
     */
    public EventoTeatro(String nome, Local local, String data, double precoIngresso, int duracao, String genero, String faixaEtaria) {
        super(nome, local, data, precoIngresso);
        this.duracao = duracao;
        this.genero = genero;
        this.faixaEtaria = faixaEtaria;
    }

    /**
     * Obtém a duração do evento em minutos.
     * 
     * @return Duração do evento.
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * Define a duração do evento em minutos.
     * 
     * @param duracao Duração do evento.
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * Obtém o gênero do teatro.
     * 
     * @return Gênero do teatro.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Define o gênero do teatro.
     * 
     * @param generoTeatro Gênero do teatro.
     */
    public void setGenero(String generoTeatro) {
        this.genero = generoTeatro;
    }

    /**
     * Obtém a faixa etária recomendada para o evento.
     * 
     * @return Faixa etária recomendada.
     */
    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    /**
     * Define a faixa etária recomendada para o evento.
     * 
     * @param faixaEtaria Faixa etária recomendada.
     */
    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    /**
     * Exibe os detalhes do teatro, incluindo informações como nome, local, capacidade,
     * data, duração, gênero, faixa etária, preço do ingresso e faturamento total.
     */
    public void exibirDetalhes() {
        System.out.println("Nome: " + getNome());
        System.out.println("Local: " + getLocal().getNome());
        System.out.println("Capacidade: " + getLocal().getCapacidade());
        System.out.println("Data: " + getData());
        System.out.println("Duração: " + duracao + " minutos");
        System.out.println("Gênero: " + genero);
        System.out.println("Faixa etária: " + faixaEtaria);
        System.out.println("Preço do Ingresso: R$" + getPrecoIngresso());
        System.out.println("Faturamento total: R$" + calcularFaturamento());
    }

    /**
     * Filtra eventos do tipo teatro com base no gênero.
     * 
     * @param evento Evento a ser comparado.
     * @return true se o gênero do evento for igual ao gênero deste teatro, false caso contrário.
     */
    public boolean filtrar(Evento evento) {
        if (evento instanceof EventoTeatro) {
            EventoTeatro eventoTeatro = (EventoTeatro) evento;
            return this.genero.equals(eventoTeatro.getGenero());
        }
        return false;
    }
}
