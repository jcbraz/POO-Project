public class SmartCamera extends SmartDevices {

    private static int counter = 0;
    private boolean isON;
    private int id;
    private int resolution_x; // pixeis
    private int resolution_y; // pixeis
    private double filesize;
    private int consumoDiarioCamera;

    public SmartCamera() {
        this.isON = false;
        this.id = 0;
        this.resolution_x = 0;
        this.resolution_y = 0;
        this.filesize = 0.00;
        this.consumoDiarioCamera = 0;
    }

    public SmartCamera(boolean isON, int resolution_x, int resolution_y, double filesize, int consumoDiarioCamera) {
        this.isON = isON;
        this.id = counter++;
        this.resolution_x = resolution_x;
        this.resolution_y = resolution_y;
        this.filesize = filesize;
        this.consumoDiarioCamera = consumoDiarioCamera;
    }

    public boolean isON() {
        return isON;
    }

    public int getId() {
        return id;
    }

    public int getResolution_x() {
        return resolution_x;
    }

    public int getResolution_y() {
        return resolution_y;
    }

    public double getFilesize() {
        return filesize;
    }

    public int getConsumoDiarioCamera() {
        return consumoDiarioCamera;
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

    public void setResolution_x(int resolution_x) {
        this.resolution_x = resolution_x;
    }

    public void setResolution_y(int resolution_y) {
        this.resolution_y = resolution_y;
    }

    public void setFilesize(double filesize) {
        this.filesize = filesize;
    }

    public void setConsumoDiarioCamera(int consumoDiarioCamera) {
        this.consumoDiarioCamera = consumoDiarioCamera;
    }

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

    public SmartCamera clone() {
        return new SmartCamera(this.isON, this.resolution_x, this.resolution_y, this.filesize, this.consumoDiarioCamera);
    }

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