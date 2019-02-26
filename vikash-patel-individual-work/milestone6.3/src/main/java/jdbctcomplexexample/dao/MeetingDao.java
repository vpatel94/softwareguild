package jdbctcomplexexample.dao;

import jdbctcomplexexample.entity.Employee;
import jdbctcomplexexample.entity.Meeting;
import jdbctcomplexexample.entity.Room;
import java.util.List;

/**
 *
 * @author kylerudy
 */
public interface MeetingDao {
    List<Meeting> getAllMeetings();
    Meeting getMeetingByid(int id);
    Meeting addMeeting(Meeting meeting);
    void updateMeeting(Meeting meeting);
    void deleteMeetingById(int id);
    
    List<Meeting> getMeetingsForRoom(Room room);
    List<Meeting> getMeetingsForEmployee(Employee employee);
}
