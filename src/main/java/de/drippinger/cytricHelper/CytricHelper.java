/**
 * This file is part of cytricHelper.
 * <p/>
 * cytricHelper is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * cytricHelper is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Affero General Public License
 * along with cytricHelper.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.drippinger.cytricHelper;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * CytricHelper
 *
 * @author Dennis Rippinger
 * @since 09.01.16
 */
public class CytricHelper {

	public void manipulatePdf(String sourceFile, String expenseID) throws IOException, DocumentException {
		PdfReader reader = new PdfReader(sourceFile);
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(getOutputName(sourceFile)));

		PdfContentByte over = stamper.getOverContent(1);
		Phrase p = new Phrase(String.format("Cytric ID: %s", expenseID));
		ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, 500, reader.getPageSize(1).getHeight() - 30, 0);
		over.saveState();

		stamper.close();
		reader.close();
	}

	private String getOutputName(String input) {
		return input.replace(".pdf", "-output.pdf");
	}
}
