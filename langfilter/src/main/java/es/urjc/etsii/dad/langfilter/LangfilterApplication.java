package es.urjc.etsii.dad.langfilter;

import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.urjc.etsii.dad.langfilter.thrift.CrossPlatformServiceServer;

@SpringBootApplication
public class LangfilterApplication {

	public static void main(String[] args) throws TTransportException {
		SpringApplication.run(LangfilterApplication.class, args);

		CrossPlatformServiceServer server = new CrossPlatformServiceServer();
        server.start();
	}

}
