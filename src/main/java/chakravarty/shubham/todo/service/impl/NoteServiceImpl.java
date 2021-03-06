package chakravarty.shubham.todo.service.impl;

import chakravarty.shubham.todo.aop.Loggable;
import chakravarty.shubham.todo.domain.Note;
import chakravarty.shubham.todo.repository.NoteRepository;
import chakravarty.shubham.todo.service.NoteService;
import chakravarty.shubham.todo.web.rest.utils.exceptions.NoteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    private static Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Override
    public Note createNewNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    @Loggable
    public Note getNote(Long id) {

        Optional<Note> oldNoteOptional = noteRepository.findById(id);
        if (!oldNoteOptional.isPresent())
            throw new NoteNotFoundException();

        return oldNoteOptional.get();
    }

    @Override
    @Loggable
    public Note updateNote(Long id, Note note) {

        boolean ifNoteExists = noteRepository.existsById(id);

        if (!ifNoteExists)
            throw new NoteNotFoundException();

        note.setId(id);
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
