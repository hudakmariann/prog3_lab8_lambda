
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Beer implements Serializable {
	private String name;
	private String style;
	private Double strength;
	static public ArrayList<Beer> beers = new ArrayList<Beer>();

	static Map<String, Comparator<Beer>> comps = new HashMap<String, Comparator<Beer>>();

	static {

		comps.put("name", Comparator.comparing((Beer beer) -> beer.getName()));
		comps.put("style", Comparator.comparing((Beer beer) -> beer.getStyle()));
		comps.put("strength", Comparator.comparing((Beer beer) -> beer.getStrength()));
	}

	static protected void add(String[] cmd) {
		Double s = Double.parseDouble(cmd[3]);
		Beer b = new Beer(cmd[1], cmd[2], s);
		beers.add(b);

	}

	public String toString() {
		return "name = " + this.name + ", style = " + this.style + ", strength = " + this.strength;

	}

	static protected void list(String[] cmd) {

		Comparator<Beer> cmp = comps.get("name");

		if (cmd.length > 1) {

			if (comps.containsKey(cmd[1])) {
				cmp = comps.get(cmd[1]);
				Collections.sort(beers, comps.get(cmd[1]));

				for (Beer b : beers) {
					System.out.println(b.toString());
				}
			}
		}

			/*
			 * if (cmd[1].equals("name") ) {
			 * 
			 * //forras: https://www.geeksforgeeks.org/comparator-interface-java/
			 * 
			 * // Sorting student entries by name //Collections.sort(beers, new
			 * NameComparator()); Collections.sort(beers, (b1,b2) ->
			 * b1.getName().compareTo(b2.getName()) );
			 * 
			 * // Display message on console for better readability //
			 * System.out.println("\nSorted by name");
			 * 
			 * // Again iterating over entries to print them for (int i = 0; i <
			 * beers.size(); i++) System.out.println(beers.get(i).toString());
			 * System.out.println("\nSorted by name"); } else if (cmd[1].equals("style") ) {
			 * 
			 * 
			 * // Sorting student entries by style //Collections.sort(beers, new
			 * StyleComparator()); Collections.sort(beers, (b1,b2) ->
			 * b1.getStyle().compareTo(b2.getStyle()) );
			 * 
			 * // Display message on console for better readability
			 * System.out.println("\nSorted by style");
			 * 
			 * // Again iterating over entries to print them for (int i = 0; i <
			 * beers.size(); i++) System.out.println(beers.get(i).toString()); } else if
			 * (cmd[1].equals("strength") ) {
			 * 
			 * // Sorting student entries by style //Collections.sort(beers, new
			 * StrengthComparator()); Collections.sort(beers, (b1,b2) ->
			 * b1.getStrength().compareTo(b2.getStrength()) );
			 * 
			 * // Display message on console for better readability
			 * System.out.println("\nSorted by strength");
			 * 
			 * // Again iterating over entries to print them for (int i = 0; i <
			 * beers.size(); i++) System.out.println(beers.get(i).toString());
			 */
			// }

		

		else
			for (Beer b : beers) {
				System.out.println(b.toString());
			}

		/*
		 * ez ugyanaz, mint a fenti: // Iterating over entries to print them for (int i
		 * = 0; i < beers.size(); i++) System.out.println(beers.get(i).toString());
		 */

	}

	// forras:
	// https://howtodoinjava.com/java/collections/arraylist/serialize-deserialize-arraylist/
	static protected void save(String[] cmd) {
		try (FileOutputStream fos = new FileOutputStream(cmd[1]);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {

			oos.writeObject(beers);

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			throw new RuntimeException(e);
		} catch (IOException ioe) {
			System.out.println("Error while writing data ");

		}
	}

	static protected void load(String[] cmd) {
		// File output = new File(System.getProperty("user.dir"), (cmd[1]));
		try (FileInputStream fis = new FileInputStream(cmd[1]); ObjectInputStream ois = new ObjectInputStream(fis);) {

			beers = (ArrayList<Beer>) ois.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
		}
	}

	static protected void search(String[] cmd) {
		if (cmd.length < 2) {
			System.out.println("Missing parameter");
		} else {
			for (Beer b : beers) {
				if (b.name.equals(cmd[1]))
					System.out.println(b.toString());
			}
		}
	}

	static protected void find(String[] cmd) {
		if (cmd.length < 2) {
			System.out.println("Missing parameter");
		} else {
			for (Beer b : beers) {
				if (b.name.contains(cmd[1]))
					System.out.println(b.toString());
			}
		}
	}

	static protected void delete(String[] cmd) {
		Iterator<Beer> iter = beers.iterator();
		if (cmd.length < 2) {
			System.out.println("Missing parameter");
		} else {
			while (iter.hasNext()) {
				if (iter.next().name.equals(cmd[1])) {
					iter.remove();
				}
			}
		}
	}

	public Beer(String nev, String jelleg, double fok) {
		name = nev;
		style = jelleg;
		strength = fok;
	}

	public Beer() {
		this("Tiltott Csiki Sor", "lager", 6);
	}

	public String getName() {
		return name;
	}

	public String getStyle() {
		return style;
	}

	public Double getStrength() {
		return strength;
	}

}
