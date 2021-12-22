package loadBalancer;

import entities.FogNode;
import entities.IotDevice;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class loadBalancer {
    public static void setRequest(ArrayList<FogNode> nodes, ArrayList<IotDevice> devices) {
        for(IotDevice device : devices) {
            for (FogNode node : nodes) {
                if (node.connectedDevices.contains(device.uid)) {
                    node.updateRequest(device.request);
                }
            }
        }
        try {
            PrintWriter fileout = new PrintWriter(new FileWriter("fognodes_before_offloading.txt"));
            fileout.println(0 + "\t" + 0);

            for (FogNode s: nodes) {
                fileout.println(s.uid+"\t"+s.tnumberOfRequest);
            }
            fileout.close();
            System.out.println("success...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void sendRequest(ArrayList<FogNode> nodes, ArrayList<IotDevice> devices) {
        for (IotDevice device : devices) {
            for (FogNode node : nodes) {
                if (node.connectedDevices.contains(device.uid)) {
                    if (!node.isHeavilyLoaded()) {
                        node.receiveRequest(device.request);
                        device.updateRequest();
                    } else if (node.isHeavilyLoaded()) {
                        for (int i = 0; i < node.sizeofConnectedNode(); i++) {
                            if (!nodes.get(node.getNeighbourNode(i)).isHeavilyLoaded()) {
                                nodes.get(node.getNeighbourNode(i)).receiveRequest(device.request);
                                device.updateRequest();
                            }
                        }
                    }
                }
            }
        }

        for (FogNode node : nodes) {
            System.out.println("Node " + node.uid + " Number of packets received : " + node.getNumberOfRequests());
        }
        try {
            PrintWriter fileout = new PrintWriter(new FileWriter("fognodes_request.txt"));
            fileout.println(0 + "\t" + 0);

            for (FogNode s: nodes) {
                fileout.println(s.uid+"\t"+s.getNumberOfRequests());
            }
            fileout.close();
            System.out.println("success...");
        } catch (Exception e) {
            System.out.println(e);
        }
        for (FogNode node : nodes){
            node.checkSatisfactory();
            System.out.println(node.satisfactory);
        }
        try {
            PrintWriter fileout = new PrintWriter(new FileWriter("satisfactory.txt"));
            fileout.println(0 + "\t" + 0);
            for (FogNode s: nodes) {
                fileout.println(s.uid+"\t"+s.satisfactory);
            }
            fileout.close();
            System.out.println("success...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}