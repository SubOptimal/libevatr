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
package sub.optimal.evatr.util;

/**
 * Tag name definitions which are used in the XML-RPC request and the XML response.
 */
public abstract interface InterfaceXmlRpcTagnames {

	/**
	 * Tag of request/response message field with the VAT identification number of the
	 * requesting company.
	 */
	final static String TAG_TAXID_1 = "USTID_1";

	/**
	 * Tag of request/response message field with the VAT identification number of the
	 * foreign company.
	 */
	final static String TAG_TAXID_2 = "USTID_2";

	/**
	 * Tag of request/response message field with the company name of the foreign
	 * company.
	 */
	final static String TAG_COMPANY_NAME = "FIRMENNAME";

	/**
	 * Tag of request/response message field with the street name of the foreign
	 * company.
	 */
	final static String TAG_STREET = "STRASSE";

	/**
	 * Tag of request/response message field with the zip code of the foreign company.
	 */
	final static String TAG_ZIP = "PLZ";

	/**
	 * Tag of request/response message field with the city of the foreign company.
	 */
	final static String TAG_CITY = "ORT";

	/**
	 * Tag of request/response message field which hold the information if a printed
	 * confirmation was also requested.
	 */
	final static String TAG_PRINT = "DRUCK";

	/**
	 * Tag of response message field with the date of the request.
	 */
	final static String TAG_DATE = "DATUM";

	/**
	 * Tag of response message field with the time of the request.
	 */
	final static String TAG_TIME = "UHRZEIT";

	/**
	 * Tag of response message field with the return code of the request.
	 */
	final static String TAG_ERRORCODE = "ERRORCODE";

	/**
	 * Tag of response message field with the result of company name validation.
	 */
	final static String TAG_STATUS_NAME = "ERG_NAME";

	/**
	 * Tag of response message field with the result of street name validation.
	 */
	final static String TAG_STATUS_STREET = "ERG_STR";

	/**
	 * Tag of response message field with the result of zip code validation.
	 */
	final static String TAG_STATUS_ZIP = "ERG_PLZ";

	/**
	 * Tag of response message field with the result of city validation.
	 */
	final static String TAG_STATUS_CITY = "ERG_ORT";

	/**
	 * Tag of response message field with the VAT identification number "valid
	 * from date". (only filled when the return code is 203 or 204)
	 */
	final static String TAG_VALID_FROM = "GUELTIG_AB";

	/**
	 * Tag of response message field with the VAT identification number "valid
	 * till date". (only filled when the return code 204)
	 */
	final static String TAG_VALID_TO = "GUELTIG_BIS";

}
