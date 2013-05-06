package info.vidc.CertificateRequestService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "CertificateRequestServiceSoap", targetNamespace = "http://service.vidc.info/certificaterequest")
public interface CertificateRequestServiceSoap {

	/**
	 * 
	 * @return returns java.lang.String
	 */
	@WebMethod(operationName = "HelloWorld", action = "http://service.vidc.info/certificaterequest/HelloWorld")
	@WebResult(name = "HelloWorldResult", targetNamespace = "http://service.vidc.info/certificaterequest")
	@RequestWrapper(localName = "HelloWorld", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.HelloWorld")
	@ResponseWrapper(localName = "HelloWorldResponse", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.HelloWorldResponse")
	public String helloWorld();

	/**
	 * 
	 * @return returns java.lang.String
	 */
	@WebMethod(operationName = "HelloWorld_Remote", action = "http://service.vidc.info/certificaterequest/HelloWorld_Remote")
	@WebResult(name = "HelloWorld_RemoteResult", targetNamespace = "http://service.vidc.info/certificaterequest")
	@RequestWrapper(localName = "HelloWorld_Remote", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.HelloWorldRemote")
	@ResponseWrapper(localName = "HelloWorld_RemoteResponse", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.HelloWorldRemoteResponse")
	public String helloWorldRemote();

	/**
	 * 
	 * @param wzhgzbh
	 * @return returns info.vidc.CertificateRequestService.QueryResult
	 */
	@WebMethod(operationName = "QueryCertificateByWZHGZBH", action = "http://service.vidc.info/certificaterequest/QueryCertificateByWZHGZBH")
	@WebResult(name = "QueryCertificateByWZHGZBHResult", targetNamespace = "http://service.vidc.info/certificaterequest")
	@RequestWrapper(localName = "QueryCertificateByWZHGZBH", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.QueryCertificateByWZHGZBH")
	@ResponseWrapper(localName = "QueryCertificateByWZHGZBHResponse", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.QueryCertificateByWZHGZBHResponse")
	public QueryResult queryCertificateByWZHGZBH(
			@WebParam(name = "wzhgzbh", targetNamespace = "http://service.vidc.info/certificaterequest") String wzhgzbh);

	/**
	 * 
	 * @param beginTime
	 * @param rqlx
	 * @param pageSize
	 * @param pageSite
	 * @param endTime
	 * @return returns info.vidc.CertificateRequestService.QueryResult
	 */
	@WebMethod(operationName = "QueryCertificateByDate", action = "http://service.vidc.info/certificaterequest/QueryCertificateByDate")
	@WebResult(name = "QueryCertificateByDateResult", targetNamespace = "http://service.vidc.info/certificaterequest")
	@RequestWrapper(localName = "QueryCertificateByDate", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.QueryCertificateByDate")
	@ResponseWrapper(localName = "QueryCertificateByDateResponse", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.QueryCertificateByDateResponse")
	public QueryResult queryCertificateByDate(
			@WebParam(name = "rqlx", targetNamespace = "http://service.vidc.info/certificaterequest") int rqlx,
			@WebParam(name = "beginTime", targetNamespace = "http://service.vidc.info/certificaterequest") String beginTime,
			@WebParam(name = "endTime", targetNamespace = "http://service.vidc.info/certificaterequest") String endTime,
			@WebParam(name = "pageSize", targetNamespace = "http://service.vidc.info/certificaterequest") int pageSize,
			@WebParam(name = "pageSite", targetNamespace = "http://service.vidc.info/certificaterequest") int pageSite);

	/**
	 * 
	 * @param data
	 * @return returns info.vidc.CertificateRequestService.OperateResult
	 */
	@WebMethod(operationName = "UploadInsert_Ent", action = "http://service.vidc.info/certificaterequest/UploadInsert_Ent")
	@WebResult(name = "UploadInsert_EntResult", targetNamespace = "http://service.vidc.info/certificaterequest")
	@RequestWrapper(localName = "UploadInsert_Ent", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.UploadInsertEnt")
	@ResponseWrapper(localName = "UploadInsert_EntResponse", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.UploadInsertEntResponse")
	public OperateResult uploadInsertEnt(
			@WebParam(name = "data", targetNamespace = "http://service.vidc.info/certificaterequest") ArrayOfCertificateInfo data);

	/**
	 * 
	 * @param memo
	 * @param data
	 * @return returns info.vidc.CertificateRequestService.OperateResult
	 */
	@WebMethod(operationName = "UploadOverTime_Ent", action = "http://service.vidc.info/certificaterequest/UploadOverTime_Ent")
	@WebResult(name = "UploadOverTime_EntResult", targetNamespace = "http://service.vidc.info/certificaterequest")
	@RequestWrapper(localName = "UploadOverTime_Ent", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.UploadOverTimeEnt")
	@ResponseWrapper(localName = "UploadOverTime_EntResponse", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.UploadOverTimeEntResponse")
	public OperateResult uploadOverTimeEnt(
			@WebParam(name = "data", targetNamespace = "http://service.vidc.info/certificaterequest") ArrayOfCertificateInfo data,
			@WebParam(name = "memo", targetNamespace = "http://service.vidc.info/certificaterequest") String memo);

	/**
	 * 
	 * @param memo
	 * @param data
	 * @return returns info.vidc.CertificateRequestService.OperateResult
	 */
	@WebMethod(operationName = "UploadUpdate_Ent", action = "http://service.vidc.info/certificaterequest/UploadUpdate_Ent")
	@WebResult(name = "UploadUpdate_EntResult", targetNamespace = "http://service.vidc.info/certificaterequest")
	@RequestWrapper(localName = "UploadUpdate_Ent", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.UploadUpdateEnt")
	@ResponseWrapper(localName = "UploadUpdate_EntResponse", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.UploadUpdateEntResponse")
	public OperateResult uploadUpdateEnt(
			@WebParam(name = "data", targetNamespace = "http://service.vidc.info/certificaterequest") ArrayOfCertificateInfo data,
			@WebParam(name = "memo", targetNamespace = "http://service.vidc.info/certificaterequest") String memo);

	/**
	 * 
	 * @param memo
	 * @param wzhgzbh
	 * @return returns info.vidc.CertificateRequestService.OperateResult
	 */
	@WebMethod(operationName = "UploadDelete_Ent", action = "http://service.vidc.info/certificaterequest/UploadDelete_Ent")
	@WebResult(name = "UploadDelete_EntResult", targetNamespace = "http://service.vidc.info/certificaterequest")
	@RequestWrapper(localName = "UploadDelete_Ent", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.UploadDeleteEnt")
	@ResponseWrapper(localName = "UploadDelete_EntResponse", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.UploadDeleteEntResponse")
	public OperateResult uploadDeleteEnt(
			@WebParam(name = "wzhgzbh", targetNamespace = "http://service.vidc.info/certificaterequest") ArrayOfString wzhgzbh,
			@WebParam(name = "memo", targetNamespace = "http://service.vidc.info/certificaterequest") String memo);

	/**
	 * 
	 * @param hardwareInfo
	 * @return returns int
	 */
	@WebMethod(operationName = "RegHardwareInfo", action = "http://service.vidc.info/certificaterequest/RegHardwareInfo")
	@WebResult(name = "RegHardwareInfoResult", targetNamespace = "http://service.vidc.info/certificaterequest")
	@RequestWrapper(localName = "RegHardwareInfo", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.RegHardwareInfo")
	@ResponseWrapper(localName = "RegHardwareInfoResponse", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.RegHardwareInfoResponse")
	public int regHardwareInfo(
			@WebParam(name = "hardwareInfo", targetNamespace = "http://service.vidc.info/certificaterequest") String hardwareInfo);

	/**
	 * 
	 * @return returns java.lang.String
	 */
	@WebMethod(operationName = "GetHardwareInfoFromServer", action = "http://service.vidc.info/certificaterequest/GetHardwareInfoFromServer")
	@WebResult(name = "GetHardwareInfoFromServerResult", targetNamespace = "http://service.vidc.info/certificaterequest")
	@RequestWrapper(localName = "GetHardwareInfoFromServer", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.GetHardwareInfoFromServer")
	@ResponseWrapper(localName = "GetHardwareInfoFromServerResponse", targetNamespace = "http://service.vidc.info/certificaterequest", className = "info.vidc.CertificateRequestService.GetHardwareInfoFromServerResponse")
	public String getHardwareInfoFromServer();

}
