import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Run {
	public float answer;
	public float result;
	public String[] names = { "Horst", "Dummi", "Trottel", "Hirni", "Else",
			"Ochse", "Affe", "Taube Nuss", "Trulla" };

	// Legt Zufallszahlen fest
	public int randomType(int a) {
		Random rand = new Random();
		int randNom = rand.nextInt(a);
		return randNom;
	}

	public static void main(String[] args) {
		// Stellt Einleitungs Fragen
		System.out.println("Was für eine Aufgabe willst Du rechnen:");
		System.out.print("Division (1) oder ");
		System.out.println("Prozente (2-4) ?");

		Scanner sm = new Scanner(System.in);
		int aufgabentyp = sm.nextInt();

		System.out.println("Gib Schwierigkeitstyp an (1-4)");
		int skill = sm.nextInt();

		System.out.println("Wieviele Aufgaben willst Du gestellt haben?");
		int anzahlAufg = sm.nextInt();

		// sm.close();

		int count = 0;
		int correct = 0;

		while (count < anzahlAufg) {
			Question q = new Question(aufgabentyp);

			q.generateNum();
			Run r = new Run();
			r.answer = q.printQ();
			r.result = q.calculate();

			if (q.compare(r.answer, r.result, skill)) {
				System.out.println("OK, ziemlich gut!");
				correct++;
			} else {
				System.out.print("Du "
						+ r.names[r.randomType(r.names.length - 1)] + "! ");
			}
			String pat="###,###.##";
			DecimalFormat df= new DecimalFormat(pat);
			String output= df.format(r.result);
			System.out.println("Die genaue Lösung ist " + output + " !");
			System.out.println();
			count++;
		}
		float quote = (float) correct / count;
		System.out.println();
		System.out.println();
		System.out.println("DEINE ERFOLGSQUOTE LIEGT BEI CA. "
				+ Math.round(quote * 100) / 1f + "% !");
	}

}
