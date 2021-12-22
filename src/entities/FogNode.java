package entities;

import parameters.Params;

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
    public int neighboruingNodesDistance[] = new int[Params.NUM_OF_NODES];
    public int connectedNodes[] = new int[Params.NUM_OF_NODES];
    public int count = 0;
    public float satisfactory;

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

    public void checkSatisfactory() {
        satisfactory = (float) (Params.capacity - packetsQueue) / Params.capacity * 100;
    }

    public boolean isHeavilyLoaded() {
        if (packetsQueue >= Params.capacity) {
            return true;
        }
        return false;
    }

    public int getNeighbourNode(int index) {
        return connectedNodes[index];
    }

    public int sizeofConnectedNode() {
        return connectedNodes.length;
    }
}
