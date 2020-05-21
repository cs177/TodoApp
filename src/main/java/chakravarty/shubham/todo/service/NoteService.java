package chakravarty.shubham.todo.service;

import chakravarty.shubham.todo.domain.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {

    Note createNewNote(Note note);

    List<Note> getAllNotes();

    Note getNote(Long id);

    Note updateNote(Long id, Note note);

    void deleteNote(Long id);
}
