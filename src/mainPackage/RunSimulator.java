package mainPackage;

import DistanceCalculator.DistanceClaculate;
import loadBalancer.loadBalancer;
import loadBalancer.rrBalancer;
import entities.FogNode;
import entities.IotDevice;
import service.ServiceClass;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunSimulator {
    public static void main(String[] args){
        ServiceClass srvc = new ServiceClass();
        ArrayList<IotDevice> sts = srvc.createuser();
        ArrayList<FogNode> nodes = srvc.createnodes();

        System.out.println("Number of Device = "+sts.size());

        try {
            PrintWriter fileout = new PrintWriter(new FileWriter("iotdevices.txt"));
            fileout.println(0 + "\t" + 0);

            for (IotDevice s: sts) {
                fileout.println(s.xaxis+"\t"+s.yaxis);
            }
            fileout.close();
            System.out.println("success...");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            PrintWriter fileout = new PrintWriter(new FileWriter("fognodes.txt"));
            fileout.println(0 + "\t" + 0);

            for (FogNode s: nodes) {
                fileout.println(s.xaxis+"\t"+s.yaxis);
            }
            fileout.close();
            System.out.println("success...");
        } catch (Exception e) {
            System.out.println(e);
        }
        DistanceClaculate.calculate(nodes, sts);
        loadBalancer.setRequest(nodes, sts);
        loadBalancer.sendRequest(nodes, sts);
//        rrBalancer.sendRequest(nodes, sts);
    }
}
