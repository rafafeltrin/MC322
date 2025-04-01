/*
 * Evento.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab01;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Contém a estrutura de implementação de um Evento.
 * 
 * @author Gabriel Leite - 216180
 * @author Caio Rhoden - 214129
 */
public abstract class Evento {
    private String nome;
    private Local local;
    private LocalDate data;
    private double precoIngresso;
    private List<Ingresso> ingressosVendidos;

    /**
     * Construtor da classe Evento
     * @param nome o nome do Evento
     * @param local o local associado ao Evento
     */
    public Evento(String nome, Local local, String data, double precoIngresso){
        this.nome = nome;
        this.local = local;
        this.data = converteStringParaData(data);
        this.precoIngresso = precoIngresso;
        this.ingressosVendidos = new ArrayList<>();
    }

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

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public String getData() {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setData(String data) {
        this.data = converteStringParaData(data);
    }

    public void adicionarIngresso(Ingresso ingresso, Usuario usuario) {
        this.ingressosVendidos.add(ingresso);
        usuario.adicionarIngresso(ingresso);
    }

    public double calcularFaturamento(){
        double faturamentoTotal = 0.0;

        for(Ingresso ingresso : ingressosVendidos){
            faturamentoTotal += ingresso.getPreco();
        }

        return faturamentoTotal;
    }

    abstract public void exibirDetalhes();
}
