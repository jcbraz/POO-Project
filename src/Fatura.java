import java.io.Serializable;
import java.time.LocalDate;

public class Fatura implements Serializable {

    // Variáveis
    private static int counter = 0;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int codefornecedor;
    private int nifCliente;
    private double consumo;
    private double custo;
    private int codigoCasa;
    private int code;

    /**
     * Construtor
     */
    public Fatura() {
        this.dataInicio = LocalDate.now();
        this.dataFim = LocalDate.now();
        this.codefornecedor = 0;
        this.nifCliente = 0;
        this.consumo = 0;
        this.custo = 0;
        this.codigoCasa = 0;
        this.code = 0;
    }

    /**
     * Construtor
     */
    public Fatura(LocalDate dateinicio, LocalDate datefim, int codefornecedor, int nifCliente, double consumo,
            double custo, int codigoCasa) {
        this.dataInicio = dateinicio;
        this.dataFim = datefim;
        this.codefornecedor = codefornecedor;
        this.nifCliente = nifCliente;
        this.consumo = consumo;
        this.custo = custo;
        this.codigoCasa = codigoCasa;
        this.code = counter++;
    }

    /**
     * Construtor
     */
    public Fatura(Fatura fat) {
        this.dataInicio = fat.dataInicio;
        this.dataFim = fat.dataFim;
        this.codefornecedor = fat.codefornecedor;
        this.nifCliente = fat.nifCliente;
        this.consumo = fat.consumo;
        this.custo = fat.custo;
        this.codigoCasa = fat.codigoCasa;
        this.code = fat.code;
    }

    /**
     * Método que retorna data inicial
     * 
     * @return Data Inicial
     */
    public LocalDate getDateInicio() {
        return this.dataInicio;
    }

    /**
     * Método que retorna data final
     * 
     * @return Data Final
     */
    public LocalDate getDateFim() {
        return this.dataFim;
    }

    /**
     * Método que implementa o getter de um Fornecedor
     * 
     * @return Codigo do Fornecedor
     */
    public int getFornecedor() {
        return this.codefornecedor;
    }

    /**
     * Método que implementa o getter de um NIF de um cliente
     * 
     * @return NIF cliente
     */
    public int getNifCliente() {
        return this.nifCliente;
    }

    /**
     * Método que implementa o getter do Consumo
     * 
     * @return Consumo
     */
    public double getConsumo() {
        return this.consumo;
    }

    /**
     * Método que implementa o getter do custo
     * 
     * @return Custo
     */
    public double getCusto() {
        return this.custo;
    }

    /**
     * Método que implementa o getter do codigo de uma casa
     * 
     * @return Codigo da casa
     */
    public int getCodigoCasa() {
        return this.codigoCasa;
    }

    /**
     * Método auxiliar à obtenção do código do fornecedor
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Método que implementa o setter da data inicial
     * 
     * @return Data Inicial
     */
    public void setDateInicio(LocalDate dateInicio) {
        this.dataInicio = dateInicio;
    }

    /**
     * Método que setorna data final
     * 
     * @return Data Final
     */
    public void setDateFim(LocalDate dateFim) {
        this.dataFim = dateFim;
    }

    /**
     * Método que implementa o setter de um Fornecedor
     * 
     * @return Codigo do Fornecedor
     */
    public void setFornecedor(int codefornecedor) {
        this.codefornecedor = codefornecedor;
    }

    /**
     * Método que implementa o setter de um NIF de um cliente
     * 
     * @return NIF cliente
     */
    public void setNifCliente(int nifCliente) {
        this.nifCliente = nifCliente;
    }

    /**
     * Método que implementa setter do Consumo
     * 
     * @return Consumo
     */
    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    /**
     * Método que implementa o setter do custo
     * 
     * @return Custo
     */
    public void setCusto(double custo) {
        this.custo = custo;
    }

    /**
     * Método que implementa o setter do codigo de uma casa
     * 
     * @return Codigo da casa
     */
    public void setCodigoCasa(int codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    /**
     * Método auxiliar à obtenção do código do fornecedor
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Método que gera clone de Fatura
     * 
     * @return Fatura.
     */
    public Fatura clone() {
        return new Fatura(this);
    }

}