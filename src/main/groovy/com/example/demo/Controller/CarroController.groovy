package com.example.demo.Controller

import com.example.demo.Services.CarroService
import com.example.demo.Utilidades.CustomRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class CarroController {

    @Autowired
    CarroService carroService

    CustomRequest MyCustomsRequests = new CustomRequest();

    @RequestMapping(value="/car/getAllbyrent", method = RequestMethod.POST)
    def GetAllrent(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            return carroService.GetAllCarbyRent(MapData.idRent as int)
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }

    @RequestMapping(value="/car/getAllCars", method = RequestMethod.POST)
    def GetAllCars(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            return carroService.GetAllCars(MapData.idUser as int)
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }

    @RequestMapping(value="/car/insert", method = RequestMethod.POST)
    def Insert(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            if(carroService.Insert(MapData.matri as String, MapData.score as Float, MapData.tipo as int, MapData.idRent as int, MapData.idColor as int, MapData.idMarca as int,
            MapData.idCate as int,MapData.precio as int, MapData.nombre as String)){
                return MyCustomsRequests.MessageSuccess()
            }
            else{
                return MyCustomsRequests.MessageFailed()
            }
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }

    @RequestMapping(value="/car/update", method = RequestMethod.POST)
    def Update(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            if(carroService.Update(MapData.matri as String, MapData.score as Float, MapData.tipo as int, MapData.idRent as int, MapData.idColor as int, MapData.idMarca as int,
                    MapData.idCate as int,MapData.precio as int, MapData.nombre as String, MapData.idCar as int)){
                return MyCustomsRequests.MessageSuccess()
            }
            else{
                return MyCustomsRequests.MessageFailed()
            }
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }

    @RequestMapping(value="/car/fueraservicio", method = RequestMethod.POST)
    def FueraServicio(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            if(carroService.FueraServicio(MapData.idCar as int)){
                return MyCustomsRequests.MessageSuccess()
            }
            else{
                return MyCustomsRequests.MessageFailed()
            }
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }

    @RequestMapping(value="/car/img", method = RequestMethod.POST)
    def TestIMGText(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            return carroService.testimgText(MapData.f_img as String)
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }

    @RequestMapping(value="/car/getimg", method = RequestMethod.POST)
    def GetIMG(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            return carroService.getIMG(MapData.idIMG as int)
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }
}
