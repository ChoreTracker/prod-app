package prodapp;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class SectorControllerTest {

	@InjectMocks
	private SectorController underTest;
	
	@Mock
	private SectorRepository sectorRepo;
	
	@Mock
	private Sector sector;
	
	@Mock
	private Sector sector2;
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleSectorToModel() throws sectorNotFoundException {
		long arbitrarySectorId = 1;
		when(sectorRepo.findById(arbitrarySectorId)).thenReturn(Optional.of(sector));
		underTest.findOneSector(arbitrarySectorId, model);
		verify(model).addAttribute("sectors", sector);
	}
	
	@Test
	public void shouldAddAllSectorsToModel() {
		Collection<Sector> allSectors = Arrays.asList(sector, sector2);
		when(sectorRepo.findAll()).thenReturn(allSectors);
		underTest.findAllSectors(model);
		verify(model).addAttribute("sectors", allSectors);
	}
	
	
}
