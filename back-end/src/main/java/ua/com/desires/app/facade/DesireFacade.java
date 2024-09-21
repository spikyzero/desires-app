package ua.com.desires.app.facade;

import ua.com.desires.app.controller.dto.DesireDTO;
import ua.com.desires.app.controller.form.desire.DesireForm;

import java.util.List;

public interface DesireFacade {

    void saveDesire(DesireForm desireForm);

    List<DesireDTO> findAllDesires(long userId);

}