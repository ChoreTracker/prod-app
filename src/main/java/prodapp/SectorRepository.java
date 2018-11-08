package prodapp;

import org.springframework.data.repository.CrudRepository;

public interface SectorRepository extends CrudRepository<Sector, Long> {

	Sector findByMissionsContains(Mission...missions);

	Sector findBySectorName(String sectorName);


}
