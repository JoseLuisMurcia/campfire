package es.urjc.etsii.dad.langfilter.thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class CrossPlatformServiceServer {
    TSimpleServer server;
    public void start() throws TTransportException {
        TServerTransport serverTransport = new TServerSocket(9092);
        server = new TSimpleServer(new TServer.Args(serverTransport)
          .processor(new CrossPlatformService.Processor<>(new CrossPlatformServiceImpl())));

        System.out.println("Starting the server... ");
        server.serve();

        System.out.println("------------server done------------");
    }

    public void stop() {
        if (server != null && server.isServing()) {
            System.out.println("Stopping the server... ");

            server.stop();

            System.out.println("------------server done------------");
        }
    }
}
