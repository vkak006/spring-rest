package ls.electric.demo.common.service;

import lombok.extern.slf4j.Slf4j;
import ls.electric.demo.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
@Service
public class UserService {
    public static Logger logger;

    @Autowired
    private MongoTemplate mongoTemplate;

    //select
    public User findByUser(String email){
        User user = mongoTemplate.findOne(Query.query(Criteria.where("email").is(email)),User.class);
        return user;
    }

    //insert
    public Map<String,Object> registerUser(User user){
        Map<String,Object> resultMap = new HashMap<>();
        String resultMessage;

        try{
            logger.info("mongoTemplate save method");
            mongoTemplate.save(user);
            resultMessage = "success";
        }catch (Exception e){
            resultMessage = "error";
        }

        resultMap.put("result",resultMessage);
        return resultMap;
    }

    //delete
    public Map<String,Object> deleteUser(User user){
        return null;
    }

}
