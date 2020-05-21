package chakravarty.shubham.todo.repository;

import chakravarty.shubham.todo.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
