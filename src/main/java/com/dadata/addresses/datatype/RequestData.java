package com.dadata.addresses.datatype;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestData {
    private String query;
    private Integer count;
    private Locations[] locations;
}