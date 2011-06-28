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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.xml.sax.SAXException;

import sub.optimal.evatr.XmlResponse;

/**
 * Demonstrate the usage of class <code>XmlResponse</code> to parse XML response
 * messages returned by the XML-RPC interface of federal central office for
 * taxes to confirm value added tax identification numbers and addresses of
 * foreign (european) companies.<br>
 * <br>
 * see in the test/ folder of the libevatr source distribution for some example
 * XML responses.<br>
 * <b>note:</b> The included examples are syntactically correct, but do not
 * contain valid foreign company data.
 */

public class ExampleResponse {

	/**
	 * @param args
	 *            name of a file with a XML response
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void main(String[] args) throws IOException, SAXException {

		// read the XML response message from a file
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		StringBuffer answer = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			answer.append(line).append(System.getProperty("line.separator"));
		}

		// parse the XML response as it would be returned by the RpcRequest.
		XmlResponse xmlResponse = new XmlResponse(answer.toString());
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
