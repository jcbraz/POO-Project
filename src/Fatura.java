import java.io.Serializable;
import java.time.LocalDate;

public class Fatura implements Serializable {

    // Declaring the variables that will be used in the class.
    private static int counter = 0;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int codefornecedor;
    private int nifCliente;
    private double consumo;
    private double custo;
    private int codigoCasa;
    private int code;

    // The default constructor.
    public Fatura() {
        this.dataInicio = LocalDate.now();
        this.dataFim = LocalDate.now();
        this.codefornecedor = 0;
        this.nifCliente = 0;
        this.consumo = 0;
        this.custo = 0;
        this.codigoCasa = 0;
        this.code = 0;
    }

    // A constructor that receives the parameters and assigns them to the variables.
    public Fatura(LocalDate dateinicio, LocalDate datefim, int codefornecedor, int nifCliente, double consumo,
            double custo, int codigoCasa) {
        this.dataInicio = dateinicio;
        this.dataFim = datefim;
        this.codefornecedor = codefornecedor;
        this.nifCliente = nifCliente;
        this.consumo = consumo;
        this.custo = custo;
        this.codigoCasa = codigoCasa;
        this.code = counter++;
    }

    /**
     * > This function returns the date of the beginning of the project
     * 
     * @return The date of the beginning of the project.
     */
    public LocalDate getDateInicio() {
        return this.dataInicio;
    }

    /**
     * This function returns the end date of the event
     * 
     * @return The end date of the period.
     */
    public LocalDate getDateFim() {
        return this.dataFim;
    }

    /**
     * This function returns the value of the variable codefornecedor
     * 
     * @return The method getFornecedor is returning the value of the variable codefornecedor.
     */
    public int getFornecedor() {
        return this.codefornecedor;
    }

    /**
     * > This function returns the nifCliente of the object
     * 
     * @return The nifCliente variable.
     */
    public int getNifCliente() {
        return this.nifCliente;
    }

    /**
     * This function returns the value of the variable "consumo"
     * 
     * @return The consumption of the vehicle.
     */
    public double getConsumo() {
        return this.consumo;
    }

    /**
     * This function returns the value of the variable custo
     * 
     * @return The cost of the item.
     */
    public double getCusto() {
        return this.custo;
    }

    /**
     * > This function returns the value of the variable codigoCasa
     * 
     * @return The method is returning the value of the variable codigoCasa.
     */
    public int getCodigoCasa() {
        return this.codigoCasa;
    }

    /**
     * > This function returns the code of the current object
     * 
     * @return The code of the enum.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * It sets the dateInicio variable to the value of the dateInicio parameter
     * 
     * @param dateInicio The date that the user wants to start the search.
     */
    public void setDateInicio(LocalDate dateInicio) {
        this.dataInicio = dateInicio;
    }

    /**
     * The function setDateFim() sets the dateFim variable to the value of the parameter dateFim
     * 
     * @param dateFim The end date of the period.
     */
    public void setDateFim(LocalDate dateFim) {
        this.dataFim = dateFim;
    }

    /**
     * This function sets the value of the variable codefornecedor to the value of the variable
     * codefornecedor
     * 
     * @param codefornecedor The code of the supplier
     */
    public void setFornecedor(int codefornecedor) {
        this.codefornecedor = codefornecedor;
    }

    /**
     * This function sets the nifCliente variable to the value of the parameter nifCliente
     * 
     * @param nifCliente The client's NIF.
     */
    public void setNifCliente(int nifCliente) {
        this.nifCliente = nifCliente;
    }

    /**
     * This function sets the value of the variable consumo to the value of the parameter consumo
     * 
     * @param consumo the amount of energy consumed by the appliance
     */
    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    /**
     * This function sets the value of the custo variable to the value of the custo parameter.
     * 
     * @param custo The cost of the item.
     */
    public void setCusto(double custo) {
        this.custo = custo;
    }

    /**
     * This function sets the value of the variable codigoCasa to the value of the parameter codigoCasa
     * 
     * @param codigoCasa The house code.
     */
    public void setCodigoCasa(int codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    /**
     * This function sets the code variable to the value of the code parameter.
     * 
     * @param code The HTTP status code.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /** 
     * It returns a new object with the same attributes as the object it was called on
     * 
     * @return A new Fatura object with the same attributes as the original.
     */
    public Fatura clone() {
        return new Fatura(this.dataInicio, this.dataFim, this.codefornecedor, this.nifCliente, this.consumo, this.custo, this.codigoCasa);
    }


}