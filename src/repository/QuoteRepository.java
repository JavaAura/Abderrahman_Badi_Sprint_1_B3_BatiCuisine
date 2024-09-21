package repository;

import model.Quote;

public interface QuoteRepository {

    public Boolean addQuote(Quote quote);

    public Boolean updateStatus(Quote quote, String[] params);

}
