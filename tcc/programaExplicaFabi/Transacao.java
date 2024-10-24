package programaExplicaFabi;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Transacao implements Serializable {
    private double valor;
    private String categoria;
    private LocalDate data;
    private boolean receita; // true para receita, false para despesa
    private String descricao;
    private String subcategoria; // nova propriedade para a subcategoria

    public Transacao(double valor, String categoria, LocalDate data, boolean receita, String descricao, String subcategoria) {
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
        this.receita = receita;
        this.descricao = descricao;
        this.subcategoria = subcategoria; // inicializa a subcategoria
    }

    public double getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isReceita() {
        return receita;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSubcategoria() { // novo método para obter a subcategoria
        return subcategoria;
    }
}
