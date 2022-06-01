import java.util.Map;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class CasaInteligente implements Serializable {

    /**
    * Os atributos da classe
    */ 
    private static int counter = 0;
    private String nomeProprietario;
    private int nifProprietario;
    private String morada;
    private int code;
    private int codeFornecedor;
    private ArrayList<Integer> codigosDeFaturas;
    private Map<String, Map<Integer, SmartDevices>> divisoes;

    // Constructor
    public CasaInteligente() {
        this.nomeProprietario = "";
        this.nifProprietario = 0;
        this.morada = "";
        this.code = 0;
        this.codeFornecedor = 0;
        this.codigosDeFaturas = new ArrayList<Integer>();
        this.divisoes = new HashMap<>();
    }

    // Construtor
    public CasaInteligente(String nomeProprietario, int nifProprietario, String morada, int codeFornecedor) {
        this.nomeProprietario = nomeProprietario;
        this.nifProprietario = nifProprietario;
        this.morada = morada;
        this.code = counter++;
        this.codeFornecedor = codeFornecedor;
        this.codigosDeFaturas = new ArrayList<Integer>();
        this.divisoes = new HashMap<>();
    }

    // Construtor
    public CasaInteligente(CasaInteligente ci) {
        setMorada(ci.getMorada());
        setNomeProprietario(ci.getNomeProprietario());
        setNifProprietario(ci.getNifProprietario());
        setCode(ci.getCode());
        setCodeFornecedor(ci.getCodeFornecedor());
        setDivisoes(ci.getDivisoes());
        setCodigoFaturas(ci.getCodigosDeFaturas());
    }

    /**
     * getter do nome do proprietario
     * 
     * @return nome do proprietario
     */
    public String getNomeProprietario() {
        return this.nomeProprietario;
    }

    /**
     * getter do nif do proprietario
     * 
     * @return nifProprietario
     */
    public int getNifProprietario() {
        return this.nifProprietario;
    }

    /**
     * metodo que implementa o getter da morada
     * 
     * @return morada
     */
    public String getMorada() {
        return this.morada;
    }

    /**
     * implementacao do getter dos codigos de faturas
     * 
     * @return codigos de fatura
     */
    public ArrayList<Integer> getCodigosDeFaturas() {
        return this.codigosDeFaturas.stream().collect(Collectors.toCollection(ArrayList::new)); // cuidado!
    }

    /**
     * getter do codigo
     * 
     * @return codigo
     */
    public int getCode() {
        return this.code;
    }

    /**
    *getter do codigo do fornecedor
     */

    public int getCodeFornecedor() {
        return codeFornecedor;
    }

    /**
     *getter das divisoes da casa
     * 
     * @return map de divisoes
     */
    public Map<String, Map<Integer, SmartDevices>> getDivisoes() {
        return this.divisoes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Setter do nome do proprietario
     * 
     * @param nomeProprietario nome do proprietario
     */
    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    /**
     * implementacao do setter do nif do proprietario
     * 
     * @param nifProprietario nif do dono
     */
    public void setNifProprietario(int nifProprietario) {
        this.nifProprietario = nifProprietario;
    }

    /**
     * implememtacao do setter da morada
     * 
     * @param morada morada
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * implememtacao do setter do codigo
     * 
     * @param code codigo
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
    *implememtacao do setter do codigo de fornecedor
    *
    *@param codeFornecedor codigo do fornecedor
     */

    public void setCodeFornecedor(int codeFornecedor) {
        this.codeFornecedor = codeFornecedor;
    }

    /**
     * implementacao do setter dos codigos de fatura
     * 
     * @param codigosDeFaturas codigos de fatura
     */
    public void setCodigoFaturas(ArrayList<Integer> codigosDeFaturas) {
        codigosDeFaturas.forEach(c -> this.codigosDeFaturas.add(c));
    }

    /**
     * implementacao do setter das divisoes
     * 
     * @param divisoes Map<String, Map<Integer, SmartDevices>>
     */
    public void setDivisoes(Map<String, Map<Integer, SmartDevices>> divisoes) {
        this.divisoes = new HashMap<>();
        divisoes.entrySet().stream().forEach(e -> this.divisoes.put(e.getKey(), e.getValue()));
    }

    /**
     * implementacao do equals()
     * 
     * @param o objeto a ser comparado
     * @return hashcode
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CasaInteligente that = (CasaInteligente) o;
        return nifProprietario == that.nifProprietario && nomeProprietario.equals(that.nomeProprietario)
                && morada.equals(that.morada) && codigosDeFaturas.equals(that.codigosDeFaturas) && code == that.code
                && codeFornecedor == that.codeFornecedor
                && divisoes.equals(that.divisoes);
    }

    /**
     * implementacao do metodo toString
     * 
     * @return concatenacao
     */
    @Override
    public String toString() {
        return "CasaInteligente{" +
                "nomeProprietario='" + nomeProprietario + '\'' +
                ", nifProprietario=" + nifProprietario +
                ", morada='" + morada + '\'' +
                ", code=" + code +
                ", codeFornecedor=" + codeFornecedor +
                ", codigosDeFaturas=" + codigosDeFaturas +
                '}';
    }

    /**
     * implementacao do clone()
     * 
     * @return nova casa inteligente mas com os atributos iguais a clonada
     */
    public CasaInteligente clone() {
        return new CasaInteligente(this);
    }

    /**
     * Verifica se existe uma lampada com esse id
     * 
     * @param key id da lampada
     * @return boolean
     */
    public boolean existeLuz(int key) {
        return this.divisoes.entrySet().stream()
                .anyMatch(divisao -> divisao.getValue().containsKey(key) && divisao.getValue().get(key).isBulb());
    }

    /**
     * Verifica se existe um speaker com esse id
     * 
     * @param key id do speaker
     * @return boolean
     */
    public boolean existeSpeaker(int key) {
        return this.divisoes.entrySet().stream()
                .anyMatch(divisao -> divisao.getValue().containsKey(key) && divisao.getValue().get(key).isSpeaker());
    }

    /**
     * Verifica se existe uma camera com esse id
     * 
     * @param key o id da camera
     * @return boolean
     */
    public boolean existeCamera(int key) {
        return this.divisoes.entrySet().stream()
                .anyMatch(divisao -> divisao.getValue().containsKey(key) && divisao.getValue().get(key).isCamera());
    }

    /**
     * Verifica se existe uma divisao consoante um id
     * 
     * @param key o id
     * @return boolean
     */
    public boolean existeDivisao(String key) {
        return this.divisoes.containsKey(key);
    }

    /**
     * Adiciona uma nova divisao a casa
     * 
     * @param divisaokey Nome da divisao
     * @param devices    map de dispositivos que estarao na divisao
     */
    public void addDivisao(String divisaokey, Map<Integer, SmartDevices> devices) {
        this.divisoes.put(divisaokey, devices);
    }

    /**
     * remove determinada divisao
     * 
     * @param key parametro identificativo da divisao
     */
    public void removeDivisao(String key) {
        this.divisoes.remove(key);
    }

    /**
     * Verifica se um determinado dispositivo existe numa divisao
     * 
     * @param divisaokey nome da divisao
     * @param id         id do device
     * @return boolean
     */
    public boolean existeDeviceInDivisao(String divisaokey, int id) {
        return this.divisoes.get(divisaokey).containsKey(id);
    }

    /**
     * Adiciona um dispositivo a uma divisao
     * 
     * @param nomeDivisao o nome da divisao a qual vamos adicionar o dispositivo.
     * @param device      o dispositivo a ser adicionado.
     */
    public void addDeviceToDivisao(String nomeDivisao, SmartDevices device) {
        this.divisoes.get(nomeDivisao).put(device.getId(), device.clone());
    }

    /**
     * Remover um dispositivo de uma divisão
     * 
     * @param divisaokey identificador da divisao
     * @param id         id do device
     */
    public void removeDeviceFromDivisao(String divisaokey, int id) {
        this.divisoes.get(divisaokey).remove(id);
    }

    /**
     * Soma total dos consumos diarios da casa(indo por divisoes e dispositivos)
     * 
     * @return Soma da energia diaria consumida
     */
    public double energiaTotalDiariaCasa() {
        return this.divisoes.values().stream()
                .mapToDouble(divisao -> divisao.values().stream().mapToDouble(device -> device.consumoDiario()).sum())
                .sum();
    }

    /**
     * Adiciona uma nova fatura com um determinado codigo 
     * 
     * @param codeFatura O código da fatura
     */
    public void addFatura(int codeFatura) {
        this.codigosDeFaturas.add(codeFatura);
    }

}