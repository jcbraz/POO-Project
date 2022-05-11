import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Fornecedor implements Serializable{
    private static int counter = 0;
    private String nomeFornecedor;
    private int code;
    private final double valorBase = 0.15; // preco por kWh
    private final double tax = 1.06; // taxa de IVA
    private ArrayList<Integer> codigosFaturas;
    private HashMap<Integer, CasaInteligente> clientes;

    // It's a constructor.
    public Fornecedor() {
        this.nomeFornecedor = "";
        this.code = 0;
        this.codigosFaturas = new ArrayList<Integer>();
        this.clientes = new HashMap<Integer, CasaInteligente>();
    }

    // It's a constructor.
    public Fornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
        this.code = counter++;
        this.codigosFaturas = new ArrayList<Integer>();
        this.clientes = new HashMap<Integer, CasaInteligente>();
    }

    /**
     * This function returns the name of the supplier
     * 
     * @return The name of the supplier.
     */
    public String getNomeFornecedor() {
        return this.nomeFornecedor;
    }

    /**
     * This function returns an ArrayList of Integers that contains the codes of the
     * invoices.
     * 
     * @return The method returns an ArrayList of Integers.
     */
    public ArrayList<Integer> getCodigosDeFaturas() {
        return this.codigosFaturas.stream().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This function returns the clientes attribute of the object
     * 
     * @return A HashMap of CasaInteligente objects.
     */
    public HashMap<Integer, CasaInteligente> getClientes() {
        return this.clientes;
    }

    /**
     * > This function returns the code of the current object
     * 
     * @return The code of the enum.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * This function sets the name of the supplier
     * 
     * @param fornecedor The name of the supplier.
     */
    public void setNomeFornecedor(String fornecedor) {
        this.nomeFornecedor = fornecedor;
    }

    /**
     * This function sets the code of the object to the value of the parameter.
     * 
     * @param code The code to be returned to the client.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * The toString() method returns a string representation of the object
     * 
     * @return The name of the supplier, the code and the list of customers.
     */
    public String toString() {
        return "Fornecedor{" +
                "nomeFornecedor='" + nomeFornecedor + '\'' +
                ", code='" + code + '\'' +
                ", clientes=" + clientes +
                '}';
    }

    /**
     * This function returns a new object that is a copy of the object that called
     * the function.
     * 
     * @return A new object of type Fornecedor with the same name as the original.
     */
    public Fornecedor clone() {
        return new Fornecedor(this.nomeFornecedor);
    }

    /**
     * It adds a client to the list of clients
     * 
     * @param cliente The client to be added to the list of clients.
     */
    public void addCliente(CasaInteligente cliente) {
        this.clientes.put(cliente.getCode(), cliente);
    }

    /**
     * It removes a client from the list of clients
     * 
     * @param cliente The client to be removed.
     */
    public void removeCliente(CasaInteligente cliente) {
        this.clientes.remove(cliente.getCode());
    }

    /**
     * It returns the total price of the energy consumed by a client in a day
     * 
     * @param code the client's code
     * @return The total price of the energy consumed by the client in a day.
     */
    public double precoTotalDiarioCliente(int code) {
        return (valorBase * this.clientes.get(code).energiaTotalDiariaCasa() * tax) * 0.9;
    }

    /**
     * Return the house with the highest daily energy consumption.
     * 
     * @return The house with the highest consumption of energy.
     */
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

    /**
     * It creates a new invoice for a client, given the client's NIF, the start and
     * end dates of the
     * invoice, the total energy consumption of the client's house, the total cost
     * of the invoice, and
     * the code of the client's house
     * 
     * @param nifCliente the client's NIF
     * @param dateinicio the start date of the bill
     * @param datefim    end date of the invoice
     * @param consumo    the total energy consumption of the client's house
     * @param custo      the cost of the energy consumed by the client
     * @param codigoCasa the code of the house
     * @return A new Fatura object is being returned.
     */
    public Fatura criaFatura(int nifCliente, LocalDate dateinicio, LocalDate datefim, double consumo, double custo,
            int codigoCasa) {
        double consumoTotal = this.clientes.values().stream().mapToDouble(c -> c.energiaTotalDiariaCasa()).sum();
        int days = (int) ChronoUnit.DAYS.between(dateinicio, datefim);
        double custoTotal = this.precoTotalDiarioCliente(code) * days;
        return new Fatura(dateinicio, datefim, code, nifCliente, consumoTotal, custoTotal, codigoCasa);
    }

    /**
     * It adds a bill to the list of bills of a client
     * 
     * @param fatura the invoice to be added
     */
    public void addFaturaClienteAL(Fatura fatura) {
        this.codigosFaturas.add(fatura.getCode());
        this.clientes.get(fatura.getCodigoCasa()).addFatura(fatura.clone().getCode());
    }

    /**
     * It calculates the total amount of money that the company has to pay to the
     * suppliers
     * 
     * @return The total value of the energy consumed by all houses.
     */
    public double faturacaoFornecedor() {
        double valorTotal = 0.00;
        for (CasaInteligente casa : clientes.values()) {
            valorTotal += (casa.energiaTotalDiariaCasa() * valorBase * tax) * 0.9;
        }
        return valorTotal;
    }
}