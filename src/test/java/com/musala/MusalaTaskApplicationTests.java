package com.musala;

import com.musala.domain.DroneModel;
import com.musala.domain.DroneState;
import com.musala.domain.dto.DroneDto;
import com.musala.rest.DispatchRestController;
import com.musala.service.DroneDispatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//@WebMvcTest
//@MockBean(DispatchRestController.class)
@SpringBootTest
@AutoConfigureMockMvc
class MusalaTaskApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DroneDispatchService droneDispatchService;


	@Test
	public void registerDroneTest() throws Exception {

		String newDrone = "{\"serialNo\":\"DRNMUS101A\",\"droneModel\":\"LightWeight\", \"droneState\":\"IDLE\", \"weightLimit\":500, \"batteryCapacity\":10 }";

		DroneDto droneDto = this.createDrone();
		when(this.droneDispatchService.registerDrone(droneDto)).thenReturn(droneDto);

		this.mockMvc.perform(post("/drone-dispatch-service/register").
						content(newDrone).
				contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON)).
				andExpect(status().isCreated());
	}

	@Test
	public void loadDroneMedicationsTest()
	{

	}

	@Test
	public void getLoadedMedicationsTest()
	{

	}

	@Test
	public void getAvailableDronesTest() throws Exception {

		DroneDto droneDto1, droneDto2, droneDto3;
		droneDto1 = new DroneDto();
		droneDto1.setId(1);
		droneDto1.setDroneState(DroneState.IDLE);
		droneDto1.setWeightLimit(200);
		droneDto1.setDroneModel(DroneModel.LightWeight);
		droneDto1.setSerialNo("DRNMUS101A");

		droneDto2 = new DroneDto();
		droneDto2.setSerialNo("DRNMUS101B");
		droneDto2.setDroneModel(DroneModel.CruiserWeight);
		droneDto2.setDroneState(DroneState.IDLE);
		droneDto2.setBatteryCapacity(400d);
		droneDto2.setWeightLimit(20);
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

	private DroneDto createDrone()
	{
		DroneDto droneDto = new DroneDto();
		droneDto.setDroneModel(DroneModel.LightWeight);
		droneDto.setDroneState(DroneState.IDLE);
		droneDto.setBatteryCapacity(10.0);
		droneDto.setSerialNo("DRNMUS101A");
		droneDto.setWeightLimit(500);

		return droneDto;
	}
	@BeforeEach( )
	public void  setUp()
	{
		Mockito.reset(droneDispatchService);
	}

}
