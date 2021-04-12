package com.example.demo.Controller

import com.example.demo.Services.LoginServices
import com.example.demo.Utilidades.CustomRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.text.SimpleDateFormat

@RestController
class LoginController {

    @Autowired
    LoginServices loginServices

    CustomRequest MyCustomsRequests = new CustomRequest();

    @RequestMapping(value="/login/logininsertuser", method = RequestMethod.POST)
    def InsertDepartamento(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
            Date dt = sdformat.parse(MapData.fecha as String);
            println (dt)

            if(loginServices.InsertUser(MapData.nombre as String, MapData.apellido as String, dt, MapData.user as String, MapData.pass as String, MapData.tipo as int)){
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

    @RequestMapping(value="/login/verificar", method = RequestMethod.POST)
    def LoginUser(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            return loginServices.VerifiedLogin(MapData.user as String, MapData.pass as String)
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }

    @RequestMapping(value="/login/test", method = RequestMethod.GET)
    def TEST() {
        return loginServices.test()
    }

}
