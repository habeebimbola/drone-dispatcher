package com.musala;

import com.musala.domain.Drone;
import com.musala.domain.DroneModel;
import com.musala.domain.DroneState;
import com.musala.domain.dto.DroneDto;
import com.musala.domain.dto.MedicationDto;
import com.musala.service.DroneDispatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//@WebMvcTest
//@MockBean(DispatchRestController.class)
@SpringBootTest
@AutoConfigureMockMvc
class DroneDispatcherApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private DroneDispatchService droneDispatchService;

	@Test
	public void registerDroneTest() throws Exception {

		String newDrone = "{\"serialNo\":\"DRNMUS101A\",\"droneModel\":\"LightWeight\", \"droneState\":\"IDLE\", \"weightLimit\":500, \"batteryCapacity\":10 }";

		DroneDto droneDto = createDummyDrone();
		when(this.droneDispatchService.registerDrone(droneDto)).thenReturn(droneDto);

		this.mockMvc.perform(post("/drone-dispatch-service/register").
						content(newDrone).
				contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON)).
				andExpect(status().isCreated());
	}

	@Test
	public void loadDroneMedicationsTest() throws Exception {
		Drone drone = new Drone(); drone.setSerialNo("DRNMUS101A");drone.setWeightLimit(24d); drone.setDroneState(DroneState.DELIVERED);
		Optional<Drone> droneOptional = Optional.ofNullable(drone);
		when(this.droneDispatchService.getDrone("DRNMUS101A")).thenReturn(droneOptional);

		String medicationDtos ="[{\"code\": \"DRG001\"}" +
				",{\"code\": \"DRG001\"}]";

		this.mockMvc.perform(patch("/drone-dispatch-service/drone/load/DRNMUS101A").
				accept(APPLICATION_JSON).
				contentType(APPLICATION_JSON).
				content(medicationDtos)).
				andExpect(status().isAccepted());
	}



	@Test
	public void getLoadedMedicationsTest() throws Exception {
		MedicationDto medicationDto = new MedicationDto();
		medicationDto.setName("Panadol Paracetamol");
		medicationDto.setImageURL("https://4.imimg.com/data4/AQ/WT/MY-7047793/dostinex-250x250.jpg");
		medicationDto.setCode("DRG0024");
		medicationDto.setWeight(120d);
		medicationDto.setId(1);

		MedicationDto medicationDto2 = new MedicationDto();
		medicationDto2.setName("Aspirin");
		medicationDto2.setImageURL("https://4.imimg.com/data4/AQ/WT/MY-7047793/dostinex-250x250.jpg");
		medicationDto2.setCode("DRG0025");
		medicationDto2.setWeight(100d);
		medicationDto2.setId(2);

		List<MedicationDto> medicationDtos = new ArrayList<>(); medicationDtos.add(medicationDto); medicationDtos.add(medicationDto2);

		when(this.droneDispatchService.getLoadedDroneMedications("DRNMUS101B")).thenReturn(medicationDtos);

		this.mockMvc.perform(get("/drone-dispatch-service/drone/medications/DRNMUS101B").
				accept(APPLICATION_JSON).
				contentType(APPLICATION_JSON)).
				andExpect(status().isOk());
	}

	@Test
	public void getAvailableDronesTest() throws Exception {

		DroneDto droneDto1, droneDto2, droneDto3;
		droneDto1 = new DroneDto();
		droneDto1.setId(1);
		droneDto1.setDroneState(DroneState.IDLE);
		droneDto1.setWeightLimit(200d);
		droneDto1.setDroneModel(DroneModel.LightWeight);
		droneDto1.setSerialNo("DRNMUS101A");

		droneDto2 = new DroneDto();
		droneDto2.setSerialNo("DRNMUS101B");
		droneDto2.setDroneModel(DroneModel.CruiserWeight);
		droneDto2.setDroneState(DroneState.IDLE);
		droneDto2.setBatteryCapacity(400d);
		droneDto2.setWeightLimit(20d);
		droneDto2.setId(2);

		List<DroneDto> droneDtoList = new ArrayList<>(); droneDtoList.add(droneDto1); droneDtoList.add(droneDto2);

		when(this.droneDispatchService.getDronesAvailableForLoading()).thenReturn(droneDtoList);

		this.mockMvc.perform(get("/drone-dispatch-service/drone/available").
				contentType(APPLICATION_JSON).
				accept(APPLICATION_JSON)).
				andExpect(status().isOk());

	}

	@Test
	public void getBatteryLevelTest() throws Exception {

		when(this.droneDispatchService.getDroneBatteryLevel("DRNMUS101A")).thenReturn(300d);

		this.mockMvc.perform(get("/drone-dispatch-service/drone/DRNMUS101A/battery-level").
				accept(APPLICATION_JSON).
				contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private DroneDto createDummyDrone()
	{
		DroneDto droneDto = new DroneDto();
		droneDto.setDroneModel(DroneModel.LightWeight);
		droneDto.setDroneState(DroneState.IDLE);
		droneDto.setBatteryCapacity(10.0);
		droneDto.setSerialNo("DRNMUS101A");
		droneDto.setWeightLimit(500d);
		droneDto.setId(1);

		return droneDto;
	}

	@Test
	public void createMedicationTest() throws Exception {

		String medication = "{ \"name\": \"DOSTINEXcabergoline_\",\"weight\": 19.0,\"code\": \"DRG021\",\"imageUrl\": \"https://4.imimg.com/data4/AQ/WT/MY-7047793/dostinex-250x250.jpg\"}";
		MedicationDto medicationDto = createMedicationDto();
		when(this.droneDispatchService.createMedication(medicationDto)).thenReturn(medicationDto);

		this.mockMvc.perform(post("/medication/create").content(medication).
				contentType(APPLICATION_JSON).
				accept(APPLICATION_JSON)).andExpect(status().isCreated());
	}
	private MedicationDto createMedicationDto() {
		MedicationDto medicationDto = new MedicationDto();
		medicationDto.setName("Panadol Paracetamol");
		medicationDto.setImageURL("https://4.imimg.com/data4/AQ/WT/MY-7047793/dostinex-250x250.jpg");
		medicationDto.setCode("DRG0024");
		medicationDto.setWeight(120d);
		medicationDto.setId(1);

		return 	medicationDto;
	}
	@BeforeEach( )
	public void  setUp()
	{
		Mockito.reset(droneDispatchService);
	}

}
