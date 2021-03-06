package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.entity.Maintenance;

public interface IMaintenanceService {
	public Integer insert(Maintenance maintenance, int valid);
	public void insertm(Maintenance maintenance);
	public void delete(int idMaintenance);

	List<Maintenance> list();
	Optional<Maintenance> listId(int idMaintenance);
	List<String[]>Reportmes();
}
