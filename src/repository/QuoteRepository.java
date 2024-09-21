package repository;

import java.sql.SQLException;
import java.util.Optional;
import java.util.List;

import model.Quote;

public interface QuoteRepository {

    public Optional<Quote> get(long id) throws SQLException;

    public List<Quote> getAll() throws SQLException;

    public Boolean addQuote(Quote quote) throws SQLException;

    public Boolean updateStatus(Quote quote) throws SQLException;

}
