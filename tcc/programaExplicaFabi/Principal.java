package programaExplicaFabi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

@SuppressWarnings("unused")
public class Principal extends Application {
    private ControladorFinanceiro controlador = new ControladorFinanceiro();
    private ObservableList<Transacao> transacoesObservableList = FXCollections.observableArrayList();
    private Label labelSaldo;

    @Override
    public void start(Stage primaryStage) {
        // Criar os componentes de interface
        Label labelValor = new Label("Valor: ");
        TextField inputValor = new TextField();

        Label labelCategoria = new Label("Categoria: ");
        TextField inputCategoria = new TextField();

        Label labelData = new Label("Data: ");
        DatePicker inputData = new DatePicker(LocalDate.now());

        Label labelTipo = new Label("Tipo: ");
        ToggleGroup group = new ToggleGroup();
        RadioButton rbReceita = new RadioButton("Receita");
        rbReceita.setToggleGroup(group);
        rbReceita.setSelected(true);
        RadioButton rbDespesa = new RadioButton("Despesa");
        rbDespesa.setToggleGroup(group);

        Label labelSubcategoria = new Label("Despesa em: ");
        ToggleGroup subGrupo = new ToggleGroup();
        RadioButton rbDebito = new RadioButton("Débito");
        rbDebito.setToggleGroup(subGrupo);
        RadioButton rbCredito = new RadioButton("Crédito");
        rbCredito.setToggleGroup(subGrupo);

        Label labelDescricao = new Label("Descrição: ");
        TextField inputDescricao = new TextField();

        Button btnAdicionar = new Button("Adicionar Transação");
        Button btnMostrarExtrato = new Button("Mostrar Extrato");
        labelSaldo = new Label("Saldo Atual: R$0,00");

        // Configurar layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(labelValor, 0, 0);
        grid.add(inputValor, 1, 0);
        grid.add(labelCategoria, 0, 1);
        grid.add(inputCategoria, 1, 1);
        grid.add(labelData, 0, 2);
        grid.add(inputData, 1, 2);
        grid.add(labelTipo, 0, 3);
        grid.add(rbReceita, 1, 3);
        grid.add(rbDespesa, 2, 3);
        grid.add(labelSubcategoria, 0, 4);
        grid.add(rbDebito, 1, 4);
        grid.add(rbCredito, 2, 4);
        grid.add(labelDescricao, 0, 5);
        grid.add(inputDescricao, 1, 5);
        grid.add(btnAdicionar, 1, 6);
        grid.add(btnMostrarExtrato, 2, 6);
        grid.add(labelSaldo, 1, 7);

        // Ocultar opções de subcategoria inicialmente
        labelSubcategoria.setVisible(false);
        rbDebito.setVisible(false);
        rbCredito.setVisible(false);

        // Mostrar/ocultar subcategoria com base na escolha de tipo
        rbReceita.setOnAction(e -> {
            labelSubcategoria.setVisible(false);
            rbDebito.setVisible(false);
            rbCredito.setVisible(false);
        });

        rbDespesa.setOnAction(e -> {
            labelSubcategoria.setVisible(true);
            rbDebito.setVisible(true);
            rbCredito.setVisible(true);
        });

        // Ação do botão adicionar
        btnAdicionar.setOnAction(e -> {
            try {
                double valor = Double.parseDouble(inputValor.getText());
                String categoria = inputCategoria.getText();
                LocalDate data = inputData.getValue();
                boolean isReceita = rbReceita.isSelected();
                String descricao = inputDescricao.getText();
                String subcategoria = null;

                if (!isReceita) {
                    subcategoria = rbDebito.isSelected() ? "Débito" : "Crédito";
                }

                Transacao transacao = new Transacao(valor, categoria, data, isReceita, descricao, subcategoria);
                controlador.adicionarTransacao(transacao);
                transacoesObservableList.add(transacao);

                // Atualiza o saldo
                atualizarSaldo();

                // Limpar os Campos
                inputValor.clear();
                inputCategoria.clear();
                inputData.setValue(LocalDate.now());
                inputDescricao.clear();
                group.selectToggle(rbReceita); // Definir como Receita por padrão
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Valor inválido!");
                alert.show();
            }
        });

        // Ação do botão mostrar extrato
        btnMostrarExtrato.setOnAction(e -> mostrarExtrato());

        // Configurar e mostrar a cena
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setTitle("Controle Financeiro Pessoal ExplicaFabi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para atualizar o saldo
    public void atualizarSaldo() {
        double saldoAtual = controlador.calcularSaldo();
        labelSaldo.setText("Saldo Atual: R$ " + String.format("%.2f", saldoAtual));
    }

    // Método para mostrar o extrato
    private void mostrarExtrato() {
        ExtratoFinanceiro extratoFinanceiro = new ExtratoFinanceiro(controlador, this);
        extratoFinanceiro.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
