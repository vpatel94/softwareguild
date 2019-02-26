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
import luxmd.data.DoctorDatabaseDao.DoctorMapper;
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
public class ServiceDatabaseDao implements ServiceDao
{

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ServiceDatabaseDao(JdbcTemplate jdbcTemplate) 
    {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional
    public Service createService(Service s) 
    {
        final String sql = "INSERT INTO Services(CareType, FlatRate) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, s.getCareType());
            statement.setInt(2, s.getFlatRate());
            return statement;

        }, keyHolder);

        s.setServiceId(keyHolder.getKey().intValue());

        return s;
    }

    @Override
    public List<Service> getAllServices() 
    {
        final String sql = "SELECT ServiceID, CareType, FlatRate FROM Services;";
        List<Service> services = jdbcTemplate.query(sql, new ServiceMapper());
        for(Service s : services)
        {
            List<Doctor> docs = addDoctorsToService(s);
            if(docs.isEmpty())
                {s.setDoctors(null);}
            else
                {s.setDoctors(docs);}
        }
        return services;
    }

    @Override
    public Service getServiceById(int id) 
    {
        final String sql = "SELECT ServiceID, CareType, FlatRate FROM Services WHERE ServiceID = ?;";
        Service s = jdbcTemplate.queryForObject(sql, new ServiceMapper(), id);
        List<Doctor> docs = addDoctorsToService(s);
        if(docs.isEmpty())
            {s.setDoctors(null);}
        else
            {s.setDoctors(docs);}
        return s;
    }

    @Override
    @Transactional
    public boolean update(Service s) 
    {   
        final String sql = "UPDATE Services "
                + "SET CareType = ?, FlatRate = ? "
                + "WHERE ServiceID = ?;";
        return jdbcTemplate.update(sql,
                s.getCareType(), s.getFlatRate(), s.getServiceId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteServiceById(int id) 
    {
        final String sql1 = "DELETE FROM Doctors WHERE ServiceID = ?;";
        jdbcTemplate.update(sql1, id);
        
        final String sql2 = "DELETE FROM Services WHERE ServiceID = ?;";
        return jdbcTemplate.update(sql2, id) > 0;
    }
    
    @Transactional
    private List<Doctor> addDoctorsToService(Service s)
    {
        final String DOCTORS_FOR_SERVICE = "SELECT d.* FROM Doctors d "
                + "JOIN Services s ON d.ServiceID = s.ServiceID WHERE s.ServiceID = ?;";
        return jdbcTemplate.query(DOCTORS_FOR_SERVICE, new DoctorMapper(), 
                s.getServiceId());
    }
    
    public static final class ServiceMapper implements RowMapper<Service> 
    {

        @Override
        public Service mapRow(ResultSet rs, int index) throws SQLException 
        {
            Service s = new Service();
            s.setServiceId(rs.getInt("ServiceID"));
            s.setCareType(rs.getString("CareType"));
            s.setFlatRate(rs.getInt("FlatRate"));
            return s;
        }
    }
}
