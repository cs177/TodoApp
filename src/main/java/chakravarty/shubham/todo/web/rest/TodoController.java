package chakravarty.shubham.todo.web.rest;

import chakravarty.shubham.todo.aop.Loggable;
import chakravarty.shubham.todo.config.Constants;
import chakravarty.shubham.todo.domain.Note;
import chakravarty.shubham.todo.service.NoteService;
import chakravarty.shubham.todo.web.rest.utils.ResponseStatus;
import chakravarty.shubham.todo.web.rest.utils.ValidationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController("/todo")
public class TodoController {

    @Autowired
    NoteService noteService;

    /*@RequestMapping("/hello")
    String sayHello(){
        return "Hello World";
    }*/

    @PostMapping("/notes")
    @Loggable
    public ResponseEntity<ResponseStatus> createNote(@RequestBody Note note){
        Note createdNote = noteService.createNewNote(note);
        return new ResponseEntity<>(new ResponseStatus(ValidationStatus.SUCCESS, createdNote, new LinkedList<>()), HttpStatus.CREATED);
    }

    @GetMapping("/notes")
    @Loggable
    public ResponseEntity<ResponseStatus> getAllNotes(){
        List<Note> notes = noteService.getAllNotes();
        return new ResponseEntity<>(new ResponseStatus(ValidationStatus.SUCCESS, notes, new LinkedList<>()), HttpStatus.OK);
    }

    @GetMapping("/note/{id}")
    @Loggable
    public ResponseEntity<ResponseStatus> getNote(@PathVariable ("id") Long id){
        try{
            Note getNote = noteService.getNote(id);
            return new ResponseEntity<>(new ResponseStatus(ValidationStatus.SUCCESS, getNote, new LinkedList<>()), HttpStatus.OK);
        }catch (Exception e){
            List<String> errorMessage = new LinkedList<>();
            errorMessage.add(e.getMessage());
            return new ResponseEntity<>(new ResponseStatus(ValidationStatus.ERROR, null, errorMessage), HttpStatus.OK);
        }

    }

    @PutMapping("/note/{id}")
    @Loggable
    public ResponseEntity<ResponseStatus> updateNote(@RequestBody Note note, @PathVariable("id") Long id){
         try {
            Note updatedNote = noteService.updateNote(id, note);
            return new ResponseEntity<>(new ResponseStatus(ValidationStatus.SUCCESS, updatedNote, new LinkedList<>()), HttpStatus.OK);
        }catch (Exception e){
            List<String> errorMessage = new LinkedList<>();
            errorMessage.add(e.getMessage());
            return new ResponseEntity<>(new ResponseStatus(ValidationStatus.ERROR, null, errorMessage), HttpStatus.OK);
        }
    }

    @DeleteMapping("/note/{id}")
    @Loggable
    public ResponseEntity<ResponseStatus> deleteNote(@PathVariable("id") Long id){
         try {
            noteService.deleteNote(id);
            return new ResponseEntity<>(new ResponseStatus(ValidationStatus.SUCCESS, Constants.SUCCESSFULLY_DELETED, new LinkedList<>()), HttpStatus.OK);
        }catch (Exception e){
            List<String> errorMessage = new LinkedList<>();
            errorMessage.add(e.getMessage());
            return new ResponseEntity<>(new ResponseStatus(ValidationStatus.ERROR, null, errorMessage), HttpStatus.OK);
        }
    }
}
