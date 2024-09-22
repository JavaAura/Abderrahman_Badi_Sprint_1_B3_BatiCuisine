package repository;

import java.util.List;

import model.WorkForce;

public interface  WorkForceRepository {
        Boolean addWorkForce(List<WorkForce> workForce, long project_id);

}
