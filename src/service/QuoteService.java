package service;

import model.Quote;
import repository.QuoteRepository;
import repository.implementation.QuoteRepositoryImpl;


public class QuoteService implements QuoteRepository {

    private QuoteRepository quoteRepository;

    public QuoteService(){
        this.quoteRepository = new QuoteRepositoryImpl();
    }

    public Quote addQuote(Quote quote) {
        return quoteRepository.addQuote(quote);
    }

    public Boolean updateStatus(Quote quote, Boolean is_accepted) {
        return quoteRepository.updateStatus(quote, is_accepted);
    }

}
