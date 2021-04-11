package com.example.demo.database

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class Sql {
    @Autowired
    JdbcTemplate template

    List<Map<String, Object>> executeQueryAsList(String sql) {
        try {
            return template.queryForList(sql)
        } catch (Exception e) {
            println e.getMessage()
            println e.printStackTrace()
            return new ArrayList<Map<String,Object>>()
        }
    }

    Map<String, Object> executeQueryAsMap(String sql) {
        try {
            return template.queryForMap(sql)
        } catch (Exception e) {
            println e.getMessage()
            println e.printStackTrace()
            return new HashMap<String, Object>()
        }
    }

    Boolean executeQueryInsertUpdate(String sql) {
        try {
            template.execute(sql);
            return true;
        } catch (Exception e) {
            println e.getMessage()
            println e.printStackTrace()
            //template.execute("ROLLBACK WORK:")
            return false;
        }
    }

}

