package servicio;

import dao.TestDAO;
import java.util.List;

public class TestServicio {
    private final TestDAO testDAO;
    
    public TestServicio(){
        testDAO = new TestDAO();
    }
    
    public List<String> databases(){
        List<String> databases = testDAO.databases();
        
        return databases;
    }
}
