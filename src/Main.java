
//import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

//import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		HashMap<String, Command> command = new HashMap<String, Command>();

		command.put("list", Beer::list);
		command.put("add", Beer::add);
		command.put("save", Beer::save);
		command.put("load", Beer::load);
		command.put("search", Beer::search);
		command.put("find", Beer::find);
		command.put("delete", Beer::delete);

		try (Scanner sc = new Scanner(System.in)) {
			String line;
			while (true) {// (line = sc.nextLine() != null) {
				if (sc.nextLine() == null) {
					sc.close();
					break;
				} else {
					line = sc.nextLine();

					String[] cmd = line.split(" "); // szóközt tartalmazó string
					System.out.println("cmd[0] = " + cmd[0]);
					if (command.containsKey(cmd[0])) {
						Command c = command.get(cmd[0]);
						c.execute(cmd);
					}
					else {
						System.out.println("No such command!");	
						}
					
				}

			}
		}

		catch (Exception e) {

			e.printStackTrace();
		}

	}

	/*
	 * // 2.-3. feladat // forras:
	 * https://www.techiedelight.com/read-multi-line-input-console-java/ try
	 * (InputStreamReader in = new InputStreamReader(System.in); BufferedReader
	 * buffer = new BufferedReader(in)) { String line; while ((line =
	 * buffer.readLine()) != null) { String[] cmd = line.split(" ");
	 * System.out.println(cmd[0]); if (cmd[0].equals("exit")) break; else if
	 * (cmd[0].equals("add")) { Beer.add(cmd); } else if (cmd[0].equals("list")) {
	 * System.out.println("list parancs elerve"); Beer.list(cmd); } else if
	 * (cmd[0].equals("save")) { System.out.println("save parancs elerve");
	 * Beer.save(cmd); } else if (cmd[0].equals("load")) {
	 * System.out.println("load parancs elerve"); Beer.load(cmd); } else if
	 * (cmd[0].equals("find")) { System.out.println("find parancs elerve");
	 * Beer.find(cmd); } else if (cmd[0].equals("search")) {
	 * System.out.println("search parancs elerve"); Beer.search(cmd); } else if
	 * (cmd[0].equals("delete")) { System.out.println("delete parancs elerve");
	 * Beer.delete(cmd); }
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */

}
