//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

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
 * @author Rafael Feltrin - 276246
 */
public class Local {
    private String nome;
    private int capacidade;

    /**
     * Construtor da classe Local
     * @param nome o nome do local
     */
    public Local(String nome, int capacidade){
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
    public int getCapacidade(){
        return capacidade;
    }
    
    /**
     * Altera a capacidade do local
     * @param capacidade a nova capacidade do local
     */
    public void setCapacidade(int capacidade){
        this.capacidade = capacidade;
    }
}
