package service;

import java.util.List;

import model.WorkForce;

import repository.WorkForceRepository;
import repository.implementation.WorkForceRepositoryImpl;

public class WorkForceService implements WorkForceRepository {

    private WorkForceRepository workForceRepository;
    
    public WorkForceService(){
        this.workForceRepository = new WorkForceRepositoryImpl();
    }

    
    public List<WorkForce> getAll(long project_id) {
        return workForceRepository.getAll(project_id);
    }

    public Boolean addWorkForce(List<WorkForce> workForces, long project_id) {
        return workForceRepository.addWorkForce(workForces, project_id);
    }

}
