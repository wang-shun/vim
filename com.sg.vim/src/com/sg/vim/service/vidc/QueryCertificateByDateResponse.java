package com.sg.vim.service.vidc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;QueryCertificateByDateResult&quot; type=&quot;{http://service.vidc.info/certificaterequest}QueryResult&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "queryCertificateByDateResult" })
@XmlRootElement(name = "QueryCertificateByDateResponse")
public class QueryCertificateByDateResponse {

	@XmlElement(name = "QueryCertificateByDateResult")
	protected QueryResult queryCertificateByDateResult;

	/**
	 * Gets the value of the queryCertificateByDateResult property.
	 * 
	 * @return possible object is {@link QueryResult }
	 * 
	 */
	public QueryResult getQueryCertificateByDateResult() {
		return queryCertificateByDateResult;
	}

	/**
	 * Sets the value of the queryCertificateByDateResult property.
	 * 
	 * @param value
	 *            allowed object is {@link QueryResult }
	 * 
	 */
	public void setQueryCertificateByDateResult(QueryResult value) {
		this.queryCertificateByDateResult = value;
	}

}
