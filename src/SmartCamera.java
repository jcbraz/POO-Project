// SmartCamera is a class that represents a smart camera
public class SmartCamera extends SmartDevices {

    private static int counter = 0;
    private boolean isON;
    private int id;
    private int resolution_x; // pixeis
    private int resolution_y; // pixeis
    private double filesize;
    private float consumoDiarioCamera;

    // The constructor of the class SmartCamera.
    public SmartCamera() {
        this.isON = false;
        this.id = 0;
        this.resolution_x = 0;
        this.resolution_y = 0;
        this.filesize = 0.00;
        this.consumoDiarioCamera = 0;
    }

    // It's a constructor.
    public SmartCamera(boolean isON, int resolution_x, int resolution_y, double filesize, float consumoDiarioCamera) {
        this.isON = isON;
        this.id = counter++;
        this.resolution_x = resolution_x;
        this.resolution_y = resolution_y;
        this.filesize = filesize;
        this.consumoDiarioCamera = consumoDiarioCamera;
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
     * This function returns the id of the object.
     * 
     * @return The id of the object.
     */
    public int getId() {
        return id;
    }

    /**
     * This function returns the value of the variable resolution_x
     * 
     * @return The resolution_x variable is being returned.
     */
    public int getResolution_x() {
        return resolution_x;
    }

    /**
     * This function returns the value of the variable resolution_y
     * 
     * @return The resolution_y variable is being returned.
     */
    public int getResolution_y() {
        return resolution_y;
    }

    /**
     * This function returns the filesize of the file
     * 
     * @return The filesize of the file.
     */
    public double getFilesize() {
        return filesize;
    }

    /**
     * This function returns the value of the variable consumoDiarioCamera
     * 
     * @return The value of the variable consumoDiarioCamera.
     */
    public double getConsumoDiarioCamera() {
        return consumoDiarioCamera;
    }

    /**
     * This function sets the value of the isON variable to true.
     */
    public void setON() {
        isON = true;
    }

    /**
     * This function sets the value of the isON variable to false.
     */
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
     * This function sets the resolution_x variable to the value of the resolution_x parameter.
     * 
     * @param resolution_x The width of the image in pixels
     */
    public void setResolution_x(int resolution_x) {
        this.resolution_x = resolution_x;
    }

    /**
     * This function sets the resolution_y variable to the value of the parameter resolution_y
     * 
     * @param resolution_y The height of the image in pixels.
     */
    public void setResolution_y(int resolution_y) {
        this.resolution_y = resolution_y;
    }

    /**
     * This function sets the filesize of the file
     * 
     * @param filesize The size of the file in bytes.
     */
    public void setFilesize(double filesize) {
        this.filesize = filesize;
    }

    /**
     * This function sets the value of the variable consumoDiarioCamera to the value of the parameter
     * consumoDiarioCamera
     * 
     * @param consumoDiarioCamera The daily consumption of the camera.
     */
    public void setConsumoDiarioCamera(float consumoDiarioCamera) {
        this.consumoDiarioCamera = consumoDiarioCamera;
    }

    /**
     * It compares two objects of the same class.
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
        SmartCamera that = (SmartCamera) o;
        return isON == that.isON && id == that.id && resolution_x == that.resolution_x && resolution_y == that.resolution_y
                && Double.compare(that.filesize, filesize) == 0
                && consumoDiarioCamera == that.consumoDiarioCamera;
    }

    /**
     * The toString() method returns a string representation of the object
     * 
     * @return The method toString() is being returned.
     */
    @Override
    public String toString() {
        return "SmartCamera{" +
                "isON=" + isON +
                "id=" + id +
                "resolution_x=" + resolution_x +
                "resolution_y=" + resolution_y +
                ", filesize=" + filesize +
                ", consumoDiarioCamera=" + consumoDiarioCamera +
                '}';
    }

    /**
     * It returns a new object that is a copy of the current object
     * 
     * @return A new SmartCamera object with the same attributes as the original.
     */
    public SmartCamera clone() {
        return new SmartCamera(this.isON, this.resolution_x, this.resolution_y, this.filesize, this.consumoDiarioCamera);
    }

    /**
     * It returns the daily consumption of the camera, based on the resolution of the camera
     * 
     * @return The method returns the daily consumption of the camera.
     */
    public double consumoDiario() {
        double grauConsumo = 0.00;
        if (this.resolution_x == 640 && this.resolution_y == 480)
            grauConsumo = 1.00;
        else if (this.resolution_x == 1280 && this.resolution_y == 720)
            grauConsumo = 1.05;
        else if (this.resolution_x == 1920 && this.resolution_y == 1080)
            grauConsumo = 1.12;
        else if (this.resolution_x == 3840 && this.resolution_y == 2160)
            grauConsumo = 1.38;
        else {
            System.out.println("Introduza uma resolução válida");
            return -1;
        }

        return (grauConsumo * this.consumoDiarioCamera);
    }
}