package com.mongo.quote.application.data;

import com.mongo.quote.persistence.document.QuoteDocument;

public class DataHelper {
  public static QuoteDocument getQuoteDocument(String id, String author) {
    var quoteDocument = new QuoteDocument();
    quoteDocument.setId("id");
    quoteDocument.setQuoteAuthor(author);
    quoteDocument.setQuoteGenre("genre");
    quoteDocument.setQuoteText("some quote");
    quoteDocument.setVersion(0);
    return quoteDocument;
  }
}
