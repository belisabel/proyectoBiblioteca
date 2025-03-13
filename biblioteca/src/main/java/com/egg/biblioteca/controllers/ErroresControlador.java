package com.egg.biblioteca.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErroresControlador implements ErrorController {
    // Método para manejar las solicitudes a la ruta "/error"

    @RequestMapping(value = "/error", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
        ModelAndView errorPage = new ModelAndView("error");
        String errorMsg = "";
        Integer httpErrorCode = getErrorCode(httpRequest);
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "El recurso solicitado no existe.";
                break;
            }
            case 403: {
                errorMsg = "No tiene permisos para acceder al recurso.";
                break;
            }
            case 401: {
                errorMsg = "No se encuentra autorizado.";
                break;
            }
            case 404: {
                errorMsg = "El recurso solicitado no fue encontrado.";
                break;
            }
            case 500: {
                errorMsg = "Ocurrió un error interno.";
                break;
            }
            default: {
                errorMsg = "Se produjo un error inesperado.";
                break;
            }
        }
        // Agrega el código de error y el mensaje de error al modelo para mostrar en
        // lavista

        errorPage.addObject("codigo", httpErrorCode);
        errorPage.addObject("mensaje", errorMsg);
        return errorPage;
    }

    // Método para obtener el código de estado HTTP del objeto HttpServletRequest
    private int getErrorCode(HttpServletRequest httpRequest) {
        Object statusCode = httpRequest.getAttribute("jakarta.servlet.error.status_code");
        if (statusCode instanceof Integer) {
            return (Integer) statusCode;
        }
        // Maneja el caso en que el atributo es null o no es un Integer
        return -1; // O puedes usar otro valor por defecto adecuado
    }

    // Método que devuelve la ruta a la página de error
    public String getErrorPath() {
        return "/error.html";
    }
}