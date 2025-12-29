import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n== Java CLI To-Do ==");
            System.out.println("1) List tasks");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                listTasks();
            } else if (choice.equals("0")) {
                System.out.println("Bye!");
                break;
            } else if(choice.equals("2")){
                addTask(sc);
            } else {
                System.out.println("Invalid option.");
            }
        }
        sc.close();
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ") " + tasks.get(i));
        }
    }

    private static void addTask(Scanner sc) {
        System.out.print("Enter task: ");
        String task = sc.nextLine().trim();
        if (task.isEmpty()) {
            System.out.println("Task cannot be empty.");
            return;
        }
        tasks.add(new Task(task));
        System.out.println("Added!");
    }
}
