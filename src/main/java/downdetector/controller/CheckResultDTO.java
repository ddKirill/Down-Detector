package downdetector.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CheckResultDTO {

    private final String url;
    private final String status;
}
