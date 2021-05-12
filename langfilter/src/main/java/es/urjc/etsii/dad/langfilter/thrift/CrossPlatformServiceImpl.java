package es.urjc.etsii.dad.langfilter.thrift;

import org.apache.thrift.TException;

import es.urjc.etsii.dad.langfilter.service.LangfilterService;

public class CrossPlatformServiceImpl implements CrossPlatformService.Iface {

    private LangfilterService langfilterService = new LangfilterService();

    @Override
    public String sendMessage(String message)
      throws InvalidOperationException, TException {
        return langfilterService.filter(message);
      }

}
