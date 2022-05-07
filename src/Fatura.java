import java.time.LocalDate;

public class Fatura implements Comparable<Fatura> {

    private static int counter = 0;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int codefornecedor;
    private int nifCliente;
    private double consumo;
    private double custo;
    private int codigoCasa;
    private int code;

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

    public LocalDate getDateInicio() {
        return this.dataInicio;
    }

    public LocalDate getDateFim() {
        return this.dataFim;
    }

    public int getFornecedor() {
        return this.codefornecedor;
    }

    public int getNifCliente() {
        return this.nifCliente;
    }

    public double getConsumo() {
        return this.consumo;
    }

    public double getCusto() {
        return this.custo;
    }

    public int getCodigoCasa() {
        return this.codigoCasa;
    }

    public int getCode() {
        return this.code;
    }

    public void setDateInicio(LocalDate dateInicio) {
        this.dataInicio = dateInicio;
    }

    public void setDateFim(LocalDate dateFim) {
        this.dataFim = dateFim;
    }

    public void setFornecedor(int codefornecedor) {
        this.codefornecedor = codefornecedor;
    }

    public void setNifCliente(int nifCliente) {
        this.nifCliente = nifCliente;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public void setCodigoCasa(int codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Fatura clone() {
        return new Fatura(this.dataInicio, this.dataFim, this.codefornecedor, this.nifCliente, this.consumo, this.custo, this.codigoCasa);
    }

    public int compareTo(Fatura fatura) {
        return this.dataInicio.compareTo(fatura.getDateInicio());
    }

}