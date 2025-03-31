/*
 * Local.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab01;

/**
 * Contém a estrutura de implementação de um Local.
 * 
 * @author Gabriel Leite - 216180
 * @author Caio Rhoden - 214129
 */
public class Local {
    private String nome;
    private double capacidade;

    /**
     * Construtor da classe Local
     * @param nome o nome do local
     */
    public Local(String nome, double capacidade){
        this.nome = nome;
        this.capacidade = capacidade;
    }

    /**
     * Retorna o nome do local
     * @return o nome do local
     */
    public String getNome(){
        return nome;
    }

    /**
     * Altera o nome do local para `nome` 
     * @param nome o novo nome do local
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Retorna a capacidade do local
     * @return a capacidade do local
     */
    public double getCapacidade(){
        return capacidade;
    }
    
    /**
     * Altera a capacidade do local para `capacidade` 
     * @param capacidade a nova capacidade do local
     */
    public void setCapacidade(double capacidade){
        this.capacidade = capacidade;
    }
}
