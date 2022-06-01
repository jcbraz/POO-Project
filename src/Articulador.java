import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Articulador implements Serializable {

    /**
     * Declarar as variaveis usadas na classe
     */
    private Map<Integer, CasaInteligente> casas;
    private Map<Integer, Fornecedor> fornecedores;
    private Map<Integer, Fatura> faturas;

    /**
     * Construtor
     */
    public Articulador() {
        this.casas = new HashMap<Integer, CasaInteligente>();
        this.fornecedores = new HashMap<Integer, Fornecedor>();
        this.faturas = new HashMap<Integer, Fatura>();
    }

    /**
     * Construtor de copia
     */
    public Articulador(Articulador ci) {
        setCasas(ci.getCasas());
        setFornecedores(ci.getFornecedores());
        setFaturas(ci.getFaturas());
    }

    /**
     * Getter das Casas Inteligentes
     * 
     * @return Map das Casas Inteligentes.
     */
    public Map<Integer, CasaInteligente> getCasas() {
        return this.casas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Getter dos Fornecedores
     * 
     * @return Map de Fornecedores.
     */
    public Map<Integer, Fornecedor> getFornecedores() {
        return this.fornecedores.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Getter das Casas Inteligentes
     * 
     * @return Map das Casas Inteligentes.
     */
    public Map<Integer, Fatura> getFaturas() {
        return this.faturas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Metodo que implementa Setter das Casas Inteligentes
     * 
     * @param Map Casas Inteligentes.
     */
    public void setCasas(Map<Integer, CasaInteligente> casas) {
        this.casas = new HashMap<>();
        casas.entrySet().forEach(entry -> this.casas.put(entry.getKey(), entry.getValue()));

    }

    /**
     * Metodo que implementa Setter dos Fornecedores
     * 
     * @param Map Fornecedores.
     */
    public void setFornecedores(Map<Integer, Fornecedor> fornecedores) {
        this.fornecedores = new HashMap<>();
        fornecedores.entrySet().forEach(entry -> this.fornecedores.put(entry.getKey(), entry.getValue()));
        // this.fornecedores=fornecedores;
    }

    /**
     * Metodo que implementa Setter das Faturas
     * 
     * @param Map Faturas.
     */
    public void setFaturas(Map<Integer, Fatura> faturas) {
        this.faturas = new HashMap<>();
        faturas.entrySet().forEach(entry -> this.faturas.put(entry.getKey(), entry.getValue()));
    }

    /**
     * Método que retorna uma String que contém as casas, fornecedores e faturas criadas
     * 
     * @return String.
     */
    public String toString() {
        return "CasaInteligente{" +
                ", casas=" + casas +
                ", fornecedores=" + fornecedores +
                ", faturas=" + faturas +
                '}';
    }

    /**
     * Método que testa igualdade de um objeto o
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Articulador that = (Articulador) o;
        return casas.equals(that.casas) &&
                fornecedores.equals(that.fornecedores) &&
                faturas.equals(that.faturas);
    }

    /**
     * Método que Clona Articulador
     */
    public Articulador clone() {
        return new Articulador(this);
    }

    /**
     * Método que adiciona fornecedor ao map associado
     * 
     * @param fornecedor Fornecedor a ser adicionado.
     */
    public void addFornecedor(Fornecedor fornecedor) {
        this.fornecedores.put(fornecedor.clone().getCode(), fornecedor);
    }

    /**
     * Método que verifica se existe Fornecedor com dado código
     * 
     * @param codigoFornecedor Codigo do Fornecedor.
     * @return se existe o fornecedor.
     */
    public boolean existeFornecedor(int codigoFornecedor) {
        return this.fornecedores.containsKey(codigoFornecedor);
    }

    /**
     * Método que adiciona casa ao map associado
     * 
     * @param CasaInteligente Casa a ser adicionada.
     */
    public void addCasa(CasaInteligente ci) {
        this.casas.put(ci.clone().getCode(), ci);
    }

    /**
     * Método que verifica se existe Casa com dado código
     * 
     * @param codigoFornecedor Codigo da Casa.
     * @return se existe a casa.
     */
    public boolean existeCasa(int codigoCasa) {
        return this.casas.containsKey(codigoCasa);
    }

    /**
     * Método que remove casa ao map associado
     * 
     * @param CasaInteligente Casa a ser removida.
     */
    public void removeCasa(int code) {
        this.casas.remove(code);
    }

    /**
     * Método que adiciona Fatura ao map associado
     * 
     * @param CasaInteligente Fatura a ser adicionada.
     */
    public void addFaturaToMap(Fatura f) {
        this.faturas.put(f.getCode(), f.clone());
    }
}