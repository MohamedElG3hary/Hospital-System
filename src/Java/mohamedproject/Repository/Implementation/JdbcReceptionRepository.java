    package Java.mohamedproject.Repository.Implementation;

    import Java.mohamedproject.Entity.Reception;
    import Java.mohamedproject.Repository.Interfaces.ReceptionRepository;

    import java.sql.*;

    public class JdbcReceptionRepository implements ReceptionRepository {


        @Override
        public int save(Connection connection, Reception reception, int departmentId, int adminId ) throws SQLException {


            int personId = insertPerson(connection, reception);
            insertEmployee(connection, reception, personId, departmentId);
            insertReception(connection, personId, adminId);


            return personId;


        }

        private int insertPerson(Connection connection, Reception reception) throws SQLException {

            String sql = """
                    INSERT INTO Person (name , city , nationality) VALUES (? , ? , ? )
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, reception.getName());
                statement.setString(2, reception.getAddress());
                statement.setString(3, reception.getNationality());

                int rowEffected = statement.executeUpdate();
                if (rowEffected == 0) {
                    throw new SQLException("Creating reception person failed, no rows affected.");
                }
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {

                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                    throw new SQLException("Creating reception person failed, no ID obtained.");


                }

            }


        }

        private void insertEmployee(Connection connection, Reception reception, int personId, int departmentId) throws SQLException {

            String sql = """
                    INSERT INTO Employee (person_id , department_id , salary , work_hours , experience_years)  VALUES (? , ? , ? , ? , ?)
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, personId);
                statement.setInt(2, departmentId);
                statement.setDouble(3, reception.getSalary());
                statement.setInt(4, reception.getWorkHours());
                statement.setInt(5, reception.getExperienceYears());



                int rowsAffected = statement.executeUpdate();

                if (rowsAffected == 0) {
                    throw new SQLException("Creating reception employee failed, no rows affected.");
                }



            }


        }


        private void  insertReception(Connection connection ,int personId , int adminId) throws SQLException {

            String sql = """
                    INSERT INTO Reception (person_id , created_by_admin_id) VALUES (? , ?)
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setInt(1,personId);
                statement.setInt(2,adminId);


                int rowsAffected = statement.executeUpdate();

                if (rowsAffected == 0) {
                    throw new SQLException("Creating reception failed, no rows affected.");
                }




            }



        }


    }


