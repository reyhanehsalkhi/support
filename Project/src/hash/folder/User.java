package hash.folder;

import java.util.Scanner;

public class User {

	public static void main(String[] args) {
		Hashing h = new Hashing();
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter the path to libraries in the customer source code:");
		String customerDir = myObj.nextLine();
		System.out.println("Enter the path to pristine libraries of Spartacus:");
		String pristineDir = myObj.nextLine();
		try {
			if (h.compareHashforPath(customerDir, pristineDir))
				System.out.println("libaries have not been changed");
			else
				System.out.println("libraries have been changed by the customer");

		} catch (Exception e) {
			e.printStackTrace();
		}
		myObj.close();

	}

}
