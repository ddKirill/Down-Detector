package downdetector.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("site_url")
@AllArgsConstructor
public class SiteUrl {

    @Id
    @Getter
    private final Integer id;

    @Getter
    @Setter
    private String url;


}
