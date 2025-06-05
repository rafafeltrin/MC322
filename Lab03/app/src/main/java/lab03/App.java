//O JavaDoc e a função de inicialização de dados dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)
// e ajustado para os novos requisitos.

/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab03;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab03.exceptions.CapacidadeInsuficienteException;
import lab03.exceptions.IngressoEsgotadoException;
import lab03.exceptions.IngressoNaoPertenceAoClienteException;
import lab03.exceptions.LocalIndisponivelException;
import lab03.model.cliente.Cliente;
import lab03.model.cliente.Ingresso;
import lab03.model.evento.Evento;
import lab03.model.evento.Local;
import lab03.model.marketplace.Marketplace;
import lab03.model.organizadora.Organizadora;
import lab03.ui.MainWindowController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Contém a estrutura de implementação da aplicação.
 *
 * @author SEU NOME - SEU RA
 */
public class App extends Application {
    private Marketplace marketplace;
    private List<Cliente> clientes = new ArrayList<>();
    private List<Organizadora> organizadoras = new ArrayList<>();
    private List<Local> locais = new ArrayList<>();
    private List<Evento> todosEventos = new ArrayList<>();


    /**
     * O método start é o principal ponto de entrada para todas as aplicações JavaFX.
     * É chamado após o método init ter retornado, e ele irá preparar o "palco" (Stage)
     * principal da sua aplicação.
     */
    @Override
    public void start(Stage stage) throws IOException {
        inicializarDados();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ui/MainWindow.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 950, 600);

        scene.getStylesheets().add(getClass().getResource("ui/styles.css").toExternalForm());

        MainWindowController controller = fxmlLoader.getController();

        // Passa a lista completa de clientes e o primeiro como cliente inicial
        if (!this.clientes.isEmpty()) {
            controller.initData(this.todosEventos, this.marketplace, this.clientes, this.clientes.get(0));
        } else {
            // Caso não hajam clientes inicializados (improvável com a nova inicializarDados)
            controller.initData(this.todosEventos, this.marketplace, this.clientes, null);
        }

        stage.setTitle("Sistema de Eventos e Marketplace");
        stage.setScene(scene);
        stage.show(); // Exibe a janela
    }

    /**
     * O método main é o ponto de partida do programa.
     * Ele apenas chama launch(), que é o método do JavaFX responsável por
     * inicializar a aplicação gráfica.
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void inicializarDados() {
        System.out.println("--- Iniciando a Simulação do Sistema ---");

        // --- Criação do Marketplace ---
        this.marketplace = new Marketplace(0.15); // Comissão de 15%
        System.out.println("* Marketplace criado com comissão de 15%.");

        // --- Criação de Organizadoras ---
        Organizadora liveNation = new Organizadora("Live Nation Brasil", 112233, "Av. das Nações Unidas, São Paulo");
        Organizadora t4f = new Organizadora("Tickets For Fun", 445566, "Rua Funchal, São Paulo");
        Organizadora cbf = new Organizadora("CBF Eventos Esportivos", 778899, "Av. Luis Carlos Prestes, Rio de Janeiro");
        this.organizadoras.addAll(Arrays.asList(liveNation, t4f, cbf));
        System.out.println("* Organizadoras criadas: " + liveNation.getNome() + ", " + t4f.getNome() + ", " + cbf.getNome());

        // --- Criação de Locais ---
        Local allianzParque = new Local("Allianz Parque", 43700);
        Local espacoUnimed = new Local("Espaço Unimed", 8000);
        Local maracana = new Local("Estádio do Maracanã", 78000);
        Local blueNoteSP = new Local("Blue Note São Paulo", 320);
        Local barDevExperience = new Local("Bar DevExperience", 100);
        Local ibirapueraAudit = new Local("Auditório Ibirapuera", 800);
        this.locais.addAll(Arrays.asList(allianzParque, espacoUnimed, maracana, blueNoteSP, barDevExperience, ibirapueraAudit));
        System.out.println("* Locais criados: Allianz Parque, Espaço Unimed, Maracanã, Blue Note SP, Bar DevExperience, Auditório Ibirapuera.");

        // --- Criação de Clientes ---
        Cliente carlos = new Cliente("Carlos Silva", "carlos.silva@email.com", 1500.0);
        Cliente beatriz = new Cliente("Beatriz Santos", "bia.santos@email.com", 2000.0);
        Cliente diego = new Cliente("Diego Costa", "diego.costa@email.com", 1200.0);
        Cliente fernanda = new Cliente("Fernanda Lima", "fe.lima@email.com", 2500.0);
        this.clientes.addAll(Arrays.asList(carlos, beatriz, diego, fernanda));
        System.out.println("* Clientes criados: Carlos, Beatriz, Diego, Fernanda.");

        // --- Criação de Eventos ---
        System.out.println("\n--- Criando Eventos ---");
        try {
            // 1. EventoShow
            t4f.criarEvento("Show do Djavan - Turnê D", espacoUnimed, 250.0, t4f, "2025-10-15", 7500, "Djavan");
            this.todosEventos.add(t4f.buscarEventos(e -> e.getNome().equals("Show do Djavan - Turnê D")).get(0));

            // 2. EventoFestival
            liveNation.criarEvento("Lollapalooza Brasil", allianzParque, 700.0, liveNation, "2026-03-28", 40000, Arrays.asList("Artista Top 1", "Banda Internacional", "DJ Famoso"), 3);
            this.todosEventos.add(liveNation.buscarEventos(e -> e.getNome().equals("Lollapalooza Brasil")).get(0));

            // 3. EventoJogo
            cbf.criarEvento("Final da Copa do Brasil", maracana, 180.0, cbf, "2025-11-20", 75000, Arrays.asList("Flamengo", "Corinthians"), "Futebol");
            this.todosEventos.add(cbf.buscarEventos(e -> e.getNome().equals("Final da Copa do Brasil")).get(0));

            // 4. EventoMusicaAoVivo
            liveNation.criarEvento("Noite de Jazz com Trio Brasileiro", blueNoteSP, 120.0, liveNation, "2025-09-05", 300, "Brasil Instrumental Trio", "Jazz Instrumental");
            this.todosEventos.add(liveNation.buscarEventos(e -> e.getNome().equals("Noite de Jazz com Trio Brasileiro")).get(0));

            // 5. EventoEmBar
            t4f.criarEvento("Happy Hour Devs & Drinks", barDevExperience, 30.0, t4f, "2025-08-22", 80, "Bar DevExperience", "18:00", "22:00");
            this.todosEventos.add(t4f.buscarEventos(e -> e.getNome().equals("Happy Hour Devs & Drinks")).get(0));

            // 6. EventoShow (Adicional)
            liveNation.criarEvento("Stand-up Comedy com Afonso Padilha", ibirapueraAudit, 90.0, liveNation, "2025-11-10", 700, "Afonso Padilha");
            this.todosEventos.add(liveNation.buscarEventos(e -> e.getNome().equals("Stand-up Comedy com Afonso Padilha")).get(0));

            System.out.println("* Total de " + this.todosEventos.size() + " eventos criados.");

            // --- Simulação de Vendas e Configurações Iniciais de Ingressos ---
            System.out.println("\n--- Simulando Vendas de Ingressos ---");

            // Cliente Carlos compra ingressos
            Evento showDjavan = this.todosEventos.get(0); // Show do Djavan
            showDjavan.venderIngresso(carlos); // Ingresso 1 para Carlos
            Ingresso ingressoDjavanCarlos = carlos.getIngressos().get(carlos.getIngressos().size() - 1);
            System.out.println("* Carlos comprou ingresso para: " + showDjavan.getNome() + ". Saldo Carlos: " + carlos.getSaldo());

            Evento finalCopa = this.todosEventos.get(2); // Final da Copa do Brasil
            finalCopa.venderIngresso(carlos);     // Ingresso 2 para Carlos
            Ingresso ingressoFinalCarlos = carlos.getIngressos().get(carlos.getIngressos().size() - 1);
            ingressoFinalCarlos.setAceitaCancelamento(false); // ESTE INGRESSO NÃO ACEITA CANCELAMENTO
            System.out.println("* Carlos comprou ingresso para: " + finalCopa.getNome() + " (NÃO CANCELÁVEL). Saldo Carlos: " + carlos.getSaldo());

            // Cliente Beatriz compra ingressos
            Evento lollapalooza = this.todosEventos.get(1); // Lollapalooza
            lollapalooza.venderIngresso(beatriz); // Ingresso 1 para Beatriz
            Ingresso ingressoLollaBeatriz = beatriz.getIngressos().get(beatriz.getIngressos().size() - 1);
            System.out.println("* Beatriz comprou ingresso para: " + lollapalooza.getNome() + ". Saldo Beatriz: " + beatriz.getSaldo());

            Evento noiteJazz = this.todosEventos.get(3); // Noite de Jazz
            noiteJazz.venderIngresso(beatriz);    // Ingresso 2 para Beatriz
            System.out.println("* Beatriz comprou ingresso para: " + noiteJazz.getNome() + ". Saldo Beatriz: " + beatriz.getSaldo());

            // Cliente Diego compra ingresso
            showDjavan.venderIngresso(diego);
            System.out.println("* Diego comprou ingresso para: " + showDjavan.getNome() + ". Saldo Diego: " + diego.getSaldo());

            // Cliente Fernanda compra ingresso
            Evento standup = this.todosEventos.get(5); // Stand-up
            standup.venderIngresso(fernanda);
            System.out.println("* Fernanda comprou ingresso para: " + standup.getNome() + ". Saldo Fernanda: " + fernanda.getSaldo());


            // --- Colocando Ingressos no Marketplace ---
            System.out.println("\n--- Ofertando Ingressos no Marketplace ---");
            // Carlos oferta seu ingresso do Show do Djavan por um preço maior
            carlos.oferecerIngressoParaVenda(ingressoDjavanCarlos, 300.0, this.marketplace);
            System.out.println("* Carlos ofertou '" + ingressoDjavanCarlos.getEvento().getNome() + "' no marketplace por R$300.00.");

            // Beatriz oferta seu ingresso do Lollapalooza
            beatriz.oferecerIngressoParaVenda(ingressoLollaBeatriz, 750.0, this.marketplace);
            System.out.println("* Beatriz ofertou '" + ingressoLollaBeatriz.getEvento().getNome() + "' no marketplace por R$750.00.");

            System.out.println("* Total de ofertas no marketplace: " + this.marketplace.listarOfertas().size());


        } catch (IngressoEsgotadoException | IngressoNaoPertenceAoClienteException |
                 LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.err.println("Ocorreu um erro na inicialização dos dados de eventos/vendas: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n--- Simulação do Sistema Finalizada ---");
    }
}
