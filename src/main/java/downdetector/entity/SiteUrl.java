package downdetector.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;


@Component
@Table("site_url")
@AllArgsConstructor
public class SiteUrl {

    @Id
    private final Integer id;

    @Getter
    @Setter
    private String url;


}
