import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/*
 * Síntese de funcionalidades da classe :
 *  Estruturação dos métodos usados na linha da frente do programa na classe Main,
 *  Melhor leitura e perceção das atividades realizadas pelos métodos na class Main.
*/

public class MainMethods implements Serializable {

    /*
     * Variáveis de instância relativas à classe MainMethods.
     */

    private CasaInteligente ci;
    private Fornecedor fornecedor;
    private Articulador art;
    private Map<Integer, SmartDevices> devices;
    private ArrayDeque<SerializableConsumer> pedidos;
    private int codeDispositivo;

    /*
     * Construtor sem parâmetros.
     */
    public MainMethods() {
        this.ci = new CasaInteligente();
        this.fornecedor = new Fornecedor();
        this.art = new Articulador();
        this.devices = new HashMap<Integer, SmartDevices>();
        this.pedidos = new ArrayDeque<SerializableConsumer>();
        this.codeDispositivo = 0;
    }

    /*
     * Construtor com parâmetro do tipo da classe.
     */
    public MainMethods setMainMethods(MainMethods mainMethods) {
        this.ci = mainMethods.getCi();
        this.fornecedor = mainMethods.getFornecedor();
        this.art = mainMethods.getArt();
        return this;
    }

    /**
     * Getter de Casa Inteligente.
     * 
     * @return O objecto CasaInteligente.
     */
    public CasaInteligente getCi() {
        return ci;
    }

    /**
     * Getter de Fornecedor.
     * 
     * @return O objecto Fornecedor.
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * Getter de Dispositivos.
     * 
     * @return Map de Dispositivos.
     */

    public Map<Integer, SmartDevices> getDevices() {
        this.devices = new HashMap<Integer, SmartDevices>();
        return this.devices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Getter do Articulador (classe de relação).
     * 
     * @return O Objecto Articulador.
     */
    public Articulador getArt() {
        return art;
    }

    /**
     * Getter de Pedidos (Fila de espera).
     * 
     * @return ArrayDeque de pedidos.
     */
    public ArrayDeque<Consumer<MainMethods>> getPedidos() {
        return this.pedidos.stream().collect(Collectors.toCollection(ArrayDeque::new));
    }

    /**
     * Getter do código de um dispostivo.
     * 
     * @return Inteiro relativo ao código de dispositivo.
     */
    public int getCodeDispositivo() {
        return codeDispositivo;
    }

    /**
     * Setter para CasaInteligente.
     * 
     * @param ci Objecto CasaInteligente.
     */
    public void setCi(CasaInteligente ci) {
        this.ci = ci;
    }

    /**
     * Setter para Fornecedor
     * 
     * @param fornecedor Objecto Fornecedor.
     */
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * Setter para o Articulador (classe de relação).
     * 
     * @param art Objecto Articulador.
     */
    public void setArt(Articulador art) {
        this.art = art;
    }

    /**
     * Setter de pedidos - define uma lista de pedidos que serão executados quando o
     * utilizador decidir avançar no tempo.
     * 
     * @param pedidos Queue de pedidos.
     */

    public void setPedidos(ArrayDeque<SerializableConsumer> pedidos) {
        this.pedidos = new ArrayDeque<SerializableConsumer>();
        this.pedidos.addAll(pedidos);
    }

    /**
     * Setter de Código de Dispositivo.
     * 
     * @param codeDispositivo Novo código para o dispositivo em causa.
     */
    public void setCodeDispositivo(int codeDispositivo) {
        this.codeDispositivo = codeDispositivo;
    }

    /**
     * Gera uma nova casa com os parâmetros fornecidos pelo utilizador e adiciona ao
     * Map de Casas Inteligentes presente no articulador.
     * 
     * @param nome   Nome do Proprietário.
     * @param nif    NIF do Proprietário.
     * @param morada Morada do Proprietário.
     */
    public void criaCasa(String nome, int nif, String morada, int codeFornecedor) {
        this.setCi(new CasaInteligente(nome, nif, morada, codeFornecedor));
        this.art.addCasa(this.ci);
    }

    /**
     * Evoca o método presente no Articulador de verificação de existência de uma
     * Casa Inteligente no Map presente no Articulador
     * 
     * @param code código da casa.
     * @return A boolean value.
     */
    public boolean existeCasa(int code) {
        return this.art.existeCasa(code);
    }

    /**
     * Gera um novo fornecedor com o parâmetro fornecido e adiciona o ao Map de
     * Fornecedores presente no Articulador.
     * 
     * @param nome Nome da Empresa fornecedora de energia.
     */
    public void criaFornecedor(String nomeFornecedor) {
        Fornecedor temp = new Fornecedor();
        temp.setNomeFornecedor(nomeFornecedor);
        this.art.addFornecedor(temp);
    }

    /**
     * Evoca o método de verificação da existência de um dado fornecedor no Map de
     * Fornecedores presente no Articulador.
     * 
     * @param code código do Fornecedor.
     * @return A boolean value.
     */
    public boolean existeFornecedor(int code) {
        return this.art.existeFornecedor(code);
    }

    /**
     * Adiciona uma Casa Inteligente ao Map de Clientes de um dado Fornecedor.
     * 
     * @param codeFornecedor código do Fornecedor.
     * @return código da casa adicionada.
     */
    public int addCasaToFornecedor(int codeFornecedor) {
        this.art.getFornecedores().get(codeFornecedor).addCliente(this.ci);
        return this.ci.getCode();
    }

    /**
     * Remove uma Casa Inteligente do Map de Cliente de um dado Fornecedor.
     */
    public void removeCasaFromFornecedor() {
        this.fornecedor.removeCliente(this.ci);
    }

    /**
     * Getter do código do fornecedor evocado
     * 
     * @return código do fornecedor.
     */
    public int getCodeFornecedor() {
        return this.fornecedor.getCode();
    }

    /**
     * Getter de uma Casa Inteligente partindo de um código único (método de
     * simplificação).
     * 
     * @param code código único de uma dada Casa Inteligente
     * @return A casa respetiva ao código fornecido.
     */
    public CasaInteligente getCiWithCode(int code) throws CasaInexistenteException {
        CasaInteligente casa = this.art.getCasas().get(code);
        if (casa == null)
            throw new CasaInexistenteException("Não existe casa com esse código");
        return casa;
    }

    /**
     * Remove Casa Inteligente do Map de Casas presente no Articulador.
     * 
     * @param code código único da casa a remover.
     */
    public void removeCasaFromArt(int code) {
        this.art.removeCasa(code);
    }

    /**
     * Getter das divisões presentes.
     * 
     * @return Map de Divisões em que cada divisão possui um Map de SmartDevices.
     */
    public Map<String, Map<Integer, SmartDevices>> getDivisoes() throws DivisoesException {
        Map<String, Map<Integer, SmartDevices>> divisoes = ci.getDivisoes();
        if (divisoes == null)
            throw new DivisoesException("Nao existe essa divisão");
        return divisoes;
    }

    /**
     * Setter do Map de Devices.
     * 
     * @param devices Map de Integer(id do device) - SmartDevices
     */
    public void setDevices(Map<Integer, SmartDevices> devices) {
        this.devices = new HashMap<>();
        devices.entrySet().forEach(device -> this.devices.put(device.getKey(), device.getValue().clone()));
    }

    /**
     * Getter de um Map respetivo a uma determinada divisão.
     * 
     * @param divisao Nome da divisão.
     * @return Map de Inteiros e SmartDevices respetivos a uma divisão.
     */
    public Map<Integer, SmartDevices> getDivisao(String divisao) throws DivisoesException {
        Map<Integer, SmartDevices> divisoes = ci.getDivisoes().get(divisao);
        if (divisoes == null)
            throw new DivisoesException("Nao existe essa divisao");
        return divisoes;
    }

    /**
     * Getter de um SmartDevice específico
     * 
     * @param code código do dispositivo.
     * @return dispositivo corresponde ao código único presente no Map de Divisões.
     */
    public SmartDevices getDeviceWithCode(int code) {
        SmartDevices devices = this.ci.getDivisoes().entrySet().stream().flatMap(e -> e.getValue().entrySet().stream())
                .filter(e -> e.getKey() == code).findFirst().get().getValue();
        return devices;
    }

    /**
     * Getter de um SmartDevice tipo SmartBulb específico.
     * 
     * @param code código do dispositivo.
     * @return dispositivo corresponde ao código único presente no Map de Divisões.
     */
    public SmartBulb getBulbWithCode(int code) {
        SmartBulb lampada = (SmartBulb) this.ci.getDivisoes().entrySet().stream()
                .flatMap(e -> e.getValue().entrySet().stream())
                .filter(e -> e.getKey() == code).findFirst().get().getValue();
        return lampada;

    }

    /**
     * Getter de um SmartDevice tipo SmartSpeaker específico.
     * 
     * @param code código do dispositivo.
     * @return dispositivo corresponde ao código único presente no Map de Divisões.
     */
    public SmartSpeaker getSpeakerWithCode(int code) {
        SmartSpeaker speaker = (SmartSpeaker) this.ci.getDivisoes().entrySet().stream()
                .flatMap(e -> e.getValue().entrySet().stream())
                .filter(e -> e.getKey() == code).findFirst().get().getValue();
        return speaker;
    }

    /**
     * Getter de um SmartDevice tipo SmartCamera específico.
     * 
     * @param code código do dispositivo.
     * @return dispositivo corresponde ao código único presente no Map de Divisões.
     */
    public SmartCamera getCameraWithCode(int code) {
        SmartCamera camera = (SmartCamera) this.ci.getDivisoes().entrySet().stream()
                .flatMap(e -> e.getValue().entrySet().stream())
                .filter(e -> e.getKey() == code).findFirst().get().getValue();
        return camera;
    }

    /**
     * Evocação do Setter de Divisões presente na classe CasaInteligente (Setter de
     * Divisões (Map)).
     * 
     * @param divisoes Map de Nome de divisão com Map de Inteiros e SmartDevices.
     */
    public void setDivisoes(Map<String, Map<Integer, SmartDevices>> divisoes) {
        ci.setDivisoes(divisoes);
    }

    /**
     * Cria uma nova divisão a adiciona a ao Map de divisões
     * 
     * @param nomeDivisao Nome da nova divisão.
     * @param devices     SmartDevices presentes na nova divisão.
     */
    public void criaDivisao(int codeCasa, String nomeDivisao, Map<Integer, SmartDevices> devices) {
        this.art.getCasas().get(codeCasa).addDivisao(nomeDivisao, devices);
    }

    /**
     * Verifica se existe uma dada divisão numa casa.
     * 
     * @param nomeDivisao nome da divisão.
     * @return boolean
     */
    public boolean existeDivisaoInCasa(String nomeDivisao) {
        return this.ci.existeDivisao(nomeDivisao);
    }

    /**
     * Criação de um SmartDevice tipo SmartBulb e posterior adição ao Map de
     * dispositivos de uma dada divisão.
     * 
     * @param isON              "ligado?"
     * @param custoInstalacao   Custo de instalação do dispositivo.
     * @param tonalidade        Tonalidade(Cold,Neutral,Warm).
     * @param dimensoes         Dimensões do dispositivo.
     * @param consumoDiarioBulb Consumo diário do dispositivo
     * @param codecasa          Codigo da casa para que se destina.
     * @param divisao           Nome da divisão a adicionar
     * 
     */
    public void criaBulb(boolean isON, float custoInstalacao, String tonalidade, double dimensoes,
            float consumoDiarioBulb, int codecasa, String divisao) {
        SmartDevices device = new SmartBulb(isON, custoInstalacao, tonalidade, dimensoes, consumoDiarioBulb);
        this.art.getCasas().get(codecasa).getDivisoes().get(divisao).put(device.getId(), device.clone());
        ;
    }

    /**
     * Criação de um SmartDevice tipo SmartSpeaker e posterior adição ao Map de
     * dispositivos de uma dada divisão.
     * 
     * @param isON                 "ligado?"
     * @param custoInstalacao      Custo de instalação do dispositivo.
     * @param volume               Volume do dispostivo.
     * @param nomeRadio            Nome da rádio atual.
     * @param marcaEquimento       Marca do equipamento.
     * @param consumoDiarioSpeaker Consumo diário de energia.
     * @param codecasa             Codigo da casa para que se destina.
     * @param divisao              Nome da divisão a adicionar.
     * 
     */
    public void criaSpeaker(boolean isON, float custoInstalacao, int volume, String nomeRadio, String marcaEquipamento,
            float consumoDiarioSpeaker, int codecasa, String divisao) {
        SmartDevices device = new SmartSpeaker(isON, custoInstalacao, volume, nomeRadio, marcaEquipamento,
                consumoDiarioSpeaker);
        this.art.getCasas().get(codecasa).getDivisoes().get(divisao).put(device.getId(), device.clone());
    }

    /**
     * Criação de um SmartDevice tipo SmartCamera e posterior adição ao Map de
     * dispositivos de uma dada divisão.
     * 
     * @param isON                "ligado?".
     * @param custoInstalacao     Custo de instalação do dispositivo.
     * @param resolution_x        Parâmetro 1 da resolução.
     * @param resolution_Y        Parâmetro 2 da resolução.
     * @param filesize            Tamanho do ficheiro de gravação.
     * @param consumoDiarioCamera Consumo diário de energia.
     * @param codecasa            Codigo da casa para que se destina.
     * @param divisao             Nome da divisão a adicionar.
     * 
     */
    public void criaCamera(boolean isON, float custoInstalacao, int resolution_x, int resolution_y, double filesize,
            float consumoDiarioCamera, int codecasa, String divisao) {
        SmartDevices device = new SmartCamera(isON, custoInstalacao, resolution_x, resolution_y, filesize,
                consumoDiarioCamera);
        this.art.getCasas().get(codecasa).getDivisoes().get(divisao).put(device.getId(), device.clone());
    }

    /**
     * Evoca o método presente na classe CasaInteligente para remover um dado
     * dispositivo de uma dada divisão
     * 
     * @param divisao nome da divisão.
     * @param id      código do dispositivo.
     */
    public void removeDeviceFromDivisao(String divisao, int id) {
        this.ci.removeDeviceFromDivisao(divisao, id);
    }

    /**
     * Adiciona um pedido à Queue de pedidos local.
     * 
     * @param pedido Pedido a adicionar.
     * @throws PedidoException
     */
    public void addPedido(SerializableConsumer pedido) throws PedidoException {
        this.pedidos.add(pedido);
    }

    /**
     * Executa todos os pedidos presentes na Queue local.
     * 
     * @throws PedidoException
     */
    public void execPedidos() throws PedidoException {
        if (this.pedidos.isEmpty()) {
            throw new PedidoException("Não existem mais pedidos para executar");
        } else {
            for (Consumer<MainMethods> pedido : this.pedidos) {
                pedido.accept(this);
                removePedido(pedido);
            }
        }
    }

    /**
     * Remove um pedido da Queue local.
     * 
     * @param pedido Pedido a remover.
     */
    public void removePedido(Consumer<MainMethods> pedido) throws PedidoException {
        if (!this.pedidos.contains(pedido)) {
            Exception e = new Exception("Pedido não existe");
            throw new PedidoException(e.getMessage());
        } else {
            this.pedidos.remove(pedido);
        }
    }

    /**
     * Indica a casa com maior consumo de um dado fornecedor (auxiliar).
     * 
     * @param codigoFornecedor Código do fornecedor.
     */
    private CasaInteligente casaComMaiorConsumoDoFornecedor(int codigoFornecedor) {
        double maiorConsumo = 0.00;
        CasaInteligente casaComMaiorConsumo = new CasaInteligente();
        for (int codigo : this.art.getFornecedores().get(codigoFornecedor).getCodigoClientes()) {
            if (this.art.getCasas().get(codigo).energiaTotalDiariaCasa() > maiorConsumo) {
                maiorConsumo = this.art.getCasas().get(codigo).energiaTotalDiariaCasa();
                casaComMaiorConsumo = this.art.getCasas().get(codigo);
            }
        }
        return casaComMaiorConsumo;
    }

    /**
     * Indica a casa com maior consumo de todas as casas num dado período de tempo
     * (stat nº1).
     * 
     * @return Casa com maior consumo num dado período de tempo.
     */
    public int casaComMaiorConsumoGeralemDatas() {
        int codeCasa = 0;
        double maxConsumo = 0.00;
        for (Fornecedor fornecedor : this.art.getFornecedores().values()) {
            if (this.casaComMaiorConsumoDoFornecedor(fornecedor.getCode()).energiaTotalDiariaCasa() > maxConsumo) {
                maxConsumo = this.casaComMaiorConsumoDoFornecedor(fornecedor.getCode()).energiaTotalDiariaCasa();
                codeCasa = this.casaComMaiorConsumoDoFornecedor(fornecedor.getCode()).getCode();
            }
        }
        return codeCasa;
    }

    /**
     * Método que calcula a faturação total de um dado fornecedor num dado período
     * (auxiliar).
     * 
     * @return valor faturado.
     */
    private double faturacaoFornecedor() {
        double valorTotal = 0.00;
        for (CasaInteligente casa : this.art.getCasas().values()) {
            valorTotal += (casa.energiaTotalDiariaCasa() * this.fornecedor.getValorBase() * this.fornecedor.getTax())
                    * 0.9;
        }
        return valorTotal;
    }

    /**
     * Indica o fornecedor existente com maior faturação (stat nº2).
     * 
     * @return código do fornecedor com maior faturação.
     */
    public int fornecedorComMaiorFaturacao() {
        double maxFatura = 0.00;
        int codeFornecedor = 0;
        for (Fornecedor fornecedor : this.art.getFornecedores().values()) {
            if (this.faturacaoFornecedor() > maxFatura) {
                maxFatura = this.faturacaoFornecedor();
                codeFornecedor = fornecedor.getCode();
            }
        }
        return codeFornecedor;
    }

    /**
     * Apresenta uma lista com todas as faturas de um dado fornecedor.
     * 
     * @param code código do fornecedor.
     * @return Lista de faturas.
     */
    public ArrayList<Integer> faturasByFornecedor(int code) {
        return this.art.getFornecedores().values().stream().filter(fornecedor -> fornecedor.getCode() == code)
                .findFirst()
                .get()
                .getCodigosDeFaturas();
    }

    /**
     * Apresenta no terminal uma lista com os códigos das faturas de uma dada casa.
     * 
     * @param code código da casa.
     * @return Lista de códigos das faturas.
     */
    public ArrayList<Integer> printsFaturasCodes(int codeCasa) {
        return this.art.getCasas().get(codeCasa).getCodigosDeFaturas();
    }

    /**
     * Apresenta no terminal um layout visual com os dados de uma dada fatura.
     * 
     * @param code código da fatura.
     */
    public void printsFatura(int codeFatura) {
        Fatura fatura = this.art.getFaturas().get(codeFatura);
        System.out.println("Código de Fornecedor: " + fatura.getFornecedor() + "\n" +
                "Código de Casa: " + fatura.getCodigoCasa() + "\n" +
                "NIF do Cliente: " + fatura.getNifCliente() + "\n" +
                "Código de Fatura: " + fatura.getCode() + "\n" +
                "Perído de Recolha de Dados: \n\n" +
                "Data Inicial: " + fatura.getDateInicio() + "\n" +
                "Data Final: " + fatura.getDateFim() + "\n\n" +
                "Energia Consumida: " + fatura.getConsumo() + "\n\n" +
                "Custo Total: " + fatura.getCusto() + "\n\n");
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
     * Valida a veracidade de duas datas (auxiliar).
     * 
     * @param dataInicio Data inicial.
     * @param dataFim    Data final.
     * @param f          Fatura em causa.
     * @return Verdadeiro se as datas forem válidas.
     */
    private static boolean validateFatura(LocalDate dateInicio, LocalDate dateFim, Fatura f) {
        if (f.getDateInicio().isAfter(dateInicio) && f.getDateFim().isBefore(dateFim)
                || f.getDateInicio().isEqual(dateInicio) && f.getDateFim().isEqual(dateFim)
                || f.getDateInicio().isAfter(dateInicio) && f.getDateFim().isEqual(dateFim)
                || f.getDateInicio().isEqual(dateInicio) && f.getDateFim().isAfter(dateFim)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Cria uma nova fatura tendo em conta o intervalo de tempo entre as duas datas.
     * 
     * @param nifCliente     NIF do cliente.
     * @param dateinicio     data inicial.
     * @param datefim        data final.
     * @param consumo        consumo energia.
     * @param custo          custo total.
     * @param codigoCasa     código da casa.
     * @param codeFornecedor código do fornecedor.
     * @return Fatura com os respetivos dados.
     */
    public Fatura criaFatura(int nifCliente, LocalDate dateinicio, LocalDate datefim, double consumo, double custo,
            int codigoCasa, int codeFornecedor) {
        double consumoTotal = this.art.getCasas().values().stream().mapToDouble(c -> c.energiaTotalDiariaCasa()).sum();
        int days = (int) ChronoUnit.DAYS.between(dateinicio, datefim);
        // int codeFornecedor = this.art.getCasas().get(codigoCasa).getCodeFornecedor();
        double custoTotal = this.art.getFornecedores().get(codeFornecedor)
                .precoTotalDiarioCliente(this.art.getCasas().get(codigoCasa)) * days;
        return new Fatura(dateinicio, datefim, codeFornecedor, nifCliente, consumoTotal, custoTotal, codigoCasa);
    }

    /**
     * Método auxiliar para ordenar um Map
     * 
     * @param unsortmap Map a ser ordenado.
     * @return Map ordenado.
     */
    private static Map<Integer, Double> sortByValue(Map<Integer, Double> unsortMap) {
        List<Map.Entry<Integer, Double>> list = new LinkedList<Map.Entry<Integer, Double>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Map.Entry<Integer, Double> o1,
                    Map.Entry<Integer, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<Integer, Double> sortedMap = new LinkedHashMap<Integer, Double>();
        for (Map.Entry<Integer, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    /**
     * Apresenta o rank de casas com maior consumo de energia num dado período
     * (4ºstat).
     * 
     * @param dateInicio Data inicial.
     * @param dateFim    Data final.
     * @return Lista de casas ordenadas por consumo.
     */
    public ArrayList<Integer> rankCasasComMaiorConsumo(LocalDate dateInicio, LocalDate dateFim) {
        int codigoCasa = 0;
        double consumo = 0.00;
        Map<Integer, Double> consumoPerCasa = new HashMap<Integer, Double>();
        for (Fatura f : this.getArt().getFaturas().values()) {
            if (validateFatura(dateInicio, dateFim, f)) {
                codigoCasa = f.getCodigoCasa();
                consumo = f.getConsumo();
            }
            for (Fatura f2 : this.getArt().getFaturas().values()) {
                if (f2.getCodigoCasa() == codigoCasa && validateFatura(dateInicio, dateFim, f2)) {
                    consumo += f2.getConsumo();
                }
            }
            consumoPerCasa.put(f.getCodigoCasa(), consumo);
            consumo = 0.00;
        }

        sortByValue(consumoPerCasa);
        ArrayList<Integer> casasID = new ArrayList<Integer>(consumoPerCasa.keySet());
        return casasID;
    }

    /**
     * Método que adiciona às respetivas estruturas de dados a fatura recentemente
     * criada.
     * 
     * @param fim            data final da fatura.
     * @param nifCliente     NIF do cliente.
     * @param consumo        consumo energia do cliente.
     * @param custo          custo total.
     * @param codigoCasa     código da casa.
     * @param codefornecedor código do fornecedor.
     */
    public void placeFatura(LocalDate fim, int nifCliente, double consumo, double custo,
            int codigoCasa, int codefornecedor) {
        Fatura fatura = criaFatura(nifCliente, LocalDate.now(), fim, consumo, custo, codigoCasa, codefornecedor);
        this.art.addFaturaToMap(fatura);
        this.art.getCasas().get(codigoCasa).addFatura(fatura.getCode());
        this.art.getFornecedores().get(codefornecedor).addFatura(fatura.getCode());
    }

}