package es.urjc.etsii.dad.langfilter.thrift;

import org.apache.thrift.TException;

public class CrossPlatformServiceImpl implements CrossPlatformService.Iface {

    @Override
    public String sendMessage(String message)
      throws InvalidOperationException, TException {
        System.out.println("HERE IN THE SERVER");
        return "MESSAGE FILTERED";
      }

}
