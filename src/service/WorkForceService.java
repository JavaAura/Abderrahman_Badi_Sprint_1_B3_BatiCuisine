package service;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import model.WorkForce;
import util.DatabaseConnection;
import util.LoggerUtils;
import repository.WorkForceRepository;

public class WorkForceService implements WorkForceRepository {

    private static final String SQL_INSERT = "INSERT INTO  public.work_force (name , component_type , vat_rate, hourly_rate, work_hours, worker_productivity) VALUES(? , ? , ? , ? , ? , ?)";

    private static Connection con = DatabaseConnection.getConnection();

    @Override
    public Boolean addWorkForce(WorkForce workForce) throws SQLException {
        if (con == null) {
            throw new SQLException("Database connection is not initialized.");
        }

        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, workForce.getName());
            stmt.setString(2, "WORKFORCE");
            stmt.setDouble(3, workForce.getVatRate());
            stmt.setDouble(4, workForce.getHourlyRate());
            stmt.setDouble(5, workForce.getWorkHours());
            stmt.setDouble(6, workForce.getWorkerProductivity());

            int n = stmt.executeUpdate();
            return n == 1;

        } catch (SQLException e) {
            LoggerUtils.logger.warning(e.getMessage());
            return false;
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
    }

}
