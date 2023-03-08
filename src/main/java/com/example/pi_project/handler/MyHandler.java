package com.example.pi_project.handler;


import com.example.pi_project.services.TaxService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MyHandler extends TextWebSocketHandler {

    @Getter
    List<WebSocketSession> list = new ArrayList<>();
    char caractereRecherche = '+';



    @Autowired
    private TaxService emailSenderService;

    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException, InterruptedException, MessagingException {
        log.info("Test message {}", message);
        String ramzi = "hello";

        list.add(session);



        if((message.getPayload().toString().equals("hello"))){


        session.sendMessage(new TextMessage((" hello,what your medical product 1-ROSSMAX Appareil aérosol 2-IH 40 BEURER 3-OXYMETRE CONTEC 4-Chambre d'inhalation 5-OXYMETRE CONTEC")));}


          switch (message.getPayload().toString()){
              case "ROSSMAX Appareil aérosol":
                  session.sendMessage(new TextMessage("ROSSMAX Appareil aérosol price 5 dt  do you want to pass the command 1-YES 2-NO "));


                  break;
              case "IH 40 BEURER":
                  session.sendMessage(new TextMessage("IH 40 BEURER price 5 dt "));
                  break;
              case "OXYMETRE CONTEC":
                  session.sendMessage(new TextMessage("OXYMETRE CONTEC price 5 dt "));
                  break;
              case "Chambre d'inhalation":
                  session.sendMessage(new TextMessage("Chambre d'inhalation price 5 dt "));
                  break;
              case " OXYMETRE CONTEC":
                  session.sendMessage(new TextMessage(" OXYMETRE CONTEC d'inhalationC price 5 dt "));
                  break;
              case "Rien":
                  session.sendMessage(new TextMessage(" choisir un autre produit non trouvé  "));
                  break;

      }

        switch (message.getPayload().toString()){

            case "YES":

                session.sendMessage(new TextMessage(" sucess  please écrire  ton émail "));






                break;
            case "NO":
                session.sendMessage(new TextMessage(" failed "));
                break;

        }
        if(message.getPayload().toString().contains("@")){

            emailSenderService.sendMailWithAttachment(message.getPayload().toString(),"Commande envoyé avec succées","Commande");
        }














          }



      }









