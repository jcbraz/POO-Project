/**
 * This class represents a smart house, which has a name, an address, a list of smart devices, and a
 * list of bills
 */
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;

public class CasaInteligente {

    private static int counter = 0;
    private String nomeProprietario;
    private int nifProprietario;
    private String morada;
    private int code;
    private ArrayList<Integer> codigosDeFaturas; // CODIGOS DE ACESSO FATURAS DA CASA QUE SERÃO USADAS NO MAP PRESENTE NO
                                           // ARTICULADOR (CONVEM GUARDAR COPIA NO FORNECEDOR)
    private Map<String, Map<Integer, SmartDevices>> divisoes;

    public CasaInteligente() {
        this.nomeProprietario = "";
        this.nifProprietario = 0;
        this.morada = "";
        this.code = 0;
        this.codigosDeFaturas = new ArrayList<Integer>();
        this.divisoes = new HashMap<>();
    }

    public CasaInteligente(String nomeProprietario, int nifProprietario, String morada) {
        this.nomeProprietario = nomeProprietario;
        this.nifProprietario = nifProprietario;
        this.morada = morada;
        this.code = counter++;
        this.codigosDeFaturas = new ArrayList<Integer>();
        this.divisoes = new HashMap<>();
    }

    public CasaInteligente(CasaInteligente ci) {
        setMorada(ci.getMorada());
        setNomeProprietario(ci.getNomeProprietario());
        setNifProprietario(ci.getNifProprietario());
        setCode(ci.getCode());
        setDivisoes(ci.getDivisoes());
        setCodigoFaturas(ci.getCodigosDeFaturas());
    }

    public String getNomeProprietario() {
        return this.nomeProprietario;
    }

    public int getNifProprietario() {
        return this.nifProprietario;
    }

    public String getMorada() {
        return this.morada;
    }

    public ArrayList<Integer> getCodigosDeFaturas() {
        return this.codigosDeFaturas;
    }

    public int getCode() {
        return this.code;
    }

    public Map<String, Map<Integer, SmartDevices>> getDivisoes() {
        return this.divisoes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public void setNifProprietario(int nifProprietario) {
        this.nifProprietario = nifProprietario;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setCodigoFaturas(ArrayList<Integer> codigosDeFaturas) {
        this.codigosDeFaturas = new ArrayList<Integer>();
        codigosDeFaturas.forEach(c -> this.codigosDeFaturas.add(c));
    }

    public void setDivisoes(Map<String, Map<Integer, SmartDevices>> divisoes) {
        this.divisoes = new HashMap<>();
        divisoes.entrySet().stream().forEach(e -> this.divisoes.put(e.getKey(), e.getValue()));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CasaInteligente that = (CasaInteligente) o;
        return nifProprietario == that.nifProprietario && nomeProprietario.equals(that.nomeProprietario)
                && morada.equals(that.morada) && codigosDeFaturas.equals(that.codigosDeFaturas)
                && divisoes.equals(that.divisoes);
    }

    @Override
    public String toString() {
        return "CasaInteligente{" +
                "nomeProprietario='" + nomeProprietario + '\'' +
                ", nifProprietario=" + nifProprietario +
                ", morada='" + morada + '\'' +
                ", code=" + code +
                ", codigosDeFaturas=" + codigosDeFaturas +
                '}';
    }

    public CasaInteligente clone() {
        return new CasaInteligente(this);
    }

    public boolean existeLuz(int key) {
        return this.divisoes.entrySet().stream()
                .anyMatch(divisao -> divisao.getValue().containsKey(key) && divisao.getValue().get(key).isBulb());
    }

    public boolean existeSpeaker(int key) {
        return this.divisoes.entrySet().stream()
                .anyMatch(divisao -> divisao.getValue().containsKey(key) && divisao.getValue().get(key).isSpeaker());
    }

    public boolean existeCamera(int key) {
        return this.divisoes.entrySet().stream()
                .anyMatch(divisao -> divisao.getValue().containsKey(key) && divisao.getValue().get(key).isCamera());
    }

    public boolean existeDivisao(String key) {
        return this.divisoes.containsKey(key);
    }

    public void addDivisao(String divisaokey, Map<Integer, SmartDevices> devices) {
        this.divisoes.put(divisaokey, devices);
    }

    public void removeDivisao(String key) {
        this.divisoes.remove(key);
    }

    public void setAllLuzesON() {
        this.divisoes.entrySet().stream().filter(
                divisao -> divisao.getValue().entrySet().stream().anyMatch(device -> device.getValue().isBulb()))
                .forEach(
                        divisao -> divisao.getValue().entrySet().stream().forEach(device -> device.getValue().setON()));
    }

    public void setAllLuzesOFF() {
        this.divisoes.entrySet().stream().filter(
                divisao -> divisao.getValue().entrySet().stream().anyMatch(device -> device.getValue().isBulb()))
                .forEach(divisao -> divisao.getValue().entrySet().stream()
                        .forEach(device -> device.getValue().setOFF()));
    }

    public void setAllSpeakersON() {
        this.divisoes.entrySet().stream().filter(
                divisao -> divisao.getValue().entrySet().stream().anyMatch(device -> device.getValue().isSpeaker()))
                .forEach(
                        divisao -> divisao.getValue().entrySet().stream().forEach(device -> device.getValue().setON()));
    }

    public void setAllSpeakersOFF() {
        this.divisoes.entrySet().stream().filter(
                divisao -> divisao.getValue().entrySet().stream().anyMatch(device -> device.getValue().isSpeaker()))
                .forEach(divisao -> divisao.getValue().entrySet().stream()
                        .forEach(device -> device.getValue().setOFF()));
    }

    public void setAllCamerasON() {
        this.divisoes.entrySet().stream().filter(
                divisao -> divisao.getValue().entrySet().stream().anyMatch(device -> device.getValue().isCamera()))
                .forEach(
                        divisao -> divisao.getValue().entrySet().stream().forEach(device -> device.getValue().setON()));
    }

    public void setAllCamerasOFF() {
        this.divisoes.entrySet().stream().filter(
                divisao -> divisao.getValue().entrySet().stream().anyMatch(device -> device.getValue().isCamera()))
                .forEach(divisao -> divisao.getValue().entrySet().stream()
                        .forEach(device -> device.getValue().setOFF()));
    }

    public void setAllDevicesON() {
        this.divisoes.entrySet()
                .forEach(divisao -> divisao.getValue().entrySet().forEach(device -> device.getValue().setON()));
    }

    public void setAllDevicesOFF() {
        this.divisoes.entrySet()
                .forEach(divisao -> divisao.getValue().entrySet().forEach(device -> device.getValue().setOFF()));
    }

    public boolean existeDeviceInDivisao(String divisaokey, int id) {
        return this.divisoes.get(divisaokey).containsKey(id);
    }

    public void addDeviceToDivisao(String nomeDivisao, SmartDevices device) {
        this.divisoes.get(nomeDivisao).put(device.getId(), device);
    }

    public void removeDeviceFromDivisao(String divisaokey, int id) {
        this.divisoes.get(divisaokey).remove(id);
    }

    public void turnOnSpecificDevice(int id) {
        this.divisoes.entrySet().stream().filter(divisao -> divisao.getValue().containsKey(id))
                .forEach(divisao -> divisao.getValue().get(id).setON());
    }

    public void turnOffSpecificDevice(int id) {
        this.divisoes.entrySet().stream().filter(divisao -> divisao.getValue().containsKey(id))
                .forEach(divisao -> divisao.getValue().get(id).setOFF());
    }

    public double energiaTotalDiariaCasa() {
        return this.divisoes.values().stream()
                .mapToDouble(divisao -> divisao.values().stream().mapToDouble(device -> device.consumoDiario()).sum())
                .sum();
    }

    public void addFatura(int codeFatura) {
        this.codigosDeFaturas.add(codeFatura);
    }

}