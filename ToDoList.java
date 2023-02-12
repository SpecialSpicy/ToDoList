
//A Simple To-Do-List in Java. Created by Houman Hafez (SpecialSpicy)
//Imports (We need Arraylist for the list and Scanner to get input from the User)
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//A Class with all the function (first we need to make a new Arraylist and a txt file to save all the assignments)
public class ToDoList {
  private ArrayList<String> tasks = new ArrayList<>();
  private File file = new File("tasks.txt");

  
  //loades the private void class from below 
  public ToDoList() {
	    loadTasksFromFile();
	  }

  //A private function to load all the strings from the txt file (With Exception Handling)
  private void loadTasksFromFile() {
	  	//A reader reads the file and loads the tasks from the file
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	      String line;
	      while ((line = reader.readLine()) != null) {
	        tasks.add(line);
	      }
	      //with IOException handled of course
	    } catch (IOException e) {
	      System.out.println("Error loading tasks from file: " + e.getMessage());
	    }
	  }
  
  
  //A fucntion to add each string to the list 'tasks' and save it (a function below)
  public void addTask(String task) {
    tasks.add(task);
    saveTasksToFile();
  }
  
  //A function to view the tasks which prints out all the tasks in order with a simple for loop
  public void viewTasks() {
    System.out.println("Tasks:");
    for (int i = 0; i < tasks.size(); i++) {
      System.out.println((i + 1) + ". " + tasks.get(i));
    }
  }

  //A function to delete a specific task (removes the given one)
  public void deleteTask(int index) {
    tasks.remove(index - 1);
    saveTasksToFile();
  }

  //A function to save all the added tasks to the file with a writer
  private void saveTasksToFile() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
	      for (String task : tasks) {
	        writer.write(task);
	        writer.newLine();
	      }
	      //IOException handled
	    } catch (IOException e) {
	        System.out.println("Error saving tasks to file: " + e.getMessage());
	      }
	    }
  
  //Main Function with a scanner and an object
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ToDoList toDoList = new ToDoList();
    //a while loop to print out all the commands the user can use (the user doesn't have to start the application again
    while (true) {
      System.out.println("What would you like to do?");
      System.out.println("1. Add Task");
      System.out.println("2. View Tasks");
      System.out.println("3. Delete Task");
      System.out.println("4. Exit");
      //a scanner  takes the number 
      int choice = scanner.nextInt();
      scanner.nextLine();
      //with an if statement, the input of the user gets taken and each function gets used when the right input is given
      if (choice == 1) {
        System.out.print("Enter task: ");
        String task = scanner.nextLine();
        toDoList.addTask(task);
      } else if (choice == 2) {
        toDoList.viewTasks();
      } else if (choice == 3) {
        System.out.print("Enter task number to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        toDoList.deleteTask(index);
      } else if (choice == 4) {
        break;
      }
      else {
    	  System.out.println("Please type only a number between 1 - 4 !");
      }
    }
    //closes the input from the user
    scanner.close();
  }
}
