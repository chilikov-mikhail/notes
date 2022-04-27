import lombok.Getter;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Note {

    @Getter
    private String title;
    @Getter
    private String text;
    @Getter
    private String path;
    private final Notes notes;

    public Note(String title, String text, String path, Notes notes) {
        this.title = clearTitle(title);
        this.text = text;
        this.path = path;
        this.notes = notes;
    }

    public boolean save(String title, String text, JPanel notePanel, JList<String> notesList) {

        boolean isSaved = false;
        title = clearTitle(title);

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(notePanel, "Введите заголовок заметки");
        } else if (!title.equals(this.title) && notes.isNotesContainsTitle(title)) {
            JOptionPane.showMessageDialog(notePanel, "Заметка с таким заголовком уже существует. Измените заголовок заметки.");
        } else {
            saveVerifiedNote(title, text);
            notesList.setListData(notes.getNotesArray());
            isSaved = true;
        }

        return isSaved;
    }

    public boolean delete(JPanel panel, JList<String> notesList) {

        boolean isDeleted = false;

        int result = JOptionPane.showConfirmDialog(panel,
                "Вы уверены, что хотите удалить заметку \"" + title + "\"?",
                "Подтверждение удаления",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            deleteNoteWithoutConfirmDialog();
            notesList.setListData(notes.getNotesArray());
            isDeleted = true;
        }

        return isDeleted;
    }

    private void saveVerifiedNote(String title, String text) {
        JSONObject object = new JSONObject();
        object.put("title", title);
        object.put("text", text);

        if (!title.equals(this.title)) {
            deleteNoteWithoutConfirmDialog();
            path = notes.getFolderPath() + title + ".json";
        }

        try {
            Files.write(Paths.get(path), object.toJSONString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.title = title;
        this.text = text;
        notes.putNote(this);
    }

    private void deleteNoteWithoutConfirmDialog() {
        if (!path.isEmpty()) {
            File file = new File(path);
            file.delete();
            notes.removeNote(this.title);
        }
    }

    private static String clearTitle(String title) {
        return title.replaceAll("[^a-zA-Z0-9а-яА-ЯёЁ_\\-\\s№()]", "");
    }
}
