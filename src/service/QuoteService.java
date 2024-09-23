package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Quote;
import repository.QuoteRepository;
import util.DatabaseConnection;
import util.LoggerUtils;

public class QuoteService implements QuoteRepository {

    private static final String SQL_INSERT = "INSERT INTO public.quote(estimated_amount, issue_date) VALUES (?, ?)";
    private static final String SQL_UPDATE_STATUS = "UPDATE public.quote validity_date = ?, is_accepted = ? WHERE id = ?";

    @Override
    public Quote addQuote(Quote quote) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {
            stmt.setDouble(1, quote.getEstimatedAmount());
            stmt.setDate(2, Date.valueOf(quote.getIssueDate()));

            int n = stmt.executeUpdate();

            if (n > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        long generatedId = rs.getLong(1); 
                        quote.setId(generatedId);
                        LoggerUtils.logger.info("Quote added successfully with ID: " + generatedId);
                        return quote;
                    }
                }
            } else {
                LoggerUtils.logger.warning("Project insertion failed, no rows affected.");
            }

        } catch (SQLException e) {
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }
        return null;
    }

    @Override
    public Boolean updateStatus(Quote quote, String[] params) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL_UPDATE_STATUS);) {
            stmt.setDouble(1, Double.parseDouble(params[0]));
            stmt.setDate(2, Date.valueOf(LocalDate.parse(params[1])));
            stmt.setLong(3, quote.getId());

            int n = stmt.executeUpdate();

            if (n == 1) {
                LoggerUtils.logger.info("Quote updated successfully");
                return true;
            }
        } catch (SQLException e) {
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        } catch (NumberFormatException e) {
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }

        return false;
    }

}
