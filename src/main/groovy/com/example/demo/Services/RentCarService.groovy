package com.example.demo.Services

import com.example.demo.database.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class RentCarService {

    @Autowired
    Sql sql

    List GetAllrent(int idUser){
        String query = "SELECT \n" +
                "  RC.*,\n" +
                "  P.\"NombreProvincia\"\n" +
                "FROM \n" +
                "  public.\"t_RentCar\" AS RC \n" +
                "  INNER JOIN PUBLIC.\"t_Provincia\" AS P\n" +
                "  ON RC.\"ID_Provincia\" = P.\"ID\" WHERE RC.\"idUser\" = ${idUser}"

        return sql.executeQueryAsList(query)
    }

    Boolean Insert(String nom, int pro, int muni, int sector, int user){
        String query = "INSERT INTO \n" +
                "  public.\"t_RentCar\"\n" +
                "(\n" +
                "  nombre,\n" +
                "  \"ID_Provincia\",\n" +
                "  \"ID_Municipio\",\n" +
                "  \"ID_Sector\",\n" +
                "  \"idUser\"\n" +
                ")\n" +
                "VALUES (\n" +
                "  '${nom}',\n" +
                "  ${pro},\n" +
                "  ${muni},\n" +
                "  ${sector},\n" +
                "  ${user}\n" +
                ");"
println(query);
        return sql.executeQueryInsertUpdate(query);
    }

    Boolean Update(String nom, int pro, int muni, int sector, int user, int idRent){
        String query = "UPDATE \n" +
                "  public.\"t_RentCar\" \n" +
                "SET \n" +
                "  nombre = '${nom}',\n" +
                "  \"ID_Provincia\" = ${pro},\n" +
                "  \"ID_Municipio\" = ${muni},\n" +
                "  \"ID_Sector\" = ${sector},\n" +
                "  \"idUser\" = ${user}\n" +
                "WHERE \n" +
                "  \"ID\" = ${idRent}\n" +
                ";"

        return sql.executeQueryInsertUpdate(query)
    }
}
