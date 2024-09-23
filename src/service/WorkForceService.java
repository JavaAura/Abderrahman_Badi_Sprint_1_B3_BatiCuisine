package service;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;

import model.WorkForce;
import util.DatabaseConnection;
import util.LoggerUtils;
import repository.WorkForceRepository;

public class WorkForceService implements WorkForceRepository {

    private static final String SQL_INSERT = "INSERT INTO  public.work_force (component_name, component_type, project_id, hourly_rate, work_hours, worker_productivity) VALUES(? , ? , ? , ? , ? , ?)";

    @Override
    public Boolean addWorkForce(List<WorkForce> workForces, long project_id) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL_INSERT);) {
            for (WorkForce workForce : workForces) {
                stmt.setString(1, workForce.getName());
                stmt.setString(2, "WORKFORCE");
                stmt.setLong(3, project_id);
                stmt.setDouble(4, workForce.getHourlyRate());
                stmt.setDouble(5, workForce.getWorkHours());
                stmt.setDouble(6, workForce.getWorkerProductivity());

                int n = stmt.executeUpdate();
                if (n == 1) {
                    LoggerUtils.logger
                            .info("Component of type \"Material\" added successfully: " + workForce.getName());
                } else {
                    LoggerUtils.logger.warning("Failed to add component: " + workForce.getName());
                }
            }

            return true;

        } catch (SQLException e) {
            System.out.println("Unexpected error occured while inserting work forces");
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }

        return false;

    }

}
