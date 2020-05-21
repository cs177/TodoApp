package chakravarty.shubham.todo.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255) DEFAULT '<unnamed>'")
    private String title;

    @Column(columnDefinition = "varchar(255)")
    private String content;

    @Column(name="timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdOn;

    @Column
    private String createdBy;

}
