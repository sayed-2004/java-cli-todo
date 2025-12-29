import java.io.*;
import java.util.ArrayList;

public class TaskStorage {
    private final String filePath;

    public TaskStorage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) return tasks;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|", 2);
                boolean done = parts.length > 0 && parts[0].equals("1");
                String title = parts.length > 1 ? parts[1] : "";
                if (title.trim().isEmpty()) continue;

                Task t = new Task(title);
                if (done) t.markDone();
                tasks.add(t);
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                pw.println((t.isDone() ? "1" : "0") + "|" + t.getTitle());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
