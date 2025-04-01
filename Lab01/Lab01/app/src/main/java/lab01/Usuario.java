/*
 * Usuario.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab01;

import java.util.ArrayList;
import java.util.List;

/**
 * Contém a estrutura de implementação de um Usuario.
 * 
 * @author Gabriel Leite - 216180
 * @author Caio Rhoden - 214129
 */
public class Usuario {

    private String nome;
    private String email;
    private List<Ingresso> ingressosComprados;

    /**
     * Construtor da classe Usuario
     * @param nome o nome do usuário
     * @param email o email do usuário
     */
    public Usuario(String nome, String email){
        this.nome = nome;
        this.email = email;
        ingressosComprados = new ArrayList<>();
    }

    /**
     * Retorna o nome do usuário
     * @return o nome do usupario
     */
    public String getNome(){
        return nome;
    }

    /**
     * Altera o nome do usuário para `nome` 
     * @param nome o novo nome do usuário
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void adicionarIngresso(Ingresso ingresso) {
        this.ingressosComprados.add(ingresso);
    }

    public void removerIngresso(Ingresso ingresso) {
        this.ingressosComprados.remove(ingresso);
    }

    public List<Ingresso> getIngressosComprados() {
        return ingressosComprados;
    }
}
