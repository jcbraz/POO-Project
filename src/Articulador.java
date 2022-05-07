/**
 * It's a class that represents a system that manages houses, suppliers and bills
 */
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Articulador {

    private Map<Integer, CasaInteligente> casas;
    private Map<Integer, Fornecedor> fornecedores;
    private Map<Integer, Fatura> faturas;

    public Articulador() {
        this.casas = new HashMap<Integer, CasaInteligente>();
        this.fornecedores = new HashMap<Integer, Fornecedor>();
        this.faturas = new HashMap<Integer, Fatura>();
    }

    public Articulador(Articulador ci) {
        setCasas(ci.getCasas());
        setFornecedores(ci.getFornecedores());
        setFaturas(ci.getFaturas());
    }

    public Map<Integer, CasaInteligente> getCasas() {
        return this.casas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Fornecedor> getFornecedores() {
        return this.fornecedores.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Fatura> getFaturas() {
        return this.faturas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setCasas(Map<Integer, CasaInteligente> casas) {
        this.casas = casas;
    }

    public void setFornecedores(Map<Integer, Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public void setFaturas(Map<Integer, Fatura> faturas) {
        this.faturas = faturas;
    }

    public String toString() {
        return "CasaInteligente{" +
                ", casas=" + casas +
                ", fornecedores=" + fornecedores +
                ", faturas=" + faturas +
                '}';
    }

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

    public Articulador clone() {
        return new Articulador(this);
    }

    public void addFornecedor(Fornecedor fornecedor) {
        this.fornecedores.put(fornecedor.getCode(), fornecedor);
    }

    public boolean existeFornecedor(int codigoFornecedor) {
        return this.fornecedores.containsKey(codigoFornecedor);
    }

    public void addCasa(CasaInteligente ci) {
        this.casas.put(ci.getCode(), ci);
    }

    public boolean existeCasa(int codigoCasa) {
        return this.casas.containsKey(codigoCasa);
    }

    public void removeCasa(int code) {
        this.casas.remove(code);
    }

    public void addFaturaToMap(Fatura f) {
        this.faturas.put(f.getCode(), f);
    }

}
