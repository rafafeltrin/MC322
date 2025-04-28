/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02;

import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nome;
    private Local local;
    private double precoIngresso; // preço base do ingresso
    private Organizadora organizadora;
    private String data;
    private CaracteristicaDeEvento caracteristica;
    private List<Ingresso> ingressosVendidos = new ArrayList<>();
    private int capacidadeMaxima;


    /**
     * Construtor da classe Evento
     * @param nome o nome do Evento
     * @param local o local associado ao Evento
     */
    public Evento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, CaracteristicaDeEvento caracteristica) {
        this.nome = nome;
        this.local = local;
        this.precoIngresso = precoIngresso; // modificar para representar o preço base do ingresso
        this.organizadora = organizadora;
        this.data = data;
        this.capacidadeMaxima = capacidadeMaxima;
        this.caracteristica = caracteristica;
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
     * Retorna o Local do Evento
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
     * Retorna o preço do ingresso do Evento
     * @return o precoIngresso do Evento
     */
    public double getPrecoIngresso(){
        return this.precoIngresso;
    }

    /**
     * Altera o precoIngresso do Evento para `precoIngresso` 
     * @param precoIngresso o novo precoIngresso do Evento
     */
    public void setPrecoIngresso(double precoIngresso){
        this.precoIngresso = precoIngresso;
    }

    public String descricao(){
        return "Evento: " + this.nome + " - Local: " + this.local + this.caracteristica.getDescricao();
    }

    public CaracteristicaDeEvento getCaracteristica() {
        return this.caracteristica;
    }

    /**
     * Retorna a data do Evento
     * @return a data do Evento
     */
    public String getData() {
        return data;
    }

    public void venderIngresso(Cliente cliente) throws IngressoEsgotadoException {
        if (ingressosVendidos.size() < capacidadeMaxima) {
            Ingresso ingresso = new Ingresso(this, this.precoIngresso);
            ingressosVendidos.add(ingresso);
            cliente.adicionarIngresso(ingresso);
        } else {
            throw new IngressoEsgotadoException("Evento lotado! Não há mais ingressos disponíveis.");
        }
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
}