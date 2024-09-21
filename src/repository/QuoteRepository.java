package repository;

import java.util.Optional;

import model.Quote;

public interface QuoteRepository {

    public Optional<Quote> get(long id);

    public Boolean addQuote(Quote quote);

    public Boolean updateStatus(Quote quote);

}
