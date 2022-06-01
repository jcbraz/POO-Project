
/**
*SmartCamera e uma classe que representa cameras inteligentes
 */ 
public class SmartCamera extends SmartDevices {

/**
*Declaracao das variaveis de instancia do metodo smartcamera
 */

    private int resolution_x; 
    private int resolution_y; 
    private double filesize;
    private float consumoDiarioCamera;

    /**
    *Construtor
     */ 
    public SmartCamera() {
        this.resolution_x = 0;
        this.resolution_y = 0;
        this.filesize = 0.00;
        this.consumoDiarioCamera = 0;
    }

    /**
    * Construtor
     */
    public SmartCamera(SmartCamera camera) {
        super(camera);
        this.resolution_x = camera.resolution_x;
        this.resolution_y = camera.resolution_y;
        this.filesize = camera.filesize;
        this.consumoDiarioCamera = camera.consumoDiarioCamera;
    }

    /**
    *Construtor
     */
    public SmartCamera(boolean isON, float custoInstalacao, int resolution_x, int resolution_y, double filesize, float consumoDiarioCamera) {
        super(isON, custoInstalacao);
        this.resolution_x = resolution_x;
        this.resolution_y = resolution_y;
        this.filesize = filesize;
        this.consumoDiarioCamera = consumoDiarioCamera;
    }

    /**
     * Getter da resolucao horizontal
     * 
     * @return resolucao_x(horizontal)
     */
    public int getResolution_x() {
        return resolution_x;
    }

     /**
     * Getter da resolucao vertical
     * 
     * @return resolucao_y(vertical)
     */
    public int getResolution_y() {
        return resolution_y;
    }

    /**
     * Getter do tamanho do ficheiro resultante da gravacao
     * 
     * @return tamanho do ficheiro
     */
    public double getFilesize() {
        return filesize;
    }

    /**
     * Getter do consumo diario da camera
     * 
     * @return consumo diario camera 
     */
    public double getConsumoDiarioCamera() {
        return consumoDiarioCamera;
    }


    /**
     * Set da resolucao_x
     * 
     * @param resolution_x resolucao da imagem horizontalmente em pixeis
     */
    public void setResolution_x(int resolution_x) {
        this.resolution_x = resolution_x;
    }

    /**
     * Set da resolucao_y
     * 
     * @param resolution_y resolucao da imagem verticalmente em pixeis
     */
    public void setResolution_y(int resolution_y) {
        this.resolution_y = resolution_y;
    }

    /**
     * Set do tamanho do ficheiro
     * 
     * @param filesize tamanho do ficheiro
     */
    public void setFilesize(double filesize) {
        this.filesize = filesize;
    }

    /**
     * Set da variavel consumoDiarioCamera
     * 
     * @param consumoDiarioCamera consumo diario da camera
     */
    public void setConsumoDiarioCamera(float consumoDiarioCamera) {
        this.consumoDiarioCamera = consumoDiarioCamera;
    }

    /**
     * definicao do equals(verificar igualdade)
     * 
     * @param o objeto a ser comparado
     * @return hashcode() metodo 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SmartCamera that = (SmartCamera) o;
        return resolution_x == that.resolution_x && resolution_y == that.resolution_y
                && Double.compare(that.filesize, filesize) == 0
                && consumoDiarioCamera == that.consumoDiarioCamera;
    }

    /**
     * metodo toString()
     * 
     * @return concatenacao de strings
     */
    @Override
    public String toString() {
        return "SmartCamera{" +
                "id =" + super.getId() +
                ", resolution_x=" + resolution_x +
                "resolution_y=" + resolution_y +
                ", filesize=" + filesize +
                ", consumoDiarioCamera=" + consumoDiarioCamera +
                '}';
    }

    /**
     * metodo para clonar
     * 
     * @return nova smartcamera com os mesmos atributos da original
     */
    public SmartCamera clone() {
        return new SmartCamera(this);
    }

    /**
     * Metodo que calcula o consumo diario tendo como base a resolucao da camera
     * 
     * @return consumo diario da camera
     */
    public double consumoDiario() {
        if (this.isON() == false)
            return 0.00;
        else {
            double grauConsumo = 0.00;
            if (this.resolution_x == 640 && this.resolution_y == 480)
                grauConsumo = (this.resolution_x * this.resolution_y * this.filesize * 0.001 * 0.001 * 0.0005);
            else if (this.resolution_x == 1024 && this.resolution_y == 768)
                grauConsumo = (this.resolution_x * this.resolution_y * this.filesize * 0.001 * 0.001 * 0.0005);
            else if (this.resolution_x == 1280 && this.resolution_y == 768)
                grauConsumo = (this.resolution_x * this.resolution_y * this.filesize * 0.001 * 0.001 * 0.0005);
            else if (this.resolution_x == 1366 && this.resolution_y == 768)
                grauConsumo = (this.resolution_x * this.resolution_y * this.filesize * 0.001 * 0.001 * 0.0005);
            else if (this.resolution_x == 1920 && this.resolution_y == 1080)
                grauConsumo = (this.resolution_x * this.resolution_y * this.filesize * 0.001 * 0.001 * 0.0005);
            else if (this.resolution_x == 2160 && this.resolution_y == 1440)
                grauConsumo = (this.resolution_x * this.resolution_y * this.filesize * 0.001 * 0.001 * 0.0005);
            else if (this.resolution_x == 3840 && this.resolution_y == 2160)
                grauConsumo = (this.resolution_x * this.resolution_y * this.filesize * 0.001 * 0.001 * 0.0005);
            else {
                System.out.println("Introduza uma resolução válida");
                return -1;
            }
            return (grauConsumo * this.consumoDiarioCamera);
        }
    }
}