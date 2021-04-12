package com.example.demo.Services

import com.example.demo.database.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ComplementosServices {

    @Autowired
    Sql sql

    Map GetListRentados(int idUser){
        String query = "SELECT \n" +
                "  count(CC.*)AS cantidad\n" +
                "FROM \n" +
                "  public.\"t_CurrentCarrosRentados\" as CC\n" +
                "  WHERE CC.\"ID_Usuario\" = ${idUser}"

        Map Mapa = sql.executeQueryAsMap(query)


        query = "SELECT \n" +
                "  CC.\"ID_Carro\" AS idCar,\n" +
                "  CC.\"ID_Usuario\" AS idUser,\n" +
                "  CC.fecha,\n" +
                "  CC.\"ID_RentCar\" , \n" +
                "  C.\"nombreCar\" AS nombre\n" +
                "FROM \n" +
                "  public.\"t_CurrentCarrosRentados\" AS CC\n" +
                "  INNER JOIN PUBLIC.\"t_Carro\" as C\n" +
                "  ON CC.\"ID_Carro\" = C.\"ID\" \n" +
                "  WHERE CC.\"ID_Usuario\" = ${idUser}"

        Mapa.get("listaCarros", sql.executeQueryAsList(query))

        return Mapa
    }

    Boolean InsertReporte(int carro, String des, int idRent){
        String query = "INSERT INTO \n" +
                "  public.\"t_ReportesAveria\"\n" +
                "(\n" +
                "  \"ID_Carro\",\n" +
                "  fecha,\n" +
                "  descripcion, \"ID_RentCar\" \n" +
                ")\n" +
                "VALUES (\n" +
                "  ${carro},\n" +
                "  now(),\n" +
                "  '${des}' , ${idRent}\n" +
                ");"

        return sql.executeQueryInsertUpdate(query)
    }

    List GetAverias(int idUser){

        String query = "SELECT \n" +
                "  R.\"ID\" AS idRentCar\n" +
                "FROM \n" +
                "  public.\"t_RentCar\" AS R WHERE R.\"idUser\" = ${idUser}";

        List rentcars = sql.executeQueryAsList(query);

                query = "SELECT \n" +
                "  R.*,\n" +
                "  RC.nombre AS nombreRent\n" +
                "FROM \n" +
                "  public.\"t_ReportesAveria\" AS R \n" +
                "  INNER JOIN PUBLIC.\"t_RentCar\" AS RC \n" +
                "  ON R.\"ID_RentCar\" = RC.\"ID\"\n" +
                "  WHERE R.activo = TRUE AND ( "

        if(rentcars.size() > 1){
            for (int i = 0; i < rentcars.size(); i++) {
                if(i == 0){
                    query = query + "R.\"ID_RentCar\" = ${rentcars[i].idrentcar} OR "
                }
                else{
                    query = query + "R.\"ID_RentCar\" = ${rentcars[i].idrentcar} )"
                }

            }
        }
        else{
            query = query + "C.\"ID_RentCar\" = ${rentcars[0].idrentcar} )"
        }

        println(query)
        return sql.executeQueryAsList(query)

    }

}
