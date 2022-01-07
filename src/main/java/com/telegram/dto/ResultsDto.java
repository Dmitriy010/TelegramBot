package com.telegram.dto;

import com.telegram.model.Results;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultsDto {
    List<Results> resultsList;
}
