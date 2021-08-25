package com.example.ebean.model;

import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {

    @Id
    protected long id;

    @Version
    protected long version;

    @WhenCreated
    protected Instant createdOn;

    @WhenModified
    protected Instant modifiedOn;
}
