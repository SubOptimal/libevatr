/*
    Copyright 2011 Frank Dietrich
   
    This software distributed under the terms of the 
    GNU General Public License Version 3 (GPLv3) of the License, 
    or (at your option) any later version.

    This file is part of the examples source for library libevatr.

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
package sub.optimal;

import java.io.IOException;

import org.apache.xmlrpc.XmlRpcException;
import org.xml.sax.SAXException;

import sub.optimal.evatr.RpcRequest;
import sub.optimal.evatr.XmlResponse;
import sub.optimal.evatr.util.RpcParameterException;

/**
 * Demonstrate the usage of library libevatr to access the XML-RPC interface of
 * federal central office for taxes to confirm value added tax identification
 * numbers and addresses of foreign (european) companies.<br>
 * <br>
 * <b>note:</b> As there are no VAT identification numbers for testing purpose
 * of this service, you must provide valid values for the request parameters.
 */

public class ExampleComplete {

	/**
	 * @param args
	 *            no arguments expected
	 * @throws IOException
	 * @throws SAXException
	 * @throws RpcParameterException
	 * @throws XmlRpcException
	 * @throws RpcParameterException
	 */
	public static void main(String[] args) throws SAXException, IOException,
			XmlRpcException, RpcParameterException {
		RpcRequest request = new RpcRequest();
		// mandatory values for "simple" and "qualified" requests
		request.setVatId1("your VAT ID in Germany");
		request.setVatId2("foreign (european) company VAT ID");
		// mandatory values for "qualified" requests
		request.setCompanyName("foreign company name");
		request.setCity("foreign company city");
		// optional values for "qualified" requests
		request.setZip("foreign company zip code");
		request.setStreet("foreign company street name");
		// optional parameter for "simple" and "qualified" requests
		request.setWantNoPrint();

		XmlResponse xmlResponse = new XmlResponse(request.execute());
		System.out.println("eigene Daten...");
		System.out.println("  UstID     : " + xmlResponse.getVatId1());
		System.out.println("Abfrage Daten (EU)...");
		System.out.println("  UstID     : " + xmlResponse.getVatId2());
		System.out.println("  Firmenname: " + xmlResponse.getCompanyName());
		System.out.println("  Straße    : " + xmlResponse.getStreet());
		System.out.println("  PLZ       : " + xmlResponse.getZip());
		System.out.println("  Ort       : " + xmlResponse.getCity());
		System.out.println("  Druck     : " + xmlResponse.getPrint());
		System.out.println("Abfrage Ergebnis...");
		System.out.println("  Datum     : " + xmlResponse.getDate());
		System.out.println("  Uhrzeit   : " + xmlResponse.getTime());
		System.out.println("  Errorcode : " + xmlResponse.getErrorCode()
				+ " - " + xmlResponse.getErrorText());
		System.out.println("  Firmenname: " + xmlResponse.getNameStatusCode()
				+ " - " + xmlResponse.getNameStatusInfo());
		System.out.println("  Straße    : " + xmlResponse.getStreetStatusCode()
				+ " - " + xmlResponse.getStreetStatusInfo());
		System.out.println("  PLZ       : " + xmlResponse.getZipStatusCode()
				+ " - " + xmlResponse.getZipStatusInfo());
		System.out.println("  Ort       : " + xmlResponse.getCityStatusCode()
				+ " - " + xmlResponse.getCityStatusInfo());
		switch (xmlResponse.getErrorCode()) {
		case 203:
			System.out.println("  gültig ab : " + xmlResponse.getValidFrom());
		case 204:
			System.out.println("  gültig bis: " + xmlResponse.getValidTo());
			break;
		default:
			break;
		}
	}
}
