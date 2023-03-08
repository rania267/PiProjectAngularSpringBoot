package com.example.pi_project.controllers;


import com.example.pi_project.entities.Invoice;
import com.example.pi_project.model.DecodedQrResponse;
import com.example.pi_project.services.InvoiceService;
import com.example.pi_project.services.QrCodeService;
import com.example.pi_project.services.TaxService;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController


public class QrCodeResource {

    @Autowired
    private QrCodeService qrCodeService;
    TaxService
            taxService;
    @Autowired
    InvoiceService piService;

    @GetMapping(path = "/g/3", produces = MediaType.IMAGE_JPEG_VALUE)
    public void generateQr(@RequestBody Invoice invoice, HttpServletResponse response ) throws MissingRequestValueException, WriterException, IOException {
        if( invoice.toString()==null || invoice.toString()==null || invoice.toString().trim().equals("") ) {
            throw new MissingRequestValueException("QR String is required");
        }
        qrCodeService.generateQr(piService.getAllInvoices().toString(), response.getOutputStream());

        response.getOutputStream().flush();
    }

        @PostMapping(path = "/decode")
    public DecodedQrResponse decodeQr(@RequestParam("qrCode") MultipartFile qrCode) throws IOException, NotFoundException {
        String qrCodeString =  qrCodeService.decodeQr(qrCode.getBytes());
        return new DecodedQrResponse(qrCodeString);
    }

}
