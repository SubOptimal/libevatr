/*
    Copyright 2011 Frank Dietrich
   
    This software distributed under the terms of the 
    GNU General Public License Version 3 (GPLv3) of the License, 
    or (at your option) any later version.

    This file is part of the library libevatr.

    libevatr is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 3 of the License, or
    (at your option) any later version.

    libevatr is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sub.optimal.evatr;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import sub.optimal.evatr.util.RpcParameterException;

/**
 * Class <code>RpcRequest</code> provide access to the automatic confirmation of
 * foreign value added tax identification numbers.<br>
 * see: <a
 * href="http://evatr.bff-online.de/eVatR/xmlrpc/">http://evatr.bff-online
 * .de/eVatR/xmlrpc/</a><br>
 * <br>
 * There are two different request modes.<br>
 * <b>simple request</b> - which only validates the foreign company VAT identification
 * number.<br>
 * <b>qualified request</b> - which validates also additional address parameters.<br>
 * see <a
 * href="http://evatr.bff-online.de/eVatR/xmlrpc/schnittstelle">http://evatr
 * .bff-online.de/eVatR/xmlrpc/schnittstelle</a> which parameters are mandatory
 * for each kind of request.
 */
public final class RpcRequest {
	/**
	 * URL of the electronic VAT request interface.
	 */
	private static final String BZST_URL = "http://evatr.bff-online.de";
	/**
	 * Name of the XML-RPC service.
	 */
	private static final String XMLRPC_SERVICE_NAME = "evatrRPC";

	private static final String WANT_PRINT = "ja";
	private static final String WANT_NO_PRINT = "nein";

	/**
	 * All Fields of the XML-RPC request. It's necessary to send all fields,
	 * specified or with the default value, to the XML-RPC service.<br>
	 * see also: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/schnittstelle">
	 * http://evatr.bff-online.de/eVatR/xmlrpc/schnittstelle</a>
	 */
	private String vatId1 = null;
	private String vatId2 = null;
	private String companyName = "";
	private String street = "";
	private String zipCode = "";
	private String city = "";
	private String print = WANT_NO_PRINT;

	/**
	 * To store the XML-RPC request parameters.
	 */
	private Vector<String> params;

	private XmlRpcClient rpcClient;

	/**
	 * Constructor for the RpcRequest object.
	 * 
	 * @throws MalformedURLException
	 *             thrown when property BZST_URL contains an invalid URL.
	 */
	public RpcRequest() throws MalformedURLException {
		XmlRpcClientConfigImpl clientConf = new XmlRpcClientConfigImpl();
		clientConf.setServerURL(new URL(BZST_URL));
		rpcClient = new XmlRpcClient();
		rpcClient.setConfig(clientConf);
	}

	/**
	 * Set the VAT identification number (Umsatzsteuer-ID) of requesting
	 * company. The company must have a valid VAT identification number in
	 * Germany.
	 * 
	 * @param taxId
	 *            your own company VAT identification number (mandatory for
	 *            simple and qualified requests), value is case sensitive
	 */
	public void setVatId1(String taxId) {
		this.vatId1 = taxId;
	}

	/**
	 * Set VAT identification number (Umsatzsteuer-ID) of the foreign (european)
	 * company.
	 * 
	 * @param taxId
	 *            VAT identification number of the foreign company (mandatory
	 *            for simple and qualified requests), value is case sensitive
	 */
	public void setVatId2(String taxId) {
		this.vatId2 = taxId;
	}

	/**
	 * Set the name of the foreign company.<br>
	 * This value will be validated against the name linked to the foreign
	 * company VAT identification number.
	 * 
	 * @param name
	 *            foreign company name (mandatory for qualified requests)
	 */
	public void setCompanyName(String name) {
		this.companyName = (name == null ? "" : name);
	}

	/**
	 * Set the street of the foreign company.<br>
	 * This value will be validated against the street linked to the foreign
	 * company VAT identification number.*
	 * 
	 * @param street
	 *            foreign company name (optional for qualified requests)
	 */
	public void setStreet(String street) {
		this.street = (street == null ? "" : street);
	}

	/**
	 * Set the zip code of the foreign company.<br>
	 * This value will be validated against the zip code linked to the foreign
	 * company VAT identification number.*
	 * 
	 * @param zipCode
	 *            foreign company zip code (optional for qualified requests)
	 */
	public void setZip(String zipCode) {
		this.zipCode = (zipCode == null ? "" : zipCode);
	}

	/**
	 * Set the city of the foreign company.<br>
	 * This value will be validated against the city linked to the foreign
	 * company VAT identification number.
	 * 
	 * @param city
	 *            foreign company city (mandatory for qualified requests)
	 */
	public void setCity(String city) {
		this.city = (city == null ? "" : city);
	}

	/**
	 * Execute the request with requirement of an official confirmation message.<br>
	 * NOTE: A printed confirmation for your request will be send by postal
	 * mail. The confirmation will be send to the address linked to your company
	 * VAT ID (parameter vatId1).<br>
	 * <br>
	 * To save the environment, request a printed confirmation only in case you
	 * really need it for your business.
	 */
	public void setWantPrint() {
		this.print = WANT_PRINT;
	}

	/**
	 * Execute the request without requirement of an official confirmation
	 * message. (default)
	 */
	public void setWantNoPrint() {
		this.print = WANT_NO_PRINT;
	}

	/**
	 * Validate the parameters before the request is send.
	 * 
	 * @throws {@link RpcParameterException}
	 */
	private void validateParams() throws RpcParameterException {
		if (this.vatId1 == null || this.vatId2 == null) {
			throw new RpcParameterException("parameter taxId1 (" + this.vatId1
					+ ") and taxId2 (" + this.vatId2 + ") are mandatory");
		}
		if (this.vatId1.equalsIgnoreCase(this.vatId2)) {
			throw new RpcParameterException("parameter VatId1 (" + this.vatId1
					+ ") and VatId2 (" + this.vatId2 + ") cannot be equal");
		}
		if (("".equals(this.companyName) && "".equals(this.city))
				|| (!"".equals(this.companyName) && !"".equals(this.city))) {
		} else {
			throw new RpcParameterException("parameter companyName ("
					+ this.companyName + ") and city (" + this.city
					+ ") must be either both omitted (simple request)"
					+ " or both specified (qualified request)");
		}
	}

	/**
	 * Initialise the XML-RPC request parameters with the values specified for
	 * the RpcRequest object.
	 * 
	 * @throws {@link RpcParameterException}
	 */
	private void initRequestParams() throws RpcParameterException {
		validateParams();
		params = new Vector<String>();
		params.add(this.vatId1);
		params.add(this.vatId2);
		params.add(this.companyName);
		params.add(this.city);
		params.add(this.zipCode);
		params.add(this.street);
		params.add(this.print);
	}

	/**
	 * Executes the request and return the result in XML format.<br>
	 * The raw format of the answer is described at URL <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/aufbau">http
	 * ://evatr.bff-online.de/eVatR/xmlrpc/aufbau</a>
	 * 
	 * @return the result of the request in XML format
	 * @throws {@link XmlRpcException}
	 * @throws {@link RpcParameterException}
	 */
	public String execute() throws XmlRpcException, RpcParameterException {
		initRequestParams();
		String answer = (String) rpcClient.execute(XMLRPC_SERVICE_NAME, params);
		return answer;
	}
}
