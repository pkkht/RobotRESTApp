package com.robotposition;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.robotposition.model.RobotPosition;
import com.robotposition.model.RobotPositionEnum;
import com.robotposition.service.IRobotPositionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Hari
 * JUnit Tests for few controller methods
 */
@WebMvcTest
public class RobotRestControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IRobotPositionService robotPositionService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void givenRobotPositionObject_whenCreateRobotPosition_thenReturnSavedRobotPosition() throws Exception {

		//Given
		RobotPosition robotPosition =  new RobotPosition();
		robotPosition.setRobotPositionId(1);
		robotPosition.setFacingdir(RobotPositionEnum.NORTH.getDirection());
		robotPosition.setXpos(0);
		robotPosition.setYpos(0);
		given(robotPositionService.createRobotPosition(any(RobotPosition.class)))
				.willAnswer((invocation)-> invocation.getArgument(0));

		//When
		ResultActions response = mockMvc.perform(post("/api/robotposition/createRobotPosition")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(robotPosition)));

		//Then
		response.andDo(print()).
				andExpect(status().isCreated())
				.andExpect(jsonPath("$.robotPositionId",
						is(robotPosition.getRobotPositionId())))
				.andExpect(jsonPath("$.facingdir",
						is(robotPosition.getFacingdir())))
				.andExpect(jsonPath("$.xpos",
						is(robotPosition.getXpos())))
				.andExpect(jsonPath("$.ypos",
						is(robotPosition.getYpos())));

	}

	// positive scenario - valid robot position id
	@Test
	public void givenRobotPositionId_whenGetRobotPositionById_thenReturnRobotPositionObject() throws Exception{

		//Given
		RobotPosition robotPosition =  new RobotPosition();
		robotPosition.setRobotPositionId(1);
		robotPosition.setFacingdir(RobotPositionEnum.NORTH.getDirection());
		robotPosition.setXpos(0);
		robotPosition.setYpos(0);
		given(robotPositionService.findRobotPositionById(robotPosition.getRobotPositionId())).willReturn(Optional.of(robotPosition));

		// when
		ResultActions response = mockMvc.perform(get("/api/robotposition/{id}", robotPosition.getRobotPositionId()));

		// then
		response.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.robotPositionId",
						is(robotPosition.getRobotPositionId())))
				.andExpect(jsonPath("$.facingdir",
						is(robotPosition.getFacingdir())))
				.andExpect(jsonPath("$.xpos",
						is(robotPosition.getXpos())))
				.andExpect(jsonPath("$.ypos",
						is(robotPosition.getYpos())));

	}

	// negative scenario for above test - GET by id
	@Test
	public void givenRobotPositionId_whenGetRobotPositionById_thenReturnEmpty() throws Exception{

		//Given
		RobotPosition robotPosition =  new RobotPosition();
		robotPosition.setRobotPositionId(1);
		robotPosition.setFacingdir(RobotPositionEnum.NORTH.getDirection());
		robotPosition.setXpos(0);
		robotPosition.setYpos(0);
		given(robotPositionService.findRobotPositionById(robotPosition.getRobotPositionId())).willReturn(Optional.empty());

		// when
		ResultActions response = mockMvc.perform(get("/api/robotposition/{id}", robotPosition.getRobotPositionId()));

		// then
		response.andExpect(status().isNotFound())
				.andDo(print());

	}

	@Test
	public void givenListOfRobotPositions_whenGetAllRobotPositions_thenReturnRobotPositionsList() throws Exception{
		// given
		List<RobotPosition> listOfRobotPositions = new ArrayList<>();
		RobotPosition robotPosition1 =  new RobotPosition();
		robotPosition1.setRobotPositionId(1);
		robotPosition1.setFacingdir(RobotPositionEnum.NORTH.getDirection());
		robotPosition1.setXpos(0);
		robotPosition1.setYpos(0);

		RobotPosition robotPosition2 =  new RobotPosition();
		robotPosition2.setRobotPositionId(1);
		robotPosition2.setFacingdir(RobotPositionEnum.NORTH.getDirection());
		robotPosition2.setXpos(3);
		robotPosition2.setYpos(3);

		listOfRobotPositions.add(robotPosition1);
		listOfRobotPositions.add(robotPosition2);
		given(robotPositionService.findAllRobotPositions()).willReturn(listOfRobotPositions);

		// when
		ResultActions response = mockMvc.perform(get("/api/robotposition/report"));

		// then
		response.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.size()",
						is(listOfRobotPositions.size())));

	}

	@Test
	public void givenRobotPositionId_whenDeleteRobotPositionRecord_thenReturn200() throws Exception{
		// given
		RobotPosition robotPosition =  new RobotPosition();
		robotPosition.setRobotPositionId(1);
		robotPosition.setFacingdir(RobotPositionEnum.NORTH.getDirection());
		robotPosition.setXpos(0);
		robotPosition.setYpos(0);
		given(robotPositionService.findRobotPositionById(robotPosition.getRobotPositionId())).willReturn(Optional.of(robotPosition));

		// when
		ResultActions response = mockMvc.perform(delete("/api/robotposition/{id}", robotPosition.getRobotPositionId()));

		// then
		response.andExpect(status().isOk())
				.andDo(print());
	}
}
