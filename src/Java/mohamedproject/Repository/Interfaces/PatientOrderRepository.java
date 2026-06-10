package Java.mohamedproject.Repository.Interfaces;

import Java.mohamedproject.DTO.PatientOrderDTO;
import Java.mohamedproject.Entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientOrderRepository {


     int save(Connection connection, Order order, int patientId) throws SQLException;



     ArrayList<PatientOrderDTO> findAllOrders(Connection connection)throws SQLException;
}
