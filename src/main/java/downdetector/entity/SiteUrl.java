package downdetector.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Table("site_url")
@AllArgsConstructor
public class SiteUrl {

    @Id
    @Getter
    private final UUID id;

    @Getter
    @Setter
    private String url;





}
