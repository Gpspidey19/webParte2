package pe.edu.upc.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Inspection;

@Repository
public interface IInspectionRepository extends JpaRepository<Inspection,Integer> {
	@Query("select count(i.d_Inspection) from Inspection i where i.d_Inspection =:d_Inspection")
	public int findbyID_Inspection(@Param("d_Inspection") Date d_Inspection);

	
	
}
