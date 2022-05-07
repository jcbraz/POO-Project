public class SmartSpeaker extends SmartDevices {

    private static int counter = 0;
    private boolean isON;
    private int volume; // escala de 0 até 100
    private int id;
    private String nomeRadio;
    private String marcaEquipamento;
    private int consumoDiarioSpeaker; // função da marca do equipamento

    public SmartSpeaker() {
        this.isON = false;
        this.volume = 0;
        this.id = 0;
        this.nomeRadio = "";
        this.marcaEquipamento = "";
        this.consumoDiarioSpeaker = 0;
    }

    public SmartSpeaker(boolean isON, int volume, String nomeRadio, String marcaEquipamento,
            int consumoDiarioSpeaker) {
        this.volume = volume;
        this.id = counter++;
        this.nomeRadio = nomeRadio;
        this.marcaEquipamento = marcaEquipamento;
        this.consumoDiarioSpeaker = consumoDiarioSpeaker;
    }

    public boolean isON() {
        return isON;
    }

    public int getVolume() {
        return volume;
    }

    public int getId() {
        return id;
    }

    public String getNomeRadio() {
        return nomeRadio;
    }

    public String getMarcaEquipamento() {
        return marcaEquipamento;
    }

    public int getconsumoDiarioSpeaker() {
        return consumoDiarioSpeaker;
    }

    public void setON() {
        isON = true;
    }

    public void setOFF() {
        isON = false;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeRadio(String nomeRadio) {
        this.nomeRadio = nomeRadio;
    }

    public void setMarcaEquipamento(String marcaEquipamento) {
        this.marcaEquipamento = marcaEquipamento;
    }

    public void setconsumoDiarioSpeaker(int consumoDiarioSpeaker) {
        this.consumoDiarioSpeaker = consumoDiarioSpeaker;
    }

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

    public SmartSpeaker clone() {
        return new SmartSpeaker(isON, volume, nomeRadio, marcaEquipamento, consumoDiarioSpeaker);
    }

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