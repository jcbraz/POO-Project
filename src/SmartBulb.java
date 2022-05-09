// SmartBulb is a class that represents a smart bulb
public class SmartBulb extends SmartDevices {

    // The attributes of the class SmartBulb.
    private static int counter = 0;
    private boolean isON;
    private int id;
    private String tonalidade;
    private double dimensoes; // em centimetros
    private float consumoDiarioBulb;

    // A constructor.
    public SmartBulb(boolean isON, String tonalidade, double dimensoes, float consumoDiarioBulb) {
        this.isON = isON;
        this.id = counter++;
        this.tonalidade = tonalidade;
        this.dimensoes = dimensoes;
        this.consumoDiarioBulb = consumoDiarioBulb;
    }

    // A constructor.
    public SmartBulb() {
        this.isON = false;
        this.id = 0;
        this.tonalidade = "";
        this.dimensoes = 0;
        this.consumoDiarioBulb = 0;
    }

    /**
     * This function returns the tonality of the song
     * 
     * @return The tonalidade attribute.
     */
    public String getTonalidade() {
        return tonalidade;
    }

    /**
     * This function returns the value of the variable dimensoes
     * 
     * @return The value of the variable dimensoes.
     */
    public double getDimensoes() {
        return dimensoes;
    }

    /**
     * This function returns the value of the variable consumoDiarioBulb
     * 
     * @return The value of the variable consumoDiarioBulb.
     */
    public double getconsumoDiarioBulb() {
        return consumoDiarioBulb;
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
     * This function sets the id of the object to the value of the parameter id.
     * 
     * @param id The id of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    // This function sets the value of the isON variable to false.
    public void setOff() {
        isON = false;
    }

    // This function sets the tonalidade to Neutral.
    public void setTonalidadeNeutral() {
        this.tonalidade = "Neutral";
    }

    // This function sets the tonalidade to Warm.
    public void setTonalidadeWarm() {
        this.tonalidade = "Warm";
    }

    // This function sets the tonalidade to Cold.
    public void setTonalidadeCold() {
        this.tonalidade = "Cold";
    }

    /**
     * This function sets the value of the variable dimensoes to the value of the variable dimensoes
     * 
     * @param dimensoes The dimensions of the object.
     */
    public void setDimensoes(double dimensoes) {
        this.dimensoes = dimensoes;
    }

    /**
     * This function sets the value of the variable consumoDiarioBulb to the value of the parameter
     * consumoDiarioBulb
     * 
     * @param consumoDiarioBulb The daily consumption of the bulb in kWh.
     */
    public void setconsumoDiarioBulb(float consumoDiarioBulb) {
        this.consumoDiarioBulb = consumoDiarioBulb;
    }

    /**
     * If the object is the same as the one being compared, return true. If the object is null or of a
     * different class, return false. If the object is of the same class, compare the fields and return
     * true if they are all equal
     * 
     * @param o The object to be compared.
     * @return The hashCode of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SmartBulb smartBulb = (SmartBulb) o;
        return isON == smartBulb.isON && smartBulb.id == id && Double.compare(smartBulb.dimensoes, dimensoes) == 0
                && Double.compare(smartBulb.consumoDiarioBulb, consumoDiarioBulb) == 0
                && tonalidade.equals(smartBulb.tonalidade);
    }

    /**
     * The toString() method returns a string representation of the object
     * 
     * @return The toString method is being returned.
     */
    @Override
    public String toString() {
        return "SmartBulb{" +
                "isON=" + isON +
                ", id=" + id +
                ", tonalidade='" + tonalidade + '\'' +
                ", dimensoes=" + dimensoes +
                ", consumoDiarioBulb=" + consumoDiarioBulb +
                '}';
    }

    /**
     * The clone() method is used to create a copy of an object
     * 
     * @return A new SmartBulb object with the same values as the original.
     */
    @Override
    public SmartBulb clone() {
        return new SmartBulb(isON, tonalidade, dimensoes, consumoDiarioBulb);
    }

    /**
     * 
     * Assumindo que a potência da lâmpada multi-tonalidade é de 8.7 W,
     * utilizando uma fórmula de consumo de energia que agrega a potência,
     * consumoDiaro, preço médio do KWh e a eficiência de cada tonalidade.
     * 
     * Os valores agregados à potência seriam o consumo adicional relativo
     * à tonalidade em que a tonalidade "Neutral" e "Warm" consumem mais
     * 15% e 30% respetivamente em relação à tonalidade "Cold".
     * 
     */

    // alterar --> não incluir preço no calculo apenas quantidade de energia
    public double consumoDiario() {
        double grauConsumo = 0.00;
        if (this.tonalidade.equals("Neutral"))
            grauConsumo = 1.13;

        if (this.tonalidade.equals("Warm"))
            grauConsumo = 1.25;

        return (grauConsumo * this.consumoDiarioBulb);
    }
}
