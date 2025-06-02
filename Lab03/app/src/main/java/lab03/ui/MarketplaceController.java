package lab03.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lab03.Cliente;
import lab03.Marketplace;
import lab03.OfertaIngresso;
import lab03.exceptions.OfertaNaoEncontradaException;
import lab03.exceptions.SaldoInsuficienteException;

public class MarketplaceController {

    @FXML private TableView<OfertaIngresso> ofertasTable;
    @FXML private TableColumn<OfertaIngresso, String> eventoColumn;
    @FXML private TableColumn<OfertaIngresso, String> vendedorColumn;
    @FXML private TableColumn<OfertaIngresso, Double> precoOriginalColumn;
    @FXML private TableColumn<OfertaIngresso, Double> precoPedidoColumn;
    @FXML private Button comprarButton;

    private Marketplace marketplace;
    private Cliente clienteLogado;

    @FXML
    public void initialize() {
        // Para propriedades diretas de OfertaIngresso, usamos PropertyValueFactory
        precoPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("precoPedido"));

        // Para propriedades aninhadas, a melhor prática é usar uma lambda para extrair o valor
        eventoColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getIngresso().getEvento().getNome())
        );
        vendedorColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getVendedor().getNome())
        );
        precoOriginalColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getIngresso().getPreco()).asObject()
        );
    }

    /**
     * Recebe os dados do sistema para poder operar.
     * @param marketplace A instância do marketplace.
     * @param cliente O cliente que está navegando no marketplace.
     */
    public void initData(Marketplace marketplace, Cliente cliente) {
        this.marketplace = marketplace;
        this.clienteLogado = cliente;

        atualizarTabela();
    }

    @FXML
    private void handleComprar() {
        OfertaIngresso ofertaSelecionada = ofertasTable.getSelectionModel().getSelectedItem();
        if (ofertaSelecionada == null) {
            exibirAlerta("Ação inválida", "Por favor, selecione um ingresso para vender.");
            return;
        }
        try {
            marketplace.processarCompra(clienteLogado, ofertaSelecionada);
            exibirAlerta("Compra realizada", "Ingresso comprado com sucesso!");
        } catch (SaldoInsuficienteException e) {
            exibirAlerta("Erro ao realizar a compra", "Saldo insuficiente: " + e.getMessage());
        } catch (OfertaNaoEncontradaException e) {
            exibirAlerta("Erro ao realizar a compra", "Oferta não encontrada: " + e.getMessage());
        } finally {
            atualizarTabela();
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void atualizarTabela() {
        ofertasTable.setItems(FXCollections.observableArrayList(marketplace.listarOfertas()));
    }
}
