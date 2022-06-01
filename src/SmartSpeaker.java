/**
 * SmartSpeaker is a class that represents a smart speaker
 */
public class SmartSpeaker extends SmartDevices {
    private int volume; // escala de 0 até 100
    private String nomeRadio;
    private String marcaEquipamento;
    private float consumoDiarioSpeaker; // função da marca do equipamento

    /**
    *Construtor
     */
    public SmartSpeaker() {
        super();
        this.volume = 0;
        this.nomeRadio = "";
        this.marcaEquipamento = "";
        this.consumoDiarioSpeaker = 0;
    }

    /**
    *Construtor
     */

    public SmartSpeaker(SmartSpeaker speaker) {
        super(speaker);
        this.volume = speaker.volume;
        this.nomeRadio = speaker.nomeRadio;
        this.consumoDiarioSpeaker = speaker.consumoDiarioSpeaker;
        this.marcaEquipamento = speaker.marcaEquipamento;
    }

    /**
    *Construtor
     */
    public SmartSpeaker(boolean isON, float custoInstalacao, int volume, String nomeRadio, String marcaEquipamento,
        float consumoDiarioSpeaker) {
        super(isON,custoInstalacao);
        this.volume = volume;
        this.nomeRadio = nomeRadio;
        this.marcaEquipamento = marcaEquipamento;
        this.consumoDiarioSpeaker = consumoDiarioSpeaker;
    }


    /**
     * Getter do volume 
     * 
     * @return volume 
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Getter da estacao de radio
     * 
     * @return nome da estacao de radio
     */
    public String getNomeRadio() {
        return nomeRadio;
    }

    /**
     * Getter da marca do speaker
     * 
     * @return marca do equipamento
     */
    public String getMarcaEquipamento() {
        return marcaEquipamento;
    }

    /**
     * Getter do consumo diario do speaker
     * 
     * @return valor da variavel consumoDiarioSpeaker
     */
    public double getconsumoDiarioSpeaker() {
        return consumoDiarioSpeaker;
    }

    /**
     * setter do volume
     * 
     * @param volume o volume sonoro
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Setter do nome da radio
     * 
     * @param nomeRadio nome da estacao de radio
     */
    public void setNomeRadio(String nomeRadio) {
        this.nomeRadio = nomeRadio;
    }

    /**
     * Setter da marca do speaker
     * 
     * @param marcaEquipamento marca do speaker
     */
    public void setMarcaEquipamento(String marcaEquipamento) {
        this.marcaEquipamento = marcaEquipamento;
    }

    /**
     * Setter do consumo diario do speaker
     * 
     * @param consumoDiarioSpeaker consumo diario do speaker
     */
    public void setconsumoDiarioSpeaker(float consumoDiarioSpeaker) {
        this.consumoDiarioSpeaker = consumoDiarioSpeaker;
    }

    /**
     *  Metodo equals
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
        SmartSpeaker that = (SmartSpeaker) o;
                return consumoDiarioSpeaker == that.consumoDiarioSpeaker
                && nomeRadio.equals(that.nomeRadio)
                && marcaEquipamento.equals(that.marcaEquipamento);
    }

    /**
     * metodo toString()
     * 
     * @return strings concatenadas
     */
    @Override
    public String toString() {
        return "SmartSpeaker{" +
                "id= " + super.getId() +
                ", volume=" + volume +
                ", nomeRadio='" + nomeRadio + '\'' +
                ", marcaEquipamento='" + marcaEquipamento + '\'' +
                ", consumoDiarioSpeaker=" + consumoDiarioSpeaker +
                '}';
    }

    /**
     * Metodo clone() para clonar um objeto
     * 
     * @return novo objeto(smartspeaker) com as caracteristicas do original 
     */
    public SmartSpeaker clone() {
        return new SmartSpeaker(this);
    }

    /**
     * Metodo que calcula o valor de consumo diario do speaker com base numa 
     * formula que leva em conta o seu volume
     * 
     * @return consumoDiario do speaker
     */
    public double consumoDiario() {
        if (this.isON() == false)
            return 0.00;
        else {
            double grauConsumo = 0.00;
            if (this.volume < 25) grauConsumo = 1.05;
            if (this.volume >= 25 && this.volume < 50)
                grauConsumo = 1.15;
            if (this.volume >= 50 && this.volume < 75)
                grauConsumo = 1.30;
            if (this.volume >= 75)
                grauConsumo = 1.45;
            

            return (grauConsumo * this.consumoDiarioSpeaker);
        }
    }
}