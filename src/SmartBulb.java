public class SmartBulb extends SmartDevices {

    private static int counter = 0;
    private boolean isON;
    private int id;
    private String tonalidade;
    private double dimensoes; // em centimetros
    private double consumoDiarioBulb;

    public SmartBulb(boolean isON, String tonalidade, double dimensoes, double consumoDiarioBulb) {
        this.isON = isON;
        this.id = counter++;
        this.tonalidade = tonalidade;
        this.dimensoes = dimensoes;
        this.consumoDiarioBulb = consumoDiarioBulb;
    }

    public SmartBulb() {
        this.isON = false;
        this.id = 0;
        this.tonalidade = "";
        this.dimensoes = 0;
        this.consumoDiarioBulb = 0;
    }

    public String getTonalidade() {
        return tonalidade;
    }

    public double getDimensoes() {
        return dimensoes;
    }

    public double getconsumoDiarioBulb() {
        return consumoDiarioBulb;
    }

    public void setON() {
        isON = true;
    }

    public void setOFF() {
        isON = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOff() {
        isON = false;
    }

    public void setTonalidadeNeutral() {
        this.tonalidade = "Neutral";
    }

    public void setTonalidadeWarm() {
        this.tonalidade = "Warm";
    }

    public void setTonalidadeCold() {
        this.tonalidade = "Cold";
    }

    public void setDimensoes(double dimensoes) {
        this.dimensoes = dimensoes;
    }

    public void setconsumoDiarioBulb(int consumoDiarioBulb) {
        this.consumoDiarioBulb = consumoDiarioBulb;
    }

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

    // clone
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
