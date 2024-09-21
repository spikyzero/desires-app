package ua.com.desires.app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.desires.app.controller.dto.DesireDTO;
import ua.com.desires.app.controller.response.ApiResponse;
import ua.com.desires.app.exception.UserNotFoundException;
import ua.com.desires.app.facade.DesireFacade;

@RestController
@RequestMapping("/api/v1/desires")
public class DesireController {

    private final DesireFacade desireFacade;

    @Autowired
    public DesireController(DesireFacade desireFacade) {
        this.desireFacade = desireFacade;
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/add")
    public ResponseEntity<?> addDesire(@Valid @ModelAttribute DesireDTO desireDTO) {
        try {
            desireFacade.saveDesire(desireDTO);
            return new ResponseEntity<>(new ApiResponse("Addition successful"), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(String.format("Could not add desire for userId: '%d'", desireDTO.getUserId())), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("Addition failed"), HttpStatus.BAD_REQUEST);
        }
    }

}