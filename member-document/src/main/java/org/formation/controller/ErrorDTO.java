package org.formation.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;


@Builder
@Data
public class ErrorDTO {
    private String message;
    private String severity;
    @JsonFormat(pattern = "dd/MM/YYYY", shape = JsonFormat.Shape.STRING)
    private Date date;
}
