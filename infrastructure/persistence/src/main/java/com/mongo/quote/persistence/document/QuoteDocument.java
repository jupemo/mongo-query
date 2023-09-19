package com.mongo.quote.persistence.document;

import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "quotes")
@CompoundIndexes({
    @CompoundIndex(
        name = "quote__id_author",
        def = "{'id' : 1, 'quoteAuthor': 1}"
    )
})
public class QuoteDocument {

  @Id
  private UUID id;
  private String quoteText;
  private String quoteAuthor;
  private String quoteGenre;
  @Version
  private Integer version;
}
