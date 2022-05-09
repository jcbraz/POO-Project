/**
 * SmartSpeaker is a class that represents a smart speaker
 */
public class SmartSpeaker extends SmartDevices {

    private static int counter = 0;
    private boolean isON;
    private int volume; // escala de 0 até 100
    private int id;
    private String nomeRadio;
    private String marcaEquipamento;
    private float consumoDiarioSpeaker; // função da marca do equipamento

    // It's a constructor.
    public SmartSpeaker() {
        this.isON = false;
        this.volume = 0;
        this.id = 0;
        this.nomeRadio = "";
        this.marcaEquipamento = "";
        this.consumoDiarioSpeaker = 0;
    }

    // It's a constructor.
    public SmartSpeaker(boolean isON, int volume, String nomeRadio, String marcaEquipamento,
            float consumoDiarioSpeaker) {
        this.volume = volume;
        this.id = counter++;
        this.nomeRadio = nomeRadio;
        this.marcaEquipamento = marcaEquipamento;
        this.consumoDiarioSpeaker = consumoDiarioSpeaker;
    }

    /**
     * This function returns a boolean value that indicates whether the light is on or off
     * 
     * @return isON
     */
    public boolean isON() {
        return isON;
    }

    /**
     * This function returns the volume of the box.
     * 
     * @return The volume of the box.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * This function returns the id of the object
     * 
     * @return The id of the object.
     */
    public int getId() {
        return id;
    }

    /**
     * This function returns the name of the radio station
     * 
     * @return The name of the radio station.
     */
    public String getNomeRadio() {
        return nomeRadio;
    }

    /**
     * It returns the value of the variable marcaEquipamento.
     * 
     * @return The brand of the equipment.
     */
    public String getMarcaEquipamento() {
        return marcaEquipamento;
    }

    /**
     * This function returns the value of the variable consumoDiarioSpeaker
     * 
     * @return The value of the variable consumoDiarioSpeaker.
     */
    public double getconsumoDiarioSpeaker() {
        return consumoDiarioSpeaker;
    }

    // This function sets the value of the isON variable to true.
    public void setON() {
        isON = true;
    }

    // This function sets the value of the isON variable to false.
    public void setOFF() {
        isON = false;
    }

    /**
     * This function sets the volume of the TV to the value of the parameter.
     * 
     * @param volume The volume of the sound.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * This function sets the id of the object to the value of the parameter id.
     * 
     * @param id The id of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This function sets the name of the radio
     * 
     * @param nomeRadio The name of the radio button.
     */
    public void setNomeRadio(String nomeRadio) {
        this.nomeRadio = nomeRadio;
    }

    /**
     * This function sets the value of the variable marcaEquipamento to the value of the parameter
     * marcaEquipamento
     * 
     * @param marcaEquipamento The brand of the equipment.
     */
    public void setMarcaEquipamento(String marcaEquipamento) {
        this.marcaEquipamento = marcaEquipamento;
    }

    /**
     * This function sets the value of the variable consumoDiarioSpeaker to the value of the parameter
     * consumoDiarioSpeaker
     * 
     * @param consumoDiarioSpeaker The amount of power consumed by the speaker in a day.
     */
    public void setconsumoDiarioSpeaker(float consumoDiarioSpeaker) {
        this.consumoDiarioSpeaker = consumoDiarioSpeaker;
    }

    /**
     * If the object is the same object, return true. If the object is null or of a different class,
     * return false. If the object is of the same class, compare the fields
     * 
     * @param o the object to be compared.
     * @return The hashCode() method is overridden to return the hashCode of the id field.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SmartSpeaker that = (SmartSpeaker) o;
        return isON == that.isON && volume == that.volume && id == that.id
                && consumoDiarioSpeaker == that.consumoDiarioSpeaker
                && nomeRadio.equals(that.nomeRadio)
                && marcaEquipamento.equals(that.marcaEquipamento);
    }

    /**
     * The toString() method returns a string representation of the object
     * 
     * @return The toString method is being returned.
     */
    @Override
    public String toString() {
        return "SmartSpeaker{" +
                "isON=" + isON +
                "volume=" + volume +
                ", id=" + id +
                ", nomeRadio='" + nomeRadio + '\'' +
                ", marcaEquipamento='" + marcaEquipamento + '\'' +
                ", consumoDiarioSpeaker=" + consumoDiarioSpeaker +
                '}';
    }

    /**
     * The clone() method is used to create a copy of an object
     * 
     * @return A new object of the same type as the object that invoked the method, with the same
     * state.
     */
    public SmartSpeaker clone() {
        return new SmartSpeaker(isON, volume, nomeRadio, marcaEquipamento, consumoDiarioSpeaker);
    }

    /**
     * It returns the daily consumption of the speaker, which is calculated by multiplying the daily
     * consumption of the speaker by a factor that depends on the volume of the speaker
     * 
     * @return The return is the consumption of the speaker.
     */
    public double consumoDiario() {
        double grauConsumo = 0.00;
        if (this.volume >= 25 && this.volume < 50)
            grauConsumo = 1.15;
        if (this.volume >= 50 && this.volume < 75)
            grauConsumo = 1.30;
        if (this.volume >= 75)
            grauConsumo = 1.45;

        return (grauConsumo * this.consumoDiarioSpeaker);
    }
}