package entities;

public class IotDevice {
    public int uid;
    public String ipAddress;
    public String macAddress;
    public int xaxis;
    public int yaxis;
    public int fogNodeDistance;
    public int fogId;
    public int request = (int) ((Math.random() * (5 - 1)) + 1);
    public int generationRate;
    public int applicationType;

    public void updateRequest() {
        request = 0;
    }
}
