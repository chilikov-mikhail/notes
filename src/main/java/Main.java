import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Notes notes = new Notes("Data/");

        JFrame frame = new JFrame("Список заметок");
        frame.setSize(500, 500);

        frame.add(new NotesListForm(notes).getNotesListPanel());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
