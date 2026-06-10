package Java.mohamedproject.Services.Implementation;

import Java.mohamedproject.DTO.PatientDTO;
import Java.mohamedproject.DTO.PatientOrderDTO;
import Java.mohamedproject.Entity.Order;
import Java.mohamedproject.Entity.Patient;
import Java.mohamedproject.Repository.Interfaces.PatientOrderRepository;
import Java.mohamedproject.Repository.Interfaces.PatientRepository;
import Java.mohamedproject.Services.Interfaces.ReceptionService;
import Java.mohamedproject.Services.Results.PatientWithOrderResult;
import Java.mohamedproject.util.DatabaseConnection;

import java.sql.Connection;
import java.util.ArrayList;

public class DefaultReceptionService implements ReceptionService {

    private final PatientRepository patientRepository;
    private final PatientOrderRepository patientOrderRepository;

    public DefaultReceptionService(
            PatientRepository patientRepository,
            PatientOrderRepository patientOrderRepository
    ) {
        this.patientRepository = patientRepository;
        this.patientOrderRepository = patientOrderRepository;
    }

    @Override
    public int addPatient(int receptionId, Patient patient) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            int patientId = patientRepository.save(connection, patient, receptionId);

            connection.commit();

            return patientId;

        } catch (Exception exception) {
            connection.rollback();
            throw exception;

        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public int addOrder(int patientId, Order order) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            int orderId = patientOrderRepository.save(connection, order, patientId);

            connection.commit();

            return orderId;

        } catch (Exception exception) {
            connection.rollback();
            throw exception;

        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public PatientWithOrderResult addPatientWithOrder(int receptionId, Patient patient, Order order ) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            int patientId = patientRepository.save(connection, patient, receptionId);

            int orderId = patientOrderRepository.save(connection, order, patientId);

            connection.commit();

            return new PatientWithOrderResult(patientId, orderId);

        } catch (Exception exception) {
            connection.rollback();
            throw exception;

        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public ArrayList<PatientDTO> getAllPatients() throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        return patientRepository.findAllPatients(connection);
    }


    @Override
    public  ArrayList<PatientOrderDTO> getAllOrders()throws Exception{

        Connection connection = DatabaseConnection.getInstance().getConnection();

        return patientOrderRepository.findAllOrders(connection);

    }




}