package lab03.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lab03.Evento;
import lab03.Marketplace;

import java.util.List;

/**
 * Controller para a janela principal da aplicação (MainWindow.fxml).
 * Responsável por popular a tabela de eventos e lidar com as interações do usuário.
 */
public class MainWindowController {

    // Anotação @FXML para injetar os componentes do arquivo FXML.
    // O nome da variável DEVE ser igual ao fx:id no FXML.
    @FXML
    private TableView<Evento> eventosTable;

    @FXML
    private TableColumn<Evento, String> nomeColumn;

    @FXML
    private TableColumn<Evento, String> dataColumn;

    @FXML
    private TableColumn<Evento, Double> precoColumn;

    // Atributos para guardar a referência aos dados do sistema
    private List<Evento> listaDeEventos;
    private Marketplace marketplace;

    /**
     * Método chamado automaticamente pelo JavaFX após o FXML ser carregado.
     * Usado para configurar os componentes iniciais da tela.
     */
    @FXML
    public void initialize() {
        // Configura as colunas da tabela para saber de onde pegar os dados de um objeto Evento.
        // O valor "nome", "data", "precoIngresso" DEVE corresponder exatamente
        // ao nome do método getter na classe Evento (ex: getNome(), getData(), getPrecoIngresso()).
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("precoIngresso"));
    }

    /**
     * Método para receber os dados da classe principal (App) e popular a interface.
     * @param eventos A lista de todos os eventos a serem exibidos.
     * @param marketplace A instância do marketplace do sistema.
     */
    public void initData(List<Evento> eventos, Marketplace marketplace) {
        this.listaDeEventos = eventos;
        this.marketplace = marketplace;

        // Popula a tabela com os eventos.
        // FXCollections.observableArrayList cria uma lista "observável" que a TableView entende.
        eventosTable.setItems(FXCollections.observableArrayList(this.listaDeEventos));
    }
}