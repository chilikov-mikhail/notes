import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;

public class NoteForm {

    private JPanel notePanel;
    private JButton saveButton;
    private JButton deleteButton;
    private final Note note;
    private JTextField title;
    private JTextArea text;
    private final JList<String> notesList;
    private JFrame frame;

    public NoteForm(Note note, JList<String> notesList) {
        this.note = note;
        title.setText(note.getTitle());
        text.setText(note.getText());
        this.notesList = notesList;
        createNewNoteForm();

        saveButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!title.getText().isEmpty() || !text.getText().isEmpty()) {
                    note.save(title.getText(), text.getText(), notePanel, notesList);
                    title.setText(note.getTitle());
                }
            }
        });

        deleteButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (note.getPath().isEmpty()) {
                    frame.dispose();
                } else if (note.delete(notePanel, notesList)) {
                    frame.dispose();
                }
            }
        });
    }

    public void createNewNoteForm() {
        frame = new JFrame("Заметка");
        frame.setSize(450, 400);

        frame.add(notePanel);

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

                if ((!title.getText().isEmpty() || !text.getText().isEmpty())
                        && (!title.getText().equals(note.getTitle()) || !text.getText().equals(note.getText()))) {
                    int result = JOptionPane.showConfirmDialog(notePanel, "Сохранить текущую заметку?");
                    switch (result) {
                        case JOptionPane.YES_OPTION:
                            if (note.save(title.getText(), text.getText(), notePanel, notesList)) {
                                frame.dispose();
                            }
                            break;
                        case JOptionPane.NO_OPTION:
                            frame.dispose();
                            break;
                        case JOptionPane.CANCEL_OPTION:
                            break;
                    }
                } else {
                    frame.dispose();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
