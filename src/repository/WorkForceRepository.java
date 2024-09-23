package repository;

import java.util.List;

import model.WorkForce;

public interface WorkForceRepository {

	public List<WorkForce> getAll(long project_id);
	public Boolean addWorkForce(List<WorkForce> workForce, long project_id);

}
