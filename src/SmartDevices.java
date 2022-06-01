import java.io.Serializable;
import java.util.Objects;

/**
*Classe Smartdevices é relstiva a estes mesmos dispositivos e um pouco geral, dado que os 
*metodos implementados devem adequar se a bulbs, cameras e speakers
 */

public abstract class SmartDevices implements Serializable {

    /**
    *Variaveis de instancia da classe
     */
    private static int counter = 0;
    private boolean isON;
    private int id;
    private float custoInstalacao;

    /**
    *Construtor
     */
    public SmartDevices() {
        this.isON = false;
        this.id = 0;
        this.custoInstalacao = 0;
    }

    /**
    *Construtor
     */

    public SmartDevices(SmartDevices device) {
        this.isON = device.isON;
        this.id = device.id;
        this.custoInstalacao = device.custoInstalacao;
    }

    /**
    *Construtor
     */
    public SmartDevices(boolean isON, float custoInstalacao) {
        this.isON = isON;
        this.id = counter;
        counter++;
        this.custoInstalacao = custoInstalacao;
    }

    /**
     * metodo que verifica se um objeto é lampada
     * 
     * @return boolean
     */
    public boolean isBulb() {
        return this instanceof SmartBulb;
    }

    /**
     * metodo que verifica se um objeto é speaker
     * 
     * @return boolean
     */
    public boolean isSpeaker() {
        return this instanceof SmartSpeaker;
    }

    /**
     * método que verifica se um objeto é camera
     * 
     * @return boolean
     */
    public boolean isCamera() {
        return this instanceof SmartCamera;
    }

    /**
     * metodo que verifica se determinado dispositivo está ligado
     * 
     * @return boolean
     */
    public boolean isON() {
        return isON;
    }

    /**
     * getter do id do device
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *  getter do custo de instalacao
     * 
     * @return o valor da variavel custoInstalacao
     */
    public float getCustoInstalacao() {
        return custoInstalacao;
    }

    /**
    *metodo que da set de isON para true
     */
    public void setON() {
        isON = true;
    }

    /**
    *metodo que da set de isON para falso
    */ 
    public void setOFF() {
        isON = false;
    }

    /**
     * setter do id
     * 
     * @param id id do dispositivo
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter do custo de instalacao
     * 
     * @param custoInstalacao custo de instalacao
     */
    public void setCustoInstalacao(float custoInstalacao) {
        this.custoInstalacao = custoInstalacao;
    }

    /**
     * implementacao do metodo equals()
     * 
     * @param o o objeto a ser comparado
     * @return hashcode()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SmartDevices that = (SmartDevices) o;
        return isON == that.isON && Float.compare(that.custoInstalacao, custoInstalacao) == 0
                && Objects.equals(id, that.id);
    }

    /**
     *implementacao do metodo toString()
     * 
     * @return concatenacao de strings
     */
    @Override
    public String toString() {
        return "SmartDevices{" +
                "isON=" + isON +
                ", id='" + id + '\'' +
                ", custoInstalacao=" + custoInstalacao +
                '}';
    }

    /**
     * implementacao do metodo clone
     * 
     * @return novo objeto mas do mesmo tipo do objeto clonado
     */
    public abstract SmartDevices clone();

    /**
     * metodo que da return do consumo diario
     * 
     * @return return de consumo diario
     */
    public abstract double consumoDiario();
}