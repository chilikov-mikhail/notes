import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class NotesListForm {

    @Getter
    private JPanel notesListPanel;
    private JList<String> notesList;
    private JButton openButton;
    private JButton deleteButton;
    private JButton createNewNoteButton;

    public NotesListForm(Notes notes) {
        notesList = new JList<>(notes.getNotesArray());
        notesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        notesListPanel.add(new JScrollPane(notesList));

        openButton.addActionListener(new Action() {
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
                new NoteForm(notes.getNote(notesList.getSelectedValue()), notesList);
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
                notes.getNote(notesList.getSelectedValue()).delete(notesListPanel, notesList);
            }
        });

        createNewNoteButton.addActionListener(new Action() {
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
                new NoteForm(new Note("", "", "", notes), notesList);
            }
        });
    }
}
