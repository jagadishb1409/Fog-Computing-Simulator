package loadBalancer;

import entities.FogNode;
import entities.IotDevice;

import java.util.ArrayList;

public class loadBalancer {

    public static void sendRequest(ArrayList<FogNode> nodes, ArrayList<IotDevice> devices) {
        for (IotDevice device : devices) {
            for (FogNode node : nodes) {
                int randomIndex = (int) (Math.random() * devices.size());
                if (!node.isHeavilyLoaded()) {
                    int randomIndex2 = (int) (Math.random() * nodes.size());
                    if (randomIndex2 != randomIndex) {
                        nodes.get(randomIndex2).receiveRequest(device.request);
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