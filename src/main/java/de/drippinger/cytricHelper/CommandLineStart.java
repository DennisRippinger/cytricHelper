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
import org.apache.commons.cli.*;

import java.io.IOException;

/**
 * de.drippinger.cytricHelper.CommandLine
 *
 * @author Dennis Rippinger
 * @since 09.01.16
 */
public class CommandLineStart {

	public static void main(String[] args) throws ParseException {

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(createOptions(), args);

		CytricHelper helper = new CytricHelper();

		if (cmd.hasOption("f") && cmd.hasOption("e")) {
			try {
				helper.manipulatePdf(cmd.getOptionValue("f"), cmd.getOptionValue("e"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} else {
			printHelp();
		}

	}

	private static void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("cytricHelper", createOptions());
	}

	public static Options createOptions() {
		Options options = new Options();

		options.addOption("h", "help", false, "print this message");
		options.addOption("f", "file", true, "the input files");
		options.addOption("e", "expenseID", true, "the travel expense id");

		return options;
	}
}
