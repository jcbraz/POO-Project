import java.io.Serializable;
import java.util.Objects;

public abstract class SmartDevices implements Serializable {

    private static int counter = 0;
    private boolean isON;
    private int id;
    private float custoInstalacao;

    

    public SmartDevices() {
        this.isON = false;
        this.id = 0;
        this.custoInstalacao = 0;

    }

    public SmartDevices(boolean isON, float custoInstalacao) {
        this.isON = isON;
        this.id = counter++;
        this.custoInstalacao = custoInstalacao;

    }

    public boolean isBulb() {
        return this instanceof SmartBulb;
    }

    public boolean isSpeaker() {
        return this instanceof SmartSpeaker;
    }

    public boolean isCamera() {
        return this instanceof SmartCamera;
    }

    public boolean isON() {
        return isON;
    }

    public int getId() {
        return id;
    }

    public float getCustoInstalacao() {
        return custoInstalacao;
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


    
    public void setCustoInstalacao(float custoInstalacao) {
        this.custoInstalacao = custoInstalacao;
    }

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

    @Override
    public String toString() {
        return "SmartDevices{" +
                "isON=" + isON +
                ", id='" + id + '\'' +
                ", custoInstalacao=" + custoInstalacao +
                '}';
    }

    public abstract SmartDevices clone();

    public abstract double consumoDiario();
}