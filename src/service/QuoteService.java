package service;

import java.util.Optional;

import model.Quote;
import repository.QuoteRepository;

public class QuoteService implements QuoteRepository {

    @Override
    public Optional<Quote> get(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public Boolean addQuote(Quote quote) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addQuote'");
    }

    @Override
    public Boolean updateStatus(Quote quote) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatus'");
    }
    
}
