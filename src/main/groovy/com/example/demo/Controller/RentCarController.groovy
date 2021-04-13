package com.example.demo.Controller

import com.example.demo.Services.RentCarService
import com.example.demo.Utilidades.CustomRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.text.SimpleDateFormat

@RestController
class RentCarController {

    @Autowired
    RentCarService rentCarService

    CustomRequest MyCustomsRequests = new CustomRequest();

    @RequestMapping(value="/rentcar/getAll", method = RequestMethod.POST)
    def GetAllrent(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            return rentCarService.GetAllrent(MapData.idUser as int)
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }

    @RequestMapping(value="/rentcar/insert", method = RequestMethod.POST)
    def Insert(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            if(rentCarService.Insert(MapData.nom as String, MapData.numpro as int, MapData.nummuni as int, MapData.numsec as int, MapData.iduser as int)){
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

    @RequestMapping(value="/rentcar/update", method = RequestMethod.POST)
    def Update(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            if(rentCarService.Update(MapData.nom as String, MapData.numpro as int, MapData.nummuni as int, MapData.numsec as int, MapData.iduser as int, MapData.idRent as int)){
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

    @RequestMapping(value="/rentcar/rentar", method = RequestMethod.POST)
    def Rentar(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            if(rentCarService.RentarCar(MapData.idCar as int, MapData.idUser as int, MapData.idRent as int, MapData.cantDias as int)){
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

}
