package com.prepared.entity;

import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

/**
 * @Author: prepared
 * @Date: 2021/9/10 11:43
 */
public class Author {

    @Field(type = Text)
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" + "name='" + name + '\'' + '}';
    }
}
