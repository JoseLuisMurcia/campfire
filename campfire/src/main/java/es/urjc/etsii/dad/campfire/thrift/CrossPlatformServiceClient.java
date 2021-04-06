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
        System.out.println("CrossPlatformServiceClient CREATED");
    }
    
    public boolean ping() {

        System.out.println("PING HAS BEEN CALLED");
        try {
            TTransport transport;

            transport = new TSocket("127.0.0.1", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            CrossPlatformService.Client client = new CrossPlatformService.Client(protocol);

            System.out.print("Calling remote method...");

            boolean result = client.ping();

            System.out.println("done.");

            transport.close();

            return result;
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException x) {
            x.printStackTrace();
        }

        return false;
    }
}
