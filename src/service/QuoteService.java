package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import model.Quote;
import repository.QuoteRepository;

public class QuoteService implements QuoteRepository {

    @Override
    public Optional<Quote> get(long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<Quote> getAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Boolean addQuote(Quote quote) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addQuote'");
    }

    @Override
    public Boolean updateStatus(Quote quote) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatus'");
    }
    
}
