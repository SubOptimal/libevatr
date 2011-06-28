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
 * This <code>Exception</code> is thrown when a required request parameter is
 * not specified or did not pass the general validation rules.<br>
 */
public final class RpcParameterException extends Exception {

	private static final long serialVersionUID = 6943319372500370915L;

	RpcParameterException() {
		super();
	}

	public RpcParameterException(String message) {
		super(message);
	}
}
