import java.util.Map;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class CasaInteligente implements Serializable {

    // The class attributes.
    private static int counter = 0;
    private String nomeProprietario;
    private int nifProprietario;
    private String morada;
    private int code;
    private ArrayList<Integer> codigosDeFaturas; // CODIGOS DE ACESSO FATURAS DA CASA QUE SERÃO USADAS NO MAP PRESENTE
                                                 // NO
    // ARTICULADOR (CONVEM GUARDAR COPIA NO FORNECEDOR)
    private Map<String, Map<Integer, SmartDevices>> divisoes;

    // A constructor.
    public CasaInteligente() {
        this.nomeProprietario = "";
        this.nifProprietario = 0;
        this.morada = "";
        this.code = 0;
        this.codigosDeFaturas = new ArrayList<Integer>();
        this.divisoes = new HashMap<>();
    }

    // A constructor.
    public CasaInteligente(String nomeProprietario, int nifProprietario, String morada) {
        this.nomeProprietario = nomeProprietario;
        this.nifProprietario = nifProprietario;
        this.morada = morada;
        this.code = counter++;
        this.codigosDeFaturas = new ArrayList<Integer>();
        this.divisoes = new HashMap<>();
    }

    // A copy constructor.
    public CasaInteligente(CasaInteligente ci) {
        setMorada(ci.getMorada());
        setNomeProprietario(ci.getNomeProprietario());
        setNifProprietario(ci.getNifProprietario());
        setCode(ci.getCode());
        setDivisoes(ci.getDivisoes());
        setCodigoFaturas(ci.getCodigosDeFaturas());
    }

    /**
     * This function returns the name of the owner of the Property
     * 
     * @return The name of the owner of the Property.
     */
    public String getNomeProprietario() {
        return this.nomeProprietario;
    }

    /**
     * > This function returns the nifProprietario of the object
     * 
     * @return The nifProprietario
     */
    public int getNifProprietario() {
        return this.nifProprietario;
    }

    /**
     * This function returns the value of the variable morada.
     * 
     * @return The address of the client.
     */
    public String getMorada() {
        return this.morada;
    }

    /**
     * This function returns an ArrayList of Integers that contains the codes of the
     * invoices
     * 
     * @return The method returns an ArrayList of Integers.
     */
    public ArrayList<Integer> getCodigosDeFaturas() {
        return this.codigosDeFaturas.stream().collect(Collectors.toCollection(ArrayList::new)); // cuidado!
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
     * It returns a map of the form <String, Map<Integer, SmartDevices>>, where the
     * String is the name
     * of the division and the Map<Integer, SmartDevices> is a map of the form
     * <Integer, SmartDevices>,
     * where the Integer is the id of the device and the SmartDevices is the device
     * itself
     * 
     * @return A map with the key being the name of the division and the value being
     *         a map with the key
     *         being the floor number and the value being a SmartDevices object.
     */
    public Map<String, Map<Integer, SmartDevices>> getDivisoes() {
        return this.divisoes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * This function sets the name of the owner of the Property
     * 
     * @param nomeProprietario The name of the owner of the Property.
     */
    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    /**
     * This function sets the nifProprietario variable to the value of the
     * nifProprietario parameter
     * 
     * @param nifProprietario The NIF of the owner of the Property.
     */
    public void setNifProprietario(int nifProprietario) {
        this.nifProprietario = nifProprietario;
    }

    /**
     * This function sets the morada of the object
     * 
     * @param morada The address of the client.
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * This function sets the code variable to the value of the code parameter.
     * 
     * @param code The HTTP status code.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * It creates a new ArrayList, iterates over the given ArrayList, and adds each
     * element to the new ArrayList
     * 
     * @param codigosDeFaturas ArrayList of Integers
     */
    public void setCodigoFaturas(ArrayList<Integer> codigosDeFaturas) {
        this.codigosDeFaturas = new ArrayList<Integer>();
        codigosDeFaturas.forEach(c -> this.codigosDeFaturas.add(c));
    }

    /**
     * It takes a map of maps of SmartDevices and copies it to the class's map of
     * maps of SmartDevices
     * 
     * @param divisoes Map<String, Map<Integer, SmartDevices>>
     */
    public void setDivisoes(Map<String, Map<Integer, SmartDevices>> divisoes) {
        this.divisoes = new HashMap<>();
        divisoes.entrySet().stream().forEach(e -> this.divisoes.put(e.getKey(), e.getValue()));
    }

    /**
     * If the object is the same object, return true. If the object is null or of a
     * different class,
     * return false. If the object is of the same class, compare the fields
     * 
     * @param o the object to be compared.
     * @return The hashCode of the object.
     */
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

    /**
     * The toString() method returns a string representation of the object
     * 
     * @return The toString method is being returned.
     */
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

    /**
     * The clone() method creates a new object that is a copy of the original
     * object.
     * 
     * @return A new CasaInteligente object with the same state as the original.
     */
    public CasaInteligente clone() {
        return new CasaInteligente(this);
    }

    /**
     * It returns true if there's a light bulb with the given key in any of the
     * rooms
     * 
     * @param key The key of the bulb to be checked
     * @return a boolean value.
     */
    public boolean existeLuz(int key) {
        return this.divisoes.entrySet().stream()
                .anyMatch(divisao -> divisao.getValue().containsKey(key) && divisao.getValue().get(key).isBulb());
    }

    /**
     * It returns true if there is a speaker with the given key in the map
     * 
     * @param key the key of the speaker
     * @return A boolean value.
     */
    public boolean existeSpeaker(int key) {
        return this.divisoes.entrySet().stream()
                .anyMatch(divisao -> divisao.getValue().containsKey(key) && divisao.getValue().get(key).isSpeaker());
    }

    /**
     * It returns true if there is a camera with the given key in the map
     * 
     * @param key The key of the camera to be checked
     * @return A boolean value.
     */
    public boolean existeCamera(int key) {
        return this.divisoes.entrySet().stream()
                .anyMatch(divisao -> divisao.getValue().containsKey(key) && divisao.getValue().get(key).isCamera());
    }

    /**
     * It returns true if the map contains a key that matches the parameter
     * 
     * @param key The key to be searched for.
     * @return A boolean value.
     */
    public boolean existeDivisao(String key) {
        return this.divisoes.containsKey(key);
    }

    /**
     * It adds a new division to the house
     * 
     * @param divisaokey The name of the division.
     * @param devices    A map of devices, where the key is the device's ID and the
     *                   value is the device
     *                   itself.
     */
    public void addDivisao(String divisaokey, Map<Integer, SmartDevices> devices) {
        this.divisoes.put(divisaokey, devices);
    }

    /**
     * It removes the division with the given key from the list of divisions
     * 
     * @param key The key of the divisao to remove.
     */
    public void removeDivisao(String key) {
        this.divisoes.remove(key);
    }

    // For each division, if it has a bulb, turn it on.
    public void setAllLuzesON() {
        this.divisoes.entrySet().stream().filter(
                divisao -> divisao.getValue().entrySet().stream().anyMatch(device -> device.getValue().isBulb()))
                .forEach(
                        divisao -> divisao.getValue().entrySet().stream().forEach(device -> device.getValue().setON()));
    }

    // For each division, if it has a bulb, turn it off
    public void setAllLuzesOFF() {
        this.divisoes.entrySet().stream().filter(
                divisao -> divisao.getValue().entrySet().stream().anyMatch(device -> device.getValue().isBulb()))
                .forEach(divisao -> divisao.getValue().entrySet().stream()
                        .forEach(device -> device.getValue().setOFF()));
    }

    // For each division, if it has a speaker, turn it on.
    public void setAllSpeakersON() {
        this.divisoes.entrySet().stream().filter(
                divisao -> divisao.getValue().entrySet().stream().anyMatch(device -> device.getValue().isSpeaker()))
                .forEach(
                        divisao -> divisao.getValue().entrySet().stream().forEach(device -> device.getValue().setON()));
    }

    // For each division, if it has a speaker, turn it off
    public void setAllSpeakersOFF() {
        this.divisoes.entrySet().stream().filter(
                divisao -> divisao.getValue().entrySet().stream().anyMatch(device -> device.getValue().isSpeaker()))
                .forEach(divisao -> divisao.getValue().entrySet().stream()
                        .forEach(device -> device.getValue().setOFF()));
    }

    // It sets all cameras to ON
    public void setAllCamerasON() {
        this.divisoes.entrySet().stream().filter(
                divisao -> divisao.getValue().entrySet().stream().anyMatch(device -> device.getValue().isCamera()))
                .forEach(
                        divisao -> divisao.getValue().entrySet().stream().forEach(device -> device.getValue().setON()));
    }

    // It sets all cameras off
    public void setAllCamerasOFF() {
        this.divisoes.entrySet().stream().filter(
                divisao -> divisao.getValue().entrySet().stream().anyMatch(device -> device.getValue().isCamera()))
                .forEach(divisao -> divisao.getValue().entrySet().stream()
                        .forEach(device -> device.getValue().setOFF()));
    }

    // For each division, for each device, set the device to ON
    public void setAllDevicesON() {
        this.divisoes.entrySet()
                .forEach(divisao -> divisao.getValue().entrySet().forEach(device -> device.getValue().setON()));
    }

    // For each division, for each device, set the device to OFF
    public void setAllDevicesOFF() {
        this.divisoes.entrySet()
                .forEach(divisao -> divisao.getValue().entrySet().forEach(device -> device.getValue().setOFF()));
    }

    /**
     * It checks if a device exists in a division
     * 
     * @param divisaokey The key of the division
     * @param id         the id of the device
     * @return A boolean value.
     */
    public boolean existeDeviceInDivisao(String divisaokey, int id) {
        return this.divisoes.get(divisaokey).containsKey(id);
    }

    /**
     * It adds a device to a division
     * 
     * @param nomeDivisao The name of the division you want to add the device to.
     * @param device      The device to be added to the division.
     */
    public void addDeviceToDivisao(String nomeDivisao, SmartDevices device) {
        this.divisoes.get(nomeDivisao).put(device.getId(), device.clone());
    }

    /**
     * It removes a device from a division
     * 
     * @param divisaokey The key of the division you want to remove the device from.
     * @param id         the id of the device
     */
    public void removeDeviceFromDivisao(String divisaokey, int id) {
        this.divisoes.get(divisaokey).remove(id);
    }

    /**
     * For each division, if it contains the device with the given id, turn it on.
     * 
     * @param id the id of the device you want to turn on
     */
    public void turnOnSpecificDevice(int id) {
        this.divisoes.entrySet().stream().filter(divisao -> divisao.getValue().containsKey(id))
                .forEach(divisao -> divisao.getValue().get(id).setON());
    }

    /**
     * For each division, if the division contains the device with the given id,
     * turn off the device.
     * 
     * @param id The id of the device you want to turn off.
     */
    public void turnOffSpecificDevice(int id) {
        this.divisoes.entrySet().stream().filter(divisao -> divisao.getValue().containsKey(id))
                .forEach(divisao -> divisao.getValue().get(id).setOFF());
    }

    /**
     * It sums the daily consumption of all devices in all divisions of the house
     * 
     * @return The sum of the daily energy consumption of all devices in the house.
     */
    public double energiaTotalDiariaCasa() {
        return this.divisoes.values().stream()
                .mapToDouble(divisao -> divisao.values().stream().mapToDouble(device -> device.consumoDiario()).sum())
                .sum();
    }

    /**
     * Adds a new invoice code to the list of invoice codes.
     * 
     * @param codeFatura The code of the invoice to be added to the list of
     *                   invoices.
     */
    public void addFatura(int codeFatura) {
        this.codigosDeFaturas.add(codeFatura);
    }

}