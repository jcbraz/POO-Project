import java.util.Map;
import java.util.stream.Collectors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class MainMethods implements Comparator<CasaInteligente> {

    private CasaInteligente ci;
    private Fornecedor fornecedor;
    private Articulador art;
    private Map<Integer, SmartDevices> devices;

    // Creating a new instance of the class CasaInteligente, Fornecedor and
    // Articulador.
    public MainMethods() {
        this.ci = new CasaInteligente();
        this.fornecedor = new Fornecedor();
        this.art = new Articulador();
        this.devices = new HashMap<Integer, SmartDevices>();
    }

    // Métodos direcionados para manipulação de casas

    // Função que retorna a Casa Inteligente
    public CasaInteligente getCi() {
        return ci;
    }

    /**
     * This function returns the fornecedor
     * 
     * @return The fornecedor object.
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * This function returns the articulator of the current object
     * 
     * @return The Articulador object.
     */
    public Articulador getArt() {
        return art;
    }

    /**
     * The function `setCi` sets the value of the variable `ci` to the value of the
     * parameter `ci`
     * 
     * @param ci The CasaInteligente object that will be used to control the house.
     */
    public void setCi(CasaInteligente ci) {
        this.ci = ci;
    }

    /**
     * 
     * @param fornecedor The object that will be used to populate the form.
     */
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * This function sets the articulator of the current object to the articulator
     * passed as a parameter
     * 
     * @param art    The articulator object that will be used to generate the
     *               speech.
     *               /**
     *               It creates a new house and adds it to the list of houses
     * 
     * @param nome   name of the house
     * @param nif    The NIF of the house owner.
     * @param morada address
     */
    public void setArt(Articulador art) {
        this.art = art;
    }

    /**
     * It creates a new house and adds it to the list of houses
     * 
     * @param nome   name of the house
     * @param nif    The NIF of the house owner.
     * @param morada address
     */
    public void criaCasa(String nome, int nif, String morada) {
        this.setCi(new CasaInteligente(nome, nif, morada));
        this.art.addCasa(this.ci);
    }

    /**
     * This function checks if a house exists in the database
     * 
     * @param code The code of the house.
     * @return A boolean value.
     */
    public boolean existeCasa(int code) {
        return this.art.existeCasa(code);
    }

    /**
     * This function creates a new supplier and adds it to the list of suppliers of
     * the current
     * article.
     * 
     * @param nome name of the supplier
     */
    public void criaFornecedor(String nome) {
        this.setFornecedor(new Fornecedor(nome));
        this.art.addFornecedor(this.fornecedor);
    }

    /**
     * This function checks if a supplier exists in the articulator
     * 
     * @param code the code of the supplier
     * @return A boolean value.
     */
    public boolean existeFornecedoremArticulador(int code) {
        return this.art.existeFornecedor(code);
    }

    /**
     * Add a client to a supplier.
     */
    public void addCasaToFornecedor() {
        this.fornecedor.addCliente(this.ci);
    }

    public void removeCasaFromFornecedor() {
        this.fornecedor.removeCliente(this.ci);
    }

    /**
     * Returns the code of the supplier.
     * 
     * @return The code of the supplier.
     */
    public int getCodeFornecedor() {
        return this.fornecedor.getCode();
    }

    /**
     * This function returns the house with the given code.
     * 
     * @param code The code of the house you want to get.
     * @return The house with the code that was passed as a parameter.
     */
    public CasaInteligente getCiWithCode(int code) {
        return this.art.getCasas().get(code);
    }

    /**
     * It removes a house from the list of houses
     * 
     * @param code The code of the house to be removed.
     */
    public void removeCasaFromArt(int code) {
        this.art.removeCasa(code);
    }

    public int getFornecedorCodeFromCasa(CasaInteligente ci) {
        return this.art.getFornecedores().values().stream().filter(f -> f.getClientes().containsKey(ci.getCode()))
                .collect(Collectors.toList()).get(0).getCode();
    }
    // Métodos direcionados para Divisoes e Devices

    /**
     * It returns a map of maps of smart devices
     * 
     * @return A map of maps of SmartDevices.
     */
    public Map<String, Map<Integer, SmartDevices>> getDivisoes() {
        return ci.getDivisoes();
    }

    /**
     * > This function sets the devices map to the devices map passed in as a
     * parameter
     * 
     * @param devices A map of all the devices in the house. The key is the device
     *                ID and the value is
     *                the device itself.
     */
    public void setDevices(Map<Integer, SmartDevices> devices) {
        this.devices = devices;
    }

    /**
     * It returns a map of integers to SmartDevices, given a string
     * 
     * @param divisao The name of the division you want to get the devices from.
     * @return A map of integers and SmartDevices.
     */
    public Map<Integer, SmartDevices> getDivisao(String divisao) {
        return ci.getDivisoes().get(divisao);
    }

    /**
     * Get the device with the given code from the given house.
     * 
     * @param code The code of the device you want to get.
     * @return The device with the code that was passed as a parameter.
     */
    public SmartDevices getDeviceWithCode(int code) {
        return this.ci.getDivisoes().entrySet().stream().flatMap(e -> e.getValue().entrySet().stream())
                .filter(e -> e.getKey() == code).findFirst().get().getValue();
    }

    /**
     * "Get the SmartBulb with the given code from the list of SmartBulbs in the
     * house."
     * 
     * The first line of the function is the return statement. It returns a
     * SmartBulb
     * 
     * @param code The code of the bulb you want to get.
     * @return SmartBulb
     */
    public SmartBulb getBulbWithCode(int code) {
        return (SmartBulb) this.ci.getDivisoes().entrySet().stream().flatMap(e -> e.getValue().entrySet().stream())
                .filter(e -> e.getKey() == code).findFirst().get().getValue();
    }

    /**
     * Get the speaker with the given code from the list of speakers.
     * 
     * @param code The code of the speaker you want to get.
     * @return A SmartSpeaker
     */
    public SmartSpeaker getSpeakerWithCode(int code) {
        return (SmartSpeaker) this.ci.getDivisoes().entrySet().stream().flatMap(e -> e.getValue().entrySet().stream())
                .filter(e -> e.getKey() == code).findFirst().get().getValue();
    }

    /**
     * "Get the camera with the given code from the list of cameras."
     * 
     * The function is a bit more complicated than that, but it's still pretty
     * simple
     * 
     * @param code The code of the camera you want to get.
     * @return A SmartCamera
     */
    public SmartCamera getCameraWithCode(int code) {
        return (SmartCamera) this.ci.getDivisoes().entrySet().stream().flatMap(e -> e.getValue().entrySet().stream())
                .filter(e -> e.getKey() == code).findFirst().get().getValue();
    }

    /**
     * It sets the value of the attribute divisoes of the class CasaInteligente to
     * the value of the
     * parameter divisoes
     * 
     * @param divisoes A map of rooms, where each room is a map of devices, where
     *                 each device is a
     *                 SmartDevice object.
     */
    public void setDivisoes(Map<String, Map<Integer, SmartDevices>> divisoes) {
        ci.setDivisoes(divisoes);
    }

    /**
     * It creates a new division in the house
     * 
     * @param nomeDivisao The name of the division you want to create.
     * @param devices     Map<Integer, SmartDevices>
     */
    public void criaDivisao(String nomeDivisao, Map<Integer, SmartDevices> devices) {
        this.ci.addDivisao(nomeDivisao, devices);
    }

    /**
     * This function checks if a division exists in a house
     * 
     * @param nomeDivisao The name of the division to be checked.
     * @return boolean
     */
    public boolean existeDivisaoInCasa(String nomeDivisao) {
        return this.ci.existeDivisao(nomeDivisao);
    }

    /**
     * It creates a new SmartBulb object and adds it to the devices HashMap and to
     * the division HashMap
     * 
     * @param isON              boolean
     * @param tonalidade        "white" or "color"
     * @param dimensoes         the dimensions of the device
     * @param consumoDiarioBulb the daily consumption of the bulb
     * @param divisao           The name of the room where the device will be
     *                          placed.
     */
    public void criaBulb(boolean isON, String tonalidade, double dimensoes, double consumoDiarioBulb, String divisao) {
        SmartDevices device = new SmartBulb(isON, tonalidade, dimensoes, consumoDiarioBulb);
        this.devices.put(device.getId(), device);
        this.ci.addDeviceToDivisao(divisao, device);
    }

    /**
     * It creates a new SmartSpeaker object and adds it to the devices HashMap and
     * to the division it
     * belongs to
     * 
     * @param isON                 boolean
     * @param volume               0-100
     * @param nomeRadio            The name of the radio station.
     * @param marcaEquipamento     Brand of the device
     * @param consumoDiarioSpeaker the daily consumption of the speaker
     * @param divisao              The name of the room where the device will be
     *                             placed.
     */
    public void criaSpeaker(boolean isON, int volume, String nomeRadio, String marcaEquipamento,
            int consumoDiarioSpeaker, String divisao) {
        SmartDevices device = new SmartSpeaker(isON, volume, nomeRadio, marcaEquipamento, consumoDiarioSpeaker);
        this.devices.put(device.getId(), device);
        this.ci.addDeviceToDivisao(divisao, device);
    }

    /**
     * This function creates a new SmartCamera object and adds it to the devices
     * HashMap and to the
     * divisao HashMap.
     * 
     * @param isON                boolean, true if the device is on, false otherwise
     * @param resolution_x        the width of the camera's resolution
     * @param resolution_y        the height of the camera's resolution
     * @param filesize            the size of the file in MB
     * @param consumoDiarioCamera the daily consumption of the camera
     * @param divisao             The name of the room where the device will be
     *                            placed.
     */
    public void criaCamera(boolean isON, int resolution_x, int resolution_y, double filesize, int consumoDiarioCamera,
            String divisao) {
        SmartDevices device = new SmartCamera(isON, resolution_x, resolution_y, filesize, consumoDiarioCamera);
        this.devices.put(device.getId(), device);
        this.ci.addDeviceToDivisao(divisao, device);
    }

    /**
     * This function returns true if the device with the given id exists in the
     * system, and false
     * otherwise.
     * 
     * @param id The id of the device.
     * @return A boolean value.
     */
    public boolean existeDispositivo(int id) {
        return this.devices.containsKey(id);
    }

    /**
     * Removes a device from a division
     * 
     * @param divisao The name of the division you want to remove the device from.
     * @param id      the id of the device you want to remove from the divisao
     */
    public void removeDeviceFromDivisao(String divisao, int id) {
        this.ci.removeDeviceFromDivisao(divisao, id);
    }

    // STATS

    /**
     * It returns the code of the house with the highest energy consumption in the
     * entire system
     * 
     * @return The code of the house with the highest consumption in the period.
     */
    public int casaComMaiorConsumoGeralemDatas() {
        int codeCasa = 0;
        double maxConsumo = 0.00;
        for (Fornecedor fornecedor : this.art.getFornecedores().values()) {
            if (fornecedor.casaComMaiorConsumoDoFornecedor().energiaTotalDiariaCasa() > maxConsumo) {
                maxConsumo = fornecedor.casaComMaiorConsumoDoFornecedor().energiaTotalDiariaCasa();
                codeCasa = fornecedor.casaComMaiorConsumoDoFornecedor().getCode();
            }
        }
        return codeCasa;
    }

    // 2 ª stat
    // return string = code of Fornecedor
    /**
     * It iterates through all the suppliers in the system, and returns the supplier
     * with the highest
     * total sales
     * 
     * @return The code of the supplier with the highest billing.
     */
    public int fornecedorComMaiorFaturacao() {
        double maxFatura = 0.00;
        int codeFornecedor = 0;
        for (Fornecedor fornecedor : this.art.getFornecedores().values()) {
            if (fornecedor.faturacaoFornecedor() > maxFatura) {
                maxFatura = fornecedor.faturacaoFornecedor();
                codeFornecedor = fornecedor.getCode();
            }
        }
        return codeFornecedor;
    }

    // 3ª stat
    // list all faturas of a fornecedor
    /**
     * It returns a set of integers that are the codes of the invoices of a supplier
     * with a given code
     * 
     * @param code the code of the supplier
     * @return A set of integers.
     */

    public ArrayList<Integer> faturasByFornecedor(int code) {
        return this.art.getFornecedores().values().stream().filter(fornecedor -> fornecedor.getCode() == code)
                .findFirst()
                .get()
                .getCodigosDeFaturas();
    }

    // 4ª stat

    /**
     * It compares the energy consumption of two houses.
     * 
     * @param c1 The first object to be compared.
     * @param c2 The second object to be compared.
     * @return The difference between the two values.
     */
    public int compare(CasaInteligente c1, CasaInteligente c2) {
        return Double.compare(c1.energiaTotalDiariaCasa(), c2.energiaTotalDiariaCasa());
    }

    /**
     * It sorts the houses by their daily energy consumption and returns a list of
     * their codes
     * 
     * @return An ArrayList of Integers.
     */
    public ArrayList<Integer> rankCasasComMaiorConsumo() {
        ArrayList<Integer> casas = new ArrayList<Integer>();
        ArrayList<CasaInteligente> consumosByCasa = new ArrayList<CasaInteligente>();
        this.art.getFornecedores().values()
                .forEach(fornece -> fornece.getClientes().values().forEach(casa -> consumosByCasa.add(casa)));
        consumosByCasa.sort(new Comparator<CasaInteligente>() {
            public int compare(CasaInteligente ci1, CasaInteligente ci2) {
                if (ci1.energiaTotalDiariaCasa() > ci2.energiaTotalDiariaCasa()) {
                    return -1;
                } else if (ci1.energiaTotalDiariaCasa() < ci2.energiaTotalDiariaCasa()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (CasaInteligente ci : consumosByCasa) {
            casas.add(ci.getCode());
        }
        return casas;
    }

    // Avanço do tempo

    public void placeFatura(LocalDate fim, Fornecedor forneceai, int nifCliente, double consumo, double custo,
            int codigoCasa) {
        Fatura fatura = forneceai.criaFatura(nifCliente, LocalDate.now(), fim, consumo, custo, codigoCasa);
        this.art.addFaturaToMap(fatura);
        this.art.getFornecedores().get(forneceai.getCode()).addFaturaClienteAL(fatura);
    }
}