package no.hvl.dat100.oppgave4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import no.hvl.dat100.oppgave3.*;

public class SkrivBlogg {

	public static boolean skriv(Blogg samling, String mappe, String filnavn) {
		try {
			// Oppretter filbanen ved å kombinere mappe og filnavn
			File file = new File(mappe, filnavn);
			PrintWriter writer = new PrintWriter(file);

			// Skriver ut antall innlegg først
			writer.println(samling.getAntall());

			// Skriver ut hvert innlegg i samlingen
			for (Innlegg innlegg : samling.getSamling()) {
				writer.print(innlegg.toString());
			}

			// Lukker skribenten etter å ha skrevet ut
			writer.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("Filen kunne ikke skrives: " + e.getMessage());
			return false;
		}
	}
}
