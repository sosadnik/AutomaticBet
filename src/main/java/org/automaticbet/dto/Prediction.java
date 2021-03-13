package org.automaticbet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class Prediction {

    private String homeTeam;
    private String awayTeam;
    private String competitionName;
    private String prediction;
    private String competitionCluster;
    private String status;
    private String result;
    private Date date;
    private Double odds;
}
