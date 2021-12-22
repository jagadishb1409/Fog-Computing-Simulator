package loadBalancer;

import entities.FogNode;
import entities.IotDevice;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class rrBalancer {
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
        try {
            PrintWriter fileout = new PrintWriter(new FileWriter("rrfognodes_request.txt"));

            for (FogNode s: nodes) {
                fileout.println(s.uid+"\t"+s.getNumberOfRequests());
            }
            fileout.close();
            System.out.println("success...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
