package downdetector;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SiteStatusResult {

    private final String nameUrl;
    private final String status;


}
