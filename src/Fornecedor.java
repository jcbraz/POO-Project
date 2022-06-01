import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
* Classe que implementa metodos para o fornecedor
 */

public class Fornecedor implements Serializable {

    /**
    *Variaveis de instancia da classe fornecedor
     */
    private static int counter = 0;
    private String nomeFornecedor;
    private int code;
    private final double valorBase = 0.15; // preco por kWh
    private final double tax = 1.06; // taxa de IVA
    private ArrayList<Integer> codigosFaturas;
    private ArrayList<Integer> codigoClientes;

    /**
    *Construtor
     */
    public Fornecedor() {
        this.nomeFornecedor = "";
        this.code = counter++;
        this.codigosFaturas = new ArrayList<Integer>();
        this.codigoClientes = new ArrayList<Integer>();
    }

    /**
    *Construtor
     */

    public Fornecedor(String nomeFornecedor, int code, ArrayList<Integer> codigosFaturas,
            Map<Integer, CasaInteligente> clientes) {
        this.nomeFornecedor = nomeFornecedor;
        this.code = code;
        setCodigosFaturas(codigosFaturas);
        setCodigoClientes(codigoClientes);
    }

    /**
    * Construtor
     */
    public Fornecedor(Fornecedor forneceai) {
        this.nomeFornecedor = forneceai.nomeFornecedor;
        this.code = forneceai.code;
        this.codigosFaturas = new ArrayList<Integer>();
        forneceai.codigosFaturas.forEach(codigo -> this.codigosFaturas.add(codigo));
        this.codigoClientes = new ArrayList<Integer>();
        forneceai.codigoClientes.forEach(codigo -> this.codigoClientes.add(codigo));
    }

    /**
     * Getter do nome do fornecedor
     * 
     * @return nome do fornecedor
     */
    public String getNomeFornecedor() {
        return this.nomeFornecedor;
    }

    /**
     * getter da lista dos codigos das faturas
     * 
     * @return arraylist de inteiros que sao os codigos das faturas
     */
    public ArrayList<Integer> getCodigosDeFaturas() {
        return this.codigosFaturas.stream().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
    * getter do codigo dos clientes
    * @return codigos de clientes
     */

    public ArrayList<Integer> getCodigoClientes() {
        return this.codigoClientes.stream().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * getter do codigode fornecedor
     * 
     * @return codigo de fornecedor
     */
    public int getCode() {
        return this.code;
    }

    /**
    * getter do preco base por kWh
    ** @return valor base
     */

    public double getValorBase() {
        return valorBase;
    }

    /**
    * getter da taxa de iva
    ** @return taxa de iva
     */

    public double getTax() {
        return tax;
    }

    /**
     * setter do nome do fornecedor
     * 
     * @param fornecedor nome do fornecedor
     */
    public void setNomeFornecedor(String fornecedor) {
        this.nomeFornecedor = fornecedor;
    }

    /**
     * setter do codigo do fornecedor
     * 
     * @param code codigo do fornecedor
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
    *setter dos codigos de faturas
    * @param codigosFaturas arraylist com os codigos de fatura
    *
     */

    public void setCodigosFaturas(ArrayList<Integer> codigosFaturas) {
        this.codigosFaturas = new ArrayList<Integer>();
        codigosFaturas.forEach(codigo -> this.codigosFaturas.add(codigo));
    }

     /**
    *setter dos codigos de cliente
    * @param codigosFaturas arraylist com os codigos de cliente
    *
     */

    public void setCodigoClientes(ArrayList<Integer> codigoClientes) {
        this.codigoClientes = new ArrayList<Integer>();
        codigoClientes.forEach(codigo -> this.codigoClientes.add(codigo));
    }

    /**
     * implementacao do metodo toString()
     * 
     * @return string
     */
    public String toString() {
        return "Fornecedor{" +
                "nomeFornecedor='" + nomeFornecedor + '\'' +
                ", code='" + code + '\'' +
                ", codigoClientes=" + codigoClientes +
                '}';
    }

    /**
     * implementacao do metodo clone()
     * 
     * @return novo fornecedor com os mesmos dados do antigo
     */
    public Fornecedor clone() {
        return new Fornecedor(this);
    }

    /**
     * Adiciona um cliente 
     *
     * @param cliente o cliente a ser adicionado aos clientes
     */
    public void addCliente(CasaInteligente cliente) {
        this.codigoClientes.add(cliente.getCode());
    }

    /**
     * Remove um cliente 
     * 
     * @param cliente cliente a ser removido
     */
    public void removeCliente(CasaInteligente cliente) {
        this.codigoClientes.remove(cliente.getCode());
    }

    /**
     * metodo que calcula o preco total diario de um determinado cliente com base
     * nos consumos de sua casa
     * 
     * @param code codigo de cliente
     * @return preco total de energia consumida diariamente
     */
    public double precoTotalDiarioCliente(CasaInteligente casa) {
        return (valorBase * casa.energiaTotalDiariaCasa() * tax) * 0.9;
    }

    /**
     * Adiciona uma fatura
     * 
     * @param codefatura fatura a ser adicionada
     */

    public void addFatura(int codefatura) {
        this.codigosFaturas.add(codefatura);
    }
}