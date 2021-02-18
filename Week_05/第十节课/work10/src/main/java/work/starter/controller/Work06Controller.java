package work.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.starter.service.StudentHikariService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 作业：配置 Hikari 连接池，改进上述操作。提交代码到 Github
 */
@RestController
public class Work06Controller {

    @Autowired
    private StudentHikariService studentHikariService;

    /**
     * 新增
     * @param name
     * @return
     */
    @RequestMapping("/insert/{name}")
    public String select(@PathVariable String name){
        int result = studentHikariService.insert(name);
        if (result<=0){
            return "error";
        }
        return "success";
    }

    /**
     * 删除
     * 示例：http://localhost:8080/delete/121
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String select(@PathVariable int id){
        int result = studentHikariService.delete(id);
        if (result<=0){
            return "error";
        }
        return "success";
    }

    /**
     * 修改
     * 示例：http://localhost:8080/update?name=qqq&id=121
     * @param name
     * @param id
     * @return
     */
    @RequestMapping("/update")
    public String update(String name,int id){
        int result = studentHikariService.update(name,id);
        if (result<=0){
            return "error";
        }
        return "success";
    }

    /**
     * 批量修改
     * 示例：http://localhost:8080/updateBatch
     * @return
     */
    @RequestMapping("/updateBatch")
    public int[] updateBatch(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"studentChouUpdate","studentChou"});
        list.add(new Object[]{"studentLiUpdate","studentLi"});
        list.add(new Object[]{"studentWuUpdate","studentWu"});
        int[] result = studentHikariService.updateBatch(list);
        return result;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/select
     * @return
     */
    @RequestMapping("/select")
    public List<Map<String, Object>> select(){
        return studentHikariService.select();
    }
}
