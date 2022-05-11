import java.io.Serializable;
import java.util.Objects;

public abstract class SmartDevices implements Serializable {

    // The attributes of the class SmartDevices
    private static int counter = 0;
    private boolean isON;
    private int id;
    private float custoInstalacao;

    // A constructor that initializes the attributes of the class SmartDevices.
    public SmartDevices() {
        this.isON = false;
        this.id = 0;
        this.custoInstalacao = 0;
    }

    // A constructor that initializes the attributes of the class SmartDevices.
    public SmartDevices(boolean isON, float custoInstalacao) {
        this.isON = isON;
        this.id = counter;
        counter++;
        this.custoInstalacao = custoInstalacao;
    }

    /**
     * If this is a SmartBulb, return true, otherwise return false.
     * 
     * @return A boolean value.
     */
    public boolean isBulb() {
        return this instanceof SmartBulb;
    }

    /**
     * If this object is a SmartSpeaker, return true, otherwise return false.
     * 
     * @return A boolean value.
     */
    public boolean isSpeaker() {
        return this instanceof SmartSpeaker;
    }

    /**
     * If this object is a SmartCamera, return true, otherwise return false.
     * 
     * @return A boolean value.
     */
    public boolean isCamera() {
        return this instanceof SmartCamera;
    }

    /**
     * This function returns the value of the isON variable.
     * 
     * @return isON
     */
    public boolean isON() {
        return isON;
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
     * > This function returns the value of the variable custoInstalacao
     * 
     * @return The value of the variable custoInstalacao.
     */
    public float getCustoInstalacao() {
        return custoInstalacao;
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

    /**
     * This function sets the value of the variable custoInstalacao to the value of the parameter
     * custoInstalacao
     * 
     * @param custoInstalacao The cost of installing the equipment.
     */
    public void setCustoInstalacao(float custoInstalacao) {
        this.custoInstalacao = custoInstalacao;
    }

    /**
     * The function returns true if the object passed as a parameter is the same as the object that
     * called the function, or if the object passed as a parameter is not null and is of the same class
     * as the object that called the function
     * 
     * @param o The object to compare with.
     * @return The hashCode() method is overridden to return the hashCode of the id field.
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
     * The toString() method returns a string representation of the object
     * 
     * @return The method toString() is being returned.
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
     * The clone() function is used to create a copy of an object
     * 
     * @return A new object of the same type as the object that called the method.
     */
    public abstract SmartDevices clone();

    /**
     * Returns the amount of energy consumed by the appliance in one day.
     * 
     * @return The method is returning the value of the variable consumoDiario.
     */
    public abstract double consumoDiario();
}