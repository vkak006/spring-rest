package ls.electric.demo.common.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.bulk.InsertRequest;
import lombok.extern.slf4j.Slf4j;
import ls.electric.demo.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Service
public class UserService {
    public static Logger logger;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> findAll(){
        return mongoTemplate.findAll(User.class);
    }

    //select
    public User findByUser(String email){
        return mongoTemplate.findOne(
                Query.query(Criteria.where("email").is(email)),
                User.class);
    }

    //insert
    public void registerUser(User user){
        User result = mongoTemplate.save(user);
    }

    //update
    public void modifyUser(String id, String password){
        UpdateResult result = mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(id)),
                Update.update("password", password),
                User.class);
    }

    //delete
    public void removeUser(String id){
        DeleteResult result = mongoTemplate.remove(
                Query.query(Criteria.where("id").is(id)),
                User.class
        );
    }

}
