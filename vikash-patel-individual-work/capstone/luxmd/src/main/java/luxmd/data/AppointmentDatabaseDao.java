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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import luxmd.data.DoctorDatabaseDao.DoctorMapper;
import luxmd.data.PatientDatabaseDao.PatientMapper;
import luxmd.models.Appointment;
import luxmd.models.Doctor;
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
public class AppointmentDatabaseDao implements AppointmentDao
{

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public AppointmentDatabaseDao(JdbcTemplate jdbcTemplate) 
    {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional
    public Appointment createAppointment(Appointment a) 
    {
        final String sql = "INSERT INTO Appointments(Date, Time, Notes, "
                + "TotalDue, DueDate) VALUES(?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setDate(1, java.sql.Date.valueOf(a.getDate()));
            statement.setTime(2, java.sql.Time.valueOf(a.getTime()));
            statement.setString(3, a.getNotes());
            statement.setInt(4, a.getTotalDue());
            statement.setDate(5, java.sql.Date.valueOf(a.getDueDate()));
            return statement;

        }, keyHolder);

        a.setAppointmentId(keyHolder.getKey().intValue());

        return a;
    }

    @Override
    public List<Appointment> getAllAppointments() 
    {
        final String sql = "SELECT AppointmentID, Date, Time, Notes, "
                + "TotalDue, DueDate FROM Appointments;";
        return jdbcTemplate.query(sql, new AppointmentMapper());
    }

    @Override
    public Appointment getAppointmentById(int id) 
    {
        final String sql = "SELECT AppointmentID, Date, Time, Notes, "
                + "TotalDue, DueDate FROM Appointments WHERE AppointmentID = ?;";

        Appointment a = jdbcTemplate.queryForObject(sql, new AppointmentMapper(), id);
        return a;
    }
    
    @Override
    public List<Appointment> getAppointmentsByPatientId(int id)
    {
        final String sql = "SELECT AppointmentID, Date, Time, Notes, "
                + "TotalDue, DueDate FROM Appointments "
                + "WHERE PatientID = ? "
                + "ORDER BY 'Date' ASC, 'Time' ASC;";
        List<Appointment> appts = jdbcTemplate.query(sql, new AppointmentMapper(), id);
        return appts;
    }
    
    @Override
    public List<Appointment> getAppointmentsByDoctorId(int id)
    {
        final String sql = "SELECT AppointmentID, Date, Time, Notes, "
                + "TotalDue, DueDate FROM Appointments "
                + "WHERE DoctorID = ? "
                + "ORDER BY 'Date' ASC, 'Time' ASC;";
        List<Appointment> appts = jdbcTemplate.query(sql, new AppointmentMapper(), id);
        return appts;
    }
    
    @Override
    public Doctor getDoctorFromAppointmentId(int id)
    {
        final String sql = "SELECT d.DoctorID, d.FirstName, d.LastName, d.PhoneNumber "
                + "FROM Doctors d JOIN Appointments a ON d.DoctorID = a.DoctorID "
                + "WHERE AppointmentID = ?;";
        Doctor d = jdbcTemplate.queryForObject(sql, new DoctorMapper(), id);
        return d;
    }
    
    @Override
    public Patient getPatientFromAppointmentId(int id)
    {
        final String sql = "SELECT p.PatientID, p.FirstName, p.LastName, p.PhoneNumber, "
                + "p.StreetAddress, p.City, p.State, p.ZipCode, p.InternalNote "
                + "FROM Patients p JOIN Appointments a ON p.PatientID = a.PatientID "
                + "WHERE AppointmentID = ?;";
        Patient p = jdbcTemplate.queryForObject(sql, new PatientMapper(), id);
        return p;
    }

    @Override
    @Transactional
    public boolean update(Appointment a, Patient p, Doctor d) 
    {
        final String sql = "UPDATE Appointments "
                + "SET PatientID = ?, DoctorID = ?, Date = ?, Time = ?, "
                + "Notes = ?, TotalDue = ?, DueDate = ? "
                + "WHERE AppointmentID = ?;";
        return jdbcTemplate.update(sql,
                p.getPatientId(), d.getDoctorId(), a.getDate(), a.getTime(),
                a.getNotes(), a.getTotalDue(), a.getDueDate(), 
                a.getAppointmentId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteAppointmentById(int id) 
    {
        final String sql = "DELETE FROM Appointments WHERE AppointmentID = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
    
    public static final class AppointmentMapper implements RowMapper<Appointment> 
    {

        @Override
        public Appointment mapRow(ResultSet rs, int index) throws SQLException 
        {
            Appointment a = new Appointment();
            a.setAppointmentId(rs.getInt("AppointmentID"));
            a.setDate(rs.getString("Date"));
            a.setTime(rs.getString("Time"));
            a.setNotes(rs.getString("Notes"));
            a.setTotalDue(rs.getInt("TotalDue"));
            a.setDueDate(rs.getString("DueDate"));
            return a;
        }
    }
    
}
