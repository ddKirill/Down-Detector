package downdetector.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("history_sites")
@AllArgsConstructor
public class HistorySites {

    @Id
    private final UUID id;
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private boolean status;
    @Getter
    private final LocalDateTime createdAt;


}
