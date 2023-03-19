package GUI.dao;
import GUI.entity.SSUser;
import java.util.List;

public interface SSUserDao {
    // 增
    public boolean addEmp(SSUser emp);
    // 删
    public int delEmp(String name);
    // 改
    public int updEmp(SSUser emp);
    // 单查
    public SSUser findOne(String user, String pwd);
    // 查
    public List<SSUser> findAll();
}
