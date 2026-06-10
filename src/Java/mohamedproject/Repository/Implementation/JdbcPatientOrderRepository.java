package Java.mohamedproject.Repository.Implementation;

import Java.mohamedproject.DTO.PatientOrderDTO;
import Java.mohamedproject.Entity.Order;
import Java.mohamedproject.Repository.Interfaces.PatientOrderRepository;
import Java.mohamedproject.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class JdbcPatientOrderRepository implements PatientOrderRepository {

   public int save(Connection connection, Order order, int patientId) throws SQLException {

        String sql = """
                INSERT INTO PatientOrder (patient_id , priority_value) VALUES (? , ? ) 
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1,patientId);
            statement.setInt(2,order.getValue());

            int effectedRows = statement.executeUpdate();

            if (effectedRows == 0){
                throw new SQLException("Creating patient order failed, no rows affected.");
            }

            try (ResultSet generatedKey = statement.getGeneratedKeys()){
                if(generatedKey.next()){

                   return generatedKey.getInt(1);
                }

            }
            throw new SQLException("Creating patient order failed, no ID obtained.");

        }

   }


   public ArrayList<PatientOrderDTO> findAllOrders(Connection connection) throws SQLException {
       String sql = """
               
               select  
                          person_id,
                          name,
                          city,
                          nationality,
                          blood_type ,
                          disease ,
                          priority_value
               from patient_order_detial
               order by priority_value  desc ;
               
               """;


       try (PreparedStatement statement = connection.prepareStatement(sql)){

           ResultSet resultSet = statement.executeQuery();
           ArrayList<PatientOrderDTO> getAllOrders = new ArrayList<>();


           while (resultSet.next()) {
               PatientOrderDTO patientOrderDTO = new PatientOrderDTO();

                patientOrderDTO.patientId = resultSet.getInt("person_id");
                patientOrderDTO.name = resultSet.getString("name");
                patientOrderDTO.nationality = resultSet.getString("nationality");
                patientOrderDTO.blood_type = resultSet.getString("blood_type");
                patientOrderDTO.disease = resultSet.getString("disease");
                patientOrderDTO.priority_value = resultSet.getInt("priority_value");


           getAllOrders.add(patientOrderDTO);

           }



           return getAllOrders;



       }






   }






}
