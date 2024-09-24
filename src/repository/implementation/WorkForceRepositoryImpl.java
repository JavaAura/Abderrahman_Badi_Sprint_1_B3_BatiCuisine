package repository.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enums.ComponentType;
import model.WorkForce;
import repository.WorkForceRepository;
import util.DatabaseConnection;
import util.LoggerUtils;

public class WorkForceRepositoryImpl implements WorkForceRepository {
	private static final String SQL_LIST = "SELECT * FROM public.work_force WHERE project_id = ?";
	private static final String SQL_INSERT = "INSERT INTO  public.work_force (component_name, component_type, project_id, hourly_rate, work_hours, worker_productivity) VALUES(?, ?::component_type, ?, ?, ?, ?)";

	@Override
	public List<WorkForce> getAll(long project_id) {
		List<WorkForce> workForces = new ArrayList<>();

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_LIST)) {
			stmt.setLong(1, project_id);

			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					long id = rs.getLong("id");
					String name = rs.getString("component_name");
					ComponentType componentType = ComponentType.valueOf(rs.getString("component_type"));
					Double hourlyRate = rs.getDouble("hourly_rate");
					Double workHours = rs.getDouble("work_hours");
					Double workerProductivity = rs.getDouble("worker_productivity");

					WorkForce workForce = new WorkForce(id, name, componentType, hourlyRate, workHours,
							workerProductivity);

					workForces.add(workForce);

				}
			}
		} catch (SQLException e) {
			System.out.println("Unexpected error occured while retrieving work forces");
			LoggerUtils.logger.warning(e.getMessage());
			LoggerUtils.logStackTrace(e);
		}
		return workForces;
	}

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
