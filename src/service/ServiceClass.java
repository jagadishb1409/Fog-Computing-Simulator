package service;
import entities.FogNode;
import entities.IotDevice;
import parameters.Params;

import java.util.ArrayList;
import java.util.Random;

public class ServiceClass {
    public ArrayList<IotDevice> createuser()
    {
        Random r = new Random();
        r.setSeed(1178);
        ArrayList<IotDevice> sts = new ArrayList<IotDevice>();
        for (int i = 0; i < Params.NUM_OF_DEVICES; i++){
            IotDevice s = new IotDevice();
            s.uid = i;
            s.xaxis = r.nextInt(Params.LENGTH);
            s.yaxis = r.nextInt(Params.LENGTH);
            sts.add(s);
        }
        return sts;
    }
    public ArrayList<FogNode> createnodes(){
        Random r = new Random();
        r.setSeed(19263);
        ArrayList<FogNode> devices = new ArrayList<FogNode>();
        for (int i = 0; i < Params.NUM_OF_NODES; i++){
            FogNode s = new FogNode();
            s.uid = i;
            s.xaxis = r.nextInt(Params.LENGTH);
            s.yaxis = r.nextInt(Params.LENGTH);
            devices.add(s);
        }
        return devices;
    }
}
