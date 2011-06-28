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
 * Class provides the description for status codes returned by additional
 * validations within a qualified request.
 */
public final class StatusCodes {

	private StatusCodes() {
	}

	/**
	 * Returns the status code description for the validation of parameters
	 * company name, zip code, city and street in a qualified request.<br>
	 * see also: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/aufbau">http://evatr.bff-
	 * online.de/eVatR/xmlrpc/aufbau</a>
	 * 
	 * @param statusCode
	 *            the status code returned by in the XML request
	 * @return explanation of the passed status code
	 */
	public static String getStatusInfo(String statusCode) {
		String info;
		if ("".equals(statusCode) || statusCode == null) {
			return "";
		}
		if ("A".equals(statusCode)) {
			info = "stimmt überein";
		} else if ("B".equals(statusCode)) {
			info = "stimmt nicht überein";
		} else if ("C".equals(statusCode)) {
			info = "nicht angefragt";
		} else if ("D".equals(statusCode)) {
			info = "vom EU-Mitgliedsstaat nicht mitgeteilt";
		} else {
			info = "unbekanter Statuscode";
		}
		return info;
	}
}
