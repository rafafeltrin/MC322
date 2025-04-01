/*
 * App.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab01;

/**
 * Contém a estrutura de implementação da aplicação.
 * Demonstra o uso de diferentes tipos de eventos e funcionalidades relacionadas.
 * 
 * @author Rafael Feltrin - 276246
 */
public class App {

    /**
     * Método principal da aplicação.
     * 
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        //Criacao de um usuários
        Usuario usuario0 = new Usuario("Rafael", "rafafeltirn@gmail.com");
        Usuario usuario1 = new Usuario("João", "joao@example.com");
        Usuario usuario2 = new Usuario("Cassia", "cassia@example.com");
        Usuario usuario3 = new Usuario("Benjamin", "benjamin@example.com");
        Usuario usuario4 = new Usuario("Mirian", "mirian@example.com");
        
        //Criacao de um historico de eventos
        HistoricoEventos historicoEventos = new HistoricoEventos();

        desmonstrandoEventoEsporte(usuario0, usuario1, historicoEventos);

        desmonstrandoEventoTeatro(usuario2, historicoEventos);

        desmonstrandoEventoFestival(usuario3, usuario0, historicoEventos);
        
        desmonstrandoEventoShow(usuario4, historicoEventos);

        desmonstrandoMultiplosIngressos(usuario0);

        demonstrandoHistorico(historicoEventos);
        
        demonstrandoFiltro(historicoEventos);
    }

    // Demonstração de um evento do tipo esporte
    private static void desmonstrandoEventoEsporte(Usuario usuario0, Usuario usuario1, HistoricoEventos historicoEventos){
        //Criacao de um evento de esporte
        System.out.println("==================================================");
        Local morumbi = new Local("Morumbi", 60000);
        EventoEsporte eventoEsporte = new EventoEsporte(
            "São Paulo x Palmeiras",
            morumbi,
            "07/04/2025",
            150.0,
            "Futebol",
            "Campeonato Brasileiro",
            "São Paulo",
            "Palmeiras"
        );
        
        //Adicionando um ingresso vip
        System.out.println("Criando ingresso VIP para o evento de esporte...");
        Ingresso ingressoVip = new IngressoVIP(eventoEsporte);
        eventoEsporte.adicionarIngresso(ingressoVip, usuario0);
        System.out.println("Código do ingresso VIP: " + ingressoVip.getCodigo());
        System.out.println("Preço do ingresso VIP: " + ingressoVip.getPreco());

        //Criando um ingresso meia-entrada
        System.out.println("\nCriando ingresso de meia-entrada para o evento de esporte...");
        Ingresso ingressoMeia = new IngressoMeia(eventoEsporte);
        eventoEsporte.adicionarIngresso(ingressoMeia, usuario1);
        System.out.println("Código do ingresso de meia-entrada: " + ingressoMeia.getCodigo());
        System.out.println("Preço do ingresso de meia-entrada: " + ingressoMeia.getPreco() + "\n");

        System.out.println("Evento Esporte");
        eventoEsporte.exibirDetalhes();
        System.out.println("==================================================\n\n\n\n");

        historicoEventos.adicionarEvento(eventoEsporte);
    }

    // Demonstração de um evento do tipo teatro
    private static void desmonstrandoEventoTeatro(Usuario usuario1, HistoricoEventos historicoEventos){
        //Criacao de um evento Teatro
        System.out.println("==================================================");
        Local teatroAmazonas = new Local("Teatro Amazonas", 1000);
        EventoTeatro eventoTeatro = new EventoTeatro(
            "Auto da compadecida",
            teatroAmazonas,
            "02/04/2025",
            100.0,
            120,
            "Comédia",
            "10+"
        );
        
        //Adicionando um ingresso Inteira
        System.out.println("Criando inteira para o evento de teatro...");
        Ingresso ingressoInteira = new IngressoInteira(eventoTeatro);
        eventoTeatro.adicionarIngresso(ingressoInteira, usuario1);
        System.out.println("Preço do ingresso Inteira: " + ingressoInteira.getPreco());
        
        System.out.println("\nEvento Teatro");
        eventoTeatro.exibirDetalhes();
        System.out.println("==================================================\n\n\n\n");

        historicoEventos.adicionarEvento(eventoTeatro);
    }

    // Demonstração de um evento do tipo festival
    private static void desmonstrandoEventoFestival(Usuario usuario1, Usuario usuario2, HistoricoEventos historicoEventos){
        //Criacao de um evento Festival
        System.out.println("==================================================");
        Local Ibirapuera = new Local("Galpão Ibirapuera", 100000);
        EventoFestival eventoFestival = new EventoFestival(
            "CamposParty",
            Ibirapuera,
            "01/05/2025",
            400.0,
            "festival de tecnologia"
        );
        eventoFestival.adicionarPatrocinador("Intel");
        eventoFestival.adicionarPatrocinador("NVIDIA");
        eventoFestival.adicionarPatrocinador("AMD");
        eventoFestival.adicionarPatrocinador("Google");

        //Adicionando um ingresso Banco do Brasil
        System.out.println("\nCriando ingresso Banco do Brasil para o evento de festival...");
        Ingresso ingressoBancoDoBrasil = new IngressoBancoDoBrasil(eventoFestival);
        eventoFestival.adicionarIngresso(ingressoBancoDoBrasil, usuario1);
        System.out.println("Preço do ingresso Banco do Brasil: " + ingressoBancoDoBrasil.getPreco() + "\n");

        //Criando um ingresso meia-entrada
        System.out.println("Criando ingresso de meia-entrada para o evento de festival...");
        Ingresso ingressoMeiaFestival = new IngressoMeia(eventoFestival);
        eventoFestival.adicionarIngresso(ingressoMeiaFestival, usuario2);
        System.out.println("Código do ingresso de meia-entrada: " + ingressoMeiaFestival.getCodigo());  
        System.out.println("Preço do ingresso de meia-entrada: " + ingressoMeiaFestival.getPreco() + "\n");
        
        //Exibindo detalhes do evento
        System.out.println("Evento Festival");
        eventoFestival.exibirDetalhes();
        System.out.println("==================================================\n\n\n");

        historicoEventos.adicionarEvento(eventoFestival);
    }

    // Demonstração de um evento do tipo show
    private static void desmonstrandoEventoShow(Usuario usuario1, HistoricoEventos historicoEventos){
        //Criacao de um evento do tipo show
        System.out.println("==================================================");
        Local chapeuBrasil = new Local("Chápeu Brasil", 1000);
        EventoShow eventoShow = new EventoShow(
            "MTG",
            chapeuBrasil,
            "02/04/2025",
            100.0,
            "MC Livinho",
            "Funk",
            300
        );

        //Adicionando um ingresso VIP
        System.out.println("Criando ingresso VIP para o evento de show...");
        Ingresso ingressoVipShow = new IngressoVIP(eventoShow);
        eventoShow.adicionarIngresso(ingressoVipShow, usuario1);
        System.out.println("Preço do ingresso VIP: " + ingressoVipShow.getPreco());
        
        //Exibindo os detalhes do evento
        System.out.println("\nEvento Show");
        eventoShow.exibirDetalhes();
        System.out.println("==================================================\n\n\n");

        historicoEventos.adicionarEvento(eventoShow);
    }

    // Demonstração de múltiplos ingressos para um único usuário
    private static void desmonstrandoMultiplosIngressos(Usuario usuario){
        //Demonstracao que um usuário pode ter mais de um ingresso
        System.out.println("==================================================");
        System.out.println("Demonstração que um usuário pode ter mais de um ingresso:");
        System.out.println("Usuário: " + usuario.getNome() + " - Email: " + usuario.getEmail());
        System.out.println("Ingressos do usuário: ");
        for (Ingresso ingresso : usuario.getIngressosComprados()) {
            System.out.println("- Código: " + ingresso.getCodigo() + ", Preço: " + ingresso.getPreco() + ", Evento: " + ingresso.getEvento().getNome());
        }
        System.out.println("==================================================\n\n\n");
    }

    // Demonstração do histórico de eventos
    private static void demonstrandoHistorico(HistoricoEventos historicoEventos){
        System.out.println("Adicionando mais eventos ao histórico...");
        historicoEventos.adicionarEvento(
            new EventoEsporte(
                "Torneio de Tênis",
                new Local("Centro de Tênis", 5000),
                "15/04/2025",
                500.0,
                "Tênis",
                "US Open"
            )
        );

        historicoEventos.adicionarEvento(
            new EventoTeatro(
                "Hamlet",
                new Local("Teatro Municipal", 1500),
                "10/05/2025",
                120.0,
                150,
                "Drama",
                "12+"
            )
        );

        historicoEventos.adicionarEvento(
            new EventoFestival(
                "Lollapalooza",
                new Local("Autódromo de Interlagos", 70000),
                "25/03/2025",
                500.0,
                "Festival de música"
            ));

        historicoEventos.adicionarEvento(
            new EventoShow(
                "Coldplay Live",
                new Local("Allianz Parque", 55000),
                "20/06/2025",
                300.0,
                "Coldplay",
                "Pop Rock",
                400
            )
        );
        
        //Demonstracao do Historico de eventos
        System.out.println("========================================");
        System.out.println("Histórico de eventos:");
        for (Evento evento : historicoEventos.buscarTodosEventos()) {
            System.out.println("Evento: " + evento.getNome());
        }
        System.out.println("==================================================\n\n\n");
    }

    // Demonstração de filtros aplicados ao histórico de eventos
    private static void demonstrandoFiltro(HistoricoEventos historicoEventos){
        System.out.println("==================================================");
        System.out.println("Eventos de esporte do tipo futebol:");
        for (Evento evento : historicoEventos.buscarEventosPorFiltro(new EventoEsporte("", new Local("", 0), "20/09/2023", 0, "Futebol", ""))) {
            System.out.println(evento.getNome());
        }

        System.out.println("\nEventos show do genero Funk:");
        for (Evento evento : historicoEventos.buscarEventosPorFiltro(new EventoShow("", new Local("", 0), "20/09/2023", 0, "", "Funk", 0))) {
            System.out.println(evento.getNome());
        }

        System.out.println("\nEventos teatro do genero Drama:");
        for (Evento evento : historicoEventos.buscarEventosPorFiltro(new EventoTeatro("", new Local("", 0), "20/09/2023", 0, 0, "Drama", ""))) {
            System.out.println(evento.getNome());
        }

        System.out.println("\nEventos festival do tipo Festival de música:");
        for (Evento evento : historicoEventos.buscarEventosPorFiltro(new EventoFestival("", new Local("", 0), "20/09/2023", 0, "Festival de música"))) {
            System.out.println(evento.getNome());
        }
        System.out.println("==================================================\n\n\n");
    }
}