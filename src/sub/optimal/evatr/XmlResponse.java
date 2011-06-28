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

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.Hashtable;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import sub.optimal.evatr.util.InterfaceXmlRpcTagnames;
import sub.optimal.evatr.util.ReturnCodes;
import sub.optimal.evatr.util.StatusCodes;

/**
 * Class <code>XmlResponse</code> parses the XML response message returned from
 * execution of <code>XmlRequest.execute()</code> and provide access to all
 * fields in the response.<br>
 * see: <a
 * href="http://evatr.bff-online.de/eVatR/xmlrpc/aufbau">http://evatr.bff
 * -online.de/eVatR/xmlrpc/aufbau</a><br>
 * <br>
 */
public final class XmlResponse implements InterfaceXmlRpcTagnames {
	private final static String XML_TAG_DATA = "data";
	private final static String XML_TAG_STRING = "string";

	/**
	 * To store one data block of the XML response message. A data block
	 * contains two string elements. The first is the field name and the second
	 * is the field value.
	 */
	private String[] dataBlock;
	private int valueIndex;

	/**
	 * To store the response field names and values.
	 */
	private Hashtable<String, String> responses = new Hashtable<String, String>();

	/**
	 * Returns the VAT identification number of the requesting company. Included
	 * in the XML-RPC request.
	 * 
	 * @return VAT identification number of the requesting company
	 */
	public String getVatId1() {
		return responses.get(TAG_TAXID_1);
	}

	/**
	 * Returns the VAT identification number of the foreign company. Included in
	 * the XML-RPC request.
	 * 
	 * @return VAT identification number of the foreign company
	 */
	public String getVatId2() {
		return responses.get(TAG_TAXID_2);
	}

	/**
	 * Returns the name of the foreign company. Included in the XML-RPC request.
	 * 
	 * @return foreign company name
	 */
	public String getCompanyName() {
		return responses.get(TAG_COMPANY_NAME);
	}

	/**
	 * Returns the street name of the foreign company. Included in the XML-RPC
	 * request.
	 * 
	 * @return foreign company street name
	 */
	public String getStreet() {
		return responses.get(TAG_STREET);
	}

	/**
	 * Returns the zip code of the foreign company. Included in the XML-RPC
	 * request.
	 * 
	 * @return foreign company zip code
	 */
	public String getZip() {
		return responses.get(TAG_ZIP);
	}

	/**
	 * Returns the city of the foreign company. Included in the XML-RPC request.
	 * 
	 * @return city of the foreign company
	 */
	public String getCity() {
		return responses.get(TAG_CITY);
	}

	/**
	 * Returns, if a printed confirmation was requested within the XML-RPC
	 * request.
	 * 
	 * @return value of the parameter PRINT in the XML-RPC request
	 */
	public String getPrint() {
		return responses.get(TAG_PRINT);
	}

	/**
	 * Returns the date of the request.<br>
	 * format 'dd.mm.yyyy'
	 * 
	 * @return date of the request
	 */
	public String getDate() {
		return responses.get(TAG_DATE);
	}

	/**
	 * Returns the time of the request.<br>
	 * 24 hour format: 'hh:mi:ss'
	 * 
	 * @return time of the request
	 */
	public String getTime() {
		return responses.get(TAG_TIME);
	}

	/**
	 * Returns the error code of the request.<br>
	 * see also: <a href="http://evatr.bff-online.de/eVatR/xmlrpc/codes"
	 * >http://evatr.bff-online.de/eVatR/xmlrpc/codes</a>
	 * 
	 * @return error code of the request
	 */
	public int getErrorCode() {
		return Integer.valueOf(responses.get(TAG_ERRORCODE));
	}

	/**
	 * Returns the error code description of the request.<br>
	 * see also: <a href="http://evatr.bff-online.de/eVatR/xmlrpc/codes"
	 * >http://evatr.bff-online.de/eVatR/xmlrpc/codes</a>
	 * 
	 * @return error description of the request
	 */
	public String getErrorText() {
		return ReturnCodes.getReturnCodeInfo(getErrorCode());
	}

	/**
	 * Returns the status code for the validation of the company name specified
	 * in a qualified request.<br>
	 * see also: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/aufbau">http://evatr.bff-
	 * online.de/eVatR/xmlrpc/aufbau</a>
	 * 
	 * @return status code of the company name validation
	 */
	public String getNameStatusCode() {
		return responses.get(TAG_STATUS_NAME);
	}

	/**
	 * Returns the status description for the validation of the company name
	 * specified in a qualified request.<br>
	 * see also: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/aufbau">http://evatr.bff-
	 * online.de/eVatR/xmlrpc/aufbau</a>
	 * 
	 * @return status description of the company name validation
	 */
	public String getNameStatusInfo() {
		return StatusCodes.getStatusInfo(getNameStatusCode());
	}

	/**
	 * Returns the status code for the validation of the street specified in a
	 * qualified request.<br>
	 * see also: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/aufbau">http://evatr.bff-
	 * online.de/eVatR/xmlrpc/aufbau</a>
	 * 
	 * @return status code of the street validation
	 */
	public String getStreetStatusCode() {
		return responses.get(TAG_STATUS_STREET);
	}

	/**
	 * Returns the status description for the validation of the street specified
	 * in a qualified request.<br>
	 * see also: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/aufbau">http://evatr.bff-
	 * online.de/eVatR/xmlrpc/aufbau</a>
	 * 
	 * @return status description of the street validation
	 */
	public String getStreetStatusInfo() {
		return StatusCodes.getStatusInfo(getStreetStatusCode());
	}

	/**
	 * Returns the status code for the validation of the zip code specified in a
	 * qualified request.<br>
	 * see also: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/aufbau">http://evatr.bff-
	 * online.de/eVatR/xmlrpc/aufbau</a>
	 * 
	 * @return status code of the zip code validation
	 */
	public String getZipStatusCode() {
		return responses.get(TAG_STATUS_ZIP);
	}

	/**
	 * Returns the status description for the validation of the zip code
	 * specified in a qualified request.<br>
	 * see also: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/aufbau">http://evatr.bff-
	 * online.de/eVatR/xmlrpc/aufbau</a>
	 * 
	 * @return status description of the zip code validation
	 */
	public String getZipStatusInfo() {
		return StatusCodes.getStatusInfo(getZipStatusCode());
	}

	/**
	 * Returns the status code for the validation of the city specified in a
	 * qualified request.<br>
	 * see also: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/aufbau">http://evatr.bff-
	 * online.de/eVatR/xmlrpc/aufbau</a>
	 * 
	 * @return status code of the city validation
	 */
	public String getCityStatusCode() {
		return responses.get(TAG_STATUS_CITY);
	}

	/**
	 * Returns the status description for the validation of the city specified
	 * in a qualified request.<br>
	 * see also: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/aufbau">http://evatr.bff-
	 * online.de/eVatR/xmlrpc/aufbau</a>
	 * 
	 * @return status description of the city validation
	 */
	public String getCityStatusInfo() {
		return StatusCodes.getStatusInfo(getCityStatusCode());
	}

	/**
	 * Returns the date from which on the requested VAT identification number
	 * will be valid.<br>
	 * Only provided if the return code of the message is 203 or 204.<br>
	 * see also: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/codes">http://evatr
	 * .bff-online.de/eVatR/xmlrpc/codes</a>
	 * 
	 * @return date from which on the foreign VAT identification number will be
	 *         valid, format 'dd.mm.yyyy'
	 */
	public String getValidFrom() {
		return responses.get(TAG_VALID_FROM);
	}

	/**
	 * Returns the date till which the requested VAT identification number was
	 * valid.<br>
	 * Only provided if the return code of the message is 204.<br>
	 * see also: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/codes">http://evatr
	 * .bff-online.de/eVatR/xmlrpc/codes</a>
	 * 
	 * @return date till which the foreign VAT identification number was valid,
	 *         format 'dd.mm.yyyy'
	 */
	public String getValidTo() {
		return responses.get(TAG_VALID_TO);
	}

	/**
	 * Construct the XmlResponse object and parse the passed raw XML response
	 * message.
	 * 
	 * @param xmlResponse
	 *            the raw XML response message returned by the XML-RPC request
	 * @throws SAXException
	 *             thrown when the response message contains not a valid XML
	 *             structure
	 * @throws IOException
	 *             thrown when the XML parser fails
	 */
	public XmlResponse(String xmlResponse) throws SAXException, IOException {
		XMLReader parser;
		parser = XMLReaderFactory.createXMLReader();
		parser.setContentHandler(new ResponseHandler());
		char[] buffer = new char[xmlResponse.length()];
		xmlResponse.getChars(0, xmlResponse.length(), buffer, 0);
		CharArrayReader charReader = new CharArrayReader(buffer);
		parser.parse(new InputSource(charReader));
		charReader.close();
	}

	/**
	 * Private XML handler to handle specific events during parsing of the XML
	 * response message.
	 */
	class ResponseHandler extends DefaultHandler {
		boolean tagString = false;

		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			if (qName.equalsIgnoreCase(XML_TAG_DATA)) {
				dataBlock = new String[2];
				dataBlock[0] = "";
				dataBlock[1] = "";
				valueIndex = 0;
			}
			if (qName.equalsIgnoreCase(XML_TAG_STRING)) {
				tagString = true;
			}
		}

		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			if (qName.equalsIgnoreCase(XML_TAG_DATA)) {
				responses.put(dataBlock[0], dataBlock[1]);
			} else if (qName.equalsIgnoreCase(XML_TAG_STRING)) {
				tagString = false;
			}
		}

		public void characters(char[] ch, int start, int length) {
			if (tagString) {
				String stringValue = new String(ch, start, length);
				if (valueIndex == 0) {
					dataBlock[valueIndex] = stringValue.toUpperCase();
				} else if (valueIndex == 1) {
					dataBlock[valueIndex] = stringValue;
				} else {
					throw new ArrayIndexOutOfBoundsException(
							dataBlock[0]
									+ ": a response data block cannot contain more then two string elements");
				}
				tagString = false;
				valueIndex++;
			}
		}
	}
}
