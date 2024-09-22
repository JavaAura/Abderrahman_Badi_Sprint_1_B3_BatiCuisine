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
    public Boolean addWorkForce(WorkForce workForce) {
        try (PreparedStatement stmt = con.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, workForce.getName());
            stmt.setString(2, "WORKFORCE");
            stmt.setDouble(3, workForce.getVatRate());
            stmt.setDouble(4, workForce.getHourlyRate());
            stmt.setDouble(5, workForce.getWorkHours());
            stmt.setDouble(6, workForce.getWorkerProductivity());

            int n = stmt.executeUpdate();
            if (n == 1) {
                LoggerUtils.logger.info("Component of type \"Work Force\" added successfully: " + workForce.getName());
                return true;
            }

        } catch (SQLException e) {
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }

        return false;

    }

}
