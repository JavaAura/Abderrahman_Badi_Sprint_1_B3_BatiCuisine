package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Quote;
import repository.QuoteRepository;
import util.DatabaseConnection;
import util.LoggerUtils;

public class QuoteService implements QuoteRepository {

    private static final String SQL_INSERT = "INSERT INTO public.quote(estimated_amount, issue_date) VALUES (?, ?)";
    private static final String SQL_UPDATE_STATUS = "UPDATE public.quote validity_date = ?, is_accepted = ? WHERE id = ?";

    private static Connection con = DatabaseConnection.getConnection();

    @Override
    public Boolean addQuote(Quote quote) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setDouble(1, quote.getEstimatedAmount());
            stmt.setDate(2, Date.valueOf(quote.getIssueDate()));

            int n = stmt.executeUpdate();

            return n == 1;

        } catch (SQLException e) {
            LoggerUtils.logger.warning(e.getMessage());
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    LoggerUtils.logger.warning(e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    LoggerUtils.logger.warning(e.getMessage());
                }
            }

        }

        return false;
    }

    @Override
    public Boolean updateStatus(Quote quote, String[] params) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL_UPDATE_STATUS);
            stmt.setDouble(1, Double.parseDouble(params[0]));
            stmt.setDate(2, Date.valueOf(LocalDate.parse(params[1])));
            stmt.setLong(3, quote.getId());

            int n = stmt.executeUpdate();

            return n == 1;
        } catch (SQLException e) {
            LoggerUtils.logger.warning(e.getMessage());
        } catch (NumberFormatException e) {
            LoggerUtils.logger.warning(e.getMessage());
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    LoggerUtils.logger.warning(e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    LoggerUtils.logger.warning(e.getMessage());
                }
            }

        }

        return false;
    }

}
