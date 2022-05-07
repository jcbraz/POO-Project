/**
 * Fornecedor is a class that represents a supplier of energy to a house
 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

public class Fornecedor {
    private static int counter = 0;
    private String nomeFornecedor;
    private int code;
    private final double valorBase = 0.15; // preco por kWh
    private final double tax = 1.06; // taxa de IVA
    private ArrayList<Integer> codigosFaturas;
    private HashMap<Integer, CasaInteligente> clientes;



    public Fornecedor() {
        this.nomeFornecedor = "";
        this.code = 0;
        this.codigosFaturas = new ArrayList<Integer>();
        this.clientes = new HashMap<Integer, CasaInteligente>();
    }

    public Fornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
        this.code = counter++;
        this.codigosFaturas = new ArrayList<Integer>();
        this.clientes = new HashMap<Integer, CasaInteligente>();
    }

    public String getNomeFornecedor() {
        return this.nomeFornecedor;
    }

    public ArrayList<Integer> getCodigosDeFaturas() {
        return this.codigosFaturas;
    }

    public HashMap<Integer, CasaInteligente> getClientes() {
        return this.clientes;
    }

    public int getCode() {
        return this.code;
    }

    public void setNomeFornecedor(String fornecedor) {
        this.nomeFornecedor = fornecedor;
    }

    public void SetCode(int code) {
        this.code = code;
    }

    // toString 
    public String toString() {
        return "Fornecedor{" +
                "nomeFornecedor='" + nomeFornecedor + '\'' +
                ", code='" + code + '\'' +
                ", clientes=" + clientes +
                '}';
    }

    // clone
    public Fornecedor clone() {
        return new Fornecedor(this.nomeFornecedor);
    }

    public void addCliente(CasaInteligente cliente) {
        this.clientes.put(cliente.getCode(), cliente);
    }

    public void removeCliente(CasaInteligente cliente) {
        this.clientes.remove(cliente.getCode());
    }

    public double precoTotalDiarioCliente(int code) {
        return (valorBase * this.clientes.get(code).energiaTotalDiariaCasa() * tax) * 0.9;
    }

    public CasaInteligente casaComMaiorConsumoDoFornecedor() {
        double maiorConsumo = 0.00;
        CasaInteligente casaComMaiorConsumo = new CasaInteligente();
        for (CasaInteligente casa : clientes.values()) {
            if (casa.energiaTotalDiariaCasa() > maiorConsumo) {
                maiorConsumo = casa.energiaTotalDiariaCasa();
                casaComMaiorConsumo = casa;
            }
        }
        return casaComMaiorConsumo;
    }

    public Fatura criaFatura(int nifCliente, LocalDate dateinicio, LocalDate datefim, double consumo, double custo, int codigoCasa) {
        double consumoTotal = this.clientes.values().stream().mapToDouble(c -> c.energiaTotalDiariaCasa()).sum();
        int days = (int) ChronoUnit.DAYS.between(dateinicio, datefim);
        double custoTotal = this.precoTotalDiarioCliente(code) * days;
        return new Fatura(dateinicio, datefim,code, nifCliente, consumoTotal, custoTotal, codigoCasa);
    }

    public void addFaturaClienteAL(Fatura fatura) {
        this.codigosFaturas.add(fatura.getCode());
        this.clientes.get(fatura.getCodigoCasa()).addFatura(fatura.clone().getCode());
    }

    public double faturacaoFornecedor() {
        double valorTotal = 0.00;
        for (CasaInteligente casa : clientes.values()) {
            valorTotal += (casa.energiaTotalDiariaCasa() * valorBase * tax) * 0.9;
        }
        return valorTotal;
    }
}