package es.urjc.etsii.dad.campfire.thrift;

import java.util.Collections;
import java.util.List;

import org.apache.thrift.TException;

public class CrossPlatformServiceImpl implements CrossPlatformService.Iface {

    @Override
    public CrossPlatformResource get(int id) 
      throws InvalidOperationException, TException {
        return new CrossPlatformResource();
    }

    @Override
    public void save(CrossPlatformResource resource) 
      throws InvalidOperationException, TException {
    }

    @Override
    public List<CrossPlatformResource> getList() 
      throws InvalidOperationException, TException {
        return Collections.emptyList();
    }

    @Override
    public boolean ping() throws InvalidOperationException, TException {
        return true;
    }
}
