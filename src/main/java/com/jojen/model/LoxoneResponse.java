package com.jojen.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LL")
public class LoxoneResponse {
    @XmlAttribute(name = "control")
    private String Control;
    @XmlAttribute(name = "value")
    private String Value;
    @XmlAttribute(name = "Code")
    private String Code;

    public String getControl() {
        return Control;
    }

    public void setControl(String control) {
        Control = control;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
