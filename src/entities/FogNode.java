package entities;

import jdk.internal.icu.text.UnicodeSet;

import java.util.ArrayList;

public class FogNode<list> {
    public int uid;
    public int xaxis;
    public int yaxis;
    public int fogNode;
    public int iotDevices;
    public ArrayList<IotDevice> connectedDevices = new ArrayList<IotDevice>();
    public int ram = 1000;
    public int load;
    public int numberOfRequests = 0;
    public int packetsQueue = 0;
    public int processingPower;
    public list connectedDevicesList;
    public int capacity = 40;
    public int neighbouringNodes;
    public int neighboruingNodesDistece;

    public void addNeighbouringNodes(int neighbouringNodes, int neighboruingNodesDistece) {
        this.neighbouringNodes = neighbouringNodes;
        this.neighboruingNodesDistece = neighboruingNodesDistece;
    }


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
