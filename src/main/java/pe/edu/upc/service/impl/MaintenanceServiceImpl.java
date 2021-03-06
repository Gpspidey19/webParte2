package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Maintenance;
import pe.edu.upc.repository.IMaintenanceRepository;
import pe.edu.upc.service.IMaintenanceService;


@Service
public class MaintenanceServiceImpl implements IMaintenanceService {
	@Autowired
	private IMaintenanceRepository mR;

	@Override
	@Transactional
	public Integer insert(Maintenance maintenance, int valid) {
		
		int rpta = mR.findByid_Inspection(maintenance.getInspection());
		if (valid == 0) {
			if (rpta == 0) {
				mR.save(maintenance);
				return rpta;
			}
		}
		if(valid==1)
		{
			mR.save(maintenance);
			rpta=0;
			return rpta;
		}
		return rpta;
		
		
	}

	@Override
	public void delete(int idMaintenance) {
		mR.deleteById(idMaintenance);
	}

	@Override
	public List<Maintenance> list() {
		return mR.findAll(Sort.by(Sort.Direction.ASC,"idMaintenance"));
	}

	@Override
	public Optional<Maintenance> listId(int idMaintenance) {
		return mR.findById(idMaintenance);
	}

	@Override
	public List<String[]> Reportmes() {
		return mR.Reportmes();
	}

	@Override
	public void insertm(Maintenance maintenance) {
		mR.save(maintenance);
	}



}
