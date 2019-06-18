package com.exam.domain;

import lombok.Data;


@Data
public class DocumentVO {
    private String documentNo; 
    private String subject;
    private String context;
    private String confirm1;
    private String confirm1Reg; 
    private String confirm2;
    private String confirm2Reg;
    private String confirm3;
    private String confirm3Reg; 
    private String doctype;
    private String type;
    private String writer;
    private String regdate;
    private String docstate;
    private String key1;
    private String key2;
}
