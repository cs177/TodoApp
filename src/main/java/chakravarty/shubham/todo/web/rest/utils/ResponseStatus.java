package chakravarty.shubham.todo.web.rest.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus {
    private ValidationStatus validationStatus;
    private Object data;
    private List<String> errorMessages;
}
