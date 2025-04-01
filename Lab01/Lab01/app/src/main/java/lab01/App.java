/*
 * App.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab01;

/**
 * Contém a estrutura de implementação da aplicação.
 * 
 * @author Rafael Feltrin - 276246
 */
public class App {

    /**
     * Aplicação principal
     * @param args
     */
    public static void main(String[] args) {
        //Criacao de um usuários
        Usuario usuario0 = new Usuario("Rafael", "rafafeltirn@gmail.com");
        Usuario usuario1 = new Usuario("João", "joao@example.com");
        Usuario usuario2 = new Usuario("Cassia", "cassia@example.com");
        Usuario usuario3 = new Usuario("Benjamin", "benjamin@example.com");
        Usuario usuario4 = new Usuario("Mirian", "mirian@example.com");


        //Criacao de um evento de esporte
        System.out.println("==================================================");
        Local morumbi = new Local("Morumbi", 60000);
        EventoEsporte eventoEsporte = new EventoEsporte("São Paulo x Palmeiras", morumbi, "07/04/2025", 150.0, "Futebol", "Campeonato Brasileiro", "São Paulo", "Palmeiras");
        
        //Adicionando um ingresso vip
        System.out.println("Criando ingresso VIP para o evento de esporte...");
        Ingresso ingressoVip = new IngressoVIP(eventoEsporte);
        eventoEsporte.adicionarIngresso(ingressoVip, usuario0);
        System.out.println("Código do ingresso VIP: " + ingressoVip.getCodigo());
        System.out.println("Preço do ingresso VIP: " + ingressoVip.getPreco());
        System.out.println("Evento correspondente ao ingresso: " + ingressoVip.getEvento().getNome());

        //Criando um ingresso meia-entrada
        System.out.println("\nCriando ingresso de meia-entrada para o evento de esporte...");
        Ingresso ingressoMeia = new IngressoMeia(eventoEsporte);
        eventoEsporte.adicionarIngresso(ingressoMeia, usuario1);
        System.out.println("Código do ingresso de meia-entrada: " + ingressoMeia.getCodigo());
        System.out.println("Preço do ingresso de meia-entrada: " + ingressoMeia.getPreco() + "\n");

        System.out.println("Evento Esporte");
        eventoEsporte.exibirDetalhes();
        System.out.println("==================================================\n\n\n\n");
        

        //Criacao de um evento Teatro
        System.out.println("==================================================");
        Local teatroAmazonas = new Local("Teatro Amazonas", 1000);
        EventoTeatro eventoTeatro = new EventoTeatro("Auto da compadecida", teatroAmazonas, "02/04/2025", 100.0, 120, "Comédia", "10+");
        
        //Adicionando um ingresso Inteira
        System.out.println("Criando inteira para o evento de teatro...");
        Ingresso ingressoInteira = new IngressoInteira(eventoTeatro);
        eventoTeatro.adicionarIngresso(ingressoInteira, usuario2);
        System.out.println("Código do ingresso Inteira: " + ingressoInteira.getCodigo());
        System.out.println("Preço do ingresso Inteira: " + ingressoInteira.getPreco());
        
        System.out.println("Evento Teatro");
        eventoTeatro.exibirDetalhes();
        System.out.println("==================================================\n\n\n\n");
        
        
        
        // DEMONSTRAÇÃO PASSO 1



        // DEMONSTRAÇÃO PASSO 2



        // DEMONSTRAÇÃO PASSO 3



        // DEMONSTRAÇÃO PASSO 4



        // DEMONSTRAÇÃO PASSO 5
    }
}
