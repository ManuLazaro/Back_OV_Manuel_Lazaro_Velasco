package main.OV.controller;

import main.OV.db.entity.CenterEntity;
import main.OV.dto.ClientDto;
import main.OV.service.impl.ICenterService;
import main.OV.dto.CenterDto;
import main.OV.util.Constants;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RepositoryRestController
public class CenterController implements Constants {
    @Autowired
    private ICenterService centerService;

    Logger log = org.apache.logging.log4j.LogManager.getLogger(CenterController.class);



    // @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISTRIBUTOR', 'ROLE_PROPERTY_USER', 'ROLE_COUNCIL_USER', 'ROLE_OWNER', 'ROLE_ENGINEER', 'ROLE_MAINTAINER')")
    @RequestMapping(value = "/centers/centersInfo", method = RequestMethod.GET)
    public ResponseEntity<Page<CenterDto>> getCentersInfo() {
        return null;
    }

   // @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISTRIBUTOR', 'ROLE_PROPERTY_USER', 'ROLE_COUNCIL_USER', 'ROLE_OWNER', 'ROLE_MAINTAINER')")
    @RequestMapping(value = "/centers/centersInfo", method = RequestMethod.GET)
    public ResponseEntity<CenterEntity> getCentersInfoById(@RequestParam(name = "id", required = true) Long id) {

        try {

            return new ResponseEntity<>(centerService.getCenterInfoById(id), HttpStatus.OK);

        } catch (Exception e) {

            log.error("error en getBuildingsInfoById", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @RequestMapping(value = "/centers/clientsCenter", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDto>> getClientsByCenterId(@RequestParam(name = "id", required = true) Long id) {
        try {
            List<ClientDto> clients = centerService.getClientsByCenterId(id);
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
