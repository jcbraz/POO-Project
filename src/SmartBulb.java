/**
*SmartBulb corresponde uma classe que representa as lampadas inteligentes
 */

public class SmartBulb extends SmartDevices {

    /**
    *Atributos da classe
     */
    private String tonalidade;
    private double dimensoes; // em centimetros
    private float consumoDiarioBulb;

    /**
    *Construtor
     */
    public SmartBulb(boolean isON, float custoInstalacao, String tonalidade, double dimensoes,
            float consumoDiarioBulb) {
        super(isON, custoInstalacao);
        this.tonalidade = tonalidade;
        this.dimensoes = dimensoes;
        this.consumoDiarioBulb = consumoDiarioBulb;
    }

    public SmartBulb(SmartBulb luz) {
        super(luz);
        this.tonalidade = luz.tonalidade;
        this.dimensoes = luz.dimensoes;
        this.consumoDiarioBulb = luz.consumoDiarioBulb;
    }

    /**
    * Construtor
     */
    public SmartBulb() {
        super();
        this.tonalidade = "";
        this.dimensoes = 0;
        this.consumoDiarioBulb = 0;
    }

    /**
     * Getter da tonalidade
     * 
     * @return tonalidade
     */
    public String getTonalidade() {
        return tonalidade;
    }

    /**
     * Getter das dimensoes
     * 
     * @return dimensoes
     */
    public double getDimensoes() {
        return dimensoes;
    }

    /**
     * Getter do consumo diário da lampada
     * 
     * @return The value of the variable consumoDiarioBulb.
     */
    public double getconsumoDiarioBulb() {
        return consumoDiarioBulb;
    }

    /**
    *Método que faz set da tonalidade para neutro
     */ 
    public void setTonalidadeNeutral() {
        this.tonalidade = "Neutral";
    }

    /**
    *Método que dá set da tonalidade para warm
     */ 
    public void setTonalidadeWarm() {
        this.tonalidade = "Warm";
    }

    /**
    *Método que dá set da tonalidade para cold
     */ 
    public void setTonalidadeCold() {
        this.tonalidade = "Cold";
    }

    /**
     * Set das dimensoes
     * 
     * @param dimensoes dimensoes da lampada
     */
    public void setDimensoes(double dimensoes) {
        this.dimensoes = dimensoes;
    }

    /**
     *set do consumo diario da lampada
     * 
     * @param consumoDiarioBulb consumo diario da lampada
     */
    public void setconsumoDiarioBulb(float consumoDiarioBulb) {
        this.consumoDiarioBulb = consumoDiarioBulb;
    }

    /**
     *metodo que verifica igualdade
     * 
     * @param o o objeto a ser comparado
     * @return o hashcode do objeto
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SmartBulb smartBulb = (SmartBulb) o;
        return Double.compare(smartBulb.dimensoes, dimensoes) == 0
                && Double.compare(smartBulb.consumoDiarioBulb, consumoDiarioBulb) == 0
                && tonalidade.equals(smartBulb.tonalidade);
    }

    /**
     * metodo tostring
     * 
     * @return strings concatenadas
     */
    @Override
    public String toString() {
        return "SmartBulb{" +
                "id = " + super.getId() +
                ", tonalidade='" + tonalidade + '\'' +
                ", dimensoes=" + dimensoes +
                ", consumoDiarioBulb=" + consumoDiarioBulb +
                '}';
    }

    /**
     * método clone()
     * 
     * @return nova smartbulb com os mesmos valores da original
     */
    @Override
    public SmartBulb clone() {
        return new SmartBulb(this);
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

    
    public double consumoDiario() {
        double grauConsumo = 0.00;
        if (this.isON() == false);
        else {
            if (this.tonalidade.equals("Neutral")) grauConsumo = 1.13;
            else if (this.tonalidade.equals("Warm")) grauConsumo = 1.25;
            else grauConsumo = 1.10;
        }
        return (grauConsumo * this.consumoDiarioBulb);
    }
}
