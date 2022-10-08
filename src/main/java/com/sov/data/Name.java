package com.sov.data;

import com.sov.model.NameModel;
import lombok.Data;

@Data
public class Name {
    private Long id;
    private String value;

    public static Name of(NameModel nameModel) {
        Name name = new Name();
        name.id = nameModel.getId();
        name.value = nameModel.getValue();
        return name;
    }
}
