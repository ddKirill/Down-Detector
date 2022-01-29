package downdetector.service;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class CheckResult {

    private final String url;
    private final String status;

}
