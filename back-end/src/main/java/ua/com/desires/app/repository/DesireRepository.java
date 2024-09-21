package ua.com.desires.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.desires.app.model.Desire;

import java.util.List;

public interface DesireRepository extends JpaRepository<Desire, Long> {

    List<Desire> findAllByUserId(Long userId);

}