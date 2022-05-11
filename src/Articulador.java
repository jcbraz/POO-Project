import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Articulador implements Serializable {

    // It's declaring the variables that will be used in the class.
    private Map<Integer, CasaInteligente> casas;
    private Map<Integer, Fornecedor> fornecedores;
    private Map<Integer, Fatura> faturas;

    // It's the constructor of the class Articulador.
    public Articulador() {
        this.casas = new HashMap<Integer, CasaInteligente>();
        this.fornecedores = new HashMap<Integer, Fornecedor>();
        this.faturas = new HashMap<Integer, Fatura>();
    }

    // It's a copy constructor.
    public Articulador(Articulador ci) {
        setCasas(ci.getCasas());
        setFornecedores(ci.getFornecedores());
        setFaturas(ci.getFaturas());
    }

    /**
     * Return a map of all the houses in the city, where the key is the house's ID
     * and the value is the
     * house itself.
     * 
     * @return A copy of the map.
     */
    public Map<Integer, CasaInteligente> getCasas() {
        return this.casas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Return a map of all the entries in the fornecedores map, where the key is the
     * key of the entry
     * and the value is the value of the entry.
     * 
     * @return A map with the key and value of the fornecedores map.
     */
    public Map<Integer, Fornecedor> getFornecedores() {
        return this.fornecedores.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Return a map of all the invoices, where the key is the invoice id and the
     * value is the invoice
     * itself.
     * 
     * @return A copy of the map.
     */
    public Map<Integer, Fatura> getFaturas() {
        return this.faturas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * > This function sets the value of the variable `casas` to the value of the
     * parameter `casas`
     * 
     * @param casas A map of all the houses in the system. The key is the house's
     *              ID.
     */
    public void setCasas(Map<Integer, CasaInteligente> casas) {
        this.casas = new HashMap<>();
        casas.entrySet().forEach(entry -> this.casas.put(entry.getKey(), entry.getValue()));

    }

    /**
     * It sets the value of the fornecedores attribute to the value of the
     * fornecedores parameter
     * 
     * @param fornecedores A map of fornecedores, where the key is the fornecedor's
     *                     ID and the value is
     *                     the fornecedor itself.
     */
    public void setFornecedores(Map<Integer, Fornecedor> fornecedores) {
        this.fornecedores = new HashMap<>();
        fornecedores.entrySet().forEach(entry -> this.fornecedores.put(entry.getKey(), entry.getValue()));
      // this.fornecedores=fornecedores;
    }

    /**
     * It sets the value of the faturas attribute to the value of the faturas
     * parameter
     * 
     * @param faturas A map of all the invoices that are in the system.
     */
    public void setFaturas(Map<Integer, Fatura> faturas) {
        this.faturas = new HashMap<>();
        faturas.entrySet().forEach(entry -> this.faturas.put(entry.getKey(), entry.getValue()));
    }

    /**
     * The function returns a string that contains the values of the variables
     * casas, fornecedores and
     * faturas
     * 
     * @return The toString method is being returned.
     */
    public String toString() {
        return "CasaInteligente{" +
                ", casas=" + casas +
                ", fornecedores=" + fornecedores +
                ", faturas=" + faturas +
                '}';
    }

    /**
     * If the object is the same object, or if the object is not null and is of the
     * same class, then
     * check if the fields are equal
     * 
     * @param o the object to compare to
     * @return The hashCode of the object.
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
     * This function returns a new Articulador object that is a copy of the
     * Articulador object that
     * called this function.
     * 
     * @return A new Articulador object with the same values as the original.
     */
    public Articulador clone() {
        return new Articulador(this);
    }

    /**
     * It adds a supplier to the list of suppliers
     * 
     * @param fornecedor The fornecedor object to be added to the fornecedores
     *                   HashMap.
     */
    public void addFornecedor(Fornecedor fornecedor) {
        this.fornecedores.put(fornecedor.getCode(), fornecedor);
    }

    /**
     * > This function checks if a supplier exists in the system
     * 
     * @param codigoFornecedor The supplier's code.
     * @return A boolean value.
     */
    public boolean existeFornecedor(int codigoFornecedor) {
        return this.fornecedores.containsKey(codigoFornecedor);
    }

    /**
     * It adds a house to the list of houses
     * 
     * @param ci CasaInteligente object
     */
    public void addCasa(CasaInteligente ci) {
        this.casas.put(ci.getCode(), ci);
    }

    /**
     * > This function returns true if the house with the given code exists in the
     * system
     * 
     * @param codigoCasa The code of the house.
     * @return A boolean value.
     */
    public boolean existeCasa(int codigoCasa) {
        return this.casas.containsKey(codigoCasa);
    }

    /**
     * > Removes a house from the list of houses
     * 
     * @param code The code of the house to be removed.
     */
    public void removeCasa(int code) {
        this.casas.remove(code);
    }

    /**
     * Adds a Fatura to the map of Faturas.
     * 
     * @param f Fatura object
     */
    public void addFaturaToMap(Fatura f) {
        this.faturas.put(f.getCode(), f.clone());
    }

}