package es.urjc.etsii.dad.campfire.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class CrossPlatformServiceClient {

    public CrossPlatformServiceClient()
    {
    }

    public String sendMessage(String message){
        try {
            TTransport transport;

            transport = new TSocket("internal_loadbalancer", 9080);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            CrossPlatformService.Client client = new CrossPlatformService.Client(protocol);

            System.out.println("Calling remote method sendMessage...");

            String result = client.sendMessage(message);

            System.out.println("done.");

            transport.close();

            return result;
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException x) {
            x.printStackTrace();
        }

        //return "message did not reach internal service";
        return message + " (not filtered)";
    }
}
