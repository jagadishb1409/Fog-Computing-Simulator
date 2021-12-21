package DistanceCalculator;

import entities.FogNode;
import entities.IotDevice;
import parameters.Params;

import java.util.Arrays;

import java.util.*;

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
        for (IotDevice i : devices) {
            int min = Params.MAX_DISTANCE;
            for (FogNode j : nodes) {
                if (calculatedData[j.uid][i.uid] <= min) {
                    i.fogId = j.uid;
                    j.connectedDevices.add(i.uid);
                }
            }
            i.fogNodeDistance = min;
        }
        for (IotDevice i : devices) {
            for (FogNode j : nodes) {
                if (j.uid == i.fogId) {
                    j.iotDevices += 1;

                }
            }
        }
        // Calculate the neighboring nodes distance and save it to the fog node
        for (FogNode j : nodes) {
            for (FogNode k : nodes) {
                if (k.uid != j.uid) {
                    int cal;
                    int x = (int) (j.xaxis - k.xaxis);
                    int y = (int) (j.yaxis - k.yaxis);
                    int square = (int) (Math.pow(x, 2) + Math.pow(y, 2));
                    cal = (int) Math.sqrt(square);
                    System.out.println("Distance between " + j.uid + " and " + k.uid + " is " + cal);
                    j.addNeighbouringNodes(k.uid, cal);
                }
            }
        }
        System.out.println("Iot devices connected to nearest fog nodes");
        for (FogNode j : nodes) {
            System.out.println("Fog Id " + j.uid + " total device connected = " + j.iotDevices);
        }

        for (FogNode j : nodes) {
            System.out.println("Fog Id " + j.uid + " connected devices = " + j.connectedDevices);
        }

        for (FogNode j : nodes) {
            System.out.println("Fog Id " + j.uid + " neighboring nodes = " + j.neighbouringNodes);
            System.out.println("Fog Id " + j.uid + " neighboring nodes distance = " + Arrays.toString(j.neighboruingNodesDistance));
            System.out.println("Fog Id " + j.uid + " X axis = " + j.xaxis + " Y axis = " + j.yaxis);
            // print list of connected nodes
            System.out.println("Fog Id " + j.uid + " connected Nodes = " + Arrays.toString(j.connectedNodes));
        }
    }
}
