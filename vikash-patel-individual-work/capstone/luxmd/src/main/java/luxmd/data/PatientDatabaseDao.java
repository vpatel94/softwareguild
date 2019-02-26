/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luxmd.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import luxmd.data.AppointmentDatabaseDao.AppointmentMapper;
import luxmd.models.Appointment;
import luxmd.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vpatel
 */
@Repository
@Profile("database")
public class PatientDatabaseDao implements PatientDao
{
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public PatientDatabaseDao(JdbcTemplate jdbcTemplate) 
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Patient createPatient(Patient p) 
    {
        final String sql = "INSERT INTO Patients(FirstName, LastName, PhoneNumber, "
                + "StreetAddress, City, State, ZipCode, InternalNote) VALUES(?,?,?,?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, p.getFirstName());
            statement.setString(2, p.getLastName());
            statement.setString(3, p.getPhoneNumber());
            statement.setString(4, p.getStreetAddress());
            statement.setString(5, p.getCity());
            statement.setString(6, p.getState());
            statement.setString(7, p.getZipCode());
            statement.setString(8, p.getInternalNote());
            return statement;

        }, keyHolder);

        p.setPatientId(keyHolder.getKey().intValue());

        return p;
    }

    @Override
    public List<Patient> getAllPatients() 
    {
        final String sql = "SELECT PatientID, FirstName, LastName, PhoneNumber, "
                + "StreetAddress, City, State, ZipCode, InternalNote FROM Patients;";
        List<Patient> patients = jdbcTemplate.query(sql, new PatientMapper());
        for(Patient p : patients)
        {
            List<Appointment> appts = addAppointmentsToPatient(p);
            if(appts.isEmpty())
                {p.setAppointments(null);}
            else
                {p.setAppointments(appts);}
        }
        return patients;
    }

    @Override
    public Patient getPatientById(int id) 
    {
        final String sql = "SELECT PatientID, FirstName, LastName, PhoneNumber, "
                + "StreetAddress, City, State, ZipCode, InternalNote FROM Patients WHERE PatientID = ?;";

        Patient p = jdbcTemplate.queryForObject(sql, new PatientMapper(), id);
        List<Appointment> appts = addAppointmentsToPatient(p);
        if(appts.isEmpty())
            {p.setAppointments(null);}
        else
            {p.setAppointments(appts);}
        return p;
    }

    @Override
    @Transactional
    public boolean update(Patient p) 
    {       
        final String sql = "UPDATE Patients "
                + "SET FirstName = ?, LastName = ?, PhoneNumber = ?, StreetAddress = ?, "
                + "City = ?, State = ?, ZipCode = ?, InternalNote = ? "
                + "WHERE PatientID = ?;";
        return jdbcTemplate.update(sql,
                p.getFirstName(), p.getLastName(), p.getPhoneNumber(), p.getStreetAddress(),
                p.getCity(), p.getState(), p.getZipCode(), p.getInternalNote(), p.getPatientId()) > 0;
    }

    @Override
    @Transactional
    public boolean deletePatientById(int id) 
    {
        final String sql1 = "DELETE FROM Appointments WHERE PatientID = ?;";
        jdbcTemplate.update(sql1, id);
        final String sql2 = "DELETE FROM Patients WHERE PatientID = ?;";
        return jdbcTemplate.update(sql2, id) > 0;
    }
    
    @Override
    @Transactional
    public List<Appointment> addAppointmentsToPatient(Patient p)
    {
        final String APPTS_FOR_PATIENT = "SELECT a.* FROM Appointments a "
                + "JOIN Patients p ON a.PatientID = p.PatientID WHERE p.PatientID = ?;";
        return jdbcTemplate.query(APPTS_FOR_PATIENT, new AppointmentMapper(), 
                p.getPatientId());
    }
    
    public static final class PatientMapper implements RowMapper<Patient> 
    {

        @Override
        public Patient mapRow(ResultSet rs, int index) throws SQLException 
        {
            Patient p = new Patient();
            p.setPatientId(rs.getInt("PatientID"));
            p.setFirstName(rs.getString("FirstName"));
            p.setLastName(rs.getString("LastName"));
            p.setPhoneNumber(rs.getString("PhoneNumber"));
            p.setStreetAddress(rs.getString("StreetAddress"));
            p.setCity(rs.getString("City"));
            p.setState(rs.getString("State"));
            p.setZipCode(rs.getString("ZipCode"));
            p.setInternalNote(rs.getString("InternalNote"));
            return p;
        }
    }
    
}
