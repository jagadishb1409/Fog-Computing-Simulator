package DistanceCalculator;

import entities.FogNode;
import entities.IotDevice;
import parameters.Params;

import java.util.ArrayList;

public class DistanceClaculate {
    public static void calculate(ArrayList<FogNode> nodes, ArrayList<IotDevice> devices) {
        int[][] calculatedData = new int[Params.NUM_OF_NODES][Params.NUM_OF_DEVICES];
        for (FogNode j : nodes) {
            for (IotDevice i : devices) {
                int cal;
                int x = (int) (j.xaxis - i.xaxis);
                int y = (int) (j.yaxis - i.yaxis);
                int square = (int) (Math.pow(x, 2) + Math.pow(y, 2));
                cal = (int) Math.sqrt(square);
                calculatedData[j.uid][i.uid] = cal;
            }
        }
        for (IotDevice i: devices){
            int min = calculatedData[0][i.uid];
            for (FogNode j: nodes){
                if (calculatedData[j.uid][i.uid] <= min){
                    min = calculatedData[j.uid][i.uid];
                    i.fogId = j.uid;
                }
            }
            i.fogNodeDistance=min;
        }
        for (IotDevice i: devices){
            for (FogNode j: nodes){
                if (j.uid == i.fogId){
                    j.iotDevices += 1;

                }
            }
        }
        for (FogNode j: nodes){
            System.out.println("Fog Id "+j.uid+" total device connected = "+j.iotDevices);
        }
    }
}
