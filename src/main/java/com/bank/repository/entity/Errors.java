package com.bank.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Errors {

    private Integer errorId;
    private String message;

}
