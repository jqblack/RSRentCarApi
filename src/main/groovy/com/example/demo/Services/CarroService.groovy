package com.example.demo.Services

import com.example.demo.database.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CarroService {

    @Autowired
    Sql sql

    List GetAllCarbyRent(int idRent){
        String query = "SELECT \n" +
                "  C.\"ID\" AS \"ID\",\n" +
                "  C.matricula,\n" +
                "  C.scoreneeded,\n" +
                "  C.\"ID_TipoCarro\",\n" +
                "  C.\"ID_RentCar\",\n" +
                "  C.\"ID_Color\",\n" +
                "  C.\"ID_Marca\",\n" +
                "  C.\"ID_Categoria\",\n" +
                "  C.\"ID_status\",\n" +
                "  C.\"precioAlquiler\",\n" +
                "  C.\"nombreCar\" AS nombre,\n" +
                "  C.\"imgCar\",\n" +
                "  M.descripcion AS marca\n" +
                "FROM \n" +
                "  public.\"t_Carro\" AS C\n" +
                "  INNER JOIN public.\"t_Marca\" AS M\n" +
                "  ON M.\"ID\" = C.\"ID\" WHERE C.activo = TRUE AND C.\"ID_RentCar\" = ${idRent}"

        return sql.executeQueryAsList(query)
    }

    List getcarClient(int scoreUser){
        String query = "SELECT \n" +
                "  C.\"ID\",\n" +
                "  C.matricula,\n" +
                "  C.scoreneeded,\n" +
                "  C.\"ID_TipoCarro\",\n" +
                "  C.\"ID_RentCar\",\n" +
                "  C.\"ID_Color\",\n" +
                "  C.\"ID_Marca\",\n" +
                "  C.\"ID_Categoria\",\n" +
                "  C.\"ID_status\",\n" +
                "  C.\"precioAlquiler\",\n" +
                "  C.\"nombreCar\",\n" +
                "  C.\"imgCar\",\n" +
                "  C.activo,\n" +
                "  M.descripcion AS marca,\n" +
                "  R.nombre AS nombrerent\n" +
                "FROM \n" +
                "  public.\"t_Carro\" AS C\n" +
                "  INNER JOIN PUBLIC.\"t_RentCar\" AS R\n" +
                "  ON C.\"ID_RentCar\" = R.\"ID\"\n" +
                "  INNER JOIN PUBLIC.\"t_Marca\" AS M\n" +
                "  ON C.\"ID_Marca\" = M.\"ID\"\n" +
                "  WHERE C.activo = TRUE AND C.scoreneeded <= ${scoreUser}"

        return sql.executeQueryAsList(query)
    }


    List GetAllCars(int idUser){
        String query = "SELECT \n" +
                "  R.\"ID\" AS idRentCar\n" +
                "FROM \n" +
                "  public.\"t_RentCar\" AS R WHERE R.\"idUser\" = ${idUser}";

        List rentcars = sql.executeQueryAsList(query);

        query = "SELECT \n" +
                "C.\"ID\" AS id,\n" +
                "C.matricula,\n" +
                "C.scoreneeded,\n" +
                "C.\"ID_TipoCarro\",\n" +
                "C.\"ID_RentCar\",\n" +
                "C.\"ID_Color\",\n" +
                "C.\"ID_Marca\",\n" +
                "C.\"ID_Categoria\",\n" +
                "C.\"ID_status\",\n" +
                "C.\"precioAlquiler\",\n" +
                "C.\"nombreCar\" AS nombre,\n" +
                "C.\"imgCar\",\n" +
                "M.descripcion AS marca\n" +
                "FROM \n" +
                "public.\"t_Carro\" AS C\n" +
                "INNER JOIN public.\"t_Marca\" AS M\n" +
                "ON M.\"ID\" = C.\"ID\" \n" +
                "WHERE C.activo = TRUE AND ( "

        if(rentcars.size() > 1){
            for (int i = 0; i < rentcars.size(); i++) {
                if(i == 0){
                    query = query + "C.\"ID_RentCar\" = ${rentcars[i].idrentcar} OR "
                }
                else{
                    query = query + "C.\"ID_RentCar\" = ${rentcars[i].idrentcar} )"
                }

            }
        }
        else{
            query = query + "C.\"ID_RentCar\" = ${rentcars[0].idrentcar} )"
        }

        println(query)
        return sql.executeQueryAsList(query)
    }

    Boolean Insert(String matri, Float score,int tipocar, int idRent, int idColor, int idMarca, int idCate, int precio, String nom, String image){
        String query = "INSERT INTO \n" +
                "  public.\"t_Carro\"\n" +
                "(\n" +
                "  matricula,\n" +
                "  scoreneeded,\n" +
                "  \"ID_TipoCarro\",\n" +
                "  \"ID_RentCar\",\n" +
                "  \"ID_Color\",\n" +
                "  \"ID_Marca\",\n" +
                "  \"ID_Categoria\",\n" +
                "  \"ID_status\",\n" +
                "  \"precioAlquiler\",\n" +
                "  \"nombreCar\", \"imgCar\"\n" +
                ")\n" +
                "VALUES (\n" +
                "  '${matri}',\n" +
                "  ${score},\n" +
                "  ${tipocar},\n" +
                "  ${idRent},\n" +
                "  ${idColor},\n" +
                "  ${idMarca},\n" +
                "  ${idCate},\n" +
                "  1,\n" +
                "  ${precio},\n" +
                "  '${nom}' , '${image}'\n" +
                ");"

        return sql.executeQueryInsertUpdate(query)
    }

    Boolean Update(String matri, Float score,int tipocar, int idRent, int idColor, int idMarca, int idCate, int precio, String nom, int idCar){

        String query = "UPDATE \n" +
                "  public.\"t_Carro\" \n" +
                "SET \n" +
                "  matricula = '${matri}',\n" +
                "  scoreneeded = ${score},\n" +
                "  \"ID_TipoCarro\" = ${tipocar},\n" +
                "  \"ID_RentCar\" = ${idRent},\n" +
                "  \"ID_Color\" = ${idColor},\n" +
                "  \"ID_Marca\" = ${idMarca},\n" +
                "  \"ID_Categoria\" = ${idCate},\n" +
                "  \"precioAlquiler\" = ${precio},\n" +
                "  \"nombreCar\" = '${nom}'\n" +
                "WHERE \n" +
                "  \"ID\" = ${idCar}\n" +
                ";"

        return sql.executeQueryInsertUpdate(query)
    }

    Boolean FueraServicio(int idCar){
        String query = "UPDATE \n" +
                "  public.\"t_Carro\" \n" +
                "SET \n" +
                "  \"ID_status\" = 3\n" +
                "WHERE \n" +
                "  \"ID\" = ${idCar}\n" +
                ";"

        return sql.executeQueryInsertUpdate(query)
    }

    Boolean testimgText(String img){
        String query = "INSERT INTO \n" +
                "  public.testimg\n" +
                "(\n" +
                "  des,\n" +
                "  img\n" +
                ")\n" +
                "VALUES (\n" +
                "  'Hola',\n" +
                "  '${img}'\n" +
                ");"

        return sql.executeQueryInsertUpdate(query)
    }

    Map getIMG(int idImg){
        String query = "SELECT \n" +
                "  id,\n" +
                "  des,\n" +
                "  img\n" +
                "FROM \n" +
                "  public.testimg WHERE id = ${idImg} ;"

        return sql.executeQueryAsMap(query)
    }
}
