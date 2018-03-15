package com.freesheep.web.test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Product {

    @XmlElement
    private String name;
    @XmlElement(name = "item")
    private List<Book> proList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getProList() {
        return proList;
    }

    public void setProList(List<Book> proList) {
        this.proList = proList;
    }
}
