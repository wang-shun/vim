package com.sg.vim.service.vidc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

import com.sg.vim.VimUtils;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390- Generated source version:
 * 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * CertificateRequestService service = new CertificateRequestService();
 * CertificateRequestServiceSoap portType = service.getCertificateRequestServiceSoap();
 * portType.helloWorld(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "CertificateRequestService", targetNamespace = "http://service.vidc.info/certificaterequest", wsdlLocation = "http://localhost:9901/CertificateRequestService.asmx?WSDL")
public class CertificateRequestService extends Service {

    private final static URL CERTIFICATEREQUESTSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger
            .getLogger(com.sg.vim.service.vidc.CertificateRequestService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.sg.vim.service.vidc.CertificateRequestService.class
                    .getResource(".");
            url = new URL(baseUrl, "http://" + VimUtils.LOCAL_SERVER
                    + "/CertificateRequestService.asmx?WSDL");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://"
                    + VimUtils.LOCAL_SERVER
                    + "/CertificateRequestService.asmx?WSDL', retrying as a local file");
            logger.warning(e.getMessage());
        }
        CERTIFICATEREQUESTSERVICE_WSDL_LOCATION = url;
    }

    public CertificateRequestService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CertificateRequestService() {
        super(CERTIFICATEREQUESTSERVICE_WSDL_LOCATION, new QName(
                "http://service.vidc.info/certificaterequest", "CertificateRequestService"));
    }

    /**
     * 
     * @return returns CertificateRequestServiceSoap
     */
    @WebEndpoint(name = "CertificateRequestServiceSoap")
    public CertificateRequestServiceSoap getCertificateRequestServiceSoap() {
        return super.getPort(new QName("http://service.vidc.info/certificaterequest",
                "CertificateRequestServiceSoap"), CertificateRequestServiceSoap.class);
    }

    /**
     * 
     * @return returns CertificateRequestServiceSoap
     */
    @WebEndpoint(name = "CertificateRequestServiceSoap12")
    public CertificateRequestServiceSoap getCertificateRequestServiceSoap12() {
        return super.getPort(new QName("http://service.vidc.info/certificaterequest",
                "CertificateRequestServiceSoap12"), CertificateRequestServiceSoap.class);
    }

}
