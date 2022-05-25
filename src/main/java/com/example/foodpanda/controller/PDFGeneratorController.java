package com.example.foodpanda.controller;

import com.example.foodpanda.business.PDFGeneratorService;
import com.example.foodpanda.model.Administrator;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * IT WAS AN ATTEMPT BUT IN THE END I COULD NOT FIGURE IT OUT WHY IT WAS NOT WORKING
 */
@Controller
public class PDFGeneratorController
{
    private final PDFGeneratorService pdfGeneratorService;

    @Autowired
    public PDFGeneratorController(PDFGeneratorService pdfGeneratorService)
    {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @PostMapping("admin/menu-pdf")
    public ResponseEntity generatePDF(@RequestBody Administrator currentRestaurant, HttpServletResponse response) throws IOException
    {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Menu";
        String headerValue = "attachment; filename=Menu_" + currentRestaurant.getRestaurantName() + "_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        pdfGeneratorService.exportPDF(currentRestaurant, response);

        return ResponseEntity.ok().body("OK");
    }
}
