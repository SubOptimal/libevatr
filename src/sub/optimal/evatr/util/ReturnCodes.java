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
 * Class provides the description for return codes included in the XML response
 * message.
 */
public final class ReturnCodes {

	private ReturnCodes() {
	}

	/**
	 * Returns the description of a return code.<br>
	 * source: <a
	 * href="http://evatr.bff-online.de/eVatR/xmlrpc/codes">http://evatr
	 * .bff-online.de/eVatR/xmlrpc/codes</a>
	 * 
	 * @param returnCode
	 *            returned by the request
	 * @return description of the return code
	 */
	public static String getReturnCodeInfo(int returnCode) {
		String info;
		switch (returnCode) {
		case 200:
			info = "Die angefragte USt-IdNr. ist gültig.";
			break;
		case 201:
			info = "Die angefragte USt-IdNr. ist ungültig.";
			break;
		case 202:
			info = "Die angefragte USt-IdNr. ist ungültig. Sie ist nicht in der Unternehmerdatei des betreffenden EU-Mitgliedstaates registriert.";
			break;
		case 203:
			info = "Die angefragte USt-IdNr. ist ungültig. Sie ist erst ab dem ... gültig (siehe Feld 'Gueltig_ab').";
			break;
		case 204:
			info = "Die angefragte USt-IdNr. ist ungültig. Sie war im Zeitraum von ... bis ... gültig (siehe Feld 'Gueltig_ab' und 'Gueltig_bis').";
			break;
		case 205:
			info = "Ihre Anfrage kann derzeit durch den angefragten EU-Mitgliedstaat oder aus anderen Gründen nicht beantwortet werden. Bitte versuchen Sie es später noch einmal. Bei wiederholten Problemen wenden Sie sich bitte an das Bundeszentralamt für Steuern - Dienstsitz Saarlouis.";
			break;
		case 206:
			info = "Ihre deutsche USt-IdNr. ist ungültig. Eine Bestätigungsanfrage ist daher nicht möglich. Den Grund hierfür können Sie beim Bundeszentralamt für Steuern - Dienstsitz Saarlouis - erfragen.";
			break;
		case 207:
			info = "Ihnen wurde die deutsche USt-IdNr. ausschliesslich zu Zwecken der Besteuerung des innergemeinschaftlichen Erwerbs erteilt. Sie sind somit nicht berechtigt, Bestätigungsanfragen zu stellen.";
			break;
		case 208:
			info = "Für die von Ihnen angefragte USt-IdNr. läuft gerade eine Anfrage von einem anderen Nutzer. Eine Bearbeitung ist daher nicht möglich. Bitte versuchen Sie es später noch einmal.";
			break;
		case 209:
			info = "Die angefragte USt-IdNr. ist ungültig. Sie entspricht nicht dem Aufbau der für diesen EU-Mitgliedstaat gilt. (Aufbau der USt-IdNr. aller EU-Länder)";
			break;
		case 210:
			info = "Die angefragte USt-IdNr. ist ungültig. Sie entspricht nicht den Prüfziffernregeln die für diesen EU-Mitgliedstaat gelten.";
			break;
		case 211:
			info = "Die angefragte USt-IdNr. ist ungültig. Sie enthält unzulässige Zeichen.";
			break;
		case 212:
			info = "Die angefragte USt-IdNr. ist ungültig. Sie enthält ein unzulässiges Länderkennzeichen.";
			break;
		case 213:
			info = "Die Abfrage einer deutschen USt-IdNr. ist nicht möglich.";
			break;
		case 214:
			info = "Ihre deutsche USt-IdNr. ist fehlerhaft. Sie beginnt mit 'DE' gefolgt von 9 Ziffern.";
			break;
		case 215:
			info = "Ihre Anfrage enthält nicht alle notwendigen Angaben für eine einfache Bestätigungsanfrage (Ihre deutsche USt-IdNr. und die ausl. USt-IdNr.). Ihre Anfrage kann deshalb nicht bearbeitet werden.";
			break;
		case 216:
			info = "Ihre Anfrage enthält nicht alle notwendigen Angaben für eine qualifizierte Bestätigungsanfrage (Ihre deutsche USt-IdNr., die ausl. USt-IdNr., Firmenname einschl. Rechtsform und Ort). Es wurde eine einfache Bestätigungsanfrage durchgeführt mit folgenden Ergebnis: Die angefragte USt-IdNr. ist gültig.";
			break;
		case 217:
			info = "Bei der Verarbeitung der Daten aus dem angefragten EU-Mitgliedstaat ist ein Fehler aufgetreten. Ihre Anfrage kann deshalb nicht bearbeitet werden.";
			break;
		case 218:
			info = "Eine qualifizierte Bestätigung ist zur Zeit nicht möglich. Es wurde eine einfache Bestätigungsanfrage mit folgendem Ergebnis durchgeführt: Die angefragte USt-IdNr. ist gültig.";
			break;
		case 219:
			info = "Bei der Durchführung der qualifizierten Bestätigungsanfrage ist ein Fehler aufgetreten. Es wurde eine einfache Bestätigungsanfrage mit folgendem Ergebnis durchgeführt: Die angefragte USt-IdNr. ist gültig.";
			break;
		case 220:
			info = "Bei der Anforderung der amtlichen Bestätigungsmitteilung ist ein Fehler aufgetreten. Sie werden kein Schreiben erhalten.";
			break;
		case 999:
			info = "Eine Bearbeitung Ihrer Anfrage ist zurzeit nicht möglich. Bitte versuchen Sie es später noch einmal.";
			break;
		default:
			info = "unknown error code";
		}
		return info;
	}
}
