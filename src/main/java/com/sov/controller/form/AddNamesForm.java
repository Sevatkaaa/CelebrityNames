package com.sov.controller.form;

import lombok.Data;

import java.util.List;

@Data
public class AddNamesForm {
    private Long playerId;
    private List<String> names;
}
