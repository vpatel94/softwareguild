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
import luxmd.data.ServiceDatabaseDao.ServiceMapper;
import luxmd.models.Appointment;
import luxmd.models.Doctor;
import luxmd.models.Service;
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
public class DoctorDatabaseDao implements DoctorDao
{

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public DoctorDatabaseDao(JdbcTemplate jdbcTemplate) 
    {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional
    public Doctor createDoctor(Doctor d) 
    {
        final String sql = "INSERT INTO Doctors(FirstName, LastName, "
                + "PhoneNumber) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, d.getFirstName());
            statement.setString(2, d.getLastName());
            statement.setString(3, d.getPhoneNumber());
            return statement;

        }, keyHolder);

        d.setDoctorId(keyHolder.getKey().intValue());

        return d;
    }

    @Override
    public List<Doctor> getAllDoctors() 
    {
        final String sql = "SELECT DoctorID, FirstName, LastName, PhoneNumber FROM Doctors;";
        List<Doctor> doctors = jdbcTemplate.query(sql, new DoctorMapper());
        for(Doctor d : doctors)
        {
            List<Appointment> appts = addAppointmentsToDoctor(d);
            if(appts.isEmpty())
                {d.setAppointments(null);}
            else
                {d.setAppointments(appts);}
        }
        return doctors;
    }

    @Override
    public Doctor getDoctorById(int id) 
    {
        final String sql = "SELECT DoctorID, FirstName, LastName, PhoneNumber "
                + "FROM Doctors WHERE DoctorID = ?;";

        Doctor d = jdbcTemplate.queryForObject(sql, new DoctorMapper(), id);
        List<Appointment> appts = addAppointmentsToDoctor(d);
        if(appts.isEmpty())
            {d.setAppointments(null);}
        else
            {d.setAppointments(appts);}
        return d;
    }
    
    @Override
    public List<Doctor> getDoctorsByServiceId(int id)
    {
        final String sql = "SELECT DoctorID, FirstName, LastName, PhoneNumber "
                + "FROM Doctors WHERE ServiceID = ?;";
        List<Doctor> doctors = jdbcTemplate.query(sql, new DoctorMapper(), id);
        for(Doctor d : doctors)
        {
            List<Appointment> appts = addAppointmentsToDoctor(d);
            if(appts.isEmpty())
                {d.setAppointments(null);}
            else
                {d.setAppointments(appts);}
        }
        return doctors;
    }
    
    @Override
    public Service getServiceFromDoctor(Doctor d)
    {
        final String sql = "SELECT s.ServiceID, s.CareType, s.FlatRate "
                + "FROM Services s JOIN Doctors d ON d.ServiceID = s.ServiceID WHERE DoctorID = ?;";
        Service s = jdbcTemplate.queryForObject(sql, new ServiceMapper(), d.getDoctorId());
        return s;
    }

    @Override
    @Transactional
    public boolean update(Doctor d, Service s) 
    {
        final String sql = "UPDATE Doctors "
                + "SET FirstName = ?, LastName = ?, PhoneNumber = ?, ServiceID = ? "
                + "WHERE DoctorID = ?;";
        return jdbcTemplate.update(sql,
                d.getFirstName(), d.getLastName(), d.getPhoneNumber(), 
                s.getServiceId(), d.getDoctorId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteDoctorById(int id) 
    {
        final String sql1 = "DELETE FROM Appointments WHERE DoctorID = ?;";
        jdbcTemplate.update(sql1, id);
        final String sql2 = "DELETE FROM Doctors WHERE DoctorID = ?;";
        return jdbcTemplate.update(sql2, id) > 0;
    }
    
    @Transactional
    private List<Appointment> addAppointmentsToDoctor(Doctor d)
    {
        final String APPTS_FOR_DOCTOR = "SELECT a.* FROM Appointments a "
                + "JOIN Doctors d ON a.DoctorID = d.DoctorID WHERE d.DoctorID = ?;";
        return jdbcTemplate.query(APPTS_FOR_DOCTOR, new AppointmentMapper(), 
                d.getDoctorId());
    }
    
    public static final class DoctorMapper implements RowMapper<Doctor> 
    {

        @Override
        public Doctor mapRow(ResultSet rs, int index) throws SQLException 
        {
            Doctor d = new Doctor();
            d.setDoctorId(rs.getInt("DoctorID"));
            d.setFirstName(rs.getString("FirstName"));
            d.setLastName(rs.getString("LastName"));
            d.setPhoneNumber(rs.getString("PhoneNumber"));
            return d;
        }
    }
    
}
