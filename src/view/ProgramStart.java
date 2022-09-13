package view;

import java.util.List;
import java.util.Scanner;

import controller.MovieItemHelper;
import model.MovieItem;

/**
 * @author kenne-krcutkomp CIS175 - Fall 2022
 */
public class ProgramStart {
	static Scanner in = new Scanner(System.in);
	static MovieItemHelper mih = new MovieItemHelper();

	private static void addAnItem() {
		System.out.print("Enter a title: ");
		String title = in.nextLine();
		System.out.print("How many copies?: ");
		int quantity = in.nextInt();
		MovieItem toAdd = new MovieItem(title, quantity);
		mih.insertItem(toAdd);

	}

	private static void deleteAnItem() {
		System.out.print("Enter the ID to delete: ");
		int ID = in.nextInt();
		MovieItem toDelete = mih.searchForItemById(ID);
		mih.deleteItem(toDelete);

	}

	private static void editAnItem() {
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Title");
		System.out.println("2 : Search by ID");
		int searchBy = in.nextInt();
		in.nextLine();

		List<MovieItem> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the title: ");
			String title = in.nextLine();
			foundItems = mih.searchForItemByTitle(title);

		} else {
			System.out.print("Enter the ID: ");
			int ID = in.nextInt();
			foundItems = mih.searchForItemByIdx(ID);

		}

		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (MovieItem l : foundItems) {
				System.out.println(l.getID() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			MovieItem toEdit = mih.searchForItemById(idToEdit);
			System.out.println("Retrieved " + toEdit.getQuantity() + " from " + toEdit.getMovieTitle());
			System.out.println("1 : Update Title");
			System.out.println("2 : Update Quantity");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Title: ");
				String newTitle = in.nextLine();
				toEdit.setMovieTitle(newTitle);
			} else if (update == 2) {
				System.out.print("New Quantity: ");
				int newQuantity = in.nextInt();
				toEdit.setQuantity(newQuantity);
			}

			mih.updateItem(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}

	/**
	 * 
	 */
	public ProgramStart() {
		// TODO Auto-generated constructor stub
	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Inventory Menu For Movies ---");
		while (goAgain) {
			System.out.println("*  Make a choice:");
			System.out.println("*  1 -- Add a Movie");
			System.out.println("*  2 -- Edit a Movie");
			System.out.println("*  3 -- Delete a Movie");
			System.out.println("*  4 -- View all Movies");
			System.out.println("*  5 -- Exit program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editAnItem();
			} else if (selection == 3) {
				deleteAnItem();
			} else if (selection == 4) {
				viewTheList();
			} else {
				mih.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		runMenu();

	}

	private static void viewTheList() {
		List<MovieItem> allItems = mih.showAllItems();
		for (MovieItem singleItem : allItems) {
			System.out.println(singleItem.getMovie());
		}
	}

}
