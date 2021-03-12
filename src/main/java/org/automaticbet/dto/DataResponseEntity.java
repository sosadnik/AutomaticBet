package org.automaticbet.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DataResponseEntity {

    private String eventName;
    private String dataInfo;
    private List<String> dataId;
    private List<String> dataValue;
    private String date;

}
