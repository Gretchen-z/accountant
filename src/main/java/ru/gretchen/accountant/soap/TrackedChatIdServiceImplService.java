
package ru.gretchen.accountant.soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TrackedChatIdServiceImplService", targetNamespace = "http://Service.soap.ramz.com/", wsdlLocation = "https://ff2d-82-179-72-69.eu.ngrok.io/notification?wsdl")
public class TrackedChatIdServiceImplService
    extends Service
{

    private final static URL TRACKEDCHATIDSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException TRACKEDCHATIDSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName TRACKEDCHATIDSERVICEIMPLSERVICE_QNAME = new QName("http://Service.soap.ramz.com/", "TrackedChatIdServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://ff2d-82-179-72-69.eu.ngrok.io/notification?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TRACKEDCHATIDSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        TRACKEDCHATIDSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public TrackedChatIdServiceImplService() {
        super(__getWsdlLocation(), TRACKEDCHATIDSERVICEIMPLSERVICE_QNAME);
    }

    public TrackedChatIdServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TRACKEDCHATIDSERVICEIMPLSERVICE_QNAME, features);
    }

    public TrackedChatIdServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, TRACKEDCHATIDSERVICEIMPLSERVICE_QNAME);
    }

    public TrackedChatIdServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TRACKEDCHATIDSERVICEIMPLSERVICE_QNAME, features);
    }

    public TrackedChatIdServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TrackedChatIdServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TrackedChatIdService
     */
    @WebEndpoint(name = "TrackedChatIdServiceImplPort")
    public TrackedChatIdService getTrackedChatIdServiceImplPort() {
        return super.getPort(new QName("http://Service.soap.ramz.com/", "TrackedChatIdServiceImplPort"), TrackedChatIdService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TrackedChatIdService
     */
    @WebEndpoint(name = "TrackedChatIdServiceImplPort")
    public TrackedChatIdService getTrackedChatIdServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://Service.soap.ramz.com/", "TrackedChatIdServiceImplPort"), TrackedChatIdService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TRACKEDCHATIDSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw TRACKEDCHATIDSERVICEIMPLSERVICE_EXCEPTION;
        }
        return TRACKEDCHATIDSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
