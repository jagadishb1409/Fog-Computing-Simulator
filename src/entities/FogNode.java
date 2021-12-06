package entities;


public class FogNode<list> {
    public int uid;
    public int xaxis;
    public int yaxis;
    public int fogNode;
    public int iotDevices;
    public int[] neighbourNodes = new int[fogNode];
    public int[] connectedDevices = new int[iotDevices];
    public int ram = 1000;
    public int load;
    public int numberOfRequests = 0;
    public int packetsQueue = 0;
    public int processingPower;
    public list connectedDevicesList;
    public int capacity = 40;

    public void receiveRequest(int request) {
        numberOfRequests += request;
        packetsQueue += request;
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public int checkLoad() {
        //check load percentage
        load = packetsQueue / capacity;
        return load;
    }

    public boolean isHeavilyLoaded() {
        if (checkLoad() > 80) {
            return true;
        }
        return false;
    }
}
