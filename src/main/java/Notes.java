import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Notes {

    private final Map<String, Note> notesMap;
    @Getter
    private final String folderPath;

    public Notes(String folderPath) {
        this.folderPath = folderPath;
        notesMap = new TreeMap<>();
        parseNotes();
    }

    public void putNote(Note note) {
        notesMap.put(note.getTitle(), note);
    }

    public Note getNote(String title) {
        return notesMap.get(title);
    }

    public void removeNote(String title) {
        notesMap.remove(title);
    }

    public boolean isNotesContainsTitle(String title) {
        return notesMap.containsKey(title);
    }

    public String[] getNotesArray() {
        return notesMap.keySet().toArray(new String[0]);
    }

    private void parseNotes() {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        for (File file : Objects.requireNonNull(files, "Не найдена папка с заметками")) {
            StringBuilder builder = new StringBuilder();
            try {
                List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
                lines.forEach(builder::append);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONParser parser = new JSONParser();
                JSONObject noteJSONObject = (JSONObject) parser.parse(builder.toString());

                Note note = new Note(
                        (String) noteJSONObject.get("title"),
                        (String) noteJSONObject.get("text"),
                        file.getPath(),
                        this
                );

                notesMap.put(note.getTitle(), note);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
