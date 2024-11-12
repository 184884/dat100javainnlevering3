package no.hvl.dat100.oppgave3;

import no.hvl.dat100.common.TODO;
import no.hvl.dat100.oppgave1.*;

public class Blogg {

	private Innlegg[] innleggtabell;
	private int nesteledig;

	// Standardkonstruktør som setter startstørrelsen på tabellen til 20
	public Blogg() {
		this.innleggtabell = new Innlegg[20];
		this.nesteledig = 0;
	}

	// Konstruktør som setter startstørrelsen på tabellen til lengden spesifisert
	public Blogg(int lengde) {
		this.innleggtabell = new Innlegg[lengde];
		this.nesteledig = 0;
	}

	// Returnerer antall innlegg som er lagret i tabellen
	public int getAntall() {
		return nesteledig;
	}
	
	// Returnerer en peker til tabellen av innlegg-objekt
	public Innlegg[] getSamling() {
		return innleggtabell;
	}

	// Finner indeks for et innlegg med samme id som gitt innlegg, eller returnerer -1
	public int finnInnlegg(Innlegg innlegg) {
		for (int i = 0; i < nesteledig; i++) {
			if (innleggtabell[i].erLik(innlegg)) {
				return i;
			}
		}
		return -1;
	}

	// Returnerer true hvis et innlegg med samme id allerede finnes i samlingen
	public boolean finnes(Innlegg innlegg) {
		return finnInnlegg(innlegg) != -1;
	}

	// Returnerer true hvis det er ledig plass i samlingen
	public boolean ledigPlass() {
		return nesteledig < innleggtabell.length;
	}
	
	// Legger til et nytt innlegg hvis det ikke finnes fra før og det er ledig plass
	public boolean leggTil(Innlegg innlegg) {
		if (!finnes(innlegg) && ledigPlass()) {
			innleggtabell[nesteledig] = innlegg;
			nesteledig++;
			return true;
		}
		return false;
	}
	
	// Returnerer data i tabellen som en streng representasjon
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nesteledig).append("\n");

		for (int i = 0; i < nesteledig; i++) {
			sb.append(innleggtabell[i].toString());
		}

		return sb.toString();
	}

	// Metoder nedenfor er valgfrie
	
	// Utvider innleggtabellen ved å øke dens lengde
	public void utvid() {
		Innlegg[] nyTabell = new Innlegg[innleggtabell.length * 2];
		for (int i = 0; i < nesteledig; i++) {
			nyTabell[i] = innleggtabell[i];
		}
		innleggtabell = nyTabell;
	}
	
	// Legger til innlegg og utvider tabellen om nødvendig
	public boolean leggTilUtvid(Innlegg innlegg) {
		if (!finnes(innlegg)) {
			if (!ledigPlass()) {
				utvid();
			}
			innleggtabell[nesteledig] = innlegg;
			nesteledig++;
			return true;
		}
		return false;
	}
	
	// Sletter et innlegg hvis det finnes i samlingen
	public boolean slett(Innlegg innlegg) {
		int pos = finnInnlegg(innlegg);
		if (pos != -1) {
			innleggtabell[pos] = innleggtabell[nesteledig - 1];
			innleggtabell[nesteledig - 1] = null;
			nesteledig--;
			return true;
		}
		return false;
	}
	
	// Søker etter innlegg som inneholder et gitt nøkkelord
	public int[] search(String keyword) {
		int[] result = new int[nesteledig];
		int count = 0;

		for (int i = 0; i < nesteledig; i++) {
			if (innleggtabell[i].toString().contains(keyword)) {
				result[count++] = i;
			}
		}

		int[] trimmedResult = new int[count];
		System.arraycopy(result, 0, trimmedResult, 0, count);
		return trimmedResult;
	}
}
