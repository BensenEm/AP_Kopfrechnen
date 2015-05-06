import java.text.DecimalFormat;
import java.util.*;

public class Question {
	public static int counter = 0;
	public int type;
	public float zahl1;
	public float zahl2;

	public Question(int i, float z1, float z2) {
		counter++;
		type = i;
		zahl1 = z1;
		zahl2 = z2;
	}

	public Question(int i) {
		type = i;
	}

	public Question() {
	}

	public void generateNum() {
		Random ran = new Random();
		switch (type) {
		case 1:
			zahl1 = ran.nextFloat() * (9) + 1;
			zahl1 = Math.round(zahl1 * 10) / 10f;
			zahl2 = ran.nextFloat() * (9) + 1;
			zahl2 = Math.round(zahl2 * 10) / 10f;
			break;
		case 2:
			//Generiert Zahlen wie 1'000er 10'000, 100'000er
			int pot=ran.nextInt(4)+1;
			//Erzeugt Float zwischen 1 und 100
			zahl1= (ran.nextFloat()*(99)+1)/10f;
			//Erweitert den Float um die pot variable
			zahl1= zahl1* (float) Math.pow(10,pot);
			// Sorgt dafür dass glatte Zahlen (%100=0) entstehen
			zahl1= (int) zahl1*100;
			//Generiert %Zahl in 0.25 Schritten
			zahl2 = (float)(ran.nextInt(399)+1)/4;
			break;
		
		//Identisch mit Case2
		case 3:
			pot=ran.nextInt(4)+1;
			zahl1= (ran.nextFloat()*(99)+1)/10f;
			zahl1= zahl1* (float) Math.pow(10,pot);
			zahl1= (int) zahl1*100;
			zahl2 = (float)(ran.nextInt(399)+1)/4;
			break;
		//Identisch mit Case2
		case 4:
			pot=ran.nextInt(4)+1;
			zahl1= (ran.nextFloat()*(99)+1)/10f;
			zahl1= zahl1* (float) Math.pow(10,pot);
			zahl1= (int) zahl1*100;
			zahl2 = (float)(ran.nextInt(399)+1)/4;
			break;
		}
	}

	// Stellt die Frage iAbh von 2 Zahlen a&b sowie Aufgabentyp i und gibt ein
	// Ergebnis zurück
	public float printQ() {
		float answer = 0;
		// Frage mit Divisionen
		if (type == 1) {
			System.out.println("Teile " + zahl1 + " durch " + zahl2 + " !");
			Scanner sq = new Scanner(System.in);
			
			try{answer = sq.nextFloat();}
			catch(InputMismatchException e){System.out.println("Dezimalzahlen nur mit KOMMA bitte");sq.next();
			answer= sq.nextFloat();}			
			return answer;
		}

		// Frage mit Prozenten
		else {
			String[] d = new String[] { " - ", " + ", " * " };
			String pattern = "###,###.##";
			DecimalFormat myFormatter = new DecimalFormat(pattern);
			String output = myFormatter.format(zahl1);
			// String output2= myFormatter.format(b);
			System.out.print("Wieviel ist:         " + output + d[type - 2]
					+ zahl2 + "% ?   ");
			Scanner sc = new Scanner(System.in);
			try{answer = sc.nextFloat();}
			catch(InputMismatchException e){System.out.println("Dezimalzahlen nur mit KOMMA bitte"); sc.next(); answer= sc.nextFloat();}
			return answer;
		}
	}

	// Kalkuliert das korrekte Ergebnis iAbh. von 2 Rechenzahlen a & b und
	// Aufgabentyp i und gibt präzisen Wert zurück
	public float calculate() {
		float result;
		switch (type) {
		case 1:
			result = zahl1 / zahl2;
			return result;
		case 2:
			result = zahl1 * (1 - zahl2/100f);
			return result;

		case 3:
			result = zahl1 * (1 + zahl2/100f);
			return result;
		case 4:
			result = zahl1 * zahl2/100f;
			return result;
		default:
			return 0.0f;
		}
	}

	// Kontrolliert Genauigkeit der Antwort
	public boolean compare(float ans, float res, int skill) {
			if ((skill==1)&& (ans > 0.995f * res) && (ans < 1.005f * res))
				return true;
	
			if ((skill==2)&&(ans > 0.98 * res) && (ans < 1.02 * res))
				return true;
		
			if ((skill==3)&& (ans > 0.95 * res) && (ans < 1.05 * res))
				return true;

			if ((skill==4)&&(ans > 0.90 * res) && (ans < 1.1 * res))
				return true;

			if ((skill>4)&&(ans > 0.80 * res) && (ans < 1.2 * res))
				return true;
			return false;
		}
	}