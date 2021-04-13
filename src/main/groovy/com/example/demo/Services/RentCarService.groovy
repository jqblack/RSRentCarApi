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

    Boolean RentarCar(int idCar, int idUser, int idRent, int cantDias){
        String query = "INSERT INTO \n" +
                "  public.\"t_CurrentCarrosRentados\"\n" +
                "(\n" +
                "  \"ID_Carro\",\n" +
                "  \"ID_Usuario\",\n" +
                "  fecha,\n" +
                "  \"ID_RentCar\" , \"cantDias\"\n" +
                ")\n" +
                "VALUES (\n" +
                "  ${idCar},\n" +
                "  ${idUser},\n" +
                "  now(),\n" +
                "  ${idRent} , ${cantDias}\n" +
                ");"

        return sql.executeQueryInsertUpdate(query)
    }

    List GetRentados(int idUser){
        String query = "SELECT \n" +
                "  R.\"ID\" AS idRentCar\n" +
                "FROM \n" +
                "  public.\"t_RentCar\" AS R WHERE R.\"idUser\" = ${idUser}";

        List rentcars = sql.executeQueryAsList(query);

        query = "SELECT \n" +
                "  CC.*,\n" +
                "  C.\"nombreCar\",\n" +
                "  r.nombre as nombreRent\n" +
                "FROM \n" +
                "  public.\"t_CurrentCarrosRentados\" as CC\n" +
                "  INNER JOIN PUBLIC.\"t_Carro\" as C\n" +
                "  ON CC.\"ID_Carro\" = C.\"ID\"\n" +
                "  INNER JOIN PUBLIC.\"t_RentCar\" AS R\n" +
                "  ON CC.\"ID_RentCar\" = R.\"ID\"\n" +
                "  WHERE ( "

        println(rentcars.size());

        if(rentcars.size() > 0){
            if(rentcars.size() > 1){
                for (int i = 0; i < rentcars.size(); i++) {
                    if(i == rentcars.size() -1){
                        query = query + "R.\"ID\" = ${rentcars[i].idrentcar} )"

                    }
                    else{
                        query = query + "R.\"ID\" = ${rentcars[i].idrentcar} OR "
                    }

                }
            }
            else{
                query = query + "R.\"ID\" = ${rentcars[0].idrentcar} )"
            }

            println(query)
            return sql.executeQueryAsList(query)
        }

        return []

    }

}
