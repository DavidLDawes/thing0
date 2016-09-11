package com.virtualsoundnw.thing.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Command entity.
 */
public class CommandDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private Long user;

    @NotNull
    @Size(max = 1024)
    private String request;

    @Size(max = 1024)
    private String response;

    @Size(max = 1024)
    private String status;

    private Boolean started;

    private Boolean completed;

    private Boolean error;

    private Boolean failed;


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
    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }
    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
    public Boolean getFailed() {
        return failed;
    }

    public void setFailed(Boolean failed) {
        this.failed = failed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommandDTO commandDTO = (CommandDTO) o;

        if ( ! Objects.equals(id, commandDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CommandDTO{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", user='" + user + "'" +
            ", request='" + request + "'" +
            ", response='" + response + "'" +
            ", status='" + status + "'" +
            ", started='" + started + "'" +
            ", completed='" + completed + "'" +
            ", error='" + error + "'" +
            ", failed='" + failed + "'" +
            '}';
    }
}
