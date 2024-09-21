package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import enums.ProjectStatus;
import model.Project;
import repository.ProjectRepository;
import util.DatabaseConnection;

public class ProjectService implements ProjectRepository {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM public.project WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM public.project WHERE is_deleted = false ORDER BY id ASC";
    private static final String SQL_INSERT = "INSERT INTO public.project(project_name, profit_margin, total_cost, surface, user_id, quote_id) VALUES (?, ?, ?, ?, ?)";

    private static Connection con = DatabaseConnection.getConnection();

    @Override
    public Boolean addProject(Project project) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, project.getProjectName());
            stmt.setDouble(2, project.getProfitMargin());
            stmt.setDouble(3, project.getTotalCost());
            stmt.setDouble(4, project.getSurface());
            stmt.setLong(5, project.getClient().getId());

            int n = stmt.executeUpdate();

            return n == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return false;

    }
    @Override
    public List<Project> getAll(ProjectStatus projectStatus) {

        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Boolean updateStatus(Project project) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatus'");
    }
}
