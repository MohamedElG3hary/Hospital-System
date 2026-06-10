package Java.mohamedproject.Services.Interfaces;

import Java.mohamedproject.DTO.PatientDTO;
import Java.mohamedproject.DTO.PatientOrderDTO;
import Java.mohamedproject.Entity.Order;
import Java.mohamedproject.Entity.Patient;
import Java.mohamedproject.Services.Results.PatientWithOrderResult;

import java.util.ArrayList;

public interface ReceptionService {



    int addPatient(int receptionId, Patient patient) throws Exception;

    int addOrder(int patientId, Order order) throws Exception;

    PatientWithOrderResult addPatientWithOrder(int receptionId, Patient patient, Order order) throws Exception;


    ArrayList<PatientDTO> getAllPatients() throws Exception;


    ArrayList<PatientOrderDTO> getAllOrders () throws Exception;







}
