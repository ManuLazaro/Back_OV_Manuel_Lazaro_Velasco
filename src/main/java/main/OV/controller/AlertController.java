package main.OV.controller;


import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RepositoryRestController
public class AlertController {

	/** The building service. */
	@Autowired
	private IAlertService alertService;

	Logger log = org.apache.logging.log4j.LogManager.getLogger(AlertController.class);


	/**
	 * Gets the buildings info.
	 *
	 * @return the buildings info
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISTRIBUTOR', 'ROLE_PROPERTY_USER', 'ROLE_COUNCIL_USER', 'ROLE_OWNER', 'ROLE_MAINTAINER')")
	@RequestMapping(value = "/alertsInfo/findBuildingAlerts", method = RequestMethod.GET)
	public ResponseEntity<Map<Long, BuildingAlertsDto>> getBuildingsInfo(
			@RequestParam(name = "buildingId") Long buildingId,
			@RequestParam(name = "buildingParentId") Long buildingParentId) {
		try {
			List<AlertInfoViewEntity> alertList = alertService.getBuildingAlerts(buildingId, buildingParentId);
			return new ResponseEntity<Map<Long, BuildingAlertsDto>>(
					parseAlertsInfoToBuildingAlertsEntityToDto(alertList), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error getBuildingsInfo", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Map<Long, BuildingAlertsDto> parseAlertsInfoToBuildingAlertsEntityToDto(
			List<AlertInfoViewEntity> alertList) {
		Map<Long, BuildingAlertsDto> buildingAlertsMap = new HashMap<Long, BuildingAlertsDto>();
		for (AlertInfoViewEntity alertInfo : alertList) {
			Long buildingId = alertInfo.getBuildingId();
			BuildingAlertsDto buildingAlertsDto = buildingAlertsMap.get(buildingId);
			if (buildingAlertsDto == null) {
				buildingAlertsDto = new BuildingAlertsDto(alertInfo.getBuildingId(), alertInfo.getBuildingType(),
						new HashMap<Long, MeterAlertsDto>(), alertInfo.getBlock(), alertInfo.getDoor(),
						alertInfo.getFloor(), alertInfo.getStaircase());
				buildingAlertsMap.put(alertInfo.getBuildingId(), buildingAlertsDto);
			}
			MeterAlertsDto meterAlertsDto = buildingAlertsDto.getMeters().get(alertInfo.getMeterId());
			if (meterAlertsDto == null) {
				meterAlertsDto = new MeterAlertsDto(alertInfo.getMeterId(), alertInfo.getMeterSerialNumber(),
						new HashMap<Long, AlertsDto>());
				buildingAlertsDto.getMeters().put(alertInfo.getMeterId(), meterAlertsDto);
			}
			Map<Long, AlertsDto> alertsDtoMap = meterAlertsDto.getAlerts();
			AlertsDto alertsDto = new AlertsDto(alertInfo.getId(), alertInfo.getAlertCodeId(),
					alertInfo.getAlertCodeName(), alertInfo.getIsSolved(), alertInfo.getSolvedDate());
			alertsDtoMap.put(alertInfo.getId(), alertsDto);

		}
		return buildingAlertsMap;

	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISTRIBUTOR','ROLE_PROPERTY_USER', 'ROLE_COUNCIL_USER', 'ROLE_MAINTAINER')")
	@RequestMapping(value = "/alertsInfo/getBuildingsWithAlerts", method = RequestMethod.GET)
	public ResponseEntity<BuildingNumAlertsListDto> getBuildingsWithAlerts() {
		try {
			CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			BuildingNumAlertsListDto alertList = alertService.getBuildinsWithAlerts();
			return new ResponseEntity<>(alertList, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error getBuildingsInfo", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISTRIBUTOR', 'ROLE_PROPERTY_USER', 'ROLE_COUNCIL_USER', 'ROLE_MAINTAINER')")
	@RequestMapping(value = "/alert/getAlerts", method = RequestMethod.GET)
	public ResponseEntity<Page<AlertViewEntity>> getAlerts(
			@RequestParam(name = "pagination", required = true) String paginationParam,
			@RequestParam(name = "sorting", required = false) String sortingParam,
			@RequestParam(name = "filters", required = false) String filtersParam) {

		log.info("Start AlertController.getAlerts");
		Pagination pagination = null;
		Filters filters = null;
		List<Long> councils = null;
		List<Long> buildings = null;
		List<Long> manufactures = null;
		String meterSerialNumber = "";
		AddressFilter addressApartment = null;
		String titular = "";
		Date startDate = null;
		Date endDate = null;
		List<Long> services = null;
		List<Long> typeAlert = null;
		List<Long> alarmTypes = null;

		try
		{
			pagination = new ObjectMapper().readValue(paginationParam, Pagination.class);

			if (null != filtersParam) {

				filters = new ObjectMapper().readValue(filtersParam, Filters.class);
				councils = CollectionUtils.isEmpty(filters.getCouncils()) ? null : filters.getCouncils();
				buildings = CollectionUtils.isEmpty(filters.getBuildings()) ? null : filters.getBuildings();
				manufactures = CollectionUtils.isEmpty(filters.getManufactures()) ? null : filters.getManufactures();
				meterSerialNumber = filters.getReadingSerialNumber();
				addressApartment = filters.getAddressApartment();
				titular = filters.getTitular();
				startDate = filters.getStartDate();
				endDate = filters.getEndDate();
				services = CollectionUtils.isEmpty(filters.getServices()) ? null : filters.getServices();
				alarmTypes = CollectionUtils.isEmpty(filters.getAlarmTypes()) ? null : filters.getAlarmTypes();
				typeAlert = CollectionUtils.isEmpty(filters.getTypeAlert()) ? null : filters.getTypeAlert();

			}

			return new ResponseEntity<Page<AlertViewEntity>>(alertService.getAllAlert(
					new PageRequest(pagination.getPageNumber(), pagination.getPageSize()), councils, buildings, meterSerialNumber, manufactures, titular, addressApartment, startDate, endDate, services, alarmTypes, typeAlert), HttpStatus.OK);

		} catch (Exception e) {
			log.error("error en getAlerts", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISTRIBUTOR', 'ROLE_PROPERTY_USER', 'ROLE_COUNCIL_USER')")
	@RequestMapping(value = "/alert/exportAlerts", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<?> exportAlerts (@RequestParam(name = "filters", required = false) String filtersParam,
										  HttpServletResponse response) {
		Filters filters = null;
		List<Long> councils = null;
		List<Long> buildings = null;
		List<Long> manufactures = null;
		String meterSerialNumber = "";
		AddressFilter addressApartment = null;
		String titular = "";
		Date startDate = null;
		Date endDate = null;
		List<Long> services = null;
		List<Long> typeAlert = null;
		List<Long> alarmTypes = null;

		try {
			if (null != filtersParam) {
				filters = new ObjectMapper().readValue(filtersParam, Filters.class);
				councils = CollectionUtils.isEmpty(filters.getCouncils()) ? null : filters.getCouncils();
				buildings = CollectionUtils.isEmpty(filters.getBuildings()) ? null : filters.getBuildings();
				manufactures = CollectionUtils.isEmpty(filters.getManufactures()) ? null : filters.getManufactures();
				meterSerialNumber = filters.getReadingSerialNumber();
				addressApartment = filters.getAddressApartment();
				titular = filters.getTitular();
				startDate = filters.getStartDate();
				endDate = filters.getEndDate();
				services = CollectionUtils.isEmpty(filters.getServices()) ? null : filters.getServices();
				alarmTypes = CollectionUtils.isEmpty(filters.getAlarmTypes()) ? null : filters.getAlarmTypes();
				typeAlert = CollectionUtils.isEmpty(filters.getTypeAlert()) ? null : filters.getTypeAlert();
			}

			byte[] salida = alertService.exportAlert(councils, buildings, meterSerialNumber, manufactures, titular, addressApartment, startDate, endDate, services, alarmTypes, typeAlert);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment; filename=Lecturas_diarias.xlsx");
			response.setContentLength(salida.length);
			ServletOutputStream out = response.getOutputStream();
			out.write(salida);
			out.flush();
			out.close();

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("error en exportReading", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RequestMapping(value = "/alert/type", method = RequestMethod.POST)
	public ResponseEntity<?> saveType(@RequestBody AlertCodeEntity alarm) {
		try {
			alertService.saveType(alarm);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ConstraintViolationException e) {
			String errors = e.getConstraintViolations().stream().map(s -> s.getMessage()).collect(Collectors.joining(", "));
			log.error("Error save alarm type", e);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error save alarm type", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RequestMapping(value = "/alert/type", method = RequestMethod.GET)
	public ResponseEntity<?> getOne(@RequestParam(name = "id", required = true) Long id) {
		try {
			AlertCodeEntity alarm = alertService.getOne(id);
			return new ResponseEntity<>(alarm, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error get one alarm type", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISTRIBUTOR')")
	@RequestMapping(value = "/alert/type/list", method = RequestMethod.GET)
	public ResponseEntity<Page<AlertCodeEntity>> getList(@RequestParam(name = "pagination", required = true) String paginationParam,
														 @RequestParam(name = "sorting", required = false) String sortingParam,
														 @RequestParam(name = "filters", required = false) String filtersParam) {
		try {
			Pagination pagination = new ObjectMapper().readValue(paginationParam, Pagination.class);

			List<Long> manufacturers = null;
			List<Long> meterTypes = null;
			String code = null;

			if (null != filtersParam) {
				Filters filters = new ObjectMapper().readValue(filtersParam, Filters.class);
				manufacturers = CollectionUtils.isEmpty(filters.getManufactures()) ? null : filters.getManufactures();
				meterTypes = CollectionUtils.isEmpty(filters.getTypeMeters()) ? null : filters.getTypeMeters();
				code = filters.getAlarmCode();
			}

			Sorting sorting = null;
			if (null != sortingParam) {
				sorting = new ObjectMapper().readValue(sortingParam, Sorting.class);
			}
			Page<AlertCodeEntity> alertTypes = alertService.getList(new PageRequest(pagination.getPageNumber(), pagination.getPageSize()), sorting, manufacturers, meterTypes, code);
			return new ResponseEntity<>(alertTypes, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error ALERT getList", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RequestMapping(value = "/alert/type", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteOne(@RequestParam(name = "id", required = true) Long id) {
		try {
			alertService.deleteOne(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error delete one alarm type", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISTRIBUTOR', 'ROLE_PROPERTY_USER', 'ROLE_COUNCIL_USER', 'ROLE_MAINTAINER')")
	@RequestMapping(value = "/alert/type/codes", method = RequestMethod.GET)
	public ResponseEntity<?> getCodes(@RequestParam(name = "manufacturers") List<Long> manufacturers,
														  @RequestParam(name = "meterTypes") List<Long> meterTypes){
		try {
			return new ResponseEntity<>(alertService.getCodes(manufacturers, meterTypes), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error get codes", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_OWNER')")
	@RequestMapping(value = "/alert/consumption", method = RequestMethod.POST)
	public ResponseEntity<?> saveAlertConsumption(@RequestBody AlertConsumptionDto alert,
													   @RequestParam(name = "alertOff", required = true) Boolean alertOff) {
		log.info("AlertController - saveAlertConsumption - START");
		try {
			List<AlertConsumptionDto> alertsConsumption = alertService.updateAlertConsumption(alert, alertOff);
			return new ResponseEntity<>(alertsConsumption, HttpStatus.OK);
		} catch (Exception e) {
			log.error("ERROR while saving alert consumption: ", e);
			return new ResponseEntity<>("No se ha podido guardar la alerta de consuomo", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
