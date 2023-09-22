package com.mongo.quote.persistence.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@ToString
@EqualsAndHashCode
@Document(collection = "quotes")
public class QuoteDocument {

  @Id
  @JsonProperty("_id")
  private String id;

  private String quoteText;
  @Indexed private String quoteAuthor;
  private String quoteGenre;

  @Version
  @JsonProperty("__v")
  @Field("__v")
  private Integer version;
}
