package loadBalancer;

import entities.FogNode;
import entities.IotDevice;

import java.util.ArrayList;

public class loadBalancer {

    public static void sendRequest(ArrayList<FogNode> nodes, ArrayList<IotDevice> devices) {
        for (IotDevice device : devices) {
            for (FogNode node : nodes) {
                if (node.connectedDevices.contains(device.uid)) {

                    if (!node.isHeavilyLoaded()){
                        node.receiveRequest(device.request);
                        device.updateRequest();
                    }
                    else if (node.isHeavilyLoaded()){
                        // send request to neighbor node
                        int nextNode = node.neighbouringNodes;
                        nodes.get(nextNode).receiveRequest(device.request);
                        device.updateRequest();
                    }
                }
            }
        }

        for (FogNode node : nodes) {
            System.out.println("Node "+node.uid+" Number of requests: "+node.getNumberOfRequests());
        }
    }
}