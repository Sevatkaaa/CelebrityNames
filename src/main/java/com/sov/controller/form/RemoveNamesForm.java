package com.sov.controller.form;

import lombok.Data;

import java.util.List;

@Data
public class RemoveNamesForm {
    private List<Long> nameIds;
}
