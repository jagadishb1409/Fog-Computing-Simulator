package entities;

import jdk.internal.icu.text.UnicodeSet;
import parameters.Params;

import java.util.ArrayList;
import java.util.Arrays;

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
    public int capacity = 40;
    public int neighbouringNodes;
    public int neighboruingNodesDistance[] = new int[Params.NUM_OF_NODES];
    public int connectedNodes[] = new int[Params.NUM_OF_NODES];
    public int count = 0;

    public void addNeighbouringNodes(int neighbouringNodes, int neighboruingNodesDistance) {
        this.neighboruingNodesDistance[count] = neighboruingNodesDistance;
        this.connectedNodes[count] = neighbouringNodes;
        count++;
    }


    public void receiveRequest(int request) {
        numberOfRequests += request;
        packetsQueue += request;
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public int checkLoad() {
        load = packetsQueue / capacity * 100;
        return load;
    }

    public boolean isHeavilyLoaded() {
        if (packetsQueue >= 80) {
            return true;
        }
        return false;
    }

    public int getFirstNeighbourNode() {
        return connectedNodes[0];
    }
}
