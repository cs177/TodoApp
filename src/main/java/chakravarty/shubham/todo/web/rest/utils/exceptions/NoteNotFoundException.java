package chakravarty.shubham.todo.web.rest.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteNotFoundException extends RuntimeException {
    private String message = "Note not Found";
}
