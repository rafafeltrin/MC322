//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab01;

/**
 * Representa um evento esportivo, contendo informações específicas para esse tipo de evento
 * 
 * @author Rafael Feltrin - 276246
 */
public class EventoEsporte extends Evento {
    private String tipoEsporte;
    private String categoria;
    private String time1;
    private String time2;

    /**
     * Construtor completo para criar um evento esportivo com disputa de times.
     * 
     * @param nome Nome do evento.
     * @param local Local onde o evento será realizado.
     * @param data Data do evento.
     * @param precoIngresso Preço do ingresso.
     * @param tipoEsporte Tipo de esporte do evento.
     * @param categoria Categoria do evento esportivo.
     * @param time1 Nome do primeiro time.
     * @param time2 Nome do segundo time.
     */
    public EventoEsporte(String nome, Local local, String data, double precoIngresso, String tipoEsporte, String categoria, String time1, String time2) {
        super(nome, local, data, precoIngresso);
        this.tipoEsporte = tipoEsporte;
        this.categoria = categoria;
        this.time1 = time1;
        this.time2 = time2;
    }

    /**
     * Construtor para criar um evento esportivo sem especificar os times.
     * 
     * @param nome Nome do evento.
     * @param local Local onde o evento será realizado.
     * @param data Data do evento.
     * @param precoIngresso Preço do ingresso.
     * @param tipoEsporte Tipo de esporte do evento.
     * @param categoria Categoria do evento esportivo.
     */
    public EventoEsporte(String nome, Local local, String data, double precoIngresso, String tipoEsporte, String categoria) {
        super(nome, local, data, precoIngresso);
        this.tipoEsporte = tipoEsporte;
        this.categoria = categoria;
    }

    /**
     * Obtém o tipo de esporte do evento.
     * 
     * @return Tipo de esporte.
     */
    public String getTipoEsporte() {
        return tipoEsporte;
    }

    /**
     * Define o tipo de esporte do evento.
     * 
     * @param tipoEsporte Tipo de esporte.
     */
    public void setTipoEsporte(String tipoEsporte) {
        this.tipoEsporte = tipoEsporte;
    }

    /**
     * Obtém a categoria do evento esportivo.
     * 
     * @return Categoria do evento.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do evento esportivo.
     * 
     * @param categoria Categoria do evento.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtém o nome do primeiro time.
     * 
     * @return Nome do primeiro time.
     */
    public String getTime1() {
        if (time1 == null) {
            return "Time 1 não definido";
        }
        return time1;
    }

    /**
     * Define o nome do primeiro time.
     * 
     * @param time1 Nome do primeiro time.
     */
    public void setTime1(String time1) {
        this.time1 = time1;
    }

    /**
     * Obtém o nome do segundo time.
     * 
     * @return Nome do segundo time.
     */
    public String getTime2() {
        if (time2 == null) {
            return "Time 2 não definido";
        }
        return time2;
    }

    /**
     * Define o nome do segundo time.
     * 
     * @param time2 Nome do segundo time.
     */
    public void setTime2(String time2) {
        this.time2 = time2;
    }

    /**
     * Exibe os detalhes do evento esportivo, incluindo informações gerais e times, se disponíveis.
     */
    public void exibirDetalhes(){
        System.out.println("Nome: " + getNome());
        System.out.println("Local: " + getLocal().getNome());
        System.out.println("Capaciadade: " + getLocal().getCapacidade());
        System.out.println("Data: " + getData());
        System.out.println("Tipo de esporte: " + tipoEsporte);
        System.out.println("Categoria: " + categoria);

        if (time1 != null && time2 != null){
            System.out.println("Times: "+ time1 + " x " + time2);
        }
        System.out.println("Preço do ingresso: R$" + getPrecoIngresso());
        System.out.println("Faturamento total: R$" + calcularFaturamento());
    }

    /**
     * Filtra eventos esportivos com base no tipo de esporte.
     * 
     * @param evento Evento a ser comparado.
     * @return true se o tipo de esporte for igual, false caso contrário.
     */
    @Override
    public boolean filtrar(Evento evento) {
        if (evento instanceof EventoEsporte) {
            EventoEsporte eventoEsporte = (EventoEsporte) evento;
            return this.tipoEsporte.equals(eventoEsporte.getTipoEsporte());
        }
        return false;
    }
}
