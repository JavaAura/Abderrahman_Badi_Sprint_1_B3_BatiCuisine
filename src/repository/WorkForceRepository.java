package repository;

import java.sql.SQLException;

import model.WorkForce;

public interface  WorkForceRepository {
        Boolean addWorkForce(WorkForce workForce) throws SQLException;

}
