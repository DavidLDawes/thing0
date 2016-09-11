package com.virtualsoundnw.thing.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Data entity.
 */
public class DataDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate date;

    @Size(min = 2, max = 8192)
    private String json;

    @Size(max = 8192)
    private String xml;

    private String text;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DataDTO dataDTO = (DataDTO) o;

        if ( ! Objects.equals(id, dataDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DataDTO{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", json='" + json + "'" +
            ", xml='" + xml + "'" +
            ", text='" + text + "'" +
            '}';
    }
}
