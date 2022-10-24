package com.seerbit.statistics.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticsDto {

    private Double sum;
    private Double avg;
    private Double max;
    private Double min;
    private Long count;


    public StatisticsDto(Double sum, Double avg, Double max, Double min, Long count) {
        super();
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }



}
