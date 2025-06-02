//O JavaDoc e a função de inicialização de dados dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

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
import lab03.ui.MainWindowController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Contém a estrutura de implementação da aplicação.
 * 
 * @author NOME - RA
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

        // Cria a cena com o conteúdo do FXML.
        Scene scene = new Scene(fxmlLoader.load(), 950, 600); // Largura e altura da janela

        scene.getStylesheets().add(getClass().getResource("ui/styles.css").toExternalForm());

        MainWindowController controller = fxmlLoader.getController();

        controller.initData(this.todosEventos, this.marketplace, this.clientes.getFirst());

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
        // Inicializando o marketplace com 10% de comissão
        this.marketplace = new Marketplace(0.2);

        // Criando 2 Clientes com saldo arbitrário
        Cliente cliente1 = new Cliente("Alice", "alice@email.com", 1000.0);
        Cliente cliente2 = new Cliente("Bob", "bob@email.com", 800.0);
        this.clientes.addAll(Arrays.asList(cliente1, cliente2));

        // Criando 2 Organizadoras
        Organizadora orgShowMasters = new Organizadora("ShowMasters Inc.", 123456, "Rua dos Palcos, 123");
        Organizadora orgFestPlus = new Organizadora("FestPlus Produções", 789012, "Avenida da Folia, 456");
        this.organizadoras.addAll(Arrays.asList(orgShowMasters, orgFestPlus));

        // Criando 3 Locais diferentes
        Local estadioOlimpico = new Local("Estádio Olímpico", 40000);
        Local teatroMunicipal = new Local("Teatro Municipal", 1500);
        Local casaDeShowCentral = new Local("Casa de Show Central", 5000);
        this.locais.addAll(Arrays.asList(estadioOlimpico, teatroMunicipal, casaDeShowCentral));

        try {
            // Criando 3 eventos, cada um em um local, por organizadoras diferentes [cite: 133]

            // Evento 1 (Show)
            orgShowMasters.criarEvento("Show do Artista X", casaDeShowCentral, 150.0, orgShowMasters, "2025-08-10", 5000, "Artista X");
            // Adicionando o evento criado à lista geral de eventos
            this.todosEventos.add(orgShowMasters.buscarEventos(new lab03.filter.EventoPorNomeFilter("Show do Artista X")).get(0));

            // Evento 2 (Festival)
            orgFestPlus.criarEvento("Festival de Verão", estadioOlimpico, 300.0, orgFestPlus, "2025-09-20", 40000, Arrays.asList("Banda Y", "Cantora Z"), 2);
            this.todosEventos.add(orgFestPlus.buscarEventos(new lab03.filter.EventoPorNomeFilter("Festival de Verão")).get(0));

            // Evento 3 (Jogo)
            orgShowMasters.criarEvento("Final do Campeonato", teatroMunicipal, 80.0, orgShowMasters, "2025-07-30", 1500, Arrays.asList("Time A", "Time B"), "Futebol de Salão");
            this.todosEventos.add(orgShowMasters.buscarEventos(new lab03.filter.EventoPorNomeFilter("Final do Campeonato")).get(0));

            // OPCIONAL: Inicializar um cliente com ingressos e ofertar um no marketplace [cite: 133]
            Evento eventoParaVenda = this.todosEventos.get(0); // Pegando o "Show do Artista X"

            // Cliente 1 compra dois ingressos
            eventoParaVenda.venderIngresso(cliente1);
            eventoParaVenda.venderIngresso(cliente1);

            System.out.println("Cliente " + cliente1.getNome() + " comprou 2 ingressos. Saldo agora: " + cliente1.getSaldo());

            // Cliente 1 oferece um dos ingressos no marketplace por um preço maior
            Ingresso ingressoParaVender = cliente1.getIngressos().get(0);
            cliente1.oferecerIngressoParaVenda(ingressoParaVender, 200.0, this.marketplace);

            System.out.println("Cliente " + cliente1.getNome() + " ofertou um ingresso no marketplace.");
            System.out.println("Ofertas no marketplace: " + this.marketplace.listarOfertas().size());


        } catch (IngressoEsgotadoException |
                 IngressoNaoPertenceAoClienteException | LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.err.println("Ocorreu um erro na inicialização dos dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
