package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import enums.ProjectStatus;
import model.Project;
import repository.ProjectRepository;
import util.DatabaseConnection;
import util.LoggerUtils;

public class ProjectService implements ProjectRepository {

    private static final String SQL_LIST = "SELECT * FROM public.project JOIN public.client ON project.user_id = client.id JOIN public.quote ON project.quote_id = quote.id ORDER BY project.id ASC";
    private static final String SQL_INSERT = "INSERT INTO public.project(project_name, profit_margin, total_cost, surface, user_id, quote_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static Connection con = DatabaseConnection.getConnection();

    @Override
    public Optional<Project> get(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

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
            stmt.setLong(6, project.getQuote().getId());

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
    public List<Project> getAll(ProjectStatus projectStatus) {

        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Boolean updateStatus(Project project) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatus'");
    }
}
