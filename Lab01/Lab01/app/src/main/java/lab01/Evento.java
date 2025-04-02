//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

/*
 * Evento.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab01;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Contém a estrutura de implementação de um Evento.
 * 
 * @author Gabriel Leite - 216180
 * @author Caio Rhoden - 214129
 * @author Rafael Feltrin - 276246
 */
public abstract class Evento implements FiltroEventos{
    private String nome;
    private Local local;
    private LocalDate data;
    private double precoIngresso;
    private List<Ingresso> ingressosVendidos;

    /**
     * Construtor da classe Evento
     * @param nome o nome do Evento
     * @param local o local associado ao Evento
     * @param data a data do Evento no formato "dd/MM/yyyy"
     * @param precoIngresso o preço do ingresso do Evento
     */
    public Evento(String nome, Local local, String data, double precoIngresso){
        this.nome = nome;
        this.local = local;
        this.data = converteStringParaData(data);
        this.precoIngresso = precoIngresso;
        this.ingressosVendidos = new ArrayList<>();
    }

    /**
     * Converte uma string no formato "dd/MM/yyyy" para um objeto LocalDate
     * @param data a data no formato "dd/MM/yyyy"
     * @return a data convertida
     */
    private LocalDate converteStringParaData(String data) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       
        return LocalDate.parse(data, formatador);
    }

    /**
     * Retorna o nome do Evento
     * @return o nome do Evento
     */
    public String getNome(){
        return nome;
    }

    /**
     * Altera o nome do Evento para `nome` 
     * @param nome o novo nome do Evento
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Retorna o preço do ingresso do Evento
     * @return o precoIngresso do Evento
     */
    public double getPrecoIngresso(){
        return precoIngresso;
    }

    /**
     * Altera o precoIngresso do Evento para `precoIngresso`
     * @param precoIngresso o novo precoIngresso do Evento
     */
    public void setPrecoIngresso(double precoIngresso){
        this.precoIngresso = precoIngresso;
    }

    /**
     * Retorna o local do Evento
     * @return o local do Evento
     */
    public Local getLocal() {
        return local;
    }

    /**
     * Altera o local do Evento para `local`
     * @param local o novo local do Evento
     */
    public void setLocal(Local local) {
        this.local = local;
    }

    /**
     * Retorna o nome do local do Evento
     * @return o nome do local do Evento
     */
    public String getLocalName() {
        return local.getNome();
    }

    /**
     * Retorna a capacidade do local do Evento
     * @return a capacidade do local do Evento
     */
    public int getCapacidade() {
        return local.getCapacidade();
    }

    /**
     * Retorna a data do Evento no formato utilizado no brasil "dd/MM/yyyy"
     * @return a data do Evento
     */
    public String getData() {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Altera a data do Evento para `data`
     * @param data a nova data do Evento no formato brasileiro "dd/MM/yyyy"
     */
    public void setData(String data) {
        this.data = converteStringParaData(data);
    }

    /**
     * Adiciona um ingresso ao Evento e associa ao usuário
     * @param ingresso o ingresso a ser adicionado
     * @param usuario o usuário que comprou o ingresso
     */
    public void adicionarIngresso(Ingresso ingresso, Usuario usuario) {
        this.ingressosVendidos.add(ingresso);
        usuario.adicionarIngresso(ingresso);
    }

    /**
     * Retorna a lista de ingressos vendidos do Evento
     * @return a lista de ingressos vendidos
     */
    public List<Ingresso> getIngressosVendidos() {
        return ingressosVendidos;
    }

    /**
     * Calcula o faturamento total do Evento com base nos ingressos vendidos
     * @return o faturamento total do Evento
     */
    public double calcularFaturamento(){
        double faturamentoTotal = 0.0;

        for(Ingresso ingresso : ingressosVendidos){
            faturamentoTotal += ingresso.getPreco();
        }

        return faturamentoTotal;
    }

    /**
     * Exibe os detalhes do Evento (implementação específica em subclasses)
     */
    abstract public void exibirDetalhes();
}
