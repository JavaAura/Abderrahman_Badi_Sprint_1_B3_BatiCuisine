package repository.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.ProjectStatus;
import model.Client;
import model.Project;
import model.Quote;
import repository.ProjectRepository;
import util.DatabaseConnection;
import util.LoggerUtils;

public class ProjectRepositoryImpl implements ProjectRepository {

    private static final String SQL_LIST = "SELECT project.id, project.project_name, project.profit_margin, project.total_cost, project.project_status, project.surface, project.vat_rate, client.id AS client_id, client.name, client.address, client.phone_number, client.is_professional, quote.id AS quote_id, quote.estimated_amount, quote.issue_date, quote.validity_date, quote.is_accepted FROM public.project JOIN public.client ON project.client_id = client.id JOIN public.quote ON project.quote_id = quote.id WHERE project_status = ? ORDER BY project.id ASC";

    private static final String SQL_LIST_WHERE_NULL = "SELECT project.id, project.project_name, project.profit_margin, project.total_cost, project.project_status, project.surface, project.vat_rate, client.id AS client_id, client.name, client.address, client.phone_number, client.is_professional, quote.id AS quote_id, quote.estimated_amount, quote.issue_date, quote.validity_date, quote.is_accepted FROM public.project JOIN public.client ON project.client_id = client.id JOIN public.quote ON project.quote_id = quote.id WHERE project_status IS NULL ORDER BY project.id ASC";

    private static final String SQL_INSERT = "INSERT INTO public.project(project_name, profit_margin, total_cost, surface, vat_rate, client_id, quote_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE public.project SET project_status = ? WHERE id = ?";

    @Override
    public List<Project> getAll(ProjectStatus status) {
        List<Project> projects = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(status == null ? SQL_LIST_WHERE_NULL : SQL_LIST)) {
            if (status != null)
                stmt.setObject(1, status.name(), java.sql.Types.OTHER);
            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String projectName = rs.getString("project_name");
                    Double profitMargin = rs.getDouble("profit_margin");
                    Double totalCost = rs.getDouble("total_cost");
                    ProjectStatus projectStatus = rs.getString("project_status") != null
                            ? ProjectStatus.valueOf(rs.getString("project_status"))
                            : null;
                    Double surface = rs.getDouble("surface");
                    Double vatRate = rs.getDouble("vat_rate");

                    long client_id = rs.getLong("client_id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String phoneNumber = rs.getString("phone_number");
                    Boolean isProfessional = rs.getBoolean("is_professional");

                    Client client = new Client(client_id, name, address, phoneNumber, isProfessional);

                    long quote_id = rs.getLong("quote_id");
                    Double estimatedAmount = rs.getDouble("estimated_amount");
                    LocalDate issuDate = rs.getDate("issue_date") != null ? rs.getDate("issue_date").toLocalDate()
                            : null;
                    LocalDate validityDate = rs.getDate("validity_date") != null
                            ? rs.getDate("validity_date").toLocalDate()
                            : null;
                    Boolean isAccepted = rs.getBoolean("is_accepted");

                    Quote quote = new Quote(quote_id, estimatedAmount, issuDate, validityDate, isAccepted);

                    Project project = new Project(id, projectName, profitMargin, totalCost, projectStatus, surface,
                            vatRate, client, quote);

                    projects.add(project);

                }
            }
        } catch (SQLException e) {
            System.out.println("Unexpected error occured while retrieving projects");
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }
        return projects;
    }

    @Override
    public Project addProject(Project project) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, project.getProjectName());
            stmt.setDouble(2, project.getProfitMargin());
            stmt.setDouble(3, project.getTotalCost());
            stmt.setDouble(4, project.getSurface());
            stmt.setDouble(5, project.getVatRate());
            stmt.setLong(6, project.getClient().getId());
            stmt.setLong(7, project.getQuote().getId());

            int n = stmt.executeUpdate();
            if (n > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        long generatedId = rs.getLong(1);
                        project.setId(generatedId);
                        LoggerUtils.logger.info("Project added successfully with ID: " + generatedId);
                        return project;
                    }
                }
            } else {
                LoggerUtils.logger.warning("Project insertion failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println("Unexpexted error occured");
            LoggerUtils.logger.warning("Error adding project: " + e.getMessage());
            LoggerUtils.logStackTrace(e);
        }
        return null;
    }

    @Override
    public Boolean updateStatus(Project project, ProjectStatus status) {

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
            stmt.setObject(1, status.name(), java.sql.Types.OTHER);
            stmt.setLong(2, project.getId());

            int n = stmt.executeUpdate();

            if (n == 1) {
                LoggerUtils.logger.info("Project status updated successfully: " + project.getProjectName());
                return true;
            } else {
                LoggerUtils.logger.warning("Failed to update project status: " + project.getProjectName());
            }
        } catch (SQLException e) {
            System.out.println("Unexpected error occured while updating the project's status");
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }
        return false;
    }
}
