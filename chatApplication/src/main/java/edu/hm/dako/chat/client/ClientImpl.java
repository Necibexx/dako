package edu.hm.dako.chat.client;

import edu.hm.dako.chat.common.ExceptionHandler;
import edu.hm.dako.chat.common.SystemConstants;

/**
 * <p/>
 * Verwaltet eine Verbindung zum Server.
 *
 * @author Mandl
 */
public class ClientImpl extends AbstractChatClient {

    /**
     * Konstruktor
     * 
     * @param userInterface
     *          Schnittstelle zum User-Interface
     * @param serverPort
     *          Portnummer des Servers
     * @param remoteServerAddress
     *          IP-Adresse/Hostname des Servers
     */

    public ClientImpl(ClientUserInterface userInterface, int serverPort,
            String remoteServerAddress, String serverType) {

        super(userInterface, serverPort, remoteServerAddress);
        this.serverPort = serverPort;
        this.remoteServerAddress = remoteServerAddress;

        Thread.currentThread().setName("Client");
        threadName = Thread.currentThread().getName();
        
        try {
            //SERVERTYPAUSWAHL
        
            if (serverType.equals(SystemConstants.IMPL_TCP_ADVANCED)) {
                // ADVANCED TCP Server erzeugen
                log.debug("ADVANCED!!!!");
                messageListenerThread = new AdvancedMessageListenerThreadImpl(userInterface,
                                                                              connection, sharedClientData);
            } else {
            	// SIMPLE TCP Server erzeugen
                messageListenerThread = new SimpleMessageListenerThreadImpl(userInterface,
                                                                            connection, sharedClientData);  
            } 
            messageListenerThread.start();
        }  catch (Exception e) {
              ExceptionHandler.logException(e);
            } 
            
            
            
            
            
        
//      switch (serverType) {
//          case SystemConstants.IMPL_TCP_SIMPLE :
//              try {
//                  // Simple TCP Server erzeugen
//                  messageListenerThread = new SimpleMessageListenerThreadImpl(userInterface,
//                                                                              connection, sharedClientData);
//                  messageListenerThread.start();
//                  } catch (Exception e) {
//                    ExceptionHandler.logException(e);
//                  } break;
//          case SystemConstants.IMPL_TCP_ADVANCED :
//              try {
//                  // Simple TCP Server erzeugen
//                  messageListenerThread = new AdvancedMessageListenerThreadImpl(userInterface,
//                                                                              connection, sharedClientData);
//                  messageListenerThread.start();
//                  } catch (Exception e) {
//                    ExceptionHandler.logException(e);
//                  } break;                
//      }

    }
}