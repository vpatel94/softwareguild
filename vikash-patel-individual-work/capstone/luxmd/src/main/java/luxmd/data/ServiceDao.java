/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luxmd.data;

import java.util.List;
import luxmd.models.Service;

/**
 *
 * @author vpatel
 */
public interface ServiceDao 
{
    public Service createService(Service s);
    public List<Service> getAllServices();
    public Service getServiceById(int id);
    public boolean update(Service s);
    public boolean deleteServiceById(int id);
}
